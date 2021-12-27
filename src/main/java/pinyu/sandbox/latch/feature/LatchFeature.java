package pinyu.sandbox.latch.feature;

import pinyu.sandbox.collector.csv.CsvRow;

public class LatchFeature implements CsvRow {
	private String historyFeaturesHeader;
	private String historyFeaturesRow;
	private String currentFeatureHeader;
	private String currentFeatureRow;

	public LatchFeature(String historyFeaturesHeader, String historyFeaturesRow, String currentFeatureHeader,
			String currentFeatureRow) {
		this.historyFeaturesHeader = historyFeaturesHeader;
		this.historyFeaturesRow = historyFeaturesRow;
		this.currentFeatureHeader = currentFeatureHeader;
		this.currentFeatureRow = currentFeatureRow;
	}

	public String toHeader() {
		return historyFeaturesHeader + "," + currentFeatureHeader;
	}

	public String toRow() {

		return historyFeaturesRow + "," + currentFeatureRow;
	}
}
