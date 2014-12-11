import java.util.concurrent.ConcurrentHashMap;

public class JavaConcurrentDictionary extends IDictionary {

	private ConcurrentHashMap<Integer, Node> internalMap;
	
	public JavaConcurrentDictionary() {
		internalMap = new ConcurrentHashMap<Integer, Node>();
	}
	
	@Override
	public void put(int k, Node v) {
		internalMap.put(k, v);
	}

	@Override
	public Node get(int k) {
		Node ret = internalMap.get(k);
		return ret;
	}

	@Override
	public void remove(int k) {
		internalMap.remove(k);
	}

	@Override
	public int size() {
		return internalMap.size();
	}

}
