package Unity;

import java.io.Serializable;

/**
 * If you have the Angie information, you get a special config option.
 * Can be extended in the future.
 * @author American Amusements
 *
 */
public class Angie implements Serializable 
{
	
	private static final long serialVersionUID = 5984899729975484439L;
	
	private Boolean freePlay;
	/**
	 * Special perms for Angie users
	 */
	public Angie (Boolean freePlayIn) 
	{
		freePlay = freePlayIn;
		
	}
	
	protected Boolean getFreePlay()
	{
		return freePlay;
	}
	
	protected void setFreePlay(Boolean freePlayIn)
	{
		freePlay = freePlayIn;
	}
		
}
