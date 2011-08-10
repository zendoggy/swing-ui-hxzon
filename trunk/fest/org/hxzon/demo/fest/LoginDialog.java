package org.hxzon.demo.fest;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginDialog extends JDialog{

    public LoginDialog(){
        setName("loginDialog");
        setBounds(300,300,800,400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 1));
        final JTextField username=new JTextField("username");
        username.setName("username");
        final JTextField password=new JTextField("password");
        password.setName("password");
        final JLabel message=new JLabel("");
        message.setName("message");
        add(username);
        add(password);
        JButton login=new JButton();
        login.setName("login");
        add(message);
        login.setAction(new AbstractAction("login"){

            @Override
            public void actionPerformed(ActionEvent e) {
                message.setText("login ok");
            }
            
        });
        add(login);
        pack();
        setVisible(true);
    }
    
    public static void main(String args[]){
        new LoginDialog();
    }
}
