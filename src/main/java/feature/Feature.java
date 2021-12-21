package feature;

public class Feature {
	private long maxAcquiredTime;
	private long avgAcquiredTime;
	private long maxWaitingTime;
	private long acquireLatch;
	private long latchAcquired;
	private long latchReleased;
	private long acquirerNum;

	public void setMaxAvgAcquiredTime(long max, long avg) {
		maxAcquiredTime = max;
		avgAcquiredTime = avg;
	}

	public void setMaxWaitingTime(long maxWaitingTime) {
		this.maxWaitingTime = maxWaitingTime;
	}

	public long setAcquireLatchTime() {
		acquireLatch = System.nanoTime();
		return acquireLatch;
	}

	public long setLatchAcquiredTime() {
		latchAcquired = System.nanoTime();

		return latchAcquired;
	}

	public void setLatchReleasedTime() {
		latchReleased = System.nanoTime();
	}

	public void setAcquirerNum(long num) {
		acquirerNum = num;
	}

	public String toString() {
		return acquireLatch + "," + latchAcquired + "," + latchReleased + "," + acquirerNum + "," + maxWaitingTime + ","
				+ maxAcquiredTime + "," + avgAcquiredTime;
	}

	public static String header() {
		return "acquire latch,latch acquired,latch released,acquirer number,max waiting time,max acquired time,avg acquired time";
	}
}
