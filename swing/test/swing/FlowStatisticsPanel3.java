package test.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.hxzon.util.DateFormatUtil;
import org.hxzon.util.Daytime;
import org.hxzon.util.NumberFormatUtil;

public class FlowStatisticsPanel3 extends JPanel {
	private static final Color backgroundColor = Color.black;
	private static final Color backgroundLineColor = Color.decode("#5d5d5d");
	private static final Color tickLineColor = Color.decode("#666b3c");
	private static final int tickLinePad = 2;

	private int timeIntervalPerTickX;//ms
	private int timeIntervalBetweenTwoPoints;//ms
	private int numPerTickY;
	private boolean drawColumnLine = true;
	private boolean drawRowLine = true;
	private int tickX = 10;
	private int tickY = 10;
	private int tickXO = 35;
	private int tickYO = 35;
	private Rectangle viewRect;
	private Dimension totalSize = new Dimension(1000, 38000);
	private List<PointData> pointDatas;
	private Daytime startTime;
	private Daytime endTime;

	public void paintComponent(Graphics g) {
		this.setBackground(backgroundColor);
		super.paintComponent(g);
		paintTickLine(g);
		int distanceXBetweenTwoPoints = computeDistanceXBetweenTwoPoints();
		int distanceY = computeDistanceYBetweenTwoPoints();
		for (PointData pointData : pointDatas) {
			if (pointData.isShow()) {
				Color color = pointData.getColor();
				int[] data = pointData.getData();
				int x = tickXO;
				for (int i = 1; i < data.length; i++) {
					g.setColor(color);
					g.drawLine(x, reverseY(data[i - 1] * distanceY), x += distanceXBetweenTwoPoints, reverseY(data[i] * distanceY));
				}
			}
		}
		paintHeader(g);
	}

	private int computeFixXO() {
		return viewRect.x + tickXO;
	}

	private int computeFixYO() {
		return viewRect.y + viewRect.height - tickYO;
	}

	private Rectangle computeRowHeaderRect() {
		return new Rectangle(viewRect.x, viewRect.y, tickXO, this.getPreferredSize().height - tickYO);
	}

	private Rectangle computeColumnHeaderRect() {
		return new Rectangle(computeFixXO(), computeFixYO(), this.getPreferredSize().width, tickYO);
	}

	private int computeDistanceXBetweenTwoPoints() {
		return tickX * timeIntervalBetweenTwoPoints / timeIntervalPerTickX;
	}

	private int computeDistanceYBetweenTwoPoints() {
		return tickY / numPerTickY;
	}

	private void paintTickLine(Graphics g) {
		int h = getPreferredSize().height;
		int w = getPreferredSize().width;
		int fixXO = computeFixXO();
		int fixYO = computeFixYO();
		g.setColor(backgroundLineColor);
		if (drawRowLine) {
			for (int i = h - tickYO; i >= 0; i -= tickY) {
//				if(i<=fixYO){//use fillRectWithBackgroundColor
				g.drawLine(fixXO, i, w, i);
//				}
			}
		}
		if (drawColumnLine) {
			for (int i = tickXO; i <= w; i += tickX) {
//				if(i>=fixXO){//use fillRectWithBackgroundColor
				g.drawLine(i, fixYO, i, fixYO - h);
//				}
			}
		}
	}

	private void paintRowHeader(Graphics g) {
		int h = this.getPreferredSize().height;
		int fixXO = computeFixXO() - tickLinePad;
		int fixYO = computeFixYO();
		g.setColor(backgroundColor);
		g.fillRect(viewRect.x, viewRect.y, tickXO, this.getPreferredSize().height);
		g.setColor(tickLineColor);
		g.drawLine(fixXO, fixYO, fixXO, fixYO - h);
		int distanceY = computeDistanceYBetweenTwoPoints();
		for (int i = h - tickYO, j = 0; i >= 0; i -= getTickY(), j++) {
			if (j % 2 == 0) {
				g.drawLine(fixXO, i, fixXO - 2, i);
				g.drawString(j * tickY / distanceY + "", fixXO - 8, i);
//				g.drawString(startTime.second+tickX"", fixXO-8, i);
			}
		}
	}

