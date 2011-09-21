package org.jfree.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LevelRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class OverlaidBarChartDemo2 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public OverlaidBarChartDemo2(String s) {
        super(s);
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartpanel);
    }

    private static JFreeChart createChart() {
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1.addValue(1.0D, "S1", "Category 1");
        dataset1.addValue(4D, "S1", "Category 2");
        dataset1.addValue(3D, "S1", "Category 3");
        dataset1.addValue(5D, "S1", "Category 4");
        dataset1.addValue(5D, "S1", "Category 5");
        dataset1.addValue(5D, "S2", "Category 1");
        dataset1.addValue(7D, "S2", "Category 2");
        dataset1.addValue(6D, "S2", "Category 3");
        dataset1.addValue(8D, "S2", "Category 4");
        dataset1.addValue(4D, "S2", "Category 5");
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(6D, "Prior 1", "Category 1");
        dataset2.addValue(7D, "Prior 1", "Category 2");
        dataset2.addValue(2D, "Prior 1", "Category 3");
        dataset2.addValue(6D, "Prior 1", "Category 4");
        dataset2.addValue(6D, "Prior 1", "Category 5");
        dataset2.addValue(4D, "Prior 2", "Category 1");
        dataset2.addValue(2D, "Prior 2", "Category 2");
        dataset2.addValue(1.0D, "Prior 2", "Category 3");
        dataset2.addValue(3D, "Prior 2", "Category 4");
        dataset2.addValue(2D, "Prior 2", "Category 5");
        //
        BarRenderer renderer1 = new BarRenderer();
        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        LevelRenderer renderer2 = new LevelRenderer();
        renderer2.setSeriesStroke(0, new BasicStroke(2.0F));
        renderer2.setSeriesStroke(1, new BasicStroke(2.0F));
        //plot
        CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer1);
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        plot.setDomainAxis(new CategoryAxis("Category"));
        plot.setRangeAxis(new NumberAxis("Value"));
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        //
        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Overlaid Bar Chart 2");
        chart.setBackgroundPaint(Color.white);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart();
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        OverlaidBarChartDemo2 overlaidbarchartdemo2 = new OverlaidBarChartDemo2("Overlaid Bar Chart Demo 2");
        overlaidbarchartdemo2.pack();
        RefineryUtilities.centerFrameOnScreen(overlaidbarchartdemo2);
        overlaidbarchartdemo2.setVisible(true);
    }
}
