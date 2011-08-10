package org.hxzon.swing.util;

import java.util.ArrayList;
import java.util.List;

import org.hxzon.swing.components.easy.HEasyJCheckBox;
import org.hxzon.swing.model.HEasyJModelValue;

public class HEasyJModelValueUtil {

	public static <V> List<V> getValues(List<HEasyJCheckBox<V>> checkBoxs, boolean check) {
		List<V> values = new ArrayList<V>();
		for (HEasyJCheckBox<V> box : checkBoxs) {
			if (box.isSelected() == check) {
				values.add(box.getValue());
			}
		}
		return values;
	}

	public static <V> List<HEasyJModelValue<V>> getValueWraps(List<HEasyJCheckBox<V>> checkBoxs, boolean check) {
		List<HEasyJModelValue<V>> values = new ArrayList<HEasyJModelValue<V>>();
		for (HEasyJCheckBox<V> box : checkBoxs) {
			if (box.isSelected() == check) {
				values.add(box.getValueWrap());
			}
		}
		return values;
	}

	public static <V> List<V> getValues(List<HEasyJModelValue<V>> wraps) {
		List<V> values = new ArrayList<V>();
		for (HEasyJModelValue<V> wrap : wraps) {
			values.add(wrap.value);
		}
		return values;
	}

}
