package org.hxzon.swing.components.ext;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;

import org.hxzon.swing.components.easy.HEasyJCheckBox;
import org.hxzon.swing.components.easy.HEasyJComboBox;
import org.hxzon.swing.model.HEasyJModelValue;
import org.hxzon.swing.util.HEasyJModelValueUtil;

import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

public class HMultiComboBox<V> extends HEasyJComboBox<V> {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private List<HEasyJCheckBox<V>> checkBoxs = new ArrayList<HEasyJCheckBox<V>>();

	private CheckBoxChangeListener checkBoxListener = new CheckBoxChangeListener();

	public HMultiComboBox() {
		textField = ((JTextField) this.getEditor().getEditorComponent());
		textField.setEditable(false);
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.setEditable(true);
	}

//	@Override
//	public void showPopup() {
//		super.showPopup();
//	}

	public void addItem(HEasyJModelValue<V> item) {
		this.getModel().addValue(item);
		HEasyJCheckBox<V> box = new HEasyJCheckBox<V>(item);
		checkBoxs.add(box);
		box.addItemListener(checkBoxListener);
		box.setSelected(false);//for fire listener
		box.setSelected(item.isSel);//for fire listener
		box.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

	public void addItems(List<HEasyJModelValue<V>> items) {
		for (HEasyJModelValue<V> item : items) {
			addItem(item);
		}
	}

	public void clearItems() {
		checkBoxs.clear();
	}

	public void setItems(List<HEasyJModelValue<V>> items) {
		clearItems();
		addItems(items);
//		this.setSelectedItem(str1);
	}

	public String getValueAsString() {
		StringBuilder sb = new StringBuilder();
		for (HEasyJCheckBox<V> box : checkBoxs) {
			if (box.isSelected())
				sb.append(box.getText()).append(";");
		}
		return sb.toString();
	}

	public List<V> getSelectedValues() {
		return HEasyJModelValueUtil.getValues(checkBoxs, true);
	}

	public List<HEasyJModelValue<V>> getSelectedValueWraps() {
		return HEasyJModelValueUtil.getValueWraps(checkBoxs, true);
	}

	public List<V> getUnselectedValues() {
		return HEasyJModelValueUtil.getValues(checkBoxs, false);
	}

	public List<HEasyJModelValue<V>> getUnselectedValueWraps() {
		return HEasyJModelValueUtil.getValueWraps(checkBoxs, false);
	}

	@SuppressWarnings("unchecked")
	public void setSelectedItem(Object item) {
		if (item instanceof HEasyJModelValue<?>) {
			textField.setText(((HEasyJModelValue<V>) item).display);
//			super.setSelectedItem(item);
		}
	}

	class HCheckBoxComboPopup extends BasicComboPopup {
		private static final long serialVersionUID = 1L;

//		JPanel panel = new JPanel();

		public HCheckBoxComboPopup(JComboBox box) {
			super(box);
			initializePopup();
		}

		@Override
		public void show() {
			updatePop();
			super.show();
		}

		protected void initializePopup() {
//			setLayout(new BorderLayout());
//			removeAll();
//			panel.setBackground(Color.white);
//			panel.setLayout(new PercentLayout(PercentLayout.VERTICAL, 2));
//			add(panel);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			updatePop();
		}

		@Override
		public void hide() {
			// super.hide();
		}

		void updatePop() {
			this.setPreferredSize(new Dimension(HMultiComboBox.this.getWidth(), this.getComponentCount() * 25));
			this.removeAll();
			if (checkBoxs == null) {
				return;
			}
			for (HEasyJCheckBox<V> checkBox : checkBoxs) {
				this.add(checkBox);
				checkBox.setOpaque(false);
			}
//			panel.setPreferredSize(new Dimension(JMultiComboBox.this.getWidth(), checkBoxs.size() * 23));
			this.setPreferredSize(new Dimension(HMultiComboBox.this.getWidth(), checkBoxs.size() * 23));
			setPreferredSize(new Dimension(HMultiComboBox.this.getWidth(), checkBoxs.size() * 23));
//			panel.validate();
			this.validate();
			this.repaint();

			pack();
		}
	}

	private class CheckBoxChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			setSelectedItem(new HEasyJModelValue<V>(null, getValueAsString(), false));
		}
	}

	public void updateUI() {
		ComboBoxUI cui = (ComboBoxUI) UIManager.getUI(this);

		if (cui instanceof MetalComboBoxUI) {
			cui = new MetalHCheckBoxComboBoxUI();
		} else if (cui instanceof MotifComboBoxUI) {
			cui = new MotifHCheckBoxComboBoxUI();
		} else if (cui instanceof WindowsComboBoxUI) {
			cui = new WindowsHCheckBoxComboBoxUI();
		}

		setUI(cui);

	}

	class MetalHCheckBoxComboBoxUI extends MetalComboBoxUI {
		protected ComboPopup createPopup() {
			return new HCheckBoxComboPopup(comboBox);
		}
	}

	class WindowsHCheckBoxComboBoxUI extends WindowsComboBoxUI {
		protected ComboPopup createPopup() {
			return new HCheckBoxComboPopup(comboBox);
		}
	}

	class MotifHCheckBoxComboBoxUI extends MotifComboBoxUI {
		private static final long serialVersionUID = 1L;

		protected ComboPopup createPopup() {
			return new HCheckBoxComboPopup(comboBox);
		}
	}

}
