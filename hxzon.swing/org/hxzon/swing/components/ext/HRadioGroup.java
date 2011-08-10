package org.hxzon.swing.components.ext;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import org.hxzon.swing.components.easy.HEasyJRadioButton;
import org.hxzon.swing.model.HEasyJModelValue;
import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;

public class HRadioGroup<V> {
	private HChangeListenerSupport<HRadioGroup<V>> listenerSupport = new HChangeListenerSupport<HRadioGroup<V>>();
	private List<HEasyJRadioButton<V>> radioButtons = new ArrayList<HEasyJRadioButton<V>>();
	private HEasyJRadioButton<V> selected;
	private ItemListener radioButtonListener;

	private boolean pauseFireListener = false;

	public HRadioGroup() {
		radioButtonListener = new ItemListener() {
			@SuppressWarnings("unchecked")
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selected = (HEasyJRadioButton<V>) e.getSource();
					fireChangeListener();
				}
			}
		};
	}

	public void setItems(List<HEasyJModelValue<V>> values) {
		radioButtons.clear();
		addItems(values);
	}

	public void addItems(List<HEasyJModelValue<V>> values) {
		for (HEasyJModelValue<V> value : values) {
			addItem(value);
		}
	}

	public void addItem(HEasyJModelValue<V> value) {
		HEasyJRadioButton<V> box = new HEasyJRadioButton<V>(value);
		radioButtons.add(box);
		box.addItemListener(radioButtonListener);
	}

	public HEasyJModelValue<V> getSelectedValueWrap() {
		return selected.getValueWrap();
	}

	public V getSelectedValue() {
		return selected.getValue();
	}

	public void addChangeListener(HChangeListener<HRadioGroup<V>> listener) {
		listenerSupport.addChangeListener(listener);
	}

	public void removeChangeListener(HChangeListener<HRadioGroup<V>> listener) {
		listenerSupport.removeChangeListener(listener);
	}

	private void fireChangeListener() {
		if (pauseFireListener) {
			return;
		}
		listenerSupport.fireChangeListener(this);
	}

	public JRadioButton getRadioButton(String name) {
		if (name == null) {
			return null;
		}
		for (JRadioButton button : radioButtons) {
			if (name.equals(button.getName()))
				return button;
		}
		return null;
	}

	public List<HEasyJRadioButton<V>> getRadioButtons() {
		return radioButtons;
	}

	public void setEnabled(boolean b) {
		for (JRadioButton button : radioButtons) {
			button.setEnabled(b);
		}
	}

}
