package edu.gatech.cx4230.projecttwo.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads in a CSV file
 * @author tbowling3
 *
 */
public abstract class CSVReader {
	protected ArrayList<CSVRow> rows;
	private int ROW_LIMIT = -1;
	private String csvSplitBy = ",";

	public CSVReader(String filename) {
		rows = new ArrayList<CSVRow>();
		if(FileHelper.fileExists(filename)) {
			BufferedReader br = null;
			String line = "";
			int lineCount = 0;


			try {

				br = new BufferedReader(new FileReader(filename));
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
	} // close constructor

	public ArrayList<CSVRow> getRows() {
		return rows;
	}
	
	protected abstract void handleHeaderRow(String[] line);
	
	protected abstract void handleDataRow(String[] line);
	
	


}
