package test.swing;

import java.awt.Color;

public class PointData {
	private Color color;
	private int data[];
	private String name;
	private boolean show = true;

	public PointData(String name, Color color, int[] data) {
		this.name = name;
		this.color = color;
		this.data = data;
	}

	public Color getColor() {
		return color;
	}

	public int[] getData() {
		return data;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public boolean isShow() {
		return show;
	}
}
