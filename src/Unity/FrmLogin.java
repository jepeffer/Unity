package Unity;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

/**
 * Simple login. No encryption, just all plain text. (If it were up to this
 * coder it'd be hashmaps)
 * 
 * @author American Amusements
 */
@SuppressWarnings("serial")
public class FrmLogin extends JPanel implements Form
{
	private JTextField		userField;
	private JButton			loginBtn;
	private JButton			exitBtn;
	private int				whichFocus	= 0;
	private JPasswordField	pinField;
	private JLabel			lblUser;
	private JLabel			lblPin;
	private JLabel			lblError;
	
	/**
	 * Create the panel.
	 */
	public FrmLogin()
	{
		this.setVisible(false);
		setName("FrmLogin");
		Main.MainInstance.setActiveComponent(this);
		Main.MainInstance.getSM().findScreenXandScreenY();
		setPreferredSize(new Dimension(1300, 800));
		this.setLocation(50, 120);
		setFont(new Font("Tahoma", Font.BOLD, 18));
		this.setOpaque(false);
		setLayout(null);
		lblUser = new JLabel("User:");
		lblUser.setBounds(146, 231, 130, 55);
		lblUser.setForeground(new Color(51, 255, 102));
		lblUser.setFont(new Font("Arial Black", Font.BOLD, 36));
		add(lblUser);
		
		userField = new JTextField();
		userField.setBounds(550, 10, 200, 55);
		userField.setFont(new Font("Arial Black", Font.BOLD, 36));
		userField.setName("userField");
		add(userField);
		userField.setColumns(10);
		
		lblPin = new JLabel("Pin:");
		lblPin.setBounds(775, 10, 94, 55);
		lblPin.setForeground(new Color(51, 255, 102));
		lblPin.setFont(new Font("Arial Black", Font.BOLD, 36));
		add(lblPin);
		
		pinField = new JPasswordField();
		pinField.setBounds(870, 10, 140, 55);
		pinField.setName("pinField");
		pinField.setFont(new Font("Arial Black", Font.BOLD, 36));
		add(pinField);
		
		exitBtn = new JButton("");
		exitBtn.setBounds(530, 690, 200, 100);
		exitBtn.setBorder(null);
		add(exitBtn);
		
		loginBtn = new JButton("");
		loginBtn.setBounds(735, 690, 200, 100);
		loginBtn.setBorder(null);
		add(loginBtn);
		
		lblError = new JLabel("ERROR TEST");
		lblError.setBounds(375, 70, 750, 55);
		lblError.setForeground(new Color(239, 9, 21));
		lblError.setFont(new Font("Arial Black", Font.BOLD, 36));
		lblError.setVisible(false);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblError);
		
