package org.hxzon.swing.components.ext;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;

import org.hxzon.swing.components.easy.HEasyJComboBox;
import org.hxzon.swing.model.HEasyJModelValue;
import org.hxzon.swing.util.HChangeListener;
import org.hxzon.swing.util.HChangeListenerSupport;

import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

public class HDatePicker extends HEasyJComboBox<String> {
	private static final long serialVersionUID = 1L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private final JTextField textField;
	private final Calendar calendar = Calendar.getInstance();
	private final HChangeListenerSupport<HDatePicker> listenerSupport = new HChangeListenerSupport<HDatePicker>();

//	@Override
//	public void showPopup() {
//		super.showPopup();
//	}

	public HDatePicker() {
		textField = ((JTextField) this.getEditor().getEditorComponent());
		this.setBackground(Color.white);
		this.setEditable(true);
//		this.setFocusable(true);
	}

	public void useCurrentDate() {
		calendar.setTime(new Date());
	}

	public void setDate(Date date) {
		calendar.setTime(date);
	}

	public void setSelectedItem(Object item) {
		if (item instanceof String) {
			String value=(String)item;
			super.setSelectedItem(new HEasyJModelValue<String>(value,value,false));
			textField.setText(value);
		}
	}

	class DatePopup extends BasicComboPopup {
		private static final long serialVersionUID = 1L;
		protected HDatePanel datePanel = null;

		public DatePopup(JComboBox box) {
			super(box);
			initializePopup();
			this.setOpaque(true);
		}

		protected void initializePopup() {
			removeAll();
			datePanel = new HDatePanel();
			datePanel.addChangeListener(new HChangeListener<HDatePanel>() {
				@Override
				public void change(HDatePanel source) {
					comboBox.setSelectedItem(source.getDateAsString());
					hide();
				}
			});
			add(datePanel, "30");
		}

		public void show()// setVisible()
		{
			updatePopup();
			super.show();
		}

		protected void updatePopup() {
			datePanel.setDate(calendar.getTime());
			datePanel.repaint();
			pack();
		}
	}

	public void updateUI() {
		ComboBoxUI cui = (ComboBoxUI) UIManager.getUI(this);

		if (cui instanceof MetalComboBoxUI) {
			cui = new MetalDateComboBoxUI();
		} else if (cui instanceof MotifComboBoxUI) {
			cui = new MotifDateComboBoxUI();
		} else if (cui instanceof WindowsComboBoxUI) {
			cui = new WindowsDateComboBoxUI();
		}
		setUI(cui);
	}

	/*
	class McWinComboBoxUI extends com.qq.plaf.mcwin.McWinComboBoxUI
	{
	  protected ComboPopup createPopup()
	  {
	      return new DatePopup(comboBox);
	  }
	}
	*/

	// UI Inner classes -- one for each supported Look and Feel

	class MetalDateComboBoxUI extends MetalComboBoxUI {
		protected ComboPopup createPopup() {
			return new DatePopup(comboBox);
		}
	}

	class WindowsDateComboBoxUI extends WindowsComboBoxUI {
		protected ComboPopup createPopup() {
			return new DatePopup(comboBox);
		}
	}

	class MotifDateComboBoxUI extends MotifComboBoxUI {
		private static final long serialVersionUID = 1L;

		protected ComboPopup createPopup() {
			return new DatePopup(comboBox);
		}
	}

	public void setCanNotNull() {

		// isCanNull = false;
//		this.clear.setVisible(false);

	}

}
