package Unity;

import java.io.Serializable;

/**
 * This class contains information regarding specific user configs.
 * This class can be easily changed to edit configs, or make them more specific in the future.
 * @author American Amusements
 *
 */
public class UserConfig implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Boolean autoCashTicket;
	
	private Boolean autoFillTicketInformation;
	
	public UserConfig(Boolean autoCashTicketIn, Boolean autoFillTicketInformationIn)
	{
		autoCashTicket = autoCashTicketIn;
		autoFillTicketInformation = autoFillTicketInformationIn;
	}
	
	public void setAutoCashTicket(Boolean autoCashTicketIn)
	{
		autoCashTicket = autoCashTicketIn;
	}
	
	public Boolean getAutoCashTicket()
	{
		return autoCashTicket;
	}
	
	public void setAutoFillTicketInformation(Boolean autoFillTicketInformationIn)
	{
		autoFillTicketInformation = autoFillTicketInformationIn;
	}
	
	public Boolean getAutoFillTicketInformation()
	{
		return autoFillTicketInformation;
	}
	
}
