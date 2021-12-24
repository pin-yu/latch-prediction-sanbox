package main;

import randomprocess.PoissonProcess;
import randomprocess.RandomProcess;
import task.AcquireLatchThenBusyWaitTask;
import task.Task;
import task.TaskExecutor;
import writer.CsvWriter;

public class StartUp {

	public static void main(String[] args) {
		final int requestPerSecond = 970;
		final int totalTaskNum = 1_0000;

		TaskExecutor taskExecutor = new TaskExecutor();
		RandomProcess rp = new PoissonProcess(requestPerSecond);
		
		// start a background thread to collect features
		taskExecutor.execute(AcquireLatchThenBusyWaitTask.getFeatureCollector());
		
		Object[] nextArrivalIntervalHistory = new Object[totalTaskNum];
		
		Task task = new AcquireLatchThenBusyWaitTask();
		for (int i = 0; i < totalTaskNum; i++) {
			long lastArrival = System.nanoTime();
			taskExecutor.execute(task);

			long nextArrivalInterval = rp.nextIntervalInNano();

			task = new AcquireLatchThenBusyWaitTask();

			// let time go until nextArrivalInterval is coming
			while (System.nanoTime() - lastArrival < nextArrivalInterval) {
				// busy waiting
			}

			nextArrivalIntervalHistory[i] = System.nanoTime() - lastArrival;
		}

		System.out.println("write poisson-arrival information for sanity check");
		CsvWriter csvWriter = new CsvWriter("poisson-arrival.csv");
		csvWriter.saveArray("interarrival", nextArrivalIntervalHistory);
	}
}
