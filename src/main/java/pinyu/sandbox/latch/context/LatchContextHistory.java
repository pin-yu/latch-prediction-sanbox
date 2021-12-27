package pinyu.sandbox.latch.context;

import com.google.common.collect.EvictingQueue;

public class LatchContextHistory {
	private static final int MAX_HISTORY_SIZE = 10;
	private EvictingQueue<LatchContext> history = EvictingQueue.create(MAX_HISTORY_SIZE);

	private String header = "";

	public LatchContextHistory() {
		// intentionally fill the history up with some dummy context at first
		for (int i = 0; i < MAX_HISTORY_SIZE; i++) {
			history.add(new LatchContext());
		}

		Object[] historyArray = history.toArray();
		for (int i = 0; i < history.size(); i++) {
			header += ((LatchContext) historyArray[i]).toHeader(i) + ",";
		}
	}

	public synchronized void add(LatchContext context) {
		history.add(context);
	}

	public String toHeader() {
		return header;
	}

	public synchronized String toRow() {
		String row = "";

		for (Object context : history.toArray()) {
			row += ((LatchContext) context).toRow() + ",";
		}

		return row;
	}
}
