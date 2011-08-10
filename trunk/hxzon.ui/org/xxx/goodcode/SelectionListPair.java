package org.xxx.goodcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectionListPair extends JPanel {
	private JList srcList;
	private JList selectedList;
	private JButton add, remove, addAll, removeAll;

	private List srcData;
	private List selectedData;
	private Comparator toStringCompator;

	public SelectionListPair(List srcData, String srcListTitle,
			String destListTitle) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		// center button panel(add, remove, addAll, removeAll)
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		add = new JButton();
		remove = new JButton();
		addAll = new JButton();
		removeAll = new JButton();
		add.setFocusable(false);
		remove.setFocusable(false);
		addAll.setFocusable(false);
		removeAll.setFocusable(false);
		add.setToolTipText("add");
		remove.setToolTipText("remove");
		addAll.setToolTipText("add all");
		removeAll.setToolTipText("remove add");
		center.add(add);
		center.add(Box.createVerticalStrut(10));
		center.add(remove);
		center.add(Box.createVerticalStrut(30));
		center.add(addAll);
		center.add(Box.createVerticalStrut(10));
		center.add(removeAll);
		// left list
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(150, 350));
		left.setSize(new Dimension(150, 350));
		left.setBorder(BorderFactory.createTitledBorder(left.getBorder(),
				srcListTitle));
		left.setLayout(new BorderLayout());
		ListCellRenderer cognitiveRender = new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel comp = (JLabel) super.getListCellRendererComponent(list,
						value, index, isSelected, cellHasFocus);
				if (selectedData != null && selectedData.contains(value))
					comp.setForeground(Color.gray);

				return comp;
			}
		};
		srcList = new JList();
		srcList.setCellRenderer(cognitiveRender);
		srcList.setVisibleRowCount(10);
		left.add(new JScrollPane(srcList), BorderLayout.CENTER);
		// right list
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(150, 350));
		right.setSize(new Dimension(150, 350));
		right.setBorder(BorderFactory.createTitledBorder(right.getBorder(),
				destListTitle));
		right.setLayout(new BorderLayout());
		selectedList = new JList();
		selectedList.setVisibleRowCount(10);
		right.add(new JScrollPane(selectedList), BorderLayout.CENTER);
		// add them all
		add(left);
		add(center);
		add(right);

		selectedData = new ArrayList();
		if (srcData != null) {
			srcList.setListData(srcData.toArray());
			this.srcData = srcData;
		}
		toStringCompator = new Comparator() {
			public int compare(Object o1, Object o2) {
				return o1.toString().compareTo(o2.toString());
			}
		};

		add.setAction(new AbstractAction(" >>") {
			public void actionPerformed(ActionEvent e) {
				add(true);
			}
		});
		remove.setAction(new AbstractAction("<< ") {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		addAll.setAction(new AbstractAction(">>>") {
			public void actionPerformed(ActionEvent e) {
				add_All();
			}
		});
		removeAll.setAction(new AbstractAction("<<<") {
			public void actionPerformed(ActionEvent e) {
				remove_All();
			}
		});
		srcList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2
						&& !selectedData.contains(srcList.getSelectedValue())) {
					add(false);
					add.setEnabled(false);
				}
			}
		});
		selectedList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					remove();
			}
		});
		// set enable
		removeAll.setEnabled(false);
		remove.setEnabled(false);
		if (srcList.getModel().getSize() < 1)
			addAll.setEnabled(false);
		if (srcList.getSelectionModel().isSelectionEmpty())
			add.setEnabled(false);
		srcList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					add.setEnabled(!selectedData.contains(srcList
							.getSelectedValue()));
				}
			}
		});
		selectedList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					remove.setEnabled(e.getLastIndex() != -1);
				}
			}
		});

	}

	public List getSelection() {
		// if (!selectedData.isEmpty())
		return selectedData;
		// else
		// return srcData;
	}

	private void add(boolean jump) {
		Object[] selected = srcList.getSelectedValues();
		List sel = Arrays.asList(selected);
		selectedData.removeAll(sel);
		selectedData.addAll(sel);
		reInitSelectedData();

		if (jump) {
			int max = srcList.getMaxSelectionIndex();
			int length = srcList.getModel().getSize();
			int newSelected = (max < (length - 1)) ? (max + 1) : 0;
			final int mark = newSelected;
			if (selectedData.contains(srcList.getModel().getElementAt(
					newSelected))) {
				// 先向前推一位
				if (newSelected == length - 1)
					newSelected = 0;
				else
					newSelected++;

				// 循环一周：直到再次遇见mark结束
				while (newSelected != mark) {
					if (!selectedData.contains(srcList.getModel().getElementAt(
							newSelected)))
						break;// 找到了合适的落脚点，提前退出
					else {
						// 再向前推一位
						if (newSelected == length - 1)
							newSelected = 0;
						else
							newSelected++;
					}
				}
			}
			srcList.setSelectedIndex(newSelected);
		}

		// 发出通知改变相关按钮状态
		if (selected.length > 0) {
			if (selectedData.size() == srcList.getModel().getSize())
				propertyChange("AllAdded");
			else
				propertyChange("Added");
		}

	}

	private void remove() {
		int[] indices = selectedList.getSelectedIndices();
		Object[] selected = selectedList.getSelectedValues();
		List sel = Arrays.asList(selected);
		selectedData.removeAll(sel);
		int oldMax = selectedList.getMaxSelectionIndex();
		boolean notTail = oldMax < (selectedList.getModel().getSize() - 1);
		reInitSelectedData();
		// 自动选中下一项
		int newSelected = notTail ? (oldMax + 1 - indices.length) : 0;
		selectedList.setSelectedIndex(newSelected);
		// 发出通知改变相关按钮状态
		if (indices.length > 0) {
			if (selectedList.getSelectedIndex() == -1)
				propertyChange("AllRemoved");
			else
				propertyChange("Removed");
		}
	}

	private void add_All() {
		ListModel model = srcList.getModel();
		int size = model.getSize();
		selectedData.clear();
		for (int i = 0; i < size; i++) {
			selectedData.add(model.getElementAt(i));
		}
		reInitSelectedData();
		if (size > 0)
			propertyChange("AllAdded");
	}

	private void remove_All() {
		selectedData.clear();
		reInitSelectedData();
		propertyChange("AllRemoved");
	}

	private void reInitSelectedData() {
		Collections.sort(selectedData, toStringCompator);
		selectedList.setListData(selectedData.toArray());
		srcList.repaint();
	}

	private void propertyChange(String name) {
		if ("AllAdded".equals(name)) {
			addAll.setEnabled(false);
			add.setEnabled(false);
			removeAll.setEnabled(true);
			if (selectedList.getSelectedIndex() != -1)
				remove.setEnabled(true);
			else
				remove.setEnabled(false);
		} else if ("AllRemoved".equals(name)) {
			removeAll.setEnabled(false);
			remove.setEnabled(false);
			addAll.setEnabled(true);
			if (srcList.getSelectedIndex() != -1)
				add.setEnabled(true);
			else
				add.setEnabled(false);
		} else if ("Added".equals(name)) {
			// 已添加了一次，但未全部添加完
			addAll.setEnabled(true);
			removeAll.setEnabled(true);
			if (selectedList.getSelectedIndex() != -1)
				remove.setEnabled(true);
			else
				remove.setEnabled(false);
		} else if ("Removed".equals(name)) {
			// 已删除了一次，但未清空
			addAll.setEnabled(true);
			removeAll.setEnabled(true);
			if (srcList.getSelectedIndex() != -1)
				add.setEnabled(true);
			else
				add.setEnabled(false);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
