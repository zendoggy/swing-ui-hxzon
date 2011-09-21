package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.data.xy.VectorXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class VectorPlotDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public VectorPlotDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static JFreeChart createChart(VectorXYDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin(0.01D);
        xAxis.setUpperMargin(0.01D);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.01D);
        yAxis.setUpperMargin(0.01D);
        yAxis.setAutoRangeIncludesZero(false);
        //
        VectorRenderer renderer = new VectorRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        plot.setOutlinePaint(Color.black);
        JFreeChart chart = new JFreeChart("Vector Plot Demo 1", plot);
        chart.setBackgroundPaint(Color.white);
        return chart;
    }

    private static VectorXYDataset createDataset() {
        VectorSeries vectorseries = new VectorSeries("Series 1");
        for (double d = 0.0D; d < 20D; d++) {
            for (double d1 = 0.0D; d1 < 20D; d1++)
                vectorseries.add(d + 10D, d1 + 10D, Math.sin(d / 5D) / 2D, Math.cos(d1 / 5D) / 2D);

        }

        VectorSeriesCollection dataset = new VectorSeriesCollection();
        dataset.addSeries(vectorseries);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()));
    }

    public static void main(String args[]) {
        VectorPlotDemo1 demo = new VectorPlotDemo1("JFreeChart : Vector Plot Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
