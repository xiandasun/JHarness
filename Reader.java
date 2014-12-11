public class Reader extends Worker {
    // make sure delays are not ignored
    private int dummy;
	
    public Reader(int rid) {
	super(rid);
	dummy = 0;
    }
	
    @Override
    public void run() {
	while (!start);
	for (int i = 0; i < Thread.currentThread().getId() * 100; i++) {
	    dummy = (dummy + 1) % 32767;
	}
		
	while (!stop) {
	    Node n = dictionary.get(id);
	    if (n != null) {
		entries = entries + 1;
		for (int i = 0; i < delay; i++) {
		    dummy = (n.data + dummy) % 32767;
		}
	    } else {
		getf = getf + 1;
	    }
	}
    }
	
    // call this to make the busy loops do something
    public int getDummy() {
	return dummy;
    }
}
