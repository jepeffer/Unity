package Unity;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

/**
 * This is a simple GUI class that contains a few buttons. It is the FrmMain
 * form, aka where the user enters after logging in. For the most part this form
 * just is event handlers to get to the next Form, really having no purpose (as
 * when I write this) besides for this.
 * 
 * @author American Amusements
 *
 */
@SuppressWarnings("serial")
public class FrmMain extends JPanel
{
	
	JButton	btnCashier		= new JButton("");
	JButton	btnReports		= new JButton("");
	JButton	btnSysConfig	= new JButton("");
	JButton	btnReprint		= new JButton("");
	JButton	btnEntries		= new JButton("");
	JButton	btnLogout		= new JButton("");
	
	/**
	 * Creates the panel and its Objects. For event handling I just override their
	 * event handlers and have them send their purpose to helper methods elsewhere.
	 */
	public FrmMain()
	{
		setBounds(100, 100, 1065, 792);
		setPreferredSize(new Dimension(1060, 240));
		this.setLocation(300, 100);
		this.setVisible(false);
		this.setOpaque(false);
		eventHandlers();
		setName("FrmMain");
		setLayout(null);
		
		btnCashier.setBounds(0, 30, 200, 100);
		btnCashier.setBackground(Color.WHITE);
		// btnCashier.setIcon(new
		// ImageIcon(FrmMain.class.getResource("/graphics/cashierbtn.jpg")));
		btnCashier.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/cashierbtn.jpg"))
				.getImage().getScaledInstance(btnCashier.getWidth(), btnCashier.getHeight(), Image.SCALE_SMOOTH)))));
		btnCashier.setBorderPainted(false);
		btnCashier.setContentAreaFilled(false);
		btnCashier.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(btnCashier);
		
		btnReports.setBounds(210, 30, 200, 100);
		// button.setBorder(0);
		btnReports.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/reportsbtn.jpg"))
				.getImage().getScaledInstance(btnReports.getWidth(), btnReports.getHeight(), Image.SCALE_SMOOTH)))));
		btnReports.setBorderPainted(false);
		btnReports.setContentAreaFilled(false);
		btnReports.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(btnReports);
		
		btnSysConfig.setBounds(420, 30, 200, 100);
		btnSysConfig
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/systembtn.jpg")).getImage()
						.getScaledInstance(btnSysConfig.getWidth(), btnSysConfig.getHeight(), Image.SCALE_SMOOTH)))));
		btnSysConfig.setBorderPainted(false);
		btnSysConfig.setContentAreaFilled(false);
		btnSysConfig.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(btnSysConfig);
		
		btnReprint.setBounds(630, 30, 200, 100);
		btnReprint.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/reprintbtn.jpg"))
				.getImage().getScaledInstance(btnReprint.getWidth(), btnReprint.getHeight(), Image.SCALE_SMOOTH)))));
		btnReprint.setBorderPainted(false);
		btnReprint.setContentAreaFilled(false);
		btnReprint.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(btnReprint);
		
		btnLogout.setBounds(840, 30, 200, 100);
		btnLogout.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/logoutbtn.jpg")).getImage()
				.getScaledInstance(btnLogout.getWidth(), btnLogout.getHeight(), Image.SCALE_SMOOTH)))));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(btnLogout);
		
		btnEntries.setBounds(210, 130, 200, 100);
		btnEntries.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/freeentriesbtn.jpg"))
				.getImage().getScaledInstance(btnEntries.getWidth(), btnEntries.getHeight(), Image.SCALE_SMOOTH)))));
		btnEntries.setContentAreaFilled(false);
		btnEntries.setBorderPainted(false);
		btnEntries.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(btnEntries);
		
		if (!Main.MainInstance.getAngie().getFreePlay())
		{
			btnEntries.setVisible(false);
		}
	}
	/*
	 * Called from onClick event from btnTicket overrided above. This method is used
	 * to set FrmTicket to visible by sending the Main form
	 * 
	 */
	
	/**
	 * This is where all the event lisners for FrmMain are. These ones are pretty
	 * simple, and rely on helper methods for logic.
	 */
	public void eventHandlers()
	{
		/**
		 * Opens up the ticket page by calling changePanel and referencing to the ticket
		 * object through getTicket in main
		 */
		btnCashier.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.changePanel(Main.MainInstance.getTicket());
				Main.MainInstance.getTicket().readDeadTickets();
			}
		});
		/**
		 * Closes the form, will be logout later TODO
		 */
		btnLogout.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				setVisible(false);
				try
				{
					Main.MainInstance.logout();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		btnReprint.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				// Main.MainInstance.changePanel(Main.MainInstance.getKeyboard());
				Main.MainInstance.printPreviousReceipt();
				/*
				 * 
				 * for (int i = 0; i < 5000; i ++) {
				 * Main.MainInstance.changePanel(Main.MainInstance.getReport());
				 * Main.MainInstance.changePanel(Main.MainInstance.getMain()); if (i % 500 == 0)
				 * { System.out.println("Im going still " + i); } } System.out.println("done");
				 * //Main.MainInstance.getMain().btnReports.doClick();
				 * //Main.MainInstance.getMainMenuButton().mainButton.doClick();
				 * //btnCashier.doClick();
				 * //Main.MainInstance.getMainMenuButton().mainButton.doClick();
				 * //btnSysConfig.doClick();
				 * //Main.MainInstance.getMainMenuButton().mainButton.doClick();
				 * 
				 */
				
			}
		});
		
		btnEntries.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.changePanel(Main.MainInstance.getFreePlay());
				Main.MainInstance.getFreePlay().checkPrinterStatus();
			}
		});
		
		btnReports.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.changePanel(Main.MainInstance.getReport());
				Main.MainInstance.getCalendar().setVisible(true);
				
			}
		});
		
		btnSysConfig.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.changePanel(Main.MainInstance.getConfig());
				// CLEANER FIX TODO?
				Main.MainInstance.getConfig().readCurrentConfig();
			}
		});
	}
	
	void scale()
	{
		
		/* CHANGE SIZE */
		getLogoutBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getEntriesBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getReportsBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getReprintBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getSysConfigBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getCashierBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		/* RELOAD IMAGE */
		
		getReportsBtn().setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/reportsbtn.jpg"))
				.getImage().getScaledInstance(btnReports.getWidth(), btnReports.getHeight(), Image.SCALE_SMOOTH)))));
		
		getCashierBtn().setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/cashierbtn.jpg"))
				.getImage().getScaledInstance(btnCashier.getWidth(), btnCashier.getHeight(), Image.SCALE_SMOOTH)))));
		
		getSysConfigBtn()
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/systembtn.jpg")).getImage()
						.getScaledInstance(btnSysConfig.getWidth(), btnSysConfig.getHeight(), Image.SCALE_SMOOTH)))));
		
		getReprintBtn().setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/reprintbtn.jpg"))
				.getImage().getScaledInstance(btnReprint.getWidth(), btnReprint.getHeight(), Image.SCALE_SMOOTH)))));
		
		getLogoutBtn().setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/logoutbtn.jpg"))
				.getImage().getScaledInstance(btnLogout.getWidth(), btnLogout.getHeight(), Image.SCALE_SMOOTH)))));
		
		getEntriesBtn().setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/freeentriesbtn.jpg"))
				.getImage().getScaledInstance(btnEntries.getWidth(), btnEntries.getHeight(), Image.SCALE_SMOOTH)))));
		
		/* SET LOCATION */
		this.setLocation((int) (300 * Main.MainInstance.getSM().gxr()), (int) (100 * Main.MainInstance.getSM().gyr()));
		
		getCashierBtn().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getLogoutBtn().setLocation((int) (840 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getEntriesBtn().setLocation((int) (210 * Main.MainInstance.getSM().gxr()),
				(int) (140 * Main.MainInstance.getSM().gyr()));
		
		getReportsBtn().setLocation((int) (210 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getReprintBtn().setLocation((int) (630 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		getSysConfigBtn().setLocation((int) (420 * Main.MainInstance.getSM().gxr()),
				(int) (30 * Main.MainInstance.getSM().gyr()));
		
		this.setSize((int)(1060 * Main.MainInstance.getSM().gxr()), (int) (280 * Main.MainInstance.getSM().gyr()));
		
	}
	
	JButton getEntriesBtn()
	{
		return btnEntries;
	}
	
	JButton getReportsBtn()
	{
		return btnReports;
	}
	
	JButton getSysConfigBtn()
	{
		return btnSysConfig;
	}
	
	JButton getReprintBtn()
	{
		return btnReprint;
	}
	
	JButton getLogoutBtn()
	{
		return btnLogout;
	}
	
	JButton getCashierBtn()
	{
		return btnCashier;
	}
	
}
