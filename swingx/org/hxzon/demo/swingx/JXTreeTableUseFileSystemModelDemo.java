package org.hxzon.demo.swingx;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hxzon.swing.layout.simple.SimpleLayout;
import org.hxzon.swing.layout.simple.SimpleLayoutData;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.treetable.FileSystemModel;
import org.jdesktop.swingx.treetable.TreeTableModel;

public class JXTreeTableUseFileSystemModelDemo extends JFrame {
    public JXTreeTableUseFileSystemModelDemo() {
        super("file system treetable");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new SimpleLayout());
        this.add(demo(), SimpleLayoutData.fillPercent(100));
        this.setLocation(300, 300);
        this.pack();
    }

    public JPanel demo() {
        JPanel panel = new JPanel(new SimpleLayout());
        JXTreeTable list = new JXTreeTable();
        TreeTableModel fileSystemModel = new FileSystemModel();
        list.setTreeTableModel(fileSystemModel);
        list.addHighlighter(HighlighterFactory.createSimpleStriping());
        //highlight mouse hover
        list.addHighlighter(new ColorHighlighter(HighlightPredicate.ROLLOVER_ROW, Color.BLUE, Color.RED));
        //ctrl+F open a search dialog
        panel.add(new JScrollPane(list), SimpleLayoutData.fillPercent(100));
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
                    new JXTreeTableUseFileSystemModelDemo().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
