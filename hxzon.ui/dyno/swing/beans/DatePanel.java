/*
 * DatePane.java
 *
 * Created on 2007-6-19, 16:36:13
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dyno.swing.beans;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author William Chen
 */
public class DatePanel extends JComponent implements MouseListener, MouseMotionListener{
    public static final int NO_EFFECT=0;
    public static final int SLIDING_EFFECT=1;
    public static final int TRANSLUCENT_EFFECT=2;
    
    private DateModel model;
    private int effect=NO_EFFECT;
    private Animator animator;
    
    private ArrayList<ActionListener> listeners=new ArrayList<ActionListener>();
    
    public DatePanel() {
        setCalendarDate(new Date());
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    private static int ANIMATION_INTERVAL=10;
    private static int ANIMATION_FRAMES=10;
    class Animator implements ActionListener{
        
        private DateModel model1;
        private DateModel model2;
        private int effect;
        
        private int frameIndex;
        private Timer timer;
        private boolean vertical;
        private boolean l2r;
        
        private void paintVertL2R(Graphics g) {
            float ratio=getRatio();
            int h=DatePanel.this.getHeight();
            int y=(int)(h*ratio);
            Graphics copyg=g.create();
            copyg.translate(0, y-h);
            model2.paint(copyg, DatePanel.this);
            copyg.dispose();
            copyg=g.create();
            copyg.translate(0, y);
            model1.paint(copyg, DatePanel.this);
            copyg.dispose();
        }
        
        private void paintVertR2L(Graphics g) {
            float ratio=getRatio();
            int h=DatePanel.this.getHeight();
            int y=(int)(h*ratio);
            Graphics copyg=g.create();
            copyg.translate(0, -y);
            model1.paint(copyg, DatePanel.this);
            copyg.dispose();
            copyg=g.create();
            copyg.translate(0, h-y);
            model1.paint(copyg, DatePanel.this);
            copyg.dispose();
        }
        
        private void paintHorzL2R(Graphics g) {
            float ratio=getRatio();
            int w=DatePanel.this.getWidth();
            int x=(int)(w*ratio);
            Graphics copyg=g.create();
            copyg.translate(x-w, 0);
            model2.paint(copyg, DatePanel.this);
            copyg.dispose();
            copyg=g.create();
            copyg.translate(x, 0);
            model1.paint(copyg, DatePanel.this);
            copyg.dispose();
        }
        
        private void paintHorzR2L(Graphics g) {
            float ratio=getRatio();
            int w=DatePanel.this.getWidth();
            int x=(int)(w*ratio);
            Graphics copyg=g.create();
            copyg.translate(-x, 0);
            model1.paint(copyg, DatePanel.this);
            copyg.dispose();
            copyg=g.create();
            copyg.translate(w-x, 0);
            model1.paint(copyg, DatePanel.this);
            copyg.dispose();
        }
        
        private void paintVerticalSliding(Graphics g) {
            if(l2r)
                paintVertL2R(g);
            else
                paintVertR2L(g);
        }
        
        private void paintHorizontalSliding(Graphics g) {
            if(l2r)
                paintHorzL2R(g);
            else
                paintHorzR2L(g);
        }
        
        private void paintSliding(Graphics g) {
            if(vertical)
                paintVerticalSliding(g);
            else
                paintHorizontalSliding(g);
        }
        private float getRatio(){
            return (float)frameIndex/(float)ANIMATION_FRAMES;
        }
        private void paintTranslucent(Graphics g) {
            model1.paint(g, DatePanel.this);
            Graphics2D g2d=(Graphics2D)g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getRatio()));
            model2.paint(g, DatePanel.this);
        }
        
        public Animator(DateModel m1, DateModel m2, int effect){
            model1=m1;
            model2=m2;
            this.effect=effect;
            frameIndex=0;
            timer=new Timer(ANIMATION_INTERVAL, this);
            vertical=m1.getYear()!=m2.getYear();
            if(vertical)
                l2r=m2.getYear()<m1.getYear();
            else
                l2r=m2.getMonth()<m1.getMonth();
        }
        
        public void start(){
            timer.start();
        }
        
        public void stop(){
            timer.stop();
        }
        
        public void paint(Graphics g){
            switch(effect){
                case SLIDING_EFFECT:
                    paintSliding(g);
                    break;
                case TRANSLUCENT_EFFECT:
                    paintTranslucent(g);
                    break;
            }
        }
        public void actionPerformed(ActionEvent e) {
            frameIndex++;
            if(frameIndex==ANIMATION_FRAMES)
                stop();
            repaint();
        }
        public boolean isRunning(){
            return timer.isRunning();
        }
    }
    public void setModel(DateModel m){
        if(model==null || model.getYear()==m.getYear()
        && model.getMonth()==m.getMonth()){
            model=m;
            repaint();
        }else if(effect!=NO_EFFECT){
            if(isAnimating())
                animator.stop();
            animator=new Animator(model, m, effect);
            animator.start();
            model=m;
        }else{
            model=m;
            repaint();
        }
    }
    boolean isAnimating(){
        return animator!=null && animator.isRunning();
    }
    public DateModel getModel(){
        return model;
    }
    public void setCalendarDate(Date d) {
        setModel(new DateModel(d));
    }
    @Override
    protected void paintComponent(Graphics g) {
        if(isAnimating())
            animator.paint(g);
        else
            model.paint(g, this);
    }
    public Dimension getPreferredSize(){
        return new Dimension(8*DateModel.CWIDTH, 7*DateModel.RHEIGHT);
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
        model.setPressed(true);
        repaint();
    }
    public void mouseReleased(MouseEvent e) {
        model.setPressed(false);
        repaint();
        int d=getDayAt(e);
        if(d!=0){
            model.setDate(d);
            fireActionPerformed(new ActionEvent(this, 0, "choose"));
        }
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
        model.setHovered_day(0);
        repaint();
    }
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
    public void mouseMoved(MouseEvent e) {
        model.setHovered_day(getDayAt(e));
        repaint();
    }
    private int getDayAt(MouseEvent e){
        return getDayAt(e.getX()/DateModel.CWIDTH, e.getY()/DateModel.RHEIGHT);
    }
    private int getDayAt(int x, int y){
        Calendar calendar=Calendar.getInstance();
        calendar.set(model.getYear(), model.getMonth()-1, 1);
        int min=calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int max=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(model.getYear(), model.getMonth()-1, min);
        int start_week=calendar.get(Calendar.WEEK_OF_YEAR);
        int start_day=calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(model.getYear(), model.getMonth()-1, max);
        int end_week=calendar.get(Calendar.WEEK_OF_YEAR);
        int end_day=calendar.get(Calendar.DAY_OF_WEEK);
        int end_index=end_week-start_week+2;
        if(x<1 || x>7 || y<1 || y>end_index || y==1 && (x<start_day-1 || x>7) || y==end_index && (x<1 || x>end_day))
            return 0;
        return (y-1)*7+x-start_day+1;
    }
    public void addActionListener(ActionListener l){
        if(!listeners.contains(l))
            listeners.add(l);
    }
    public void removeActionListener(ActionListener l){
        if(listeners.contains(l))
            listeners.remove(l);
    }
    protected void fireActionPerformed(ActionEvent e){
        for(ActionListener l:listeners)
            l.actionPerformed(e);
    }
    
    public int getEffect() {
        return effect;
    }
    
    public void setEffect(int effect) {
        this.effect = effect;
    }
    
}