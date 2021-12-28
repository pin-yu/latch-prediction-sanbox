package pinyu.sandbox;

import pinyu.sandbox.latch.ServingTask;
import pinyu.sandbox.randomprocess.PoissonProcess;
import pinyu.sandbox.randomprocess.RandomProcess;
import pinyu.sandbox.task.Task;
import pinyu.sandbox.task.TaskExecutor;

public class LatchSandbox {
	public static final int TOTAL_TASKS = 100_000;

	public static final int ARRIVAL_RATE = 4500;
	public static final int SERVING_RATE = 5000;

	public static final String CONTEXT_FILE_NAME = "latch-context.csv";
	public static final String FEATURE_FILE_NAME = "latch-feature.csv";

	private static RandomProcess arrivalProcess = new PoissonProcess(ARRIVAL_RATE);
	private static RandomProcess servingProcess = new PoissonProcess(SERVING_RATE);
	private static TaskExecutor taskExecutor = new TaskExecutor();

	static {
		System.out.println("============================================================");
		System.out.println("Total tasks: " + TOTAL_TASKS);
		System.out.println("Arrival rate (task/sec): " + ARRIVAL_RATE);
		System.out.println("Serving rate (task/sec): " + SERVING_RATE);
		System.out.println("Ideal stationary waiters in queue: " + stationaryWaitersInQueue());
		System.out.println("============================================================");
	}

	public static int stationaryWaitersInQueue() {
		double rho = (double) ARRIVAL_RATE / SERVING_RATE;
		return (int) (rho * rho / (1 - rho));
	}

	public static TaskExecutor taskExectutor() {
		return taskExecutor;
	}

	public static RandomProcess servingProcess() {
		return servingProcess;
	}

	public static void startGenerateTasks() {
		Task task = new ServingTask();

		for (int i = 0; i < TOTAL_TASKS; i++) {
			long lastArrival = System.nanoTime();

			addToQueue(task);

			task = new ServingTask();
			arrivalProcess.busyWait(lastArrival);
		}

		System.out.println("Benchmarking finished, the collector is saving file");
	}

	private static void addToQueue(Task task) {
		// imaging the thread pool as a queue
		taskExecutor.execute(task);
	}
}
