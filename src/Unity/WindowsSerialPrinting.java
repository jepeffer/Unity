package Unity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * I didn't encapsulate this form. This form handles the windows serial printing
 * functions and logic.
 * 
 * @author American Amusements
 *
 */
public class WindowsSerialPrinting extends TimerTask
{
	Character	ESC						= 0x1B;
	byte		AT						= 0x40;
	byte		LF						= 0xA;
	byte		GS						= 0x1D;
	
	SerialPort	serialPort				= new SerialPort("COM1");
	Timer		timer;
	
	boolean		done;
	int			currentNumTickets		= 0;
	int			numberTicketsPrinted	= 0;
	Integer		ticketAmount			= 0;
	
	boolean		paperJam				= false;
	boolean		ticketLow				= false;
	boolean		ticketInPrinter			= false;
	boolean		ticketNotInPath			= false;
	String		currentPrinterStatus	= "";
	
	/**
	 * Pads the string with spaces so it can be printed to the center of the
	 * printer.
	 * 
	 * @param stringIn - The string to be padded
	 * @return - The new padded string
	 */
	private String center(String stringIn)
	{
		String stringOut = "";
		
		int pre;
		int post;
		
		pre = (61 - stringIn.length()) / 2;
		post = 61 - stringIn.length() - pre;
		
		while (pre > 0)
		{
			stringOut = stringOut + " ";
			pre = pre - 1;
		}
		
		stringOut = stringOut + stringIn;
		
		while (post > 0)
		{
			stringOut = stringOut + " ";
			post = post - 1;
		}
		return stringOut;
		
	}
	
	/**
	 * Pads the string with spaces so it can be printed to the center of the
	 * printer.
	 * 
	 * @param stringIn - The string to be padded.
	 * @return - The new padded string
	 */
	private String centerWide(String stringIn)
	{
		String stringOut = "";
		
		int pre;
		int post;
		
		pre = (31 - stringIn.length()) / 2;
		post = 31 - stringIn.length() - pre;
		stringOut += (char) 0xE;
		while (pre > 0)
		{
			stringOut = stringOut + " ";
			pre = pre - 1;
		}
		
		stringOut = stringOut + stringIn;
		
		while (post > 0)
		{
			stringOut = stringOut + " ";
			post = post - 1;
		}
		stringOut += (char) 0x14;
		return stringOut;
		
	}
	
	/**
	 * Unused for now, unsure of its purpose
	 * 
	 * @param originalIn
	 * @param stringIn
	 * @return
	 */
	@SuppressWarnings("unused")
	private String right(String originalIn, String stringIn)
	{
		String stringOut = originalIn;
		int i;
		
		if (stringIn.length() + originalIn.length() > 61)
		{
			stringOut = stringOut.substring(0, 61);
		}
		i = 61 - stringIn.length() - stringOut.length();
		while (i > 0)
		{
			stringOut = stringOut + " ";
			i = i - 1;
		}
		stringOut += stringIn;
		return stringOut;
		
	}
	
	/**
	 * Barcode code.
	 * 
	 * @param stringIn - The string to be turned into a barcode
	 * @return - The barcode
	 */
	private String barcode(String stringIn)
	{
		String barcodeHeight = "" + (char) 0x64;
		String stringOut = "";
		/* Barcode Position */
		stringOut = (char) ESC + "" + (char) 0x58 + "" + (char) 0x0 + "" + (char) 0xF0;
		/* Barcode widths */
		stringOut += (char) GS + "" + (char) 0x57 + "" + (char) 0x4 + "" + (char) 0x8;
		/* Barcode heights */
		stringOut += (char) GS + "" + (char) 0x68 + "" + barcodeHeight;
		/* Type of barcode */
		stringOut += (char) GS + "" + (char) 0x6b + "" + (char) 0x7;
		
		/* Length */
		stringOut += (char) stringIn.length() + "";
		/* Barcode data */
		stringOut += stringIn;
		/* Clear positioning */
		stringOut += (char) ESC + "" + (char) 0x58 + "" + (char) 0x0 + "" + (char) 0x0;
		return stringOut;
	}
	
