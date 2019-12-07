package Unity;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.border.LineBorder;

/**
 * This class functions as a ticket cashing form.
 * 
 * @author American Amusements
 *
 */
@SuppressWarnings("serial")
public class FrmTicket extends JPanel implements Form, ActionListener
{
	/**
	 * Stores the ticket objects
	 */
	ArrayList<Ticket>			tickets				= new ArrayList<Ticket>();
	/**
	 * Used to store the ticket information
	 */
	/**
	 * Create the panel.
	 */
	private double				ticketAmount		= 0.0;
	private JButton				printBtn			= new JButton("");
	private JButton				cashBtn				= new JButton("");
	private JButton				clearBtn			= new JButton("");
	private JButton				ticketBtn			= new JButton(">");
	private String[]			ticketInfo;
	private JTextField			ticketBox			= new JTextField();
	private JLabel				timeWonLabel		= new JLabel("");
	private JLabel				timeCashedLabel		= new JLabel("");
	private JLabel				vendorLabel			= new JLabel("");
	private JLabel				machineLabel		= new JLabel("");
	private JLabel				amountLabel			= new JLabel("");
	private String				currentRequest		= "";
	private ArrayList<String>	currentParams		= new ArrayList<String>();
	private JLabel				lblPlayerTotal		= new JLabel("Player Total:");
	private JLabel				lblTickets			= new JLabel("# Tickets:");
	private JLabel				totalAmountLabel	= new JLabel("");
	private JLabel				totalTicketsLabel	= new JLabel("0");
	private JTextArea			textArea;
	private JLabel				errorLabel			= new JLabel("");
	private final JLabel		lblCashedBy			= new JLabel("");
	private final JLabel		cashedByLabel		= new JLabel("");
	JLabel						lblTicket;
	JLabel						lblTimeWon;
	JLabel						lblTimeCashed;
	JLabel						lblMachine;
	JLabel						lblAmount;
	JLabel						lblVendor;
	private JButton				button_Back;
	private JButton				button_Six;
	private JButton				button_Seven;
	private JButton				button_Eight;
	private JButton				button_Four;
	private JButton				button_Three;
	private JButton				button_Two;
	private JButton				button_Nine;
	private JButton				button_Zero;
	private JButton				button_One;
	private JButton				button_Five;
	
	/**
	 * Creates the Gui, button elements, etc. Sets the cash/print button to disabled
	 * by default. Turns on the Event Listeners.
	 */
	public FrmTicket()
	{
		timerFocus();
		this.setVisible(false);
		setName("FrmTicket");
		setToolTipText("");
		this.setPreferredSize(new Dimension(1132, 700));
		// this.setLocation(TICKET_X, TICKET_Y);
		this.setLocation(Main.MainInstance.getCenterX() / 4, Main.MainInstance.getCenterY() / 4);
		this.setOpaque(false);
		setLayout(null);
		
		errorLabel.setBounds(0, 0, 740, 25);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(errorLabel);
		
		textArea = new JTextArea();
		textArea.setBounds(760, 0, 370, 260);
		textArea.setFont(new Font("Monospaced", Font.BOLD, 16));
		textArea.setEditable(false);
		add(textArea);
		
		lblTicket = new JLabel("Ticket:");
		lblTicket.setBounds(74, 28, 85, 30);
		lblTicket.setForeground(new Color(255, 255, 153));
		lblTicket.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblTicket);
		
		ticketBox.setBounds(169, 30, 525, 30);
		ticketBox.setFont(new Font("Arial Black", Font.PLAIN, 18));
		add(ticketBox);
		ticketBox.setColumns(10);
		
		ticketBtn.setBounds(700, 30, 55, 30);
		ticketBtn.setFont(new Font("Arial Black", Font.PLAIN, 14));
		ticketBtn.setBackground(Color.CYAN);
		add(ticketBtn);
		
