/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
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
 * BarChartDemo1.java
 * ------------------
 * (C) Copyright 2003-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * Changes
 * -------
 * 09-Mar-2005 : Version 1 (DG);
 *
 */

package org.hxzon.demo.jfreechart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hxzon.swing.components.easy.HEasyJComboBox;
import org.hxzon.swing.model.HEasyJModelValue;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.labels.HighLowItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.StandardXYZToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.WaferMapPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.WaferMapRenderer;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer2;
import org.jfree.chart.renderer.xy.WindItemRenderer;
import org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.urls.StandardXYZURLGenerator;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.general.WaferMapDataset;
import org.jfree.data.statistics.BoxAndWhiskerCalculator;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.DefaultWindDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class OtherDatasetDemo extends ApplicationFrame {

    private static final long serialVersionUID = 1L;
    //
    private static String xAxisLabel = "Date";
    private static String yAxisLabel = "Price Per Unit";
    private static PlotOrientation orientation = PlotOrientation.VERTICAL;
    private static boolean tooltips = true;
    private static boolean urls = true;
    private static boolean legend = true;
    //dataset
    private static XYZDataset xyzDataset = createXYZDataset();
    private static TableXYDataset tableXyDataset = createTableXYDataset();
    private static OHLCDataset ohlcDataset = createOpenHighLowCloseDataset();
    private static BoxAndWhiskerCategoryDataset boxAndWhiskerCategoryDataset = createBoxAndWhiskerCategoryDataset();
    private static BoxAndWhiskerXYDataset boxAndWhiskerXyDataset = createBoxAndWhiskerXYDataset();
    private static WindDataset windDataset = createWindDataset();
    private static WaferMapDataset waferMapDataset = createWaferMapDataset();
    private static XYDataset polarDataset = createPolarDataset();
    //chart
    private static JFreeChart bubbleChart = createBubbleChart(xyzDataset);
    private static JFreeChart stackedXYAreaChart = createStackedXYAreaChart(tableXyDataset);
    private static JFreeChart candlestickChart = createCandlestickChart(ohlcDataset);
    private static JFreeChart highLowChart = createHighLowChart(ohlcDataset);
    private static JFreeChart boxAndWhiskerCategoryChart = createBoxAndWhiskerChart(boxAndWhiskerCategoryDataset);
    private static JFreeChart boxAndWhiskerXyChart = createBoxAndWhiskerChart(boxAndWhiskerXyDataset);
    private static JFreeChart windChart = createWindChart(windDataset);
    private static JFreeChart waferMapChart = createWaferMapChart(waferMapDataset);
    private static JFreeChart polarChart = createPolarChart(polarDataset);

    public OtherDatasetDemo(String title) {
        super(title);
        ChartPanel chartPanel = new ChartPanel(bubbleChart);
        //have a bug after show tooltips
//        chartPanel.setHorizontalAxisTrace(true);
//        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        getContentPane().add(chartPanel);
        getContentPane().add(new ChartComboBox(chartPanel), BorderLayout.SOUTH);
    }

    private static XYZDataset createXYZDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        //x1,x2,x3,y1,y2,y3,z1,z2,z3=>x1,x2,x3,y1,y2,y3,r1,r2,r3
        double[][] s1 = new double[][] { { 1, 2, 3 }, { 3, 4, 5 }, { 2, 2, 4 } };
        dataset.addSeries("series 1", s1);
        double[][] s2 = new double[][] { { 11, 12 }, { 13, 14 }, { 3, 3 } };
        dataset.addSeries("series 2", s2);
        return dataset;
    }

    private static TableXYDataset createTableXYDataset() {
        boolean notify = false;
        DefaultTableXYDataset dataset = new DefaultTableXYDataset();
//        XYSeries(Comparable key, boolean autoSort,
//                boolean allowDuplicateXValues)
        XYSeries series1 = new XYSeries("series 1", false, false);
        series1.add(1, 1, notify);
        series1.add(2, 2, notify);
        series1.add(3, 3, notify);
        dataset.addSeries(series1);
        XYSeries series2 = new XYSeries("series 2", false, false);
        series2.add(11, 1, notify);
        series2.add(12, 2, notify);
        series2.add(13, 3, notify);
        dataset.addSeries(series2);
        return dataset;
    }

    private static OHLCDataset createOpenHighLowCloseDataset() {
        OHLCDataItem[] data = new OHLCDataItem[3];
//        OHLCDataItem(Date date,
//                double open,
//                double high,
//                double low,
//                double close,
//                double volume)
        long time = new Date().getTime();
        data[0] = new OHLCDataItem(new Date(time + 10000), 20, 20, 0, 0, 5);
        data[1] = new OHLCDataItem(new Date(time + 30000), 22, 22, 2, 2, 5);
        data[2] = new OHLCDataItem(new Date(time + 60000), 25, 20, 3, 0, 5);
        DefaultOHLCDataset dataset = new DefaultOHLCDataset("data", data);
        return dataset;
    }

    private static BoxAndWhiskerCategoryDataset createBoxAndWhiskerCategoryDataset() {
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        List<Integer> data1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        dataset.add(data1, "row 1", "col 1");
        List<Integer> data2 = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        dataset.add(data2, "row 1", "col 2");
        List<Integer> data3 = Arrays.asList(1, 12, 3, 14, 5, 16, 7, 8, 19, 10);
        dataset.add(data3, "row 2", "col 1");
        return dataset;
    }

    private static BoxAndWhiskerXYDataset createBoxAndWhiskerXYDataset() {
        DefaultBoxAndWhiskerXYDataset dataset = new DefaultBoxAndWhiskerXYDataset("series 1");
        long time = new Date().getTime();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        BoxAndWhiskerItem item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(list);
        dataset.add(new Date(time + 10000), item);
        list = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(list);
        dataset.add(new Date(time + 30000), item);
        list = Arrays.asList(1, 12, 3, 14, 5, 16, 7, 8, 19, 10);
        item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(list);
        dataset.add(new Date(time + 60000), item);
        return dataset;
    }

    private static WindDataset createWindDataset() {
        String[] seriesNames = new String[] { "series 1", "series 2", "series 3" };
        //xNumber, windDir,windForce
//        the wind direction .  This is a
//        number between 0 and 12, like the numbers on an upside-down clock face.
//        the wind force for one item within a series.  This is a number
//        between 0 and 12, as defined by the Beaufort scale.
        long time = new Date().getTime();
        Object[][] series1 = new Object[][] { { time + 1000, 2, 3 }, { time + 2000, 4, 9 }, { time + 3000, 6, 6 } };
        Object[][] series2 = new Object[][] { { time + 4000, 12, 12 }, { time + 5000, 0, 3 }, { time + 6000, 8, 11 } };
        Object[][] series3 = new Object[][] { { time + 7000, 6, 8 }, { time + 8000, 9, 1 }, { time + 9000, 8, 3 } };
        Object[][][] data = new Object[][][] { series1, series2, series3 };
        DefaultWindDataset dataset = new DefaultWindDataset(seriesNames, data);
        return dataset;
    }

    //晶片图
    private static WaferMapDataset createWaferMapDataset() {
        WaferMapDataset dataset = new WaferMapDataset(50, 50);
        dataset.addValue(20, 19, 21);
        dataset.addValue(21, 22, 20);
        dataset.addValue(32, 40, 40);
        dataset.addValue(33, 33, 33);
        dataset.addValue(34, 34, 34);
        dataset.addValue(35, 35, 35);
        dataset.addValue(36, 36, 36);
        return dataset;
    }

    private static XYDataset createPolarDataset() {
        DefaultXYDataset dataset = new DefaultXYDataset();
        //{{theta1,theta2,theta3, },{radius1,radius2,radius3,}}
        double[][] series1 = new double[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 },
                { 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117 }, };

        double[][] series2 = new double[][] { { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170 },
                { 129.6, 123.2, 117.2, 124.1, 122.6, 119.2, 116.5, 112.7, 101.5, 106.1, 110.3, 111.7, 111.0, 109.6, 113.2, 111.6, 108.8, 101.6 }, };
        dataset.addSeries("series 1", series1);
        dataset.addSeries("series 2", series2);

        return dataset;
    }

    //chart------------------------------

    private static JFreeChart createBubbleChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
