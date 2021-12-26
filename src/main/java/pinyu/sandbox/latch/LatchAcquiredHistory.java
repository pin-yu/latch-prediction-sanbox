package pinyu.sandbox.latch;

import java.util.ArrayList;
import java.util.List;

public class LatchAcquiredHistory {
	private static List<Long> history = new ArrayList<Long>(10_000);

	public synchronized void addToHistory(long start, long end) {
		history.add(end - start);
	}

	public synchronized long[] getMaxAvg(int windowSize) {
		long [] results = new long[2];
		
		if (history.size() == 0) {
			results[0] = 0;
			results[1] = 0;
			return results;
		}
		
		int size = history.size();
		long maxVal = -1;
		
		long sum = 0;
		long counter = 0;
		
		for (int i = size - 1; i >= 0 && size - i <= windowSize; i--) {
			if (history.get(i) > maxVal) {
				maxVal = history.get(i);
			}
			
			sum += history.get(i);
			counter += 1;
		}
		
		results[0] = maxVal;
		results[1] = sum / counter;
		return results;
	}
}
