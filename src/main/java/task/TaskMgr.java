package task;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskMgr {
	public static final int THREAD_POOL_SIZE = 1000;

	private ThreadPoolExecutor executor;

	public TaskMgr() {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}

	public void runTask(Task task) {
		executor.execute(task);
	}
}
