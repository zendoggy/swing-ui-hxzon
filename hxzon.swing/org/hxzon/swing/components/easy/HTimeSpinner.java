package org.hxzon.swing.components.easy;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;

public class HTimeSpinner extends JSpinner {

	private static final long serialVersionUID = 1946692217689575856L;
	private final Calendar calendar = Calendar.getInstance();
	private final HChangeListenerSupport<HTimeSpinner> listenerSupport = new HChangeListenerSupport<HTimeSpinner>();

	public HTimeSpinner() {
		super(new SpinnerDateModel());
		JSpinner.DateEditor editor = new JSpinner.DateEditor(this, "HH:mm:ss");
		editor.getTextField().setHorizontalAlignment(JTextField.RIGHT);
		setEditor(editor);
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				listenerSupport.fireChangeListener(HTimeSpinner.this);
			}
		});
	}

	public void setTime(int hour, int minute, int second) {
		useCurrentTime();
		calendar.setTime(getDate());
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
	}

	public void useCurrentTime() {
		super.setValue(new Date());
	}

	public Date getDate() {
		return (Date) super.getValue();
	}

	public int getHour() {
		calendar.setTime(getDate());
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		calendar.setTime(getDate());
		return calendar.get(Calendar.MINUTE);
	}

	public int getSecond() {
		calendar.setTime(getDate());
		return calendar.get(Calendar.SECOND);
	}

	public int getSecondInDay() {
		calendar.setTime(getDate());
		return calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
	}

	public void addChangeListener(HChangeListener<HTimeSpinner> listener) {
		listenerSupport.addChangeListener(listener);
	}

	public void removeChangeListener(HChangeListener<HTimeSpinner> listener) {
		listenerSupport.removeChangeListener(listener);
	}
}
