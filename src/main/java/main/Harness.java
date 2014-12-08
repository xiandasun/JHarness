package main;

import components.Worker;
import components.Reader;
import components.Writer;

public class Harness {

	public static void main(String[] args) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		if (args.length < 6) {
			System.out.println("Usage: java Harness Dictionary Time #Reader #Writer Delay");
			for (int i = 0; i < args.length; i++) {
				System.out.print(args[i] + " ");
			}
			return;
		}
		
		String DictionaryName = args[1];
		int Time = Integer.parseInt(args[2]);
		int Readers = Integer.parseInt(args[3]);
		int Writers = Integer.parseInt(args[4]);
		int Delay = Integer.parseInt(args[5]);
		
		Worker.use(DictionaryName);
		Worker.populateDictionary(Readers + Writers);
		Worker.setDelay(Delay);
		
		Reader reader[] = new Reader[Readers];
		Writer writer[] = new Writer[Writers];
		
		for (int i = 0; i < Readers; i++) {
			reader[i] = new Reader(i);
			reader[i].start();
		}
		for (int i = 0; i < Writers; i++) {
			writer[i] = new Writer(i + Readers);
			writer[i].start();
		}
		
		Worker.turnOn();
		try {
			Thread.currentThread();
			Thread.sleep(Time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Worker.turnOff();
		
		for (int i = 0; i < Readers; i++) {
			reader[i].join();
		}
		
		for (int i = 0; i < Writers; i++) {
			writer[i].join();
		}
		
		// ***************************************
		// statistic variables
		
		int subReads[] = new int[Readers];
		int subWrites[] = new int[Writers];
		int subGetf[] = new int[Readers];
		int dummies[] = new int[Readers + Writers];
		int totalReads = 0, totalGetf = 0, totalWrites = 0, totalDummy = 0;
		int averReads, averWrites;
		
		// ***************************************
		
		// collecting
		for (int i = 0; i < Readers; i++) {
			subReads[i] = reader[i].getEntries();
			subGetf[i] = reader[i].getGetf();
			dummies[i] = reader[i].getDummy();
		}
		
		for (int i = 0; i < Writers; i++) {
			subWrites[i] = writer[i].getEntries();
			dummies[i + Readers] = writer[i].getDummy();
		}
		
		// calculations
		for (int i = 0; i < Readers; i++) {
			totalReads += subReads[i];
			totalGetf += subGetf[i];
			totalDummy += dummies[i];
		}
		
		for (int i = 0; i < Writers; i++) {
			totalWrites += subWrites[i];
			totalDummy += dummies[Readers + i];
		}
		
		averReads = totalReads / Readers;
		averWrites = totalWrites / Writers;
		
		System.out.println(
				Readers 
				+ "\t" 
				+ Writers
				+ "\t"
				+ totalReads
				+ "\t"
				+ totalGetf
				+ "\t"
				+ totalWrites
				+ "\t"
				+ totalDummy);
		
		return;
	}
}
