package org.hxzon.swing.components.easy;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;

public class HYearSpinner extends JSpinner {
	private static final long serialVersionUID = 1L;
	private final HChangeListenerSupport<HYearSpinner> listenerSupport = new HChangeListenerSupport<HYearSpinner>();

	public HYearSpinner() {
		this(1, 9999);
	}

	public HYearSpinner(int minimum, int maximum) {
		super(new SpinnerNumberModel(1, minimum, maximum, 1));
		setEditor(new JSpinner.NumberEditor(this, "0"));
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				listenerSupport.fireChangeListener(HYearSpinner.this);
			}
		});
	}

	public int getYear() {
		return (Integer) getValue();
	}

	public void setYear(int year) {
		setValue(year);
	}

	public void addChangeListener(HChangeListener<HYearSpinner> listener) {
		listenerSupport.addChangeListener(listener);
	}

	public void removeChangeListener(HChangeListener<HYearSpinner> listener) {
		listenerSupport.removeChangeListener(listener);
	}
}
