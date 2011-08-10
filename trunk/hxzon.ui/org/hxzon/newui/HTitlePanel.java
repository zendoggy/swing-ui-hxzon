package org.hxzon.newui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class HTitlePanel extends JPanel {
    JLabel message = new JLabel(" ");

    public HTitlePanel() {
        super(new MigLayout());
        add(message);
    }

    public void showText(String text, boolean important) {
        if(text==null || text.isEmpty()){
            text=" ";
        }
        text = "<html><u><font color=red>" + text + "</font></u></html>";
        if (important) {
            message.setForeground(Color.red);
        }
        message.setForeground(Color.blue);
        message.setText(text);
    }

    public void showMessage(String text) {
        showText(text, false);
    }

    public void showErrorMessage(String text) {
        showText(text, true);
    }

    public void clearMessage() {
        showText(" ", false);
    }

    public JLabel getLabel() {
        return message;
    }
}
