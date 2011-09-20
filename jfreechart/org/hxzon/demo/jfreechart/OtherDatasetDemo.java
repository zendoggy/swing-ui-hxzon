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
import java.util.ArrayList;
import java.util.Calendar;
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
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.DefaultWindDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
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
        chartPanel.addMouseListener(new MyChartClickHandler(chartPanel));
        chartPanel.setPreferredSize(new Dimension(500, 270));
        getContentPane().add(chartPanel);
        getContentPane().add(new ChartComboBox(chartPanel), BorderLayout.SOUTH);
    }

    private static XYZDataset createXYZDataset() {
//        DefaultXYZDataset dataset = new DefaultXYZDataset();
//        //x1,x2,x3,y1,y2,y3,z1,z2,z3=>x1,x2,x3,y1,y2,y3,r1,r2,r3
//        double[][] s1 = new double[][] { { 1, 2, 3 }, { 3, 4, 5 }, { 2, 2, 4 } };
//        dataset.addSeries("series 1", s1);
//        double[][] s2 = new double[][] { { 11, 12 }, { 13, 14 }, { 3, 3 } };
//        dataset.addSeries("series 2", s2);
//        return dataset;
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double ad[] = { 2.1000000000000001D, 2.2999999999999998D, 2.2999999999999998D, 2.2000000000000002D, 2.2000000000000002D, 1.8D, 1.8D, 1.8999999999999999D, 2.2999999999999998D,
                3.7999999999999998D };
        double ad1[] = { 14.1D, 11.1D, 10D, 8.8000000000000007D, 8.6999999999999993D, 8.4000000000000004D, 5.4000000000000004D, 4.0999999999999996D, 4.0999999999999996D, 25D };
        double ad2[] = { 2.3999999999999999D, 2.7000000000000002D, 2.7000000000000002D, 2.2000000000000002D, 2.2000000000000002D, 2.2000000000000002D, 2.1000000000000001D, 2.2000000000000002D,
                1.6000000000000001D, 4D };
        double ad3[][] = { ad, ad1, ad2 };
        dataset.addSeries("Series 1", ad3);
        return dataset;
    }

    private static TableXYDataset createTableXYDataset() {
        //DefaultTableXYDataset
//        boolean notify = false;
//        boolean autoSort = true;//must autoSort?
//        DefaultTableXYDataset dataset = new DefaultTableXYDataset();
////        XYSeries(Comparable key, boolean autoSort,
////                boolean allowDuplicateXValues)
//        XYSeries series1 = new XYSeries("series 1", autoSort, false);
//        series1.add(1, 1, notify);
//        series1.add(2, 2, notify);
//        series1.add(3, 3, notify);
//        dataset.addSeries(series1);
//        XYSeries series2 = new XYSeries("series 2", autoSort, false);
//        series2.add(12, 1, notify);
//        series2.add(13, 2, notify);
//        series2.add(14, 3, notify);
//        dataset.addSeries(series2);
//        return dataset;
        //CategoryTableXYDataset
//        CategoryTableXYDataset categorytablexydataset = new CategoryTableXYDataset();
//        categorytablexydataset.add(0.0D, 0.0D, "Series 1");
//        categorytablexydataset.add(10D, 20D, "Series 1");
//        categorytablexydataset.add(20D, 15D, "Series 1");
//        categorytablexydataset.add(30D, 25D, "Series 1");
//        categorytablexydataset.add(40D, 21D, "Series 1");
//        categorytablexydataset.add(10D, 9D, "Series 2");
//        categorytablexydataset.add(20D, -7D, "Series 2");
//        categorytablexydataset.add(30D, 15D, "Series 2");
//        categorytablexydataset.add(40D, 11D, "Series 2");
//        categorytablexydataset.add(45D, -10D, "Series 2");
//        categorytablexydataset.add(50D, 0.0D, "Series 2");
//        return categorytablexydataset;
        //TimeTableXYDataset
        TimeTableXYDataset timetablexydataset = new TimeTableXYDataset();
        timetablexydataset.add(new Day(14, 2, 2007), 87D, "Series 1");
        timetablexydataset.add(new Day(15, 2, 2007), 67D, "Series 1");
        timetablexydataset.add(new Day(16, 2, 2007), 78D, "Series 1");
        timetablexydataset.add(new Day(17, 2, 2007), 55D, "Series 1");
        timetablexydataset.add(new Day(18, 2, 2007), 58D, "Series 1");
        timetablexydataset.add(new Day(19, 2, 2007), 60D, "Series 1");
        timetablexydataset.add(new Day(14, 2, 2007), 45D, "Series 2");
        timetablexydataset.add(new Day(15, 2, 2007), 67D, "Series 2");
        timetablexydataset.add(new Day(16, 2, 2007), 61D, "Series 2");
        timetablexydataset.add(new Day(17, 2, 2007), 58D, "Series 2");
        timetablexydataset.add(new Day(18, 2, 2007), 73D, "Series 2");
        timetablexydataset.add(new Day(19, 2, 2007), 64D, "Series 2");
        timetablexydataset.add(new Day(14, 2, 2007), 32D, "Series 3");
        timetablexydataset.add(new Day(15, 2, 2007), 38D, "Series 3");
        timetablexydataset.add(new Day(16, 2, 2007), 43D, "Series 3");
        timetablexydataset.add(new Day(17, 2, 2007), 12D, "Series 3");
        timetablexydataset.add(new Day(18, 2, 2007), 19D, "Series 3");
        timetablexydataset.add(new Day(19, 2, 2007), 26D, "Series 3");
        return timetablexydataset;
    }

    private static OHLCDataset createOpenHighLowCloseDataset() {
        OHLCDataItem[] data = new OHLCDataItem[3];
//        OHLCDataItem(Date date,
//                double open,
//                double high,
//                double low,
//                double close,
//                double volume)
        data[0] = new OHLCDataItem(new Date(millisForDate(1, 1, 2009)), 20, 20, 0, 0, 5);
        data[1] = new OHLCDataItem(new Date(millisForDate(2, 1, 2009)), 22, 22, 2, 2, 5);
        data[2] = new OHLCDataItem(new Date(millisForDate(3, 1, 2009)), 25, 20, 3, 0, 5);
        DefaultOHLCDataset dataset = new DefaultOHLCDataset("data", data);
        return dataset;
    }

    private static List<Double> createValueList(double lowerBound, double upperBound, int count) {
        List<Double> result = new ArrayList<Double>();
        for (int i = 0; i < count; i++) {
            double v = lowerBound + (Math.random() * (upperBound - lowerBound));
            result.add(new Double(v));
        }
        return result;
    }

    private static BoxAndWhiskerCategoryDataset createBoxAndWhiskerCategoryDataset() {
        int SERIES_COUNT = 3;
        int CATEGORY_COUNT = 2;
        int VALUE_COUNT = 10;

        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();

        for (int s = 0; s < SERIES_COUNT; s++) {
            for (int c = 0; c < CATEGORY_COUNT; c++) {
                List<Double> values = createValueList(0, 20.0, VALUE_COUNT);
                dataset.add(values, "Series " + s, "Category " + c);
            }
        }
        return dataset;
    }

    private static BoxAndWhiskerXYDataset createBoxAndWhiskerXYDataset() {
        int VALUE_COUNT = 10;
        DefaultBoxAndWhiskerXYDataset dataset = new DefaultBoxAndWhiskerXYDataset("series 1");
        long time = new Date().getTime();
        List<Double> values = createValueList(0, 20.0, VALUE_COUNT);
        BoxAndWhiskerItem item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values);
        dataset.add(new Date(time + 10000), item);
        values = createValueList(0, 20.0, VALUE_COUNT);
        item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values);
        dataset.add(new Date(time + 30000), item);
        values = createValueList(0, 20.0, VALUE_COUNT);
        item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values);
        dataset.add(new Date(time + 60000), item);
        return dataset;
    }

    private static long millisForDate(int date, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date, 12, 0);
        return calendar.getTimeInMillis();
    }

    private static Object[] createItem(long l, int i, int j) {
        return (new Object[] { new Date(l), new Integer(i), new Integer(j) });
    }

    public static WindDataset createWindDataset() {
        Object aobj[] = createItem(millisForDate(3, 1, 1999), 0, 10);
        Object aobj1[] = createItem(millisForDate(4, 1, 1999), 1, 8);
        Object aobj2[] = createItem(millisForDate(5, 1, 1999), 2, 10);
        Object aobj3[] = createItem(millisForDate(6, 1, 1999), 3, 10);
        Object aobj4[] = createItem(millisForDate(7, 1, 1999), 4, 7);
        Object aobj5[] = createItem(millisForDate(8, 1, 1999), 5, 10);
        Object aobj6[] = createItem(millisForDate(9, 1, 1999), 6, 8);
        Object aobj7[] = createItem(millisForDate(10, 1, 1999), 7, 11);
        Object aobj8[] = createItem(millisForDate(11, 1, 1999), 8, 10);
        Object aobj9[] = createItem(millisForDate(12, 1, 1999), 9, 11);
        Object aobj10[] = createItem(millisForDate(13, 1, 1999), 10, 3);
        Object aobj11[] = createItem(millisForDate(14, 1, 1999), 11, 9);
        Object aobj12[] = createItem(millisForDate(15, 1, 1999), 12, 11);
        Object aobj13[] = createItem(millisForDate(16, 1, 1999), 0, 0);
        Object aobj14[][] = { aobj, aobj1, aobj2, aobj3, aobj4, aobj5, aobj6, aobj7, aobj8, aobj9, aobj10, aobj11, aobj12, aobj13 };
        Object aobj15[][][] = { aobj14 };
        return new DefaultWindDataset(aobj15);
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
        boolean notify = false;
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("series 1");
        //(theta,radius)
        for (int i = 0; i < 20; i++) {
            series1.add(i * 10, i * 10, notify);
        }
        dataset.addSeries(series1);
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
        ValueAxis yAxis = new NumberAxis("Direction / Force");
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
