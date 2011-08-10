package test.swing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang.math.RandomUtils;
import org.hxzon.util.Daytime;
import org.hxzon.util.MathUtil;

public class FlowStatisticsPaintPanelModel {
	//max
	private int maxPacketNum100;
	private int maxPacketNum1000;
	private int maxBitNum100;
	private int maxBitNum1000;
	//min
	private int minPacketNum100;
	private int minPacketNum1000;
	private int minBitNum100;
	private int minBitNum1000;
	//orig flow info
	private List<FlowInfo> gooseFlowInfos;
	private List<FlowInfo> mmsFlowInfos;
	private List<FlowInfo> smvFlowInfos;
	private List<FlowInfo> otherFlowInfos;
	private List<FlowInfo> allFlowInfos;
	//data
	private FlowStatisticsData gooseData;
	private FlowStatisticsData mmsData;
	private FlowStatisticsData smvData;
	private FlowStatisticsData otherData;
	private FlowStatisticsData allData;
	//time
	private Daytime startTime;
	private Daytime endTime;

	public static class PreferredSize {
		public int size;
		public int perTick;

		public PreferredSize(int size, int perTick) {
			this.size = size;
			this.perTick = perTick;
		}
	}

//	public int computePreferredHeight(int totalNum, int numPerTickY, int tickY) {
//		return totalNum * tickY / numPerTickY;
//	}
//
//	public int computePreferredWidth(int totalTimeInterval, int timeIntervalPerTickX, int tickX) {
//		return totalTimeInterval * tickX / timeIntervalPerTickX;
//	}

	public int computeNumPerTickY(int totalNum, int totalHeight, int tickY) {
		return totalNum * tickY / totalHeight;
	}

	public int computeTimeIntervalPerTickX(int totalTimeInterval, int totalWidth, int tickX) {
		return totalTimeInterval * tickX / totalWidth;
	}

	private static final int timeIntervalBetweenTwoPoints = 100;

	public PreferredSize computePreferredWidth(int totalTimeInterval, int tickX) {
		int t = totalTimeInterval * tickX;
		int totalWidth = 1000;
		int timeIntervalPerTickX = t / totalWidth;
		if (timeIntervalPerTickX < 100) {
			timeIntervalPerTickX = 100;
		} else if (timeIntervalPerTickX > 20 * timeIntervalBetweenTwoPoints) {//20 * tickX
			timeIntervalPerTickX = 20 * timeIntervalBetweenTwoPoints;
		} else {
		}
		totalWidth = t / timeIntervalPerTickX;
		return new PreferredSize(totalWidth, timeIntervalPerTickX);
	}

	public PreferredSize computePreferredWidth(int totalTimeInterval, int tickX, int totalWidth) {
		int t = totalTimeInterval * tickX;
		int timeIntervalPerTickX = t / totalWidth;
		return new PreferredSize(totalWidth, timeIntervalPerTickX);
	}

	public PreferredSize computePreferredHeight(int totalNum, int tickY) {
		int t = totalNum * tickY;
		int totalHeight = 1000;
		int numPerTickY = t / totalHeight;
		if (numPerTickY < 5) {
			numPerTickY = 5;
		} else if (numPerTickY > tickY * 20) {
			numPerTickY = tickY * 20;
		} else {
		}
		totalHeight = t / numPerTickY;
		return new PreferredSize(totalHeight, numPerTickY);
	}

	public PreferredSize computePreferredHeight(int totalNum, int tickY, int totalHeight) {
		int t = totalNum * tickY;
		int numPerTickY = t / totalHeight;
		return new PreferredSize(totalHeight, numPerTickY);
	}

	private static final int mockMinute = 10;

