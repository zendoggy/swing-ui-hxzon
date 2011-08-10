package test.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class TestLayout {

    public static class HButton extends JButton {
        public HButton(String title) {
            super(title);
        }

        public void setBounds(Rectangle r) {
            System.out.println("HButton setBounds:" + r);
            super.setBounds(r);
        }

        public void setBounds(int x, int y, int width, int height) {
            System.out.println("HButton setBounds:" + x + "," + y + "," + width + "," + height);
            super.setBounds(x, y, width, height);
        }

        public Rectangle getBounds() {
            System.out.println("HButton getBounds:");
            Rectangle r = super.getBounds();
            System.out.println("HButton getBounds:" + r);
            return r;
        }

        public void setSize(int width, int height) {
            System.out.println("HButton setSize:" + width + "," + height);
            super.setSize(width, height);
        }

        public void setSize(Dimension d) {
            System.out.println("HButton setSize:" + d);
            super.setSize(d);
        }

        public void setPreferredSize(Dimension d) {
            System.out.println("HButton setPreferredSize:" + d);
            super.setPreferredSize(d);
        }

        public void setMaximumSize(Dimension d) {
            System.out.println("HButton setMaximumSize:" + d);
            super.setMaximumSize(d);
        }

        public void setMinimumSize(Dimension d) {
            System.out.println("HButton setMinimumSize:" + d);
            super.setMinimumSize(d);
        }

        public Dimension getSize() {
            System.out.println("HButton getSize:");
            Dimension d = super.getSize();
            System.out.println("HButton getSize:" + d);
            return d;
        }

        public Dimension getPreferredSize() {
            System.out.println("HButton getPreferredSize:");
            Dimension d = super.getPreferredSize();
            System.out.println("HButton getPreferredSize:" + d);
            return d;
        }

        public Dimension getMaximumSize() {
            System.out.println("HButton getMaximumSize:");
            Dimension d = super.getMaximumSize();
            System.out.println("HButton getMaximumSize:" + d);
            return d;
        }

        public Dimension getMinimumSize() {
            System.out.println("HButton getMinimumSize:");
            Dimension d = super.getMinimumSize();
            System.out.println("HButton getMinimumSize:" + d);
            return d;
        }
    }

    public static class HTextField extends JTextField {
        public HTextField(String title) {
            super(title);
        }

        public void setBounds(Rectangle r) {
            System.out.println("HTextField setBounds:" + r);
            super.setBounds(r);
        }

        public void setBounds(int x, int y, int width, int height) {
            System.out.println("HTextField setBounds:" + x + "," + y + "," + width + "," + height);
            super.setBounds(x, y, width, height);
        }

        public Rectangle getBounds() {
            System.out.println("HTextField getBounds:");
            Rectangle r = super.getBounds();
            System.out.println("HTextField getBounds:" + r);
            return r;
        }

        public void setSize(int width, int height) {
            System.out.println("HTextField setSize:" + width + "," + height);
            super.setSize(width, height);
        }

        public void setSize(Dimension d) {
            System.out.println("HTextField setSize:" + d);
            super.setSize(d);
        }

        public void setPreferredSize(Dimension d) {
            System.out.println("HTextField setPreferredSize:" + d);
            super.setPreferredSize(d);
        }

        public void setMaximumSize(Dimension d) {
            System.out.println("HTextField setMaximumSize:" + d);
            super.setMaximumSize(d);
        }

        public void setMinimumSize(Dimension d) {
            System.out.println("HTextField setMinimumSize:" + d);
            super.setMinimumSize(d);
        }

        public Dimension getSize() {
            System.out.println("HTextField getSize:");
            Dimension d = super.getSize();
            System.out.println("HTextField getSize:" + d);
            return d;
        }

        public Dimension getPreferredSize() {
            System.out.println("HTextField getPreferredSize:");
            Dimension d = super.getPreferredSize();
            System.out.println("HTextField getPreferredSize:" + d);
            return d;
        }

        public Dimension getMaximumSize() {
            System.out.println("HTextField getMaximumSize:");
            Dimension d = super.getMaximumSize();
            System.out.println("HTextField getMaximumSize:" + d);
            return d;
        }

        public Dimension getMinimumSize() {
            System.out.println("HTextField getMinimumSize:");
            Dimension d = super.getMinimumSize();
            System.out.println("HTextField getMinimumSize:" + d);
            return d;
        }
    }

    public static class HList extends JList {
    
        public void setBounds(Rectangle r) {
            System.out.println("HList setBounds:" + r);
            super.setBounds(r);
        }
    
        public void setBounds(int x, int y, int width, int height) {
            System.out.println("HList setBounds:" + x + "," + y + "," + width + "," + height);
            super.setBounds(x, y, width, height);
        }
    
        public Rectangle getBounds() {
            System.out.println("HList getBounds:");
            Rectangle r = super.getBounds();
            System.out.println("HList getBounds:" + r);
            return r;
        }

        public void setSize(int width, int height) {
            System.out.println("HList setSize:" + width + "," + height);
            super.setSize(width, height);
        }
    
        public void setSize(Dimension d) {
            System.out.println("HList setSize:" + d);
            super.setSize(d);
        }
    
        public void setPreferredSize(Dimension d) {
            System.out.println("HList setPreferredSize:" + d);
            super.setPreferredSize(d);
        }
    
        public void setMaximumSize(Dimension d) {
            System.out.println("HList setMaximumSize:" + d);
            super.setMaximumSize(d);
        }
    
        public void setMinimumSize(Dimension d) {
            System.out.println("HList setMinimumSize:" + d);
            super.setMinimumSize(d);
        }
    
        public Dimension getSize() {
            System.out.println("HList getSize:");
            Dimension d = super.getSize();
            System.out.println("HList getSize:" + d);
            return d;
        }
    
        public Dimension getPreferredSize() {
            System.out.println("HList getPreferredSize:");
            Dimension d = super.getPreferredSize();
            System.out.println("HList getPreferredSize:" + d);
            return d;
        }
    
        public Dimension getMaximumSize() {
            System.out.println("HList getMaximumSize:");
            Dimension d = super.getMaximumSize();
            System.out.println("HList getMaximumSize:" + d);
            return d;
        }
    
        public Dimension getMinimumSize() {
            System.out.println("HList getMinimumSize:");
            Dimension d = super.getMinimumSize();
            System.out.println("HList getMinimumSize:" + d);
            return d;
        }
    }

    public static class HFrame extends JFrame {
        public HFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setSize(500, 100);
//            setLocation(700, 600);
            setBounds(700,600,500,100);
            System.out.println(getLayout());
        }

        public void setBounds(Rectangle r) {
            System.out.println("HFrame setBounds:" + r);
            super.setBounds(r);
        }

        public void setBounds(int x, int y, int width, int height) {
            System.out.println("HFrame setBounds:" + x + "," + y + "," + width + "," + height);
            super.setBounds(x, y, width, height);
        }

        public Rectangle getBounds() {
            System.out.println("HFrame getBounds:");
            Rectangle r = super.getBounds();
            System.out.println("HFrame getBounds:" + r);
            return r;
        }

        public void setSize(int width, int height) {
            System.out.println("HFrame setSize:" + width + "," + height);
            super.setSize(width, height);
        }

        public void setSize(Dimension d) {
            System.out.println("HFrame setSize:" + d);
            super.setSize(d);
        }

        public void setPreferredSize(Dimension d) {
            System.out.println("HFrame setPreferredSize:" + d);
            super.setPreferredSize(d);
        }

        public void setMaximumSize(Dimension d) {
            System.out.println("HFrame setMaximumSize:" + d);
            super.setMaximumSize(d);
        }

        public void setMinimumSize(Dimension d) {
            System.out.println("HFrame setMinimumSize:" + d);
            super.setMinimumSize(d);
        }

        public Dimension getSize() {
            System.out.println("HFrame getSize:");
            Dimension d = super.getSize();
            System.out.println("HFrame getSize:" + d);
            return d;
        }

        public Dimension getPreferredSize() {
            System.out.println("HFrame getPreferredSize:");
            Dimension d = super.getPreferredSize();
            System.out.println("HFrame getPreferredSize:" + d);
            return d;
        }

        public Dimension getMaximumSize() {
            System.out.println("HFrame getMaximumSize:");
            Dimension d = super.getMaximumSize();
            System.out.println("HFrame getMaximumSize:" + d);
            return d;
        }

        public Dimension getMinimumSize() {
            System.out.println("HFrame getMinimumSize:");
            Dimension d = super.getMinimumSize();
            System.out.println("HFrame getMinimumSize:" + d);
            return d;
        }
    }

    public static void main(String[] args) {
        HFrame frame = new HFrame();
         frame.setLayout(new FlowLayout());
//        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
//        frame.setLayout(new GridLayout());
        HButton btn = new HButton("hello");
        HTextField field = new HTextField("why");
        HList list = new HList();
        frame.add(btn);
        frame.add(field);
        frame.add(list);
        frame.setVisible(true);
    }

}
