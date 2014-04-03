package edu.gatech.cx4230.projecttwo.sim.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.gatech.cx4230.projecttwo.utilities.CSVRow;

public class CSVRowInitialMatrix extends CSVRow {
	private String originAirport;
	
	public CSVRowInitialMatrix(int line, String here, Map<String,String> rowData) {
		this.rowNumber = line;
		this.data = rowData;
		this.originAirport = here;
	}

	/**
	 * @return the originAirport
	 */
	public String getOriginAirport() {
		return originAirport;
	}
	
	public int getWeightForAirport(String code) {
		int out = -1;
		if(code != null && !code.isEmpty() && data.containsKey(code)) {
			out = Integer.parseInt(data.get(code));
		}
		return out;
	}
	
	public String[] getAirports() {
		List<String> set = new ArrayList<String>(data.keySet());
		String[] out = new String[set.size()];
		for(int i = 0; i < set.size(); i++) {
			out[i] = set.get(i);
		}
		return out;
	}

	/**
	 * @param originAirport the originAirport to set
	 */
	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}

}
