/*
 * JDatePicker.java
 *
 * Created on June 20, 2007, 4:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package dyno.swing.beans;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author William Chen
 */
public class DatePicker extends JComponent{
    private static ImageIcon icon=new ImageIcon(DatePicker.class.getResource("/dyno/swing/beans/drop_button.png"));
    private static ImageIcon pressedIcon=new ImageIcon(DatePicker.class.getResource("/dyno/swing/beans/pressed_drop_button.png"));
    private static ImageIcon hoveredIcon=new ImageIcon(DatePicker.class.getResource("/dyno/swing/beans/hovered_drop_button.png"));
    private static int BUTTON_WIDTH=16;
    
    private DatePopup popup;
    private JLabel lblDropdown;
    private JTextField txtEditor;
    private Date date;
    
    private long cancel_time;
    
    private Handler mouseHandler=new Handler();
    
    public DatePicker() {
        setLayout(new ComboLayout());
        setBorder(BorderFactory.createLineBorder(new Color(127, 157, 185)));
        
        txtEditor = new JTextField();
        txtEditor.setBackground(Color.WHITE);
        txtEditor.setEditable(false);
        txtEditor.setBorder(null);
        
        lblDropdown = new JLabel();
        lblDropdown.setIcon(icon);
        
        popup=new DatePopup();
        popup.setInvoker(this);
        
        add(txtEditor);
        add(lblDropdown);
        
        txtEditor.addMouseListener(mouseHandler);
        lblDropdown.addMouseListener(mouseHandler);
        popup.addPopupMenuListener(new PopupListener());
        popup.addActionListener(new PopupAction());
        addMouseListener(mouseHandler);
    }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,getWidth(),getHeight());
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date=date;
        txtEditor.setText(date==null?"":getDateText(date));
    }
    
    String getDateText(Date d){
        Calendar c=Calendar.getInstance();
        c.setTime(d);
        return ""+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DATE);
    }
    
    private void togglePopup() {
        if(popup.isVisible())
            popup.setVisible(false);
        else{
            if(date!=null)
                popup.setDate(date);
            popup.show(this, 0, getHeight());
        }
    }
    public void setAnimationEffect(int effect){
        popup.setAnimationEffect(effect);
    }
    class ComboLayout implements LayoutManager{
        
        public void addLayoutComponent(String name, Component comp) {
        }
        
        public void removeLayoutComponent(Component comp) {
        }
        
        public Dimension preferredLayoutSize(Container parent) {
            Dimension txtDim=txtEditor.getPreferredSize();
            Dimension btnDim=lblDropdown.getPreferredSize();
            int w=(txtDim.width+btnDim.width);
            int h=txtDim.height;
            if(h<btnDim.height)
                h=btnDim.height;
            Insets insets=parent.getInsets();
            if(insets!=null){
                w=insets.left+insets.right;
                h=insets.top+insets.bottom;
            }
            return new Dimension(w, h);
        }
        
        public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(0,0);
        }
        
        public void layoutContainer(Container parent) {
            int w=parent.getWidth();
            int h=parent.getHeight();
            Insets insets=parent.getInsets();
            h=h-insets.top-insets.bottom;
            
            lblDropdown.setBounds(w-insets.right-BUTTON_WIDTH, insets.top+1, BUTTON_WIDTH, h-2);
            txtEditor.setBounds(insets.left, insets.top+1, w-BUTTON_WIDTH-insets.left-insets.right, h-2);
        }
        
    }
    
    private class Handler implements MouseListener {
        
        private boolean iamsrc;
        
        public void mouseClicked(MouseEvent e) {
        }
        
        public void mousePressed(MouseEvent e) {
            long time = System.currentTimeMillis() - cancel_time;
            if (time < 200)
                iamsrc = true; else
                    iamsrc = false;
            lblDropdown.setIcon(pressedIcon);
        }
        
        public void mouseReleased(MouseEvent e) {
            lblDropdown.setIcon(icon);
            if (!iamsrc)
                togglePopup();
        }
        
        public void mouseEntered(MouseEvent e) {
            lblDropdown.setIcon(hoveredIcon);
        }
        
        public void mouseExited(MouseEvent e) {
            lblDropdown.setIcon(icon);
        }
    }
    
    private class PopupAction implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            setDate(popup.getDate());
            togglePopup();
        }
    }
    
    private class PopupListener implements PopupMenuListener {
        
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        }
        
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }
        
        public void popupMenuCanceled(PopupMenuEvent e) {
            cancel_time = System.currentTimeMillis();
        }
    }
}
