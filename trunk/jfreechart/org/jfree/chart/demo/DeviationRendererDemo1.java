package org.jfree.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class DeviationRendererDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public DeviationRendererDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static XYDataset createDataset() {
        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        YIntervalSeries series2 = new YIntervalSeries("Series 2");
        double d = 100D;
        double d1 = 100D;
        for (int i = 0; i <= 100; i++) {
            d = (d + Math.random()) - 0.47999999999999998D;
            double d2 = 0.050000000000000003D * (double) i;
            series1.add(i, d, d - d2, d + d2);
            d1 = (d1 + Math.random()) - 0.5D;
            double d3 = 0.070000000000000007D * (double) i;
            series2.add(i, d1, d1 - d3, d1 + d3);
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    private static JFreeChart createChart(XYDataset xydataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("DeviationRenderer - Demo 1", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        //A specialised subclass of the {@link XYLineAndShapeRenderer} that requires
        // an {@link IntervalXYDataset} and represents the y-interval by shading an
        // area behind the y-values on the chart.
        DeviationRenderer renderer = new DeviationRenderer(true, false);
        renderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
        renderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
        renderer.setSeriesStroke(1, new BasicStroke(3F, 1, 1));
        renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
        plot.setRenderer(renderer);
        //
        NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
        valueAxis.setAutoRangeIncludesZero(false);
        valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        DeviationRendererDemo1 deviationrendererdemo1 = new DeviationRendererDemo1("JFreeChart : DeviationRendererDemo1");
        deviationrendererdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(deviationrendererdemo1);
        deviationrendererdemo1.setVisible(true);
    }
}
