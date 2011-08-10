package org.hxzon.goodcode;

//JTimeField author hxzon
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JTimeField2 extends JTextField {
    private int fieldType = SECOND;
    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private int milli = 0;
    private int hourMax = 23;
    private int minuteMax = 59;
    private int secondMax = 59;
    private int milliMax = 999;
    private int hourMin = 0;
    private int minuteMin = 0;
    private int secondMin = 0;
    private int milliMin = 0;
    private Calendar calendar = Calendar.getInstance();
    public static final int HOUR = 1;
    public static final int MINUTE = 2;
    public static final int SECOND = 3;
    public static final int MILLISECOND = 4;

    JTimeField2() {
        addListener();
        setColumns(fieldType * 3);
    }

    JTimeField2(int fieldType) {
        addListener();
        setType(fieldType);
    }

    JTimeField2(int hour, int minute, int second) {
        addListener();
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        setText(getTimeString());
        setColumns(fieldType * 3);
    }

    JTimeField2(int hour, int minute, int second, int millisecond) {
        addListener();
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.milli = millisecond;
        this.fieldType = MILLISECOND;
        setText(getTimeString());
        setColumns(fieldType * 3);
    }

    JTimeField2(Date date) {
        addListener();
        initDate(date);
        setText(getTimeString());
        setColumns(fieldType * 3);
    }

    JTimeField2(Date date, int fieldType) {
        addListener();
        initDate(date);
        setType(fieldType);
        setText(getTimeString());
    }
    
    public void setDate(Date date){
        initDate(date);
        setText(getTimeString());
    }

    public void setMin(int hmin, int mmin, int smin, int msmin) {

        if (hmin >= 0 && hmin <= 23)
            hourMin = hmin;

        if (mmin >= 0 && mmin <= 59)
            minuteMin = mmin;

        if (smin >= 0 && smin <= 59)
            secondMin = smin;

        if (msmin >= 0 && msmin <= 999)
            milliMin = msmin;
        valid();
    }

    public void setMax(int hmax, int mmax, int smax, int msmax) {
        if (hmax >= 0 && hmax <= 23)
            hourMax = hmax;
        if (mmax >= 0 && mmax <= 59)
            minuteMax = mmax;
        if (smax >= 0 && smax <= 59)
            secondMax = smax;
        if (msmax >= 0 && msmax <= 999)
            milliMax = msmax;
        valid();

    }

    public void setSecondDelta(int hour, int minute, int second, int secondUp, int secondDown) {
        if (secondUp < 0)
            return;
        int hourAdd = secondUp / 3600;
        int minuteAdd = (secondUp % 3600) / 60;
        int secondAdd = secondUp % 60;
        setMax(hour + hourAdd, minute + minuteAdd, second + secondAdd, -1);

        if (secondDown < 0)
            return;
        int hourMinus = secondDown / 3600;
        int minuteMinus = (secondDown % 3600) / 60;
        int secondMinus = secondDown % 60;
        setMin(hour - hourMinus, minute - minuteMinus, second - secondMinus, -1);
    }

    public void setMsDelta(int hour, int minute, int second, int milli, int milliUp, int milliDown) {
        if (milliUp < 0)
            return;
        int secondUp = milliUp / 1000;
        int hourAdd = secondUp / 3600;
        int minuteAdd = (secondUp % 3600) / 60;
        int secondAdd = secondUp % 60;
        milliUp = milliUp % 1000;
        setMax(hour + hourAdd, second + secondAdd, minute + minuteAdd, milli + milliUp);

        if (milliDown < 0)
            return;
        int secondDown = milliDown / 1000;
        int hourMinus = secondDown / 3600;
        int minuteMinus = (secondDown % 3600) / 60;
        int secondMinus = secondDown % 60;
        milliDown = milliDown % 1000;
        setMin(hour - hourMinus, second - secondMinus, minute - minuteMinus, milli - milliDown);
    }

    public void setSecondDelta(JTimeField2 link, int secondUp, int secondDown) {
        setSecondDelta(link.getHour(), link.getMinute(), link.getSecond(), secondUp, secondDown);
    }

    public void setMsDelta(JTimeField2 link, int msUp, int msDown) {
        setMsDelta(link.getHour(), link.getMinute(), link.getSecond(), link.getMilliSecond(), msUp, msDown);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMilliSecond() {
        return milli;
    }

    public Date getTime() {
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        set();
        return calendar.getTime();
    }

    public Date addDate(Date date) {
        calendar.setTime(date);
        set();
        return calendar.getTime();
    }

    public Date addDate(int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        set();
        return calendar.getTime();
    }

    public int getType() {
        return fieldType;
    }

    private void addListener() {
        this.addKeyListener(new KeyListener() {
            int state;
            char input;
            String orig;
            int pos;
            String left;
            String right;
            boolean isColon=false;
            @Override
            public void keyPressed(KeyEvent e) {
                input=e.getKeyChar();
                orig=getText();
                pos =getCaretPosition();
                left=orig.substring(0,pos);
                right=orig.substring(pos,orig.length());
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
 
                int fieldCount = orig.replaceAll("_", "").replace('.', ':').split(":").length;
                if (input == KeyEvent.VK_ENTER || input == KeyEvent.VK_SPACE) {
                    valid();
                    return ;
                }
                if(input == KeyEvent.VK_BACK_SPACE ){
                    if(pos==0){
                        return;
                    }
                    if(left.endsWith(":")) {
                        setText(orig);
                    }else{
                        left=orig.substring(0,pos-1);
                        setText(left + "_" + right);
                    }
                    setCaretPosition(pos-1);
                    return ;
                }
                if( input == KeyEvent.VK_DELETE){
                    if(pos==fieldType*3-1){
                        return;
                    }
                    if(right.startsWith(":")) {
                        setText(orig);
                    }else{
                        right=orig.substring(pos+1,orig.length());
                        setText(left + "_" + right);
                    }
                    setCaretPosition(pos+1);
                    return ;
                }
                
                if (input == KeyEvent.CHAR_UNDEFINED ) {
                    return;
                }
                if(input >='0' && input<='9'){
                    if(pos==fieldType*3-1){
                        e.consume();
                        return;
                    }
                    if (right.startsWith(":")) {
                        setText(orig);
                        e.consume();
                        setCaretPosition(pos+1);
                    } else {
                        right = orig.substring(pos + 1, orig.length());
                        setText(left + right);
                        setCaretPosition(pos);
                    }
                    return ;
                }
                if (input < '0' || input > '9') {
                    if (orig.endsWith(":") || fieldCount >= fieldType || orig.length() == 0) {
                    } else {
                        String left = orig.substring(0, pos);
                        String right = orig.substring(pos+1, orig.length());
                        setText(left + ":" + right);
                        setCaretPosition(pos+1);
                    }
                    e.consume();
                }
            }
        });

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                valid();
            }
        });
    }

    private void setType(int fieldType) {
        if (fieldType < 1 || fieldType > 4) {
            throw new IllegalArgumentException("fieldType must be one of HOUR , MINUTE , SECOND or MILLISECOND");
        }
        this.fieldType = fieldType;
        setColumns(fieldType * 3);
    }

    private void initDate(Date date) {
        calendar.setTime(date);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        milli = calendar.get(Calendar.MILLISECOND);
    }

    private String getTimeString() {
        String format = "";
        if (hour < 10)
            format = "0";
        format += hour;
        if (fieldType == HOUR)
            return format;
        format += ":";
        if (minute < 10)
            format += "0";
        format += minute;
        if (fieldType == MINUTE)
            return format;
        format += ":";
        if (second < 10)
            format += "0";
        format += second;
        if (fieldType == SECOND)
            return format;
        return format += "." + milli;
    }

    private void valid() {
        hour = 0;
        minute = 0;
        second = 0;
        milli = 0;
        String[] tmp = getText().replaceAll("_", "").replace('.', ':').split(":");
        hour = Integer.parseInt(tmp[0]);
        if (hour > hourMax) {
            hour = hourMax;
        }
        if (hour < hourMin) {
            hour = hourMin;
        }
        if (tmp.length > 1) {
            minute = Integer.parseInt(tmp[1]);
            if (minute > 59)
                minute = 59;
            if (hour == hourMax && minute > minuteMax) {
                minute = minuteMax;
            }
            if (hour == hourMin && minute < minuteMin) {
                minute = minuteMin;
            }
        }
        if (tmp.length > 2) {
            second = Integer.parseInt(tmp[2]);
            if (second > 59)
                second = 59;
            if (minute == minuteMax && second > secondMax) {
                second = secondMax;
            }
            if (minute == minuteMin && second < secondMin) {
                second = secondMin;
            }
        }
        if (tmp.length > 3) {
            if (tmp[3].length() > 3) {
                tmp[3] = tmp[3].substring(0, 3);
            }
            milli = Integer.parseInt(tmp[3]);
            if (milli > 999)
                milli = 999;
            if (milli == milliMax && milli > milliMax) {
                milli = milliMax;
            }
            if (second == secondMin && milli < milliMin) {
                milli = milliMin;
            }
        }
        setText(getTimeString());
    }

    private void set() {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.MILLISECOND, milli);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLayout(new FlowLayout());
        final JTimeField2 timefield = new JTimeField2(new Date());
        // timefield.setMax(17, 40, 0, -1);
        // timefield.setMin(2, 25, -1, -1);
        timefield.setSecondDelta(17, 30, 0, 3600, 1800);
        f.add(timefield);
        JButton button = new JButton(new AbstractAction("显示时间") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("timestring:" + timefield.getText());
                System.out.println("date:" + timefield.getTime());
                System.out.println("date:" + timefield.getTime().getTime());
                System.out.println("date:" + timefield.addDate(1985, 2, 3));
            }

        });
        f.add(button);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 100);
        f.setLocation(300, 300);
        f.setVisible(true);
    }
}