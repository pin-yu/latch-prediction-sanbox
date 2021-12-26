package pinyu.sandbox.latch;

import java.util.concurrent.locks.ReentrantLock;

import pinyu.sandbox.feature.Feature;

public class Latch {
	private final ReentrantLock latch;

	// TODO: implement a queue
	private static LatchMonitor latchMonitor = new LatchMonitor();

	public Latch() {
		latch = new ReentrantLock();
	}

	public void lock(Feature feature) {
		feature.setAcquirerNum(latch.getQueueLength());
		
		long[] maxAvgAcquiredTime = latchMonitor.getMaxAvgAcquiredTime();
		feature.setMaxAvgAcquiredTime(maxAvgAcquiredTime[0], maxAvgAcquiredTime[1]);

		feature.setMaxWaitingTime(latchMonitor.getMaxWaitingTime());
		
		long start = feature.setAcquireLatchTime();
		latchMonitor.addToWaiters(start);
		
		latch.lock();
		
		long end = feature.setLatchAcquiredTime();
		latchMonitor.rmFromWaiters(start);
		latchMonitor.addToAcquiredHistory(start, end);
	}

	public void unLock(Feature feature) {
		latch.unlock();
		feature.setLatchReleasedTime();
	}
}
