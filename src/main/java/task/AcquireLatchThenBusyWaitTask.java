package task;

import feature.Feature;
import feature.FeatureCollector;
import latch.Latch;
import randomprocess.PoissonProcess;
import randomprocess.RandomProcess;

public class AcquireLatchThenBusyWaitTask extends Task {
	private static int servingRate = 1000;
	private static Latch latch = new Latch();
	private static RandomProcess rp = new PoissonProcess(servingRate);
	private static FeatureCollector featureCollector = new FeatureCollector();

	public static FeatureCollector getFeatureCollector() {
		return featureCollector;
	}

	public static void busyWait(Feature feature) {
		try {
			latch.lock(feature);
			long startServingTime = System.nanoTime();
			long servingInterval = rp.nextIntervalInNano();
			while (System.nanoTime() - startServingTime < servingInterval) {
				// busy waiting
			}
		} finally {
			latch.unLock(feature);
		}
	}

	public void run() {
		Feature feature = new Feature();
		busyWait(feature);
		featureCollector.addFeature(feature);
	}
}
