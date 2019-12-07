package Unity;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

/**
 * This will contain the print, save, and exit buttons for the ReportClass JFrame.
 * @author American Amusements
 *
 */
@SuppressWarnings("serial")
public class ReportClassButtons extends JPanel {

	private JButton btnMainMenu;
	private JButton btnSave;
	private JButton btnPrint;
	private JButton btnBack;
	/**
	 * Create the panel.
	 */
	public ReportClassButtons() {
		this.setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnSave = new JButton("");
		btnSave.setBorder(null);
		btnSave.setIcon(new ImageIcon(ReportClassButtons.class.getResource("/graphics/savesmallbtn.jpg")));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		add(btnSave, gbc_btnSave);
		
		btnPrint = new JButton("");
		btnPrint.setBorder(null);
		btnPrint.setIcon(new ImageIcon(ReportClassButtons.class.getResource("/graphics/printgreybtn.jpg")));
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrint.gridx = 3;
		gbc_btnPrint.gridy = 0;
		add(btnPrint, gbc_btnPrint);
		
		btnBack = new JButton("");
		btnBack.setBorder(null);
		btnBack.setIcon(new ImageIcon(ReportClassButtons.class.getResource("/graphics/backsmallbtn.jpg")));
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 5;
		gbc_btnBack.gridy = 0;
		add(btnBack, gbc_btnBack);

		btnMainMenu = new JButton("");
		btnMainMenu.setBorder(null);
		btnMainMenu.setIcon(new ImageIcon(ReportClassButtons.class.getResource("/graphics/mainbtn.jpg")));
		GridBagConstraints gbc_btnMainMenu = new GridBagConstraints();
		gbc_btnMainMenu.insets = new Insets(0, 0, 0, 5);
		gbc_btnMainMenu.gridx = 7;
		gbc_btnMainMenu.gridy = 0;
		add(btnMainMenu, gbc_btnMainMenu);
		eventHandlers();
	}
	private void eventHandlers() {
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				saveReport();
			}
		});
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				backOut();
			}
		});
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				toMainMenu();
			}
		});
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				printReport();
			}
		});
	}
	
	public void backOut() 
	{
		Main.MainInstance.getReportClass().setVisible(false);
	}
	
	public void toMainMenu() 
	{
		Main.MainInstance.getReportClass().setVisible(false);
		Main.MainInstance.changePanel(Main.MainInstance.getMain());
	}
	
	public void printReport()
	{
		
	}
	
	public void saveReport()
	{
		
	}
}
