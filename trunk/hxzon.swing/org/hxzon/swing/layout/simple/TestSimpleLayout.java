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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import org.hxzon.util.DebugUtil;

public class TestSimpleLayout {
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 150);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new SimpleLayout());
		((JComponent) contentPane).setBorder(BorderFactory.createLineBorder(Color.RED));
		JButton button1 = new JButton("hello");
		JButton button2 = new JButton("hello 2");
		JTextField field1 = new JTextField("field 1");
		JTextField field2 = new JTextField("field 2");
		contentPane.add(button1, SimpleLayoutData.fixedSize(25));
		contentPane.add(button2, SimpleLayoutData.fillPercent(25));
		contentPane.add(field1, SimpleLayoutData.fixedSize(25));
		contentPane.add(field2, SimpleLayoutData.fillPercent(25));
		JTree tree1 = new JTree();
		JScrollPane scrollPane1 = new JScrollPane(tree1);
		JTree tree2=new JTree();
		JScrollPane scrollPane2=new JScrollPane(tree2);
		JPanel treePane=new JPanel(new SimpleLayout(true));
		treePane.add(scrollPane1,SimpleLayoutData.fillPercent(40));
		treePane.add(new JPanel(new SimpleLayout()),SimpleLayoutData.fixedSize(40));
		treePane.add(scrollPane2,SimpleLayoutData.fillPercent(40));
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
