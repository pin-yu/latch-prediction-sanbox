package pinyu.sandbox.collector.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
	private String fileName;

	public CsvWriter(String fileName) {
		this.fileName = fileName;
	}

	public void saveList(List<String> rows) {
		Object[] arrayRows = rows.toArray();
		saveArray(arrayRows);
	}

	public void saveArray(Object[] rows) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			for (Object row : rows) {
				writer.append(row + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(fileName + " has been saved");
	}

	public void saveArray(String header, Object[] rows) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.append(header + "\n");
			for (Object row : rows) {
				writer.append(row + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(fileName + " has been saved");
	}
}
