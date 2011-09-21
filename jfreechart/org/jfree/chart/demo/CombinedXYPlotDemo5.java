package org.jfree.chart.demo;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CombinedXYPlotDemo5 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public CombinedXYPlotDemo5(String s) {
        super(s);
        JFreeChart jfreechart = createCombinedChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, true, true);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartpanel);
    }

    private static JFreeChart createCombinedChart() {
        XYDataset dataset1 = createDataset1();
        StandardXYItemRenderer renderer1 = new StandardXYItemRenderer();
        NumberAxis valueAxis1 = new NumberAxis("Range 1");
        XYPlot plot1 = new XYPlot(dataset1, null, valueAxis1, renderer1);
        plot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        //annotation
        XYTextAnnotation xytextannotation = new XYTextAnnotation("Hello!", 50D, 10000D);
        xytextannotation.setFont(new Font("SansSerif", 0, 9));
        xytextannotation.setRotationAngle(0.78539816339744828D);
        plot1.addAnnotation(xytextannotation);
        //
        XYDataset dataset2 = createDataset2();
        StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
        NumberAxis valueAxis2 = new NumberAxis("Range 2");
        valueAxis2.setAutoRangeIncludesZero(false);
        XYPlot plot2 = new XYPlot(dataset2, null, valueAxis2, renderer2);
        plot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
        //
        NumberAxis valueAxis = new NumberAxis("Domain");
        valueAxis.setTickMarkInsideLength(5F);
        CombinedDomainXYPlot combinedPlot = new CombinedDomainXYPlot(valueAxis);
        combinedPlot.setGap(10D);
        combinedPlot.add(plot1, 1);
        combinedPlot.add(plot2, 1);
        combinedPlot.setOrientation(PlotOrientation.VERTICAL);
        return new JFreeChart("CombinedDomainXYPlot Demo", JFreeChart.DEFAULT_TITLE_FONT, combinedPlot, true);
    }

    private static XYDataset createDataset1() {
        XYSeries xyseries = new XYSeries("Series 1");
        xyseries.add(10D, 12353.299999999999D);
        xyseries.add(20D, 13734.4D);
        xyseries.add(30D, 14525.299999999999D);
        xyseries.add(40D, 13984.299999999999D);
        xyseries.add(50D, 12999.4D);
        xyseries.add(60D, 14274.299999999999D);
        xyseries.add(70D, 15943.5D);
        xyseries.add(80D, 14845.299999999999D);
        xyseries.add(90D, 14645.4D);
        xyseries.add(100D, 16234.6D);
        xyseries.add(110D, 17232.299999999999D);
        xyseries.add(120D, 14232.200000000001D);
        xyseries.add(130D, 13102.200000000001D);
        xyseries.add(140D, 14230.200000000001D);
        xyseries.add(150D, 11235.200000000001D);
        XYSeries xyseries1 = new XYSeries("Series 2");
        xyseries1.add(10D, 15000.299999999999D);
        xyseries1.add(20D, 11000.4D);
        xyseries1.add(30D, 17000.299999999999D);
        xyseries1.add(40D, 15000.299999999999D);
        xyseries1.add(50D, 14000.4D);
        xyseries1.add(60D, 12000.299999999999D);
        xyseries1.add(70D, 11000.5D);
        xyseries1.add(80D, 12000.299999999999D);
        xyseries1.add(90D, 13000.4D);
        xyseries1.add(100D, 12000.6D);
        xyseries1.add(110D, 13000.299999999999D);
        xyseries1.add(120D, 17000.200000000001D);
        xyseries1.add(130D, 18000.200000000001D);
        xyseries1.add(140D, 16000.200000000001D);
        xyseries1.add(150D, 17000.200000000001D);
        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(xyseries);
        xyseriescollection.addSeries(xyseries1);
        return xyseriescollection;
    }

    private static XYDataset createDataset2() {
        XYSeries xyseries = new XYSeries("Series 3");
        xyseries.add(10D, 16853.200000000001D);
        xyseries.add(20D, 19642.299999999999D);
        xyseries.add(30D, 18253.5D);
        xyseries.add(40D, 15352.299999999999D);
        xyseries.add(50D, 13532D);
        xyseries.add(100D, 12635.299999999999D);
        xyseries.add(110D, 13998.200000000001D);
        xyseries.add(120D, 11943.200000000001D);
        xyseries.add(130D, 16943.900000000001D);
        xyseries.add(140D, 17843.200000000001D);
        xyseries.add(150D, 16495.299999999999D);
        xyseries.add(160D, 17943.599999999999D);
        xyseries.add(170D, 18500.700000000001D);
        xyseries.add(180D, 19595.900000000001D);
        return new XYSeriesCollection(xyseries);
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createCombinedChart();
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        CombinedXYPlotDemo5 combinedxyplotdemo5 = new CombinedXYPlotDemo5("JFreeChart: CombinedDomainXYPlotDemo1");
        combinedxyplotdemo5.pack();
        RefineryUtilities.centerFrameOnScreen(combinedxyplotdemo5);
        combinedxyplotdemo5.setVisible(true);
    }
}