//        A renderer that draws a circle at each data point with a diameter that is
//        determined by the z-value in the dataset (the renderer requires the dataset
//        to be an instance of {@link XYZDataset}.
        XYItemRenderer renderer = new XYBubbleRenderer(XYBubbleRenderer.SCALE_ON_RANGE_AXIS);
        if (tooltips) {
            renderer.setBaseToolTipGenerator(new StandardXYZToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYZURLGenerator());
        }
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);

        JFreeChart chart = new JFreeChart("Bubble Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        return chart;
    }

    private static JFreeChart createStackedXYAreaChart(TableXYDataset dataset) {
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2(toolTipGenerator, urlGenerator);
        renderer.setOutline(true);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);

        plot.setRangeAxis(yAxis); // forces recalculation of the axis range

        JFreeChart chart = new JFreeChart("StackedXYArea Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        return chart;
    }

    //烛台, 蜡烛架
    private static JFreeChart createCandlestickChart(OHLCDataset dataset) {
        ValueAxis timeAxis = new DateAxis(xAxisLabel);
        NumberAxis valueAxis = new NumberAxis(yAxisLabel);
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        plot.setRenderer(new CandlestickRenderer());
        JFreeChart chart = new JFreeChart("Candlestick Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        return chart;
    }

    private static JFreeChart createHighLowChart(OHLCDataset dataset) {
        ValueAxis timeAxis = new DateAxis(xAxisLabel);
        NumberAxis valueAxis = new NumberAxis(yAxisLabel);
        HighLowRenderer renderer = new HighLowRenderer();
        renderer.setBaseToolTipGenerator(new HighLowItemLabelGenerator());
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        JFreeChart chart = new JFreeChart("HighLow Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        return chart;
    }

    private static JFreeChart createBoxAndWhiskerChart(BoxAndWhiskerCategoryDataset dataset) {
        CategoryAxis categoryAxis = new CategoryAxis(xAxisLabel);
        NumberAxis valueAxis = new NumberAxis(yAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);

        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setBaseToolTipGenerator(new BoxAndWhiskerToolTipGenerator());

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        JFreeChart chart = new JFreeChart("BoxAndWhiskerCategory Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        return chart;
    }

    private static JFreeChart createBoxAndWhiskerChart(BoxAndWhiskerXYDataset dataset) {
        ValueAxis timeAxis = new DateAxis(xAxisLabel);
        NumberAxis valueAxis = new NumberAxis(yAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);
        XYBoxAndWhiskerRenderer renderer = new XYBoxAndWhiskerRenderer(10.0);
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        JFreeChart chart = new JFreeChart("BoxAndWhiskerXY Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        return chart;
    }

    private static JFreeChart createWaferMapChart(WaferMapDataset dataset) {
        WaferMapPlot plot = new WaferMapPlot(dataset);
        WaferMapRenderer renderer = new WaferMapRenderer();
        plot.setRenderer(renderer);

        JFreeChart chart = new JFreeChart("WaferMap Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);

        return chart;
    }

    private static JFreeChart createWindChart(WindDataset dataset) {
        ValueAxis xAxis = new DateAxis(xAxisLabel);
        ValueAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setRange(-12.0, 12.0);

        WindItemRenderer renderer = new WindItemRenderer();
        if (tooltips) {
            renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        JFreeChart chart = new JFreeChart("Wind Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        return chart;
    }

    //极坐标
    private static JFreeChart createPolarChart(XYDataset dataset) {

        PolarPlot plot = new PolarPlot();
        plot.setDataset(dataset);
        NumberAxis rangeAxis = new NumberAxis();
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        plot.setAxis(rangeAxis);
        plot.setRenderer(new DefaultPolarItemRenderer());
        JFreeChart chart = new JFreeChart("Polar Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);

        plot.setAngleGridlinesVisible(true);
        plot.setAngleLabelsVisible(true);
        plot.setAngleTickUnit(new NumberTickUnit(5));

        return chart;
    }

    public static class ChartComboBox extends HEasyJComboBox<JFreeChart> implements ItemListener {
        private static final long serialVersionUID = 1L;
        private ChartPanel chartPanel;

        public ChartComboBox(ChartPanel chartPanel) {
            this.chartPanel = chartPanel;
            super.addItem(new HEasyJModelValue<JFreeChart>(bubbleChart, "BubbleChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(stackedXYAreaChart, "StackedXYAreaChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(candlestickChart, "CandlestickChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(highLowChart, "HighLowChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(boxAndWhiskerCategoryChart, "BoxAndWhiskerCategoryChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(boxAndWhiskerXyChart, "BoxAndWhiskerXYChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(windChart, "WindChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(waferMapChart, "WaferMapChart", false));
            super.addItem(new HEasyJModelValue<JFreeChart>(polarChart, "PolarChart", false));
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
        OtherDatasetDemo demo = new OtherDatasetDemo("Other Dataset Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
