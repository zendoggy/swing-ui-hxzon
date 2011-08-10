/*
 * DatePopup.java
 *
 */

package dyno.swing.beans;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author  William Chen
 */
public class DatePopup extends JPopupMenu {
    private static final Month[] MONTHS = {new Month(1), new Month(2), new Month(3), new Month(4), new Month(5), new Month(6), new Month(7), new Month(8), new Month(9), new Month(10), new Month(11), new Month(12)};
    private static final String[] names = {"January", "Februray", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    static {
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    }

    static int getMonth(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.MONTH) + 1;
    }

    static int getYear(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.YEAR);
    }

    static int getDate(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DATE);
    }

    static class Month {

        private int month;

        public Month(Date d) {
            month = getMonth(d);
        }

        public Month(int m) {
            month = m;
        }

        public int month() {
            return month;
        }

        public String toString() {
            return names[month - 1];
        }

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Month)) {
                return false;
            }
            Month m = (Month) o;
            return m.month == month;
        }
    }

    private JButton btnLeft;
    private JButton btnRight;
    private JButton btnClear;
    private JButton btnToday;
    private MonthCombo cbMonth;
    private JSpinner spYear;
    private DatePanel datePanel;

    private Date date;

    private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

    public DatePopup() {
        initComponents();
        DefaultComboBoxModel model = new DefaultComboBoxModel(MONTHS);
        cbMonth.setModel(model);
        Date today = new Date();
        datePanel.setCalendarDate(today);
        cbMonth.setSelectedItem(new Month(today));
        spYear.setValue(new Integer(getYear(today)));
    }

    public void addActionListener(ActionListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void removeActionListener(ActionListener l) {
        if (listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    protected void fireActionPerformed(ActionEvent e) {
        for (ActionListener l : listeners) {
            l.actionPerformed(e);
        }
    }

    public void setAnimationEffect(int effect) {
        datePanel.setEffect(effect);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date d) {
        date = d;
        DateModel m = new DateModel(d);
        datePanel.setModel(m);
        cbMonth.setSelectedItem(new Month(d));
        spYear.setValue(new Integer(m.getYear()));
    }

    private MonthCombo getCbMonth() {
        if (cbMonth == null) {
            cbMonth = new MonthCombo();
            cbMonth.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    cbMonthActionPerformed(evt);
                }
            });
        }
        return cbMonth;
    }

    private JSpinner getSpYear() {
        if (spYear == null) {
            spYear = new JSpinner();
            spYear.addChangeListener(new ChangeListener() {

                public void stateChanged(ChangeEvent evt) {
                    spYearStateChanged(evt);
                }
            });
        }
        return spYear;
    }

    private JButton getBtnLeft() {
        if (btnLeft == null) {
            btnLeft = new JButton();
            btnLeft.setFocusPainted(false);
            btnLeft.setIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/left_arrow.png")));
            btnLeft.setPressedIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/pressed_left_arrow.png")));
            btnLeft.setRolloverIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/hovered_left_arrow.png")));
            btnLeft.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    btnLeftActionPerformed(evt);
                }
            });
        }
        return btnLeft;
    }

    private JButton getBtnRight() {
        if (btnRight == null) {
            btnRight = new JButton();
            btnRight.setFocusPainted(false);
            btnRight.setIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/right_arrow.png")));
            btnRight.setPressedIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/pressed_right_arrow.png")));
            btnRight.setRolloverIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/hovered_right_arrow.png")));
            btnRight.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    btnRightActionPerformed(evt);
                }
            });
        }
        return btnRight;
    }

    private JButton getBtnClear() {
        if (btnClear == null) {
            btnClear = new JButton();
            btnClear.setText("Clear");
            btnClear.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    btnClearActionPerformed(evt);
                }
            });
        }
        return btnClear;
    }

    private JButton getBtnToday() {
        if (btnToday == null) {
            btnToday = new JButton();
            btnToday.setText("Today");
            btnToday.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    btnTodayActionPerformed(evt);
                }
            });
        }
        return btnToday;
    }

    private DatePanel getDatePanel() {
        if (datePanel == null) {
            datePanel = new DatePanel();
            datePanel.setBackground(Color.white);
            datePanel.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    datePanelActionPerformed(evt);
                }
            });
        }
        return datePanel;
    }

    private void initComponents() {
        setOpaque(true);
        setLayout(new DatePopupLayout());
        setBackground(new Color(122, 150, 223));
        setBorder(BorderFactory.createLineBorder(new Color(122, 150, 223)));

        add(getCbMonth());
        add(getSpYear());
        add(getBtnLeft());
        add(getBtnRight());
        add(getBtnClear());
        add(getBtnToday());
        add(getDatePanel());
    }

    private void datePanelActionPerformed(ActionEvent evt) {
        DateModel m = datePanel.getModel();
        Calendar calendar=Calendar.getInstance();
        calendar.set(m.getYear(), m.getMonth() - 1, m.getDate());
        date=calendar.getTime();
        fireActionPerformed(new ActionEvent(this, 0, "choose"));
    }

    private void btnClearActionPerformed(ActionEvent evt) {
        date = null;
        fireActionPerformed(new ActionEvent(this, 0, "choose"));
    }

    private void btnTodayActionPerformed(ActionEvent evt) {
        date = new Date();
        DateModel m = new DateModel(date);
        datePanel.setModel(m);
        cbMonth.setSelectedItem(new Month(m.getMonth()));
        spYear.setValue(new Integer(m.getYear()));
        fireActionPerformed(new ActionEvent(this, 0, "choose"));
    }

    private void btnLeftActionPerformed(ActionEvent evt) {
        DateModel model = datePanel.getModel();
        int m = model.getMonth();
        int y = model.getYear();
        if (m == 1) {
            m = 12;
            y--;
        } else {
            m--;
        }
        DateModel newModel = new DateModel(y, m, model.getDate());
        datePanel.setModel(newModel);
        spYear.setValue(new Integer(y));
        cbMonth.setSelectedItem(new Month(m));
    }

    private void btnRightActionPerformed(ActionEvent evt) {
        DateModel model = datePanel.getModel();
        int m = model.getMonth();
        int y = model.getYear();
        if (m == 12) {
            m = 1;
            y++;
        } else {
            m++;
        }
        DateModel newModel = new DateModel(y, m, model.getDate());
        datePanel.setModel(newModel);
        spYear.setValue(new Integer(y));
        cbMonth.setSelectedItem(new Month(m));
    }

    private void spYearStateChanged(ChangeEvent evt) {
        DateModel model = datePanel.getModel();
        DateModel newModel = new DateModel(((Integer) spYear.getValue()).intValue(), model.getMonth(), model.getDate());
        datePanel.setModel(newModel);
    }

    private void cbMonthActionPerformed(ActionEvent evt) {
        Object o = cbMonth.getSelectedItem();
        if (o == null) {
            return;
        }
        Month m = (Month) o;
        DateModel model = datePanel.getModel();
        DateModel newModel = new DateModel(model.getYear(), m.month(), model.getDate());
        datePanel.setModel(newModel);
    }


    
    class DatePopupLayout implements LayoutManager {

        public void addLayoutComponent(String name, Component comp) {
        }

        public void removeLayoutComponent(Component comp) {
        }

        public Dimension preferredLayoutSize(Container parent) {
            return new Dimension(298,240);
        }

        public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(0,0);
        }

        public void layoutContainer(Container parent) {
            int w=parent.getWidth();
            int h=parent.getHeight();
            btnLeft.setBounds(20, 13, 18, 15);
            btnRight.setBounds(259,13, 18, 15);
            cbMonth.setBounds(64, 10, 80, 22);
            spYear.setBounds(154, 10, 80, 22);
            datePanel.setBounds(1, 42, w-2, 146);
            btnToday.setBounds(55, h-37, 70, 22);
            btnClear.setBounds(w-120, h-37, 70, 22);
        }
    }    
}
