package edu.gatech.cx4230.projecttwo.sim.creation;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.cx4230.projecttwo.utilities.CSVReader;
import edu.gatech.cx4230.projecttwo.utilities.FileHelper;
import edu.gatech.cx4230.projecttwo.utilities.Logger;

public class CSVReaderInitialMatrix extends CSVReader {
	private static final String filename = "initialMatrix.csv";
	private String[] header;
	private int lineCount = 1;

	public CSVReaderInitialMatrix(String filename) {
		super(FileHelper.getPathToResource(filename));
		// TODO Auto-generated constructor stub
	}
	
	public CSVReaderInitialMatrix() {
		this(filename);
	}

	@Override
	protected void handleHeaderRow(String[] line) {
		this.header = line;
		
	}

	@Override
	protected void handleDataRow(String[] line) {		
		if(header.length == line.length) {
			Map<String, String> rowData = new HashMap<String,String>();
			for(int i = 1; i < header.length; i++) {
				rowData.put(header[i], line[i]);
			} // close for
			CSVRowInitialMatrix row = new CSVRowInitialMatrix(lineCount, line[0], rowData);
			rows.add(row);
			lineCount++;
		} else {
			Logger.logLine("Header and Data row lengths don't match");
		}	
	}

}
