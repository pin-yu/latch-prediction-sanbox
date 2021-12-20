package latch;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class LatchAcquiredHistory {
	private static TreeMap<Long, Long> history = new TreeMap<Long, Long>();

	public synchronized void addToHistory(long start, long end) {
		history.put(start, end - start);
	}
	
	

	public synchronized long getMaxValue(long timeWindowInMs) {
		long floorTimeStamp = System.nanoTime() - timeWindowInMs * 1_000_000;

		Optional<Map.Entry<Long, Long>> maxAcquiredTime = history.entrySet().stream()
				.filter(entry -> entry.getKey() > floorTimeStamp)
				.max((entry1, entry2) -> Long.compare(entry1.getValue(), entry2.getValue()));

		if (maxAcquiredTime.isPresent()) {
			return maxAcquiredTime.get().getValue();
		}

		return 0;
	}
}
