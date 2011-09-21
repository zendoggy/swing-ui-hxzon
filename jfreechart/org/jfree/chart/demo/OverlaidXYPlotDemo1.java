package org.jfree.chart.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class OverlaidXYPlotDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public OverlaidXYPlotDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static JFreeChart createChart() {
        IntervalXYDataset dataset1 = createDataset1();
        XYBarRenderer renderer1 = new XYBarRenderer(0.20000000000000001D);
        renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
        DateAxis domainAxis = new DateAxis("Date");
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        NumberAxis valueAxis = new NumberAxis("Value");
        XYPlot plot = new XYPlot(dataset1, domainAxis, valueAxis, renderer1);
        XYDataset dataset2 = createDataset2();
        StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        return new JFreeChart("Overlaid XYPlot Demo 1", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
    }

    private static IntervalXYDataset createDataset1() {
        TimeSeries timeseries = new TimeSeries("Series 1");
        timeseries.add(new Day(1, 3, 2002), 12353.299999999999D);
        timeseries.add(new Day(2, 3, 2002), 13734.4D);
        timeseries.add(new Day(3, 3, 2002), 14525.299999999999D);
        timeseries.add(new Day(4, 3, 2002), 13984.299999999999D);
        timeseries.add(new Day(5, 3, 2002), 12999.4D);
        timeseries.add(new Day(6, 3, 2002), 14274.299999999999D);
        timeseries.add(new Day(7, 3, 2002), 15943.5D);
        timeseries.add(new Day(8, 3, 2002), 14845.299999999999D);
        timeseries.add(new Day(9, 3, 2002), 14645.4D);
        timeseries.add(new Day(10, 3, 2002), 16234.6D);
        timeseries.add(new Day(11, 3, 2002), 17232.299999999999D);
        timeseries.add(new Day(12, 3, 2002), 14232.200000000001D);
        timeseries.add(new Day(13, 3, 2002), 13102.200000000001D);
        timeseries.add(new Day(14, 3, 2002), 14230.200000000001D);
        timeseries.add(new Day(15, 3, 2002), 11235.200000000001D);
        return new TimeSeriesCollection(timeseries);
    }

    private static XYDataset createDataset2() {
        TimeSeries timeseries = new TimeSeries("Series 2");
        timeseries.add(new Day(3, 3, 2002), 16853.200000000001D);
        timeseries.add(new Day(4, 3, 2002), 19642.299999999999D);
        timeseries.add(new Day(5, 3, 2002), 18253.5D);
        timeseries.add(new Day(6, 3, 2002), 15352.299999999999D);
        timeseries.add(new Day(7, 3, 2002), 13532D);
        timeseries.add(new Day(8, 3, 2002), 12635.299999999999D);
        timeseries.add(new Day(9, 3, 2002), 13998.200000000001D);
        timeseries.add(new Day(10, 3, 2002), 11943.200000000001D);
        timeseries.add(new Day(11, 3, 2002), 16943.900000000001D);
        timeseries.add(new Day(12, 3, 2002), 17843.200000000001D);
        timeseries.add(new Day(13, 3, 2002), 16495.299999999999D);
        timeseries.add(new Day(14, 3, 2002), 17943.599999999999D);
        timeseries.add(new Day(15, 3, 2002), 18500.700000000001D);
        timeseries.add(new Day(16, 3, 2002), 19595.900000000001D);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeseries);
        return timeseriescollection;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart();
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        OverlaidXYPlotDemo1 overlaidxyplotdemo1 = new OverlaidXYPlotDemo1("Overlaid XYPlot Demo");
        overlaidxyplotdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(overlaidxyplotdemo1);
        overlaidxyplotdemo1.setVisible(true);
    }
}
