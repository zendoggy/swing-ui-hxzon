package org.dxj.guitools.gridbagtable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


public class Test implements ActionListener{
	
	GridBagTable table;
	public Test()
	{
		JFrame d = new JFrame();
		DefaultTableModel model = new DefaultTableModel(5,5);
		
		table = new GridBagTable(model);
		table.setRowHeight(20);
		
		JScrollPane pane = new JScrollPane(table);
		d.getContentPane().add(pane, BorderLayout.CENTER);
		JButton btn = new JButton("合并/拆分");
		d.getContentPane().add(btn, BorderLayout.NORTH);
		btn.addActionListener(this);
		d.setBounds(0, 0, 400, 400);
		d.setVisible(true);
	}
	
	public static void main(String[] fsd){
		new Test();
	}
	
	public void actionPerformed(ActionEvent e) {
		table.mergeCells(table.getSelectedRows(), table.getSelectedColumns());
	}
}
