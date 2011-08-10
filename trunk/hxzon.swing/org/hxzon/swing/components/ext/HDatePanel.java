package org.hxzon.swing.components.ext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.hxzon.swing.components.easy.HMonthSpinner;
import org.hxzon.swing.components.easy.HYearSpinner;
import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;

public class HDatePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat dayFormat = new SimpleDateFormat("d");

	private final HYearSpinner yearSpinner = new HYearSpinner();;
	private final HMonthSpinner monthSpinner = new HMonthSpinner();
	private final JPanel daysPanel = new JPanel(new GridLayout(0, 7));;

	private Color dayLableBackgroundColor = Color.WHITE;
	private Color dayLabelForegroundColor = Color.BLACK;
	private Color dayLabelSelectedBorderColor = Color.BLUE;
	private Color dayLableHoverBorderColor = Color.red;
	private Color dayLableHoverBackgroundColor = Color.red;
	private Color selectStartBgColor = new Color(255, 211, 137);
	private Color selectEndBgColor = new Color(255, 176, 89);

	private final Calendar calendar = Calendar.getInstance();
	private final HChangeListenerSupport<HDatePanel> listenerSupport = new HChangeListenerSupport<HDatePanel>();

	public HDatePanel() {
		this.initPanel();
		this.useCurrentDate();
	}

	public void useCurrentDate() {
		updateDate(new Date());
	}

	public void setDate(Date date) {
		updateDate(date);
	}

	private void updateDate(Date date) {
		calendar.setTime(date);
		updatePopup();
		yearSpinner.setYear(calendar.get(Calendar.YEAR));
		monthSpinner.setMonth(calendar.get(Calendar.MONTH) + 1);
		listenerSupport.fireChangeListener(HDatePanel.this);
	}

	private void updateYear(int year) {
		calendar.set(Calendar.YEAR, year);
		updatePopup();
//		listenerSupport.fireChangeListener(HDatePanel.this);
	}

	private void updateMonth(int month) {
		calendar.set(Calendar.MONTH, month - 1);
		updatePopup();
//		listenerSupport.fireChangeListener(HDatePanel.this);
	}

	public Calendar getDate() {
		return calendar;
	}

	public String getDateAsString() {
		return dateFormat.format(calendar.getTime());
	}

	private void initPanel() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		header.setBackground(new Color(0, 84, 227));
		header.setForeground(Color.white);
		header.setPreferredSize(new Dimension(1, 25));

		yearSpinner.setYear(calendar.get(Calendar.YEAR));
		header.add(yearSpinner);

		monthSpinner.setMonth(calendar.get(Calendar.MONTH));
		header.add(monthSpinner);

		JPanel pWeeks = new JPanel(new GridLayout(0, 7));
		pWeeks.setBackground(dayLableBackgroundColor);
		DateFormatSymbols sy = new DateFormatSymbols(Locale.getDefault());
		String strWeeks[] = sy.getShortWeekdays();

		for (int i = 1; i <= 7; i++) {
			JLabel label = new JLabel();
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setForeground(dayLabelForegroundColor);
			label.setText(strWeeks[i]);
			pWeeks.add(label);
		}

		daysPanel.setBackground(dayLableBackgroundColor);
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
		pCenter.setBackground(dayLableBackgroundColor);
		pCenter.add(pWeeks);

		pCenter.add(daysPanel);

		JLabel todayLabel = new JDayLable(new Date(), dateFormat.format(new Date()), false);
		todayLabel.setHorizontalAlignment(JLabel.CENTER);

		todayLabel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				updateDate(new Date());
			}
		});

		JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pSouth.add(todayLabel);
		todayLabel.setPreferredSize(new Dimension(150, 25));

		pSouth.setOpaque(false);

		this.setOpaque(false);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#0055e5")), BorderFactory.createEmptyBorder(0, 0, 10, 0)));
		setLayout(new BorderLayout());

		add(header,BorderLayout.NORTH);
		add(pCenter,BorderLayout.CENTER);
		add(pSouth,BorderLayout.SOUTH);

		yearSpinner.addChangeListener(new HChangeListener<HYearSpinner>() {
			@Override
			public void change(HYearSpinner source) {
				updateYear(source.getYear());
			}
		});
		monthSpinner.addChangeListener(new HChangeListener<HMonthSpinner>() {
			@Override
			public void change(HMonthSpinner source) {
				updateMonth(source.getMonth());
			}
		});
	}

	protected void updatePopup() {
		daysPanel.removeAll();

		Calendar setupCalendar = (Calendar) calendar.clone();
		setupCalendar.set(Calendar.DAY_OF_MONTH, 1);
		int first = setupCalendar.get(Calendar.DAY_OF_WEEK);
		setupCalendar.add(Calendar.DATE, -first);
		int flag = 0;
		for (int i = 0; i < 42; i++) {
			setupCalendar.add(Calendar.DATE, 1);
			String txt = dayFormat.format(setupCalendar.getTime());
			JLabel label = new JDayLable(setupCalendar.getTime(), txt);
			daysPanel.add(label);
			if (txt.equals("1")) {
				flag++;
			}
			if (flag != 1) {
				label.setEnabled(false);
			}
		}
		daysPanel.revalidate();
	}

