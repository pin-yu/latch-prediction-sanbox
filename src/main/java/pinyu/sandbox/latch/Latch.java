package pinyu.sandbox.latch;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import pinyu.sandbox.latch.context.LatchContext;

public class Latch {
	private final ReentrantLock latch;
	private final AtomicLong serialNumber = new AtomicLong();

	public Latch() {
		latch = new ReentrantLock();
	}

	public void lock(LatchContext currentContext) {
		currentContext.setTimeBeforeLock();
		currentContext.setSerialNumberBeforeLock(serialNumber.get());
		currentContext.setWaitingQueueLength(latch.getQueueLength());

		latch.lock();
		currentContext.setTimeAfterLock();
		currentContext.setSerialNumberAfterLock(serialNumber.get());
	}

	public void unLock(LatchContext currentContext) {
		latch.unlock();
		serialNumber.incrementAndGet();
		currentContext.setTimeAfterUnlock();
	}
}
