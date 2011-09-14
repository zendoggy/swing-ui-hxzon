package test.swing;

import java.util.Date;

public class FlowInfo {
	
	private String type;
	private int[] packetNum;
	private int[] bitNum;
	private Date startTime;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int[] getPacketNum() {
		return packetNum;
	}

	public void setPacketNum(int[] packetNum) {
		this.packetNum = packetNum;
	}

	public int[] getBitNum() {
		return bitNum;
	}

	public void setBitNum(int[] bitNum) {
		this.bitNum = bitNum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

}
