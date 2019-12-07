package Unity;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;

/**
 * This is the Main object. This Object contains all the other objects so they
 * can communicate between the selves. This object is turned into an Singleton
 * using an instance. The rest of the objects are created and stored in a
 * contentPane, sort of like an ArrayList I use the contentPane to control which
 * classes are displayed. I also use the contentPane to create and delete new
 * objects as needed.
 * 
 * @author American Amusements
 *
 */
@SuppressWarnings("serial")
public class Main extends JFrame
{
	/*
	 * Global tickets for end of shift report. Only tickets cashed this session or
	 * printed this session with be included in the shift report.
	 */
	protected ArrayList<Ticket>		globalTickets	= new ArrayList<Ticket>();
	/**
	 * The current server name.
	 */
	protected String				currentServer;
	/**
	 * The userconfig class associated with this Unity
	 */
	protected UserConfig			userConfig;
	/**
	 * Angie is an employee who a lot of login stuff is based off of. So Angie is
	 * basically a super user, and so if "Angie" is logged in, you can click the
	 * little label and it will have a free play option.
	 */
	protected Angie					angie;
	/**
	 * The server class
	 */
	protected Server				server;
	/**
	 * The current user logged in.
	 */
	protected String				currentUser;
	/**
	 * The current user's security.
	 */
	protected int					userSecurity;
	/**
	 * XMLRPC server https://en.wikipedia.org/wiki/XML-RPC
	 */
	private XMLRPCServer			xmlrpc			= new XMLRPCServer();
	
	/**
	 * Used to keep track of the most active form for memory/logic reasons
	 */
	private Component				activeComponent;
	/**
	 * The container for the layout/panel
	 */
	private JPanel					contentPane;
	/**
	 * The Main object is the container/interface for all of the other objects. This
	 * allows for communication between the forms and a place where I can manage
	 * memory. HA MANAGE MEMORY IN JAVA LOL
	 */
	protected static Main			MainInstance	= null;
	/**
	 * For loading reports
	 */
	protected ReportClass			rc;
	
	private static ScalingManager	sm;
	
	protected static Timer			time;
	
	protected static Dimension		screenSize;
	
	protected static double			screenWidth;
	
	protected static double			screenHeight;
	
	protected static int			centerX;
	
	protected static int			centerY;
	
	int								tester			= 0;
	/**
	 * User's current ID.
	 */
	protected String				currentUserID;
	/**
	 * The last printed receipt, used for reprinting if needed
	 */
	String							previousReceipt;
	/**
	 * Used in MainMenuButton because some operations shouldn't be done until the user is logged in.
	 */
	boolean							isLoggedIn		= false;
	/**
	 * Dictated by the Angie class.
	 */
	boolean							freeEntries;
	