	/**
	 * This creates the printer string and prints to the serial printer.
	 * 
	 * @param nameIn       - Name of the server
	 * @param addressIn    - Address of the server
	 * @param cityIn       - City of the location
	 * @param phoneIn      - Phone number of the location
	 * @param typeIn       - Type of ticket
	 * @param validationIn - Validation number (barcode and otherwise)
	 * @param issueIn      - Issued date
	 * @param expireIn     - Expirey date
	 * @param amountIn     - Amount of the ticket
	 * @throws SerialPortException
	 */
	private void print(String nameIn, String addressIn, String cityIn, String phoneIn, String typeIn,
			String validationIn, String issueIn, String expireIn, String amountIn) throws SerialPortException
	{
		
		String printString = (char) ESC + "";
		printString += (char) AT + "" + (char) GS + "" + (char) 0x74 + "" + (char) AT;
		printString += (char) GS + "" + (char) 0x56 + "" + (char) 0x1;
		printString += (char) GS + "" + (char) 0x4C + "" + (char) 0x4 + "" + (char) 0xF4;
		printString += (char) GS + "" + (char) 0x54 + "" + (char) 0x0;
		printString += center(nameIn);
		printString += (char) LF;
		printString += center(addressIn + " " + cityIn);
		printString += (char) LF;
		printString += center(phoneIn);
		printString += (char) LF;
		printString += (char) LF;
		
		//printString += largeStuff();
		printString += centerWide(typeIn);
		printString += (char) LF;
		//printString += defaultFont();
		printString += center("VALIDATION: " + validationIn);
		printString += (char) LF;
		printString += barcode(validationIn);
		printString += center("ISSUED: " + issueIn);
		printString += (char) LF;
		printString += center("EXPIRES: " + expireIn);
		printString += (char) LF;
		printString += center("MACH: CASH STATION");
		printString += (char) LF;
		printString += centerWide(formatAmount(amountIn));
		printString += (char) 0xC;
		serialPort.writeString(printString);
		
	}
	
	private String defaultFont()
	{
		String printString = "";
		
		printString += (char)0x1B;
		
		printString += (char)0x4D;
		
		printString += (char)0x1D;
		
		printString += (char)0x21;
		
		printString += (char)0x00;
		
		return printString;
	}
	
	private String largeStuff()
	{
		String printString = "";
		printString += (char)0x1B;
		
		printString += (char)0x50;
		
		printString += (char)0x1D;
		
		printString += (char)0x21;
		
		printString += (char)0x33;
		
		return printString;
	}
	/**
	 * Helper function to take a String, and turn it into a money formatted string
	 * 
	 * @param amountIn - The amount to be formatted
	 * @return - The new amount
	 */
	public String formatAmount(String amountIn)
	{
		double temp = Double.parseDouble(amountIn);
		temp = temp / 100;
		DecimalFormat df = new DecimalFormat("$0.00");
		return df.format(temp);
	}
	
