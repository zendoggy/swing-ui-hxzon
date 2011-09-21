// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package org.jfree.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DeviationRendererDemo3 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public DeviationRendererDemo3(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static XYDataset createDataset() {
        YIntervalSeries series1 = new YIntervalSeries("Band A");
        YIntervalSeries series2 = new YIntervalSeries("Band B");
        YIntervalSeries series3 = new YIntervalSeries("Band C");
        Object obj = new Quarter(1, 2005);
        double d = 0.0D;
        for (int i = 0; i <= 12; i++) {
            d += (Math.random() - 0.5D) * 15D;
            series1.add(((RegularTimePeriod) (obj)).getMiddleMillisecond(), d, d + 10D, Math.max(50D, d + 30D));
            series2.add(((RegularTimePeriod) (obj)).getMiddleMillisecond(), d, d - 10D, d + 10D);
            series3.add(((RegularTimePeriod) (obj)).getMiddleMillisecond(), d, Math.min(-50D, d - 30D), d - 10D);
            obj = ((RegularTimePeriod) (obj)).next();
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        return dataset;
    }

    private static JFreeChart createChart(XYDataset xydataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("DeviationRenderer - Demo 3", "X", "Y", xydataset, PlotOrientation.VERTICAL, false, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        //
        DeviationRenderer renderer = new DeviationRenderer(false, false);
        renderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
        renderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
        renderer.setSeriesStroke(1, new BasicStroke(3F, 1, 1));
        renderer.setSeriesFillPaint(0, Color.red);
        renderer.setSeriesFillPaint(1, Color.orange);
        renderer.setSeriesFillPaint(2, Color.green);
        plot.setRenderer(renderer);
        //
        DateAxis domainAxis = new DateAxis("Date");
        domainAxis.setLowerMargin(0.0D);
        domainAxis.setUpperMargin(0.0D);
        plot.setDomainAxis(domainAxis);
        NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
        valueAxis.setRange(-40D, 40D);
        valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        DeviationRendererDemo3 deviationrendererdemo3 = new DeviationRendererDemo3("JFreeChart : DeviationRendererDemo3.java");
        deviationrendererdemo3.pack();
        RefineryUtilities.centerFrameOnScreen(deviationrendererdemo3);
        deviationrendererdemo3.setVisible(true);
    }
}
