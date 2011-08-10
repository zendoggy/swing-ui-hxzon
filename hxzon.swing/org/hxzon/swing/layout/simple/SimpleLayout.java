package org.hxzon.swing.layout.simple;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.hxzon.util.DebugUtil;

public class SimpleLayout implements LayoutManager2 {
	protected Map<Component, SimpleLayoutData> componentMap;
	protected boolean horizontal;

	public SimpleLayout() {
		this(false);
	}

	public SimpleLayout(boolean horizontal) {
		this.horizontal = horizontal;
		componentMap = new HashMap<Component, SimpleLayoutData>();
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		//never use
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		componentMap.put(comp, (SimpleLayoutData) constraints);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		componentMap.remove(comp);
	}

	//if fixedSize use fixedSize,else use preferredSize
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		int resultWidth = 0;
		int resultHeight = 0;
		Insets insets = parent.getInsets();
		if (horizontal) {//h
			for (Entry<Component, SimpleLayoutData> e : componentMap.entrySet()) {
				SimpleLayoutData layoutData = e.getValue();
				Dimension compPreferredSize = getCompPreferredSize(e.getKey());
				if (layoutData == null) {
					resultWidth += compPreferredSize.width;
				} else if (layoutData.isFixedSize()) {
					resultWidth += layoutData.fixedSize;
				} else {
					resultWidth += compPreferredSize.width;
				}
				resultHeight = Math.max(resultHeight, compPreferredSize.height);
			}
		} else {//v
			for (Entry<Component, SimpleLayoutData> e : componentMap.entrySet()) {
				SimpleLayoutData layoutData = e.getValue();
				Dimension compPreferredSize = getCompPreferredSize(e.getKey());
				if (layoutData == null) {
					resultHeight += compPreferredSize.height;
				} else if (layoutData.isFixedSize()) {
					resultHeight += layoutData.fixedSize;
				} else {
					resultHeight += compPreferredSize.height;
				}
				resultWidth = Math.max(resultWidth, compPreferredSize.width);
			}
		}
		resultWidth += (insets.left + insets.right);
		resultHeight += (insets.top + insets.bottom);
		return new Dimension(resultWidth, resultHeight);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return preferredLayoutSize(target);
	}

	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Component[] components = parent.getComponents();
			Insets insets = parent.getInsets();
			int x = insets.left;
			int y = insets.top;
			int parentWidth = parent.getWidth() - insets.left - insets.right;
			int parentHeight = parent.getHeight() - insets.top - insets.bottom;
//			Dimension preferredParentSize = this.preferredLayoutSize(parent);
			int fillSize = 0;
			int fixedSize = getTotalFixedSize();
			if (horizontal) {//h
				fillSize = parentWidth - fixedSize;
				DebugUtil.debug("parent size:" + parentWidth);
			} else {//v
				fillSize = parentHeight - fixedSize;
				DebugUtil.debug("parent size:" + parentHeight);
			}
			layoutWhenFull(components, parent, fillSize, parentWidth, parentHeight, x, y);
		}

	}

	private Dimension getCompPreferredSize(Component comp) {
//		if (comp instanceof JScrollPane) {
//			return ((JScrollPane) comp).getViewport().getPreferredSize();
//		} else {
		return comp.getPreferredSize();
//		}
	}

//	private int getTotalPercent(){
//		int result=0;
//		for(SimpleLayoutData e : componentMap.values()){
//			if(!e.isFixedSize()){
//				result+=e.fixedPercent;
//			}
//		}
//		return result;
//	}

	private int getTotalFixedSize() {
		int result = 0;
		for (SimpleLayoutData layoutData : componentMap.values()) {
			if (layoutData != null && layoutData.isFixedSize()) {
				result += layoutData.fixedSize;
			}
		}
		DebugUtil.debug("total fixed size:" + result);
		return result;
	}

	private void layoutWhenFull(Component[] components, Container parent, int fillSize, int parentWidth, int parentHeight, int x, int y) {
		int curSize = 0;
		DebugUtil.debug("total fill size:" + fillSize);
		if (horizontal) {//h
			for (Component comp : components) {
				SimpleLayoutData layoutData = componentMap.get(comp);
				if (layoutData == null) {
					curSize = getCompPreferredSize(comp).width;
					DebugUtil.debug("preferred size:" + curSize);
				} else if (layoutData.isFixedSize()) {
					curSize = layoutData.fixedSize;
					DebugUtil.debug("fixed size:" + curSize);
				} else {
					curSize = fillSize * layoutData.fixedPercent / 100;
					DebugUtil.debug("fill size:" + curSize + ", percent:" + layoutData.fixedPercent);
				}
				comp.setBounds(x, y, curSize, parentHeight);
				x += curSize;
			}
		} else {//v
			for (Component comp : components) {
				SimpleLayoutData layoutData = componentMap.get(comp);
				if (layoutData == null) {
					curSize = getCompPreferredSize(comp).height;
					DebugUtil.debug("preferred size:" + curSize);
				} else if (layoutData.isFixedSize()) {
					curSize = layoutData.fixedSize;
					DebugUtil.debug("fixed size:" + curSize);
				} else {
					curSize = fillSize * layoutData.fixedPercent / 100;
					DebugUtil.debug("fill size:" + curSize + ", percent:" + layoutData.fixedPercent);
				}
				comp.setBounds(x, y, parentWidth, curSize);
				y += curSize;
			}
		}
		DebugUtil.debug("-----------------------------");
	}

	@SuppressWarnings("unused")
	private void layoutWhenPoor() {

	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0;
	}

	@Override
	public void invalidateLayout(Container target) {

	}

}
