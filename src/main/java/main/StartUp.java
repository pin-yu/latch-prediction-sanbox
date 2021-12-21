package main;

import task.Task;
import task.TaskMgr;
import task.cputask.CpuTask;

public class StartUp {

	public static void main(String[] args) {
		TaskMgr taskMgr = new TaskMgr();

		// create 1000 tasks
		int taskNum = 1000;
		Task[] tasks = taskMgr.createCpuTasks(taskNum);

		taskMgr.runTask(CpuTask.getFeatureCollector());
		taskMgr.runTasks(tasks);

		// 2s
		long waitTime = 2_000;
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		taskMgr.stopTasks();
	}

}
