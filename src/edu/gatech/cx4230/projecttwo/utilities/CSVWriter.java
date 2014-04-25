package edu.gatech.cx4230.projecttwo.utilities;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
	
	/**
	 * Writes an array to a comma-separated-variable (CSV) file to the directory of this application
	 * @param array Array to be saved to the file
	 * @param filename Name of the csv File
	 */
	public static void writeDoubleArrayToCSV(double[][] array, String filename) {
		try {
			if(!filename.endsWith(".csv")) {
				filename = filename + ".csv";
			}
			if(array != null) {
				FileWriter fw = new FileWriter(filename);
				for(int i = 0; i < array.length; i++) {
					String line = "";

					for(int j = 0; j < array[i].length; j++) {
						if(j != 0) line += ", ";
						line += array[i][j];
					} // close for j
					line += "\n";
					fw.write(line);
				} // close for i

				fw.close();
			} // close if null
		} catch (IOException e) {
			System.err.println("There was a problem writing Array to CSV file");
			e.printStackTrace();
		} // close catch
	} // close method

	/**
	 * Writes an array to a comma-separated-variable (CSV) file to the directory of this application
	 * @param array Array to be saved to the file
	 * @param filename Name of the csv File
	 */
	public static void writeIntArrayToCSV(int[][] array, String filename) {
		try {
			if(!filename.endsWith(".csv")) {
				filename = filename + ".csv";
			}
			if(array != null) {
				FileWriter fw = new FileWriter(filename);
				for(int i = 0; i < array.length; i++) {
					String line = "";

					for(int j = 0; j < array[i].length; j++) {
						if(j != 0) line += ", ";
						line += array[i][j];
					} // close for j
					line += "\n";
					fw.write(line);
				} // close for i

				fw.close();
			} // close if null
		} catch (IOException e) {
			System.err.println("There was a problem writing Array to CSV file");
			e.printStackTrace();
		} // close catch
	} // close method
	
	/**
	 * Writes an array to a comma-separated-variable (CSV) file to the directory of this application
	 * @param array Array to be saved to the file
	 * @param filename Name of the csv File
	 */
	public static void writeIntArrayToCSV(Object[][] array, String filename) {
		try {
			if(!filename.endsWith(".csv")) {
				filename = filename + ".csv";
			}
			if(array != null) {
				FileWriter fw = new FileWriter(filename);
				for(int i = 0; i < array.length; i++) {
					String line = "";

					for(int j = 0; j < array[i].length; j++) {
						if(j != 0) line += ", ";
						line += array[i][j];
					} // close for j
					line += "\n";
					fw.write(line);
				} // close for i

				fw.close();
			} // close if null
		} catch (IOException e) {
			System.err.println("There was a problem writing Array to CSV file");
			e.printStackTrace();
		} // close catch
	} // close method

}
