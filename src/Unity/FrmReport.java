package Unity;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FrmReport extends JPanel implements Form
{
	
	JButton	ticketCashierBtn	= new JButton("");
	JButton	ticketVendorBtn		= new JButton("");
	JButton	expiredTicketsBtn	= new JButton("");
	JButton	liveTicketsBtn		= new JButton("");
	JButton	auditBtn			= new JButton("");
	JButton	ticketbyVendorBtn	= new JButton("");
	JButton	cashierSortBtn		= new JButton("");
	JButton	vendorSortBtn		= new JButton("");
	
	/**
	 * Create the panel.
	 */
	public FrmReport()
	{
		this.setVisible(false);
		setName("FrmReport");
		setPreferredSize(new Dimension(1300, 432));
		this.setOpaque(false);
		this.setLocation(100, 500);
		setLayout(null);
		
		auditBtn.setBounds(0, 0, 200, 100);
		auditBtn.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/dailyauditbtn.jpg"))
				.getImage().getScaledInstance(auditBtn.getWidth(), auditBtn.getHeight(), Image.SCALE_SMOOTH)))));
		auditBtn.setBorder(null);
		add(auditBtn);
		
		ticketbyVendorBtn.setBounds(0, 122, 200, 100);
		ticketbyVendorBtn.setMaximumSize(new Dimension(200, 9));
		ticketbyVendorBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickbyvendorbtn.jpg"))
						.getImage().getScaledInstance(ticketbyVendorBtn.getWidth(), ticketbyVendorBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		ticketbyVendorBtn.setBorder(null);
		add(ticketbyVendorBtn);
		
		liveTicketsBtn.setBounds(210, 122, 200, 100);
		liveTicketsBtn.setIcon(new ImageIcon(
				((new ImageIcon(FrmMain.class.getResource("/graphics/liveticketbtn.jpg")).getImage().getScaledInstance(
						liveTicketsBtn.getWidth(), liveTicketsBtn.getHeight(), Image.SCALE_SMOOTH)))));
		liveTicketsBtn.setBorder(null);
		add(liveTicketsBtn);
		
		expiredTicketsBtn.setBounds(420, 122, 200, 100);
		expiredTicketsBtn.setIcon(new ImageIcon(
				((new ImageIcon(FrmMain.class.getResource("/graphics/expticketbtn.jpg")).getImage().getScaledInstance(
						expiredTicketsBtn.getWidth(), expiredTicketsBtn.getHeight(), Image.SCALE_SMOOTH)))));
		expiredTicketsBtn.setBorder(null);
		add(expiredTicketsBtn);
		
		ticketVendorBtn.setBounds(630, 122, 200, 100);
		ticketVendorBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickcashvendorbtn.jpg"))
						.getImage().getScaledInstance(ticketVendorBtn.getWidth(), ticketVendorBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		ticketVendorBtn.setBorder(null);
		add(ticketVendorBtn);
		
		ticketCashierBtn.setBounds(840, 122, 200, 100);
		ticketCashierBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickcashcashierbtn.jpg"))
						.getImage().getScaledInstance(ticketCashierBtn.getWidth(), ticketCashierBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		ticketCashierBtn.setBorder(null);
		add(ticketCashierBtn);
		
		cashierSortBtn = new JButton("");
		cashierSortBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickcashiersortbtn.jpg"))
						.getImage().getScaledInstance(ticketCashierBtn.getWidth(), ticketCashierBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		cashierSortBtn.setBorder(null);
		cashierSortBtn.setBounds(630, 225, 200, 100);
		add(cashierSortBtn);
		
		vendorSortBtn = new JButton("");
		
		vendorSortBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickvendorsortbtn.jpg"))
						.getImage().getScaledInstance(ticketCashierBtn.getWidth(), ticketCashierBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		vendorSortBtn.setBorder(null);
		vendorSortBtn.setBounds(630, 330, 200, 100);
		add(vendorSortBtn);
		
		if (Main.MainInstance.getCurrentUserSecurity() > 1)
		{
			liveTicketsBtn.setVisible(false);
			auditBtn.setVisible(false);
			ticketbyVendorBtn.setVisible(true);
			expiredTicketsBtn.setVisible(false);
		}
		else
		{
			liveTicketsBtn.setVisible(true);
			auditBtn.setVisible(true);
			ticketbyVendorBtn.setVisible(true);
			expiredTicketsBtn.setVisible(true);
		}
		getCashierSortBtn().setVisible(false);
		getVendorSortBtn().setVisible(false);
		eventHandlers();
	}
	
	public void eventHandlers()
	{
		auditBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getReportClass().createReport(ReportNames.DAILYAUDIT);
			}
		});
		
		ticketbyVendorBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getReportClass().createReport(ReportNames.TICKETSBYVENDOR);
			}
		});
		
		liveTicketsBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getReportClass().createReport(ReportNames.LIVETICKETS);
			}
		});
		
		expiredTicketsBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getReportClass().createReport(ReportNames.EXPIREDTICKETS);
			}
		});
		
		ticketCashierBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getReportClass().createReport(ReportNames.TICKETSCASHEDBYPOS);
			}
		});
		
		ticketVendorBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			
			public void mouseClicked(MouseEvent arg0)
			{
				// Main.MainInstance.getReportClass().createReport(ReportNames.TICKETSCASHEDBYVENDOR);
				
				if (getCashierSortBtn().isVisible())
				{
					getCashierSortBtn().setVisible(false);
					getVendorSortBtn().setVisible(false);
				}
				else
				{
					//getCashierSortBtn().setEnabled(true);
					getCashierSortBtn().setVisible(true);
					//getVendorSortBtn().setEnabled(true);
					getVendorSortBtn().setVisible(true);
				}
			}
		});
		
		cashierSortBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getReportClass().createReport(ReportNames.TICKETSCASHEDBYPOSSORT);
				getCashierSortBtn().setVisible(false);
				getVendorSortBtn().setVisible(false);
			}
		});
		
		vendorSortBtn.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.MainInstance.getReportClass().createReport(ReportNames.TICKETSCASHEDBYVENDOR);
				getCashierSortBtn().setVisible(false);
				getVendorSortBtn().setVisible(false);
			}
		});
	}
	
	/**
	 * @throws EngineException
	 * 
	 */
	
	@Override
	public Form getNew()
	{
		return new FrmReport();
	}
	
	@Override
	public void scale()
	{
		getTicketCashierBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getLiveTicketsBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getTicketbyVendorBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getExpiredTicketsBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getTicketVendorBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getAuditBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getCashierSortBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getVendorSortBtn().setSize((int) (ScalingManager.getButtonX() * Main.MainInstance.getSM().gxr()),
				(int) (ScalingManager.getButtonY() * Main.MainInstance.getSM().gyr()));
		
		getTicketCashierBtn().setLocation((int) (840 * Main.MainInstance.getSM().gxr()),
				(int) (120 * Main.MainInstance.getSM().gyr()));
		
		getLiveTicketsBtn().setLocation((int) (210 * Main.MainInstance.getSM().gxr()),
				(int) (120 * Main.MainInstance.getSM().gyr()));
		
		getTicketbyVendorBtn().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (120 * Main.MainInstance.getSM().gyr()));
		
		getExpiredTicketsBtn().setLocation((int) (420 * Main.MainInstance.getSM().gxr()),
				(int) (120 * Main.MainInstance.getSM().gyr()));
		
		getTicketVendorBtn().setLocation((int) (630 * Main.MainInstance.getSM().gxr()),
				(int) (120 * Main.MainInstance.getSM().gyr()));
		
		getAuditBtn().setLocation((int) (0 * Main.MainInstance.getSM().gxr()),
				(int) (0 * Main.MainInstance.getSM().gyr()));
		
		// cashierSortBtn.setBounds(630, 233, 200, 100);
		// vendorSortBtn.setBounds(630, 344, 200, 100);
		getCashierSortBtn().setLocation((int) (630 * Main.MainInstance.getSM().gxr()),
				(int) (225 * Main.MainInstance.getSM().gyr()));
		
		getVendorSortBtn().setLocation((int) (630 * Main.MainInstance.getSM().gxr()),
				(int) (330 * Main.MainInstance.getSM().gyr()));
		
		ticketbyVendorBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickbyvendorbtn.jpg"))
						.getImage().getScaledInstance(ticketbyVendorBtn.getWidth(), ticketbyVendorBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		
		auditBtn.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/dailyauditbtn.jpg"))
				.getImage().getScaledInstance(auditBtn.getWidth(), auditBtn.getHeight(), Image.SCALE_SMOOTH)))));
		
		liveTicketsBtn.setIcon(new ImageIcon(
				((new ImageIcon(FrmMain.class.getResource("/graphics/liveticketbtn.jpg")).getImage().getScaledInstance(
						liveTicketsBtn.getWidth(), liveTicketsBtn.getHeight(), Image.SCALE_SMOOTH)))));
		
		expiredTicketsBtn.setIcon(new ImageIcon(
				((new ImageIcon(FrmMain.class.getResource("/graphics/expticketbtn.jpg")).getImage().getScaledInstance(
						expiredTicketsBtn.getWidth(), expiredTicketsBtn.getHeight(), Image.SCALE_SMOOTH)))));
		
		ticketVendorBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickcashvendorbtn.jpg"))
						.getImage().getScaledInstance(ticketVendorBtn.getWidth(), ticketVendorBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		
		ticketCashierBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickcashcashierbtn.jpg"))
						.getImage().getScaledInstance(ticketCashierBtn.getWidth(), ticketCashierBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		
		cashierSortBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickcashiersortbtn.jpg"))
						.getImage().getScaledInstance(ticketCashierBtn.getWidth(), ticketCashierBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
		vendorSortBtn
				.setIcon(new ImageIcon(((new ImageIcon(FrmMain.class.getResource("/graphics/tickvendorsortbtn.jpg"))
						.getImage().getScaledInstance(ticketCashierBtn.getWidth(), ticketCashierBtn.getHeight(),
								Image.SCALE_SMOOTH)))));
	}
	
	/**
	 * @return the cashierSortBtn
	 */
	protected JButton getCashierSortBtn()
	{
		return cashierSortBtn;
	}
	
	/**
	 * @param cashierSortBtn the cashierSortBtn to set
	 */
	protected void setCashierSortBtn(JButton cashierSortBtn)
	{
		this.cashierSortBtn = cashierSortBtn;
	}
	
	/**
	 * @return the vendorSortBtn
	 */
	protected JButton getVendorSortBtn()
	{
		return vendorSortBtn;
	}
	
	/**
	 * @param vendorSortBtn the vendorSortBtn to set
	 */
	protected void setVendorSortBtn(JButton vendorSortBtn)
	{
		this.vendorSortBtn = vendorSortBtn;
	}
	
	/**
	 * @return the ticketCashierBtn
	 */
	JButton getTicketCashierBtn()
	{
		return ticketCashierBtn;
	}
	
	/**
	 * @param ticketCashierBtn the ticketCashierBtn to set
	 */
	void setTicketCashierBtn(JButton ticketCashierBtn)
	{
		this.ticketCashierBtn = ticketCashierBtn;
	}
	
	/**
	 * @return the ticketVendorBtn
	 */
	JButton getTicketVendorBtn()
	{
		return ticketVendorBtn;
	}
	
	/**
	 * @param ticketVendorBtn the ticketVendorBtn to set
	 */
	void setTicketVendorBtn(JButton ticketVendorBtn)
	{
		this.ticketVendorBtn = ticketVendorBtn;
	}
	
	/**
	 * @return the expiredTicketsBtn
	 */
	JButton getExpiredTicketsBtn()
	{
		return expiredTicketsBtn;
	}
	
	/**
	 * @param expiredTicketsBtn the expiredTicketsBtn to set
	 */
	void setExpiredTicketsBtn(JButton expiredTicketsBtn)
	{
		this.expiredTicketsBtn = expiredTicketsBtn;
	}
	
	/**
	 * @return the liveTicketsBtn
	 */
	JButton getLiveTicketsBtn()
	{
		return liveTicketsBtn;
	}
	
	/**
	 * @param liveTicketsBtn the liveTicketsBtn to set
	 */
	void setLiveTicketsBtn(JButton liveTicketsBtn)
	{
		this.liveTicketsBtn = liveTicketsBtn;
	}
	
	/**
	 * @return the auditBtn
	 */
	JButton getAuditBtn()
	{
		return auditBtn;
	}
	
	/**
	 * @param auditBtn the auditBtn to set
	 */
	void setAuditBtn(JButton auditBtn)
	{
		this.auditBtn = auditBtn;
	}
	
	/**
	 * @return the ticketbyVendorBtn
	 */
	JButton getTicketbyVendorBtn()
	{
		return ticketbyVendorBtn;
	}
	
	/**
	 * @param ticketbyVendorBtn the ticketbyVendorBtn to set
	 */
	void setTicketbyVendorBtn(JButton ticketbyVendorBtn)
	{
		this.ticketbyVendorBtn = ticketbyVendorBtn;
	}
}
