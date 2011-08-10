/*
 * MonthComboUI.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package dyno.swing.beans;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;

/**
 *
 * @author William Chen
 */
public class MonthComboUI extends BasicComboBoxUI{
    private static int ARROW_HEIGHT=18;
    private static int ARROW_WIDTH=15;
    
    protected ComboPopup createPopup() {
        return new MonthComboPopup(comboBox);
    }
    
    protected JButton createArrowButton() {
        JButton button = new DropdownButton();
        button.setIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/drop_button.png")));
        button.setPressedIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/pressed_drop_button.png")));
        button.setRolloverIcon(new ImageIcon(getClass().getResource("/dyno/swing/beans/hovered_drop_button.png")));
        button.setName("ComboBox.arrowButton");
        return button;
    }
    class DropdownButton extends JButton{
        public void paint(Graphics g) {
            ButtonModel model=getModel();
            Icon iconImage=getIcon();
            if(model.isPressed()){
                iconImage=getPressedIcon();
            }else if(model.isRollover()){
                iconImage=getRolloverIcon();
            }
            if(iconImage!=null)
                iconImage.paintIcon(this, g, 0, 0);
        }
    }
    protected LayoutManager createLayoutManager() {
        return new MonthComboLayout();
    }
    private class MonthComboLayout implements LayoutManager {
        
        public void addLayoutComponent(String name, Component comp) {}
        
        public void removeLayoutComponent(Component comp) {}
        
        public Dimension preferredLayoutSize(Container parent) {
            return parent.getPreferredSize();
        }
        
        public Dimension minimumLayoutSize(Container parent) {
            return parent.getMinimumSize();
        }
        
        public void layoutContainer(Container parent) {
            Insets insets=parent.getInsets();
            arrowButton.setBounds( parent.getWidth() - (ARROW_WIDTH+insets.right+1),
                    insets.top+1,
                    ARROW_WIDTH, ARROW_HEIGHT);
            if(editor!=null)
                editor.setBounds(rectangleForCurrentValue());
        }
    }
}
