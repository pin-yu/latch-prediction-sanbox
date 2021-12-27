package pinyu.sandbox.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import pinyu.sandbox.collector.csv.CsvRow;
import pinyu.sandbox.collector.csv.CsvWriter;
import pinyu.sandbox.task.Task;

public class DataCollector extends Task {
	private BlockingQueue<CsvRow> queue = new ArrayBlockingQueue<CsvRow>(1_000_000);

	private String fileName;
	private String label;

	public DataCollector(String fileName, String label) {
		this.fileName = fileName;
		this.label = label;

	}

	public void add(CsvRow row) {
		queue.add(row);
	}

	public void run() {
		try {
			System.out.println(String.format("Start collecting %s", label));
			List<String> rows = new ArrayList<String>();

			CsvRow row = queue.take();

			// add a header
			rows.add(row.toHeader());

			// add the first row after the header row
			rows.add(row.toRow());

			int timeToFlush = 2;
			// stop waiting if nothing is coming for 5 seconds
			while ((row = queue.poll(timeToFlush, TimeUnit.SECONDS)) != null) {
				rows.add(row.toRow());
			}

			System.out.println(String.format("No more %s come", label));

			// write to file
			writeToFile(rows);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile(List<String> rows) {
		CsvWriter csvSaver = new CsvWriter(fileName);
		csvSaver.saveList(rows);
	}
}
