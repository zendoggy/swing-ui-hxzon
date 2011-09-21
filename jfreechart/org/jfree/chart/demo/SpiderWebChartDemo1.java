package org.jfree.chart.demo;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

public class SpiderWebChartDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public SpiderWebChartDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static CategoryDataset createDataset() {
        String s = "First";
        String s1 = "Second";
        String s2 = "Third";
        String s3 = "Category 1";
        String s4 = "Category 2";
        String s5 = "Category 3";
        String s6 = "Category 4";
        String s7 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0D, s, s3);
        dataset.addValue(4D, s, s4);
        dataset.addValue(3D, s, s5);
        dataset.addValue(5D, s, s6);
        dataset.addValue(5D, s, s7);
        dataset.addValue(5D, s1, s3);
        dataset.addValue(7D, s1, s4);
        dataset.addValue(6D, s1, s5);
        dataset.addValue(8D, s1, s6);
        dataset.addValue(4D, s1, s7);
        dataset.addValue(4D, s2, s3);
        dataset.addValue(3D, s2, s4);
        dataset.addValue(2D, s2, s5);
        dataset.addValue(3D, s2, s6);
        dataset.addValue(6D, s2, s7);
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset categorydataset) {
        SpiderWebPlot plot = new SpiderWebPlot(categorydataset);
        plot.setStartAngle(54D);
        plot.setInteriorGap(0.40000000000000002D);
        plot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        JFreeChart chart = new JFreeChart("Spider Web Chart Demo 1", TextTitle.DEFAULT_FONT, plot, false);
        LegendTitle legendtitle = new LegendTitle(plot);
        legendtitle.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legendtitle);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        SpiderWebChartDemo1 spiderwebchartdemo1 = new SpiderWebChartDemo1("SpiderWebChartDemo1");
        spiderwebchartdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(spiderwebchartdemo1);
        spiderwebchartdemo1.setVisible(true);
    }

}
