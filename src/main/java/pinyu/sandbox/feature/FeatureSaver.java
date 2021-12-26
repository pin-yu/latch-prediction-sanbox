package pinyu.sandbox.feature;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FeatureSaver {
	
	private final String fileName;
	private final List<String> rows;
	
	public FeatureSaver(String fileName, List<String> rows) {
		this.fileName = fileName;
		this.rows = rows;
	}
	
	public void saveToCsv() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			for (String row : rows)
				writer.append(row + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
