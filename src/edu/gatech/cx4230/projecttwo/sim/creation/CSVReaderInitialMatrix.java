package edu.gatech.cx4230.projecttwo.sim.creation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.cx4230.projecttwo.utilities.FileHelper;
import edu.gatech.cx4230.projecttwo.utilities.ListHelper;
import edu.gatech.cx4230.projecttwo.utilities.Logger;

public class CSVReaderInitialMatrix {
	private static final String filename = "data/initialMatrix.csv";
	private ArrayList<CSVRowInitialMatrix> rows;
	private String[] header;
	private int lineCount = 1;
	private int ROW_LIMIT = -1;
	private String csvSplitBy = ",";

	public CSVReaderInitialMatrix(String filename) {
		String filepath = FileHelper.getPathToResource(filename);
		System.out.println("CSVReaderInitialMatrix<init> "+ filepath);
		
		rows = new ArrayList<CSVRowInitialMatrix>();
		if(FileHelper.fileExists(filepath)) {
			BufferedReader br = null;
			String line = "";
			int lineCount = 0;

			try {
				br = new BufferedReader(new FileReader(filepath));
				while ((line = br.readLine()) != null) {
					if(ROW_LIMIT == -1 || lineCount < ROW_LIMIT) {
						// use comma as separator
						String[] lineSplit = line.split(csvSplitBy);

						// TODO write specific formatting for this
						if(lineCount == 0) {
							// This is the header row
							handleHeaderRow(lineSplit);
						} else {
							// This is the rest of the data
							handleDataRow(lineSplit);
						} // close else

					} else {
						break;
					} // close else
					lineCount++;
				} // close while

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public CSVReaderInitialMatrix() {
		this(filename);
	}

	private void handleHeaderRow(String[] line) {
		this.header = line;
		
	}

	private void handleDataRow(String[] line) {		
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
	
	/**
	 * @return the rows
	 */
	public ArrayList<CSVRowInitialMatrix> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(ArrayList<CSVRowInitialMatrix> rows) {
		this.rows = rows;
	}

	public static void main(String[] args) {
		CSVReaderInitialMatrix reader = new CSVReaderInitialMatrix();
		List<CSVRowInitialMatrix> rows = reader.getRows();
		System.out.println(ListHelper.listToString(rows));
	}

}
