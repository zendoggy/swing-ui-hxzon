package org.hxzon.demo.fest;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FirstFrame extends JFrame{

    public FirstFrame(){
        setBackground(Color.red);
        setLayout(new GridLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        final JLabel output=new JLabel("");
        output.setName("ouput");
        final JTextField input=new JTextField();
        input.setName("input");
        add(output);
        add(input);
        JButton copy=new JButton();
        copy.setName("copy");
        copy.setAction(new AbstractAction("copy"){

            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText(input.getText());
            }
            
        });
        JButton loginBt=new JButton();
        loginBt.setName("loginBt");
        loginBt.setAction(new AbstractAction("login"){

            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginDialog().setName("loginDialog");
//                new LoginDialog().setName("loginDialog2");
            }
            
        });
        add(copy);
        add(loginBt);
        pack();
        setVisible(true);
    }
}
