package task.cputask;

import java.util.concurrent.ThreadLocalRandom;

import feature.Feature;
import feature.FeatureCollector;
import latch.Latch;
import task.Task;

public class CpuTask extends Task {
//	private static final int origin = 100_000;
//	private static final int bound = 1_000_000;
	private static final int origin = 1;
	private static final int bound = 10;

	private static Latch latch = new Latch();
	private static FeatureCollector featureCollector = new FeatureCollector();

	public static FeatureCollector getFeatureCollector() {
		return featureCollector;
	}

	public static int sumValue(int load, Feature feature) {
		try {
			latch.lock(feature);
			int sum = 0;
			for (int i = 0; i < load; i++) {
				if (i % 2 == 0) {
					sum += i;
				} else {
					sum -= i;
				}
			}

			return sum;
		} finally {
			latch.unLock(feature);
		}
	}

	public void run() {
		while (!stop) {
			Feature feature = new Feature();
			int load = ThreadLocalRandom.current().nextInt(origin, bound);
			sumValue(load, feature);
			featureCollector.addFeature(feature);
		}
	}
}
