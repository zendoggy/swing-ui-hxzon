package org.hxzon.demo.jide;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.StyleRange;
import com.jidesoft.swing.StyledLabel;
import com.jidesoft.tree.StyledTreeCellRenderer;

public class StyledLabelDemo {

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
        //simple
        StyledLabel javaTM = new StyledLabel("JavaTM");
        javaTM.addStyleRange(new StyleRange(4, 2, Font.PLAIN, StyleRange.STYLE_SUPERSCRIPT));
        panel.add(javaTM);
        //mixed
        StyledLabel mixed = new StyledLabel("Mixed Underlined Strikethrough Styles");
        mixed.addStyleRange(new StyleRange(0, 5, Font.BOLD, Color.BLUE));
        mixed.addStyleRange(new StyleRange(6, 10, Font.PLAIN, Color.BLACK, StyleRange.STYLE_UNDERLINED));
        mixed.addStyleRange(new StyleRange(17, 13, Font.PLAIN, Color.RED, StyleRange.STYLE_STRIKE_THROUGH));
        panel.add(mixed);
        //customizedUnderlined
        StyledLabel customizedUnderlined = new StyledLabel("Customized Underlined");
        customizedUnderlined.addStyleRange(new StyleRange(Font.PLAIN, Color.BLACK, StyleRange.STYLE_UNDERLINED, Color.BLACK, new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND,
                1.0f, new float[] { 6, 3, 0, 3 }, 0)));
        panel.add(customizedUnderlined);
        //StyledTreeCellRenderer
        JTree tree = new JTree();
        tree.setCellRenderer(new StyledTreeCellRenderer() {
            int[] randomStyle = new int[] { StyleRange.STYLE_DOTTED, StyleRange.STYLE_DOUBLE_STRIKE_THROUGH, StyleRange.STYLE_STRIKE_THROUGH, StyleRange.STYLE_SUBSCRIPT, StyleRange.STYLE_SUPERSCRIPT,
                    StyleRange.STYLE_UNDERLINED, StyleRange.STYLE_WAVED, };
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
        panel.add(new JScrollPane(tree));
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
