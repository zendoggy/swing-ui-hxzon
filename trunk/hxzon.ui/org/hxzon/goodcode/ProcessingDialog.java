package org.hxzon.goodcode;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ProcessingDialog extends JDialog {

	private JLabel message = new JLabel("", SwingConstants.CENTER);
	private JProgressBar bar = new JProgressBar(0, 100);

	public ProcessingDialog(Frame owner, boolean model) {
		super(owner, model);
		initGUI();
	}

	public ProcessingDialog(Dialog owner, boolean model) {
		super(owner, model);
		initGUI();
	}

	private void initGUI() {
		this.setUndecorated(true);
		JPanel view = new JPanel();
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

		view.add(Box.createVerticalStrut(30));
		// label.setBorder(BorderFactory.createLineBorder(Color.RED));
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BorderLayout());
		labelPanel.add(message, BorderLayout.CENTER);
		labelPanel.setPreferredSize(new Dimension(200, 20));
		view.add(labelPanel);
		view.add(Box.createVerticalStrut(3));
		view.add(bar);

		view.add(Box.createVerticalStrut(50));
		view.setBorder(new JTextField().getBorder());
		this.setContentPane(view);
		this.setSize(300, 100);
		this.centerInScreen();
	}

	public void setMessage(String msg) {
		message.setText(msg);
	}

	public void setValue(int i) {
		bar.setValue(i);
	}

	public void setStringPainted(boolean b) {
		bar.setStringPainted(b);
	}

	public void setIndeterminate(boolean b) {
		bar.setIndeterminate(b);
	}

	public void centerInScreen() {
		this.setAlwaysOnTop(false);
		this.setResizable(true);
		Dimension screenSize = this.getToolkit().getScreenSize();
		Dimension size = this.getSize();
		screenSize.height = screenSize.height / 2;
		screenSize.width = screenSize.width / 2;
		size.height = size.height / 2;
		size.width = size.width / 2;
		int y = screenSize.height - size.height;
		int x = screenSize.width - size.width;
		this.setLocation(x, y);
	}

}
