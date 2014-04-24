package edu.gatech.cx4230.projecttwo.sim.testing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.utilities.FileHelper;

/**
 * A specific logger for a specific type of event
 * @author tbowling3
 *
 */
public class AirportSimulationLogger {
	private List<String> lines;
	private boolean print;
	private String partialLine;
	private String name;

	public AirportSimulationLogger(boolean print, String name) {
		this.print = print;
		this.name = name;
		lines = new ArrayList<String>();

	}

	/**
	 * Clears the log of lines
	 */
	public void clear() {
		lines.clear();
	}

	/**
	 * Saves the log to the specified file
	 * @param filename
	 * @param clear
	 */
	public void save(String filename, boolean clear) {
		String out = FileHelper.getPathToResource("Output/" + filename);

		if(!out.endsWith(".txt")) {
			out = out + ".txt";
		}
		try {
			FileWriter fw = new FileWriter(out);
			fw.write(this.toString());

			for(String line: lines) {
				fw.write(line);
			} // close for i

			fw.close();
		} catch(IOException e) {
			System.err.println("There was a problem writing Log to txt file");
			e.printStackTrace();
		}
		if(print) System.out.println("Log saved");
		if(clear) clear();
	}

	public void save(boolean clear) {
		this.save(name, clear);
	}

	public String toString() {
		return "ASLogger: " + name + " Line Count: " + lines.size() + "\n";
	}


	// Log methods
	public void logLine(String line) {
		if(line != null) {
			if(print) System.out.println(line);
			if(partialLine != null && !partialLine.isEmpty()) {
				line += partialLine;
				partialLine = null;
			}
			lines.add(line + "\n");

		}
	}

	public void log(String partial) {
		if(partial != null) {
			partialLine += partial;
			System.out.print(partial);
		}
	}

	public void logLine(Object o) {
		if(o != null) {
			logLine(o.toString());
		}
	}

	public void log(Object o) {
		if(o != null) {
			log(o.toString());
		}
	}
	
	public boolean isPrint() {
		return print;
	}
	
	public void setPrint(boolean bool) {
		this.print = bool;
	}

}