	public void randomMockData() {
		startTime = new Daytime(new Date());
		endTime = startTime.addMinute(mockMinute);
		FutureTask<List<FlowInfo>> gooseMockCall = new FutureTask<List<FlowInfo>>(new RandomMockTask());
		FutureTask<List<FlowInfo>> mmsMockCall = new FutureTask<List<FlowInfo>>(new RandomMockTask());
		FutureTask<List<FlowInfo>> smvMockCall = new FutureTask<List<FlowInfo>>(new RandomMockTask());
		FutureTask<List<FlowInfo>> otherMockCall = new FutureTask<List<FlowInfo>>(new RandomMockTask());
		FutureTask<List<FlowInfo>> allMockCall = new FutureTask<List<FlowInfo>>(new RandomMockTask());
		ExecutorService executorService = Executors.newScheduledThreadPool(5);
		executorService.execute(gooseMockCall);
		executorService.execute(mmsMockCall);
		executorService.execute(smvMockCall);
		executorService.execute(otherMockCall);
		executorService.execute(allMockCall);
		try {
			gooseFlowInfos = gooseMockCall.get();
			mmsFlowInfos = mmsMockCall.get();
			smvFlowInfos = smvMockCall.get();
			otherFlowInfos = otherMockCall.get();
			allFlowInfos = allMockCall.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public void mockData() {
		startTime = new Daytime(new Date());
		endTime = startTime.addMinute(mockMinute);
		FutureTask<List<FlowInfo>> gooseMockCall = new FutureTask<List<FlowInfo>>(new MockTask());
		FutureTask<List<FlowInfo>> mmsMockCall = new FutureTask<List<FlowInfo>>(new MockTask());
		FutureTask<List<FlowInfo>> smvMockCall = new FutureTask<List<FlowInfo>>(new MockTask());
		FutureTask<List<FlowInfo>> otherMockCall = new FutureTask<List<FlowInfo>>(new MockTask());
		FutureTask<List<FlowInfo>> allMockCall = new FutureTask<List<FlowInfo>>(new MockTask());
		ExecutorService executorService = Executors.newScheduledThreadPool(5);
		executorService.execute(gooseMockCall);
		executorService.execute(mmsMockCall);
		executorService.execute(smvMockCall);
		executorService.execute(otherMockCall);
		executorService.execute(allMockCall);
		try {
			gooseFlowInfos = gooseMockCall.get();
			mmsFlowInfos = mmsMockCall.get();
			smvFlowInfos = smvMockCall.get();
			otherFlowInfos = otherMockCall.get();
			allFlowInfos = allMockCall.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public void prepareData() {
		gooseData = new FlowStatisticsData("goose", Color.cyan);
		mmsData = new FlowStatisticsData("mms", Color.green);
		smvData = new FlowStatisticsData("smv", Color.magenta);
		otherData = new FlowStatisticsData("other", Color.yellow);
		allData = new FlowStatisticsData("all", Color.pink);
		FutureTask<FlowStatisticsData> gooseCall = new FutureTask<FlowStatisticsData>(new NumTask(gooseFlowInfos, gooseData));
		FutureTask<FlowStatisticsData> mmsCall = new FutureTask<FlowStatisticsData>(new NumTask(mmsFlowInfos, mmsData));
		FutureTask<FlowStatisticsData> smvCall = new FutureTask<FlowStatisticsData>(new NumTask(smvFlowInfos, smvData));
		FutureTask<FlowStatisticsData> otherCall = new FutureTask<FlowStatisticsData>(new NumTask(otherFlowInfos, otherData));
		FutureTask<FlowStatisticsData> allCall = new FutureTask<FlowStatisticsData>(new NumTask(allFlowInfos, allData));
		ExecutorService executorService = Executors.newScheduledThreadPool(5);
		executorService.execute(gooseCall);
		executorService.execute(mmsCall);
		executorService.execute(smvCall);
		executorService.execute(otherCall);
		executorService.execute(allCall);
		try {
			gooseCall.get();
			mmsCall.get();
			smvCall.get();
			otherCall.get();
			allCall.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		FutureTask<FlowStatisticsData> gooseMaxCall = new FutureTask<FlowStatisticsData>(new MaxMinTask(gooseData));
		FutureTask<FlowStatisticsData> mmsMaxCall = new FutureTask<FlowStatisticsData>(new MaxMinTask(mmsData));
		FutureTask<FlowStatisticsData> smvMaxCall = new FutureTask<FlowStatisticsData>(new MaxMinTask(smvData));
		FutureTask<FlowStatisticsData> otherMaxCall = new FutureTask<FlowStatisticsData>(new MaxMinTask(otherData));
		FutureTask<FlowStatisticsData> allMaxCall = new FutureTask<FlowStatisticsData>(new MaxMinTask(allData));
		executorService.execute(gooseMaxCall);
		executorService.execute(mmsMaxCall);
		executorService.execute(smvMaxCall);
		executorService.execute(otherMaxCall);
		executorService.execute(allMaxCall);
		try {
			gooseMaxCall.get();
			mmsMaxCall.get();
			smvMaxCall.get();
			otherMaxCall.get();
			allMaxCall.get();
			maxPacketNum100 = MathUtil.max(new int[] { gooseData.getMaxPacketNumPer100(), mmsData.getMaxPacketNumPer100(), smvData.getMaxPacketNumPer100(), otherData.getMaxPacketNumPer100(),
					allData.getMaxPacketNumPer100() });
			maxBitNum100 = MathUtil.max(new int[] { gooseData.getMaxBitNumPer100(), mmsData.getMaxBitNumPer100(), smvData.getMaxBitNumPer100(), otherData.getMaxBitNumPer100(),
					allData.getMaxBitNumPer100() });
			maxPacketNum1000 = MathUtil.max(new int[] { gooseData.getMaxPacketNumPer1000(), mmsData.getMaxPacketNumPer1000(), smvData.getMaxPacketNumPer1000(), otherData.getMaxPacketNumPer1000(),
					allData.getMaxPacketNumPer1000() });
			maxBitNum1000 = MathUtil.max(new int[] { gooseData.getMaxBitNumPer1000(), mmsData.getMaxBitNumPer1000(), smvData.getMaxBitNumPer1000(), otherData.getMaxBitNumPer1000(),
					allData.getMaxBitNumPer1000() });
			//min
			minPacketNum100 = MathUtil.min(new int[] { gooseData.getMinPacketNumPer100(), mmsData.getMinPacketNumPer100(), smvData.getMinPacketNumPer100(), otherData.getMinPacketNumPer100(),
					allData.getMinPacketNumPer100() });
			minBitNum100 = MathUtil.min(new int[] { gooseData.getMinBitNumPer100(), mmsData.getMinBitNumPer100(), smvData.getMinBitNumPer100(), otherData.getMinBitNumPer100(),
					allData.getMaxBitNumPer100() });
			minPacketNum1000 = MathUtil.min(new int[] { gooseData.getMinPacketNumPer1000(), mmsData.getMinPacketNumPer1000(), smvData.getMinPacketNumPer1000(), otherData.getMinPacketNumPer1000(),
					allData.getMaxPacketNumPer1000() });
			minBitNum1000 = MathUtil.min(new int[] { gooseData.getMinBitNumPer1000(), mmsData.getMinBitNumPer1000(), smvData.getMinBitNumPer1000(), otherData.getMinBitNumPer1000(),
					allData.getMaxBitNumPer1000() });
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static class RandomMockTask implements Callable<List<FlowInfo>> {
		public List<FlowInfo> call() {
			List<FlowInfo> flowInfos = new ArrayList<FlowInfo>();
			for (int i = 0; i < mockMinute; i++) {
				FlowInfo flowInfo = new FlowInfo();
				flowInfo.setType("goose");
				flowInfos.add(flowInfo);
				int[] bitNum = new int[600];
				int[] packetNum = new int[600];
				for (int j = 0; j < 600; j++) {
					bitNum[j] = RandomUtils.nextInt(1000) + 9000;
					packetNum[j] = RandomUtils.nextInt(50) + 50;
				}
				flowInfo.setBitNum(bitNum);
				flowInfo.setPacketNum(packetNum);
			}
			return flowInfos;
		}
	}

	private static class MockTask implements Callable<List<FlowInfo>> {
		public List<FlowInfo> call() {
			List<FlowInfo> flowInfos = new ArrayList<FlowInfo>();
			for (int i = 0; i < mockMinute; i++) {
				FlowInfo flowInfo = new FlowInfo();
				flowInfo.setType("goose");
				flowInfos.add(flowInfo);
				int[] bitNum = new int[600];
				int[] packetNum = new int[600];
				for (int j = 0; j < 600; j++) {
					bitNum[j] = (j + 1) * 100;
					packetNum[j] = j + 1;
				}
				flowInfo.setBitNum(bitNum);
				flowInfo.setPacketNum(packetNum);
			}
			return flowInfos;
		}
	}

	private static class NumTask implements Callable<FlowStatisticsData> {
		private List<FlowInfo> flowInfos;
		private FlowStatisticsData pointData;

		public NumTask(List<FlowInfo> flowInfos, FlowStatisticsData pointData) {
			this.flowInfos = flowInfos;
			this.pointData = pointData;
		}

		public FlowStatisticsData call() {
			int[] packetNumPer100 = new int[600 * flowInfos.size()];
			int[] bitNumPer100 = new int[600 * flowInfos.size()];
			for (int i = 0; i < flowInfos.size(); i++) {
				FlowInfo flowInfo = flowInfos.get(i);
				for (int j = 0; j < 600; j++) {
					packetNumPer100[i * 600 + j] = flowInfo.getPacketNum()[j];
					bitNumPer100[i * 600 + j] = flowInfo.getBitNum()[j];
				}
			}
			pointData.setPacketNumPer100(packetNumPer100);
			pointData.setBitNumPer100(bitNumPer100);
			pointData.setPacketNumPer1000(MathUtil.average(packetNumPer100, 10));
			pointData.setBitNumPer1000(MathUtil.average(bitNumPer100, 10));
			return pointData;
		}
	}

	private static class MaxMinTask implements Callable<FlowStatisticsData> {
		private FlowStatisticsData pointData;

		public MaxMinTask(FlowStatisticsData pointData) {
			this.pointData = pointData;
		}

		public FlowStatisticsData call() {
			pointData.setMaxPacketNumPer100(MathUtil.max(pointData.getPacketNumPer100()));
			pointData.setMaxBitNumPer100(MathUtil.max(pointData.getBitNumPer100()));
			pointData.setMaxPacketNumPer1000(MathUtil.max(pointData.getPacketNumPer1000()));
			pointData.setMaxBitNumPer1000(MathUtil.max(pointData.getBitNumPer1000()));
			//min
			pointData.setMinPacketNumPer100(MathUtil.min(pointData.getPacketNumPer100()));
			pointData.setMinBitNumPer100(MathUtil.min(pointData.getBitNumPer100()));
			pointData.setMinPacketNumPer1000(MathUtil.min(pointData.getPacketNumPer1000()));
			pointData.setMinBitNumPer1000(MathUtil.min(pointData.getBitNumPer1000()));
			return pointData;
		}
	}

	public int getMaxPacketNum100() {
		return maxPacketNum100;
	}

	public void setMaxPacketNum100(int maxPacketNum100) {
		this.maxPacketNum100 = maxPacketNum100;
	}

	public int getMaxPacketNum1000() {
		return maxPacketNum1000;
	}

	public void setMaxPacketNum1000(int maxPacketNum1000) {
		this.maxPacketNum1000 = maxPacketNum1000;
	}

	public int getMaxBitNum100() {
		return maxBitNum100;
	}

	public void setMaxBitNum100(int maxBitNum100) {
		this.maxBitNum100 = maxBitNum100;
	}

	public int getMaxBitNum1000() {
		return maxBitNum1000;
	}

	public void setMaxBitNum1000(int maxBitNum1000) {
		this.maxBitNum1000 = maxBitNum1000;
	}

	public int getMinPacketNum100() {
		return minPacketNum100;
	}

	public void setMinPacketNum100(int minPacketNum100) {
		this.minPacketNum100 = minPacketNum100;
	}

	public int getMinPacketNum1000() {
		return minPacketNum1000;
	}

	public void setMinPacketNum1000(int minPacketNum1000) {
		this.minPacketNum1000 = minPacketNum1000;
	}

	public int getMinBitNum100() {
		return minBitNum100;
	}

	public void setMinBitNum100(int minBitNum100) {
		this.minBitNum100 = minBitNum100;
	}

	public int getMinBitNum1000() {
		return minBitNum1000;
	}

	public void setMinBitNum1000(int minBitNum1000) {
		this.minBitNum1000 = minBitNum1000;
	}

	public List<FlowInfo> getGooseFlowInfo() {
		return gooseFlowInfos;
	}

	public void setGooseFlowInfo(List<FlowInfo> gooseFlowInfo) {
		this.gooseFlowInfos = gooseFlowInfo;
	}

	public List<FlowInfo> getMmsFlowInfo() {
		return mmsFlowInfos;
	}

	public void setMmsFlowInfo(List<FlowInfo> mmsFlowInfo) {
		this.mmsFlowInfos = mmsFlowInfo;
	}

	public List<FlowInfo> getSmvFlowInfo() {
		return smvFlowInfos;
	}

	public void setSmvFlowInfo(List<FlowInfo> smvFlowInfo) {
		this.smvFlowInfos = smvFlowInfo;
	}

	public List<FlowInfo> getOtherFlowInfo() {
		return otherFlowInfos;
	}

	public void setOtherFlowInfo(List<FlowInfo> otherFlowInfo) {
		this.otherFlowInfos = otherFlowInfo;
	}

	public List<FlowInfo> getAllFlowInfo() {
		return allFlowInfos;
	}

	public void setAllFlowInfo(List<FlowInfo> allFlowInfo) {
		this.allFlowInfos = allFlowInfo;
	}

	public Daytime getStartTime() {
		return startTime;
	}

	public void setStartTime(Daytime startTime) {
		this.startTime = startTime;
	}

	public Daytime getEndTime() {
		return endTime;
	}

	public void setEndTime(Daytime endTime) {
		this.endTime = endTime;
	}

	public List<FlowInfo> getGooseFlowInfos() {
		return gooseFlowInfos;
	}

	public void setGooseFlowInfos(List<FlowInfo> gooseFlowInfos) {
		this.gooseFlowInfos = gooseFlowInfos;
	}

	public List<FlowInfo> getMmsFlowInfos() {
		return mmsFlowInfos;
	}

	public void setMmsFlowInfos(List<FlowInfo> mmsFlowInfos) {
		this.mmsFlowInfos = mmsFlowInfos;
	}

	public List<FlowInfo> getSmvFlowInfos() {
		return smvFlowInfos;
	}

	public void setSmvFlowInfos(List<FlowInfo> smvFlowInfos) {
		this.smvFlowInfos = smvFlowInfos;
	}

	public List<FlowInfo> getOtherFlowInfos() {
		return otherFlowInfos;
	}

	public void setOtherFlowInfos(List<FlowInfo> otherFlowInfos) {
		this.otherFlowInfos = otherFlowInfos;
	}

	public List<FlowInfo> getAllFlowInfos() {
		return allFlowInfos;
	}

	public void setAllFlowInfos(List<FlowInfo> allFlowInfos) {
		this.allFlowInfos = allFlowInfos;
	}

	public FlowStatisticsData getGooseData() {
		return gooseData;
	}

	public void setGooseData(FlowStatisticsData gooseData) {
		this.gooseData = gooseData;
	}

	public FlowStatisticsData getMmsData() {
		return mmsData;
	}

	public void setMmsData(FlowStatisticsData mmsData) {
		this.mmsData = mmsData;
	}

	public FlowStatisticsData getSmvData() {
		return smvData;
	}

	public void setSmvData(FlowStatisticsData smvData) {
		this.smvData = smvData;
	}

	public FlowStatisticsData getOtherData() {
		return otherData;
	}

	public void setOtherData(FlowStatisticsData otherData) {
		this.otherData = otherData;
	}

	public FlowStatisticsData getAllData() {
		return allData;
	}

	public void setAllData(FlowStatisticsData allData) {
		this.allData = allData;
	}

}
