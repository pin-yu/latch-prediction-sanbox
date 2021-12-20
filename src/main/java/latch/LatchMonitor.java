package latch;

public class LatchMonitor {
	private LatchAcquiredHistory acquiredHistory = new LatchAcquiredHistory();
	private LatchWaiter latchWaiter = new LatchWaiter();
	
	public LatchMonitor() {
		
	}
	
	public void addToWaiters(long start) {
		latchWaiter.addToWaiters(start);
	}
	
	public void rmFromWaiters(long start) {
		latchWaiter.rmFromWaiters(start);
	}
	
	public long getMaxWaitingTime() {
		return latchWaiter.getMaxWaitingTime();
	}
	
	public void addToAcquiredHistory(long start, long end) {
		acquiredHistory.addToHistory(start, end);
	}
	
	public long[] getMaxAcquiredTime() {
		long[] maxWaitings = new long[3];
		
		maxWaitings[0] = acquiredHistory.getMaxValue(10);
		maxWaitings[1] = acquiredHistory.getMaxValue(50);
		maxWaitings[2] = acquiredHistory.getMaxValue(100);
		
		return maxWaitings;
	}
}
