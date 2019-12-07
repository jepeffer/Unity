package Unity;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.joda.time.DateTime;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

@SuppressWarnings("serial")
public class FrmCalendar extends JPanel implements Form
{
	String[]	calendarOneDate	= new String[3];
	String[]	calendarTwoDate	= new String[3];
	JLabel		dateFromLabel;
	JLabel		dateToLabel;
	JButton		btnCalendarOneLeft;
	JButton		btnCalendarOneRight;
	JButton		btnCalendarTwoLeft;
	JButton		btnCalendarTwoRight;
	
	/**
	 * Create the panel.
	 */
	public FrmCalendar()
	{
		this.setVisible(false);
		this.setOpaque(false);
		setPreferredSize(new Dimension(1080, 700));
		setName("FrmCalendar");
		this.setLocation(100, 100);
		setLayout(null);
		
		dateFromLabel = new JLabel("DATE FROM");
		dateFromLabel.setForeground(Color.CYAN);
		dateFromLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		dateFromLabel.setBounds(190, 55, 158, 30);
		dateToLabel = new JLabel("DATE TO");
		dateToLabel.setForeground(Color.CYAN);
		dateToLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		dateToLabel.setBounds(600, 55, 158, 30);
		btnCalendarOneLeft = new JButton("<");
		btnCalendarOneLeft.setBounds(98, 19, 50, 25);
		btnCalendarOneRight = new JButton(">");
		btnCalendarOneRight.setBounds(398, 19, 50, 25);
		btnCalendarTwoLeft = new JButton("<");
		btnCalendarTwoLeft.setBounds(501, 19, 50, 25);
		btnCalendarTwoRight = new JButton(">");
		btnCalendarTwoRight.setBounds(799, 19, 50, 25);
		
		setCalendarOneDate(getCurrentDate().split("/"));
		setCalendarTwoDate(getCurrentDate().split("/"));
		createCalendarOneButtons();
		createCalendarTwoButtons();
		setCalendarOneButtonColor(getCalendarOneCurrentDay(), -1);
		setCalendarTwoButtonColor(getCalendarTwoCurrentDay(), -1);
		add(dateToLabel);
		add(dateFromLabel);
		add(btnCalendarTwoLeft);
		add(btnCalendarTwoRight);
		add(btnCalendarOneRight);
		add(btnCalendarOneLeft);
		eventHandlers();
		revalidate();
	}
	
