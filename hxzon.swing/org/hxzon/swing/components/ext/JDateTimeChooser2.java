//JDateChooser -swingcode -gcode
//日期时间选择对话框，模仿Windows日期和时间属性对话框

package org.hxzon.swing.components.ext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import org.hxzon.newui.HSwingUtils.NewComboPopup;


public class JDateTimeChooser2 extends JPanel {
//    static{
//        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
//    }

    private DayGrid dayGrid = new DayGrid();//must before monthChooser
    private YearSpinner yearSpinner = new YearSpinner();
    private MonthChooser monthSpinner = new MonthChooser();
    private JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());

    private Calendar calendar = Calendar.getInstance();
    static String pattern="yyyy-MM-dd";
    static SimpleDateFormat format=new SimpleDateFormat(pattern);
    
//    private PropertyChangeListener forward;

    public JDateTimeChooser2(Calendar initialDate, boolean showTime) {
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), BorderFactory
                .createEtchedBorder()));
        this.setLayout(new GridLayout(1, 2, 5, 10));
        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);
        p1.setLayout(new BorderLayout(10, 10));
        JPanel p11 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        p11.setBackground(Color.white);
//        forward=new PropertyChangeListener(){
//
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                firePropertyChange(evt.getPropertyName(),evt.getOldValue(),evt.getNewValue());
//            }
//            
//        };
        
        yearSpinner.setPreferredSize(new Dimension(50, 25));
//        yearSpinner.addPropertyChangeListener("year",forward);
        p11.add(yearSpinner);
        monthSpinner.setPreferredSize(new Dimension(80, 25));
//        monthSpinner.addPropertyChangeListener("month",forward);
        p11.add(monthSpinner);
        
        p1.add(p11, BorderLayout.NORTH);
//        dayGrid.addPropertyChangeListener("day",forward);
        p1.add(dayGrid);
        this.add(p1);

        setCalendar(initialDate);
        if (showTime) {
            setPreferredSize(new Dimension(369, 200));
        } else {
            setPreferredSize(new Dimension(185, 180));
        }

    }

    public void setCalendar(Calendar initCalendar) {
        calendar = initCalendar;
        if (calendar == null)
            calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        yearSpinner.setYear(year);
        monthSpinner.setMonth(month);
        dayGrid.set(year, month);
        dayGrid.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        timeSpinner.setValue(calendar.getTime());
    }

    public Calendar getCalendar() {
        if (calendar == null)
            return null;
        int year = this.yearSpinner.getYear();
        int month = this.monthSpinner.getMonth();
        int day = dayGrid.getDay();
        Date date = (Date) timeSpinner.getValue();
        calendar.setTime(date);
        calendar.set(year, month, day);
        return calendar;
    }

    public static Calendar showDialog(Component c, Calendar initialDate, boolean showTime, boolean showButton) {
        Window window = c == null ? JOptionPane.getRootFrame() : SwingUtilities.windowForComponent(c);
        final JDialog dlg;
        if (window instanceof Frame) {
            dlg = new JDialog((Frame) window, true);
        } else {
            dlg = new JDialog((Dialog) window, true);
        }
        // dlg.setUndecorated(true);
        JDateTimeChooser2 dc = new JDateTimeChooser2(initialDate, showTime);
        PropertyChangeListener listener = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("day".endsWith(evt.getPropertyName())) {
                    dlg.dispose();
                }
            }

        };
        dc.addPropertyChangeListener("day", listener);
        dlg.add(dc);
        dlg.pack();
        dlg.setLocationRelativeTo(c);
        dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dlg.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

        });
        dlg.setVisible(true);

        return (dc == null) ? null : dc.getCalendar();
    }
    static MaskFormatter mf1=null;

    public static JPanel build(final JButton button){
        JPanel panel=new JPanel(new BorderLayout());
//        try {
//            mf1 = new MaskFormatter("####-##-##");
//            mf1.setPlaceholderCharacter('_');
//        } catch (ParseException e1) {
//            e1.printStackTrace();
//        }
          final JFormattedTextField field = new 
            JFormattedTextField(new DateFormatter(format));
//        button.setFocusable(false);
        final JDateTimeChooser2 chooser=new JDateTimeChooser2(null,false);
        final JPopupMenu popup = new JPopupMenu();
        PropertyChangeListener listener=new PropertyChangeListener(){

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if("day".equals(e.getPropertyName())){
                popup.setVisible(false);
//                    format.applyPattern("yyyy-MM-dd");
                    field.setText(format.format(chooser.getCalendar().getTime()));
                }
            }

            
        };
        chooser.addPropertyChangeListener(listener);
        AbstractAction action=new AbstractAction(button.getActionCommand()){//
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = button.getWidth() - (int) popup.getPreferredSize().getWidth();
                int y = button.getY() + button.getHeight();
                popup.show(button, x, y);
            }
        };
        
        popup.add(chooser);
