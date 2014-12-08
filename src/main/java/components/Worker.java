package components;

import dictionaries.IDictionary;
import dictionaries.SpinLockDictionary;
import main.Node;

public abstract class Worker extends Thread {
	protected static IDictionary dictionary;
	protected int id;
	protected int entries;
	protected int getf;
	protected static int delay;
	volatile static boolean start;
	volatile static boolean stop;
	
	public Worker() {
		start = false;
		stop = false;
		delay = 0;
	}
	
	public static void use(String dictionaryName) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dictionary = (IDictionary) Class.forName(dictionaryName).newInstance();
	}
	public abstract void run();
	
	public static void turnOn() {
		start = true;
	}
	
	public static void turnOff() {
		stop = true;
	}
	
	public static void setDelay(int loops) {
		delay = loops;
	}
	
	public static void populateDictionary(int n) {
		for (int i = 0; i < n; i++) {
			dictionary.put(i, new Node(i));
		}
	}
	
	public int getEntries() {
		return entries;
	}
	
	public int getGetf() {
		return getf;
	}
}
