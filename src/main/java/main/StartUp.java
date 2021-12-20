package main;

import task.Task;
import task.TaskMgr;
import task.cputask.CpuTask;

public class StartUp {

	public static void main(String[] args) {
		TaskMgr taskMgr = new TaskMgr();

		// create 100 tasks
		int taskNum = 100;
		Task[] tasks = taskMgr.createCpuTasks(taskNum);

		taskMgr.runTask(CpuTask.getFeatureCollector());
		taskMgr.runTasks(tasks);

		// 20s
		long waitTime = 20000;
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		taskMgr.stopTasks();
	}

}
