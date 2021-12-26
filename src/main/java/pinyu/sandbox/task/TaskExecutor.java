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

//	public void runTasks(Task[] tasks) {
//		for (Task t : tasks) {
//			runTask(t);
//		}
//	}
//
//	public Task[] createCpuTasks(int taskNum) {
//		CpuTask[] tasks = new CpuTask[taskNum];
//
//		for (int i = 0; i < taskNum; i++) {
//			tasks[i] = new CpuTask();
//		}
//
//		return tasks;
//	}
//
//	public void stopTasks() {
//		Task.stop();
//	}
}
