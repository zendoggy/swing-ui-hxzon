package test.swing;

import java.awt.Color;

public class FlowStatisticsData {
	private Color color;
	private String name;
	private int[] packetNumPer100;
	private int[] packetNumPer1000;
	private int[] bitNumPer100;
	private int[] bitNumPer1000;
	//max
	private int maxPacketNumPer100;
	private int maxPacketNumPer1000;
	private int maxBitNumPer100;
	private int maxBitNumPer1000;
	//min
	private int minPacketNumPer100;
	private int minPacketNumPer1000;
	private int minBitNumPer100;
	private int minBitNumPer1000;
	private boolean show = true;

	public FlowStatisticsData(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	public int[] getPacketNumPer100() {
		return packetNumPer100;
	}

	public void setPacketNumPer100(int[] packetNumPer100) {
		this.packetNumPer100 = packetNumPer100;
	}

	public int[] getPacketNumPer1000() {
		return packetNumPer1000;
	}

	public void setPacketNumPer1000(int[] packetNumPer1000) {
		this.packetNumPer1000 = packetNumPer1000;
	}

	public int[] getBitNumPer100() {
		return bitNumPer100;
	}

	public void setBitNumPer100(int[] bitNumPer100) {
		this.bitNumPer100 = bitNumPer100;
	}

	public int[] getBitNumPer1000() {
		return bitNumPer1000;
	}

	public void setBitNumPer1000(int[] bitNumPer1000) {
		this.bitNumPer1000 = bitNumPer1000;
	}

	public int getMaxPacketNumPer100() {
		return maxPacketNumPer100;
	}

	public void setMaxPacketNumPer100(int maxPacketNumPer100) {
		this.maxPacketNumPer100 = maxPacketNumPer100;
	}

	public int getMaxPacketNumPer1000() {
		return maxPacketNumPer1000;
	}

	public void setMaxPacketNumPer1000(int maxPacketNumPer1000) {
		this.maxPacketNumPer1000 = maxPacketNumPer1000;
	}

	public int getMaxBitNumPer100() {
		return maxBitNumPer100;
	}

	public void setMaxBitNumPer100(int maxBitNumPer100) {
		this.maxBitNumPer100 = maxBitNumPer100;
	}

	public int getMaxBitNumPer1000() {
		return maxBitNumPer1000;
	}

	public void setMaxBitNumPer1000(int maxBitNumPer1000) {
		this.maxBitNumPer1000 = maxBitNumPer1000;
	}

	public int getMinPacketNumPer100() {
		return minPacketNumPer100;
	}

	public void setMinPacketNumPer100(int minPacketNumPer100) {
		this.minPacketNumPer100 = minPacketNumPer100;
	}

	public int getMinPacketNumPer1000() {
		return minPacketNumPer1000;
	}

	public void setMinPacketNumPer1000(int minPacketNumPer1000) {
		this.minPacketNumPer1000 = minPacketNumPer1000;
	}

	public int getMinBitNumPer100() {
		return minBitNumPer100;
	}

	public void setMinBitNumPer100(int minBitNumPer100) {
		this.minBitNumPer100 = minBitNumPer100;
	}

	public int getMinBitNumPer1000() {
		return minBitNumPer1000;
	}

	public void setMinBitNumPer1000(int minBitNumPer1000) {
		this.minBitNumPer1000 = minBitNumPer1000;
	}

}
