package org.hxzon.demo.jide;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jidesoft.popup.JidePopup;
import com.jidesoft.swing.JideSwingUtilities;

public class JidePopupDemo {

    public static void createAndShowGUI() {
        final JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        demo1(f);
        f.pack();
        JideSwingUtilities.globalCenterWindow(f);
        f.setVisible(true);
    }

    public static void demo1(final JFrame f) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //
        final JButton attachedButton = new JButton();
        attachedButton.setAction(new AbstractAction("attached button") {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JidePopup popup = new JidePopup();
                popup.getContentPane().setLayout(new BorderLayout());
                JTextArea view = new JTextArea();
                view.setRows(10);
                view.setColumns(40);
                popup.getContentPane().add(new JScrollPane(view));
                JMenuBar menuBar = new JMenuBar();
                JMenu menu = menuBar.add(new JMenu("File"));
                menu.add("<< Example >>");
                menuBar.add(new JMenu("Edit"));
                menuBar.add(new JMenu("Help"));
                popup.setJMenuBar(menuBar);
                popup.setOwner(attachedButton);
                popup.setResizable(true);
                popup.setMovable(true);
                popup.setFocusable(true);
                popup.setDefaultFocusComponent(view);
                popup.showPopup();

            }

        });
        panel.add(new JTextField("nothing"));
        panel.add(attachedButton);
        f.add(panel);
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
