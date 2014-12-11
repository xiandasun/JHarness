// Java HashMap gua.rded by locks

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedDictionary extends IDictionary {

	private HashMap<Integer, Node> internalMap;
	private ReentrantLock lock;
	private Object[] keys;
	
	public SynchronizedDictionary() {
		internalMap = new HashMap<Integer, Node>();
		lock = new ReentrantLock();
		keys = new Object[128];
		for (int i = 0; i < 128; i++) {
			keys[i] = new Object();
		}
	}
	
	@Override
	public void put(int k, Node v) {
		while (!internalMap.containsKey(k)) {
			lock.lock();
			if (!internalMap.containsKey(k)) {
				internalMap.put(k, v);
			}
			lock.unlock();
		}
		synchronized(keys[k]) {
			internalMap.put(k, v);
		}
	}

	@Override
	public Node get(int k) {
		Node ret = null;
		//synchronized(keys[k]) {
		ret = internalMap.get(k);
		//}
		return ret;
	}

	@Override
	public void remove(int k) {
		synchronized(keys[k]) {
			internalMap.remove(k);
		}
	}

	@Override
	public int size() {
		return internalMap.size();
	}

}

