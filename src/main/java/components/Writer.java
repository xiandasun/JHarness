package components;

import data.Node;

public class Writer extends Worker {
	// make sure delays are not ignored
	private int dummy;
	
	public Writer(int wid) {
		super(wid);
		dummy = 0;
	}
	
	@Override
	public void run() {
		while (!start);
		for (int i = 0; i < Thread.currentThread().getId() * 100; i++) {
			dummy = (dummy + 1) % 32767;
		}
		
		Node n = new Node(id);
		
		while (!stop) {
			dictionary.put(id, n);
			entries++;
			for (int i = 0; i < delay; i++) {
				dummy = (n.data + dummy) % 32767;
			}
		}
	}
	
	// call this to make the busy loops do something
	public int getDummy() {
		return dummy;
	}
}