	private void paintColumnHeader(Graphics g) {
		int w = getWidth();
		int fixXO = computeFixXO();
		int fixYO = computeFixYO() + tickLinePad;
		g.setColor(backgroundColor);
		g.fillRect(computeFixXO(), computeFixYO(), this.getPreferredSize().width, tickYO);
		g.setColor(tickLineColor);
		g.drawLine(fixXO, fixYO, w + fixXO, fixYO);
		int timeIntervalPerTickX = getTimeIntervalPerTickX();
		for (int i = tickXO, j = 0; i <= w; i += getTickX(), j++) {
			if (j % 2 == 0) {
				g.drawLine(i, fixYO, i, fixYO + 2);
//				g.drawString(j + "", i, fixYO + 12);
				Daytime time = startTime.addUsec(j * timeIntervalPerTickX * 1000);
				String hhmmss = DateFormatUtil.formatTime(time.hour, time.minute, time.second);
				String millisec = NumberFormatUtil.format(time.usec / 1000, "000");
				g.drawString(hhmmss, i, fixYO + 12);
				g.drawString(millisec, i, fixYO + 24);
			}
		}
	}

	private void paintHeader(Graphics g) {
		paintRowHeader(g);
		paintColumnHeader(g);
		g.setColor(backgroundColor);
		g.fillRect(viewRect.x, computeFixYO(), tickXO, tickYO);
	}

	public int reverseY(int y) {
		int h = getPreferredSize().height - tickYO;
		return h - y;
	}

	public Dimension getPreferredSize() {
		return totalSize;
	}

	public void setTimeIntervalPerTickX(int timeIntervalPerTickX) {
		this.timeIntervalPerTickX = timeIntervalPerTickX;
	}

	public int getTimeIntervalPerTickX() {
		return timeIntervalPerTickX;
	}

//	public Daytime computeTickXOTime() {
//		return new Daytime(startTime.origsec, startTime.usec / 100);
//	}

	public void setStartTime(Daytime startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Daytime endTime) {
		this.endTime = endTime;
	}

	public void setTickX(int x) {
		if (x < 0) {
			return;
		}
		this.tickX = x;
	}

	public int getTickX() {
		return tickX;
	}

	public void setTickY(int y) {
		if (y < 0) {
			return;
		}
		this.tickY = y;
	}

	public int getTickY() {
		return tickY;
	}

	public void setNumPerTickY(int y) {
		this.numPerTickY = y;
	}

	public int getNumPerTickY() {
		return this.numPerTickY;
	}

	public void setPointDatas(List<PointData> pointDatas) {
		this.pointDatas = pointDatas;
	}

	public void setTimeIntervalBetweenTwoPoints(int interval) {
		this.timeIntervalBetweenTwoPoints = interval;
	}

	public void showData(String name) {
		for (PointData data : pointDatas) {
			if (data.getName().equals(name)) {
				data.setShow(true);
				break;
			}
		}
		this.repaint();
	}

	public void hideData(String name) {
		for (PointData data : pointDatas) {
			if (data.getName().equals(name)) {
				data.setShow(false);
				break;
			}
		}
		this.repaint();
	}

	public void setViewSize(Rectangle size) {
		this.viewRect = size;
		repaint();
	}

	public static class ControlPanel extends JPanel {
		private FlowStatisticsPanel3 flowPanel;

