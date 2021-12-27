package pinyu.sandbox.randomprocess;

public interface RandomProcess {
	public long nextIntervalInNano();
	
	public void busyWait(long lastTimestamp);
}
