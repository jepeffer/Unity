package Unity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FrmUser extends JPanel implements Form
{
	private int					isAdmin		= 1;
	private int					whichFocus	= 0;						// Controls which textField has focus for the
																		// keyboard
	private JButton				btnAdmin	= new JButton("ADMIN: YES");
	protected JComboBox<String>	comboBox;
	private JTextField			userField	= new JTextField(8);
	private ArrayList<String>	Users		= new ArrayList<String>();
	private int					whichForm	= 0;
	protected JButton			btnSave		= new JButton("");
	private JPasswordField		pinField;
	private JButton				deleteBtn;
	private String				selectedUser;
	JLabel						lblPin;
	JLabel						lblUser;
	JButton						btnCancel;
	
	/**
	 * Create the panel.
	 */
	public FrmUser()
	{
		this.setOpaque(false);
		this.setName("FrmUser");
		setLayout(null);
		setPreferredSize(new Dimension(1080, 700));
		lblUser = new JLabel("User:");
		lblUser.setBounds(75, 25, 90, 40);
		lblUser.setForeground(Color.CYAN);
		lblUser.setFont(new Font("Arial Black", Font.BOLD, 24));
		add(lblUser);
		
		lblPin = new JLabel("PIN:");
		lblPin.setBounds(305, 25, 71, 40);
		lblPin.setForeground(Color.CYAN);
		lblPin.setFont(new Font("Arial Black", Font.BOLD, 24));
		add(lblPin);
		
		btnAdmin.setBounds(30, 580, 200, 100);
		btnAdmin.setOpaque(false);
		btnAdmin.setBorder(null);
		btnAdmin.setForeground(new Color(102, 255, 153));
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(btnAdmin);
		
		btnCancel = new JButton("");
		btnCancel.setBounds(250, 580, 200, 100);
		btnCancel.setBorder(null);
		btnCancel.setIcon(new ImageIcon(FrmUser.class.getResource("/graphics/cancelbtn.jpg")));
		add(btnCancel);
		
		btnSave = new JButton("");
		btnSave.setBounds(460, 580, 200, 100);
		btnSave.setIcon(new ImageIcon(FrmUser.class.getResource("/graphics/savebtn.jpg")));
		btnSave.setBorder(null);
		add(btnSave);
		
		deleteBtn = new JButton("");
		deleteBtn.setBounds(670, 580, 200, 100);
		deleteBtn.setIcon(new ImageIcon(FrmUser.class.getResource("/graphics/deleteuserbtn.jpg")));
		deleteBtn.setBorder(null);
		add(deleteBtn);
		
		pinField = new JPasswordField(4);
		pinField.setName("pinField");
		pinField.setBounds(385, 25, 90, 40);
		add(pinField);
		
		this.setLocation(100, 100);
		this.setVisible(false);
	}
	

	private void eventHandlers()
	{
		btnCancel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.changePanel(Main.MainInstance.getMain());
			}
		});
		deleteBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				try
				{
					createDeleteUserRequest();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnAdmin.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (btnAdmin.getText().equals("ADMIN: YES"))
				{
					isAdmin = 0;
					btnAdmin.setText("ADMIN: NO ");
				}
				else
				{
					isAdmin = 1;
					btnAdmin.setText("ADMIN: YES");
				}
			}
		});
		
		btnSave.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (whichForm == 0) // EditUser
				{
					createEditUserRequest();
				}
				else if (whichForm == 1) // new User
				{
					createNewUserRequest();
				}
			}
		});
		/**
		 * Couldn't think of a cleaner way to do this. When the focus is on the
		 * pinField, set a int so Keyboard can put the text into the correct box. See
		 * whichFocus() below.
		 */
		pinField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				whichFocus = 0; // pinField focus has 0
			}
		});
		/**
		 * Couldn't think of a cleaner way to do this. When the focus is on the
		 * userField, set a int so Keyboard can put the text into the correct box. See
		 * whichFocus() below.
		 */
		userField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				whichFocus = 1; // Number doesn't matter much, just different than 0
				
			}
		});
		/**
		 * If pin is less or equal to 4 and the key doesn't equal backspace...... BEEP
		 * AT THEM YEET I had to remove the beep -yeet
		 */
		pinField.addKeyListener(new java.awt.event.KeyAdapter()
		{
			@SuppressWarnings("deprecation")
			public void keyTyped(java.awt.event.KeyEvent evt)
			{
				if (pinField.getText().length() >= 4 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE))
				{ // Pins gotta
					// be less
					// than 4
					// comon
					// user.
					evt.consume(); // eat the event because they have too many characters in their damn pinbox
				}
			}
		});
		userField.addKeyListener(new java.awt.event.KeyAdapter()
		{
			public void keyTyped(java.awt.event.KeyEvent evt)
			{
				if (userField.getText().length() >= 8 && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE))
				{
					evt.consume(); // eat the event because they have too many characters in their damn userfield
				}
			}
		});
		if (comboBox != null)
		{
			comboBox.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					selectedUser = comboBox.getSelectedItem().toString();
				}
			});
		}
	}
	
	private void createDeleteUserRequest() throws SQLException
	{
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(selectedUser);
		String cmd = String.format("{call UnityVLDPOSUserDelete(?)} ");
		Main.MainInstance.getServer().deleteUserRequest(cmd, arrayList);
	}
	
	/**
	 * Overrides the interface Form's method getNew to return a new FrmUser
	 */
	@Override
	public Form getNew()
	{
		return new FrmUser();
	}
	
	/**
	 * ComboBox is only needed with editUser, not newUser
	 */
	public void loadUsers()
	{
		Users = Main.MainInstance.getServer().loadUsers();
		String listofUsers[] = new String[Users.size()];
		for (int i = 0; i < Users.size(); i++)
		{
			listofUsers[i] = Users.get(i);
		}
		/*
		 * You gotta make the Combobox when you have the data, so I have to create it
		 * here.
		 */
		comboBox = new JComboBox<String>(listofUsers);
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 16));
		comboBox.setBounds(165, 30, 130, 40);
		add(comboBox);
		eventHandlers();
	}
	
	/*
	 * This form is used for newUsers and editUsers, this int will control which
	 * one.
	 */
	public void whichForm(int x)
	{
		whichForm = x;
		
	 	if (whichForm == 1)
	 	{
	 		getDeleteBtn().setVisible(false);
	 	}
	}
	
	/**
	 * Textbox is only needed with newUser, not editUser. Max characters is 8.
	 */
	public void loadTextBox()
	{
		userField.setName("userField");
		userField.setFont(new Font("Arial Black", Font.BOLD, 16));
		userField.setColumns(10);
		userField.setBounds(165, 25, 130, 40);
		add(userField);
		eventHandlers();
	}
	
	/**
	 * Controls when the form is on newUser since there is two textFields which
	 * textField has focus
	 * 
	 * @return the textField with the focus
	 */
	public JTextField whichFocus()
	{
		if (whichFocus == 0) // pinField
		{
			return pinField;
		}
		else // Has to be userField
		{
			return userField;
		}
	}
	
	public void createNewUserRequest()
	{
		String cmd = "";
		String user = getUserField().getText();
		String pin = getPinField().getText();
		if (!checkPin(pin))
		{
			JOptionPane.showMessageDialog(Main.MainInstance, "Pin must be of length 4 and contain only numbers.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (!alphaNumeric(user))
		{
			JOptionPane.showMessageDialog(Main.MainInstance,
					"Username must be less than 8 characters and contain only alphabetic or numeric characters.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			cmd = String.format("INSERT INTO Users (Pin, SecurityLevel, Name) Values ('%s', %d, '%s')", pin, isAdmin,
					user);
			Main.MainInstance.getServer().createRequest(cmd);
			Main.MainInstance.getUser().setVisible(false);
			Main.MainInstance.getKeyboard().setVisible(false);
			Main.MainInstance.changePanel(Main.MainInstance.getConfig());
		}
	}
	
	public void createEditUserRequest()
	{
		// TODO ALPHANUMERIC ONLY
		String cmd = "";
		String user = getComboBox().getSelectedItem().toString();
		String pin = getPinField().getText();
		if (!checkPin(pin))
		{
			JOptionPane.showMessageDialog(Main.MainInstance, "Pin must be of length 4 and contain only numbers.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (!alphaNumeric(user))
		{
			JOptionPane.showMessageDialog(Main.MainInstance,
					"Username must be less than 8 characters and contain only alphabetic or numeric characters.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			cmd = String.format("UPDATE Users SET Pin = '%s', SecurityLevel = %d WHERE Name = '%s'", pin, isAdmin,
					user);
			Main.MainInstance.getServer().createRequest(cmd);
			Main.MainInstance.getUser().setVisible(false);
			Main.MainInstance.getKeyboard().setVisible(false);
			Main.MainInstance.changePanel(Main.MainInstance.getConfig());
		}
	}
	
	private Boolean checkPin(String pinIn)
	{
		char c;
		if (pinIn.length() != 4)
		{
			return false;
		}
		for (int i = 0; i < pinIn.length(); i++)
		{
			c = pinIn.charAt(i);
			
			if (!Character.isDigit(c))
			{
				return false;
			}
		}
		return true;
	}
	
	private Boolean alphaNumeric(String stringIn)
	{
		char c;
		if (stringIn.length() > 8)
		{
			return false;
		}
		for (int i = 0; i < stringIn.length(); i++)
		{
			c = stringIn.charAt(i);
			if (!Character.isLetterOrDigit(c))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private JTextField getUserField()
	{
		return userField;
	}
	
	private JComboBox<String> getComboBox()
	{
		return comboBox;
	}
	
	protected JButton getDeleteBtn()
	{
		return deleteBtn;
	}
	
	JButton getAdminBtn()
	{
		return btnAdmin;
	}
	
	JButton getSaveBtn()
	{
		return btnSave;
	}
	
	JButton getCancelBtn()
	{
		return btnCancel;
	}
	
	JLabel getPinLbl()
	{
		return lblPin;
	}
	
	JLabel getUserLbl()
	{
		return lblUser;
	}
	
	/**
	 * Used on FrmKeyboard when the form is editUser, since the only textField is
	 * userPin
	 * 
	 * @return pinField
	 */
	JTextField getPinField()
	{
		return pinField;
	}
	
	@Override
	public void scale()
	{
		/* SIZE */
		getDeleteBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getAdminBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getSaveBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getCancelBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getUserLbl().setSize((int) (90 * Main.MainInstance.getSM().gxr()),
				(int) (40 * Main.MainInstance.getSM().gyr()));
		
		getPinLbl().setSize((int) (90 * Main.MainInstance.getSM().gxr()), (int) (40 * Main.MainInstance.getSM().gyr()));
		
		getPinField().setSize((int) (90 * Main.MainInstance.getSM().gxr()),
				(int) (40 * Main.MainInstance.getSM().gyr()));
		
		if (getUserField() != null) // Textbox is only needed with newUser, not editUser
			getUserField().setSize((int) (130 * Main.MainInstance.getSM().gxr()),
					(int) (40 * Main.MainInstance.getSM().gyr()));
		
		if (getComboBox() != null)
		{
			getComboBox().setSize((int) (130 * Main.MainInstance.getSM().gxr()),
					(int) (40 * Main.MainInstance.getSM().gyr()));
		}
		
		/* SCALED IMAGES */
		
		getDeleteBtn().setIcon(new ImageIcon(
				((new ImageIcon(FrmMain.class.getResource("/graphics/deleteuserbtn.jpg")).getImage().getScaledInstance(
						getDeleteBtn().getWidth(), getDeleteBtn().getHeight(), Image.SCALE_SMOOTH)))));
		
		getSaveBtn()
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/savebtn.jpg")).getImage()
						.getScaledInstance(getSaveBtn().getWidth(), getSaveBtn().getHeight(), Image.SCALE_SMOOTH)))));
		
		getCancelBtn().setIcon(new ImageIcon(
				((new ImageIcon(FrmMain.class.getResource("/graphics/cancelbtn.jpg")).getImage().getScaledInstance(
						getCancelBtn().getWidth(), getCancelBtn().getHeight(), Image.SCALE_SMOOTH)))));
		
		/* LOCATION */
		if (getComboBox() != null)
		{
			getComboBox().setLocation((int) (165 * Main.MainInstance.getSM().gxr()),
					(int) (25 * Main.MainInstance.getSM().gyr()));
		}
		
		getUserLbl().setLocation((int) (75 * Main.MainInstance.getSM().gxr()),
				(int) (25 * Main.MainInstance.getSM().gyr()));
		
		getPinLbl().setLocation((int) (305 * Main.MainInstance.getSM().gxr()),
				(int) (25 * Main.MainInstance.getSM().gyr()));
		
		getAdminBtn().setLocation((int) (30 * Main.MainInstance.getSM().gxr()),
				(int) (580 * Main.MainInstance.getSM().gyr()));
		
		getCancelBtn().setLocation((int) (250 * Main.MainInstance.getSM().gxr()),
				(int) (580 * Main.MainInstance.getSM().gyr()));
		
		getSaveBtn().setLocation((int) (460 * Main.MainInstance.getSM().gxr()),
				(int) (580 * Main.MainInstance.getSM().gyr()));
		
		getDeleteBtn().setLocation((int) (670 * Main.MainInstance.getSM().gxr()),
				(int) (580 * Main.MainInstance.getSM().gyr()));
		
		getPinField().setLocation((int) (385 * Main.MainInstance.getSM().gxr()),
				(int) (25 * Main.MainInstance.getSM().gyr()));
		
		getUserField().setLocation((int) (165 * Main.MainInstance.getSM().gxr()),
				(int) (25 * Main.MainInstance.getSM().gyr()));
		/* FONT */
		double newFontSize = 22 * Main.MainInstance.getSM().gxr();
		Font font = new Font("Arial Black", Font.BOLD, (int) newFontSize);
		getPinField().setFont(font);
		getUserField().setFont(font);
		getPinLbl().setFont(font);
		getUserLbl().setFont(font);
		if (getComboBox() != null) getComboBox().setFont(font);
		repaint();
		revalidate();
	}
	
}
