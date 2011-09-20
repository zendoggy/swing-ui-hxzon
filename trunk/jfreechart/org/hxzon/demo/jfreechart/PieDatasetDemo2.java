/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * ------------------
 * PieChartDemo1.java
 * ------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * Changes
 * -------
 * 09-Mar-2005 : Version 1, copied from the demo collection that ships with
 *               the JFreeChart Developer Guide (DG);
 *
 */

package org.hxzon.demo.jfreechart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.List;

import org.hxzon.swing.components.easy.HEasyJComboBox;
import org.hxzon.swing.model.HEasyJModelValue;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class PieDatasetDemo2 extends ApplicationFrame {
    private static final long serialVersionUID = 1L;
    //
    private static boolean tooltips = true;
    private static boolean urls = true;
    private static boolean legend = true;
    //dataset
    private static PieDataset dataset = createDataset();
    private static PieDataset previousDataset = createPreviousDataset();
    //chart
    private static JFreeChart pieChart = createPieChart(dataset);
    private static JFreeChart ringChart = createRingChart(dataset);
    private static JFreeChart pieChart3D = createPieChart3D(dataset);
    private static JFreeChart pieChart_2 = createPieChart(dataset, previousDataset);

    public PieDatasetDemo2(String title) {
        super(title);
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.addMouseListener(new MyChartClickHandler(chartPanel));
        chartPanel.setPreferredSize(new Dimension(500, 270));
        getContentPane().add(chartPanel);
        getContentPane().add(new ChartComboBox(chartPanel), BorderLayout.SOUTH);
    }

    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return dataset;
    }

    private static PieDataset createPreviousDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(45.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(20.5));
        dataset.setValue("Four", new Double(12.5));
        dataset.setValue("Five", new Double(31.0));
        dataset.setValue("Six", new Double(19.4));
        return dataset;
    }

    private static JFreeChart createPieChart(PieDataset dataset) {

        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }
        JFreeChart chart = new JFreeChart("Pie Chart Demo 1", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");

        return chart;

    }

    private static JFreeChart createPieChart(PieDataset dataset, PieDataset previousDataset) {
        final boolean greenForIncrease = true;
        final boolean subTitle = true;
        final boolean showDifference = true;
        int percentDiffForMaxScale = 20;
        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));

        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }

        @SuppressWarnings({ "rawtypes", "unchecked" })
        List<Comparable> keys = dataset.getKeys();
        DefaultPieDataset series = null;
        if (showDifference) {
            series = new DefaultPieDataset();
        }

        double colorPerPercent = 255.0 / percentDiffForMaxScale;
        for (@SuppressWarnings("rawtypes")
        Comparable key : keys) {
            Number newValue = dataset.getValue(key);
            Number oldValue = previousDataset.getValue(key);

            if (oldValue == null) {
                if (greenForIncrease) {
                    plot.setSectionPaint(key, Color.green);
                } else {
                    plot.setSectionPaint(key, Color.red);
                }
                if (showDifference) {
                    series.setValue(key + " (+100%)", newValue);
                }
            } else {
                double percentChange = (newValue.doubleValue() / oldValue.doubleValue() - 1.0) * 100.0;
                double shade = (Math.abs(percentChange) >= percentDiffForMaxScale ? 255 : Math.abs(percentChange) * colorPerPercent);
                if (greenForIncrease && newValue.doubleValue() > oldValue.doubleValue() || !greenForIncrease && newValue.doubleValue() < oldValue.doubleValue()) {
                    plot.setSectionPaint(key, new Color(0, (int) shade, 0));
                } else {
                    plot.setSectionPaint(key, new Color((int) shade, 0, 0));
                }
                if (showDifference) {
                    series.setValue(key + " (" + (percentChange >= 0 ? "+" : "") + NumberFormat.getPercentInstance().format(percentChange / 100.0) + ")", newValue);
                }
            }
        }

        if (showDifference) {
            plot.setDataset(series);
        }

        JFreeChart chart = new JFreeChart("Pie Chart Demo 2", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);

        if (subTitle) {
            TextTitle subtitle = null;
            subtitle = new TextTitle("Bright " + (greenForIncrease ? "red" : "green") + "=change >=-" + percentDiffForMaxScale + "%, Bright " + (!greenForIncrease ? "red" : "green") + "=change >=+"
                    + percentDiffForMaxScale + "%", new Font("SansSerif", Font.PLAIN, 10));
            chart.addSubtitle(subtitle);
        }
        plot.setNoDataMessage("No data available");

        return chart;

    }

    private static JFreeChart createPieChart3D(PieDataset dataset) {

        PiePlot3D plot = new PiePlot3D(dataset);
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }
        JFreeChart chart = new JFreeChart("Pie Chart 3D Demo 1", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");

        return chart;

    }

    private static JFreeChart createRingChart(PieDataset dataset) {

        RingPlot plot = new RingPlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }
        JFreeChart chart = new JFreeChart("Ring Chart Demo 1", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");

        return chart;

    }

    public static class ChartComboBox extends HEasyJComboBox<JFreeChart> implements ItemListener {
        private static final long serialVersionUID = 1L;
        private ChartPanel chartPanel;

        public ChartComboBox(ChartPanel chartPanel) {
            this.chartPanel = chartPanel;
            super.addItem(new HEasyJModelValue<JFreeChart>(pieChart, "PieChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(ringChart, "RingChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(pieChart3D, "PieChart3D", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(pieChart_2, "PieChart_2", false));
            this.addItemListener(this);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                @SuppressWarnings("unchecked")
                JFreeChart item = ((HEasyJModelValue<JFreeChart>) e.getItem()).value;
                chartPanel.setChart(item);
            }

        }
    }

    public static void main(String[] args) {

        PieDatasetDemo2 demo = new PieDatasetDemo2("Pie Dataset Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
