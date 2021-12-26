package pinyu.sandbox.latch;

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
	
	public long[] getMaxAvgAcquiredTime() {
		long[] maxAvgWaitings = acquiredHistory.getMaxAvg(100);
		
		return maxAvgWaitings;
	}
}
