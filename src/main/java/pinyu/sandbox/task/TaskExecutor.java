package pinyu.sandbox.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskExecutor {
	public static final int THREAD_POOL_SIZE = 1000;

	private ThreadPoolExecutor executor;

	public TaskExecutor() {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}

	public void execute(Task task) {
		executor.execute(task);
	}
}
