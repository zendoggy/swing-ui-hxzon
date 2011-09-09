package org.hxzon.demo.swingx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hxzon.swing.layout.simple.SimpleLayout;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXCollapsiblePane.Direction;
import org.jdesktop.swingx.JXColorSelectionButton;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXGraph;
import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.JXTextField;
import org.jdesktop.swingx.calendar.CalendarUtils;
import org.jdesktop.swingx.calendar.DateSelectionModel;
import org.jdesktop.swingx.hyperlink.HyperlinkAction;
import org.jdesktop.swingx.image.StackBlurFilter;
import org.jdesktop.swingx.painter.AbstractPainter;
import org.jdesktop.swingx.painter.MattePainter;

public class SwingXComponentsDemo extends JXFrame {
    private static final long serialVersionUID = 1L;

    public SwingXComponentsDemo() {
        super("SwingX Components Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new SimpleLayout());
        demo();
        this.pack();
    }

    public void demo() {
        demo1();
        demo2();
        demo3();
    }

    public void demo1() {
        //JXCollapsiblePane
        JXCollapsiblePane collapsibelPane1 = new JXCollapsiblePane();
        collapsibelPane1.setLayout(new SimpleLayout());

        //JXMonthView
        JXMonthView monthView = new JXMonthView();
//        monthView.setPreferredCols(2);
//        monthView.setPreferredRows(2);
        monthView.setSelectionMode(DateSelectionModel.SelectionMode.MULTIPLE_INTERVAL_SELECTION);
        collapsibelPane1.add(monthView);

        JButton toggle1 = new JButton(collapsibelPane1.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
        this.add(toggle1);
        this.add(collapsibelPane1);
        //JXCollapsiblePane,Direction.LEFT no effect?
        JXCollapsiblePane collapsibelPane2 = new JXCollapsiblePane(Direction.UP);
        collapsibelPane2.setLayout(new SimpleLayout());

        //JXGraph
        Point2D origin = new Point2D.Double(0.0d, 0.0d);
        Rectangle2D view = new Rectangle2D.Double(-1.0d, 1.0d, 2.0d, 2.0d);
        JXGraph graph = new JXGraph(origin, view, 0.5d, 5, 0.5d, 5);
        collapsibelPane2.add(graph);

        JButton toggle2 = new JButton(collapsibelPane2.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
        this.add(toggle2);
        this.add(collapsibelPane2);
    }

    public void demo2() {

        JPanel panel = new JPanel(new SimpleLayout());
        //JXTextField,PromptSupport,BuddySupport
        JXTextField textFieldWithPrompt = new JXTextField();
        textFieldWithPrompt.setPreferredSize(new Dimension(100, 25));
        textFieldWithPrompt.setPrompt("email");
        panel.add(textFieldWithPrompt);
        //JXDatePicker
        JXDatePicker datePicker = new JXDatePicker();
        datePicker.setFormats("yyyy-MM-dd", "yyyy,MM,dd", "yyyy.MM.dd", "yyyyMMdd");
        Calendar calendar = datePicker.getMonthView().getCalendar();
        // starting today if we are in a hurry
        calendar.setTime(new Date());
//        picker.getMonthView().setLowerBound(calendar.getTime());
        // end of next week
        CalendarUtils.endOfWeek(calendar);
        datePicker.getMonthView().setUpperBound(calendar.getTime());
        panel.add(datePicker);

        try {
            //JXHyperlink
            HyperlinkAction linkAction = HyperlinkAction.createHyperlinkAction(new URI("http://swinglabs.org"));
            JXHyperlink hyperlink = new JXHyperlink(linkAction);
            panel.add(hyperlink);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //JXButton
        JXButton b = new JXButton("Execute");
        MattePainter p = new MattePainter(Color.GREEN);
        b.setForegroundPainter(p);
        AbstractPainter fgPainter = (AbstractPainter) b.getForegroundPainter();
        StackBlurFilter filter = new StackBlurFilter();
        fgPainter.setFilters(filter);
        panel.add(b);
        this.add(panel);
        //

    }

    public void demo3() {
        JPanel panel = new JPanel(new SimpleLayout(true));
        //JXBusyLabel
        JXBusyLabel busyLabel = new JXBusyLabel(new Dimension(25, 25));
//        BusyPainter painter = new BusyPainter(
//        new Rectangle2D.Float(0, 0,13.500001f,1),
//        new RoundRectangle2D.Float(12.5f,12.5f,59.0f,59.0f,10,10));
//        painter.setTrailLength(5);
//        painter.setPoints(31);
//        painter.setFrame(1);
//        label.setBusyPainter(painter);
        panel.add(busyLabel);
        //JXColorSelectionButton
        JXColorSelectionButton colorButton = new JXColorSelectionButton();
        //no effect
//        colorButton.setText("color");
        panel.add(colorButton);
        this.add(panel);
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
                    new SwingXComponentsDemo().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
