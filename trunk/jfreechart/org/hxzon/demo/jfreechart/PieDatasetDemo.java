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
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.hxzon.swing.components.easy.HEasyJComboBox;
import org.hxzon.swing.model.HEasyJModelValue;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieDatasetDemo extends ApplicationFrame {
    private static final long serialVersionUID = 1L;
    //dataset
    private static PieDataset dataset = createDataset();
    private static PieDataset previousDataset = createPreviousDataset();
    //chart
    private static JFreeChart pieChart = createPieChart(dataset);
    private static JFreeChart ringChart = createRingChart(dataset);
    private static JFreeChart pieChart3D = createPieChart3D(dataset);
    private static JFreeChart pieChart_2 = createPieChart(dataset, previousDataset);

    public PieDatasetDemo(String title) {
        super(title);
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
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

        JFreeChart chart = ChartFactory.createPieChart("Pie Chart Demo 1", // chart title
                dataset, // data
                true, // include legend
                true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");

        return chart;

    }

    private static JFreeChart createPieChart(PieDataset dataset, PieDataset previousDataset) {
//        createPieChart(String title,
//                PieDataset dataset,
//                PieDataset previousDataset,
//                int percentDiffForMaxScale,
//                boolean greenForIncrease,
//                boolean legend,
//                boolean tooltips,
//                boolean urls,
//                boolean subTitle,
//                boolean showDifference)
        JFreeChart chart = ChartFactory.createPieChart("Pie Chart Demo 2", // chart title
                dataset, // data
                previousDataset, 0,// percentDiffForMaxScale
                true, // greenForIncrease
                true, // include legend
                true, // tooltips
                false, // urls
                true, // subTitle
                true); // showDifference

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available");

        return chart;

    }

    private static JFreeChart createPieChart3D(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart3D("Pie Chart 3D Demo 1", // chart title
                dataset, // data
                true, // include legend
                true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");

        return chart;

    }

    private static JFreeChart createRingChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createRingChart("Ring Chart Demo 1", // chart title
                dataset, // data
                true, // include legend
                true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
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

        PieDatasetDemo demo = new PieDatasetDemo("Pie Dataset Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
