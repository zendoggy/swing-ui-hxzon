package org.jfree.chart.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CombinedTimeSeriesDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public CombinedTimeSeriesDemo1(String s) {
        super(s);
        setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        TimeSeries timeseries = new TimeSeries("Annual");
        timeseries.add(new Year(1998), 80D);
        timeseries.add(new Year(1999), 85D);
        timeseries.add(new Year(2000), 87.599999999999994D);
        TimeSeriesCollection dataset1 = new TimeSeriesCollection(timeseries);
        TimeSeries timeseries1 = new TimeSeries("Monthly A");
        timeseries1.add(new Month(7, 2000), 85.799999999999997D);
        timeseries1.add(new Month(8, 2000), 85.799999999999997D);
        timeseries1.add(new Month(9, 2000), 85.799999999999997D);
        timeseries1.add(new Month(10, 2000), 86.5D);
        timeseries1.add(new Month(11, 2000), 86.5D);
        timeseries1.add(new Month(12, 2000), 86.5D);
        timeseries1.add(new Month(1, 2001), 87.700000000000003D);
        timeseries1.add(new Month(2, 2001), 87.700000000000003D);
        timeseries1.add(new Month(3, 2001), 87.700000000000003D);
        timeseries1.add(new Month(4, 2001), 88.5D);
        timeseries1.add(new Month(5, 2001), 88.5D);
        timeseries1.add(new Month(6, 2001), 88.5D);
        timeseries1.add(new Month(7, 2001), 90D);
        timeseries1.add(new Month(8, 2001), 90D);
        timeseries1.add(new Month(9, 2001), 90D);
        timeseries1.add(new Month(10, 2001), 90D);
        timeseries1.add(new Month(11, 2001), 90D);
        timeseries1.add(new Month(12, 2001), 90D);
        timeseries1.add(new Month(1, 2002), 90D);
        timeseries1.add(new Month(2, 2002), 90D);
        timeseries1.add(new Month(3, 2002), 90D);
        timeseries1.add(new Month(4, 2002), 90D);
        timeseries1.add(new Month(5, 2002), 90D);
        timeseries1.add(new Month(6, 2002), 90D);
        TimeSeries timeseries2 = new TimeSeries("Monthly B");
        timeseries2.add(new Month(7, 2000), 83.799999999999997D);
        timeseries2.add(new Month(8, 2000), 83.799999999999997D);
        timeseries2.add(new Month(9, 2000), 83.799999999999997D);
        timeseries2.add(new Month(10, 2000), 84.5D);
        timeseries2.add(new Month(11, 2000), 84.5D);
        timeseries2.add(new Month(12, 2000), 84.5D);
        timeseries2.add(new Month(1, 2001), 85.700000000000003D);
        timeseries2.add(new Month(2, 2001), 85.700000000000003D);
        timeseries2.add(new Month(3, 2001), 85.700000000000003D);
        timeseries2.add(new Month(4, 2001), 86.5D);
        timeseries2.add(new Month(5, 2001), 86.5D);
        timeseries2.add(new Month(6, 2001), 86.5D);
        timeseries2.add(new Month(7, 2001), 88D);
        timeseries2.add(new Month(8, 2001), 88D);
        timeseries2.add(new Month(9, 2001), 88D);
        timeseries2.add(new Month(10, 2001), 88D);
        timeseries2.add(new Month(11, 2001), 88D);
        timeseries2.add(new Month(12, 2001), 88D);
        timeseries2.add(new Month(1, 2002), 88D);
        timeseries2.add(new Month(2, 2002), 88D);
        timeseries2.add(new Month(3, 2002), 88D);
        timeseries2.add(new Month(4, 2002), 88D);
        timeseries2.add(new Month(5, 2002), 88D);
        timeseries2.add(new Month(6, 2002), 88D);
        TimeSeriesCollection dataset21 = new TimeSeriesCollection();
        dataset21.addSeries(timeseries1);
        dataset21.addSeries(timeseries2);
        TimeSeries timeseries3 = new TimeSeries("XXX");
        timeseries3.add(new Month(7, 2000), 81.5D);
        timeseries3.add(new Month(8, 2000), 86D);
        timeseries3.add(new Month(9, 2000), 82D);
        timeseries3.add(new Month(10, 2000), 89.5D);
        timeseries3.add(new Month(11, 2000), 88D);
        timeseries3.add(new Month(12, 2000), 88D);
        timeseries3.add(new Month(1, 2001), 90D);
        timeseries3.add(new Month(2, 2001), 89.5D);
        timeseries3.add(new Month(3, 2001), 90.200000000000003D);
        timeseries3.add(new Month(4, 2001), 90.599999999999994D);
        timeseries3.add(new Month(5, 2001), 87.5D);
        timeseries3.add(new Month(6, 2001), 91D);
        timeseries3.add(new Month(7, 2001), 89.700000000000003D);
        timeseries3.add(new Month(8, 2001), 87D);
        timeseries3.add(new Month(9, 2001), 91.200000000000003D);
        timeseries3.add(new Month(10, 2001), 84D);
        timeseries3.add(new Month(11, 2001), 90D);
        timeseries3.add(new Month(12, 2001), 92D);
        TimeSeriesCollection dataset22 = new TimeSeriesCollection(timeseries3);
        //
        XYBarRenderer renderer1 = new XYBarRenderer(0.20000000000000001D);
        renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0} ({1}, {2})", new SimpleDateFormat("yyyy"), new DecimalFormat("0.00")));
        XYPlot plot1 = new XYPlot(dataset1, new DateAxis("Date"), null, renderer1);
        //
        XYAreaRenderer renderer21 = new XYAreaRenderer();
        XYPlot plot2 = new XYPlot(dataset21, new DateAxis("Date"), null, renderer21);
        StandardXYItemRenderer renderer22 = new StandardXYItemRenderer(3);
        renderer22.setBaseShapesFilled(true);
        plot2.setDataset(1, dataset22);
        plot2.setRenderer(1, renderer22);
        plot2.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        //
        NumberAxis valueAxis = new NumberAxis("Value");
        valueAxis.setAutoRangeIncludesZero(false);
        CombinedRangeXYPlot combinedPlot = new CombinedRangeXYPlot(valueAxis);
        combinedPlot.add(plot1, 1);
        combinedPlot.add(plot2, 4);
        //chart
        JFreeChart jfreechart = new JFreeChart("Sample Combined Plot", JFreeChart.DEFAULT_TITLE_FONT, combinedPlot, true);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        chartpanel.addChartMouseListener(new ChartMouseListener() {

            public void chartMouseClicked(ChartMouseEvent chartmouseevent) {
                System.out.println(chartmouseevent.getEntity());
            }

            public void chartMouseMoved(ChartMouseEvent chartmouseevent) {
                System.out.println(chartmouseevent.getEntity());
            }

        });
        return chartpanel;
    }

    public static void main(String args[]) {
        CombinedTimeSeriesDemo1 combinedtimeseriesdemo1 = new CombinedTimeSeriesDemo1("JFreeChart: Combined Time Series Demo");
        combinedtimeseriesdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(combinedtimeseriesdemo1);
        combinedtimeseriesdemo1.setVisible(true);
    }

}
