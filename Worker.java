public abstract class Worker extends Thread {
    protected static IDictionary dictionary;
    protected int id;
    protected double entries;
    protected double getf;
    protected static int delay;
    volatile static boolean start;
    volatile static boolean stop;
	
    static {
	System.loadLibrary("Affinity");
    }
	
    private native void cppSetAffinity(long tid, int cpu);
	
    public Worker(int simulatedId) {
	start = false;
	stop = false;
	entries = 0;
	getf = 0;
	id = simulatedId;
		
	setAffinity();
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
	
    public double getEntries() {
	return entries;
    }
	
    public double getGetf() {
	return getf;
    }
	
    public void setAffinity() {
	int cores = Runtime.getRuntime().availableProcessors();
	long tid = Thread.currentThread().getId();
	int core = (32 + id) % cores;
	cppSetAffinity(tid, core);
    }
}
