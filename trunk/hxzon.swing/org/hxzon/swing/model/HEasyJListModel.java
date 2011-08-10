package org.hxzon.swing.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class HEasyJListModel<V> extends AbstractListModel {
	private static final long serialVersionUID = 1L;
	private List<HEasyJModelValue<V>> values = new ArrayList<HEasyJModelValue<V>>();

	@Override
	public HEasyJModelValue<V> getElementAt(int index) {
		return getValue(index);
	}

	@Override
	public int getSize() {
		return values.size();
	}

	public boolean isEmpty() {
		return values.isEmpty();
	}

	public void addValue(List<HEasyJModelValue<V>> value) {
		int index0 = this.values.size();
		this.values.addAll(value);
		int index1 = this.values.size();
		index1 = (index1 == 0 ? 0 : index1 - 1);
		this.fireIntervalAdded(this, index0, index1);
	}

	public void addValues(HEasyJModelValue<V> values) {
		int index0 = this.values.size();
		this.values.add(values);
		this.fireIntervalAdded(this, index0, index0);
	}

	public void clearValues() {
		if (values.isEmpty()) {
			return;
		}
		int index1 = values.size() - 1;
		this.values.clear();
		if (index1 >= 0) {
			this.fireIntervalRemoved(this, 0, index1);
		}
	}

	public void removeValue(HEasyJModelValue<V> value) {
		int index = values.indexOf(value);
		this.values.remove(value);
		if (index >= 0) {
			this.fireIntervalRemoved(this, index, index);
		}
	}

	public void removeValues(List<HEasyJModelValue<V>> value) {
		if (value.isEmpty()) {
			return;
		}
		int index1 = this.values.size() - 1;
		this.values.removeAll(value);
		if (index1 >= 0) {
			this.fireIntervalRemoved(this, 0, index1);
		}
	}

	public HEasyJModelValue<V> getValue(int index) {
		return values.get(index);
	}

//	public HEasyJModelValue<V>[] getValues(){
//		return values.toArray(new HEasyJModelValue[values.size()]);
//	}

	public List<HEasyJModelValue<V>> getValues() {
		return values;
	}

}
