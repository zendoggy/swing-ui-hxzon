// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package org.jfree.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

// Referenced classes of package demo:
//			DemoPanel

public class SlidingCategoryDatasetDemo2 extends ApplicationFrame {
    private static final long serialVersionUID = 1L;

    static class MyDemoPanel extends DemoPanel implements ChangeListener {

        private static final long serialVersionUID = 1L;
        JScrollBar scroller;
        SlidingCategoryDataset dataset;

        private static CategoryDataset createDataset() {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < 50; i++)
                dataset.addValue(Math.random() * 100D, "S1", "S" + i);

            return dataset;
        }

        private static JFreeChart createChart(CategoryDataset dataset) {
            JFreeChart chart = ChartFactory.createBarChart("SlidingCategoryDatasetDemo2", "Series", "Value", dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setMaximumCategoryLabelWidthRatio(0.8F);
            domainAxis.setLowerMargin(0.02D);
            domainAxis.setUpperMargin(0.02D);
            NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
            valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            valueAxis.setRange(0.0D, 100D);//hxzon:when restoreAutoBounds,invalidation
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);
            GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
            renderer.setSeriesPaint(0, gradientpaint);
            return chart;
        }

        public void stateChanged(ChangeEvent changeevent) {
            //SlidingCategoryDataset
            dataset.setFirstCategoryIndex(scroller.getValue());
        }

        public MyDemoPanel() {
            super(new BorderLayout());
            dataset = new SlidingCategoryDataset(createDataset(), 0, 10);
            JFreeChart jfreechart = createChart(dataset);
            addChart(jfreechart);
            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(400, 400));
            scroller = new JScrollBar(0, 0, 10, 0, 50);
            add(chartpanel);
            scroller.getModel().addChangeListener(this);
            JPanel jpanel = new JPanel(new BorderLayout());
            jpanel.add(scroller);
            jpanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            jpanel.setBackground(Color.white);
            add(jpanel, "South");
        }
    }

    public SlidingCategoryDatasetDemo2(String s) {
        super(s);
        setDefaultCloseOperation(3);
        setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String args[]) {
        SlidingCategoryDatasetDemo2 slidingcategorydatasetdemo2 = new SlidingCategoryDatasetDemo2("JFreeChart: SlidingCategoryDatasetDemo2.java");
        slidingcategorydatasetdemo2.pack();
        RefineryUtilities.centerFrameOnScreen(slidingcategorydatasetdemo2);
        slidingcategorydatasetdemo2.setVisible(true);
    }
}
