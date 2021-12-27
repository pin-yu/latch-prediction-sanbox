package pinyu.sandbox.latch;

import pinyu.sandbox.LatchSandbox;
import pinyu.sandbox.collector.DataCollector;
import pinyu.sandbox.latch.context.LatchContext;
import pinyu.sandbox.latch.context.LatchContextHistory;
import pinyu.sandbox.latch.feature.LatchFeature;
import pinyu.sandbox.task.Task;

public class ServingTask extends Task {
	private static Latch latch = new Latch();
	private static DataCollector latchContextCollector = new DataCollector(LatchSandbox.CONTEXT_FILE_NAME, "context");
	private static DataCollector latchFeatureCollector = new DataCollector(LatchSandbox.FEATURE_FILE_NAME, "feature");
	private static LatchContextHistory latchContextHistory = new LatchContextHistory();

	static {
		LatchSandbox.taskExectutor().execute(latchContextCollector);
		LatchSandbox.taskExectutor().execute(latchFeatureCollector);
	}

	public static DataCollector latchFeatureCollector() {
		return latchFeatureCollector;
	}

	public void run() {
		LatchContext context = new LatchContext();

		String historyContextHeader = latchContextHistory.toHeader();
		String historyContextRow = latchContextHistory.toRow();

		serve(context);

		latchContextCollector.add(context);
		latchContextHistory.add(context);

		LatchFeature latchFeature = new LatchFeature(historyContextHeader, historyContextRow, context.toHeader(),
				context.toRow());
		ServingTask.latchFeatureCollector().add(latchFeature);
	}

	public void serve(LatchContext context) {
		try {
			latch.lock(context);

			long startServingTime = System.nanoTime();
			LatchSandbox.servingProcess().busyWait(startServingTime);
		} finally {
			latch.unLock(context);
		}
	}
}
