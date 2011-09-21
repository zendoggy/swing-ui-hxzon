package org.jfree.chart.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.jfree.chart.plot.PiePlot3D;

class Rotator extends Timer implements ActionListener {

    private static final long serialVersionUID = 1L;

    private PiePlot3D plot;
    private int angle;

    Rotator(PiePlot3D pieplot3d) {
        super(100, null);
        angle = 270;
        plot = pieplot3d;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionevent) {
        plot.setStartAngle(angle);
        angle = angle + 1;
        if (angle == 360)
            angle = 0;
    }

}
