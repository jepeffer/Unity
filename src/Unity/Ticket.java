package Unity;


import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * I was gong to make this an abstract class, and have a
 * TicketCashed/TicketNotCashed object but instead I decided to just add a few
 * booleans for logic checking. Don't hate me Java developers.
 * 
 * @author American Amusements
 *
 */

public class Ticket implements Serializable
{
	
	private static final long	serialVersionUID	= 1L;
	
	private String				ticketNumber;
	
	private String				amount;
	
	private String				timeCashed;
	
	private String				timeWon;
	
	private String				machine;
	
	private String				vendor;
	
	private boolean				isCashed			= false;
	
	private boolean				isCleared			= false;
	
	private boolean				isPrinted			= false;
	
	
	/**
	 * Creates the ticket object before its cashed
	 * 
	 * @param amountIn       - The amount of money the ticket is worth.
	 * @param ticketNumberIn - The ticket number of the ticket.
	 * @param timeWonIn      - The time the ticket was won.
	 * @param timeCashedIn   - The time the ticket was cashed.
	 * @param machineIn      - The machine number the ticket is from.
	 * @param vendorIn       - The vendor of the ticket.
	 */
	
	public Ticket(String ticketNumberIn, String timeWonIn, String timeCashedIn, String amountIn, String machineIn,
			String vendorIn)
	{
		setAmount(amountIn);
		setTicketNumber(ticketNumberIn);
		setTimeWon(timeWonIn);
		setMachineNumber(machineIn);
		setVendor(vendorIn);
		setTimeCashed(timeCashedIn);
	}
	
	private void setAmount(String amountIn)
	{
		amount = amountIn;
	}
	
	private void setTicketNumber(String ticketNumberIn)
	{
		ticketNumber = ticketNumberIn;
	}
	
	private void setTimeWon(String timeWonIn)
	{
		timeWon = timeWonIn;
	}
	
	private void setMachineNumber(String machineIn)
	{
		machine = machineIn;
	}
	
	private void setVendor(String vendorIn)
	{
		vendor = vendorIn;
	}
	
	private void setTimeCashed(String timeCashedIn)
	{
		timeCashed = timeCashedIn;
	}
	
	public String getAmount()
	{
		return amount;
	}
	
	public String getTicketNumber()
	{
		return ticketNumber;
	}
	
	public String getTimeWon()
	{
		return timeWon;
	}
	
	public String getTimeCashed()
	{
		return timeCashed;
	}
	
	public String getMachine()
	{
		return machine;
	}
	
	public String getVendor()
	{
		return vendor;
	}
	
	public Boolean isCleared()
	{
		return isCleared;
	}
	
	public Boolean isPrinted()
	{
		return isPrinted;
	}
	
	Boolean isCashed()
	{
		return isCashed;
	}
	
	void setPrinted(boolean isPrintedIn)
	{
		isPrinted = isPrintedIn;
	}
	
	void setCleared(boolean isClearedIn)
	{
		isCleared = isClearedIn;
	}
	
	
	public String formatAmount()
	{
		double temp = Double.parseDouble(getAmount());
		temp = temp / 100;
		DecimalFormat df = new DecimalFormat("$0.00");
		return df.format(temp);
	}
	
	@Override
	public String toString()
	{
		String temp = String.format("%s - %s - %s ", getVendor(), getTicketNumber(), formatAmount());
		return temp;
	}
	
	/**
	 * TODO change;
	 * 
	 * @return
	 */
	public String printString()
	{
		String temp = String.format("%s - %s - %s ", getVendor(), getTicketNumber(), formatAmount());
		return temp;
	}
	
	int counter = 0;
	
}