		lblTimeWon = new JLabel("Time Won:");
		lblTimeWon.setBounds(34, 70, 130, 30);
		lblTimeWon.setForeground(new Color(255, 255, 153));
		lblTimeWon.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblTimeWon);
		
		timeWonLabel.setBounds(179, 70, 560, 30);
		timeWonLabel.setForeground(Color.WHITE);
		timeWonLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(timeWonLabel);
		
		lblTimeCashed = new JLabel("Time Cashed:");
		lblTimeCashed.setBounds(0, 110, 165, 30);
		lblTimeCashed.setForeground(new Color(255, 255, 153));
		lblTimeCashed.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblTimeCashed);
		
		timeCashedLabel.setBounds(169, 105, 570, 30);
		timeCashedLabel.setForeground(Color.WHITE);
		timeCashedLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(timeCashedLabel);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(58, 145, 101, 30);
		lblAmount.setForeground(new Color(255, 255, 153));
		lblAmount.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblAmount);
		
		amountLabel.setBounds(179, 145, 570, 30);
		amountLabel.setForeground(Color.GREEN);
		amountLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(amountLabel);
		
		lblMachine = new JLabel("Machine:");
		lblMachine.setBounds(54, 180, 110, 30);
		lblMachine.setForeground(new Color(255, 255, 153));
		lblMachine.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblMachine);
		
		machineLabel.setBounds(170, 180, 525, 30);
		machineLabel.setForeground(Color.WHITE);
		machineLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(machineLabel);
		
		lblVendor = new JLabel("Vendor:");
		lblVendor.setBounds(69, 220, 95, 45);
		lblVendor.setForeground(new Color(255, 255, 153));
		lblVendor.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblVendor);
		
		vendorLabel.setBounds(180, 220, 515, 45);
		vendorLabel.setForeground(Color.WHITE);
		vendorLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(vendorLabel);
		
		clearBtn.setBounds(135, 515, 200, 100);
		clearBtn.setBorder(null);
		clearBtn.setIcon(new ImageIcon(FrmTicket.class.getResource("/graphics/clearbtn.jpg")));
		add(clearBtn);
		
		cashBtn.setBounds(345, 515, 200, 100);
		getCashBtn().setEnabled(false);
		cashBtn.setIcon(new ImageIcon(FrmTicket.class.getResource("/graphics/cashbtn.jpg")));
		cashBtn.setBorder(null);
		add(cashBtn);
		
		printBtn.setBounds(555, 515, 200, 100);
		getPrintBtn().setEnabled(false);
		printBtn.setBorder(null);
		printBtn.setIcon(new ImageIcon(FrmTicket.class.getResource("/graphics/printbtn.jpg")));
		add(printBtn);
		
		lblPlayerTotal.setBounds(745, 270, 155, 30);
		lblPlayerTotal.setForeground(new Color(255, 255, 153));
		lblPlayerTotal.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblPlayerTotal);
		
		totalAmountLabel.setBounds(899, 271, 130, 30);
		totalAmountLabel.setForeground(Color.GREEN);
		totalAmountLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(totalAmountLabel);
		
		lblTickets.setBounds(745, 311, 120, 30);
		lblTickets.setForeground(new Color(255, 255, 153));
		lblTickets.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(lblTickets);
		
		totalTicketsLabel.setBounds(875, 311, 15, 30);
		totalTicketsLabel.setForeground(Color.WHITE);
		totalTicketsLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		add(totalTicketsLabel);
		
		lblCashedBy.setBounds(0, 370, 480, 30);
		lblCashedBy.setForeground(Color.RED);
		lblCashedBy.setFont(new Font("Arial Black", Font.PLAIN, 24));
		add(lblCashedBy);
		
		cashedByLabel.setBounds(490, 370, 205, 30);
		cashedByLabel.setForeground(Color.RED);
		cashedByLabel.setFont(new Font("Arial Black", Font.PLAIN, 24));
		add(cashedByLabel);
		
		button_Back = new JButton("<");
		button_Back.setActionCommand("-1");
		button_Back.setMinimumSize(new Dimension(40, 20));
		button_Back.setForeground(new Color(255, 255, 153));
		button_Back.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_Back.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Back.setBackground(Color.BLACK);
		button_Back.setBounds(980, 403, 95, 90);
		add(button_Back);
		
		button_Zero = new JButton("0");
		button_Zero.setMinimumSize(new Dimension(40, 20));
		button_Zero.setForeground(new Color(255, 255, 153));
		button_Zero.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_Zero.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Zero.setBackground(Color.BLACK);
		button_Zero.setBounds(881, 403, 95, 90);
		add(button_Zero);
		
		button_Nine = new JButton("9");
		button_Nine.setMinimumSize(new Dimension(40, 20));
		button_Nine.setForeground(new Color(255, 255, 153));
		button_Nine.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_Nine.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Nine.setBackground(Color.BLACK);
		button_Nine.setBounds(783, 403, 95, 90);
		add(button_Nine);
		
		button_Eight = new JButton("8");
		button_Eight.setMinimumSize(new Dimension(40, 20));
		button_Eight.setForeground(new Color(255, 255, 153));
		button_Eight.setFont(new Font("Arial Black", Font.PLAIN, 56));
		button_Eight.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Eight.setBackground(Color.BLACK);
		button_Eight.setBounds(685, 403, 95, 90);
		add(button_Eight);
		
		button_Seven = new JButton("7");
		button_Seven.setMinimumSize(new Dimension(40, 20));
		button_Seven.setForeground(new Color(255, 255, 153));
		button_Seven.setFont(new Font("Arial Black", Font.PLAIN, 56));
		button_Seven.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Seven.setBackground(Color.BLACK);
		button_Seven.setBounds(588, 403, 95, 90);
		add(button_Seven);
		
		button_Six = new JButton("6");
		button_Six.setMinimumSize(new Dimension(40, 20));
		button_Six.setForeground(new Color(255, 255, 153));
		button_Six.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_Six.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Six.setBackground(Color.BLACK);
		button_Six.setBounds(491, 403, 95, 90);
		add(button_Six);
		
		button_Five = new JButton("5");
		button_Five.setMinimumSize(new Dimension(40, 20));
		button_Five.setForeground(new Color(255, 255, 153));
		button_Five.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_Five.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Five.setBackground(Color.BLACK);
		button_Five.setBounds(393, 403, 95, 90);
		add(button_Five);
		
		button_Four = new JButton("4");
		button_Four.setMinimumSize(new Dimension(40, 20));
		button_Four.setForeground(new Color(255, 255, 153));
		button_Four.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_Four.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Four.setBackground(Color.BLACK);
		button_Four.setBounds(294, 403, 95, 90);
		add(button_Four);
		
		button_Three = new JButton("3");
		button_Three.setMinimumSize(new Dimension(40, 20));
		button_Three.setForeground(new Color(255, 255, 153));
		button_Three.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_Three.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Three.setBackground(Color.BLACK);
		button_Three.setBounds(196, 403, 95, 90);
		add(button_Three);
		
		button_Two = new JButton("2");
		button_Two.setMinimumSize(new Dimension(40, 20));
		button_Two.setForeground(new Color(255, 255, 153));
		button_Two.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_Two.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_Two.setBackground(Color.BLACK);
		button_Two.setBounds(97, 403, 95, 90);
		add(button_Two);
		
		button_One = new JButton("1");
		button_One.setMinimumSize(new Dimension(40, 20));
		button_One.setForeground(new Color(255, 255, 153));
		button_One.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_One.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_One.setBackground(Color.BLACK);
		button_One.setBounds(0, 403, 95, 90);
		add(button_One);
		eventListeners();
		
	}
	
	public void eventListeners()
	{
		
		button_One.addActionListener(this);
		button_Two.addActionListener(this);
		button_Three.addActionListener(this);
		button_Four.addActionListener(this);
		button_Five.addActionListener(this);
		button_Six.addActionListener(this);
		button_Seven.addActionListener(this);
		button_Eight.addActionListener(this);
		button_Nine.addActionListener(this);
		button_Back.addActionListener(this);
		
		clearBtn.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					clearAll();
				}
			}
		});
		ticketBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (getTicketBox().getText().matches("[0-9]+") && getTicketBox().getText().length() == 18)
				{
					createTicketRequest();
				}
				else
				{
					getErrorLabel().setText("The entry must be numeric and 18 characters.");
					// TODO ERROR NUMBERS ONLY
				}
			}
		});
		printBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				
				printReceipts();
			}
		});
		clearBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				clearAll();
			}
		});
		cashBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				createCashTicketRequest();
			}
		});
		
		ticketBox.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
				/* Not neeeded */}
				
			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				// TODO CONFIG AND NUMERIC CHECK
				if (ticketBox.getText().length() == 18)
				{
					if (Main.MainInstance.getUserConfig().getAutoFillTicketInformation())
					{
						if (ticketBox.getText().matches("[0-9]+"))
						{
							createTicketRequest();
						}
						else
						{
							getErrorLabel().setText("Only numeric values allowed.");
							// TODO ERROR MESSAGE NUMBERS ONLY
						}
					}
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
				if (getCashBtn().getText().length() < 18)
				{
					getCashBtn().setEnabled(false);
					getErrorLabel().setText("");
				}
				
			}
		});
	}
	
	/**
	 * Yes I actually had to create a 5 milisecond long timer to get focus lol
	 */
	public void timerFocus()
	{
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{
			public void run()
			{
				getTicketBox().requestFocusInWindow();
			}
		}, 5);
	}
	
	public void createCashTicketRequest()
	{
		currentRequest = "cashTicketTito";
		currentParams.add(getTicketBox().getText());
		currentParams.add(Main.MainInstance.getCurrentUser());
		String response = Main.MainInstance.getXMLRPCServer().startRequest(currentRequest, currentParams);
		parseResponse(response);
		currentParams.clear();
		cashTicket();
	}
	
	private void printReceipts()
	{
		ReceiptPrinting rp = new ReceiptPrinting();
		String toPrint = "";
		toPrint = rp.createStartOfPrinterString();
		
		for (int i = 0; i < tickets.size(); i++)
		{
			toPrint += String.format("%" + rp.padding(tickets.get(i).printString()) + "s" + rp.newLine(1),
					tickets.get(i).printString());
		}
		
		String currentTotal = "CUSTOMER TOTAL: " + totalAmountLabel.getText();
		String tempString = String
				.format(String.format(rp.newLine(1) + rp.verticalLine() + "%1$" + rp.padding(currentTotal) + "s",
						currentTotal + rp.newLine(2)));
		
		toPrint += tempString;
		toPrint += rp.endOfReceipt();
		rp.print(toPrint);
		getTextArea().setEditable(true);
		getTextArea().setText("");
		getTextArea().setEditable(false);
		getTickets().clear();
		clearDeadTicketsFile();
		getPrintBtn().setEnabled(false);
		setTotalTickets();
		Main.MainInstance.setPreviousPrintReceipt(toPrint);
	}
	
	/**
	 * Cashes a ticket if it is not a clone. That check is probably unnessasary(sp)
	 * Increase the total label by the Amount on the ticket, increase the ticket
	 * number label by the number of tickets. Clear all of the field that hold the
	 * ticket information for the next ticket. Write the tickets to a temp file just
	 * incase they aren't printed. Add the new tickets toString() to the box to
	 * indicate they need to be printed to the user.
	 * 
	 * @param ticketLocationIn
	 */
	private void cashTicket()
	{
		addTicket();
		setTotalTickets();
		Runnable clearAll = new Runnable()
		{
			@Override
			public void run()
			{
				clearAll();
			}
		};
		SwingUtilities.invokeLater(clearAll);
		
		//clearAll();
		writeTicketsToFile();
	}
	
	/**
	 * Create a new ticket object.
	 */
	private void addTicket()
	{
		// Ticket #, Time Won, Time Cashed, Amount, MachNum, Vendor
		tickets.add(
				new Ticket(ticketInfo[0], ticketInfo[1], ticketInfo[2], ticketInfo[3], ticketInfo[4], ticketInfo[7]));
		timeCashedLabel.setText(ticketInfo[2]);
		Main.MainInstance.globalTickets.add(
				new Ticket(ticketInfo[0], ticketInfo[1], ticketInfo[2], ticketInfo[3], ticketInfo[4], ticketInfo[7]));
		ticketToBox(tickets.size() - 1);
	}
	
	private void ticketToBox(int ticketLocationIn)
	{
		String temp = String.format("%s - %s - %s\n", tickets.get(ticketLocationIn).getVendor(),
				tickets.get(ticketLocationIn).getTicketNumber(), tickets.get(ticketLocationIn).formatAmount());
		getTextArea().append(temp);
	}
	
	private void createTicketRequest()
	{
		currentRequest = "getTicketInfoTitoSweeps";
		currentParams.add(getTicketBox().getText());
		String response = Main.MainInstance.getXMLRPCServer().startRequest(currentRequest, currentParams);
		currentParams.clear();
		parseResponse(response);
		clearLabels();
		checkTicketStatus();
	}
	
	/**
	 * Finds the ticket in the ticket array and I use the int returned to edit it in
	 * other helper methods
	 * 
	 * @param key - the ticket validation number
	 * @return - where the Ticket is located in the array.
	 */
	@SuppressWarnings("unused")
	private int findTicket(String key)
	{
		int temp = -1;
		for (int i = 0; i < tickets.size(); i++)
		{
			if (tickets.get(i).getTicketNumber().equals(key))
			{
				temp = i;
			}
		}
		return temp;
	}
	
	/**
	 * This method checks for a clone ticket.
	 * 
	 * @param key - TICKET NUMBER
	 * @return TRUE IF CLONE FALSE IF NOT
	 */
	@SuppressWarnings("unused")
	private Boolean checkForClone(String key)
	{
		for (int i = 0; i < tickets.size(); i++)
		{
			if (tickets.get(i).getTicketNumber().equals(key))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the ticket to see its status Method names should give it away
	 * pretty easily.
	 */
	private void checkTicketStatus()
	{
		if (ticketInfo[5].equals("3"))
		{
			alreadyCashed();
		}
		else if (ticketInfo[5].equals("2"))
		{
			notFound();
		}
		else if (ticketInfo[5].equals("4"))
		{
			ticketFailure();
		}
		else if (ticketInfo[5].equals("1"))
		{
			ticketExpired();
		}
		else if (ticketInfo[5].equals("6"))
		{
			ticketFree();
		}
		else if (ticketInfo[5].equals("7"))
		{
			ticketPromo();
		}
		else if (ticketInfo[5].equals("0"))
		{
			getCashBtn().setEnabled(true);
			setTicketInfo(); // WE GOT A FRESH ONE BOYS
		}
		else
		{
			ticketFailure();
		}
	}
	
	private void ticketPromo()
	{
		getErrorLabel().setText("Uncashable Promo Ticket");
		getTimeWonLbl().setText(ticketInfo[1]);
		getTimeCashed().setText(ticketInfo[2]);
		getAmountLabel().setText(formatAmount(ticketInfo[3]));
		getMachineLabel().setText(ticketInfo[4]);
		getVendorLabel().setText(ticketInfo[7]);
		clearButtonGetFocus();
	}
	
	private void ticketFree()
	{
		getErrorLabel().setText("Uncashable Free Entries Ticket");
		getTimeWonLbl().setText(ticketInfo[1]);
		getTimeCashed().setText(ticketInfo[2]);
		getAmountLabel().setText(formatAmount(ticketInfo[3]));
		getMachineLabel().setText(ticketInfo[4]);
		getVendorLabel().setText(ticketInfo[7]);
		clearButtonGetFocus();
	}
	
	private void ticketExpired()
	{
		getErrorLabel().setText("Ticket Expired");
		getTimeWonLbl().setText(ticketInfo[1]);
		getTimeCashed().setText(ticketInfo[2]);
		getAmountLabel().setText(formatAmount(ticketInfo[3]));
		getMachineLabel().setText(ticketInfo[4]);
		getVendorLabel().setText(ticketInfo[7]);
		clearButtonGetFocus();
	}
	
	private void clearButtonGetFocus()
	{
		SwingUtilities.invokeLater(doGetFocus);
	}
	
	Runnable doGetFocus = new Runnable()
	{
		public void run()
		{
			getClearBtn().requestFocusInWindow();
		}
	};
	
	/**
	 * OH NO
	 */
	private void ticketFailure()
	{
		getErrorLabel().setText("Ticket Failure");
		clearButtonGetFocus();
	}
	
	/**
	 * amber.Alert();
	 */
	private void notFound()
	{
		getErrorLabel().setText("Ticket Not Found");
		clearButtonGetFocus();
	}
	
	/**
	 * Since the ticket is already cashed I do not create a ticket object. This
	 * method requests more information from the server to display to the user.
	 */
	private void UVIS()
	{
		ArrayList<String> currentRequest = new ArrayList<String>();
		String cmd = "{call UnityVoucherInfoSweeps(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // Feels
																								// like
																								// a
																								// meme
																								// but its really a
																								// dream
		String response = "";
		currentRequest.add(ticketInfo[4]); // Mach ID
		currentRequest.add(ticketInfo[0]); // VoucherNumber
		currentRequest.add(""); // Amount
		currentRequest.add(""); // IssueVendor
		currentRequest.add(""); // IssueMach
		currentRequest.add(""); // Redeem Vendor Return Param
		currentRequest.add(""); // Reedem Mach Return Param
		currentRequest.add(""); // Redeem Time
		currentRequest.add(""); // Issue Time
		currentRequest.add(""); // Donation
		currentRequest.add(""); // IssueType
		currentRequest.add(""); // Result
		currentRequest.add(""); // Result Desc
		try
		{
			response = Main.MainInstance.getServer().UVIS(cmd, currentRequest);
			System.out.println("UVIS: " + response);
			String tempArray[] = response.split("\\|");
			cmd = "UnityPOSUsers";
			currentRequest.clear();
			
			ArrayList<String> tempResponse = Main.MainInstance.getServer().UnityPOSUsers(cmd, currentRequest);
			ArrayList<String> tempUserID = new ArrayList<String>();
			ArrayList<String> tempUserNames = new ArrayList<String>();
			
			for (int i = 0; i < tempResponse.size(); i += 2)
			{
				tempUserID.add(tempResponse.get(i));
			}
			
			for (int i = 1; i < tempResponse.size(); i += 2)
			{
				tempUserNames.add(tempResponse.get(i));
			}
			
			if (tempArray[0].equals("unity"))
			{
				for (int i = 0; i < tempUserID.size(); i++)
				{
					for (int j = 0; j < tempArray.length; j++)
					{
						if (tempUserID.get(i).equals(tempArray[j]))
						{
							getCashedBy().setText(tempUserNames.get(i));
							getLblCashedBy().setText("Cashed By: ");
							break;
						}
					}
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method updates the GUI and calls UVIS
	 */
	private void alreadyCashed()
	{
		UVIS();
		getTimeWonLbl().setText(ticketInfo[1]);
		getTimeCashed().setText(ticketInfo[2]);
		getAmountLabel().setText(formatAmount(ticketInfo[3]));
		getMachineLabel().setText(ticketInfo[4]);
		getVendorLabel().setText(ticketInfo[7]);
		getErrorLabel().setText("Ticket Already Cashed");
		getTicketBox().setEnabled(false);
		clearButtonGetFocus();
	}
	
	/**
	 * Clears the labels.
	 */
	private void clearLabels()
	{
		getTicketBox().grabFocus();
		getErrorLabel().setText("");
		getTimeWonLbl().setText("");
		getTimeCashed().setText("");
		getAmountLabel().setText("");
		getMachineLabel().setText("");
		getVendorLabel().setText("");
		getCashedBy().setText("");
		getLblCashedBy().setText("");
		getCashedBy().setText("");
		
	}
	
	/**
	 * Clears every field.
	 */
	private void clearAll()
	{
		
		getTicketBox().grabFocus();
		getErrorLabel().setText("");
		getTimeWonLbl().setText("");
		getTimeCashed().setText("");
		getAmountLabel().setText("");
		getMachineLabel().setText("");
		getVendorLabel().setText("");
		getLblCashedBy().setText("");
		getCashedBy().setText("");
		getTicketBox().setEnabled(true);
		getTicketBox().setText("");
	}
	
	/**
	 * Updates the GUI with the ticket information. Adds a ticket if its not a clone
	 * to the ticket arraylist.
	 */
	private void setTicketInfo()
	{ // Ticket #, Time Won, Time Cashed, Amount, MachNum, Vendor
		// tickets.add(new Ticket(ticketInfo[0], ticketInfo[1], ticketInfo[2],
		// ticketInfo[3], ticketInfo[4], ticketInfo[7]));
		
		getTimeWonLbl().setText(ticketInfo[1]);
		getTimeCashed().setText(ticketInfo[2]);
		getAmountLabel().setText(formatAmount(ticketInfo[3]));
		getMachineLabel().setText(ticketInfo[4]);
		getVendorLabel().setText(ticketInfo[7]);
		
		if (Main.MainInstance.getUserConfig().getAutoCashTicket())
		{
			createCashTicketRequest();
		}
	}
	
	/*
	 * Formats the string if a String needs to be formatted
	 * 
	 * @param amountIn
	 * 
	 * @return the formatted string
	 */
	String formatAmount(String amountIn)
	{
		ticketAmount = Double.parseDouble(amountIn);
		ticketAmount = ticketAmount / 100;
		DecimalFormat df = new DecimalFormat("$0.00");
		return df.format(ticketAmount);
	}
	
	/**
	 * Formats the string if a double needs to be formatted
	 * 
	 * @param amountIn
	 * @return the formatted string
	 */
	String formatAmount(Double amountIn)
	{
		amountIn = amountIn / 100;
		DecimalFormat df = new DecimalFormat("$0.00");
		return df.format(amountIn);
	}
	
	/**
	 * Reads the dead tickets from the writeTicketsToFile method and displays them
	 * to the user so they know they can't just not cash tickets. Also sets
	 * totaltickets at bottom.
	 */
	void readDeadTickets()
	{
		FileInputStream f;
		try
		{
			f = new FileInputStream(
					new File("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\\\src\\DeadTickets"));
			ObjectInputStream reader = new ObjectInputStream(f);
			while (true) // life is just a meme
			{
				try
				{
					Ticket ticket = (Ticket) reader.readObject();
					tickets.add(ticket);
					textArea.append(ticket.toString() + '\n');
				}
				catch (EOFException e)
				{
					reader.close();
					f.close();
					break;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (EOFException e)
		{
			// Do nothing, just means it is empty
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		setTotalTickets();
		
	}
	
	private void clearDeadTicketsFile()
	{
		FileOutputStream f;
		try
		{
			f = new FileOutputStream(
					new File("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\\\src\\DeadTickets"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.close();
			f.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * As tickets are cashed they are written to a file just incase they are not
	 * cleared before the program exits.
	 */
	void writeTicketsToFile()
	{
		FileOutputStream f;
		try
		{
			f = new FileOutputStream(
					new File("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\\\src\\DeadTickets"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			for (int i = 0; i < tickets.size(); i++)
			{
				o.writeObject(tickets.get(i));
				
			}
			o.close();
			f.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void removeTicket(String ticketNumberIn)
	{
		
	}
	
	@SuppressWarnings("unused")
	private void clearTickets()
	{
		tickets.clear();
	}
	
	/**
	 * Sets the total tickets label and total label.
	 */
	private void setTotalTickets()
	{
		Integer temp = tickets.size();
		Double totalTemp = 0.0;
		totalTicketsLabel.setText(temp.toString());
		if (tickets.size() == 0)
		{
			totalTemp = 0.0;
			totalAmountLabel.setText(formatAmount(totalTemp));
			return;
		}
		for (int i = 0; i < tickets.size(); i++)
		{
			totalTemp = totalTemp + Double.parseDouble(tickets.get(i).getAmount());
		}
		if (temp > 0)
		{
			getPrintBtn().setEnabled(true);
		}
		totalAmountLabel.setText(formatAmount(totalTemp));
	}
	
	/**
	 * XMLRPC returns all the info with a | in between it, this just breaks that up.
	 * 
	 * @param response
	 */
	private void parseResponse(String response)
	{
		ticketInfo = response.split("\\|");
	}
	
	public JTextField getTicketBox()
	{
		return ticketBox;
	}
	
	@Override
	public Form getNew()
	{
		return new FrmTicket();
	}
	
	@Override
	public void scale()
	{
		getErrorLabel().setSize((int) (740 * Main.MainInstance.getSM().gxr()),
				(int) (25 * Main.MainInstance.getSM().gyr()));
		
		getTextArea().setSize((int) (370 * Main.MainInstance.getSM().gxr()),
				(int) (260 * Main.MainInstance.getSM().gyr()));
		
		getTicketLbl().setSize((int) (85 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getTicketBox().setSize((int) (525 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getTicketBtn().setSize((int) (55 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getTimeWonLbl().setSize((int) (560 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLabelTimeWon().setSize((int) (130 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLabelTimeCashed().setSize((int) (165 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getTimeCashedLbl().setSize((int) (570 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLabelAmount().setSize((int) (100 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getAmountLabel().setSize((int) (570 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLabelMachine().setSize((int) (110 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getMachineLabel().setSize((int) (525 * Main.MainInstance.getSM().gxr()),
				(int) (45 * Main.MainInstance.getSM().gyr()));
		
		getVendorLabel().setSize((int) (515 * Main.MainInstance.getSM().gxr()),
				(int) (45 * Main.MainInstance.getSM().gyr()));
		
		getLabelVendor().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (35 * Main.MainInstance.getSM().gyr()));
		
		getCashBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getPrintBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getClearBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getLabelPlayerTotal().setSize((int) (155 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getTotalAmountLbl().setSize((int) (130 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLabelTickets().setSize((int) (120 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getTotalTicketsLbl().setSize((int) (15 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLabelCashedBy().setSize((int) (480 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getCashedByLabel().setSize((int) (205 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getButton_Back().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Back().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Zero().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_One().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Two().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Three().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Four().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Five().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Six().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Seven().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Eight().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_Nine().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		/* End size */
		
		getErrorLabel().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (0 * Main.MainInstance.getSM().gyr()));
		
		getTextArea().setLocation((int) (760 * Main.MainInstance.getSM().gxr()),
				(int) (0 * Main.MainInstance.getSM().gyr()));
		
		getTicketLbl().setLocation((int) (75 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getTicketBox().setLocation((int) (170 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLabelTimeWon().setLocation((int) (35 * Main.MainInstance.getSM().gxr()),
				(int) (70 * Main.MainInstance.getSM().gyr()));
		
		getTimeWonLbl().setLocation((int) (180 * Main.MainInstance.getSM().gxr()),
				(int) (70 * Main.MainInstance.getSM().gyr()));
		
		getTicketBtn().setLocation((int) (700 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLabelTimeCashed().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (110 * Main.MainInstance.getSM().gyr()));
		
		getTimeCashedLbl().setLocation((int) (170 * Main.MainInstance.getSM().gxr()),
				(int) (105 * Main.MainInstance.getSM().gyr()));
		
		getLabelAmount().setLocation((int) (60 * Main.MainInstance.getSM().gxr()),
				(int) (145 * Main.MainInstance.getSM().gyr()));
		
		getAmountLabel().setLocation((int) (180 * Main.MainInstance.getSM().gxr()),
				(int) (145 * Main.MainInstance.getSM().gyr()));
		
		getLabelMachine().setLocation((int) (55 * Main.MainInstance.getSM().gxr()),
				(int) (180 * Main.MainInstance.getSM().gyr()));
		
		getMachineLabel().setLocation((int) (170 * Main.MainInstance.getSM().gxr()),
				(int) (180 * Main.MainInstance.getSM().gyr()));
		
		getLabelVendor().setLocation((int) (70 * Main.MainInstance.getSM().gxr()),
				(int) (220 * Main.MainInstance.getSM().gyr()));
		
		getVendorLabel().setLocation((int) (180 * Main.MainInstance.getSM().gxr()),
				(int) (220 * Main.MainInstance.getSM().gyr()));
		
		getCashBtn().setLocation((int) (345 * Main.MainInstance.getSM().gxr()),
				(int) (515 * Main.MainInstance.getSM().gyr()));
		
		getPrintBtn().setLocation((int) (555 * Main.MainInstance.getSM().gxr()),
				(int) (515 * Main.MainInstance.getSM().gyr()));
		
		getClearBtn().setLocation((int) (135 * Main.MainInstance.getSM().gxr()),
				(int) (515 * Main.MainInstance.getSM().gyr()));
		
		getLabelPlayerTotal().setLocation((int) (745 * Main.MainInstance.getSM().gxr()),
				(int) (270 * Main.MainInstance.getSM().gyr()));
		
		getTotalAmountLbl().setLocation((int) (900 * Main.MainInstance.getSM().gxr()),
				(int) (270 * Main.MainInstance.getSM().gyr()));
		
		getLabelTickets().setLocation((int) (745 * Main.MainInstance.getSM().gxr()),
				(int) (310 * Main.MainInstance.getSM().gyr()));
		
		getTotalTicketsLbl().setLocation((int) (875 * Main.MainInstance.getSM().gxr()),
				(int) (311 * Main.MainInstance.getSM().gyr()));
		
		getLabelCashedBy().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (370 * Main.MainInstance.getSM().gyr()));
		
		getCashedByLabel().setLocation((int) (490 * Main.MainInstance.getSM().gxr()),
				(int) (370 * Main.MainInstance.getSM().gyr()));
		
		getButton_Back().setLocation((int) (980 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Zero().setLocation((int) (881 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_One().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Two().setLocation((int) (97 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Three().setLocation((int) (196 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Four().setLocation((int) (294 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Five().setLocation((int) (393 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Six().setLocation((int) (491 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Seven().setLocation((int) (588 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Eight().setLocation((int) (685 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		getButton_Nine().setLocation((int) (783 * Main.MainInstance.getSM().gxr()),
				(int) (403 * Main.MainInstance.getSM().gyr()));
		
		/* END LOCATION */
		/* SCALE IMAGE */
		
		getClearBtn()
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/clearbtn.jpg")).getImage()
						.getScaledInstance(getClearBtn().getWidth(), getClearBtn().getHeight(), Image.SCALE_SMOOTH)))));
		
		getCashBtn()
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/cashbtn.jpg")).getImage()
						.getScaledInstance(getCashBtn().getWidth(), getCashBtn().getHeight(), Image.SCALE_SMOOTH)))));
		
		getPrintBtn()
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/printbtn.jpg")).getImage()
						.getScaledInstance(getPrintBtn().getWidth(), getPrintBtn().getHeight(), Image.SCALE_SMOOTH)))));
		/* End Scale */
		
		/* FONT */
		
		double newFontSize = 22 * Main.MainInstance.getSM().gxr();
		Font labelFont = new Font("Arial Black", Font.PLAIN, (int) newFontSize);
		getErrorLabel().setFont(labelFont);
		getTicketLbl().setFont(labelFont);
		getTicketBox().setFont(labelFont);
		getTicketBtn().setFont(labelFont);
		getLabelTimeWon().setFont(labelFont);
		getTimeWonLbl().setFont(labelFont);
		getLabelTimeCashed().setFont(labelFont);
		getTimeCashedLbl().setFont(labelFont);
		getLabelAmount().setFont(labelFont);
		getAmountLabel().setFont(labelFont);
		getLabelMachine().setFont(labelFont);
		getMachineLabel().setFont(labelFont);
		getLabelVendor().setFont(labelFont);
		getVendorLabel().setFont(labelFont);
		getLabelPlayerTotal().setFont(labelFont);
		getTotalAmountLbl().setFont(labelFont);
		getLabelTickets().setFont(labelFont);
		getTotalTicketsLbl().setFont(labelFont);
		getLabelCashedBy().setFont(labelFont);
		getCashedByLabel().setFont(labelFont);
		
		double newButtonFontSize = 56 * Main.MainInstance.getSM().gxr();
		Font buttonFont = new Font("Arial Black", Font.PLAIN, (int) newButtonFontSize);
		
		getButton_Back().setFont(buttonFont);
		getButton_One().setFont(buttonFont);
		getButton_Two().setFont(buttonFont);
		getButton_Three().setFont(buttonFont);
		getButton_Four().setFont(buttonFont);
		getButton_Five().setFont(buttonFont);
		getButton_Six().setFont(buttonFont);
		getButton_Seven().setFont(buttonFont);
		getButton_Eight().setFont(buttonFont);
		getButton_Nine().setFont(buttonFont);
		getButton_Zero().setFont(buttonFont);
		
		double newTextAreaFontSize = 16 * Main.MainInstance.getSM().gxr();
		Font textAreaFont = new Font("Arial Black", Font.PLAIN, (int) newTextAreaFontSize);
		getTextArea().setFont(textAreaFont);
		
		double ticketBtnFontSize = 14 * Main.MainInstance.getSM().gxr();
		Font ticketButtonFont = new Font("Arial Black", Font.PLAIN, (int) ticketBtnFontSize);
		getTicketBtn().setFont(ticketButtonFont);
	}
	
	/* BEGIN ENCAPSULATION */
	
	JButton getTicketBtn()
	{
		return ticketBtn;
	}
	
	JLabel getTimeCashedLbl()
	{
		return timeCashedLabel;
	}
	
	JLabel getLabelTimeCashed()
	{
		return lblTimeCashed;
	}
	
	JLabel getCashedByLabel()
	{
		return cashedByLabel;
	}
	
	JLabel getLabelCashedBy()
	{
		return lblCashedBy;
	}
	
	JLabel getTotalTicketsLbl()
	{
		return totalTicketsLabel;
	}
	
	JLabel getLabelTickets()
	{
		return lblTickets;
	}
	
	JLabel getLabelVendor()
	{
		return lblVendor;
	}
	
	JLabel getTicketLbl()
	{
		return lblTicket;
	}
	
	JLabel getLabelAmount()
	{
		return lblAmount;
	}
	
	JLabel getLabelTimeWon()
	{
		return lblTimeWon;
	}
	
	protected JLabel getTimeWonLbl()
	{
		return timeWonLabel;
	}
	
	protected JLabel getTimeCashed()
	{
		return timeCashedLabel;
	}
	
	protected JLabel getVendorLabel()
	{
		return vendorLabel;
	}
	
	protected JLabel getAmountLabel()
	{
		return amountLabel;
	}
	
	protected JLabel getMachineLabel()
	{
		return machineLabel;
	}
	
	JLabel getLabelMachine()
	{
		return lblMachine;
	}
	
	protected JTextField getTicketField()
	{
		return ticketBox;
	}
	
	JLabel getLabelPlayerTotal()
	{
		return lblPlayerTotal;
	}
	
	JLabel getTotalAmountLbl()
	{
		return totalAmountLabel;
	}
	
	protected JTextArea getTextArea()
	{
		return textArea;
	}
	
	protected JButton getCashBtn()
	{
		return cashBtn;
	}
	
	protected JButton getClearBtn()
	{
		return clearBtn;
	}
	
	protected JLabel getErrorLabel()
	{
		return errorLabel;
	}
	
	protected JButton getPrintBtn()
	{
		return printBtn;
	}
	
	protected JLabel getCashedBy()
	{
		return cashedByLabel;
	}
	
	protected JLabel getLblCashedBy()
	{
		return lblCashedBy;
	}
	
	public JButton getButton_Back()
	{
		return button_Back;
	}
	
	public JButton getButton_Six()
	{
		return button_Six;
	}
	
	public JButton getButton_Seven()
	{
		return button_Seven;
	}
	
	public JButton getButton_Eight()
	{
		return button_Eight;
	}
	
	public JButton getButton_Four()
	{
		return button_Four;
	}
	
	public JButton getButton_Three()
	{
		return button_Three;
	}
	
	public JButton getButton_Two()
	{
		return button_Two;
	}
	
	public JButton getButton_Nine()
	{
		return button_Nine;
	}
	
	public JButton getButton_Zero()
	{
		return button_Zero;
	}
	
	public JButton getButton_One()
	{
		return button_One;
	}
	
	public JButton getButton_Five()
	{
		return button_Five;
	}
	
	ArrayList<Ticket> getTickets()
	{
		return tickets;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand() == "-1" && getTicketBox().getText().length() > 0)
			getTicketBox().setText(getTicketBox().getText().substring(0, getTicketBox().getText().length() - 1));
		else if (e.getActionCommand() != "-1") getTicketBox().setText(getTicketBox().getText() + e.getActionCommand());
		
		timerFocus();
		
	}
}
