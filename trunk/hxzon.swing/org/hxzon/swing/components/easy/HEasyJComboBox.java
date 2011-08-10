package org.hxzon.swing.components.easy;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import org.hxzon.swing.model.HEasyJComboBoxModel;
import org.hxzon.swing.model.HEasyJModelValue;

public class HEasyJComboBox<V> extends JComboBox {
	private static final long serialVersionUID = 1L;

	public HEasyJComboBox() {
		super(new HEasyJComboBoxModel<V>());
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

	@SuppressWarnings("unchecked")
	public HEasyJModelValue<V> getValueWrap() {
		return (HEasyJModelValue<V>) super.getSelectedItem();
	}

	public V getValue() {
		return getValueWrap().value;
	}

	public void addItem(HEasyJModelValue<V> value) {
		this.getModel().addValue(value);
	}

	@SuppressWarnings("unchecked")
	public HEasyJComboBoxModel<V> getModel() {
		return (HEasyJComboBoxModel<V>) super.getModel();
	}

	public void setModel(ComboBoxModel model) {
		if (model instanceof HEasyJComboBoxModel) {
			super.setModel(model);
		}
	}

//	public ExListValue<V>[] getSelectedValues() {
//		ListSelectionModel sm = getSelectionModel();
//		ExComboBoxModel<V> dm = getModel();
//
//		int iMin = sm.getMinSelectionIndex();
//		int iMax = sm.getMaxSelectionIndex();
//
//		if ((iMin < 0) || (iMax < 0)) {
//			return new ExListValue[0];
//		}
//
//		ExListValue<V>[] rvTmp = new ExListValue[1 + (iMax - iMin)];
//		int n = 0;
//		for (int i = iMin; i <= iMax; i++) {
//			if (sm.isSelectedIndex(i)) {
//				rvTmp[n++] = dm.getElementAt(i);
//			}
//		}
//		return rvTmp;
//	}

}
