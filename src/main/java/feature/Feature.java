package feature;

public class Feature {
	public long acquireLatch;
	public long latchAcquired;
	public long latchReleased;
	public long acquirerNum;
	public long[] MaxAcquirerWaitTimes; // different time window
	public long[] AvgAcquirerWaitTimes; // different time window
	
	public Feature() {
		
	}
	
	public String toString() {
		return acquireLatch + "," + latchAcquired + "," + latchReleased;
	}
	
	public static String header() {
		return "acquire latch,latch acquired,latch released";
	}
}
