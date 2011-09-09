package org.hxzon.demo.swingx;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hxzon.swing.layout.simple.SimpleLayout;
import org.hxzon.util.ImageUtil;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXHeader;
import org.jdesktop.swingx.JXImageView;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXMultiThumbSlider;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

public class SwingXComponentsDemo2 extends JXFrame {
    private static final long serialVersionUID = 1L;

    public SwingXComponentsDemo2() {
        super("SwingX Components Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new SimpleLayout());
        demo1();
        demo2();
        demo3();
        this.pack();
    }

    public void demo1() {
        //JXCollapsiblePane
//        JXMultiSplitPane multiSplitPanel=new JXMultiSplitPane() ;
//        multiSplitPanel.setPreferredSize(new Dimension(120,300));
//        multiSplitPanel.setModel(new MultiSplitLayout.RowSplit() );
        JPanel multiSplitPanel = new JPanel(new SimpleLayout());
        //JXHeader
        JXHeader jxheader = new JXHeader();
        jxheader.setTitle("SwingX components demo2");
        jxheader.setDescription("JXHeader itself extends JXPanel, providing translucency and painting delegates.\n" + "BasicHeaderUI uses the following UI defaults:\n"
                + "Header.defaultIcon: The default icon to use when creating a new JXHeader.");
        multiSplitPanel.add(jxheader);

        //JXLabel
        JXLabel jxLabel = new JXLabel("jxLabel supports Painters\n multi-line text\n and text rotation");
        jxLabel.setLineWrap(true);
        multiSplitPanel.add(jxLabel);
        //
        JXMultiThumbSlider<Integer> multiThumbSlider = new JXMultiThumbSlider<Integer>();
        multiThumbSlider.setMaximumValue(5);
        multiThumbSlider.setMinimumValue(-1);
        multiSplitPanel.add(multiThumbSlider);

        // a container to put all JXTaskPane together
        JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();

        // create a first taskPane with common actions
        JXTaskPane taskPane1 = new JXTaskPane();
        taskPane1.setTitle("JXImageView");
        taskPane1.setSpecial(true);

        //JXImageView
        //The user can drag an image into the panel from other applications and move the image around within the view.
        JXImageView imageView = new JXImageView();
        BufferedImage screenImage = ImageUtil.captureScreen(3);
        imageView.setImage(screenImage);
        imageView.setPreferredSize(new Dimension(75, 75));
        taskPane1.add(imageView);

        JXTaskPane taskPane2 = new JXTaskPane();
        taskPane2.setTitle("Search");

        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField("");
        taskPane2.add(searchLabel);
        taskPane2.add(searchField);

        taskPaneContainer.add(taskPane1);
        taskPaneContainer.add(taskPane2);
        multiSplitPanel.add(taskPaneContainer);
        this.add(multiSplitPanel);
    }

    public void demo2() {
        JPanel panel = new JPanel(new SimpleLayout(true));
       
        this.add(panel);
    }

    public void demo3() {
        JXStatusBar bar = new JXStatusBar();
        JLabel statusLabel = new JLabel("Ready");
        JXStatusBar.Constraint c1 = new JXStatusBar.Constraint();
        // Fixed width of 100 with no inserts
        c1.setFixedWidth(100);
        bar.add(statusLabel, c1);
     // Fill with no inserts - will use remaining space
        JXStatusBar.Constraint c2 = new JXStatusBar.Constraint(JXStatusBar.Constraint.ResizeBehavior.FILL);
        JProgressBar pbar = new JProgressBar();
        bar.add(pbar, c2); 
        //JXFrame.setStatusBar()
        this.setStatusBar(bar);
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
                    new SwingXComponentsDemo2().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
