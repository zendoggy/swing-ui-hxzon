package org.hxzon.demo.jide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jidesoft.plaf.UIDefaultsLookup;
import com.jidesoft.plaf.basic.ThemePainter;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.Resizable;
import com.jidesoft.swing.ResizablePanel;

public class TestResize {
 public static void main(String args[]){
	 JFrame f=new JFrame();
	 f.setLayout(new BorderLayout());
	 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 final JPanel content=new JPanel();
	 content.setBackground(Color.blue);
	 JLabel label=new JLabel("hello world");
	 content.add(label);
	 content.setSize(100,200);
	 ResizablePanel resizePanel = new ResizablePanel();
     resizePanel.setBorder(new EmptyBorder(4, 4, 4, 4) {
         @Override
         public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
             super.paintBorder(c, g, x, y, width, height);
             ThemePainter painter = (ThemePainter) UIDefaultsLookup.get("Theme.painter");
             if (painter != null) {
                 Insets insets = getBorderInsets(c);
                 Rectangle rect = new Rectangle(0, y + height - insets.bottom, width, insets.bottom);
                 painter.paintGripper((JComponent) c, g, rect, SwingConstants.HORIZONTAL, ThemePainter.STATE_DEFAULT);
             }
         }
     });
     resizePanel.add(content);
     resizePanel.setBackground(Color.red);
     JPanel p1=new JPanel();
     JLabel label1=new JLabel("1");
     p1.add(label1);
     JPanel p2=new JPanel();
     JLabel label2=new JLabel("2");
     p2.add(label2);
     JPanel p3=new JPanel();
     JLabel label3=new JLabel("3");
     p3.add(label3);
     JPanel p4=new JPanel();
     JLabel label4=new JLabel("4");
     p4.add(label4);
     f.add(p1,BorderLayout.EAST);
     f.add(p2,BorderLayout.SOUTH);
     f.add(p3,BorderLayout.WEST);
     f.add(p4,BorderLayout.NORTH);
     JPanel p5=new JPanel();
     p5.setBackground(Color.green);
     p5.add(resizePanel);
     f.add(p5,BorderLayout.CENTER);
     f.pack();
     JideSwingUtilities.globalCenterWindow(f);
	 f.setVisible(true);
 }
}
