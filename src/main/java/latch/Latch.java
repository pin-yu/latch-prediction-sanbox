package latch;

import java.util.concurrent.locks.ReentrantLock;

import feature.Feature;

public class Latch {
	private final ReentrantLock latch;
	
	// TODO: implement a queue
	
	public Latch() {
		latch = new ReentrantLock();
	}
	
	public void lock(Feature feature) {
		feature.acquireLatch = System.nanoTime();
		
		latch.lock();
		
		feature.latchAcquired = System.nanoTime();
	}
	
	public void unLock(Feature feature) {
		latch.unlock();
		feature.latchReleased = System.nanoTime();
	}
}
