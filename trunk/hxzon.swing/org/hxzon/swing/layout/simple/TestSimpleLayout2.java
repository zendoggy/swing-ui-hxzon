package org.hxzon.swing.layout.simple;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;

public class TestSimpleLayout2 {
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
		JPanel fieldPane = new JPanel(new SimpleLayout());
		fieldPane.add(button1, SimpleLayoutData.fixedSize(25));
		fieldPane.add(button2, SimpleLayoutData.fillPercent(25));
		fieldPane.add(field1, SimpleLayoutData.fixedSize(25));
		fieldPane.add(field2, SimpleLayoutData.fillPercent(25));

		JTree tree1 = new JTree();
		JTree tree2 = new JTree();
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setAutoscrolls(true);
		splitPane.setOneTouchExpandable(true);
		splitPane.setTopComponent(new JScrollPane(tree1));
		splitPane.setBottomComponent(new JScrollPane(tree2));

		JSplitPane splitPane2 = new JSplitPane();
		splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane2.setAutoscrolls(true);
		splitPane2.setTopComponent(new JScrollPane(fieldPane));
		splitPane2.setBottomComponent(splitPane);

		contentPane.add(splitPane2, SimpleLayoutData.fillPercent(100));
		frame.pack();
		frame.setVisible(true);
	}
}
