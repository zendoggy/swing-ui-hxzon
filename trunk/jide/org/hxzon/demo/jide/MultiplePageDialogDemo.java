package org.hxzon.demo.jide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jidesoft.dialog.AbstractDialogPage;
import com.jidesoft.dialog.MultiplePageDialog;
import com.jidesoft.dialog.PageList;
import com.jidesoft.swing.CheckBoxList;
import com.jidesoft.swing.CheckBoxTree;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.StyleRange;
import com.jidesoft.tree.StyledTreeCellRenderer;

public class MultiplePageDialogDemo {

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
        //multiple page dialog
        final MultiplePageDialog multiplePageDialog = new MultiplePageDialog(f);
        multiplePageDialog.setStyle(MultiplePageDialog.TREE_STYLE);
        PageList pageList = new PageList();
        pageList.append(new AbstractDialogPage("styled tree cell renderer") {

            @Override
            public void lazyInitialize() {
                this.setLayout(new BorderLayout());
                //StyledTreeCellRenderer
                CheckBoxTree tree = new CheckBoxTree();
                tree.setCellRenderer(new StyledTreeCellRenderer() {
                    int[] randomStyle = new int[] { StyleRange.STYLE_DOTTED, StyleRange.STYLE_DOUBLE_STRIKE_THROUGH, StyleRange.STYLE_STRIKE_THROUGH, StyleRange.STYLE_SUBSCRIPT,
                            StyleRange.STYLE_SUPERSCRIPT, StyleRange.STYLE_UNDERLINED, StyleRange.STYLE_WAVED, };
                    Color[] randomColor = new Color[] { Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.RED, };
                    int[] randomFont = new int[] { Font.BOLD, Font.PLAIN, Font.ITALIC, };

                    protected void customizeStyledLabel(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                        super.customizeStyledLabel(tree, value, sel, expanded, leaf, row, hasFocus);
                        String text = getText();
                        int style = randomStyle[row % randomStyle.length];
                        Color color = randomColor[row % randomColor.length];
                        int font = randomFont[row % randomFont.length];
                        this.addStyleRange(new StyleRange(0, text.length(), font, color, style));
                    }
                });
                this.add(new JScrollPane(tree));
            }

        });
        pageList.append(new AbstractDialogPage("check box tree") {

            @Override
            public void lazyInitialize() {
                this.setLayout(new BorderLayout());
                CheckBoxTree checkBoxTree = new CheckBoxTree();
                checkBoxTree.setDigIn(true);
                this.add(new JScrollPane(checkBoxTree));
            }

        });
        pageList.append(new AbstractDialogPage("check box list") {

            @Override
            public void lazyInitialize() {
                this.setLayout(new BorderLayout());
                CheckBoxList checkBoxList = new CheckBoxList(new String[] { "1", "2", "3", "4", "12", "21", "13", "123" });
                checkBoxList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                this.add(new JScrollPane(checkBoxList));
            }

        });
        multiplePageDialog.setPageList(pageList);
        JButton multiplePageDialogButton = new JButton();
        multiplePageDialogButton.setAction(new AbstractAction("open") {

            @Override
            public void actionPerformed(ActionEvent e) {
                multiplePageDialog.pack();
                JideSwingUtilities.globalCenterWindow(multiplePageDialog);
                multiplePageDialog.setVisible(true);
            }

        });
        panel.add(multiplePageDialogButton);
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
