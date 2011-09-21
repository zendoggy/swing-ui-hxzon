package org.jfree.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

public class CategoryMarkerDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public CategoryMarkerDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(21D, "Series 1", "Category 1");
        defaultcategorydataset.addValue(50D, "Series 1", "Category 2");
        defaultcategorydataset.addValue(152D, "Series 1", "Category 3");
        defaultcategorydataset.addValue(184D, "Series 1", "Category 4");
        defaultcategorydataset.addValue(299D, "Series 1", "Category 5");
        return defaultcategorydataset;
    }

    private static JFreeChart createChart(CategoryDataset categorydataset) {
        JFreeChart jfreechart = ChartFactory.createLineChart("Category Marker Demo 1", "Category", "Count", categorydataset, PlotOrientation.VERTICAL, true, true, false);
        jfreechart.setBackgroundPaint(Color.white);
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        categoryplot.setBackgroundPaint(Color.lightGray);
        categoryplot.setRangeGridlinePaint(Color.white);
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
        lineandshaperenderer.setSeriesShapesVisible(0, true);
        lineandshaperenderer.setDrawOutlines(true);
        lineandshaperenderer.setUseFillPaint(true);
        lineandshaperenderer.setBaseFillPaint(Color.white);
        CategoryMarker categorymarker = new CategoryMarker("Category 4", Color.blue, new BasicStroke(1.0F));
        categorymarker.setDrawAsLine(true);
        categorymarker.setLabel("Marker Label");
        categorymarker.setLabelFont(new Font("Dialog", 0, 11));
        categorymarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        categorymarker.setLabelOffset(new RectangleInsets(2D, 5D, 2D, 5D));
        categoryplot.addDomainMarker(categorymarker, Layer.BACKGROUND);
        return jfreechart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        CategoryMarkerDemo1 categorymarkerdemo1 = new CategoryMarkerDemo1("Category Marker Demo 1");
        categorymarkerdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(categorymarkerdemo1);
        categorymarkerdemo1.setVisible(true);
    }
}
