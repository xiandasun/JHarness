// Java HashMap guarded by locks

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class DumbAtomicDictionary extends IDictionary {

	private HashMap<Integer, Node> internalMap;
	private AtomicBoolean[] lockList;
	
	public DumbAtomicDictionary() {
		internalMap = new HashMap<Integer, Node>();
		lockList = new AtomicBoolean[128];
		for (int i = 0; i < 128; i++) {
			lockList[i] = new AtomicBoolean(false);
		}
	}
	
	@Override
	public void put(int k, Node v) {
		while (!lockList[k].compareAndSet(false, true)) {
			try {
				synchronized (lockList[k]) {
					lockList[k].wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		internalMap.put(k, v);
		lockList[k].set(false);
		synchronized (lockList[k]) {
			lockList[k].notifyAll();
		}
	}

	@Override
	public Node get(int k) {
		Node ret = null;
		while (!lockList[k].compareAndSet(false, true)) {
			try {
				synchronized (lockList[k]) {
					lockList[k].wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ret = internalMap.get(k);
		lockList[k].set(false);
		synchronized (lockList[k]) {
			lockList[k].notifyAll();
		}
		return ret;
	}

	@Override
	public void remove(int k) {
		while (!lockList[k].compareAndSet(false, true)) {
			try {
				synchronized (lockList[k]) {
					lockList[k].wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		internalMap.remove(k);
		lockList[k].set(false);
		synchronized (lockList[k]) {
			lockList[k].notifyAll();
		}
	}

	@Override
	public int size() {
		return internalMap.size();
	}

}

