package test.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.hxzon.swing.layout.simple.SimpleLayout;
import org.hxzon.swing.layout.simple.SimpleLayoutData;
import org.hxzon.util.DateFormatUtil;
import org.hxzon.util.Daytime;
import org.hxzon.util.NumberFormatUtil;

import test.swing.FlowStatisticsPaintPanelModel.PreferredSize;

@SuppressWarnings("serial")
public class FlowStatisticsPaintPanel extends JPanel {
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
	private int preferredHeight = 38000;
	private int preferredWidth = 2000;
	private List<FlowStatisticsData> pointDatas;
	private Daytime startTime;
	private boolean isPacketNum = true;
	private boolean isPer100 = true;
	private FlowStatisticsPaintPanelModel model;
	private int minNum = 0;

	private int computeFloatXO() {
		return viewRect.x + tickXO;
	}

	private int computeFloatYO() {
		return viewRect.y + viewRect.height - tickYO;
	}

	@SuppressWarnings("unused")
	private Rectangle computeRowHeaderRect() {
		return new Rectangle(viewRect.x, viewRect.y, tickXO, this.getPreferredHeight() - tickYO);
	}

	@SuppressWarnings("unused")
	private Rectangle computeColumnHeaderRect() {
		return new Rectangle(computeFloatXO(), computeFloatYO(), this.getPreferredWidth() - tickXO, tickYO);
	}

	@SuppressWarnings("unused")
	private Rectangle computeContentRect() {
		return new Rectangle(computeFloatXO(), viewRect.y, viewRect.width - tickXO, viewRect.height - tickYO);
	}

	public int reverseY(int y) {
		int h = getPreferredHeight() - tickYO;
		return h - y;
	}

	private int computeY(int orig) {
		return reverseY((orig - minNum) * tickY / numPerTickY);
	}

	@SuppressWarnings("unused")
	private int[] computeY(int[] orig) {
		int[] result = new int[orig.length];
		for (int i = 0; i < orig.length; i++) {
			result[i] = computeY(orig[i]);
		}
		return result;
	}

	@SuppressWarnings("unused")
	private int computeX(int orig) {
		return orig * tickX / timeIntervalPerTickX;
	}

//	private Image offScreenImage;

//	public void paint(Graphics g) {
//		if (offScreenImage == null || offScreenImage.getWidth(this) != this.preferredWidth || offScreenImage.getHeight(this) != this.preferredHeight) {
//			offScreenImage = this.createImage(this.preferredWidth, this.preferredHeight);
//		}
//		Graphics gImage = offScreenImage.getGraphics();
//		paintComponent(gImage);
//		g.drawImage(offScreenImage, 0, 0, null);
//	}

	private void updateImage() {
		repaint();
	}

	protected void paintComponent(Graphics g) {
//		this.setBackground(backgroundColor);
//		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(viewRect.x, viewRect.y, viewRect.width, viewRect.height);
		paintTickLine(g);
//		Rectangle rect=computeContentRect();
//		g.setClip(rect.x, rect.y, rect.width, rect.height);
		for (FlowStatisticsData pointData : pointDatas) {
			if (pointData.isShow()) {
				Color color = pointData.getColor();
				g.setColor(color);
				int[] data;
				if (isPacketNum) {
					data = isPer100 ? pointData.getPacketNumPer100() : pointData.getPacketNumPer1000();
				} else {
					data = isPer100 ? pointData.getBitNumPer100() : pointData.getBitNumPer1000();
				}
				int x = tickXO;
				for (int i = 1; i < data.length; i++) {
					int x2 = i * timeIntervalBetweenTwoPoints * tickX / timeIntervalPerTickX + tickXO;
					g.drawLine(x, computeY(data[i - 1]), x2, computeY(data[i]));
					g.drawString(data[i] + "", x2, computeY(data[i]));
					x = x2;
				}
			}
		}
//		g.setClip(null);
		paintHeader(g);
	}

	private void paintTickLine(Graphics g) {
		int h = getPreferredHeight();
		int w = getPreferredWidth();
		int floatXO = computeFloatXO();
		int floatYO = computeFloatYO();
		g.setColor(backgroundLineColor);
		if (drawRowLine) {
			for (int i = h - tickYO; i >= 0; i -= tickY) {
				if (i < floatYO) {
					g.drawLine(floatXO, i, floatXO + viewRect.width, i);
				}
			}
		}
		if (drawColumnLine) {
			for (int i = tickXO; i <= w; i += tickX) {
				if (i > floatXO) {
					g.drawLine(i, floatYO, i, viewRect.y);
				}
			}
		}
	}

	private void paintRowHeader(Graphics g) {
		int h = this.getPreferredHeight();
		int floatXO = computeFloatXO() - tickLinePad;
		int floatYO = computeFloatYO();
		g.setColor(backgroundColor);
		g.fillRect(viewRect.x, viewRect.y, tickXO, h);
		g.setColor(tickLineColor);
		g.drawLine(floatXO, floatYO, floatXO, viewRect.y);
		FontMetrics fm = g.getFontMetrics();
		for (int i = h - tickYO, j = 0; i >= 0; i -= getTickY(), j++) {
			if (j % 2 == 0) {
				g.drawLine(floatXO, i, floatXO - 2, i);
				String str = String.valueOf(j * numPerTickY + minNum);
				int strw = fm.stringWidth(str);
				g.drawString(str, floatXO - strw - 8, i);
			}
		}
	}