//	class TopBottomLineBorder extends AbstractBorder {
//		private Color lineColor;
//
//		public TopBottomLineBorder(Color color) {
//			lineColor = color;
//		}
//
//		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//			g.setColor(lineColor);
//			g.drawLine(0, 0, c.getWidth(), 0);
//			g.drawLine(0, c.getHeight() - 1, c.getWidth(), c.getHeight() - 1);
//
//		}
//	}

	private class JDayLable extends JLabel implements MouseListener {
		private static final long serialVersionUID = 1L;
		private Date date;
		private boolean mouseHover = false;

		public JDayLable(Date date, String text) {
			this(date, text, true);
		}

		public JDayLable(Date date, String text, boolean b) {
			setHorizontalAlignment(JLabel.CENTER);
			setForeground(dayLabelForegroundColor);
			setPreferredSize(new Dimension(40, 20));
			setToolTipText(dateFormat.format(date));
			addMouseListener(this);
			this.date = date;
			setText(text);

			if (calendar.getTime().equals(date) && b == true) {
				setBorder(new LineBorder(dayLabelSelectedBorderColor, 1));
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {

			if (isEnabled()) {
				setBackground(dayLableBackgroundColor);
				setForeground(dayLabelForegroundColor);
			}
			updateDate(date);
			this.mouseHover = false;
			repaint();
		}

		public void mouseEntered(MouseEvent e) {
			mouseHover = true;
			repaint();
		}

		public void mouseExited(MouseEvent e) {
			mouseHover = false;
			repaint();
		}

		/*
		public void paint(Graphics g)
		{
		  super.paint(g);
		  
		  if(isToday && todayIcon != null && isEnabled())
		  {
		    int x = (this.getWidth() - todayIcon.getIconWidth()) / 2;
		    int y = (this.getHeight() - todayIcon.getIconHeight()) / 2;
		    todayIcon.paintIcon(this, g, x, y);
		  }
		}
		*/

		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			if (mouseHover) {
				g.setColor(dayLableHoverBackgroundColor);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());

				g2d.setPaint(new GradientPaint(1, 1, selectStartBgColor, 1, this.getHeight(), selectEndBgColor));
				g2d.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);

				g2d.setColor(dayLableHoverBorderColor);
				g2d.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);

			} else {
				g.setColor(dayLableBackgroundColor);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			}

			super.paint(g);
		}

	}

	public void addChangeListener(HChangeListener<HDatePanel> listener) {
		listenerSupport.addChangeListener(listener);
	}

	public void removeChangeListener(HChangeListener<HDatePanel> listener) {
		listenerSupport.removeChangeListener(listener);
	}
}
