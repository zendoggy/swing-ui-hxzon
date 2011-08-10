package org.hxzon.swing.components.easy;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import org.hxzon.swing.model.HEasyJListModel;
import org.hxzon.swing.model.HEasyJModelValue;

public class HEasyJList<V> extends JList {
	private static final long serialVersionUID = 1L;
	private boolean showIndex = true;
	private static final ShowIndexCellRenderer showIndexCellRenderer = new ShowIndexCellRenderer();
	private static final DefaultListCellRenderer defaultCellRenderer = new DefaultListCellRenderer();

	public HEasyJList() {
		super(new HEasyJListModel<V>());
		showIndex(showIndex);
//		this.addListSelectionListener(new ListSelectionAction() {
//
//			@Override
//			public boolean selectOne() {
//				return false;
//			}
//
//			@Override
//			public void whenSelect(Object source, int i) {
//				getModel().getElementAt(i).isSel = true;
//			}
//
//		});
	}

	public void addValue(HEasyJModelValue<V> value) {
		this.getModel().addValues(value);
	}

	public void addValues(List<HEasyJModelValue<V>> values) {
		this.getModel().addValue(values);
	}

	public void clearValues() {
		this.getModel().clearValues();
	}

	public void removeValue(HEasyJModelValue<V> value) {
		this.getModel().removeValue(value);
	}

	public void removeValues(List<HEasyJModelValue<V>> values) {
		this.getModel().removeValues(values);
	}

	public List<HEasyJModelValue<V>> getValueWraps() {
		return this.getModel().getValues();
	}

	public List<HEasyJModelValue<V>> getSelectedValueWraps() {
		ListSelectionModel sm = getSelectionModel();
		HEasyJListModel<V> dm = getModel();

		int iMin = sm.getMinSelectionIndex();
		int iMax = sm.getMaxSelectionIndex();

		List<HEasyJModelValue<V>> result = new ArrayList<HEasyJModelValue<V>>();
		if ((iMin < 0) || (iMax < 0)) {
			return result;
		}

		for (int i = iMin; i <= iMax; i++) {
			if (sm.isSelectedIndex(i)) {
				result.add(dm.getElementAt(i));
			}
		}
		return result;
	}

	public void showIndex(boolean show) {
		showIndex = show;
		if (showIndex) {
			this.setCellRenderer(showIndexCellRenderer);
		} else {
			this.setCellRenderer(defaultCellRenderer);
		}
	}

	public void setModel(ListModel model) {

	}

	@SuppressWarnings("unchecked")
	public HEasyJListModel<V> getModel() {
		return (HEasyJListModel<V>) super.getModel();
	}

	public static class ShowIndexCellRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			label.setText((index + 1) + "." + label.getText());
			return label;
		}

	}

}