		public ControlPanel(FlowStatisticsPanel3 flowPanel) {
//			this.setBackground(backgroundColor);
			this.flowPanel = flowPanel;
			JPanel packetPanel = new JPanel();
			packetPanel.setLayout(new BoxLayout(packetPanel, BoxLayout.Y_AXIS));
			PacketCheckBox goose = new PacketCheckBox(flowPanel, "goose");
			PacketCheckBox mms = new PacketCheckBox(flowPanel, "mms");
			PacketCheckBox other = new PacketCheckBox(flowPanel, "other");
			packetPanel.add(goose);
			packetPanel.add(mms);
			packetPanel.add(other);
			packetPanel.add(new TimeIntervalComboBox(flowPanel));
			packetPanel.add(new PacketNumOrBitNum(flowPanel));
			this.add(packetPanel);
		}
	}

	public static class PacketCheckBox extends JCheckBox {
		public PacketCheckBox(final FlowStatisticsPanel3 flowPanel, final String name) {
			super(name);
			this.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flowPanel.hideData(name);
					} else {
						flowPanel.showData(name);
					}
				}
			});
		}
	}

	public static class TimeIntervalComboBox extends JComboBox {
		public TimeIntervalComboBox(final FlowStatisticsPanel3 flowPanel) {
			this.addItem(new TimeIntervalComboBoxItem("0.1s", 100));
			this.addItem(new TimeIntervalComboBoxItem("1s", 1000));
			this.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					TimeIntervalComboBoxItem item = (TimeIntervalComboBoxItem) e.getItem();
					flowPanel.setTimeIntervalBetweenTwoPoints(item.timeInterval);
					flowPanel.repaint();
				}
			});
		}
	}

	public static class PacketNumOrBitNum extends JComboBox {
		public PacketNumOrBitNum(final FlowStatisticsPanel3 flowPanel) {
			this.addItem(new TimeIntervalComboBoxItem("报文数", 0));
			this.addItem(new TimeIntervalComboBoxItem("比特数", 1));
			this.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					TimeIntervalComboBoxItem item = (TimeIntervalComboBoxItem) e.getItem();

				}
			});
		}
	}

	public static class TimeIntervalComboBoxItem {
		public String display;
		public int timeInterval;

		public TimeIntervalComboBoxItem(String display, int timeInterval) {
			this.display = display;
			this.timeInterval = timeInterval;
		}

		public String toString() {
			return display;
		}
	}

	public static void main(String args[]) {
		JFrame f = new JFrame("测试");
		Container contPane = f.getContentPane();
		contPane.setLayout(new BoxLayout(contPane, BoxLayout.LINE_AXIS));
		final FlowStatisticsPanel3 flowPanel = new FlowStatisticsPanel3();
		List<PointData> datas = new ArrayList<PointData>();
		int data1[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		int data2[] = new int[] { 5, 5, 5, 5, 5, 6, 7, 8, 8, 9, 10, 8, 7, 6, 6, 5, 5, 5, 4 };
		int data3[] = new int[] { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 24, 27, 30, 27, 24, 21, 19, 17, 15, 13, 11, 9, 7, 5, 3, 1 };
		datas.add(new PointData("goose", Color.red, data1));
		datas.add(new PointData("mms", Color.blue, data2));
		datas.add(new PointData("other", Color.green, data3));
		flowPanel.setStartTime(new Daytime(new Date()));
		flowPanel.setTickX(30);
		flowPanel.setTimeIntervalPerTickX(100);
		flowPanel.setTimeIntervalBetweenTwoPoints(100);
		flowPanel.setTickY(50);
		flowPanel.setNumPerTickY(2);
		flowPanel.setPointDatas(datas);
		final JScrollPane jsp = new JScrollPane(flowPanel);
		jsp.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
//				if (!e.getValueIsAdjusting()) {
				flowPanel.setViewSize(jsp.getViewport().getViewRect());
//				}
			}
		});
		jsp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
//				if (!e.getValueIsAdjusting()) {
				flowPanel.setViewSize(jsp.getViewport().getViewRect());
//				}
			}
		});
		f.add(jsp);
		f.add(new ControlPanel(flowPanel));
		f.setBounds(300, 300, 800, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
