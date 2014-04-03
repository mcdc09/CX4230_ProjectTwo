package edu.gatech.cx4230.projecttwo.utilities;

import java.util.Map;

/**
 * Object to hold data read from a single row of a CSV file
 * @author tbowling3
 *
 */
public abstract class CSVRow {
	/**
	 * Contains the data for a specific row in the CSV file.  The key is the header.
	 */
	protected Map<String,String> data;
	protected int rowNumber;
	
	
	
	/**
	 * @return the data
	 */
	public Map<String, String> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	/**
	 * @return the rowNumber
	 */
	public int getRowNumber() {
		return rowNumber;
	}
	/**
	 * @param rowNumber the rowNumber to set
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
}