	void eventHandlers()
	{
		btnCalendarOneLeft.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Integer temp = getCalendarOneCurrentMonth() - 1;
				changeCalendarOneMonth(temp.toString());
			}
		});
		
		btnCalendarOneRight.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Integer temp = getCalendarOneCurrentMonth() + 1;
				changeCalendarOneMonth(temp.toString());
			}
		});
		
		btnCalendarTwoLeft.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Integer temp = getCalendarTwoCurrentMonth() - 1;
				changeCalendarTwoMonth(temp.toString());
			}
		});
		
		btnCalendarTwoRight.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Integer temp = getCalendarTwoCurrentMonth() + 1;
				changeCalendarTwoMonth(temp.toString());
			}
		});
	}
	
	void createCalendarOneButtons()
	{
		int xCounter = 100;
		int yCounter = 100;
		
		for (Integer i = 1; i <= 31; i++)
		{
			JButton newBtn = new JButton(i.toString());
			newBtn.setSize(50, 50);
			newBtn.setBackground(Color.WHITE);
			newBtn.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					changeCalendarOneDay(newBtn.getText());
				}
			});
			
			newBtn.setLocation(xCounter, yCounter);
			xCounter += newBtn.getWidth();
			if (i % 7 == 0)
			{
				yCounter += newBtn.getHeight();
				xCounter = 100;
			}
			newBtn.setToolTipText("Calendar One Button");
			add(newBtn);
		}
	}
	
	void createCalendarTwoButtons()
	{
		int xCounter = 500;
		int yCounter = 100;
		
		for (Integer i = 1; i <= 31; i++)
		{
			JButton newBtn = new JButton(i.toString());
			newBtn.setSize(50, 50);
			newBtn.setBackground(Color.WHITE);
			newBtn.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					changeCalendarTwoDay(newBtn.getText());
				}
			});
			
			newBtn.setLocation(xCounter, yCounter);
			xCounter += newBtn.getWidth();
			if (i % 7 == 0)
			{
				yCounter += newBtn.getHeight();
				xCounter = 500;
			}
			newBtn.setToolTipText("Calendar Two Button");
			add(newBtn);
		}
	}
	
	void setCalendarOneDate(String[] newDateIn)
	{
		calendarOneDate[0] = newDateIn[0];
		calendarOneDate[1] = newDateIn[1];
		calendarOneDate[2] = newDateIn[2];
		setDateFromLabel(getCalendarOneDate().toString());
	}
	
	void setCalendarTwoDate(String[] newDateIn)
	{
		calendarTwoDate[0] = newDateIn[0];
		calendarTwoDate[1] = newDateIn[1];
		calendarTwoDate[2] = newDateIn[2];
		setDateToLabel(getCalendarTwoDate().toString());
	}
	
	private void setAvaliableDayButtons(int monthIn, int yearIn, int whichCalendar)
	{
		int numDays = getNumberOfDays(monthIn, yearIn);
		for (Component c : getComponents())
		{
			if (c instanceof JButton)
			{
				if (whichCalendar == 1 && ((JButton) c).getToolTipText() != null
						&& ((JButton) c).getToolTipText().equals("Calendar One Button"))
				{
					
					int currButtonDay = Integer.parseInt(((JButton) c).getText());
					
					if (currButtonDay > numDays)
					{
						c.setEnabled(false);
						c.setVisible(false);
					}
					else
					{
						c.setEnabled(true);
						c.setVisible(true);
					}
					/* Checks for if the current day selected is greater than the number of days in the month */
					if (((JButton) c).getBackground() == Color.red)
					{
						if (currButtonDay > numDays)
						{
							setCalendarOneButtonColor(1, currButtonDay);
							changeCalendarOneDay("1");
						}
					}
					
				}
				else if (whichCalendar == 2 && ((JButton) c).getToolTipText() != null
						&& ((JButton) c).getToolTipText().equals("Calendar Two Button"))
				{
					int currButtonDay = Integer.parseInt(((JButton) c).getText());
					
					if (currButtonDay > numDays)
					{
						c.setEnabled(false);
						c.setVisible(false);
					}
					else
					{
						c.setEnabled(true);
						c.setVisible(true);
					}
					/* Checks for if the current day selected is greater than the number of days in the month */
					if (((JButton) c).getBackground() == Color.red)
					{
						if (currButtonDay > numDays)
						{
							setCalendarTwoButtonColor(1, currButtonDay);
							changeCalendarTwoDay("1");
						}
					}
				}
			}
		}
		revalidate();
	}
	
	String getCalendarOneDate()
	{
		String tempDate = String.format("%02d/%02d/%s", Integer.parseInt(calendarOneDate[0]),
				Integer.parseInt(calendarOneDate[1]), calendarOneDate[2]);
		return tempDate;
	}
	
	String getCalendarTwoDate()
	{
		String tempDate = String.format("%02d/%02d/%s", Integer.parseInt(calendarTwoDate[0]),
				Integer.parseInt(calendarTwoDate[1]), calendarTwoDate[2]);
		return tempDate;
	}
	
	Date getConvertedCalendarOneDate()
	{
		String tempString = String.format("%s-%s-%s", calendarOneDate[2], calendarOneDate[0], calendarOneDate[1]);
		Date tempDate = null;
		tempDate = Date.valueOf(tempString);
		return tempDate;
	}
	
	Date getConvertedCalendarTwoDate()
	{
		String tempString = String.format("%s-%s-%s", calendarTwoDate[2], calendarTwoDate[0], calendarTwoDate[1]);
		Date tempDate = null;
		tempDate = Date.valueOf(tempString);
		return tempDate;
	}
	
	void changeCalendarTwoDay(String newDayIn)
	{
		setCalendarTwoButtonColor(Integer.parseInt(newDayIn), getCalendarTwoCurrentDay());
		calendarTwoDate[1] = newDayIn;
		setDateToLabel(getCalendarTwoDate().toString());
	}
	
	void changeCalendarOneDay(String newDayIn)
	{
		setCalendarOneButtonColor(Integer.parseInt(newDayIn), getCalendarOneCurrentDay());
		calendarOneDate[1] = newDayIn;
		setDateFromLabel(getCalendarOneDate().toString());
	}
	
	
	void setCalendarOneButtonColor(int buttonNumberIn, int oldButtonNumberIn)
	{
		if (oldButtonNumberIn != -1)
		{
			getComponent((oldButtonNumberIn - 1)).setBackground(Color.WHITE);
		}
		getComponent(buttonNumberIn - 1).setBackground(Color.RED);
		
	}
	
	void setCalendarTwoButtonColor(int buttonNumberIn, int oldButtonNumberIn)
	{
		if (oldButtonNumberIn != -1)
		{
			oldButtonNumberIn = oldButtonNumberIn + 31;
			getComponent((oldButtonNumberIn - 1)).setBackground(Color.WHITE);
		}
		buttonNumberIn = buttonNumberIn + 31;
		getComponent((buttonNumberIn - 1)).setBackground(Color.RED);
	}
	
	void changeCalendarTwoMonth(String newMonthIn)
	{
		if (newMonthIn.equals("0"))
		{
			newMonthIn = "12";
			Integer temp = getCalendarTwoCurrentYear() - 1;
			changeCalendarTwoYear(temp.toString());
		}
		else if (newMonthIn.equals("13"))
		{
			newMonthIn = "1";
			Integer temp = getCalendarTwoCurrentYear() + 1;
			changeCalendarTwoYear(temp.toString());
		}
		calendarTwoDate[0] = newMonthIn;
		setAvaliableDayButtons(Integer.parseInt(calendarTwoDate[0]), Integer.parseInt(calendarTwoDate[2]), 2);
		setDateToLabel(getCalendarTwoDate().toString());
	}
	
	void changeCalendarOneMonth(String newMonthIn)
	{
		if (newMonthIn.equals("0"))
		{
			newMonthIn = "12";
			Integer temp = getCalendarOneCurrentYear() - 1;
			changeCalendarOneYear(temp.toString());
		}
		else if (newMonthIn.equals("13"))
		{
			newMonthIn = "1";
			Integer temp = getCalendarOneCurrentYear() + 1;
			changeCalendarOneYear(temp.toString());
		}
		calendarOneDate[0] = newMonthIn;
		setAvaliableDayButtons(Integer.parseInt(calendarOneDate[0]), Integer.parseInt(calendarOneDate[2]), 1);
		setDateFromLabel(getCalendarOneDate().toString());
	}
	
	void changeCalendarTwoYear(String newYearIn)
	{
		calendarTwoDate[2] = newYearIn;
	}
	
	void changeCalendarOneYear(String newYearIn)
	{
		calendarOneDate[2] = newYearIn;
	}
	
	int getCalendarOneCurrentMonth()
	{
		int temp = Integer.parseInt(calendarOneDate[0]);
		return temp;
	}
	
	int getCalendarOneCurrentYear()
	{
		int temp = Integer.parseInt(calendarOneDate[2]);
		return temp;
	}
	
	int getCalendarOneCurrentDay()
	{
		int temp = Integer.parseInt(calendarOneDate[1]);
		return temp;
	}
	
	int getCalendarTwoCurrentMonth()
	{
		int temp = Integer.parseInt(calendarTwoDate[0]);
		return temp;
	}
	
	int getCalendarTwoCurrentYear()
	{
		int temp = Integer.parseInt(calendarTwoDate[2]);
		return temp;
	}
	
	int getCalendarTwoCurrentDay()
	{
		int temp = Integer.parseInt(calendarTwoDate[1]);
		return temp;
	}
	
	private int getNumberOfDays(int currentMonthIn, int currentYearIn)
	{
		int numDays = 0;
		switch (currentMonthIn)
		{
			case 1:
			{
				numDays = 31;
				break;
			}
			case 2:
			{
				if (isLeapYear(currentYearIn))
				{
					numDays = 29;
				}
				else
				{
					numDays = 28;
				}
				break;
			}
			case 3:
			{
				numDays = 31;
				break;
			}
			case 4:
			{
				numDays = 30;
				break;
			}
			case 5:
			{
				numDays = 31;
				break;
			}
			case 6:
			{
				numDays = 30;
				break;
			}
			case 7:
			{
				numDays = 31;
				break;
			}
			case 8:
			{
				numDays = 31;
				break;
			}
			case 9:
			{
				numDays = 30;
				break;
			}
			case 10:
			{
				numDays = 31;
				break;
			}
			case 11:
			{
				numDays = 30;
				break;
			}
			case 12:
			{
				numDays = 31;
				break;
			}
		}
		
		return numDays;
	}
	
	private boolean isLeapYear(int currentYearIn)
	{
		int year = currentYearIn;
		boolean flag = false;
		if (((year % 4 == 0) && (year % 100 != 0)) || year % 400 == 0)
		{
			flag = true;
		}
		else
		{
			flag = false;
		}
		return flag;
	}
	
	String getCurrentDate()
	{
		DateTime dt = new DateTime();
		return dt.toString("MM/dd/yyyy");
	}
	
	void setDateFromLabel(String dateIn)
	{
		dateFromLabel.setText(dateIn);
	}
	
	void setDateToLabel(String dateIn)
	{
		dateToLabel.setText(dateIn);
	}
	
	String getDateFromLabel()
	{
		return dateFromLabel.getText();
	}
	
	String getDateToLabel()
	{
		return dateToLabel.getText();
	}
	
	@Override
	public Form getNew()
	{
		return new FrmCalendar();
	}
	
	@Override
	public void scale()
	{
		// TODO Auto-generated method stub
		
	}
}
