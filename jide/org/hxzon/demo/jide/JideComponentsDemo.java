package org.hxzon.demo.jide;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jidesoft.spinner.DateSpinner;
import com.jidesoft.swing.Calculator;
import com.jidesoft.swing.CheckBoxList;
import com.jidesoft.swing.DefaultOverlayable;
import com.jidesoft.swing.FolderChooser;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.MarqueePane;
import com.jidesoft.swing.MultilineLabel;
import com.jidesoft.swing.OverlayRadioButton;
import com.jidesoft.swing.OverlayTextField;
import com.jidesoft.swing.OverlayableIconsFactory;
import com.jidesoft.swing.OverlayableUtils;
import com.jidesoft.swing.RangeSlider;
import com.jidesoft.swing.SelectAllUtils;
import com.jidesoft.swing.TristateCheckBox;

public class JideComponentsDemo {

    public static void createAndShowGUI() {
        final JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        demo1(f);
        demo2(f);
        demo3(f);
        demo4(f);
        f.pack();
        JideSwingUtilities.globalCenterWindow(f);
        f.setVisible(true);
    }

    public static void demo1(final JFrame f) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //folder chooser dialog
        JButton folderChooserButton = new JButton();
        folderChooserButton.setAction(new AbstractAction("选择文件夹") {

            @Override
            public void actionPerformed(ActionEvent e) {
                FolderChooser fc = new FolderChooser();
                fc.showOpenDialog(f);
            }

        });
        panel.add(folderChooserButton);
        //
        TristateCheckBox tristateCheckBox = new TristateCheckBox("tristate checkbox");
        panel.add(tristateCheckBox);
        //select all when focus
        JTextField textFieldWithSelectAll1 = new JTextField("select all when first focus");
        SelectAllUtils.install(textFieldWithSelectAll1);
        panel.add(textFieldWithSelectAll1);
        JTextField textFieldWithSelectAll2 = new JTextField("select all when focus");
        SelectAllUtils.install(textFieldWithSelectAll2, false);
        panel.add(textFieldWithSelectAll2);
        //check box list
        final CheckBoxList checkBoxList = new CheckBoxList(new String[] { "1", "2", "3", "4" });
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
        panel.add(checkBoxList);
        //old implementation
//        CheckBoxListWithSelectable list2 = new CheckBoxListWithSelectable(new String[] { "1", "2", "3", "4" });
//        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        panel.add(list2);
        //
        String longString = "MarqueePane extends JScrollPane and it automatically scrolls the content. Here is the list of features that MarqueePane supports."
                + "Changes the scroll direction. It can scroll up, down, right or left. Changes the scroll amount. The number of pixels it scrolls every loop. The smaller, the"
                + "smoother it appears. Scrolls and stays. For example, when you scroll a text, it can scroll line by line and stay"
                + "for a while on each line so that the full text on that line can be read. ";
        JLabel longLabel = new JLabel(longString);
        MarqueePane horizonMarqueeLeft = new MarqueePane(longLabel);
        horizonMarqueeLeft.setPreferredSize(new Dimension(250, 40));
        MultilineLabel textArea = new MultilineLabel(longString);
        MarqueePane verticalMarqueeUp = new MarqueePane(textArea);
        verticalMarqueeUp.setScrollDirection(MarqueePane.SCROLL_DIRECTION_UP);
        verticalMarqueeUp.setPreferredSize(new Dimension((int) horizonMarqueeLeft.getPreferredSize().getWidth(), 38));
        verticalMarqueeUp.setScrollAmount(1);
        verticalMarqueeUp.setStayPosition(14);
        panel.add(horizonMarqueeLeft);
        panel.add(verticalMarqueeUp);
        f.add(panel);
    }

    public static void demo2(JFrame f) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //Calculator
        final JTextField calculatorResultField = new JTextField();
        calculatorResultField.setColumns(20);
        calculatorResultField.setHorizontalAlignment(JTextField.TRAILING);
        Calculator calculator = new Calculator();
        calculator.registerKeyboardActions(calculatorResultField, JComponent.WHEN_FOCUSED);
        calculator.addPropertyChangeListener(Calculator.PROPERTY_DISPLAY_TEXT, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                calculatorResultField.setText("" + evt.getNewValue());
            }
        });
        panel.add(calculatorResultField);
        panel.add(calculator);
        f.add(panel);
    }

    public static void demo3(JFrame f) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //Overlayable
        //hxzon:have bug
        JLabel info = new JLabel(OverlayableUtils.getPredefinedOverlayIcon(OverlayableIconsFactory.INFO));
        panel.add(new DefaultOverlayable(new OverlayRadioButton("with overlay"), info, DefaultOverlayable.SOUTH_EAST));
        JLabel attention = new JLabel(OverlayableUtils.getPredefinedOverlayIcon(OverlayableIconsFactory.ATTENTION));
        panel.add(new DefaultOverlayable(new OverlayTextField("with overlay"), attention, DefaultOverlayable.SOUTH_EAST));
        f.add(panel);
    }
    
    public static void demo4(JFrame f){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //range slider
        RangeSlider rangeSlider = new RangeSlider(0, 100, 10, 90); 
        rangeSlider.setPaintTicks(true); 
        rangeSlider.setMajorTickSpacing(10);  
        rangeSlider.setPaintLabels(true);
        panel.add(rangeSlider);
        //date spinner
        DateSpinner dateSpinner=new DateSpinner();
        panel.add(dateSpinner);
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
