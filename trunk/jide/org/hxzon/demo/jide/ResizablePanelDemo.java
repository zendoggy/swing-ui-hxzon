package org.hxzon.demo.jide;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.jidesoft.swing.CheckBoxList;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.ResizablePanel;

public class ResizablePanelDemo {
    public static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo(f);
        f.pack();
        JideSwingUtilities.globalCenterWindow(f);
        f.setVisible(true);
    }

    public static void demo(JFrame f) {
        JLabel label = new JLabel("hello world");
        final CheckBoxList checkBoxList = new CheckBoxList(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" });
        checkBoxList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ResizablePanel resizablePanel = new ResizablePanel();
        resizablePanel.setBackground(Color.red);
//        resizePanel.setBorder(new EmptyBorder(4, 4, 4, 4) {
//            @Override
//            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//                super.paintBorder(c, g, x, y, width, height);
//                ThemePainter painter = (ThemePainter) UIDefaultsLookup.get("Theme.painter");
//                if (painter != null) {
//                    Insets insets = getBorderInsets(c);
//                    Rectangle rect = new Rectangle(0, y + height - insets.bottom, width, insets.bottom);
//                    painter.paintGripper((JComponent) c, g, rect, SwingConstants.HORIZONTAL, ThemePainter.STATE_DEFAULT);
//                }
//            }
//        });
        resizablePanel.setBorder(new EmptyBorder(1, 1, 1, 1));
        //default layout is BorderLayout
//        resizablePanel.setLayout(new FlowLayout());
        //set BoxLayout will can't resizable?
//        resizablePanel.setLayout(new BoxLayout(resizablePanel,BoxLayout.Y_AXIS));
//        resizablePanel.add(label);
//        resizablePanel.add(new JScrollPane(checkBoxList));
        //
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(label);
        content.add(new JScrollPane(checkBoxList));
        resizablePanel.add(content);
        JPanel p5 = new JPanel();
        p5.setBackground(Color.green);
        p5.add(resizablePanel);
        f.add(p5, BorderLayout.CENTER);
        JPanel p1 = new JPanel();
        JLabel label1 = new JLabel("1");
        p1.add(label1);
        JPanel p2 = new JPanel();
        JLabel label2 = new JLabel("2");
        p2.add(label2);
        JPanel p3 = new JPanel();
        JLabel label3 = new JLabel("3");
        p3.add(label3);
        JPanel p4 = new JPanel();
        JLabel label4 = new JLabel("4");
        p4.add(label4);
        f.add(p1, BorderLayout.EAST);
        f.add(p2, BorderLayout.SOUTH);
        f.add(p3, BorderLayout.WEST);
        f.add(p4, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
