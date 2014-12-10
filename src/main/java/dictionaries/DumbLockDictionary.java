package dictionaries;

// Java HashMap guarded by locks

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import data.Node;

public class DumbLockDictionary extends IDictionary {

	private HashMap<Integer, Node> internalMap;
	private ReentrantLock[] lockList;
	
	public DumbLockDictionary() {
		internalMap = new HashMap<Integer, Node>();
		lockList = new ReentrantLock[128];
		for (int i = 0; i < 128; i++) {
			lockList[i] = new ReentrantLock();
		}
	}
	
	@Override
	public void put(int k, Node v) {
		lockList[k].lock();
		internalMap.put(k, v);
		lockList[k].unlock();
	}

	@Override
	public Node get(int k) {
		Node ret = null;
		lockList[k].lock();
		ret = internalMap.get(k);
		lockList[k].unlock();
		return ret;
	}

	@Override
	public void remove(int k) {
		lockList[k].lock();
		internalMap.remove(k);
		lockList[k].unlock();
	}

	@Override
	public int size() {
		return internalMap.size();
	}

}

