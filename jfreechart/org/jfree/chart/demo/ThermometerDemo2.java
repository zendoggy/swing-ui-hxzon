package org.jfree.chart.demo;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ThermometerDemo2 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public ThermometerDemo2(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        setContentPane(jpanel);
    }

    private static JFreeChart createChart() {
        DefaultValueDataset dataset = new DefaultValueDataset(37.200000000000003D);
        ThermometerPlot plot = new ThermometerPlot(dataset);
        JFreeChart chart = new JFreeChart("ThermometerDemo2", plot);
        chart.setBackgroundPaint(Color.white);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String args[]) {
        ThermometerDemo2 demo = new ThermometerDemo2("JFreeChart: ThermometerDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
