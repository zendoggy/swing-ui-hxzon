package layout.formlayout;

import java.awt.Component;
import java.awt.Container;

public class CenterLayout extends FormLayout {
	public synchronized void layoutContainer(Container target) {
		synchronized (target.getTreeLock()) {
			int w = target.getWidth();
			int h = target.getHeight();
			Component[] components = target.getComponents();
			for (Component component : components) {
				FormData formData = componentMap.get(component);
				if (formData != null) {
					FormAttachment centerX = formData.centerX;
					FormAttachment centerY = formData.centerY;
					int width = component.getPreferredSize().width;
					int height = component.getPreferredSize().height;
					int x = (int) (centerX.percentage * w) / 100 + centerX.offset - width / 2;
					int y = (int) (centerY.percentage * h) / 100 + centerY.offset - height / 2;
					component.setBounds(x, y, width, height);
				}
			}
		}
	}

	public void addLayoutComponent(Component comp, Object constraints) {
		if (constraints == null) {
			throw new IllegalArgumentException("constraints can't be null");
		} else if (!(constraints instanceof FormData)) {
			throw new IllegalArgumentException("constraints must be a " + FormData.class.getName() + " instance");
		} else {
			synchronized (comp.getTreeLock()) {
				FormData formData = (FormData) constraints;
				if (formData.centerX == null || formData.centerY == null) {
					throw new IllegalArgumentException("centerX FormAttachment and centerY FormAttachment can't be null");
				} else if (comp.getPreferredSize() == null) {
					throw new RuntimeException("component must have preferred size before be add into parent with CenterLayout");
				}
				componentMap.put(comp, (FormData) constraints);
			}
		}
	}
}
