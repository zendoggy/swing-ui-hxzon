package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

public class XYSeriesDemo3 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public XYSeriesDemo3(String s) {
        super(s);
        IntervalXYDataset intervalxydataset = createDataset();
        JFreeChart jfreechart = createChart(intervalxydataset);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartpanel);
    }

    private static IntervalXYDataset createDataset() {
        XYSeries xyseries = new XYSeries("Random Data");
        xyseries.add(1.0D, 400.19999999999999D);
        xyseries.add(5D, 294.10000000000002D);
        xyseries.add(4D, 100D);
        xyseries.add(12.5D, 734.39999999999998D);
        xyseries.add(17.300000000000001D, 453.19999999999999D);
        xyseries.add(21.199999999999999D, 500.19999999999999D);
        xyseries.add(21.899999999999999D, null);
        xyseries.add(25.600000000000001D, 734.39999999999998D);
        xyseries.add(30D, 453.19999999999999D);
        XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);
        return xyseriescollection;
    }

    private static JFreeChart createChart(IntervalXYDataset intervalxydataset) {
        JFreeChart jfreechart = ChartFactory.createXYBarChart("XY Series Demo", "X", false, "Y", intervalxydataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        IntervalMarker intervalmarker = new IntervalMarker(400D, 700D);
        intervalmarker.setLabel("Target Range");
        intervalmarker.setLabelFont(new Font("SansSerif", 2, 11));
        intervalmarker.setLabelAnchor(RectangleAnchor.LEFT);
        intervalmarker.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        intervalmarker.setPaint(new Color(222, 222, 255, 128));
        xyplot.addRangeMarker(intervalmarker, Layer.BACKGROUND);
        return jfreechart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        XYSeriesDemo3 xyseriesdemo3 = new XYSeriesDemo3("XY Series Demo 3");
        xyseriesdemo3.pack();
        RefineryUtilities.centerFrameOnScreen(xyseriesdemo3);
        xyseriesdemo3.setVisible(true);
    }
}