	private void paintColumnHeader(Graphics g) {
		int w = getWidth();
		int floatXO = computeFloatXO();
		int floatYO = computeFloatYO() + tickLinePad;
		g.setColor(backgroundColor);
		g.fillRect(computeFloatXO(), computeFloatYO(), this.getPreferredWidth(), tickYO);
		g.setColor(tickLineColor);
		g.drawLine(floatXO, floatYO, viewRect.x + viewRect.width, floatYO);
		int timeIntervalPerTickX = getTimeIntervalPerTickX();
		for (int i = tickXO, j = 0; i <= w; i += getTickX(), j++) {
			if (j % 2 == 0) {
				g.drawLine(i, floatYO, i, floatYO + 2);
//				g.drawString(j + "", i, fixYO + 12);
				Daytime time = startTime.addUsec(j * timeIntervalPerTickX * 1000);
				String hhmmss = DateFormatUtil.formatTime(time.hour, time.minute, time.second);
				String millisec = NumberFormatUtil.format(time.usec / 1000, "000");
				g.drawString(hhmmss, i, floatYO + 12);
				g.drawString(millisec, i, floatYO + 24);
			}
		}
	}

	private void paintHeader(Graphics g) {
		paintRowHeader(g);
		paintColumnHeader(g);
		g.setColor(backgroundColor);
		g.fillRect(viewRect.x, computeFloatYO(), tickXO, tickYO);
	}

	public Dimension getPreferredSize() {
		return new Dimension(preferredWidth, preferredHeight);
	}

	public int getPreferredHeight() {
		return preferredHeight;
	}

	public void setPreferredHeight(int preferredHeight) {
		this.preferredHeight = preferredHeight;
	}

	public int getPreferredWidth() {
		return preferredWidth;
	}

	public void setPreferredWidth(int preferredWidth) {
		this.preferredWidth = preferredWidth;
	}

	public void setTimeIntervalPerTickX(int timeIntervalPerTickX) {
		this.timeIntervalPerTickX = timeIntervalPerTickX;
	}

	public int getTimeIntervalPerTickX() {
		return timeIntervalPerTickX;
	}

	public void setStartTime(Daytime startTime) {
		this.startTime = startTime;
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

	public void setPointDatas(List<FlowStatisticsData> pointDatas) {
		this.pointDatas = pointDatas;
	}

	public void setTimeIntervalBetweenTwoPoints(int interval) {
		this.timeIntervalBetweenTwoPoints = interval;
	}

	public void showData(String name) {
		for (FlowStatisticsData data : pointDatas) {
			if (data.getName().equals(name)) {
				data.setShow(true);
				break;
			}
		}
		updateImage();
	}

	public void hideData(String name) {
		for (FlowStatisticsData data : pointDatas) {
			if (data.getName().equals(name)) {
				data.setShow(false);
				break;
			}
		}
		updateImage();
	}

	public boolean isPacketNum() {
		return isPacketNum;
	}

	public void setPacketNum(boolean isPacketNum) {
		this.isPacketNum = isPacketNum;
	}

	public boolean isPer100() {
		return isPer100;
	}

	public void setPer100(boolean isPer100) {
		this.isPer100 = isPer100;
		this.timeIntervalBetweenTwoPoints = (isPer100 ? 100 : 1000);
	}

	public void setViewSize(Rectangle size) {
		this.viewRect = size;
		updateImage();
//		updateData();
	}

	public FlowStatisticsPaintPanelModel getModel() {
		return model;
	}

	public void setModel(FlowStatisticsPaintPanelModel model) {
		this.model = model;
	}

	public void updateData() {
		int maxNum = 0;
		if (isPacketNum) {
			maxNum = isPer100 ? model.getMaxPacketNum100() : model.getMaxPacketNum1000();
		} else {
			maxNum = isPer100 ? model.getMaxBitNum100() : model.getMaxBitNum1000();
		}
		minNum = 0;
		if (isPacketNum) {
			minNum = isPer100 ? model.getMinPacketNum100() : model.getMinPacketNum1000();
		} else {
			minNum = isPer100 ? model.getMinBitNum100() : model.getMinBitNum1000();
		}
		PreferredSize t = model.computePreferredHeight(maxNum - minNum, this.tickY);
		this.setPreferredHeight(t.size + 2 * this.tickY);
		this.setNumPerTickY(t.perTick);
		int totalTimeInterval = (int) (model.getEndTime().toMillssec() - model.getStartTime().toMillssec());
		t = model.computePreferredWidth(totalTimeInterval, this.tickX);
		this.setPreferredWidth(t.size + 2 * this.tickX);
		this.setTimeIntervalPerTickX(t.perTick);
		//set size for update the jscrollpane
		this.setSize(new Dimension(this.preferredWidth, this.preferredHeight));
	}

	public static class FlowStatisticsControlPanel extends JPanel {

		public FlowStatisticsControlPanel(FlowStatisticsPaintPanel flowPanel) {
//			this.setBackground(backgroundColor);
			JPanel packetPanel = new JPanel();
			packetPanel.setLayout(new SimpleLayout());
			PacketCheckBox goose = new PacketCheckBox(flowPanel, "goose", "GOOSE");
			PacketCheckBox mms = new PacketCheckBox(flowPanel, "mms", "MMS");
			PacketCheckBox smv = new PacketCheckBox(flowPanel, "smv", "采样值");
			PacketCheckBox other = new PacketCheckBox(flowPanel, "other", "其它报文");
			PacketCheckBox all = new PacketCheckBox(flowPanel, "all", "全部");
			packetPanel.add(new JLabel("显示报文类型："));
			packetPanel.add(goose);
			packetPanel.add(mms);
			packetPanel.add(smv);
			packetPanel.add(other);
			packetPanel.add(all);
			packetPanel.add(new JLabel("统计时间间隔："), SimpleLayoutData.fixedSize(25));
			packetPanel.add(new IsPer100ComboBox(flowPanel));
			packetPanel.add(new JLabel("统计项目："), SimpleLayoutData.fixedSize(25));
			packetPanel.add(new IsPacketNumComboBox(flowPanel));
			this.add(packetPanel);
		}
	}

	public static class PacketCheckBox extends JCheckBox {
		public PacketCheckBox(final FlowStatisticsPaintPanel flowPanel, final String name, String desc) {
			super(desc);
			super.setSelected(true);
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

	public static class IsPer100ComboBox extends JComboBox {
		public IsPer100ComboBox(final FlowStatisticsPaintPanel flowPanel) {
			this.addItem(new MyBooleanComboBoxItem("0.1s", true));
			this.addItem(new MyBooleanComboBoxItem("1s", false));
			this.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					MyBooleanComboBoxItem item = (MyBooleanComboBoxItem) e.getItem();
					flowPanel.setPer100(item.b);
					flowPanel.updateData();
					flowPanel.updateImage();
				}
			});
		}
	}

