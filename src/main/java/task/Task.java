package task;

public abstract class Task implements Runnable {
	protected static boolean stop = false;
	
	public static void stop() {
		stop = true;
	}
}
