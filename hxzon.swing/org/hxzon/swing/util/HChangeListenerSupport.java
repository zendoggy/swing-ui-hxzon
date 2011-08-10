package org.hxzon.swing.util;

import java.util.ArrayList;
import java.util.List;

public class HChangeListenerSupport<V> {

	private List<HChangeListener<V>> listeners = new ArrayList<HChangeListener<V>>();

	public void addChangeListener(HChangeListener<V> listener) {
		listeners.add(listener);
	}

	public void removeChangeListener(HChangeListener<V> listener) {
		listeners.remove(listener);
	}

	public void clearChangeListener() {
		listeners.clear();
	}

	public void fireChangeListener(V source) {
		for (HChangeListener<V> listener : listeners) {
			listener.change(source);
		}
	}
}
