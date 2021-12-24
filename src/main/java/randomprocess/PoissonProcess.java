package randomprocess;

public class PoissonProcess implements RandomProcess {
	private static long secondToNano = 1_000_000_000;

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
		long nextIntervalInNano = (long) (nextIntervalInSecond * secondToNano);

		return nextIntervalInNano;
	}
}
