package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class SubCategoryAxisDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public SubCategoryAxisDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static CategoryDataset createDataset() {
        String series1 = "S1";
        String series2 = "S2";
        String series3 = "S3";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0D, series1, category1);
        dataset.addValue(4D, series1, category2);
        dataset.addValue(3D, series1, category3);
        dataset.addValue(5D, series1, category4);
        dataset.addValue(5D, series1, category5);
        dataset.addValue(5D, series2, category1);
        dataset.addValue(7D, series2, category2);
        dataset.addValue(6D, series2, category3);
        dataset.addValue(8D, series2, category4);
        dataset.addValue(4D, series2, category5);
        dataset.addValue(4D, series3, category1);
        dataset.addValue(3D, series3, category2);
        dataset.addValue(2D, series3, category3);
        dataset.addValue(3D, series3, category4);
        dataset.addValue(6D, series3, category5);
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("SubCategoryAxis Demo 1", "Category", "Value", dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        SubCategoryAxis subCategoryAxis = new SubCategoryAxis(null);
        subCategoryAxis.addSubCategory("S1");
        subCategoryAxis.addSubCategory("S2");
        subCategoryAxis.addSubCategory("S3");
        plot.setDomainAxis(subCategoryAxis);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
        valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        GradientPaint gradientpaint0 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gradientpaint0);
        renderer.setSeriesPaint(1, gradientpaint1);
        renderer.setSeriesPaint(2, gradientpaint2);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String args[]) {
        SubCategoryAxisDemo1 demo = new SubCategoryAxisDemo1("JFreeChart: SubCategoryAxisDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
