package Unity;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.awt.Component;

/**
 * This form holds the printer functions, and the NewUsr/EditUsr Functions Also
 * holds the local config, printers are global though.
 * 
 * @author American Amusements
 *
 */
@SuppressWarnings("serial")
public class FrmConfig extends JPanel implements Form
{
	
	private JComboBox<String>	cmbReceipt	= new JComboBox<String>();
	private JComboBox<String>	cmbReport	= new JComboBox<String>();
	private JButton				btnNewUsr	= new JButton("");
	private JButton				btnEditUsr	= new JButton("");
	private JButton				autoCashBtn;
	private JButton				autoFillBtn;
	private Boolean				autoCash;
	private Boolean				autoFill;
	private JLabel				lblPrinters;
	private JLabel				lblReport;
	private JLabel				lblUsers;
	private JLabel				lblReceipt;
	private JLabel				lblTickets;
	/**
	 * The two printers will be saved in this array to be used later.
	 */
	public String[]				printers	= new String[2];
	/**
	 * The receipt Printer
	 */
	private String				receiptPrinter;
	/**
	 * The reportPrinter
	 */
	private String				reportPrinter;
	
	/**
	 * Creates FrmConfig, calls getPrinters to get the printers to display to the
	 * user
	 * 
	 * @throws IOException
	 */
	public FrmConfig() throws IOException
	{
		this.setVisible(false);
		getPrinters(); // Get the printers based on if they have selected a printer already
		checkPrinters(); // Check to see if any printers are already selected by reading a file
		this.setOpaque(false);
		setName("FrmConfig");
		setPreferredSize(new Dimension(1080, 700));
		this.setLocation(25, 65);
		setLayout(null);
		
		lblPrinters = new JLabel("Printers:");
		lblPrinters.setBounds(5, 50, 145, 30);
		lblPrinters.setForeground(new Color(255, 153, 102));
		lblPrinters.setFont(new Font("Arial Black", Font.PLAIN, 26));
		add(lblPrinters);
		
		lblReport = new JLabel("Report Printer:");
		lblReport.setBounds(5, 150, 210, 25);
		lblReport.setForeground(new Color(0, 255, 102));
		lblReport.setFont(new Font("Arial Black", Font.PLAIN, 26));
		add(lblReport);
		
		cmbReport.setBounds(235, 140, 500, 50);
		cmbReport.setBackground(new Color(0, 0, 0));
		cmbReport.setForeground(new Color(255, 255, 51));
		cmbReport.setFont(new Font("Arial Black", Font.PLAIN, 24));
		add(cmbReport);
		
		lblReceipt = new JLabel("Receipt Printer:");
		lblReceipt.setBounds(0, 210, 225, 32);
		lblReceipt.setForeground(new Color(0, 255, 102));
		lblReceipt.setFont(new Font("Arial Black", Font.PLAIN, 26));
		add(lblReceipt);
		
		cmbReceipt.setBounds(235, 200, 500, 50);
		cmbReceipt.setBackground(new Color(0, 0, 0));
		cmbReceipt.setForeground(new Color(255, 255, 51));
		cmbReceipt.setFont(new Font("Arial Black", Font.PLAIN, 24));
		add(cmbReceipt);
		
		lblUsers = new JLabel("Users:");
		lblUsers.setBounds(0, 290, 103, 32);
		lblUsers.setForeground(new Color(255, 153, 102));
		lblUsers.setFont(new Font("Arial Black", Font.PLAIN, 26));
		add(lblUsers);
		
		btnNewUsr.setBounds(0, 320, 200, 100);
		btnNewUsr.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewUsr.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/newuserbtn.jpg"))
				.getImage().getScaledInstance(btnNewUsr.getWidth(), btnNewUsr.getHeight(), Image.SCALE_SMOOTH)))));
		btnNewUsr.setMinimumSize(new Dimension(33, 10));
		btnNewUsr.setBorder(null);
		add(btnNewUsr);
		
		btnEditUsr.setBounds(210, 320, 200, 100);
		btnEditUsr.setBorder(null);
		btnEditUsr.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/edituserbtn.jpg"))
				.getImage().getScaledInstance(btnEditUsr.getWidth(), btnEditUsr.getHeight(), Image.SCALE_SMOOTH)))));
		btnEditUsr.setMinimumSize(new Dimension(33, 10));
		add(btnEditUsr);
		
		lblTickets = new JLabel("Tickets:");
		lblTickets.setBounds(0, 435, 98, 32);
		lblTickets.setForeground(new Color(255, 153, 102));
		lblTickets.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblTickets);
		
		autoCashBtn = new JButton("Auto-Cash Tickets: No");
		autoCashBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
		autoCashBtn.setBounds(0, 460, 250, 100);
		add(autoCashBtn);
		
		autoFillBtn = new JButton("Auto-Fill: No");
		autoFillBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
		autoFillBtn.setBounds(0, 590, 250, 100);
		add(autoFillBtn);
		eventHandlers(); // Now initialize the event handlers otherwise getting/setting the printers will
							// make this go nuts
	}
	
	/**
	 * 
	 */
	protected void readCurrentConfig()
	{
		setAutoCash(Main.MainInstance.getUserConfig().getAutoCashTicket());
		setAutoFill(Main.MainInstance.getUserConfig().getAutoFillTicketInformation());
	}
	
	/**
	 * This method gets the computers printers as an object and adds them to the
	 * combobox as an object
	 */
	private void getPrinters()
	{
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null); // double null because I want
																							// all the printers, not
																							// just some of them
		for (PrintService printer : printServices)
		{
			cmbReceipt.addItem(printer.getName());
			cmbReport.addItem(printer.getName());
		}
	}
	
	/**
	 * Setter for reportPrinter
	 * 
	 * @param reportPrinterIn
	 * @throws IOException
	 */
	/*
	 * public void setReportPrinter(String reportPrinterIn) throws IOException {
	 * reportPrinter = reportPrinterIn; File file = new
	 * File("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\src\\Printers"
	 * ); Scanner in = new Scanner(file); Writer wr = new FileWriter(file);
	 * wr.write(reportPrinter); // write string wr.flush(); wr.close(); }
	 */
	/**
	 * Setter for receiptPrinter
	 * 
	 * @param receiptPrinterIn
	 * @throws IOException
	 */
	public void setPrinters(String receiptPrinterIn, String reportPrinterIn) throws IOException
	{
		receiptPrinter = receiptPrinterIn;
		reportPrinter = reportPrinterIn;
		Writer wr = new FileWriter("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\src\\Printers");
		wr.write(reportPrinter + "\n" + receiptPrinter); // write string
		wr.flush();
		wr.close();
	}
	
	/**
	 * This method checks to see if any printers have been chosen yet, if not rip
	 * 
	 * @throws IOException
	 */
	public void checkPrinters() throws IOException
	{
		
		File file = new File("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\src\\Printers");
		int counter = 0;
		Scanner in = new Scanner(file);
		while (in.hasNextLine())
		{
			printers[counter] = in.nextLine();
			counter++;
		}
		if (printers[0].equals("x") && printers[1].equals("y"))
		{
			noPrintersSelected();
		}
		else
		{
			reportPrinter = printers[0];
			receiptPrinter = printers[1];
			printersSelected(reportPrinter, receiptPrinter);
		}
		in.close();
	}
	
	/**
	 * Initialize the event handlers for this object.
	 */
	private void eventHandlers()
	{
		/**
		 * This is the receiptPrinter event it listens for a selectedindex change
		 */
		cmbReceipt.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				if (ie.getStateChange() == ItemEvent.SELECTED)
				{
					try
					{
						setPrinters(ie.getItem().toString(), reportPrinter);
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		/**
		 * This is the reportPrinter event, it listens for a selectedindex change
		 */
		cmbReport.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				if (ie.getStateChange() == ItemEvent.SELECTED)
				{
					try
					{
						setPrinters(receiptPrinter, ie.getItem().toString());
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		/**
		 * Opens new user form with keyboard
		 */
		btnNewUsr.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getConfig().setVisible(false); // Do not need a new config, just set it's visiblity to
																	// false
				Main.MainInstance.changePanel(new FrmUser()); // Make a new userform and display it
				Main.MainInstance.getKeyboard().setVisible(true); // Do not need a new keyboard, just sets its
																	// visibility to true
				Main.MainInstance.getKeyboard().setFormControl(2);
				Main.MainInstance.getUser().whichForm(1);
				Main.MainInstance.getUser().loadTextBox();
				Main.MainInstance.getUser().scale(); // This is probably the worst code for the program ~ I can't figure out why I need to scale this
				// and why I need to scale the keyboard here, normally the changepanel method takes care of it. Maybe it has something to deal with
				// how whichForm works, and I'll take a look at that in the future TODO
				Main.MainInstance.getKeyboard().scale();
			}
		});
		btnEditUsr.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getConfig().setVisible(false); // Do not need a new config, just set it's visiblity to
																	// false
				Main.MainInstance.changePanel(Main.MainInstance.getUser()); // Make a new userform and display it
				Main.MainInstance.getKeyboard().setVisible(true); // Do not need a new keyboard, just sets its
																	// visibility to true
				Main.MainInstance.getKeyboard().setFormControl(1);
				Main.MainInstance.getUser().whichForm(0);
				Main.MainInstance.getUser().loadUsers();
				Main.MainInstance.getUser().scale();
				Main.MainInstance.getKeyboard().scale();
			}
		});
		
		autoFillBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (getAutoFillBtn().getName().equals("Yes"))
				{
					setAutoFill(false);
				}
				else
				{
					setAutoFill(true);
				}
			}
		});
		autoCashBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (getAutoCashBtn().getName().equals("Yes"))
				{
					setAutoCash(false);
				}
				else
				{
					setAutoCash(true);
				}
			}
		});
		
	}
	
	/**
	 * If no printers have been selected, set them to the first printers on the
	 * list.
	 */
	private void noPrintersSelected()
	{
		if (cmbReceipt.getComponentCount() > 0 && cmbReport.getComponentCount() > 0)
		{
			printers[0] = cmbReport.getItemAt(0).toString();
			printers[1] = cmbReceipt.getItemAt(0).toString();
			
			try
			{
				setPrinters(printers[0], printers[1]);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			printersSelected("No printers avaliable", "No printers avaliable");
		}
	}
	
	/**
	 * If printers have been selected, this will match the selected printers with
	 * the printers in the list, this is just for GUI reasons, so the user knows
	 * they're printer has been selected properly
	 */
	private void printersSelected(String reportIn, String receiptIn)
	{
		cmbReport.setEditable(true);
		cmbReceipt.setEditable(true);
		cmbReport.setSelectedItem(reportIn);
		cmbReceipt.setSelectedItem(receiptIn);
		cmbReport.setEditable(false);
		cmbReceipt.setEditable(false);
	}
	
	@Override
	public Form getNew()
	{
		try
		{
			return new FrmConfig();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private void setAutoCash(Boolean autoCashIn)
	{
		autoCash = autoCashIn;
		
		if (autoCash)
		{
			// String temp = getAutoCashBtn().getText().replaceAll("No", "Yes");
			getAutoCashBtn().setText("Auto-Cash Ticket: Yes");
			getAutoCashBtn().setName("Yes");
			Main.MainInstance.getUserConfig().setAutoCashTicket(true);
			Main.MainInstance.writeConfigToFile(Main.MainInstance.getUserConfig());
		}
		else
		{
			// String temp = getAutoCashBtn().getText().replaceAll("Yes", "No");
			getAutoCashBtn().setText("Auto-Cash Ticket: No");
			getAutoCashBtn().setName("No");
			Main.MainInstance.getUserConfig().setAutoCashTicket(false);
			Main.MainInstance.writeConfigToFile(Main.MainInstance.getUserConfig());
		}
	}
	
	private void setAutoFill(Boolean autoFillIn)
	{
		autoFill = autoFillIn;
		
		if (autoFill)
		{
			// String temp = getAutoFillBtn().getText().replaceAll("No", "Yes");
			getAutoFillBtn().setText("Auto-Fill Ticket: Yes");
			getAutoFillBtn().setName("Yes");
			Main.MainInstance.getUserConfig().setAutoFillTicketInformation(true);
			Main.MainInstance.writeConfigToFile(Main.MainInstance.getUserConfig());
		}
		else
		{
			// String temp = getAutoFillBtn().getText().replaceAll("Yes", "No");
			getAutoFillBtn().setText("Auto-Fill Ticket: No");
			getAutoFillBtn().setName("No");
			Main.MainInstance.getUserConfig().setAutoFillTicketInformation(false);
			Main.MainInstance.writeConfigToFile(Main.MainInstance.getUserConfig());
		}
		
	}
	
	@Override
	public void scale()
	{
		/* SET SIZE */
		getNewUsrBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getEditUsrBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getAutoCashBtn().setSize((int) (300 * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getAutoFillBtn().setSize((int) (300 * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getTicketLabel().setSize((int) (435 * Main.MainInstance.getSM().gxr()),
				(int) (0 * Main.MainInstance.getSM().gyr()));
		
		getReceiptPrinterLabel().setSize((int) (225 * Main.MainInstance.getSM().gxr()),
				(int) (32 * Main.MainInstance.getSM().gyr()));
		
		getReportPrinterLabel().setSize((int) (210 * Main.MainInstance.getSM().gxr()),
				(int) (25 * Main.MainInstance.getSM().gyr()));
		
		getUserLabel().setSize((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (290 * Main.MainInstance.getSM().gyr()));
		
		getPrinterLabel().setSize((int) (145 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getReceiptComboBox().setSize((int) (500 * Main.MainInstance.getSM().gxr()),
				(int) (50 * Main.MainInstance.getSM().gyr()));
		
		getReportComboBox().setSize((int) (500 * Main.MainInstance.getSM().gxr()),
				(int) (50 * Main.MainInstance.getSM().gyr()));
		
		/* SCALE IMAGE */
		
		/* SET LOCATION */
		/* SET SIZE */
		getNewUsrBtn().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (320 * Main.MainInstance.getSM().gyr()));
		
		getEditUsrBtn().setLocation((int) (210 * Main.MainInstance.getSM().gxr()),
				(int) (320 * Main.MainInstance.getSM().gyr()));
		
		getAutoCashBtn().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (460 * Main.MainInstance.getSM().gyr()));
		
		getAutoFillBtn().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (590 * Main.MainInstance.getSM().gyr()));
		
		getTicketLabel().setLocation((int) (435 * Main.MainInstance.getSM().gxr()),
				(int) (0 * Main.MainInstance.getSM().gyr()));
		
		getReceiptPrinterLabel().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (210 * Main.MainInstance.getSM().gyr()));
		
		getReportPrinterLabel().setLocation((int) (5 * Main.MainInstance.getSM().gxr()),
				(int) (150 * Main.MainInstance.getSM().gyr()));
		
		getUserLabel().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (290 * Main.MainInstance.getSM().gyr()));
		
		getPrinterLabel().setLocation((int) (5 * Main.MainInstance.getSM().gxr()),
				(int) (50 * Main.MainInstance.getSM().gyr()));
		
		getReceiptComboBox().setLocation((int) (235 * Main.MainInstance.getSM().gxr()),
				(int) (200 * Main.MainInstance.getSM().gyr()));
		
		getReportComboBox().setLocation((int) (235 * Main.MainInstance.getSM().gxr()),
				(int) (140 * Main.MainInstance.getSM().gyr()));
		/* FONT */
		double newFont = 22 * Main.MainInstance.getSM().gxr();
		Font font = new Font("Arial Black", Font.BOLD, (int) newFont);
		
		getTicketLabel().setFont(font);
		getReceiptPrinterLabel().setFont(font);
		getReportPrinterLabel().setFont(font);
		getUserLabel().setFont(font);
		getPrinterLabel().setFont(font);
		getReceiptComboBox().setFont(font);
		getReportComboBox().setFont(font);
		newFont = 18 * Main.MainInstance.getSM().gxr();
		Font btnFont = new Font("Arial Black", Font.BOLD, (int) newFont);
		getAutoFillBtn().setFont(btnFont);
		getAutoCashBtn().setFont(btnFont);
		
		repaint();
		revalidate();
	}
	
	private JButton getAutoCashBtn()
	{
		return autoCashBtn;
	}
	
	private JButton getAutoFillBtn()
	{
		return autoFillBtn;
	}
	
	private JButton getNewUsrBtn()
	{
		return btnNewUsr;
	}
	
	private JButton getEditUsrBtn()
	{
		return btnEditUsr;
	}
	
	private JLabel getPrinterLabel()
	{
		return lblPrinters;
	}
	
	private JLabel getReportPrinterLabel()
	{
		return lblReport;
	}
	
	private JLabel getReceiptPrinterLabel()
	{
		return lblReceipt;
	}
	
	private JLabel getTicketLabel()
	{
		return lblTickets;
	}
	
	private JLabel getUserLabel()
	{
		return lblUsers;
	}
	
	/**
	 * Getter for Report Printer
	 * 
	 * @return reportPrinter
	 */
	String getReportPrinter()
	{
		return reportPrinter;
	}
	
	/**
	 * Getter for receiptPrinter
	 * 
	 * @return reportPrinter
	 */
	String getReceiptPrinter()
	{
		return receiptPrinter;
	}
	
	JComboBox<String> getReportComboBox()
	{
		return cmbReport;
	}
	
	JComboBox<String> getReceiptComboBox()
	{
		return cmbReceipt;
	}
	
	
	
}
