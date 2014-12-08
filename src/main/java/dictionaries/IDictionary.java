package dictionaries;

import main.Node;

public abstract class IDictionary {
	public IDictionary() {
		
	}
	
	public abstract void put(int k, Node v);
	
	public abstract Node get(int k);
	
	public abstract void remove(int k);
	
	public abstract int size();
}
