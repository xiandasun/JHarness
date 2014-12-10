package dictionaries;

// Java HashMap guarded by locks

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import data.Node;

public class DumbRwLockDictionary extends IDictionary {

	private HashMap<Integer, Node> internalMap;
	private ReentrantReadWriteLock[] lockList;
	
	public DumbRwLockDictionary() {
		internalMap = new HashMap<Integer, Node>();
		lockList = new ReentrantReadWriteLock[128];
		for (int i = 0; i < 128; i++) {
			lockList[i] = new ReentrantReadWriteLock();
		}
	}
	
	@Override
	public void put(int k, Node v) {
		lockList[k].writeLock().lock();
		internalMap.put(k, v);
		lockList[k].writeLock().unlock();
	}

	@Override
	public Node get(int k) {
		Node ret = null;
		lockList[k].readLock().lock();
		ret = internalMap.get(k);
		lockList[k].readLock().unlock();
		return ret;
	}

	@Override
	public void remove(int k) {
		lockList[k].writeLock().lock();
		internalMap.remove(k);
		lockList[k].writeLock().unlock();
	}

	@Override
	public int size() {
		return internalMap.size();
	}

}

