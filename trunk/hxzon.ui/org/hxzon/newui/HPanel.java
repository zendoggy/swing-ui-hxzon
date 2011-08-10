package org.hxzon.newui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import net.miginfocom.swing.MigLayout;

public class HPanel extends JPanel {

    public HPanel() {
        super(new MigLayout("debug", "[right]", ""));
    }

    public void add(HWidget widget) {
        add(widget, false);
    }

    public void add(HWidget widget, boolean newline) {
        addLabel(widget.getTitle(), newline);
        add(widget.getComponent(), "grow,pushx");
    }

    public void addFill(HWidget widget) {
        addFill(widget, false);
    }

    public void addFill(HWidget widget, boolean newline) {
        addLabel(widget.getTitle(), newline);
        add(widget.getComponent(), "grow,pushx,pushy,growy");
    }

    public void addLabel(String title, boolean newline) {
        addLabel(new JLabel(title), newline);
    }

    public void addLabel(String title) {
        addLabel(new JLabel(title), false);
    }

    public void addLabel(JLabel label, boolean newline) {
        add(label, newline ? "newline,top" : "top");
    }

    public void addLabel(JLabel label) {
        addLabel(label, false);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HPanel form = new HPanel();
        form.add(new HText("hello:","hello:"));
        form.add(new HText("o:","o:"));
        form.add(new HText("hut:","hut:"), true);
        form.addLabel("helllllllllll");
        form.add(new JSpinner(), "");
        form.addFill(new HText("hut:","hut:"), true);
        form.addFill(new HText("hut:","hut:"), true);
        f.add(form);
        f.pack();
        // f.setSize(500, 480);
        f.setVisible(true);

    }

}
