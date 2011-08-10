package layout.formlayout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.HashMap;
import java.util.Map;

public class FormLayout implements LayoutManager2 {
	protected Map<Component, FormData> componentMap;

	public FormLayout() {
		componentMap = new HashMap<Component, FormData>();
	}

	public void layoutContainer(Container target) {
		synchronized (target.getTreeLock()) {
			int w = target.getWidth();
			int h = target.getHeight();
			Component[] components = target.getComponents();
			for (Component component : components) {
				FormData formData = componentMap.get(component);
				if (formData == null) {
					continue;
				}
				FormAttachment left = formData.left;
				FormAttachment right = formData.right;
				FormAttachment top = formData.top;
				FormAttachment bottom = formData.bottom;
				int x = (int) (left.percentage * w) / 100 + left.offset;
				int y = (int) (top.percentage * h) / 100 + top.offset;
				int width = 0;
				int height = 0;
				if (right == null || bottom == null) {
					Dimension size = component.getPreferredSize();
					if (size == null) {
						throw new RuntimeException("if right FormAttachment or bottom FormAttachment is null,the component must have preferred-size");
					} else {
						width = size.width;
						height = size.height;
					}
				} else {
					int x2 = (int) (right.percentage * w)/100 - right.offset;
					int y2 = (int) (bottom.percentage * h)/100 - bottom.offset;
					width = x2 - x;
					height = y2 - y;
				}
				component.setBounds(x, y, width, height);
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
				if (formData.left == null || formData.top == null) {
					throw new IllegalArgumentException("left FormAttachment and top FormAttachment can't be null");
				}
				componentMap.put(comp, (FormData) constraints);
			}
		}
	}

	public void invalidateLayout(Container target) {

	}

	/**
	 * @deprecated
	 */
	public void addLayoutComponent(String name, Component comp) {
		//never use in LayoutManager2
	}

	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension(0, 0);
	}

	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(0, 0);
	}

	public float getLayoutAlignmentX(Container target) {
		return 0;
	}

	public float getLayoutAlignmentY(Container target) {
		return 0;
	}

	public void removeLayoutComponent(Component comp) {
		synchronized (comp.getTreeLock()) {
			componentMap.remove(comp);
		}
	}

}
