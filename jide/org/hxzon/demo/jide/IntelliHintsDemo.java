package org.hxzon.demo.jide;

import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jidesoft.hints.FileIntelliHints;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.swing.JideSwingUtilities;

public class IntelliHintsDemo {

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
        //FileIntelliHints
        JTextField pathTextField = new JTextField();
        FileIntelliHints intelliHints = new FileIntelliHints(pathTextField);
        intelliHints.setFolderOnly(true);
        panel.add(pathTextField);
        //
        List<String> urls = Arrays.asList(new String[] { 
                "http://www.jidesoft.com/products/download.htm", 
                "http://www.jidesoft.com/products/download2.htm",
                "http://www.jidesoft.com/products/download3.htm", 
                "http://www.jidesoft.com/products", 
                "http://www.jidesoft.com", 
                "http://www.jide.com", "http://www.hxzon.com",
                });
        JTextField urlTextField = new JTextField("http://");
        ListDataIntelliHints<String> intellihints = new ListDataIntelliHints<String>(urlTextField, urls);
        intellihints.setCaseSensitive(false);
        panel.add(urlTextField);
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