	/**
	 * If x = 0 we need the serial port for printing. This is a tad spag but it
	 * works fine, might fix
	 * 
	 * @param x if x = 1 then its just the initial check and we don't need the
	 *          serial port anymore
	 */
	void initializeSerialPort(int x) throws SerialPortException
	{
		serialPort.openPort();
		serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);
		serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		Main.MainInstance.getFreePlay().isOpen = true;
		serialPort.addEventListener(new SerialPortEventListener()
		{
			public void serialEvent(SerialPortEvent event)
			{
				try
				{
					String dataReceived = serialPort.readHexString(event.getEventValue());
					Main.MainInstance.getFreePlay().isOpen = true;
					processResponse(dataReceived);
					
					if (x == 1)
					{
						serialPort.closePort(); // TODO UNSPAGHETTI
					}
				}
				catch (SerialPortException e)
				{
					e.printStackTrace();
				}
				
			}
		});
	} // TODO FIX THE EXCEPTIONS, OPEN VM, AND TRY
	/*
	 * catch (SerialPortException e) { e.printStackTrace();
	 * Main.MainInstance.getFreePlay().isOpen = false; }
	 */
	
	/**
	 * This method is called from Freeplay and begins the printing process. It
	 * creates a new timer and the task is the "run" method.
	 * 
	 * @param numTickets - Number of tickets to be printed
	 * @param amountIn   - Amount of the ticket
	 * @throws SerialPortException
	 */
	void requestFreePlay(int numTickets, double amountIn) throws SerialPortException
	{
		timer = new Timer();
		currentNumTickets = numTickets;
		ticketAmount = doubleToInt(amountIn);
		timer.schedule(this, 0, 7000);
	}
	
	/**
	 * Helper method to turn a double to an integer, so its in pennies.
	 * 
	 * @param amountIn - The amount to be turned into a whole number
	 * @return - The new integer representing the old double
	 */
	int doubleToInt(Double amountIn)
	{
		String tempString = amountIn.toString();
		tempString = tempString.replace(".", "");
		int tempInt = Integer.parseInt(tempString);
		return tempInt;
	}
	
	/**
	 * This method requests the rpinter status
	 * 
	 * @throws SerialPortException
	 */
	void printerStatus()
	{
		String status = "";
		status += (char) GS + "" + (char) 0x7A;
		if (serialPort.isOpened()) try
		{
			serialPort.writeString(status);
		}
		catch (SerialPortException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			Thread.sleep(300); // Give the printer time
		}
		catch (InterruptedException e)
		{
			Main.MainInstance.getFreePlay().getNumPrintedLabel()
					.setText("Unknown error while checking for printer status!"); // I have no idea what could cause
																					// this, BUT WE EAT IT ANYWAY
			e.printStackTrace();
		}
	}
	
	/**
	 * Converts a string to a padded (8 bit) status array
	 * 
	 * @param byteIn
	 * @return
	 */
	String[] stringByteToBinary(String byteIn)
	{
		String[] statusArray = new String[8];
		
		int decimal = Integer.parseInt(byteIn, 16);
		
		String binary = Integer.toBinaryString(decimal);
		
		while (binary.length() < 8)
		{
			binary = "0" + binary;
		}
		
		for (int i = 0; i < statusArray.length; i++)
		{
			Character c = binary.charAt(i);
			statusArray[i] = c.toString();
			
		}
		
		return statusArray;
	}
	
	/**
	 * This method takes the status binary array, read its, and lets me know if
	 * there are any issues with the printer. The only real issues I care about is
	 * ticket not in printer and paperjam
	 * 
	 * @param dataReceived - The data receieved
	 * @return The currentPrinterStatus
	 * @throws SerialPortException
	 */
	protected String processResponse(String dataReceived)
	{
		currentPrinterStatus = "";
		System.out.println("DATA: " + dataReceived);
		String[] statusArray = stringByteToBinary(dataReceived);
		@SuppressWarnings("unused")
		String status = "";
		
		if (statusArray[0].equals("0"))
		{
			status += "\nNo paper jam ";
			paperJam = false;
			// No paper jam
		}
		if (statusArray[0].equals("1"))
		{
			paperJam = true;
			status += "\nPaper Jam";
			// Paper jam
		}
		if (statusArray[1].equals("0"))
		{
			ticketNotInPath = true;
			status += "\nTicket not in path";
			// Ticket not in path
		}
		if (statusArray[1].equals("1"))
		{
			ticketNotInPath = false;
			status += "\nTicket in path";
			// Ticket in path
		}
		if (statusArray[2].equals("0"))
		{
			status += "\nValidation not completed";
			// Validation not completed
		}
		if (statusArray[2].equals("1"))
		{
			status += "\nValidation completed";
			// Validation completed
		}
		if (statusArray[3].equals("0"))
		{
			status += "\nBarcode not completed";
			// Barcode not completed
		}
		if (statusArray[3].equals("1"))
		{
			status += "\nBarcode completed";
			// Barcode completed
		}
		if (statusArray[4].equals("0"))
		{
			// RESERVED
		}
		if (statusArray[4].equals("1"))
		{
			status += "\nAlways 1";
			// RESERVED == 1
		}
		if (statusArray[5].equals("0"))
		{
			status += "\nTop of form state";
			// Top of Form State
		}
		if (statusArray[5].equals("1"))
		{
			status += "\nNot top of form state";
			// Not top of Form State
		}
		if (statusArray[6].equals("0"))
		{
			ticketInPrinter = false;
			status += "\nTicket not in printer";
			// Ticket not in Printer
		}
		if (statusArray[6].equals("1"))
		{
			ticketInPrinter = true;
			status += "\nTicket in printer";
			// Ticket in Printer
		}
		if (statusArray[7].equals("0"))
		{
			ticketLow = false;
			status += "\nTicket not low";
			// Ticket Not Low
		}
		if (statusArray[7].equals("1"))
		{
			ticketLow = true;
			status += "\nTicket low";
			// Ticket Low
		}
		
		if (!ticketInPrinter)
		{
			currentPrinterStatus += "[Ticket not in printer] ";
		}
		if (paperJam)
		{
			currentPrinterStatus += "[Paper jam]";
		}
		return currentPrinterStatus;
	}
	
	/**
	 * This is the TimerTask that runs at a 7 second interval (to give time for
	 * status and printing)
	 * 
	 */
	@Override
	public void run()
	{
		if (currentNumTickets == 0)
		{
			Main.MainInstance.getFreePlay().updateNumPrintedLabel(numberTicketsPrinted);
			if (serialPort.isOpened()) try
			{
				serialPort.closePort();
			}
			catch (SerialPortException e)
			{
				Main.MainInstance.getFreePlay().getNumPrintedLabel().setText("Serial port could not be closed");
				e.printStackTrace();
			}
			Main.MainInstance.getFreePlay().getPrintBtn().setEnabled(true);
			timer.cancel();
		}
		else
		{
			Main.MainInstance.getFreePlay().getPrintBtn().setEnabled(false);
			
			printerStatus();
			if (currentPrinterStatus.length() > 0)
			{
				Main.MainInstance.getFreePlay().getNumPrintedLabel().setText("Printer error: " + currentPrinterStatus);
			}
			else
			{
				if (serialPort.isOpened())
				{
					printerStatus();
					if (currentPrinterStatus.length() > 0)
					{
						Main.MainInstance.getFreePlay().getNumPrintedLabel()
								.setText("Printer error: " + currentPrinterStatus);
					}
					else
					{
						Main.MainInstance.getFreePlay().isOpen = true;
						
						numberTicketsPrinted++;
						
						String ticketInfo[];
						
						ArrayList<String> currentParams = new ArrayList<>();
						
						String currentRequest = "createTicketFreeSweeps";
						
						currentParams.add(ticketAmount.toString());
						
						currentParams.add(Main.MainInstance.getCurrentUserID());
						
						String response = Main.MainInstance.getXMLRPCServer().startRequest(currentRequest,
								currentParams);
						
						ticketInfo = response.split("\\|");
						
						try
						{
							
							print(ticketInfo[7], ticketInfo[8],
									ticketInfo[9] + " " + ticketInfo[10] + " " + ticketInfo[11], ticketInfo[12],
									ticketInfo[3], ticketInfo[4], ticketInfo[5], ticketInfo[6], ticketInfo[2]);
						}
						
						catch (SerialPortException e)
						{
							Main.MainInstance.getFreePlay().getNumPrintedLabel().setText("Printing Failed");
							e.printStackTrace();
						}
						Main.MainInstance.getFreePlay().updateNumPrintedLabel(numberTicketsPrinted);
						
						if (currentNumTickets > 0)
						{
							currentNumTickets--;
						}
					}
				}
				else
				{
					Main.MainInstance.getFreePlay().isOpen = false;
				}
			}
			
		}
		
	}
	
}
