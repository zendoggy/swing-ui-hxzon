package org.hxzon.swing.components.ext;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormatSymbols;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;

import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;
import org.hxzon.swing.util.NewComboPopup;

public class HMonthComboSpinner extends JPanel implements ItemListener, ChangeListener {

	private static final long serialVersionUID = 1L;

	private int month;

	private int oldSpinnerValue = 0;

	private JComboBox comboBox;
	private final HChangeListenerSupport<HMonthComboSpinner> listenerSupport = new HChangeListenerSupport<HMonthComboSpinner>();

	public HMonthComboSpinner() {
		super(new BorderLayout());
        comboBox = new JComboBox(){
            public void updateUI() {
                setUI(new BasicComboBoxUI(){
                    protected ComboPopup createPopup() {
                        return new NewComboPopup(comboBox);
                    }
                });
            }    
        };
//		comboBox = new JComboBox();
		comboBox.addItemListener(this);

		String[] monthNames = new DateFormatSymbols().getMonths();

		for (int i = 0; i < 12; i++) {
			comboBox.addItem(monthNames[i]);
		}

		JSpinner spinner = new JSpinner();
		spinner.setEditor(comboBox);
		comboBox.setBorder(null);
		spinner.addChangeListener(this);

		add(spinner, BorderLayout.CENTER);
	}

	public void stateChanged(ChangeEvent e) {
		SpinnerNumberModel model = (SpinnerNumberModel) ((JSpinner) e.getSource()).getModel();
		int value = model.getNumber().intValue();
		boolean increase = (value > oldSpinnerValue) ? true : false;
		oldSpinnerValue = value;

		int newMonth = getMonth();
		if (increase) {
			newMonth += 1;
			if (newMonth == 12) {
				newMonth = 0;
//                yearSpinner.setYear(( yearSpinner.getYear()) + 1);
			}
		} else {
			newMonth -= 1;
			if (newMonth == -1) {
				newMonth = 11;
//                yearSpinner.setYear(( yearSpinner.getYear()) - 1);
			}
		}
		setMonth(newMonth);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int index = comboBox.getSelectedIndex();

//            if ((index >= 0) && (index != month)) {
			setMonth(index);
//            }
		}
	}

	public void setMonth(int newMonth) {
//        if (newMonth < 0 || newMonth == Integer.MIN_VALUE) {
//            newMonth = 0;
//        } else if (newMonth > 11) {
//            newMonth = 11;
//        }
		int oldMonth = month;
		month = newMonth;
		comboBox.setSelectedIndex(month);
		listenerSupport.fireChangeListener(this);
	}

	public int getMonth() {
		return month;
	}

	public void addChangeListener(HChangeListener<HMonthComboSpinner> listener) {
		listenerSupport.addChangeListener(listener);
	}

	public void removeChangeListener(HChangeListener<HMonthComboSpinner> listener) {
		listenerSupport.removeChangeListener(listener);
	}
}
