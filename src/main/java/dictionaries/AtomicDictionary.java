package dictionaries;

// Java HashMap guarded by locks

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import data.Node;

public class AtomicDictionary extends IDictionary {

	private HashMap<Integer, Node> internalMap;
	private AtomicBoolean isLocked;
	
	public AtomicDictionary() {
		internalMap = new HashMap<Integer, Node>();
		isLocked = new AtomicBoolean(false);
	}
	
	@Override
	public void put(int k, Node v) {
		while (!isLocked.compareAndSet(false, true)) {
			synchronized (isLocked) {
				try {
					isLocked.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		internalMap.put(k, v);
		isLocked.set(false);
		synchronized (isLocked) {
			isLocked.notifyAll();
		}
	}

	@Override
	public Node get(int k) {
		Node ret = null;
		while (!isLocked.compareAndSet(false, true)) {
			synchronized (isLocked) {
				try {
					isLocked.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ret = internalMap.get(k);
		isLocked.set(false);
		synchronized (isLocked) {
			isLocked.notifyAll();
		}
		return ret;
	}

	@Override
	public void remove(int k) {
		while (!isLocked.compareAndSet(false, true)) {
			synchronized (isLocked) {
				try {
					isLocked.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		internalMap.remove(k);
		isLocked.set(false);
		synchronized (isLocked) {
			isLocked.notifyAll();
		}
	}

	@Override
	public int size() {
		return internalMap.size();
	}

}