		scale();
		this.setVisible(true);
		eventHandlers();
	}
	
	private void eventHandlers()
	{
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
				whichFocus = 1;
				lblError.setVisible(false);
			}
		});
		
		loginBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (getPinField().getText().length() > 4)
				{
					// TODO CAN'T BE LONGER THAN 4
					
				}
				else
				{
					checkUserNameAndPin();
				}
			}
		});
		
		exitBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				// TODO LOG WHEN THEY LEAVE TOO
				System.exit(0); // ABORT ABORT
			}
		});
		
		pinField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				lblError.setVisible(false);
			}
		});
		pinField.getDocument().addDocumentListener(new DocumentListener()
		{
			
			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
				lblError.setVisible(false);
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				String password = String.valueOf(((JPasswordField) getPinField()).getPassword());
				lblError.setVisible(false);
				if (password.length() == 4)
				{
					if (password.matches("[0-9]+"))
					{
						if (password.length() > 4)
						{
							getLblError().setText("Password must be of length four or lower.");
							getLblError().setVisible(true);
						}
						else
						{
							if (getUserField().getText().length() > 0)
							{
								checkUserNameAndPin();
							}
						}
					}
					else
					{
						getLblError().setText("Only numeric values allowed.");
						getLblError().setVisible(true);
						clearAll();
						// TODO ERROR MESSAGE NUMBERS ONLY
					}
				}
				else if (password.length() > 4)
				{
					getLblError().setText("Password must be of length four or lower.");
					getLblError().setVisible(true);
					clearAll();
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
			}
		});
	}
	
	/**
	 * Calls the server with the function UnityPOSUsersByNameRequest to verify if
	 * the user's name and pin is in the DB If the response from the server is
	 * empty, it doesn't exist. If the response from the server is equal to the
	 * username sent in, it exists.
	 */
	private void checkUserNameAndPin()
	{
		String[] info;
		ArrayList<String> currentRequest = new ArrayList<String>();
		String cmd = "{call UnityPOSUsersByName(?)}";
		String response = "";
		currentRequest.add(getUserField().getText().toUpperCase());
		try
		{
			response = Main.MainInstance.getServer().UnityPOSUsersByNameRequest(cmd, currentRequest);
			info = response.split("\\|");
			if (!response.equals("") && info[1].equals(getPinField().getText()))
			{
				userLogin(info);
			}
			else
			{
				clearAll();
				getLblError().setText("Login failed.");
				getLblError().setVisible(true);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void clearAll()
	{
		
		Runnable doClear = new Runnable()
		{
			@Override
			public void run()
			{
				getPinField().setText("");
				getUserField().setText("");	
			}
		};
		SwingUtilities.invokeLater(doClear);
	}
	
	/**
	 * If the user name and pin checks out, we will log the users login and make it
	 * official in the db
	 */
	private void userLogin(String[] infoIn)
	{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateString = now.format(formatter);
		ArrayList<String> currentRequest = new ArrayList<String>();
		String cmd = "{call UnityPOSUserLogin(?, ?)}";
		currentRequest.add(infoIn[0]);
		currentRequest.add(dateString);
		try
		{
			Main.MainInstance.login(); // Add the rest of the new components
			Main.MainInstance.getServer().UnityPOSUserLoginRequest(cmd, currentRequest);
			Main.MainInstance.setCurrentUser(infoIn[0]);
			Main.MainInstance.setCurrentUserID(infoIn[2]);
			Main.MainInstance.setCurrentUserSecurity(Integer.parseInt(infoIn[3]));
			Main.MainInstance.setCurrentServerName(Main.MainInstance.getServer().UnityPOSLocation());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) // From AfterLogin method.
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method returns the textfield that has the focus so the keyboard can
	 * input the text in the proper box. 0 is pinField, 1 is userField.
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
	
	protected JTextField getUserField()
	{
		return userField;
	}
	
	protected JTextField getPinField()
	{
		return pinField;
	}
	
	@Override
	public Form getNew()
	{
		return new FrmLogin();
	}
	
	protected JButton getLoginBtn()
	{
		return loginBtn;
	}
	
	protected JButton getExitBtn()
	{
		return exitBtn;
	}
	
	JLabel getLblPin()
	{
		return lblPin;
	}
	
	JLabel getLblUser()
	{
		return lblUser;
	}
	
	JLabel getLblError()
	{
		return lblError;
	}
	
	@Override
	public void scale()
	{
		/* SET THE SIZE */
		getExitBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getLoginBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getUserField().setSize((int) (200 * Main.MainInstance.getSM().gxr()),
				(int) (55 * Main.MainInstance.getSM().gyr()));
		
		getPinField().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (55 * Main.MainInstance.getSM().gyr()));
		
		/* REFRESH IMAGES */
		getExitBtn().setIcon(new ImageIcon(
				((new ImageIcon(FrmMain.class.getResource("/graphics/exitbtn.jpg")).getImage().getScaledInstance(
						ScalingManager.getButtonX(), ScalingManager.getButtonY(), Image.SCALE_SMOOTH)))));
		getLoginBtn().setIcon(new ImageIcon(
				((new ImageIcon(FrmMain.class.getResource("/graphics/loginbtn.jpg")).getImage().getScaledInstance(
						ScalingManager.getButtonX(), ScalingManager.getButtonY(), Image.SCALE_SMOOTH)))));
		/* SET THE LOCATION */
		
		getExitBtn().setLocation((int) (530 * Main.MainInstance.getSM().gxr()),
				(int) (690 * Main.MainInstance.getSM().gyr()));
		
		getLoginBtn().setLocation((int) (735 * Main.MainInstance.getSM().gxr()),
				(int) (690 * Main.MainInstance.getSM().gyr()));
		
		getUserField().setLocation((int) (550 * Main.MainInstance.getSM().gxr()),
				(int) (10 * Main.MainInstance.getSM().gyr()));
		
		getPinField().setLocation((int) (870 * Main.MainInstance.getSM().gxr()),
				(int) (10 * Main.MainInstance.getSM().gyr()));
		
		getLblUser().setLocation((int) (435 * Main.MainInstance.getSM().gxr()),
				(int) (10 * Main.MainInstance.getSM().gyr()));
		
		getLblPin().setLocation((int) (775 * Main.MainInstance.getSM().gxr()),
				(int) (10 * Main.MainInstance.getSM().gyr()));
		
		getLblError().setLocation((int) (375 * Main.MainInstance.getSM().gxr()),
				(int) (70 * Main.MainInstance.getSM().gyr()));
		
		/* CHANGE FONT */
		
		int tempFont = (int) (36 * Main.MainInstance.getSM().gxr());
		Font font = new Font("Arial Black", Font.BOLD, tempFont);
		getLblUser().setFont(font);
		getLblPin().setFont(font);
		getPinField().setFont(font);
		getUserField().setFont(font);
		getLblError().setFont(font);
		
		/* END SCALING */
		repaint();
		revalidate();
	}
}