	public static class IsPacketNumComboBox extends JComboBox {
		public IsPacketNumComboBox(final FlowStatisticsPaintPanel flowPanel) {
			this.addItem(new MyBooleanComboBoxItem("报文数", true));
			this.addItem(new MyBooleanComboBoxItem("比特数", false));
			this.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					MyBooleanComboBoxItem item = (MyBooleanComboBoxItem) e.getItem();
					flowPanel.setPacketNum(item.b);
					flowPanel.updateData();
					flowPanel.updateImage();
				}
			});
		}
	}

	public static class MyBooleanComboBoxItem {
		public String display;
		public boolean b;

		public MyBooleanComboBoxItem(String display, boolean b) {
			this.display = display;
			this.b = b;
		}

		public String toString() {
			return display;
		}
	}

	public static class FlowStatisticsPanel extends JPanel {
		public FlowStatisticsPanel() {
			final FlowStatisticsPaintPanel paintPanel = new FlowStatisticsPaintPanel();
			List<FlowStatisticsData> datas = new ArrayList<FlowStatisticsData>();
			paintPanel.setStartTime(new Daytime(new Date()));
			paintPanel.setTickX(50);
			paintPanel.setTimeIntervalPerTickX(100);
			paintPanel.setTimeIntervalBetweenTwoPoints(100);
			paintPanel.setTickY(50);
			paintPanel.setNumPerTickY(50);
			//
			FlowStatisticsPaintPanelModel model = new FlowStatisticsPaintPanelModel();
			model.randomMockData();
			model.prepareData();

			datas.add(model.getGooseData());
			datas.add(model.getMmsData());
			datas.add(model.getSmvData());
			datas.add(model.getOtherData());
			datas.add(model.getAllData());
			paintPanel.setPointDatas(datas);
			paintPanel.setModel(model);
			paintPanel.setStartTime(model.getStartTime());
			paintPanel.updateData();
			final JScrollPane jsp = new JScrollPane(paintPanel);
			jsp.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
//					if (!e.getValueIsAdjusting()) {
					paintPanel.setViewSize(jsp.getViewport().getViewRect());
//					}
				}
			});
			jsp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
//					if (!e.getValueIsAdjusting()) {
					paintPanel.setViewSize(jsp.getViewport().getViewRect());
//					}
				}
			});
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(jsp);
			FlowStatisticsControlPanel controlPanel = new FlowStatisticsControlPanel(paintPanel);
			add(controlPanel);

		}
	}

	public static void main(String args[]) {
		JFrame f = new JFrame("测试");
		Container contPane = f.getContentPane();
		contPane.setLayout(new BoxLayout(contPane, BoxLayout.LINE_AXIS));
		f.add(new FlowStatisticsPanel());
		f.setBounds(300, 300, 800, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
