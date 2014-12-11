// Java HashMap guarded by locks

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SpinLockDictionary extends IDictionary {
    private HashMap<Integer, Node> internalMap;
    private Lock lock;
	
    /*
      public class Lock {

      private AtomicBoolean isLocked;// = false;

      public Lock() {
      isLocked = new AtomicBoolean(false);
      }
		
      public synchronized void lock() {
      while (!isLocked.compareAndSet(false, true)) {
      try {
      wait();
      } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      }
      }
      }

      public synchronized void unlock() {
      isLocked.set(false);;
      notify();
      }
      }*/
	
    public SpinLockDictionary() {
	internalMap = new HashMap<Integer, Node>();
	lock = new ReentrantLock();
    }
	
    @Override
    public void put(int k, Node v) {
	// TODO Auto-generated method stub
	lock.lock();
	internalMap.put(k, v);
	lock.unlock();
    }

    @Override
    public Node get(int k) {
	// TODO Auto-generated method stub
	Node ret = null;
	lock.lock();
	ret = internalMap.get(k);
	lock.unlock();
	return ret;
    }

    @Override
    public void remove(int k) {
	// TODO Auto-generated method stub
	lock.lock();
	internalMap.remove(k);
	lock.unlock();
    }

    @Override
    public int size() {
	// TODO Auto-generated method stub
	return internalMap.size();
    }
}
