package org.hxzon.demo.jfreechart;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.hxzon.swing.layout.simple.SimpleLayout;
import org.jfree.ui.DateChooserPanel;
import org.jfree.ui.FontChooserDialog;
import org.jfree.ui.FontDisplayField;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.Spinner;
import org.jfree.ui.UIUtilities;
import org.jfree.ui.about.SystemPropertiesPanel;
import org.jfree.ui.tabbedui.TabbedDialog;

public class JCommonDemo {
    public static void createAndShowGUI() {
        final JFrame f = new JFrame("jfreechart jcommon demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        demo1(f);
        demo2(f);
        f.pack();
        RefineryUtilities.centerFrameOnScreen(f);
        f.setVisible(true);
    }

    public static void demo1(final JFrame f) {
        JPanel panel = new JPanel(new SimpleLayout());
        //DateChooserPanel
        DateChooserPanel dateChooserPanel = new DateChooserPanel();
        panel.add(dateChooserPanel);
        f.add(panel);
    }

    public static void demo2(final JFrame f) {
        JPanel panel = new JPanel(new SimpleLayout(true));
        //TabbedDialog
        JButton openTabbedDialogButton = new JButton();
        openTabbedDialogButton.setAction(new AbstractAction("open tabbed dialog") {
            public void actionPerformed(ActionEvent e) {
                TabbedDialog dialog = new TabbedDialog(f);
                //SystemPropertiesPanel
                //use SystemPropertiesTableModel and SortableTable 
                dialog.add(new SystemPropertiesPanel());
                dialog.pack();
                RefineryUtilities.centerFrameOnScreen(dialog);
                dialog.setVisible(true);
            }
        });
        panel.add(openTabbedDialogButton);
        //FontChooserDialog
        final FontDisplayField openFontChooserDialogButton = new FontDisplayField(f.getFont());
        openFontChooserDialogButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                FontChooserDialog dialog = new FontChooserDialog(f, "Font Chooser Dialog", true, f.getFont());
                dialog.pack();
                RefineryUtilities.centerFrameOnScreen(dialog);
                dialog.setVisible(true);
                openFontChooserDialogButton.setDisplayFont(dialog.getSelectedFont());
            }
        });
        panel.add(openFontChooserDialogButton);
        //Spinner
        //A very basic spinner component, used for demo purposes only.
        Spinner spinner = new Spinner(1);
        panel.add(spinner);
        f.add(panel);
    }

    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        UIUtilities.setupUI();
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
