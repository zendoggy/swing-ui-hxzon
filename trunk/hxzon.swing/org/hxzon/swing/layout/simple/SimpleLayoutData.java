package org.hxzon.swing.layout.simple;

public class SimpleLayoutData {
	public int fixedSize = -1;
	public int fixedPercent = -1;
	public boolean canHide;

//	private SimpleLayoutData(){
//	}

	private SimpleLayoutData(int size, int percent) {
		this.fixedSize = size;
		this.fixedPercent = percent;
	}

	public SimpleLayoutData canHide(boolean canHide) {
		this.canHide = canHide;
		return this;
	}

	public static SimpleLayoutData fixedSize(int fixedSize) {
		return new SimpleLayoutData(fixedSize, -1);
	}

	public static SimpleLayoutData fillPercent(int percent) {
		return new SimpleLayoutData(-1, percent);
	}

	public static SimpleLayoutData preferredSize() {
		return null;
	}

	public boolean isFixedSize() {
		return fixedSize != -1;
	}

	public boolean isCanHide() {
		return canHide;
	}
}
