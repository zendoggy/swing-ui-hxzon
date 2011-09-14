package org.hxzon.demo.swingx;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hxzon.swing.layout.simple.SimpleLayout;
import org.jdesktop.swingx.JXEditorPane;

public class JXEditorPaneDemo extends JFrame {
    public JXEditorPaneDemo() throws Exception {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new SimpleLayout());
        init();
        this.setLocation(300, 300);
        this.pack();
        this.setVisible(true);
    }

    public void init() {
        JPanel panel = new JPanel(new SimpleLayout());
        try {
            JXEditorPane editorPane = new JXEditorPane("http://swinglabs.org");
            panel.add(editorPane);
//            JToolBar toolBar = new ActionContainerFactory(editorPane.getActionMap()).createToolBar(editorPane.getCommands());
//            toolBar.addSeparator();
//            toolBar.add(editorPane.getParagraphSelector());
//            panel.add(toolBar);
            panel.add(new JScrollPane(editorPane));
            this.add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    new JXEditorPaneDemo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
