package org.jfree.chart.demo;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CombinedCategoryPlotDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public CombinedCategoryPlotDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    public static CategoryDataset createDataset1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String s = "First";
        String s1 = "Second";
        String s2 = "Type 1";
        String s3 = "Type 2";
        String s4 = "Type 3";
        String s5 = "Type 4";
        String s6 = "Type 5";
        String s7 = "Type 6";
        String s8 = "Type 7";
        String s9 = "Type 8";
        dataset.addValue(1.0D, s, s2);
        dataset.addValue(4D, s, s3);
        dataset.addValue(3D, s, s4);
        dataset.addValue(5D, s, s5);
        dataset.addValue(5D, s, s6);
        dataset.addValue(7D, s, s7);
        dataset.addValue(7D, s, s8);
        dataset.addValue(8D, s, s9);
        dataset.addValue(5D, s1, s2);
        dataset.addValue(7D, s1, s3);
        dataset.addValue(6D, s1, s4);
        dataset.addValue(8D, s1, s5);
        dataset.addValue(4D, s1, s6);
        dataset.addValue(4D, s1, s7);
        dataset.addValue(2D, s1, s8);
        dataset.addValue(1.0D, s1, s9);
        return dataset;
    }

    public static CategoryDataset createDataset2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String s = "Third";
        String s1 = "Fourth";
        String s2 = "Type 1";
        String s3 = "Type 2";
        String s4 = "Type 3";
        String s5 = "Type 4";
        String s6 = "Type 5";
        String s7 = "Type 6";
        String s8 = "Type 7";
        String s9 = "Type 8";
        dataset.addValue(11D, s, s2);
        dataset.addValue(14D, s, s3);
        dataset.addValue(13D, s, s4);
        dataset.addValue(15D, s, s5);
        dataset.addValue(15D, s, s6);
        dataset.addValue(17D, s, s7);
        dataset.addValue(17D, s, s8);
        dataset.addValue(18D, s, s9);
        dataset.addValue(15D, s1, s2);
        dataset.addValue(17D, s1, s3);
        dataset.addValue(16D, s1, s4);
        dataset.addValue(18D, s1, s5);
        dataset.addValue(14D, s1, s6);
        dataset.addValue(14D, s1, s7);
        dataset.addValue(12D, s1, s8);
        dataset.addValue(11D, s1, s9);
        return dataset;
    }

    private static JFreeChart createChart() {
        CategoryDataset dataset1 = createDataset1();
        NumberAxis valueAxis1 = new NumberAxis("Value");
        valueAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot plot1 = new CategoryPlot(dataset1, null, valueAxis1, renderer1);
        plot1.setDomainGridlinesVisible(true);
        //
        CategoryDataset dataset2 = createDataset2();
        NumberAxis valueAxis2 = new NumberAxis("Value");
        valueAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer2 = new BarRenderer();
        renderer2.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot plot2 = new CategoryPlot(dataset2, null, valueAxis2, renderer2);
        plot2.setDomainGridlinesVisible(true);
        //
        CategoryAxis domainAxis = new CategoryAxis("Category");
        CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot(domainAxis);
        combinedPlot.add(plot1, 2);
        combinedPlot.add(plot2, 1);
        JFreeChart chart = new JFreeChart("Combined Domain Category Plot Demo", new Font("SansSerif", 1, 12), combinedPlot, true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart();
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        String s = "Combined Category Plot Demo 1";
        CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(s);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
