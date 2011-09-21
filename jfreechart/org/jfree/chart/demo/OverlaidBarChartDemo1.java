package org.jfree.chart.demo;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class OverlaidBarChartDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public OverlaidBarChartDemo1(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    public static JFreeChart createChart() {
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1.addValue(1.0D, "S1", "Category 1");
        dataset1.addValue(4D, "S1", "Category 2");
        dataset1.addValue(3D, "S1", "Category 3");
        dataset1.addValue(5D, "S1", "Category 4");
        dataset1.addValue(5D, "S1", "Category 5");
        dataset1.addValue(7D, "S1", "Category 6");
        dataset1.addValue(7D, "S1", "Category 7");
        dataset1.addValue(8D, "S1", "Category 8");
        dataset1.addValue(5D, "S2", "Category 1");
        dataset1.addValue(7D, "S2", "Category 2");
        dataset1.addValue(6D, "S2", "Category 3");
        dataset1.addValue(8D, "S2", "Category 4");
        dataset1.addValue(4D, "S2", "Category 5");
        dataset1.addValue(4D, "S2", "Category 6");
        dataset1.addValue(2D, "S2", "Category 7");
        dataset1.addValue(1.0D, "S2", "Category 8");
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(9D, "T1", "Category 1");
        dataset2.addValue(7D, "T1", "Category 2");
        dataset2.addValue(2D, "T1", "Category 3");
        dataset2.addValue(6D, "T1", "Category 4");
        dataset2.addValue(6D, "T1", "Category 5");
        dataset2.addValue(9D, "T1", "Category 6");
        dataset2.addValue(5D, "T1", "Category 7");
        dataset2.addValue(4D, "T1", "Category 8");
        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        dataset3.addValue(94D, "R1", "Category 1");
        dataset3.addValue(75D, "R1", "Category 2");
        dataset3.addValue(22D, "R1", "Category 3");
        dataset3.addValue(74D, "R1", "Category 4");
        dataset3.addValue(83D, "R1", "Category 5");
        dataset3.addValue(9D, "R1", "Category 6");
        dataset3.addValue(23D, "R1", "Category 7");
        dataset3.addValue(98D, "R1", "Category 8");
        //
        StandardCategoryItemLabelGenerator standardcategoryitemlabelgenerator = new StandardCategoryItemLabelGenerator();
        BarRenderer renderer1 = new BarRenderer();
        renderer1.setBaseItemLabelGenerator(standardcategoryitemlabelgenerator);
        renderer1.setBaseItemLabelsVisible(true);
        LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
        //plot
        CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer1);
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        plot.setDataset(2, dataset3);
        plot.setRenderer(2, renderer3);
        plot.setDomainAxis(new CategoryAxis("Category"));
        //more rangeAxis
        plot.setRangeAxis(new NumberAxis("Value"));
        plot.setRangeAxis(1, new NumberAxis("Axis 2"));
        plot.mapDatasetToRangeAxis(2, 1);
        //
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        //chart
        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Overlaid Bar Chart");
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.addChartMouseListener(new ChartMouseListener() {

            public void chartMouseClicked(ChartMouseEvent chartmouseevent) {
                System.out.println(chartmouseevent.getEntity());
            }

            public void chartMouseMoved(ChartMouseEvent chartmouseevent) {
            }

        });
        return chartpanel;
    }

    public static void main(String args[]) {
        OverlaidBarChartDemo1 overlaidbarchartdemo1 = new OverlaidBarChartDemo1("JFreeChart: OverlaidBarChartDemo1.java");
        overlaidbarchartdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(overlaidbarchartdemo1);
        overlaidbarchartdemo1.setVisible(true);
    }
}
