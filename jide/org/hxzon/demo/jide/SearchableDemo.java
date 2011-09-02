package org.hxzon.demo.jide;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jidesoft.swing.CheckBoxList;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.ListSearchable;
import com.jidesoft.swing.SearchableUtils;

public class SearchableDemo {

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
        //check box list
        final CheckBoxList checkBoxList = new CheckBoxList(new String[] { "1", "2", "3", "4", "12", "21", "13", "123" });
        checkBoxList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        checkBoxList.getCheckBoxListSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Object[] selectValues = checkBoxList.getCheckBoxListSelectedValues();
                for (Object o : selectValues) {
                    System.out.print(o + ",");
                }
                System.out.println();
            }
        });
        panel.add(new JScrollPane(checkBoxList));
        ListSearchable searchable = SearchableUtils.installSearchable(checkBoxList);
        // further configure it 
        searchable.setCaseSensitive(true);
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
