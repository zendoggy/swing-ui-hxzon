package org.hxzon.swing.components.ext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

import org.hxzon.swing.util.HWindowUtil;

public class HProcessingDialog extends JDialog {
	private static final long serialVersionUID = 5926125070284408595L;
	public JLabel label = new JLabel("", SwingConstants.CENTER);
	public JProgressBar bar = new JProgressBar(0, 100);
	public JButton cancelBt = new JButton();
	private boolean canCancel;

	public HProcessingDialog(Dialog parent) {
		super(parent, "", true);
		initGUI();
	}

	public HProcessingDialog(Frame parent) {
		super(parent, true);
		initGUI();
	}

	public HProcessingDialog(Dialog parent, boolean canCancel) {
		super(parent, "", true);
		this.canCancel = canCancel;
		initGUI();
	}

	public HProcessingDialog(Frame parent, boolean canCancel) {
		super(parent, true);
		this.canCancel = canCancel;
		initGUI();
	}

	public void initGUI() {
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		JPanel view = new JPanel();
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

		bar.setIndeterminate(true);
		view.add(Box.createVerticalStrut(30));

		// label.setBorder(BorderFactory.createLineBorder(Color.RED));
		JPanel labelPanel = new JPanel(new BorderLayout());
		labelPanel.add(label, BorderLayout.CENTER);
		labelPanel.setPreferredSize(new Dimension(200, 20));
		view.add(labelPanel);
		view.add(Box.createVerticalStrut(3));

		view.add(bar);
		if (canCancel) {
//			barPanel.add(cancelBt, BorderLayout.EAST);
			JPanel btPanel = new JPanel(new BorderLayout());
			btPanel.add(cancelBt, BorderLayout.EAST);
			view.add(Box.createVerticalStrut(10));
			view.add(btPanel, BorderLayout.SOUTH);
		}
		view.add(Box.createVerticalStrut(20));

		view.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(20, 20, 30, 20)));
		this.setContentPane(view);
		this.setSize(400, 130);
		HWindowUtil.centerInScreen(this);
	}

	public void setCancelAction(Action action) {
		cancelBt.setAction(action);
//		cancelBt.setMargin(new Insets(0, 0, 0, 0));
//		cancelBt.setFocusPainted(false);
//		cancelBt.setIcon(AppConstants.getExitIcon());
//		cancelBt.setContentAreaFilled(false);
//		cancelBt.setToolTipText("取消");
	}

}
