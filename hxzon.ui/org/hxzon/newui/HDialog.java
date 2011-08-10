package org.hxzon.newui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;

public class HDialog extends JDialog {

    public HDialog(String title) {
        this(title, true);
    }

    public HDialog(String title, boolean modal) {

        this.setTitle(title);
        this.setModal(modal);
    }

    public HDialog(Frame frame, boolean modal) {
        super(frame, modal);
    }

    public HDialog(Dialog frame, String title, boolean modal) {
        super(frame, title, modal);
    }

    public HDialog(Dialog frame, String title) {
        super(frame, title, true);
    }

    public HDialog(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
    }

    public HDialog(Frame frame, String title) {
        super(frame, title, true);

    }

    public HDialog() {

    }

    public void fixPageSize(Dimension popupPageSize) {
        this.setPreferredSize(popupPageSize);
        this.setMaximumSize(popupPageSize);
        this.setSize(popupPageSize);

    }
    



}
