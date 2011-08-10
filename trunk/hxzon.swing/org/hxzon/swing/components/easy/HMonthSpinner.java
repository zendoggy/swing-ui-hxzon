package org.hxzon.swing.components.easy;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;

public class HMonthSpinner extends JSpinner {

	private static final long serialVersionUID = 1L;
	private final HChangeListenerSupport<HMonthSpinner> listenerSupport = new HChangeListenerSupport<HMonthSpinner>();

	public HMonthSpinner() {
		super(new SpinnerNumberModel(1, 1, 12, 1));
		setEditor(new JSpinner.NumberEditor(this, "0"));
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				listenerSupport.fireChangeListener(HMonthSpinner.this);
			}
		});
	}

	public void setMonth(int newMonth) {
		setValue(newMonth);
	}

	public int getMonth() {
		return (Integer) getValue();
	}

	public void addChangeListener(HChangeListener<HMonthSpinner> listener) {
		listenerSupport.addChangeListener(listener);
	}

	public void removeChangeListener(HChangeListener<HMonthSpinner> listener) {
		listenerSupport.removeChangeListener(listener);
	}
}
