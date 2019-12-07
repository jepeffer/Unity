package Unity;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

/**
 * 
 * 
 * @author American Amusements
 *
 */
@SuppressWarnings("serial")
public class FrmAngie extends JPanel
{
	private transient JLabel	freeEntriesLabel;
	private transient JButton	closeBtn;
	private transient JButton	freeEntriesBtn;
	
	public FrmAngie(Boolean freePlayIn)
	{
		this.setLocation((int) (550 * Main.MainInstance.getSM().gxr()),
				(int) (400 * Main.MainInstance.getSM().gyr()));
		this.setName("FrmAngie");
		this.setVisible(false);
		setBackground(Color.BLACK);
		setLayout(null);
		setPreferredSize(new Dimension(450, 300));
		freeEntriesBtn = new JButton("");
		freeEntriesBtn.setIcon(new ImageIcon(Angie.class.getResource("/graphics/freeentriesbtn.jpg")));
		freeEntriesBtn.setBounds(10, 11, 129, 67);
		
		add(freeEntriesBtn);
		closeBtn = new JButton("");
		closeBtn.setIcon(new ImageIcon(Angie.class.getResource("/graphics/closebtn.jpg")));
		closeBtn.setBounds(311, 222, 129, 67);
		add(closeBtn);
		
		freeEntriesLabel = new JLabel("ON");
		freeEntriesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		freeEntriesLabel.setForeground(Color.RED);
		freeEntriesLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		freeEntriesLabel.setBounds(41, 78, 59, 26);
		add(freeEntriesLabel);
		eventHandlers();
		if (freePlayIn)
		{
			getFreeEntriesLbl().setText("ON");
		}
		else
		{
			getFreeEntriesLbl().setText("OFF");
		}
	}
	
	protected void setFreePlay(Boolean freePlayIn)
	{
		if (freePlayIn)
		{
			getFreeEntriesLbl().setText("OFF");
			Main.MainInstance.setFreeEntries(false);
		}
		else
		{
			Main.MainInstance.setFreeEntries(true);
			getFreeEntriesLbl().setText("ON");
		}
		Main.MainInstance.writeAngieToFile(Main.MainInstance.getAngie());
		
	}
	
	protected JButton getFreeEntriesBtn()
	{
		return freeEntriesBtn;
	}
	
	protected JButton getCloseBtn()
	{
		return closeBtn;
	}
	
	protected JLabel getFreeEntriesLbl()
	{
		return freeEntriesLabel;
	}
	
	void scale()
	{
		this.setLocation((int) (550 * Main.MainInstance.getSM().gxr()),
				(int) (400 * Main.MainInstance.getSM().gyr()));
	}
	
	private void eventHandlers()
	{
		getFreeEntriesBtn().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				setFreePlay(Main.MainInstance.getAngie().getFreePlay());
			}
		});
		
		getCloseBtn().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				setVisible(false);
			}
		});
	}
}
