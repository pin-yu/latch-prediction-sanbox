package pinyu.sandbox.randomprocess;

public class PoissonProcess implements RandomProcess {
	private final static long SECOND_TO_NANO = 1_000_000_000;

	private int requestPerSecond;

	public PoissonProcess(int requestPerSecond) {
		this.requestPerSecond = requestPerSecond;
	}

	/**
	 * This method generates exponential distribution from uniform distribution via
	 * <a href=
	 * "https://en.wikipedia.org/wiki/Exponential_distribution#Generating_exponential_variates">
	 * Inversion Transformation </a>
	 */
	public long nextIntervalInNano() {
		double u = Math.random();
		double nextIntervalInSecond = -Math.log(u) / requestPerSecond;
		long nextIntervalInNano = (long) (nextIntervalInSecond * SECOND_TO_NANO);

		return nextIntervalInNano;
	}

	public void busyWait(long lastTimestamp) {
		long nextInterval = nextIntervalInNano();

		// let time go until nextArrivalInterval passes
		long hasElapsed = System.nanoTime() - lastTimestamp;

		while (hasElapsed < nextInterval) {
			hasElapsed = System.nanoTime() - lastTimestamp;

			// busy waiting
		}
	}
}
