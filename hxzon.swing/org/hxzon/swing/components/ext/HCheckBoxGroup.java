package org.hxzon.swing.components.ext;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;

import org.hxzon.swing.components.easy.HEasyJCheckBox;
import org.hxzon.swing.model.HEasyJModelValue;
import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;
import org.hxzon.swing.util.HEasyJModelValueUtil;

public class HCheckBoxGroup<V> {
	private HChangeListenerSupport<HCheckBoxGroup<V>> listenerSupport = new HChangeListenerSupport<HCheckBoxGroup<V>>();
	private List<HEasyJCheckBox<V>> checkBoxs = new ArrayList<HEasyJCheckBox<V>>();
	private ItemListener checkBoxListener;

	private boolean pauseFireListener = false;

	public HCheckBoxGroup() {
		checkBoxListener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fireChangeListener();
			}
		};
	}

	public void setItems(List<HEasyJModelValue<V>> values) {
		checkBoxs.clear();
		addItems(values);
	}

	public void addItems(List<HEasyJModelValue<V>> values) {
		for (HEasyJModelValue<V> value : values) {
			addItem(value);
		}
	}

	public void addItem(HEasyJModelValue<V> value) {
		HEasyJCheckBox<V> box = new HEasyJCheckBox<V>(value);
		checkBoxs.add(box);
		box.addItemListener(checkBoxListener);
	}

	public List<HEasyJModelValue<V>> getSelectedValueWraps() {
		return HEasyJModelValueUtil.getValueWraps(checkBoxs, true);
	}

	public List<V> getSelectedValues() {
		return HEasyJModelValueUtil.getValues(checkBoxs, true);
	}

	public List<HEasyJModelValue<V>> getUnselectedValueWraps() {
		return HEasyJModelValueUtil.getValueWraps(checkBoxs, false);
	}

	public List<V> getUnselectedValues() {
		return HEasyJModelValueUtil.getValues(checkBoxs, false);
	}

	public boolean isSelectAll() {
		return getSelectedSize() == checkBoxs.size();
	}

	public boolean isSelectEmpty() {
		return getSelectedSize() == 0;
	}

	public int getSelectedSize() {
		int selectedCount = 0;
		for (HEasyJCheckBox<V> box : checkBoxs) {
			if (box.isSelected()) {
				selectedCount++;
			}
		}
		return selectedCount;
	}

	public void addChangeListener(HChangeListener<HCheckBoxGroup<V>> listener) {
		listenerSupport.addChangeListener(listener);
	}

	public void removeChangeListener(HChangeListener<HCheckBoxGroup<V>> listener) {
		listenerSupport.removeChangeListener(listener);
	}

	private void fireChangeListener() {
		if (pauseFireListener) {
			return;
		}
		listenerSupport.fireChangeListener(this);
	}

	public JCheckBox getCheckBox(String name) {
		if (name == null) {
			return null;
		}
		for (JCheckBox button : checkBoxs) {
			if (name.equals(button.getName()))
				return button;
		}
		return null;
	}

	public List<HEasyJCheckBox<V>> getCheckBoxs() {
		return checkBoxs;
	}

	public void setEnabled(boolean b) {
		for (JCheckBox button : checkBoxs) {
			button.setEnabled(b);
		}
	}

	public void selectAll() {
		pauseFireListener = true;
		for (HEasyJCheckBox<V> box : checkBoxs) {
			box.setSelected(true);
		}
		pauseFireListener = false;
		fireChangeListener();
	}

	public void selectEmpty() {
		pauseFireListener = true;
		for (HEasyJCheckBox<V> box : checkBoxs) {
			box.setSelected(false);
		}
		pauseFireListener = false;
		fireChangeListener();
	}

//	public JCheckBox getAllCheckBox(String text, String name) {
//		JCheckBox all = new JCheckBox();
//		all.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
//				pauseFireListener = true;
//				for (JCheckBox button : checkBoxs) {
//					button.setSelected(selected);
//				}
//				pauseFireListener = false;
//				fireChangeListener();
//			}
//		});
//		return all;
//	}
//
//	public void addAllButton(JCheckBox button) {
//		button.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
//				pauseFireListener = true;
//				for (JCheckBox button : checkBoxs) {
//					button.setSelected(selected);
//				}
//				pauseFireListener = false;
//				fireChangeListener();
//			}
//		});
//	}
//
//	public void setAllowAll(boolean allow) {
//
//	}

}
