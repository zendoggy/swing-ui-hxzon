package org.hxzon.swing.layout.simple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import org.hxzon.swing.util.HBorderUtil;
import org.hxzon.util.DebugUtil;

public class TestSimpleLayout4 {
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 150);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new SimpleLayout());
		((JComponent) contentPane).setBorder(BorderFactory.createLineBorder(Color.RED));
//		HBorderUtil.addTitleBorder((JComponent) contentPane, "simple layout 4");
//		HBorderUtil.addTitleBorder((JComponent) contentPane, "simple layout 4", 10,true);
		HBorderUtil.addTitleBorder((JComponent) contentPane, "simple layout 4", 10,false);
		JLabel label1 = new JLabel("label 1");
		JLabel label2 = new JLabel("label 2");
		JButton button1 = new JButton("hello");
		JButton button2 = new JButton("hello 2");
		JTextField field1 = new JTextField("field 1");
		JTextField field2 = new JTextField("field 2");
		JPanel line1 = new JPanel(new SimpleLayout(true));
		line1.add(label1, SimpleLayoutData.fillPercent(30));
		line1.add(field1, SimpleLayoutData.fillPercent(40));
		line1.add(button1, SimpleLayoutData.fillPercent(30));
		JPanel line2 = new JPanel(new SimpleLayout(true));
		line2.add(label2, SimpleLayoutData.fillPercent(30));
		line2.add(field2, SimpleLayoutData.fillPercent(40));
		line2.add(button2, SimpleLayoutData.fillPercent(30));
		contentPane.add(line1);
		contentPane.add(line2);
		JTree tree1 = new JTree();
		JScrollPane scrollPane1 = new JScrollPane(tree1);
		JPanel leftPane = new JPanel(new SimpleLayout(true));
		leftPane.add(scrollPane1, SimpleLayoutData.fillPercent(100));
		leftPane.add(new JPanel(), SimpleLayoutData.fixedSize(10));
		JTree tree2 = new JTree();
		JScrollPane scrollPane2 = new JScrollPane(tree2);
		JPanel rightPane = new JPanel(new SimpleLayout(true));
		rightPane.add(new JPanel(), SimpleLayoutData.fixedSize(10));
		rightPane.add(scrollPane2, SimpleLayoutData.fillPercent(100));
		JPanel treePane = new JPanel(new SimpleLayout(true));
		treePane.add(leftPane, SimpleLayoutData.fillPercent(50));
		treePane.add(rightPane, SimpleLayoutData.fillPercent(50));
		contentPane.add(treePane, SimpleLayoutData.fillPercent(50));
		contentPane.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				Component comp = (Component) e.getSource();
				DebugUtil.debug("content resize:" + comp.getSize());
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

		});
		frame.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				Component comp = (Component) e.getSource();
				DebugUtil.debug("window resize:" + comp.getSize());
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

		});
		frame.pack();
		frame.setVisible(true);
	}
}
