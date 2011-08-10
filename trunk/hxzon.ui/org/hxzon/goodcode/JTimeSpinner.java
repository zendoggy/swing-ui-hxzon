package org.hxzon.goodcode;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JTimeSpinner extends JSpinner {
    JTimeField timefield=new JTimeField(new Date());
    public JTimeSpinner(){
        setEditor(timefield);
        timefield.addCaretListener(new CaretListener(){

            @Override
            public void caretUpdate(CaretEvent e) {
            }
            
        });
        timefield.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                getModel().setValue(timefield.getTime());
            }
            
        });
        setModel(new SpinnerDateModel());
        
        this.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                Date date=(Date) getModel().getValue();
                timefield.setDate(date);
            }
            
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLayout(new FlowLayout());
        final JTimeSpinner time = new JTimeSpinner();
        f.add(time);
        JButton button = new JButton(new AbstractAction("显示时间") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("timestring:" + time.getValue());
            }
        });
        f.add(button);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 100);
        f.setLocation(300, 300);
        f.setVisible(true);
    }

}
