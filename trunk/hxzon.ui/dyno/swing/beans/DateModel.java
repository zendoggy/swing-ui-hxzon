/*
 * DateModel.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package dyno.swing.beans;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;

/**
 *
 * @author William Chen
 */
public class DateModel {
    private int year;
    private int month;
    private int date;
    
    private int hovered_day;
    private boolean pressed;

    private static Color BCOLOR=new Color(122, 150, 223);
    
    public DateModel(){
        this(new Date());
    }

    public DateModel(Date d){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        setYear(calendar.get(Calendar.YEAR));
        setMonth(calendar.get(Calendar.MONTH)+1);
        setDate(calendar.get(Calendar.DAY_OF_MONTH));    
    }
    
    public DateModel(int y, int m, int d){
        setYear(y);
        setMonth(m);
        setDate(d);
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    int getHovered_day() {
        return hovered_day;
    }

    void setHovered_day(int hovered_day) {
        this.hovered_day = hovered_day;
    }

    boolean isPressed() {
        return pressed;
    }

    void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
    private String getWeekName(int i) {
        switch(i){
            case 0:return "Sun";
            case 1:return "Mon";
            case 2:return "Tue";
            case 3:return "Wed";
            case 4:return "Thu";
            case 5:return "Fri";
            case 6:return "Sat";
        }
        return "Sun";
    }
    private boolean isToday(Calendar cal1){
        return equivalent(cal1, Calendar.getInstance());
    }
    private static boolean equivalent(Calendar cal1, Calendar cal2){
        return cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR) 
        && cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH) 
        && cal1.get(Calendar.DATE)==cal2.get(Calendar.DATE);
    }     
    public boolean equals(Object o){
        if(o==null)
            return false;
        if(!(o instanceof Calendar))
            return false;
        DateModel another=(DateModel)o;
        return another.getYear() == getYear() 
        && another.getMonth() == getMonth() 
        && another.getDate() == getDate();
    }
    void paint(Graphics g, JComponent c) {
        int w=c.getWidth();
        int h=c.getHeight();
        
        g.setColor(c.getBackground());
        g.fillRect(0, 0, w, h);
        
        g.setColor(c.getForeground());
        int x=CWIDTH;
        FontMetrics fm=g.getFontMetrics();
        int y=(RHEIGHT-fm.getHeight())/2+fm.getAscent();
        for(int i=0;i<=6;i++){
            String wdname=getWeekName(i);
            x=(i+1)*CWIDTH+(CWIDTH-fm.stringWidth(wdname))/2;
            g.drawString(wdname, x, y);
        }
        
        g.setColor(BCOLOR);
        g.drawLine(CWIDTH, RHEIGHT, w, RHEIGHT);
        g.drawLine(CWIDTH, RHEIGHT, CWIDTH, h);
        
        g.setColor(c.getForeground());
        Calendar calendar=Calendar.getInstance();
        calendar.set(getYear(), getMonth()-1, 1);
        int min=calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int max=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(getYear(), getMonth()-1, min);
        int start_week=calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.set(getYear(), getMonth()-1, max);
        int end_week=calendar.get(Calendar.WEEK_OF_YEAR);
        for(int i=0;i<=end_week-start_week;i++){
            int current_week=start_week+i;
            x=(CWIDTH-fm.stringWidth(""+current_week))/2;
            y=(i+1)*RHEIGHT+(RHEIGHT-fm.getHeight())/2+fm.getAscent();
            g.drawString(""+current_week, x, y);
        }
        
        for(int day=min;day<=max;day++){
            calendar=Calendar.getInstance();
            calendar.set(getYear(), getMonth()-1, day);
            int dow=calendar.get(Calendar.DAY_OF_WEEK);
            int woy=calendar.get(Calendar.WEEK_OF_YEAR);
            woy=woy-start_week+1;
            x=dow*CWIDTH;
            y=woy*RHEIGHT;
            if(isToday(calendar)){
                if(day==getHovered_day()){
                    if(isPressed())
                        drawPressedToday(g, x, y);
                    else
                        drawHoveredToday(g, x, y);
                }else{
                    drawToday(g, x, y);
                }
            }else{
                if(day==getHovered_day()){
                    if(isPressed())
                        drawPressedDay(g, x, y);
                    else
                        drawHoveredDay(g, x, y);
                }
            }
            
            g.setColor(c.getForeground());
            String d=""+day;
            x+=(CWIDTH-fm.stringWidth(d))/2;
            y+=(RHEIGHT-fm.getHeight())/2+fm.getAscent();
            g.drawString(d, x, y);
        }
    }    
    private static Color HOVERED_DAY_BORDER=new Color(0,0,128);
    private static Color HOVERED_DAY_LIHGT=new Color(255,244,204);
    private static Color HOVERED_DAY_DARK=new Color(255,212,153);
    
    private static Color PRESSED_DAY_BORDER=new Color(0,0,128);
    private static Color PRESSED_DAY_LIHGT=new Color(254,145,78);
    private static Color PRESSED_DAY_DARK=new Color(254,201,133);
    
    private static Color TODAY_BORDER=new Color(255,0,0);
    private static Color TODAY_LIGHT=new Color(255,213,140);
    private static Color TODAY_DARK=new Color(255,178,92);
    
    private static Color HOVERED_TODAY_BORDER=new Color(255,0,0);
    private static Color HOVERED_TODAY_LIGHT=new Color(254,145,78);
    private static Color HOVERED_TODAY_DARK=new Color(254,201,133);
    
    private static Color PRESSED_TODAY_BORDER=new Color(255,0,0);
    private static Color PRESSED_TODAY_LIGHT=new Color(254,145,78);
    private static Color PRESSED_TODAY_DARK=new Color(254,201,133);
    
    private void drawHoveredToday(Graphics g, int x, int y) {
        drawBox(g, x, y, HOVERED_TODAY_BORDER, HOVERED_TODAY_LIGHT, HOVERED_TODAY_DARK);
    }
    
    private void drawToday(Graphics g, int x, int y) {
        drawBox(g, x, y, TODAY_BORDER, TODAY_LIGHT, TODAY_DARK);
    }
    
    private void drawHoveredDay(Graphics g, int x, int y) {
        drawBox(g, x, y, HOVERED_DAY_BORDER, HOVERED_DAY_LIHGT, HOVERED_DAY_DARK);
    }
    
    private void drawBox(Graphics g, int x, int y, Color border, Color light, Color dark){
        Graphics2D g2d=(Graphics2D)g;
        GradientPaint paint=new GradientPaint(x+1, y+1, light, x+1, y+RHEIGHT-3, dark);
        g2d.setPaint(paint);
        g2d.fillRect(x+1, y+1, CWIDTH-2, RHEIGHT-2);
        g.setColor(border);
        g.drawRect(x+1, y+1, CWIDTH-2, RHEIGHT-2);
    }
    
    private void drawPressedToday(Graphics g, int x, int y) {
        drawBox(g, x, y, PRESSED_TODAY_BORDER, PRESSED_TODAY_LIGHT, PRESSED_TODAY_DARK);
    }
    
    private void drawPressedDay(Graphics g, int x, int y) {
        drawBox(g, x, y, PRESSED_DAY_BORDER, PRESSED_DAY_LIHGT, PRESSED_DAY_DARK);
    }
    static final int CWIDTH=36;
    static final int RHEIGHT=20;    
}