//        popup.setLightWeightPopupEnabled(true);
        button.setAction(action);
        panel.add(field,BorderLayout.CENTER);
        panel.add(button,BorderLayout.EAST);
        return panel;
    }

    private class DayGrid extends JPanel {
        JLabel[] title = new JLabel[7];
        JButton[][] lbs = new JButton[6][7];

        int year = 1, month = 1, day = 1;

        public DayGrid() {
            super(new GridLayout(7, 7));
            this.setBackground(Color.white);
            Color blue = new Color(140, 150, 255);
            Color white = new Color(220, 220, 255);
            for (int j = 0; j < 7; j++) {
                this.add(title[j] = new JLabel((String) null));
                title[j].setOpaque(true);
                title[j].setBackground(blue);
                title[j].setForeground(white);
            }
            title[0].setText("日");
            title[1].setText("一");
            title[2].setText("二");
            title[3].setText("三");
            title[4].setText("四");
            title[5].setText("五");
            title[6].setText("六");
            AbstractAction action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton b = (JButton) e.getSource();
                    if (b.getText() != null) {
                        setDay(Integer.parseInt(b.getText()));
                        JDateTimeChooser2.this.firePropertyChange("day",0,day);
                        // SwingUtilities.windowForComponent(b).dispose();//fixme
                        // SwingUtilities.windowForComponent(b).setVisible(false);
                    }
                }
            };
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    this.add(lbs[i][j] = new JButton((String) null));
                    lbs[i][j].setOpaque(true);
                    lbs[i][j].setHorizontalAlignment(JLabel.CENTER);
                    lbs[i][j].setBackground(Color.white);
                    lbs[i][j].setBorder(null);
                    lbs[i][j].setAction(action);
                }
            }

            this.setBorder(BorderFactory.createLoweredBevelBorder());
        }

        public void setDay(int day) {
            if (day < 1)
                day = 1;
            int days = maxDay(year, month);
            if (day > days)
                day = days;
            this.day = day;
            int d = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (lbs[i][j].getText() != null) {
                        if (++d == day) {
                            lbs[i][j].setForeground(Color.red);
                            continue;
                        }
                    }
                    lbs[i][j].setForeground(Color.blue);
                }
            }
            repaint();
        }

        public int getDay() {
            return day;
        }

        public void set(int year, int month) {
            if (year < 1)
                year = 1;
            else if (year > 9999)
                year = 9999;
            if (month < 1)
                month = 1;
            else if (month > 12)
                month = 12;
            // 计算当月一号星期几 本可以用Calendar现成的功能，只是好玩，自己写了个算法
            int pastdays = (year - 1) * 365 + (year - 1) / 4 - (year - 1) / 100;
            pastdays += (month - 1) * 31;
            pastdays -= (month - 1) / 2;
            if (month >= 9 && month % 2 == 1)
                pastdays += 1;
            if (month > 2) {
                pastdays -= 2;
                if ((year % 4 == 0) && (year % 400 != 0))
                    pastdays += 1;
            }
            int dayInWeak = pastdays % 7 - 1;
            if (dayInWeak == -1)
                dayInWeak = 6;
            // ~
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    lbs[i][j].setText(null);
                }
            }
            int days = maxDay(year, month);
            for (int i = 1; i <= days; i++) {
                lbs[(dayInWeak + i - 1) / 7][(dayInWeak + i - 1) % 7].setText(String.valueOf(i));
            }
            this.year = year;
            this.month = month;
            setDay(day);
        }

        private int maxDay(int year, int month) {
            int days = (month % 2 == (month > 7 ? 0 : 1)) ? 31 : 30;
            if (month == 2)
                days = (year % 4 == 0 && year % 400 != 0) ? 29 : 28;
            return days;
        }

    }

    private class MonthChooser extends JPanel implements ItemListener, ChangeListener {

        private int month;

        private int oldSpinnerValue = 0;

        private JComboBox comboBox;

        public MonthChooser() {
            super(new BorderLayout());
            comboBox = new JComboBox(){
                public void updateUI() {
                    setUI(new BasicComboBoxUI(){
                        protected ComboPopup createPopup() {
                            return new NewComboPopup(comboBox);
                        }
                    });
                }    
            };
            comboBox.addItemListener(this);

            String[] monthNames = new DateFormatSymbols().getMonths();

            for (int i = 0; i < 12; i++) {
                comboBox.addItem(monthNames[i]);
            }

            JSpinner spinner = new JSpinner();
            spinner.setEditor(comboBox);
            comboBox.setBorder(null);
            spinner.addChangeListener(this);

            add(spinner, BorderLayout.CENTER);
        }

        public void stateChanged(ChangeEvent e) {
            SpinnerNumberModel model = (SpinnerNumberModel) ((JSpinner) e.getSource()).getModel();
            int value = model.getNumber().intValue();
            boolean increase = (value > oldSpinnerValue) ? true : false;
            oldSpinnerValue = value;

            int newMonth = getMonth();
            if (increase) {
                newMonth += 1;
                if (newMonth == 12) {
                    newMonth = 0;
                    yearSpinner.setYear(( yearSpinner.getYear()) + 1);
                }
            } else {
                newMonth -= 1;
                if (newMonth == -1) {
                    newMonth = 11;
                    yearSpinner.setYear(( yearSpinner.getYear()) - 1);
                }
            }
            setMonth(newMonth);
        }

        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int index = comboBox.getSelectedIndex();

//                if ((index >= 0) && (index != month)) {
                    setMonth(index);
//                }
            }
        }

        public void setMonth(int newMonth) {
//            if (newMonth < 0 || newMonth == Integer.MIN_VALUE) {
//                newMonth = 0;
//            } else if (newMonth > 11) {
//                newMonth = 11;
//            }
            int oldMonth = month;
            month = newMonth;
            comboBox.setSelectedIndex(month);

            dayGrid.set((Integer) yearSpinner.getValue(), getMonth());

            JDateTimeChooser2.this.firePropertyChange("month", oldMonth, month);
        }

        public int getMonth() {
            return month;
        }

    }
    
    private class YearSpinner extends JSpinner {
        public YearSpinner() {
            this(1, 9999);
        }

        public YearSpinner(int minimum, int maximum) {
            super(new SpinnerNumberModel(1, minimum, maximum, 1));
            setEditor(new JSpinner.NumberEditor(this, "0"));
            addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    dayGrid.set(yearSpinner.getYear(), monthSpinner.getMonth());
                    JDateTimeChooser2.this.firePropertyChange("year", 0, getYear());
                }

            });
        }
        
        public int getYear(){
            return (Integer)getValue();
        }
        
        public void setYear(int year){
            setValue(year);
        }

    }

//    public static void main(String[] args) throws Exception {
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        final JFrame f = new JFrame();
//        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setSize(500, 100);
//        f.setLocation(300, 300);
//        JButton bt = new JButton(new AbstractAction() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JButton bt = (JButton) e.getSource();
//                Calendar c = JDateTimeChooser.showDialog(bt, null, false, false);
//                if (c == null)
//                    JOptionPane.showMessageDialog(bt, "你没有选择时间");
//                else
//                    JOptionPane.showMessageDialog(bt, "你选择的时间是" + c.getTime());
//            }
//
//        });
//        f.add(bt);
//        f.setVisible(true);
//
//    }
    
    public static void main(String args[]) throws Exception{
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        final JFrame f=new JFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 100);
        f.setLocation(300, 300);
        JButton button=new JButton("取消");
//        JFormattedTextField field=new JFormattedTextField();
        f.add(build(button));
//        f.add(field,BorderLayout.CENTER);
//        f.add(button,BorderLayout.EAST);
        f.setVisible(true);
    }
}
// 转自 http://blog.csdn.net/wenlong342/archive/2008/05/09/2420732.aspx
