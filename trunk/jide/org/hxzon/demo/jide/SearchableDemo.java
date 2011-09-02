package org.hxzon.demo.jide;

import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jidesoft.swing.CheckBoxList;
import com.jidesoft.swing.CheckBoxTree;
import com.jidesoft.swing.JideSplitPane;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.ListSearchable;
import com.jidesoft.swing.SearchableBar;
import com.jidesoft.swing.SearchableUtils;
import com.jidesoft.swing.Sticky;
import com.jidesoft.swing.TreeSearchable;

public class SearchableDemo {

    public static void createAndShowGUI() {
        final JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.X_AXIS));
        JideSplitPane jideSplitPane=new JideSplitPane();
        jideSplitPane.add(demo1());
        jideSplitPane.add(demo2());
        f.add(jideSplitPane);
        f.pack();
        JideSwingUtilities.globalCenterWindow(f);
        f.setVisible(true);
    }

    public static JPanel demo1() {
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
        ListSearchable listSearchable = SearchableUtils.installSearchable(checkBoxList);
        // further configure it 
        listSearchable.setCaseSensitive(true);
        //check box tree
        CheckBoxTree checkBoxTree = new CheckBoxTree();
        checkBoxTree.setDigIn(true);
        TreeSearchable treeSearchable = SearchableUtils.installSearchable(checkBoxTree);
        treeSearchable.setCaseSensitive(true);
        new Sticky(checkBoxTree);
        panel.add(new JScrollPane(checkBoxTree));

        return panel;
    }

    public static JPanel demo2() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //SearchableBar
        CheckBoxTree checkBoxTree2 = new CheckBoxTree();
        checkBoxTree2.setDigIn(true);
        TreeSearchable treeSearchable2 = SearchableUtils.installSearchable(checkBoxTree2);
        treeSearchable2.setCaseSensitive(true);
        SearchableBar searchableBar = new SearchableBar(treeSearchable2);
        panel.add(searchableBar);
        panel.add(new JScrollPane(checkBoxTree2));
        //
        CheckBoxTree checkBoxTree3 = new CheckBoxTree();
        checkBoxTree3.setDigIn(true);
        TreeSearchable treeSearchable3 = SearchableUtils.installSearchable(checkBoxTree3);
        treeSearchable3.setCaseSensitive(true);
        SearchableBar searchableBar3 = SearchableBar.install(treeSearchable3, KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK), new SearchableBar.Installer() {
            public void openSearchBar(SearchableBar searchableBar) {
                searchableBar.setVisible(true);
            }

            public void closeSearchBar(SearchableBar searchableBar) {
                searchableBar.setVisible(false);
            }
        });
        panel.add(searchableBar3);
        panel.add(new JScrollPane(checkBoxTree3));
        return panel;
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
