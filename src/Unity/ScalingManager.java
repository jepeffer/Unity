package Unity;

import java.awt.Component;
import java.awt.DisplayMode;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

public class ScalingManager implements ComponentListener
{
	
	private static final double	DEFAULT_RESOLUTION_X	= 1650;
	private static final double	DEFAULT_RESOLUTION_Y	= 1050;
	private static final int	BUTTON_X				= 200;
	private static final int	BUTTON_Y				= 100;
	static int							screenX;

	static int							screenY;
	private double				xRatio;
	private double				yRatio;
	/**
	 * @return the screenX
	 */
	protected static int getScreenX()
	{
		return screenX;
	}

	/**
	 * @return the screenY
	 */
	protected static int getScreenY()
	{
		return screenY;
	}

	
	@Override
	public void componentResized(ComponentEvent e)
	{
		findScreenXandScreenY();
		try
		{
			scaleForm();
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void componentMoved(ComponentEvent e)
	{
	}
	
	@Override
	public void componentShown(ComponentEvent e)
	{
	}
	
	@Override
	public void componentHidden(ComponentEvent e)
	{
	}
	
	public void findxRatio()
	{
		xRatio = (screenX / DEFAULT_RESOLUTION_X);
	}
	
	public void findyRatio()
	{
		yRatio = (screenY / DEFAULT_RESOLUTION_Y);
	}
	
	public double gxr()
	{
		return xRatio;
	}
	
	public double gyr()
	{
		return yRatio;
	}
	
	public static int getButtonX()
	{
		return BUTTON_X;
	}
	
	public static int getButtonY()
	{
		return BUTTON_Y;
	}
	
	public void scaleForm() throws IOException
	{
		Component tempForm = Main.MainInstance.getActiveComponent();
		
		if (tempForm instanceof Form)
		{
			((Form) tempForm).scale();
			if (Main.MainInstance.getKeyboard().getFormControl() >= 0) Main.MainInstance.getKeyboard().scale();
		}
		else
		{
			((FrmMain) tempForm).scale();
		}
		
		if (Main.MainInstance.isLoggedIn)
		{
			Main.MainInstance.getUserLabel().scale();
			Main.MainInstance.getMainMenuButton().scale();
			Main.MainInstance.getFrmAngie().scale();
		}
	}
	
	public void findScreenXandScreenY()
	{
		DisplayMode mode = Main.MainInstance.getGraphicsConfiguration().getDevice().getDisplayMode();
		screenX = mode.getWidth();
		screenY = mode.getHeight();
		findxRatio();
		findyRatio();
	}
}