	private static String			OS				= System.getProperty("os.name").toLowerCase();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				screenWidth = screenSize.getWidth();
				screenHeight = screenSize.getHeight();
				MainInstance = Instance(); // Create the jFrame
				ImageIcon frameIcon = new ImageIcon(FrmMain.class.getResource("/graphics/Unitylogo.png"));
				MainInstance.setIconImage(frameIcon.getImage()); // Get and set the Unity logo
				// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get
				// the screen size
				// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// this didnt work
				// frame.setUndecorated(true); // Make it look boring,
				// dragging??
				MainInstance.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the screen
				MainInstance.pack(); // Initialize Main's Instance
				// main.setVisible(true);
				// main.dispose();
				MainInstance.setVisible(true);
				MainInstance.addComponentListener(sm);
			}
		});
	}
	
	/**
	 * Create the frame. I use an OverlayLayout and I override its placement method
	 * so I can place objects on the exact pixels I want to. It still maintains its
	 * automatic scaling as well. I add one of each GUI related object to the
	 * contentPane which uses the OverlayLayout so I can easily change forms by
	 * setting their visiblity to false. Also the OverlayLayout is nice for the
	 * background (bg) object (label), because it wont interfere with any of the
	 * rest of the controls. Set all of the forms that don't need to be shown
	 * visiblity set to false
	 * 
	 * @throws IOException
	 */
	public Main() throws IOException
	{
		contentPane = new JPanel();
		
		// rc = rc.createFrame();
		/**
		 * Create the layout for the contentPane here so we can override its positioning
		 * because it doesn't allow for absolute position at all. It does allow for
		 * really simple and easy form changes
		 */
		OverlayLayout layout = new OverlayLayout(contentPane)
		{
			public void layoutContainer(Container target)
			{
				int nChildren = target.getComponentCount();
				for (int i = 0; i < nChildren; i++)
				{
					Component c = target.getComponent(i);
					c.setBounds(c.getLocation().x, c.getLocation().y, c.getPreferredSize().width,
							c.getPreferredSize().height);
				}
			}
		};
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 1065, 792);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		rc = new ReportClass();
		contentPane.setLayout(layout);
		
	}
	
	public static Main Instance()
	{
		if (MainInstance == null)
		{
			try
			{
				sm = new ScalingManager();
				MainInstance = new Main();
				MainInstance.setSize(screenSize);
				centerX = ((MainInstance.getWidth()) / 2);
				centerY = ((MainInstance.getHeight()) / 2);
				MainInstance.contentPane.add(new FrmLogin(), ComponentLocation.LOGIN);
				MainInstance.contentPane.add(new Keyboard(), ComponentLocation.KEYBOARD);
				MainInstance.contentPane.add(new BackgroundImage(), 2); // This is temporary, it will be set to its
																		// ComponentLocation after a login is made.
				MainInstance.getKeyboard().setFormControl(3);
				Main.MainInstance.getKeyboard().setVisible(true);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return MainInstance;
	}
	
	/**
	 * This method is what is used to set the activeComponent
	 * 
	 * @param compIn The component being sent
	 */
	public void setActiveComponent(Component compIn)
	{
		if (compIn != null)
		{
			activeComponent = compIn;
		}
		else
		{
			System.out.println("Component is null, won't be set as active.");
		}
	}
	
	/**
	 * Returns the active component
	 * 
	 * @return Component - the active component
	 */
	public Component getActiveComponent()
	{
		return activeComponent;
	}
	
	/**
	 * Returns the index of the component at its location. This is a linear search.
	 * O(n)
	 * 
	 * @param nameIn - The name the search is trying to find
	 * @return - The location of the component. 0 if not found.
	 */
	public int findComponent(String nameIn)
	{
		for (int i = 0; i < contentPane.getComponentCount(); i++)
		{
			if (contentPane.getComponent(i).getName().equals(nameIn))
			{
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Sets the component to visible Repaint and Revalidate are required whenever
	 * there is GUI change to update the GUI thread This also does the
	 * MainMenuButton logic by checking the name of the component. Also recreates
	 * the FrmTicket object
	 * 
	 * @param comp the Object (component subclass) being sent
	 */
	protected void changePanel(Component comp)
	{
		if (comp instanceof Form)
		{
			int componentIndex = findComponent(comp.getName()); // Find the object so I can remove/place it correctly
			
			if (!comp.getName().equals("FrmLogin"))// If its not FrmLogin
				getMainMenuButton().setVisible(true);// Any form thats not FrmMain will have the MainMenuBtn (cept
														// FrmLogin)
			contentPane.getComponent(ComponentLocation.MAIN).setVisible(false); // FrmMain
			contentPane.remove(componentIndex); // Old object
			Form form = ((Form) comp).getNew(); // Create new object
			setActiveComponent((Component) form); // Set the active component for later usage
			contentPane.add((Component) form, componentIndex); // Add new object
			if (!comp.getName().equals("FrmLogin")) ((Component) form).setVisible(true); // Show the form
			form.scale(); // Scale the form
			
			if (getFrmAngie().isVisible()) // Close FrmAngie if its open.
				getFrmAngie().setVisible(false);
		}
		else // If its not a Form class, its FrmMain
		{
			getActiveComponent().setVisible(false); // If we switch to FrmMain we must close the other form
			comp.setVisible(true); // Set FrmMain to visible
			((FrmMain) comp).scale();
			getMainMenuButton().setVisible(false); // Set the MainMenubutton to visible
			setActiveComponent(getMain());
		}
		if (getKeyboard().getFormControl() >= 0) // If it's FormControl is above 0 than it means that it is visible //
													// needs to be not visible
		{
			getKeyboard().setFormControl(-1); // Sets keyboard to not visible (see kb formcontrol method)
		}
		Runtime r = Runtime.getRuntime();
		r.gc(); // rip performance for testing lol
		repaint(); // Tells the GUI main thread to update
		revalidate(); // Loads the GUI update
	}
	
	/**
	 * Reads the Config file to create the user config for this session.
	 */
	protected void readConfigFile()
	{
		FileInputStream f;
		try
		{
			f = new FileInputStream(
					new File("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\\\src\\Config"));
			ObjectInputStream reader = new ObjectInputStream(f);
			while (true)
			{
				try
				{
					userConfig = (UserConfig) reader.readObject();
				}
				catch (EOFException e)
				{
					reader.close();
					f.close();
					break;
				}
			}
		}
		catch (EOFException e)
		{
			// Means there is no config created yet, so we will just use the base config.
			writeConfigToFile(new UserConfig(false, true)); // AUTO CASH OFF, Auto fill ON,
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		setUserConfig(userConfig); // This means that if there is no matching name, just use the base config with
									// the name of the person who logged in
	}
	
	protected void writeConfigToFile(UserConfig userConfigIn)
	{
		FileOutputStream f;
		try
		{
			f = new FileOutputStream(
					new File("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\src\\Config"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(userConfigIn);
			setUserConfig(userConfigIn);
			o.close();
			f.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void readAngieFromFile()
	{
		Angie tempAngie = null;
		try
		{
			FileInputStream fileStream = new FileInputStream(
					"C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\src\\Angie");
			ObjectInputStream o = new ObjectInputStream(fileStream);
			try
			{
				while (true)
				{
					tempAngie = (Angie) o.readObject();
					break;
				}
			}
			catch (EOFException | ClassNotFoundException e)
			{
				o.close();
				fileStream.close();
			}
		}
		catch (EOFException e) // If its an empty file
		{
			
			tempAngie = new Angie(true);
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		setAngie(tempAngie);
		writeAngieToFile(tempAngie);
	}
	
	protected void writeAngieToFile(Angie tempAngie)
	{
		try
		{
			FileOutputStream outStream = new FileOutputStream(
					new File("C:\\Users\\User\\Desktop\\Julian's Java Playground\\Unity\\src\\Angie"));
			ObjectOutputStream o = new ObjectOutputStream(outStream);
			o.writeObject(tempAngie);
			setAngie(tempAngie);
			o.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Keep the connections, remove the panels and effectively reset the program. I
	 * may just have the jar restart in the future
	 * 
	 * @throws IOException
	 */
	protected void logout() throws IOException
	{
		// contentPane.remove(2); // Remove backgroundimage to place it at end.
		/* Print the end of shift report */
		endOfShiftReport();
		/* Report logout time to server */
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateString = now.format(formatter);
		ArrayList<String> currentRequest = new ArrayList<String>();
		String cmd = "{call UnityPOSUserLogout(?, ?)}";
		currentRequest.add(getCurrentUser());
		currentRequest.add(dateString);
		try
		{
			Main.MainInstance.getServer().UnityPOSUserLogoutRequest(cmd, currentRequest);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		/* End reporting logout time to server */
		
		setCurrentUser("");
		getContentPane().removeAll();
		contentPane.add(new FrmLogin(), ComponentLocation.LOGIN);
		contentPane.add(new Keyboard(), ComponentLocation.KEYBOARD);
		contentPane.add(new BackgroundImage(), 2); // Temporary
		writeConfigToFile(userConfig);
		writeAngieToFile(angie);
		getLogin().scale();
		getKeyboard().setFormControl(3);
		getKeyboard().scale();
		clearGlobalTickets();
		isLoggedIn = false;
		
	}
	
	private void clearGlobalTickets()
	{
		getGlobalTickets().removeAll(globalTickets);
	}
	
	/**
	 * Prints the end of shift report for the given employee
	 */
	protected void endOfShiftReport()
	{
		if (globalTickets.size() > 0)
		{
			ReceiptPrinting rp = new ReceiptPrinting();
			String toPrint = "";
			toPrint = rp.createStartOfPrinterString();
			
			double customerTotal = 0;
			for (int i = 0; i < globalTickets.size(); i++)
			{
				
				toPrint += String.format("%" + rp.padding(globalTickets.get(i).printString()) + "s" + rp.newLine(1),
						globalTickets.get(i).printString());
				
				customerTotal += Double.parseDouble(globalTickets.get(i).getAmount());
			}
			
			String customerTotalFormatted = Main.MainInstance.getTicket().formatAmount(customerTotal);
			String currentTotal = "CUSTOMER TOTAL: " + customerTotalFormatted;
			String tempString = String
					.format(String.format(rp.newLine(1) + rp.verticalLine() + "%1$" + rp.padding(currentTotal) + "s",
							currentTotal + rp.newLine(2)));
			
			toPrint += tempString;
			toPrint += rp.endOfReceipt();
			rp.print(toPrint);
			setPreviousPrintReceipt(toPrint);
		}
	}
	
	protected void login() throws IOException
	{
		getContentPane().removeAll();
		readConfigFile();
		readAngieFromFile();
		contentPane.add(new FrmLogin(), ComponentLocation.LOGIN);
		contentPane.add(new Keyboard(), ComponentLocation.KEYBOARD);
		contentPane.add(new MainMenuButton(), ComponentLocation.MAINMENUBUTTON);
		contentPane.add(new FrmReport(), ComponentLocation.REPORT);
		contentPane.add(new FrmConfig(), ComponentLocation.CONFIG);
		contentPane.add(new FrmMain(), ComponentLocation.MAIN);
		contentPane.add(new FrmUser(), ComponentLocation.USER);
		contentPane.add(new FrmTicket(), ComponentLocation.TICKET);
		contentPane.add(new FrmUserLabel(), ComponentLocation.USERLABEL);
		contentPane.add(new FrmAngie(getAngie().getFreePlay()), ComponentLocation.ANGIE);
		contentPane.add(new FrmCalendar(), ComponentLocation.CALENDAR);
		contentPane.add(new FrmFreeplay(), ComponentLocation.FREEPLAY);
		contentPane.add(new BackgroundImage(), ComponentLocation.BACKGROUND);
		changePanel(getMain());
		isLoggedIn = true;
		this.repaint();
	}
	
	void printPreviousReceipt()
	{
		if (getPreviousPrintReceipt() != null && !getPreviousPrintReceipt().equals(""))
		{
			System.out.println("old receipt: " + getPreviousPrintReceipt());
			ReceiptPrinting rp = new ReceiptPrinting();
			rp.print(getPreviousPrintReceipt());
		}
	}
	
	protected BackgroundImage getBackgroundImage()
	{
		return (BackgroundImage) contentPane.getComponent(ComponentLocation.BACKGROUND);
	}
	
	/**
	 * Returns the FrmTicket ticket object
	 * 
	 * @return ticket - the FrmTicket object
	 */
	protected FrmTicket getTicket()
	{
		return (FrmTicket) contentPane.getComponent(ComponentLocation.TICKET);
	}
	
	/**
	 * Returns FrmMain main object
	 * 
	 * @return main - the main object
	 */
	protected FrmMain getMain()
	{
		return (FrmMain) contentPane.getComponent(ComponentLocation.MAIN);
	}
	
	/**
	 * Returns the MainMenuButton mainMenuBtn
	 * 
	 * @return MainMenuBtn
	 */
	protected MainMenuButton getMainMenuButton()
	{
		return (MainMenuButton) contentPane.getComponent(ComponentLocation.MAINMENUBUTTON);
	}
	
	/**
	 * Returns FrmReport report
	 * 
	 * @return FrmReport
	 */
	protected FrmReport getReport()
	{
		return (FrmReport) contentPane.getComponent(ComponentLocation.REPORT);
	}
	
	/**
	 * Getter for config
	 * 
	 * @return config
	 */
	protected FrmConfig getConfig()
	{
		return (FrmConfig) contentPane.getComponent(ComponentLocation.CONFIG);
	}
	
	/**
	 * Getter for ReportClass
	 * 
	 * @return rc class
	 */
	protected ReportClass getReportClass()
	{
		return rc;
	}
	
	/**
	 * Gets the XMLRPCServer for other functions
	 * 
	 * @return xmlrpc class
	 */
	protected XMLRPCServer getXMLRPCServer()
	{
		return xmlrpc;
	}
	
	/**
	 * Gets the keyboard for other functions
	 * 
	 * @return the keyboard
	 */
	protected Keyboard getKeyboard()
	{
		return (Keyboard) contentPane.getComponent(ComponentLocation.KEYBOARD);
	}
	
	/**
	 * Getter for the user form
	 * 
	 * @return the user form
	 */
	protected FrmUser getUser()
	{
		return (FrmUser) contentPane.getComponent(ComponentLocation.USER);
	}
	
	protected Server getServer()
	{
		return new Server();
	}
	
	protected UserConfig getUserConfig()
	{
		return userConfig;
	}
	
	protected void setUserConfig(UserConfig userConfigIn)
	{
		userConfig = userConfigIn;
	}
	
	protected FrmLogin getLogin()
	{
		return (FrmLogin) contentPane.getComponent(ComponentLocation.LOGIN);
	}
	
	protected FrmCalendar getCalendar()
	{
		return (FrmCalendar) contentPane.getComponent(ComponentLocation.CALENDAR);
	}
	
	protected void setCurrentUser(String nameIn)
	{
		currentUser = nameIn;
		getUserLabel().setCurrentUserLabelText(getCurrentUser());
	}
	
	protected String getCurrentUser()
	{
		return currentUser;
	}
	
	protected void setCurrentUserSecurity(int securityIn)
	{
		userSecurity = securityIn;
		
		if (userSecurity == 1)
		{
			Main.MainInstance.getMain().btnReports.setVisible(true);
			Main.MainInstance.getMain().btnSysConfig.setVisible(true);
		}
		else if (userSecurity > 1)
		{
			Main.MainInstance.getMain().btnReports.setVisible(true);
			Main.MainInstance.getMain().btnSysConfig.setVisible(false);
			Main.MainInstance.getMain().btnEntries.setVisible(false);
		}
		else
		{
			Main.MainInstance.getMain().btnReports.setVisible(false);
			Main.MainInstance.getMain().btnSysConfig.setVisible(false);
			Main.MainInstance.getMain().btnEntries.setVisible(false);
			
		}
	}
	
	protected int getCurrentUserSecurity()
	{
		return userSecurity;
	}
	
	protected FrmUserLabel getUserLabel()
	{
		return (FrmUserLabel) contentPane.getComponent(ComponentLocation.USERLABEL);
	}
	
	protected FrmAngie getFrmAngie()
	{
		return (FrmAngie) contentPane.getComponent(ComponentLocation.ANGIE);
	}
	
	protected Angie getAngie()
	{
		return angie;
	}
	
	protected FrmFreeplay getFreePlay()
	{
		return (FrmFreeplay) contentPane.getComponent(ComponentLocation.FREEPLAY);
	}
	
	protected void setAngie(Angie angieIn)
	{
		angie = angieIn;
	}
	
	protected String getCurrentServerName()
	{
		return currentServer;
	}
	
	protected void setCurrentServerName(String currentServerIn)
	{
		currentServer = currentServerIn;
	}
	
	protected int getScreenWidth()
	{
		return (int) screenWidth;
	}
	
	protected int getScreenHeight()
	{
		return (int) screenHeight;
	}
	
	protected int getCenterX()
	{
		return centerX;
	}
	
	protected int getCenterY()
	{
		return centerY;
	}
	
	protected void setCenterX(int centerXIn)
	{
		centerX = centerXIn;
	}
	
	protected void setCenterY(int centerYIn)
	{
		centerY = centerYIn;
	}
	
	protected ScalingManager getSM()
	{
		return sm;
	}
	
	void setCurrentUserID(String userIDIn)
	{
		currentUserID = userIDIn;
	}
	
	String getCurrentUserID()
	{
		return currentUserID;
	}
	
	ArrayList<Ticket> getGlobalTickets()
	{
		return globalTickets;
	}
	
	void setFreeEntries(boolean freeEntriesIn)
	{
		if (freeEntriesIn)
		{
			Main.MainInstance.getMain().getEntriesBtn().setVisible(true);
		}
		else
		{
			Main.MainInstance.getMain().getEntriesBtn().setVisible(false);
		}
		getAngie().setFreePlay(freeEntriesIn);
	}
	
	boolean getEntriesBoolean()
	{
		return freeEntries;
	}
	
	String getPreviousPrintReceipt()
	{
		if (previousReceipt == null)
		{
			return "";
		}
		else
			return previousReceipt;
	}
	
	void setPreviousPrintReceipt(String previousReceiptIn)
	{
		previousReceipt = previousReceiptIn;
	}
	
	String getCurrentOperatingSystem()
	{
		return OS;
	}
	
	void setCurrentOperatingSystem(String currentOperatingSystemIn)
	{
		OS = currentOperatingSystemIn;
	}
	
}
