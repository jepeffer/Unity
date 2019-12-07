package Unity;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import jssc.SerialPortException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class FrmFreeplay extends JPanel implements Form, ActionListener
{
	Timer				timer;
	private JTextField	ticketValueBox;
	private JButton		button;
	private JButton		button_1;
	private JButton		button_2;
	private JButton		upTenBtn;
	private JLabel		howManyLabel;
	private JButton		button_5;
	private JButton		button_4;
	private JButton		downOneBtn;
	private JLabel		numTicketsLabel;
	private JButton		button_8;
	private JButton		button_10;
	private JButton		button_6;
	private JButton		upOneBtn;
	private JLabel		whatValueLabel;
	private JButton		button_9;
	private JButton		button_7;
	private JButton		button_3;
	private JButton		downTenBtn;
	private JButton		printBtn;
	private JLabel		numPrintedLabel;
	private Integer		numTickets	= 0;
	int					fakeLabel	= 0;
	// private Double numPadValue = 00.00;
	private int[]		numPadValue	= { 0, 0, 0, 0 };
	private double		ticketValue	= 0;
	boolean isOpen = true;
	boolean timerRunning = false;
	
	/**
	 * Create the panel.
	 */
	public FrmFreeplay()
	{
		setName("FrmFreeplay");
		setLayout(null);
		setPreferredSize(new Dimension(1080, 700));
		this.setOpaque(false);
		this.setLocation(200, 150);
		upTenBtn = new JButton("\u25B2");
		upTenBtn.setBackground(new Color(0, 255, 255));
		upTenBtn.setFont(new Font("Arial Black", Font.BOLD, 36));
		upTenBtn.setBounds(135, 70, 100, 90);
		add(upTenBtn);
		
		downTenBtn = new JButton("\u25BC");
		downTenBtn.setBackground(new Color(0, 255, 255));
		downTenBtn.setFont(new Font("Arial Black", Font.BOLD, 36));
		downTenBtn.setBounds(135, 325, 100, 90);
		add(downTenBtn);
		
		upOneBtn = new JButton("\u25B2");
		upOneBtn.setBackground(new Color(0, 153, 204));
		upOneBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
		upOneBtn.setBounds(160, 170, 50, 45);
		add(upOneBtn);
		
		downOneBtn = new JButton("\u25BC");
		downOneBtn.setBackground(new Color(0, 153, 204));
		downOneBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
		downOneBtn.setBounds(160, 270, 50, 45);
		add(downOneBtn);
		
		numTicketsLabel = new JLabel("0");
		numTicketsLabel.setForeground(new Color(255, 0, 0));
		numTicketsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numTicketsLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		numTicketsLabel.setBounds(160, 226, 45, 25);
		add(numTicketsLabel);
		
		howManyLabel = new JLabel("HOW MANY");
		howManyLabel.setForeground(new Color(51, 153, 0));
		howManyLabel.setBackground(new Color(0, 100, 0));
		howManyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		howManyLabel.setFont(new Font("Arial Black", Font.BOLD, 26));
		howManyLabel.setBounds(105, 30, 174, 35);
		add(howManyLabel);
		
		button_3 = new JButton("1");
		button_3.setMinimumSize(new Dimension(40, 20));
		button_3.setForeground(new Color(255, 255, 153));
		button_3.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_3.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_3.setBackground(Color.BLACK);
		button_3.setBounds(359, 309, 95, 90);
		add(button_3);
		
		button_4 = new JButton("2");
		button_4.setMinimumSize(new Dimension(40, 20));
		button_4.setForeground(new Color(255, 255, 153));
		button_4.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_4.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_4.setBackground(Color.BLACK);
		button_4.setBounds(460, 309, 95, 90);
		add(button_4);
		
		button_1 = new JButton("3");
		button_1.setMinimumSize(new Dimension(40, 20));
		button_1.setForeground(new Color(255, 255, 153));
		button_1.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(561, 309, 95, 90);
		add(button_1);
		
		button_6 = new JButton("4");
		button_6.setMinimumSize(new Dimension(40, 20));
		button_6.setForeground(new Color(255, 255, 153));
		button_6.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_6.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_6.setBackground(Color.BLACK);
		button_6.setBounds(359, 213, 95, 90);
		add(button_6);
		
		button_7 = new JButton("5");
		button_7.setMinimumSize(new Dimension(40, 20));
		button_7.setForeground(new Color(255, 255, 153));
		button_7.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_7.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_7.setBackground(Color.BLACK);
		button_7.setBounds(460, 213, 95, 90);
		add(button_7);
		
		button = new JButton("6");
		button.setMinimumSize(new Dimension(40, 20));
		button.setForeground(new Color(255, 255, 153));
		button.setFont(new Font("Arial Black", Font.BOLD, 56));
		button.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button.setBackground(Color.BLACK);
		button.setBounds(561, 213, 95, 90);
		add(button);
		
		button_9 = new JButton("7");
		button_9.setMinimumSize(new Dimension(40, 20));
		button_9.setForeground(new Color(255, 255, 153));
		button_9.setFont(new Font("Arial Black", Font.PLAIN, 56));
		button_9.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_9.setBackground(Color.BLACK);
		button_9.setBounds(359, 115, 95, 90);
		add(button_9);
		
		button_10 = new JButton("8");
		button_10.setMinimumSize(new Dimension(40, 20));
		button_10.setForeground(new Color(255, 255, 153));
		button_10.setFont(new Font("Arial Black", Font.PLAIN, 56));
		button_10.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_10.setBackground(Color.BLACK);
		button_10.setBounds(460, 115, 95, 90);
		add(button_10);
		
		button_8 = new JButton("9");
		button_8.setMinimumSize(new Dimension(40, 20));
		button_8.setForeground(new Color(255, 255, 153));
		button_8.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_8.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_8.setBackground(Color.BLACK);
		button_8.setBounds(561, 115, 95, 90);
		add(button_8);
		
		button_5 = new JButton("0");
		button_5.setMinimumSize(new Dimension(40, 20));
		button_5.setForeground(new Color(255, 255, 153));
		button_5.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_5.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_5.setBackground(Color.BLACK);
		button_5.setBounds(398, 410, 95, 90);
		add(button_5);
		
		button_2 = new JButton("<");
		button_2.setActionCommand("-1");
		button_2.setMinimumSize(new Dimension(40, 20));
		button_2.setForeground(new Color(255, 255, 153));
		button_2.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_2.setBackground(Color.BLACK);
		button_2.setBounds(521, 410, 95, 90);
		add(button_2);
		
		whatValueLabel = new JLabel("WHAT VALUE");
		whatValueLabel.setForeground(new Color(51, 153, 0));
		whatValueLabel.setBackground(new Color(0, 100, 0));
		whatValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		whatValueLabel.setFont(new Font("Arial Black", Font.BOLD, 26));
		whatValueLabel.setBounds(396, 28, 220, 34);
		add(whatValueLabel);
		
		ticketValueBox = new JTextField();
		ticketValueBox.setFont(new Font("Arial Black", Font.BOLD, 20));
		ticketValueBox.setHorizontalAlignment(SwingConstants.CENTER);
		ticketValueBox.setText("$00.00");
		ticketValueBox.setBounds(359, 70, 297, 34);
		add(ticketValueBox);
		ticketValueBox.setColumns(10);
		
		printBtn = new JButton("PRINT");
		printBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
		printBtn.setBounds(262, 532, 100, 45);
		add(printBtn);
		
		numPrintedLabel = new JLabel("0 PRINTED");
		numPrintedLabel.setForeground(Color.RED);
		numPrintedLabel.setBackground(Color.WHITE);
		numPrintedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numPrintedLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		numPrintedLabel.setBounds(0, 588, 627, 25);
		add(numPrintedLabel);
		this.setVisible(false);
		eventHandlers();
	}
	
	/**
	 * This method requests from the serial printer 9 times, with 400 milisecond
	 * intervals. At any point there is an issue with the serial printer this method
	 * will display the issue that was found and continue to retry until either the
	 * printer is fixed or until they leave the form. If the printer is fine, it
	 * will show a fake label animation that means nothing and it will then display
	 * "PRINTER GOOD" followed by a second followed by "PRINTED 0"
	 */
	void fakePrinterStatusLabel()
	{
		timer = new Timer();
		WindowsSerialPrinting WSP = new WindowsSerialPrinting();
		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				timerRunning = true;
				try
				{
					WSP.initializeSerialPort(1);
					WSP.printerStatus();
					
				}
				catch (SerialPortException e)
				{
					isOpen = false;
					e.printStackTrace();
				}
				getPrintBtn().setEnabled(false);
				if (WSP.currentPrinterStatus.length() > 0 || isOpen == false)
				{
					fakeLabel = 0;
					if (WSP.currentPrinterStatus.length() > 0)
					getNumPrintedLabel().setText("Printer error: " + WSP.currentPrinterStatus);
					else
						getNumPrintedLabel().setText("Serial Port cannot be opened!");
					try
					{
						Thread.sleep(2000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					getNumPrintedLabel().setText("Retrying...");
					try
					{
						Thread.sleep(2000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
				}
				else
				{
					if (getNumPrintedLabel().getText().equals("Retrying..."))
					{
						getNumPrintedLabel().setText("Checking Printer...");
					}
					if (getNumPrintedLabel().getText().equals("Checking Printer..."))
					{
						getNumPrintedLabel().setText("Checking Printer");
					}
					else if (getNumPrintedLabel().getText().equals("Checking Printer"))
					{
						getNumPrintedLabel().setText("Checking Printer.");
					}
					else if (getNumPrintedLabel().getText().equals("Checking Printer."))
					{
						getNumPrintedLabel().setText("Checking Printer..");
					}
					else if (getNumPrintedLabel().getText().equals("Checking Printer.."))
					{
						getNumPrintedLabel().setText("Checking Printer...");
					}
					fakeLabel++;
				}
				if (fakeLabel == 9)
				{
					timer.cancel();
					getNumPrintedLabel().setText("PRINTER GOOD!");
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					getNumPrintedLabel().setText("0 Printed");
					getPrintBtn().setEnabled(true);
					isOpen = true;
					timerRunning = false;
				}
			}
		};
		timer.schedule(task, 400, 400);
	}
	
	/**
	 * This starts the printer status check by calling fakePrinterStatusLabel()
	 * method
	 */
	void checkPrinterStatus()
	{
		getPrintBtn().setEnabled(false);
		getNumPrintedLabel().setText("Checking Printer...");
		fakePrinterStatusLabel();
	}
	
	@Override
	public Form getNew()
	{
		return new FrmFreeplay();
	}
	
	@Override
	public void scale()
	{
		// TODO I MAY OR MAY NOT SCALE THIS FORM, I MEAN... UGH
		
	}
	
	/**
	 * This method takes a number and adds it to the numberpad array. If the total
	 * number is greater than 50, it will keep it at 50. If the number is -1, it is
	 * assumed that it is called for a delete.
	 * 
	 * @param numberIn - The new user inputted numberpad number
	 */
	public void numPad(int numberIn)
	{
		if (numberIn != -1) // -1 is what backspace sends
		{
			int tempValue3 = numPadValue[3];
			int tempValue2 = numPadValue[2];
			int tempValue1 = numPadValue[1];
			numPadValue[3] = numberIn;
			numPadValue[2] = tempValue3;
			numPadValue[1] = tempValue2;
			numPadValue[0] = tempValue1;
			if (numPadValue[0] >= 5)
			{
				numPadValue[3] = 0;
				numPadValue[2] = 0;
				numPadValue[1] = 0;
				numPadValue[0] = 5;
			}
		}
		else
		{
			int tempValue2 = numPadValue[2];
			int tempValue1 = numPadValue[1];
			int tempValue0 = numPadValue[0];
			
			numPadValue[3] = tempValue2;
			numPadValue[2] = tempValue1;
			numPadValue[1] = tempValue0;
			numPadValue[0] = 0;
		}
		
		String tempString = numPadValue[0] + "" + numPadValue[1] + "." + numPadValue[2] + "" + numPadValue[3];
		setTicketValue(Double.parseDouble(tempString));
		getTicketValueBox().setText(formatAmount(ticketValue));
		
	}
	
	/**
	 * 
	 * @param amountIn
	 * @return
	 */
	private String formatAmount(Double amountIn)
	{
		DecimalFormat df = new DecimalFormat("$00.00");
		return df.format(amountIn);
	}
	
	public void eventHandlers()
	{
		/*
		 * Add action listeners to the numpad buttons since they old send a single
		 * number to the num pad function
		 */
		button.addActionListener(this);
		button_1.addActionListener(this);
		button_2.addActionListener(this);
		button_3.addActionListener(this);
		button_4.addActionListener(this);
		button_5.addActionListener(this);
		button_6.addActionListener(this);
		button_7.addActionListener(this);
		button_8.addActionListener(this);
		button_9.addActionListener(this);
		button_10.addActionListener(this);
		
		upTenBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				updateNumTicketsLabel(10);
			}
		});
		
		upOneBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				updateNumTicketsLabel(1);
			}
		});
		
		downTenBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				updateNumTicketsLabel(-10);
			}
		});
		
		downOneBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				updateNumTicketsLabel(-1);
			}
		});
		/**
		 * If the print button is enabled, and the number of tickets is greater than 0,
		 * and the ticket value is greater than 0 then we can print.
		 */
		printBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (getPrintBtn().isEnabled() && getNumTickets() > 0 && getTicketValue() > 0) print();
			}
		});
		
	}
	
	private void print()
	{
		WindowsSerialPrinting WSP = new WindowsSerialPrinting();
		try
		{
			WSP.initializeSerialPort(0);
			WSP.requestFreePlay(getNumTickets(), getTicketValue());
			getNumTicketsLabel().setText("0");
		}
		catch (SerialPortException e)
		{
			isOpen = false;
			if (!timerRunning)
			{
				fakePrinterStatusLabel();
			}
			e.printStackTrace();
		}
	}
	
	private void updateNumTicketsLabel(int i)
	{
		setNumTickets(Integer.parseInt(getNumTicketsLabel().getText().toString()));
		
		setNumTickets(getNumTickets() + i);// += i;
		
		if (getNumTickets() < 0)
		{
			setNumTickets(0);
		}
		else if (getNumTickets() > 50)
		{
			setNumTickets(50);
		}
		
		getNumTicketsLabel().setText(getNumTickets().toString());
	}
	
	void updateNumPrintedLabel(int i)
	{
		getNumPrintedLabel().setText("Printed: " + i);
		repaint();
		revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		numPad(Integer.parseInt(e.getActionCommand()));
	}
	
	protected JButton getButton_8()
	{
		return button;
	}
	
	public JButton getButton_5()
	{
		return button_1;
	}
	
	public JButton getButton_13()
	{
		return button_2;
	}
	
	public JButton getUpTenBtn()
	{
		return upTenBtn;
	}
	
	public JLabel getHowManyLabel()
	{
		return howManyLabel;
	}
	
	public JButton getButton_12()
	{
		return button_5;
	}
	
	public JButton getButton_4()
	{
		return button_4;
	}
	
	public JButton getDownOneBtn()
	{
		return downOneBtn;
	}
	
	public JLabel getNumTicketsLabel()
	{
		return numTicketsLabel;
	}
	
	public JButton getButton_11()
	{
		return button_8;
	}
	
	public JButton getButton_10()
	{
		return button_10;
	}
	
	public JButton getButton_6()
	{
		return button_6;
	}
	
	public JButton getUpOneBtn()
	{
		return upOneBtn;
	}
	
	public JLabel getWhatValueLabel()
	{
		return whatValueLabel;
	}
	
	public JButton getButton_9()
	{
		return button_9;
	}
	
	public JButton getButton_7()
	{
		return button_7;
	}
	
	public JButton getButton_3()
	{
		return button_3;
	}
	
	public JTextField getTicketValueBox()
	{
		return ticketValueBox;
	}
	
	public JButton getDownTenBtn()
	{
		return downTenBtn;
	}
	
	protected JButton getPrintBtn()
	{
		return printBtn;
	}
	
	protected JLabel getNumPrintedLabel()
	{
		return numPrintedLabel;
	}
	
	Integer getNumTickets()
	{
		return numTickets;
	}
	
	void setNumTickets(int numTicketsIn)
	{
		numTickets = numTicketsIn;
	}
	
	double getTicketValue()
	{
		return ticketValue;
	}
	
	void setTicketValue(double ticketValueIn)
	{
		ticketValue = ticketValueIn;
	}
	
}
