// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package org.jfree.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.gantt.GanttCategoryDataset;
import org.jfree.data.gantt.SlidingGanttCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RefineryUtilities;

public class SlidingGanttDatasetDemo1 extends ApplicationFrame {
    private static final long serialVersionUID = 1L;

    static class DemoPanel extends JPanel implements ChangeListener {

        private static final long serialVersionUID = 1L;
        JScrollBar scroller;
        SlidingGanttCategoryDataset dataset;

        public static GanttCategoryDataset createDataset() {
            TaskSeries taskseries = new TaskSeries("Scheduled");
            Day day = new Day();
            Day day1 = new Day();
            for (int i = 0; i < 50; i++) {
                int j = (int) (Math.random() * 10D) + 1;
                for (int k = 0; k < j; k++)
                    day1 = (Day) day1.next();

                taskseries.add(new Task("Task " + i, new Date(day.getMiddleMillisecond()), new Date(day1.getMiddleMillisecond())));
                day = (Day) day1.next();
                day1 = (Day) day1.next();
            }

            TaskSeriesCollection dataset = new TaskSeriesCollection();
            dataset.add(taskseries);
            return dataset;
        }

        private static JFreeChart createChart(SlidingGanttCategoryDataset dataset) {
            JFreeChart chart = ChartFactory.createGanttChart("Gantt Chart Demo", "Task", "Date", dataset, true, true, false);
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            Hour hour = new Hour(1, 14, 5, 2008);
            for (int i = 0; i < 12; i++) {
                IntervalMarker intervalmarker = new IntervalMarker(hour.getFirstMillisecond(), hour.getLastMillisecond(), Color.lightGray);
                plot.addRangeMarker(intervalmarker, Layer.BACKGROUND);
                hour = (Hour) hour.next().next();
            }

            plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10F);
            DateAxis valueAxis = (DateAxis) plot.getRangeAxis();
            //hxzon:when restoreAutoBounds,invalidation
            valueAxis.setRange(DatasetUtilities.findRangeBounds(dataset.getUnderlyingDataset(), true));
            GanttRenderer ganttrenderer = (GanttRenderer) plot.getRenderer();
            ganttrenderer.setDrawBarOutline(false);
            ganttrenderer.setShadowVisible(false);
            return chart;
        }

        public void stateChanged(ChangeEvent changeevent) {
            //SlidingGanttCategoryDataset
            dataset.setFirstCategoryIndex(scroller.getValue());
        }

        public DemoPanel() {
            super(new BorderLayout());
            dataset = new SlidingGanttCategoryDataset(createDataset(), 0, 15);
            JFreeChart jfreechart = createChart(dataset);
            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(400, 400));
            scroller = new JScrollBar(1, 0, 15, 0, 50);
            add(chartpanel);
            scroller.getModel().addChangeListener(this);
            JPanel jpanel = new JPanel(new BorderLayout());
            jpanel.add(scroller);
            jpanel.setBorder(BorderFactory.createEmptyBorder(66, 2, 2, 2));
            add(jpanel, "East");
        }
    }

    public SlidingGanttDatasetDemo1(String s) {
        super(s);
        setDefaultCloseOperation(3);
        setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    public static void main(String args[]) {
        SlidingGanttDatasetDemo1 slidingganttdatasetdemo1 = new SlidingGanttDatasetDemo1("JFreeChart: SlidingGanttDatasetDemo1.java");
        slidingganttdatasetdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(slidingganttdatasetdemo1);
        slidingganttdatasetdemo1.setVisible(true);
    }
}
