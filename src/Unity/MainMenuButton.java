package Unity;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuButton extends JPanel
{
	
	/**
	 * Create the panel.
	 */
	protected JButton			mainButton			= new JButton("");
	
	public MainMenuButton()
	{
		setPreferredSize(new Dimension(250, 150));
		toggleVisibility();
		eventHandlers();
		this.setName("MainMenuButton");
		this.setOpaque(false);
		mainButton.setBounds(10, 5, 100, 50);
		mainButton.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/mainbtn.jpg"))
				.getImage().getScaledInstance(mainButton.getWidth(), mainButton.getHeight(), Image.SCALE_SMOOTH)))));
		mainButton.setBorderPainted(false);
		mainButton.setContentAreaFilled(false);
		mainButton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		this.setLocation((int)((Main.MainInstance.getScreenWidth() - 200) * Main.MainInstance.getSM().gxr()), 10);
		setLayout(null);
		this.add(mainButton);
	}
	
	public void toggleVisibility()
	{
		if (this.isVisible())
		{
			this.setVisible(false);
		}
		else
		{
			this.setVisible(true);
		}
	}
	
	public void eventHandlers()
	{
		mainButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (Main.MainInstance.getActiveComponent().getName().equals("FrmFreeplay"))
				{
					Main.MainInstance.getFreePlay().timer.cancel(); // If they leave while the printer is being checked.
				}
				if (Main.MainInstance.getActiveComponent().getName().equals("FrmReport")) // Adds a new Calendar to the
																							// contentPane
				{
					Main.MainInstance.getContentPane().remove(ComponentLocation.CALENDAR);
					Main.MainInstance.getContentPane().add(new FrmCalendar(), ComponentLocation.CALENDAR);
					Main.MainInstance.getCalendar().setVisible(false);
				}
				
				Main.MainInstance.changePanel(Main.MainInstance.getMain());
				Main.MainInstance.getKeyboard().setVisible(false);
			}
		});
	}
	
	void scale()
	{
		this.setLocation((int)((Main.MainInstance.getScreenWidth() - 200) * Main.MainInstance.getSM().gxr()), 10);	
		mainButton.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/mainbtn.jpg"))
				.getImage().getScaledInstance(mainButton.getWidth(), mainButton.getHeight(), Image.SCALE_SMOOTH)))));
	}
	
}
