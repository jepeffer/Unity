package Unity;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author American Amusements
 *
 */
@SuppressWarnings("serial")
public class FrmUserLabel extends JPanel
{
	private JLabel currentUserLabel = new JLabel("TESTUSER");
	
	/**
	 * Create the panel.
	 */
	public FrmUserLabel()
	{
		setPreferredSize(new Dimension(204, 40));
		this.setName("FrmUserLabel");
		setLayout(null);
		currentUserLabel.setBounds(0, 0, 180, 37);
		currentUserLabel.setName("currentUserLabel");
		currentUserLabel.setForeground(Color.WHITE);
		currentUserLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		add(currentUserLabel);
		this.setOpaque(false);
		this.setVisible(true);
		eventHandlers();
		scale();
		
	}
	
	public String getCurrentUserLabelText()
	{
		return currentUserLabel.getText();
	}
	
	public void setCurrentUserLabelText(String nameIn)
	{
		currentUserLabel.setText(nameIn);
	}
	
	public void eventHandlers()
	{
		currentUserLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (getCurrentUserLabelText().equals("ANGIE"))
				{
					if (Main.MainInstance.getActiveComponent().getName().equals("FrmMain"))
						Main.MainInstance.getFrmAngie().setVisible(true);
				}
				else
				{
					// TODO DISPLAY VERSION NAME IN POPUP BOX OR SOMESHIT
				}
			}
		});
	}
	
	public void scale()
	{
		
		/* This is some cheese but it fits all sizes */
		
		this.setLocation(
				  (int) (1285 * Main.MainInstance.getSM().gxr()),
				(int) (750 * Main.MainInstance.getSM().gyr()));
		/*this.setLocation(
				(int) ((Main.MainInstance.getScreenWidth() - (this.getWidth() * 2)) * Main.MainInstance.getSM().gxr()),
				(int) ((Main.MainInstance.getScreenHeight() - 200) * Main.MainInstance.getSM().gyr()));*/
		

	}
	
}
