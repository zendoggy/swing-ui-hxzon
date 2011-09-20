package org.hxzon.demo.jfreechart;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;

public class MyChartClickHandler extends MouseAdapter {
    private ChartPanel chartPanel;

    public MyChartClickHandler(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public void mouseClicked(MouseEvent event) {
        String result = null;
        ChartPanel cp = chartPanel;
        ChartRenderingInfo info = cp.getChartRenderingInfo();
        if (info != null) {
            EntityCollection entities = info.getEntityCollection();
            if (entities != null) {
                Insets insets = cp.getInsets();
                ChartEntity entity = entities.getEntity((int) ((event.getX() - insets.left) / cp.getScaleX()), (int) ((event.getY() - insets.top) / cp.getScaleY()));
                if (entity != null) {
                    result = entity.getToolTipText();
                }
            }
        }
        if (result != null) {
            System.out.println("mouse click:" + result);
        }
    }
}
