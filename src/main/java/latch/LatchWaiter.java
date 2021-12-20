package latch;

import java.util.TreeMap;

public class LatchWaiter {
	private TreeMap<Long, Long> waiters = new TreeMap<Long, Long>();

	public synchronized void addToWaiters(long start) {
		waiters.put(start, start);
	}

	public synchronized long getMaxWaitingTime() {
		if (waiters.isEmpty()) {
			return 0;
		}
		return System.nanoTime() - waiters.firstKey();
	}

	public synchronized void rmFromWaiters(long start) {
		waiters.remove(start);
	}
}
