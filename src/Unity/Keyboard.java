package Unity;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.border.LineBorder;

/**
 * This class is a resuable keyboard class. It uses the ActionListener interface
 * and the method is overrided at the bottom
 * 
 * @author American Amusements
 *
 */
@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener
{
	
	/**
	 * Which form is using the keyboard?
	 */
	private int		formControl	= -1;
	/**
	 * THE BUTTONS
	 */
	private JButton	button_7;
	private JButton	btnZ;
	private JButton	btnN;
	private JButton	button_8;
	private JButton	btnX;
	private JButton	button_9;
	private JButton	btnA;
	private JButton	btnP;
	private JButton	btnK;
	private JButton	btnJ;
	private JButton	btnV;
	private JButton	button_4;
	private JButton	btnS;
	private JButton	btnR;
	private JButton	btnQ;
	private JButton	btnW;
	private JButton	button;
	private JButton	btnL;
	private JButton	button_6;
	private JButton	button_5;
	private JButton	btnO;
	private JButton	btnD;
	private JButton	button_1;
	private JButton	btnI;
	private JButton	btnT;
	private JButton	btnH;
	private JButton	btnC;
	private JButton	btnM;
	private JButton	btnE;
	private JButton	btnU;
	private JButton	button_2;
	private JButton	button_3;
	private JButton	btnF;
	private JButton	btnG;
	private JButton	btnB;
	private JButton	btnY;
	private JButton	btnBackSpace;
	
	/**
	 * Create the keyboard. LOTS OF GUI STUFF HERE
	 */
	public Keyboard()
	{
		setName("Keyboard");
		// this.setLocation(100, 500);
		
		this.setOpaque(false);
		setPreferredSize(new Dimension(1300, 1000));
		button = new JButton("1");
		button.setBounds(10, 29, 95, 90);
		button.setMinimumSize(new Dimension(40, 20));
		button.setForeground(new Color(255, 255, 153));
		button.setFont(new Font("Arial Black", Font.BOLD, 56));
		button.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button.setBackground(Color.BLACK);
		button.addActionListener(this);
		setLayout(null);
		add(button);
		
		button_1 = new JButton("2");
		button_1.setBounds(107, 29, 95, 90);
		button_1.setMinimumSize(new Dimension(40, 20));
		button_1.setForeground(new Color(255, 255, 153));
		button_1.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_1.setBackground(Color.BLACK);
		button_1.addActionListener(this);
		add(button_1);
		
		button_2 = new JButton("3");
		button_2.setBounds(206, 29, 95, 90);
		button_2.setMinimumSize(new Dimension(40, 20));
		button_2.setForeground(new Color(255, 255, 153));
		button_2.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_2.setBackground(Color.BLACK);
		button_2.addActionListener(this);
		add(button_2);
		
		button_3 = new JButton("4");
		button_3.setBounds(304, 29, 95, 90);
		button_3.setMinimumSize(new Dimension(40, 20));
		button_3.setForeground(new Color(255, 255, 153));
		button_3.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_3.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_3.setBackground(Color.BLACK);
		button_3.addActionListener(this);
		add(button_3);
		
		button_4 = new JButton("5");
		button_4.setBounds(403, 29, 95, 90);
		button_4.setMinimumSize(new Dimension(40, 20));
		button_4.setForeground(new Color(255, 255, 153));
		button_4.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_4.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_4.setBackground(Color.BLACK);
		button_4.addActionListener(this);
		add(button_4);
		
		button_5 = new JButton("6");
		button_5.setBounds(501, 29, 95, 90);
		button_5.setMinimumSize(new Dimension(40, 20));
		button_5.setForeground(new Color(255, 255, 153));
		button_5.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_5.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_5.setBackground(Color.BLACK);
		button_5.addActionListener(this);
		add(button_5);
		
		button_6 = new JButton("7");
		button_6.setBounds(598, 29, 95, 90);
		button_6.setMinimumSize(new Dimension(40, 20));
		button_6.setForeground(new Color(255, 255, 153));
		button_6.setFont(new Font("Arial Black", Font.PLAIN, 56));
		button_6.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_6.setBackground(Color.BLACK);
		button_6.addActionListener(this);
		add(button_6);
		
		button_7 = new JButton("8");
		button_7.setBounds(695, 29, 95, 90);
		button_7.setMinimumSize(new Dimension(40, 20));
		button_7.setForeground(new Color(255, 255, 153));
		button_7.setFont(new Font("Arial Black", Font.PLAIN, 56));
		button_7.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_7.setBackground(Color.BLACK);
		button_7.addActionListener(this);
		add(button_7);
		
		button_8 = new JButton("9");
		button_8.setBounds(793, 29, 95, 90);
		button_8.setMinimumSize(new Dimension(40, 20));
		button_8.setForeground(new Color(255, 255, 153));
		button_8.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_8.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_8.setBackground(Color.BLACK);
		button_8.addActionListener(this);
		add(button_8);
		
		button_9 = new JButton("0");
		button_9.setBounds(891, 29, 95, 90);
		button_9.setMinimumSize(new Dimension(40, 20));
		button_9.setForeground(new Color(255, 255, 153));
		button_9.setFont(new Font("Arial Black", Font.BOLD, 56));
		button_9.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		button_9.setBackground(Color.BLACK);
		button_9.addActionListener(this);
		add(button_9);
		
		btnA = new JButton("A");
		btnA.setBounds(10, 123, 95, 90);
		btnA.setMinimumSize(new Dimension(40, 20));
		btnA.setForeground(new Color(255, 255, 153));
		btnA.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnA.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnA.setBackground(Color.BLACK);
		btnA.addActionListener(this);
		add(btnA);
		
		btnBackSpace = new JButton("<");
		btnBackSpace.setBounds(990, 29, 95, 90);
		btnBackSpace.setMinimumSize(new Dimension(40, 20));
		btnBackSpace.setForeground(new Color(255, 255, 153));
		btnBackSpace.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnBackSpace.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnBackSpace.setBackground(Color.BLACK);
		btnBackSpace.addActionListener(this);
		add(btnBackSpace);
		
		btnB = new JButton("B");
		btnB.setBounds(107, 123, 95, 90);
		btnB.setMinimumSize(new Dimension(40, 20));
		btnB.setForeground(new Color(255, 255, 153));
		btnB.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnB.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnB.setBackground(Color.BLACK);
		btnB.addActionListener(this);
		add(btnB);
		
		btnC = new JButton("C");
		btnC.setBounds(206, 123, 95, 90);
		btnC.setMinimumSize(new Dimension(40, 20));
		btnC.setForeground(new Color(255, 255, 153));
		btnC.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnC.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnC.setBackground(Color.BLACK);
		btnC.addActionListener(this);
		add(btnC);
		
		btnD = new JButton("D");
		btnD.setBounds(304, 123, 95, 90);
		btnD.setMinimumSize(new Dimension(40, 20));
		btnD.setForeground(new Color(255, 255, 153));
		btnD.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnD.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnD.setBackground(Color.BLACK);
		btnD.addActionListener(this);
		add(btnD);
		
		btnE = new JButton("E");
		btnE.setBounds(403, 123, 95, 90);
		btnE.setMinimumSize(new Dimension(40, 20));
		btnE.setForeground(new Color(255, 255, 153));
		btnE.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnE.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnE.setBackground(Color.BLACK);
		btnE.addActionListener(this);
		add(btnE);
		
		btnF = new JButton("F");
		btnF.setBounds(501, 123, 95, 90);
		btnF.setMinimumSize(new Dimension(40, 20));
		btnF.setForeground(new Color(255, 255, 153));
		btnF.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnF.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnF.setBackground(Color.BLACK);
		btnF.addActionListener(this);
		add(btnF);
		
		btnG = new JButton("G");
		btnG.setBounds(598, 123, 95, 90);
		btnG.setMinimumSize(new Dimension(40, 20));
		btnG.setForeground(new Color(255, 255, 153));
		btnG.setFont(new Font("Arial Black", Font.PLAIN, 56));
		btnG.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnG.setBackground(Color.BLACK);
		btnG.addActionListener(this);
		add(btnG);
		
		btnH = new JButton("H");
		btnH.setBounds(695, 123, 95, 90);
		btnH.setMinimumSize(new Dimension(40, 20));
		btnH.setForeground(new Color(255, 255, 153));
		btnH.setFont(new Font("Arial Black", Font.PLAIN, 56));
		btnH.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnH.setBackground(Color.BLACK);
		btnH.addActionListener(this);
		add(btnH);
		
		btnI = new JButton("I");
		btnI.setBounds(793, 123, 95, 90);
		btnI.setMinimumSize(new Dimension(40, 20));
		btnI.setForeground(new Color(255, 255, 153));
		btnI.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnI.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnI.setBackground(Color.BLACK);
		btnI.addActionListener(this);
		add(btnI);
		
		btnJ = new JButton("J");
		btnJ.setBounds(891, 123, 95, 90);
		btnJ.setMinimumSize(new Dimension(40, 20));
		btnJ.setForeground(new Color(255, 255, 153));
		btnJ.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnJ.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnJ.setBackground(Color.BLACK);
		btnJ.addActionListener(this);
		add(btnJ);
		
		btnK = new JButton("K");
		btnK.setBounds(10, 217, 95, 90);
		btnK.setMinimumSize(new Dimension(40, 20));
		btnK.setForeground(new Color(255, 255, 153));
		btnK.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnK.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnK.setBackground(Color.BLACK);
		btnK.addActionListener(this);
		add(btnK);
		
		btnL = new JButton("L");
		btnL.setBounds(107, 217, 95, 90);
		btnL.setMinimumSize(new Dimension(40, 20));
		btnL.setForeground(new Color(255, 255, 153));
		btnL.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnL.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnL.setBackground(Color.BLACK);
		btnL.addActionListener(this);
		add(btnL);
		
		btnM = new JButton("M");
		btnM.setBounds(206, 217, 95, 90);
		btnM.setMinimumSize(new Dimension(40, 20));
		btnM.setForeground(new Color(255, 255, 153));
		btnM.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnM.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnM.setBackground(Color.BLACK);
		btnM.addActionListener(this);
		add(btnM);
		
		btnN = new JButton("N");
		btnN.setBounds(304, 217, 95, 90);
		btnN.setMinimumSize(new Dimension(40, 20));
		btnN.setForeground(new Color(255, 255, 153));
		btnN.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnN.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnN.setBackground(Color.BLACK);
		btnN.addActionListener(this);
		add(btnN);
		
		btnO = new JButton("O");
		btnO.setBounds(403, 217, 95, 90);
		btnO.setMinimumSize(new Dimension(40, 20));
		btnO.setForeground(new Color(255, 255, 153));
		btnO.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnO.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnO.setBackground(Color.BLACK);
		btnO.addActionListener(this);
		add(btnO);
		
		btnP = new JButton("P");
		btnP.setBounds(501, 217, 95, 90);
		btnP.setMinimumSize(new Dimension(40, 20));
		btnP.setForeground(new Color(255, 255, 153));
		btnP.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnP.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnP.setBackground(Color.BLACK);
		btnP.addActionListener(this);
		add(btnP);
		
		btnQ = new JButton("Q");
		btnQ.setBounds(598, 217, 95, 90);
		btnQ.setMinimumSize(new Dimension(40, 20));
		btnQ.setForeground(new Color(255, 255, 153));
		btnQ.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnQ.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnQ.setBackground(Color.BLACK);
		btnQ.addActionListener(this);
		add(btnQ);
		
		btnR = new JButton("R");
		btnR.setBounds(695, 217, 95, 90);
		btnR.setMinimumSize(new Dimension(40, 20));
		btnR.setForeground(new Color(255, 255, 153));
		btnR.setFont(new Font("Arial Black", Font.PLAIN, 56));
		btnR.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnR.setBackground(Color.BLACK);
		btnR.addActionListener(this);
		add(btnR);
		
		btnS = new JButton("S");
		btnS.setBounds(793, 217, 95, 90);
		btnS.setMinimumSize(new Dimension(40, 20));
		btnS.setForeground(new Color(255, 255, 153));
		btnS.setFont(new Font("Arial Black", Font.PLAIN, 56));
		btnS.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnS.setBackground(Color.BLACK);
		btnS.addActionListener(this);
		add(btnS);
		
		btnT = new JButton("T");
		btnT.setBounds(891, 217, 95, 90);
		btnT.setMinimumSize(new Dimension(40, 20));
		btnT.setForeground(new Color(255, 255, 153));
		btnT.setFont(new Font("Arial Black", Font.PLAIN, 56));
		btnT.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnT.setBackground(Color.BLACK);
		btnT.addActionListener(this);
		add(btnT);
		
		btnU = new JButton("U");
		btnU.setBounds(204, 309, 95, 90);
		btnU.setMinimumSize(new Dimension(40, 20));
		btnU.setForeground(new Color(255, 255, 153));
		btnU.setFont(new Font("Arial Black", Font.PLAIN, 56));
		btnU.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnU.setBackground(Color.BLACK);
		btnU.addActionListener(this);
		add(btnU);
		
		btnV = new JButton("V");
		btnV.setBounds(303, 309, 95, 90);
		btnV.setMinimumSize(new Dimension(40, 20));
		btnV.setForeground(new Color(255, 255, 153));
		btnV.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnV.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnV.setBackground(Color.BLACK);
		btnV.addActionListener(this);
		add(btnV);
		
		btnW = new JButton("W");
		btnW.setBounds(401, 309, 95, 90);
		btnW.setMinimumSize(new Dimension(40, 20));
		btnW.setForeground(new Color(255, 255, 153));
		btnW.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnW.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnW.setBackground(Color.BLACK);
		btnW.addActionListener(this);
		add(btnW);
		
		btnX = new JButton("X");
		btnX.setBounds(500, 309, 95, 90);
		btnX.setMinimumSize(new Dimension(40, 20));
		btnX.setForeground(new Color(255, 255, 153));
		btnX.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnX.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnX.setBackground(Color.BLACK);
		btnX.addActionListener(this);
		add(btnX);
		
		btnY = new JButton("Y");
		btnY.setBounds(598, 309, 95, 90);
		btnY.setMinimumSize(new Dimension(40, 20));
		btnY.setForeground(new Color(255, 255, 153));
		btnY.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnY.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnY.setBackground(Color.BLACK);
		btnY.addActionListener(this);
		add(btnY);
		
		btnZ = new JButton("Z");
		btnZ.setBounds(695, 309, 95, 90);
		btnZ.setMinimumSize(new Dimension(40, 20));
		btnZ.setForeground(new Color(255, 255, 153));
		btnZ.setFont(new Font("Arial Black", Font.BOLD, 56));
		btnZ.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		btnZ.setBackground(Color.BLACK);
		btnZ.addActionListener(this);
		add(btnZ);
		this.setVisible(false);
	}
	
	/**
	 * Setter for form control
	 * 
	 * @param formControlIn
	 */
	public void setFormControl(int formControlIn)
	{
		formControl = formControlIn;
		if (formControl <= 0)
		{
			this.setVisible(false); // nada
		}
		else if (formControl == 1)
		{
			this.setVisible(true);
		}
		else if (formControl == 2)
		{
			this.setLocation(300, 250); // New User
			this.setVisible(true);
		}
		else if (formControl == 3)
		{
			this.setLocation(300, 250); // login
			this.setVisible(true);
		}
		scale();
		// scale();
	}
	
	/**
	 * Getter for FromControl
	 * 
	 * @return
	 */
	public int getFormControl()
	{
		return formControl;
	}
	
	/**
	 * Maybe a bit too much generic/polymorphism but it works fine.
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (getFormControl() == 1) // Edit User has one textField
		{
			if (e.getActionCommand() != "<")
			{
				if (Main.MainInstance.getUser().whichFocus().getText().length() < 4) // Pins can only be 4 in length
				{
					Main.MainInstance.getUser().whichFocus()
							.setText(Main.MainInstance.getUser().whichFocus().getText() + e.getActionCommand());
				}
			}
			else
			{
				if (Main.MainInstance.getUser().whichFocus().getText().length() > 0)
				{
					String temp = Main.MainInstance.getUser().whichFocus().getText();
					temp = temp.substring(0, temp.length() - 1);
					Main.MainInstance.getUser().whichFocus().setText(temp);
				}
			}
		}
		else if (getFormControl() == 2) // New User has two textField
		{
			if (e.getActionCommand() != "<")
			{
				if (Main.MainInstance.getUser().whichFocus().getName().equals("pinField"))
				{
					if (Main.MainInstance.getUser().whichFocus().getText().length() < 4) // pinField < 4 text
					{
						Main.MainInstance.getUser().whichFocus()
								.setText(Main.MainInstance.getUser().whichFocus().getText() + e.getActionCommand());
					}
				}
				else
				{
					Main.MainInstance.getUser().whichFocus()
							.setText(Main.MainInstance.getUser().whichFocus().getText() + e.getActionCommand());
					
				}
			}
			else
			{
				if (Main.MainInstance.getUser().whichFocus().getText().length() > 0)
				{
					String temp = Main.MainInstance.getUser().whichFocus().getText();
					temp = temp.substring(0, temp.length() - 1);
					Main.MainInstance.getUser().whichFocus().setText(temp);
				}
			}
		}
		else if (getFormControl() == 3)
		{
			if (e.getActionCommand() != "<")
			{
				if (Main.MainInstance.getLogin().whichFocus().getName().equals("pinField"))
				{
					if (Main.MainInstance.getLogin().whichFocus().getText().length() < 4) // pinField < 4 text
					{
						Main.MainInstance.getLogin().whichFocus()
								.setText(Main.MainInstance.getLogin().whichFocus().getText() + e.getActionCommand());
					}
				}
				else
				{
					Main.MainInstance.getLogin().whichFocus()
							.setText(Main.MainInstance.getLogin().whichFocus().getText() + e.getActionCommand()); // userField
																													// //
																													// text
				}
			}
			// TODO FIX PINFIELD
			else
			{
				if (Main.MainInstance.getLogin().whichFocus().getText().length() > 0)
				{
					String temp = Main.MainInstance.getLogin().whichFocus().getText();
					temp = temp.substring(0, temp.length() - 1);
					Main.MainInstance.getLogin().whichFocus().setText(temp);
				}
			}
		}
	}
	
	/**
	 * What is a magic number???
	 */
	void scale()
	{
		if (formControl == 1 || formControl == 2)
			this.setLocation((int) (300 * Main.MainInstance.getSM().gxr()),
					(int) (200 * Main.MainInstance.getSM().gyr()));
		else
			this.setLocation((int) (300 * Main.MainInstance.getSM().gxr()),
					(int) (250 * Main.MainInstance.getSM().gyr()));
		
		getButton().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_1().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_2().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_3().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_4().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_5().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_6().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_7().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_8().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getButton_9().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnA().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnB().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnC().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnD().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnE().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnF().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnG().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnH().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnI().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnJ().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnK().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnL().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnM().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnN().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnO().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnP().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnQ().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnR().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnS().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnT().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnU().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnV().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnW().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnX().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnY().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnZ().setSize((int) (95 * Main.MainInstance.getSM().gxr()), (int) (90 * Main.MainInstance.getSM().gyr()));
		
		getBtnBackSpace().setSize((int) (95 * Main.MainInstance.getSM().gxr()),
				(int) (90 * Main.MainInstance.getSM().gyr()));
		
		/* Location */
		
		getButton().setLocation((int) (10 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_1().setLocation((int) (107 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_2().setLocation((int) (206 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_3().setLocation((int) (304 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_4().setLocation((int) (403 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_5().setLocation((int) (501 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_6().setLocation((int) (598 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_7().setLocation((int) (695 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_8().setLocation((int) (793 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getButton_9().setLocation((int) (891 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getBtnA().setLocation((int) (10 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnBackSpace().setLocation((int) (990 * Main.MainInstance.getSM().gxr()),
				(int) (29 * Main.MainInstance.getSM().gyr()));
		
		getBtnB().setLocation((int) (107 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnC().setLocation((int) (206 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnD().setLocation((int) (304 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnE().setLocation((int) (403 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnF().setLocation((int) (501 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnG().setLocation((int) (598 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnH().setLocation((int) (695 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnI().setLocation((int) (793 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnJ().setLocation((int) (891 * Main.MainInstance.getSM().gxr()),
				(int) (123 * Main.MainInstance.getSM().gyr()));
		
		getBtnK().setLocation((int) (10 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnL().setLocation((int) (107 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnM().setLocation((int) (206 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnN().setLocation((int) (304 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnO().setLocation((int) (403 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnP().setLocation((int) (501 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnQ().setLocation((int) (598 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnR().setLocation((int) (695 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnS().setLocation((int) (793 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnT().setLocation((int) (891 * Main.MainInstance.getSM().gxr()),
				(int) (217 * Main.MainInstance.getSM().gyr()));
		
		getBtnU().setLocation((int) (204 * Main.MainInstance.getSM().gxr()),
				(int) (309 * Main.MainInstance.getSM().gyr()));
		
		getBtnV().setLocation((int) (303 * Main.MainInstance.getSM().gxr()),
				(int) (309 * Main.MainInstance.getSM().gyr()));
		
		getBtnW().setLocation((int) (401 * Main.MainInstance.getSM().gxr()),
				(int) (309 * Main.MainInstance.getSM().gyr()));
		
		getBtnX().setLocation((int) (500 * Main.MainInstance.getSM().gxr()),
				(int) (309 * Main.MainInstance.getSM().gyr()));
		
		getBtnY().setLocation((int) (598 * Main.MainInstance.getSM().gxr()),
				(int) (309 * Main.MainInstance.getSM().gyr()));
		
		getBtnZ().setLocation((int) (695 * Main.MainInstance.getSM().gxr()),
				(int) (309 * Main.MainInstance.getSM().gyr()));
		
		int newFontSize = (int) (56 * Main.MainInstance.getSM().gxr());
		
		Font newFont = new Font("Arial Black", Font.BOLD, newFontSize);
		
		getButton_1().setFont(newFont);
		
		getButton_2().setFont(newFont);
		
		getButton_3().setFont(newFont);
		
		getButton_4().setFont(newFont);
		
		getButton_5().setFont(newFont);
		
		getButton_6().setFont(newFont);
		
		getButton_7().setFont(newFont);
		
		getButton_8().setFont(newFont);
		
		getButton_9().setFont(newFont);
		
		getBtnA().setFont(newFont);
		
		getBtnBackSpace().setFont(newFont);
		
		getBtnB().setFont(newFont);
		
		getBtnC().setFont(newFont);
		
		getBtnD().setFont(newFont);
		
		getBtnE().setFont(newFont);
		
		getBtnF().setFont(newFont);
		
		getBtnG().setFont(newFont);
		
		getBtnH().setFont(newFont);
		
		getBtnI().setFont(newFont);
		
		getBtnJ().setFont(newFont);
		
		getBtnK().setFont(newFont);
		
		getBtnL().setFont(newFont);
		
		getBtnM().setFont(newFont);
		
		getBtnN().setFont(newFont);
		
		getBtnO().setFont(newFont);
		
		getBtnP().setFont(newFont);
		
		getBtnQ().setFont(newFont);
		
		getBtnR().setFont(newFont);
		
		getBtnS().setFont(newFont);
		
		getBtnT().setFont(newFont);
		
		getBtnU().setFont(newFont);
		
		getBtnV().setFont(newFont);
		
		getBtnW().setFont(newFont);
		
		getBtnX().setFont(newFont);
		
		getBtnY().setFont(newFont);
		
		getBtnZ().setFont(newFont);
		
	}
	
	/**
	 * @return the button_7
	 */
	protected JButton getButton_7()
	{
		return button_7;
	}
	
	/**
	 * @param button_7 the button_7 to set
	 */
	protected void setButton_7(JButton button_7)
	{
		this.button_7 = button_7;
	}
	
	/**
	 * @return the btnZ
	 */
	protected JButton getBtnZ()
	{
		return btnZ;
	}
	
	/**
	 * @param btnZ the btnZ to set
	 */
	protected void setBtnZ(JButton btnZ)
	{
		this.btnZ = btnZ;
	}
	
	/**
	 * @return the btnN
	 */
	protected JButton getBtnN()
	{
		return btnN;
	}
	
	/**
	 * @param btnN the btnN to set
	 */
	protected void setBtnN(JButton btnN)
	{
		this.btnN = btnN;
	}
	
	/**
	 * @return the button_8
	 */
	protected JButton getButton_8()
	{
		return button_8;
	}
	
	/**
	 * @param button_8 the button_8 to set
	 */
	protected void setButton_8(JButton button_8)
	{
		this.button_8 = button_8;
	}
	
	/**
	 * @return the btnX
	 */
	protected JButton getBtnX()
	{
		return btnX;
	}
	
	/**
	 * @param btnX the btnX to set
	 */
	protected void setBtnX(JButton btnX)
	{
		this.btnX = btnX;
	}
	
	/**
	 * @return the button_9
	 */
	protected JButton getButton_9()
	{
		return button_9;
	}
	
	/**
	 * @param button_9 the button_9 to set
	 */
	protected void setButton_9(JButton button_9)
	{
		this.button_9 = button_9;
	}
	
	/**
	 * @return the btn_A
	 */
	protected JButton getBtnA()
	{
		return btnA;
	}
	
	/**
	 * @param btn_A the btn_A to set
	 */
	protected void setBtnA(JButton btn_A)
	{
		this.btnA = btn_A;
	}
	
	/**
	 * @return the btnP
	 */
	protected JButton getBtnP()
	{
		return btnP;
	}
	
	/**
	 * @param btnP the btnP to set
	 */
	protected void setBtnP(JButton btnP)
	{
		this.btnP = btnP;
	}
	
	/**
	 * @return the btnK
	 */
	protected JButton getBtnK()
	{
		return btnK;
	}
	
	/**
	 * @param btnK the btnK to set
	 */
	protected void setBtnK(JButton btnK)
	{
		this.btnK = btnK;
	}
	
	/**
	 * @return the btnJ
	 */
	protected JButton getBtnJ()
	{
		return btnJ;
	}
	
	/**
	 * @param btnJ the btnJ to set
	 */
	protected void setBtnJ(JButton btnJ)
	{
		this.btnJ = btnJ;
	}
	
	/**
	 * @return the btnV
	 */
	protected JButton getBtnV()
	{
		return btnV;
	}
	
	/**
	 * @param btnV the btnV to set
	 */
	protected void setBtnV(JButton btnV)
	{
		this.btnV = btnV;
	}
	
	/**
	 * @return the button_4
	 */
	protected JButton getButton_4()
	{
		return button_4;
	}
	
	/**
	 * @param button_4 the button_4 to set
	 */
	protected void setButton_4(JButton button_4)
	{
		this.button_4 = button_4;
	}
	
	/**
	 * @return the btnS
	 */
	protected JButton getBtnS()
	{
		return btnS;
	}
	
	/**
	 * @param btnS the btnS to set
	 */
	protected void setBtnS(JButton btnS)
	{
		this.btnS = btnS;
	}
	
	/**
	 * @return the btnR
	 */
	protected JButton getBtnR()
	{
		return btnR;
	}
	
	/**
	 * @param btnR the btnR to set
	 */
	protected void setBtnR(JButton btnR)
	{
		this.btnR = btnR;
	}
	
	/**
	 * @return the btnQ
	 */
	protected JButton getBtnQ()
	{
		return btnQ;
	}
	
	/**
	 * @param btnQ the btnQ to set
	 */
	protected void setBtnQ(JButton btnQ)
	{
		this.btnQ = btnQ;
	}
	
	/**
	 * @return the btnW
	 */
	protected JButton getBtnW()
	{
		return btnW;
	}
	
	/**
	 * @param btnW the btnW to set
	 */
	protected void setBtnW(JButton btnW)
	{
		this.btnW = btnW;
	}
	
	/**
	 * @return the button
	 */
	protected JButton getButton()
	{
		return button;
	}
	
	/**
	 * @param button the button to set
	 */
	protected void setButton(JButton button)
	{
		this.button = button;
	}
	
	/**
	 * @return the btnL
	 */
	protected JButton getBtnL()
	{
		return btnL;
	}
	
	/**
	 * @param btnL the btnL to set
	 */
	protected void setBtnL(JButton btnL)
	{
		this.btnL = btnL;
	}
	
	/**
	 * @return the button_6
	 */
	protected JButton getButton_6()
	{
		return button_6;
	}
	
	/**
	 * @param button_6 the button_6 to set
	 */
	protected void setButton_6(JButton button_6)
	{
		this.button_6 = button_6;
	}
	
	/**
	 * @return the button_5
	 */
	protected JButton getButton_5()
	{
		return button_5;
	}
	
	/**
	 * @param button_5 the button_5 to set
	 */
	protected void setButton_5(JButton button_5)
	{
		this.button_5 = button_5;
	}
	
	/**
	 * @return the btnO
	 */
	protected JButton getBtnO()
	{
		return btnO;
	}
	
	/**
	 * @param btnO the btnO to set
	 */
	protected void setBtnO(JButton btnO)
	{
		this.btnO = btnO;
	}
	
	/**
	 * @return the btnD
	 */
	protected JButton getBtnD()
	{
		return btnD;
	}
	
	/**
	 * @param btnD the btnD to set
	 */
	protected void setBtnD(JButton btnD)
	{
		this.btnD = btnD;
	}
	
	/**
	 * @return the button_1
	 */
	protected JButton getButton_1()
	{
		return button_1;
	}
	
	/**
	 * @param button_1 the button_1 to set
	 */
	protected void setButton_1(JButton button_1)
	{
		this.button_1 = button_1;
	}
	
	/**
	 * @return the btnI
	 */
	protected JButton getBtnI()
	{
		return btnI;
	}
	
	/**
	 * @param btnI the btnI to set
	 */
	protected void setBtnI(JButton btnI)
	{
		this.btnI = btnI;
	}
	
	/**
	 * @return the btnT
	 */
	protected JButton getBtnT()
	{
		return btnT;
	}
	
	/**
	 * @param btnT the btnT to set
	 */
	protected void setBtnT(JButton btnT)
	{
		this.btnT = btnT;
	}
	
	/**
	 * @return the btnH
	 */
	protected JButton getBtnH()
	{
		return btnH;
	}
	
	/**
	 * @param btnH the btnH to set
	 */
	protected void setBtnH(JButton btnH)
	{
		this.btnH = btnH;
	}
	
	/**
	 * @return the btnC
	 */
	protected JButton getBtnC()
	{
		return btnC;
	}
	
	/**
	 * @param btnC the btnC to set
	 */
	protected void setBtnC(JButton btnC)
	{
		this.btnC = btnC;
	}
	
	/**
	 * @return the btnM
	 */
	protected JButton getBtnM()
	{
		return btnM;
	}
	
	/**
	 * @param btnM the btnM to set
	 */
	protected void setBtnM(JButton btnM)
	{
		this.btnM = btnM;
	}
	
	/**
	 * @return the btnE
	 */
	protected JButton getBtnE()
	{
		return btnE;
	}
	
	/**
	 * @param btnE the btnE to set
	 */
	protected void setBtnE(JButton btnE)
	{
		this.btnE = btnE;
	}
	
	/**
	 * @return the btnU
	 */
	protected JButton getBtnU()
	{
		return btnU;
	}
	
	/**
	 * @param btnU the btnU to set
	 */
	protected void setBtnU(JButton btnU)
	{
		this.btnU = btnU;
	}
	
	/**
	 * @return the button_2
	 */
	protected JButton getButton_2()
	{
		return button_2;
	}
	
	/**
	 * @param button_2 the button_2 to set
	 */
	protected void setButton_2(JButton button_2)
	{
		this.button_2 = button_2;
	}
	
	/**
	 * @return the button_3
	 */
	protected JButton getButton_3()
	{
		return button_3;
	}
	
	/**
	 * @param button_3 the button_3 to set
	 */
	protected void setButton_3(JButton button_3)
	{
		this.button_3 = button_3;
	}
	
	/**
	 * @return the btnF
	 */
	protected JButton getBtnF()
	{
		return btnF;
	}
	
	/**
	 * @param btnF the btnF to set
	 */
	protected void setBtnF(JButton btnF)
	{
		this.btnF = btnF;
	}
	
	/**
	 * @return the btnG
	 */
	protected JButton getBtnG()
	{
		return btnG;
	}
	
	/**
	 * @param btnG the btnG to set
	 */
	protected void setBtnG(JButton btnG)
	{
		this.btnG = btnG;
	}
	
	/**
	 * @return the btnB
	 */
	protected JButton getBtnB()
	{
		return btnB;
	}
	
	/**
	 * @param btnB the btnB to set
	 */
	protected void setBtnB(JButton btnB)
	{
		this.btnB = btnB;
	}
	
	/**
	 * @return the btnY
	 */
	protected JButton getBtnY()
	{
		return btnY;
	}
	
	/**
	 * @param btnY the btnY to set
	 */
	protected void setBtnY(JButton btnY)
	{
		this.btnY = btnY;
	}
	
	/**
	 * @return the btnBackSpace
	 */
	protected JButton getBtnBackSpace()
	{
		return btnBackSpace;
	}
	
	/**
	 * @param btnBackSpace the btnBackSpace to set
	 */
	protected void setBtnBackSpace(JButton btnBackSpace)
	{
		this.btnBackSpace = btnBackSpace;
	}
}
