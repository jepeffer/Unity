package Unity;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import java.awt.CardLayout;

@SuppressWarnings("serial")
public class ReportClass extends JFrame
{
	
	private JPanel	contentPane;
	JRViewer		jr;
	
	/**
	 * Launch the application.
	 */
	
	public ReportClass createFrame()
	{
		ReportClass frame = new ReportClass();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		return frame;
	}
	
	/**
	 * Create the frame.
	 */
	public ReportClass()
	{
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setBounds(100, 100, 1065, 792);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
	}
	
	/**
	 * This method is for report loading. It uses an ENUM called ReportNames just
	 * for readability. If the contentPane holding the JRViewer has one element by
	 * the time this class is called again, it will remove the JRViewer in room for
	 * another. JasperReport is reset to null to prepare for the next report.
	 */
	public void createReport(ReportNames rn)
	{
		/*.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickbyvendorbtn.jpg"))
						.getImage().getScaledInstance(ticketbyVendorBtn.getWidth(), ticketbyVendorBtn.getHeight(),
								Image.SCALE_SMOOTH)))));*/
		JasperReport report = null;
		try
		{
			if (getContentPane().getComponentCount() == 1)
			{
				getContentPane().remove(0); // only need 1 report loaded at a time
			}
			if (rn == ReportNames.LIVETICKETS)
			{
				report = JasperCompileManager.compileReport(
						getClass().getResourceAsStream("/Reports/LiveTickets.jrxml"));
			}
			else if (rn == ReportNames.EXPIREDTICKETS)
			{
				report = JasperCompileManager.compileReport(
						getClass().getResourceAsStream("/Reports/ExpiredTickets.jrxml"));
			}
			else if (rn == ReportNames.DAILYAUDIT)
			{
				report = JasperCompileManager.compileReport(
						getClass().getResourceAsStream("/Reports/DailyAudit.jrxml"));
			}
			else if (rn == ReportNames.DAILYFREEPLAY1)
			{
				report = JasperCompileManager.compileReport(
						getClass().getResourceAsStream("/Reports/DailyFreePlay1.jrxml"));
			}
			else if (rn == ReportNames.TICKETSBYVENDOR)
			{
				report = JasperCompileManager.compileReport(
						getClass().getResourceAsStream("/Reports/TicketsIssuedByVendor.jrxml"));
			}
			else if (rn == ReportNames.TICKETSCASHEDBYVENDOR)
			{
				report = JasperCompileManager.compileReport(
						getClass().getResourceAsStream("/Reports/TicketsCashedByVendor.jrxml"));
			}
			else if (rn == ReportNames.TICKETSCASHEDBYPOS)
			{
				report = JasperCompileManager.compileReport(
						getClass().getResourceAsStream("/Reports/TicketsCashedByPOS.jrxml"));
			}
			else if (rn == ReportNames.TICKETSCASHEDBYPOSSORT)
			{
				report = JasperCompileManager.compileReport(
						getClass().getResourceAsStream("/Reports/TicketsCashedByPOSSort.jrxml"));
			}
			
			Date dateFrom = Main.MainInstance.getCalendar().getConvertedCalendarOneDate();
			Date dateTo = Main.MainInstance.getCalendar().getConvertedCalendarTwoDate();
			Connection con;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:9001;", "unitypos", "unity812");		
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("DATEFROM", dateFrom);
			parameters.put("DATETO", dateTo);
			parameters.put("CurrentServer", Main.MainInstance.getCurrentServerName());
			InputStream inputStream = ReportClass.class.getClassLoader().getResourceAsStream("graphics/Unitylogo.bmp");
			parameters.put("IMAGEDIR", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
			JasperPrint jp = JasperFillManager.fillReport(report, parameters, con);
			addReportToFrame(jp);
		}
		catch (JRException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
	}
	
	private void addReportToFrame(JasperPrint jpIn)
	{
		jr = new JRViewer(jpIn);
		getContentPane().add(jr);
		repaint();
		revalidate();
	}
	
}
