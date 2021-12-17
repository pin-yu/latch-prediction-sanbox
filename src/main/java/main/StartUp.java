package main;

import task.TaskMgr;
import task.cputask.CpuTask;

public class StartUp {

	public static void main(String[] args) {
		TaskMgr taskMgr = new TaskMgr();
		
		// create 100 tasks
		int taskNum = 100;
		CpuTask[] tasks = new CpuTask[taskNum];
		for (int i = 0; i < taskNum; i++) {
			tasks[i] = new CpuTask();
		}
		
		taskMgr.runTask(CpuTask.featureCollector());
		
		// run
		for (int i = 0; i < taskNum; i++) {
			taskMgr.runTask(tasks[i]);
		}
		
		// 20s
		long waitTime = 20000;
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CpuTask.stop();
	}

}
