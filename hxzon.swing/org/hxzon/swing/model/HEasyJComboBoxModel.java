package org.hxzon.swing.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

public class HEasyJComboBoxModel<V> extends AbstractListModel implements MutableComboBoxModel {
	private static final long serialVersionUID = 1L;
	private List<HEasyJModelValue<V>> values = new ArrayList<HEasyJModelValue<V>>();
	private HEasyJModelValue<V> selValue;

	public void addValue(HEasyJModelValue<V> value) {
		int size = this.values.size();
		this.values.add(value);
		this.fireIntervalAdded(this, size, this.values.size() - 1);
	}

	public void addValues(List<HEasyJModelValue<V>> values) {
		int size = this.values.size();
		this.values.addAll(values);
		this.fireIntervalAdded(this, size, this.values.size() - 1);
	}

	public void clearValues() {
		int size = values.size() - 1;
		this.values.clear();
		this.fireIntervalRemoved(this, 0, size >= 0 ? size : 0);
	}

	public void removeValue(HEasyJModelValue<V> value) {
		int index = values.indexOf(value);
		this.values.remove(value);
		this.fireIntervalRemoved(this, index, index);
	}

	public void removeValues(List<HEasyJModelValue<V>> values) {
		this.values.removeAll(values);
	}

	public HEasyJModelValue<V> getValueAt(int index) {
		return values.get(index);
	}

	public HEasyJModelValue<V>[] getValues() {
		return values.toArray(new HEasyJModelValue[values.size()]);
	}

	@Override
	public HEasyJModelValue<V> getSelectedItem() {
		return selValue != null ? selValue : (values.isEmpty() ? null : values.get(0));
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selValue = (HEasyJModelValue<V>) anItem;
	}
	
	@Override
	public HEasyJModelValue<V> getElementAt(int index) {
		return getValueAt(index);
	}

	@Override
	public int getSize() {
		return values.size();
	}

	@Override
	public void addElement(Object value) {
		if (value instanceof HEasyJModelValue) {
			this.addValue((HEasyJModelValue) value);
		}
	}

	@Override
	public void insertElementAt(Object obj, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeElement(Object value) {
		if (value instanceof HEasyJModelValue) {
			this.removeValue((HEasyJModelValue) value);
		}

	}

	@Override
	public void removeElementAt(int index) {
		// TODO Auto-generated method stub

	}

}
