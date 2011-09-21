package org.jfree.chart.demo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

public class ThermometerDemo1 extends ApplicationFrame {
    private static final long serialVersionUID = 1L;

    static class ContentPanel extends JPanel implements ChangeListener {

        private static final long serialVersionUID = 1L;

        private static JFreeChart createChart(ValueDataset dataset) {
            ThermometerPlot plot = new ThermometerPlot(dataset);
            JFreeChart chart = new JFreeChart("Thermometer Demo 1", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            plot.setInsets(new RectangleInsets(5D, 5D, 5D, 5D));
            plot.setPadding(new RectangleInsets(10D, 10D, 10D, 10D));
            plot.setThermometerStroke(new BasicStroke(2.0F));
            plot.setThermometerPaint(Color.lightGray);
            plot.setUnits(1);
            return chart;
        }

        public void stateChanged(ChangeEvent changeevent) {
            dataset.setValue(new Integer(slider.getValue()));
        }

        JSlider slider;
        DefaultValueDataset dataset;

        public ContentPanel() {
            super(new BorderLayout());
            slider = new JSlider(0, 100, 50);
            slider.setPaintLabels(true);
            slider.setPaintTicks(true);
            slider.setMajorTickSpacing(25);
            slider.addChangeListener(this);
            add(slider, "South");
            dataset = new DefaultValueDataset(slider.getValue());
            add(new ChartPanel(createChart(dataset)));
        }
    }

    public ThermometerDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        setContentPane(jpanel);
    }

    public static JPanel createDemoPanel() {
        return new ContentPanel();
    }

    public static void main(String args[]) {
        ThermometerDemo1 demo = new ThermometerDemo1("Thermometer Demo 1");
        demo.pack();
        demo.setVisible(true);
    }
}
