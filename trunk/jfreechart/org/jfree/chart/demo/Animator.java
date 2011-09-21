package org.jfree.chart.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.jfree.data.category.DefaultCategoryDataset;

class Animator extends Timer implements ActionListener {

    private static final long serialVersionUID = 1L;

    private DefaultCategoryDataset dataset;

    Animator(DefaultCategoryDataset defaultcategorydataset) {
        super(40, null);
        dataset = defaultcategorydataset;
        addActionListener(this);
    }

    @SuppressWarnings("rawtypes")
    public void actionPerformed(ActionEvent actionevent) {
        int i = (int) (Math.random() * (double) dataset.getRowCount());
        Comparable comparable = dataset.getRowKey(i);
        int j = (int) (Math.random() * (double) dataset.getColumnCount());
        Comparable comparable1 = dataset.getColumnKey(j);
        int k = Math.random() - 0.5D >= 0.0D ? 5 : -5;
        dataset.setValue(Math.max(0.0D, dataset.getValue(i, j).doubleValue() + (double) k), comparable, comparable1);
    }

}
