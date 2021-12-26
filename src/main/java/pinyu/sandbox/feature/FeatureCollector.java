package pinyu.sandbox.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import pinyu.sandbox.task.Task;
import pinyu.sandbox.writer.CsvWriter;

public class FeatureCollector extends Task {

	private BlockingQueue<Feature> queue = new LinkedBlockingQueue<Feature>();

	public FeatureCollector() {

	}
	
	public void addFeature(Feature feautre) {
		queue.add(feautre);
	}

	public void run() {
		try {
			Feature row = queue.take();

			System.out.println("Start taking features");

			List<String> rows = new ArrayList<String>();
			
			// add a header
			rows.add(Feature.header());
			
			// add the first row after the header row
			rows.add(row.toString());

			int timeToFlush = 2;
			// stop waiting if nothing is coming for 5 seconds
			while ((row = queue.poll(timeToFlush, TimeUnit.SECONDS)) != null) {
				rows.add(row.toString());
			}
			
			System.out.println("Collecting done");
			
			// write to file
			writeToFile(rows);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void writeToFile(List<String> rows) {
		String fileName = "latch-features.csv";
				
		System.out.println("Write to " + fileName);
		
		CsvWriter csvSaver = new CsvWriter(fileName);
		csvSaver.saveList(rows);
	}
}
