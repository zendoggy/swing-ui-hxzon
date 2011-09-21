package org.jfree.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class TimeSeriesDemo13 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public TimeSeriesDemo13(String s) {
        super(s);
        setContentPane(createDemoPanel());
    }

    private static JFreeChart createChart(XYDataset xydataset) {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Weekly Data", "Date", "Value", xydataset, true, true, false);
        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        if (xyitemrenderer instanceof StandardXYItemRenderer) {
            StandardXYItemRenderer standardxyitemrenderer = (StandardXYItemRenderer) xyitemrenderer;
            standardxyitemrenderer.setBaseShapesVisible(true);
            standardxyitemrenderer.setBaseShapesFilled(true);
        }
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        TickUnits tickunits = new TickUnits();
        tickunits.add(new DateTickUnit(DateTickUnitType.DAY, 1, new SimpleDateFormat("MMM dd ''yy")));
        tickunits.add(new DateTickUnit(DateTickUnitType.DAY, 7, new SimpleDateFormat("MMM dd ''yy")));
        tickunits.add(new DateTickUnit(DateTickUnitType.MONTH, 1, new SimpleDateFormat("MMM ''yy")));
        dateaxis.setStandardTickUnits(tickunits);
        return jfreechart;
    }

    private static XYDataset createDataset(int i) {
        TimeSeries timeseries = new TimeSeries("Random Data");
        Object obj = new Week();
        double d = 100D;
        for (int j = 0; j < i; j++) {
            timeseries.add(((RegularTimePeriod) (obj)), d);
            d *= 1.0D + (Math.random() - 0.499D) / 100D;
            obj = ((RegularTimePeriod) (obj)).next();
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeseries);
        return timeseriescollection;
    }

    public static JPanel createDemoPanel() {
        XYDataset xydataset = createDataset(26);
        JFreeChart jfreechart = createChart(xydataset);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        XYDataset xydataset1 = createDataset(1);
        JFreeChart jfreechart1 = createChart(xydataset1);
        ChartPanel chartpanel1 = new ChartPanel(jfreechart1);
        JTabbedPane jtabbedpane = new JTabbedPane();
        jtabbedpane.add("Chart 1", chartpanel);
        jtabbedpane.add("Chart 2", chartpanel1);
        JPanel jpanel = new JPanel(new BorderLayout());
        jpanel.setPreferredSize(new Dimension(500, 270));
        jpanel.add(jtabbedpane);
        return jpanel;
    }

    public static void main(String args[]) {
        TimeSeriesDemo13 timeseriesdemo13 = new TimeSeriesDemo13("Time Series Demo 13");
        timeseriesdemo13.pack();
        RefineryUtilities.centerFrameOnScreen(timeseriesdemo13);
        timeseriesdemo13.setVisible(true);
    }
}
