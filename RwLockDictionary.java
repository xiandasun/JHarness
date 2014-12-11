import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RwLockDictionary extends IDictionary {
	
	HashMap<Integer, Node> internalMap;
	ReadWriteLock lock;

	public RwLockDictionary() {
		internalMap = new HashMap<Integer, Node>();
		lock = new ReentrantReadWriteLock();
	}
	
	@Override
	public void put(int k, Node v) {
		lock.writeLock().lock();
		internalMap.put(k, v);
		lock.writeLock().unlock();
	}

	@Override
	public Node get(int k) {
		Node ret = null;
		lock.readLock().lock();
		ret = internalMap.get(k);
		lock.readLock().unlock();
		return ret;
	}

	@Override
	public void remove(int k) {
		lock.writeLock().lock();
		internalMap.remove(k);
		lock.writeLock().unlock();
	}

	@Override
	public int size() {
		return internalMap.size();
	}

}
