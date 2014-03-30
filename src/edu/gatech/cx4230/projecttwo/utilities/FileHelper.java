package edu.gatech.cx4230.projecttwo.utilities;

import java.io.File;

/**
 * Contains many useful methods for manipulating Files and check if they exist
 * @author tBowling3
 *
 */
public class FileHelper {
	public static final String WORKING_DIRECTORY = "CX4230_ProjectTwo";
	public static final String RESOURCE_PATH = "res/";
	
	/**
	 * Creates a new FileHelper object
	 */
	public FileHelper() {
		
	}

	/**
	 * Checks if the given file exists.  The filename must include a file path
	 * @param filename Filename of the file in question
	 * @return true if the file exists and false otherwise
	 */
	public boolean fileExists(String filename) {
		boolean out = false;
		
		if(filename != null) {
			File file = new File(filename);
			if(file.isFile()) {
				out = true;
			}
		}
		
		return out;
	}
	
	/**
	 * Checks if the given file exists in the given filepath
	 * @param path Folder path to the file in question
	 * @param filename Name of the file in question
	 * @return true if the file exists and false otherwise
	 */
	public boolean fileExists(String path, String filename) {
		return fileExists(path + File.separator + filename);
	}
	
	/**
	 * Tests if a folder exists
	 * @param folder Folder path in question
	 * @return true if the folder is a directory that works and false otherwise
	 */
	public boolean folderExists(String folder) {
		boolean out = false;
		
		if(folder != null) {
			File file = new File(folder);
			if(file.isDirectory()) {
				out = true;
			}
		}
		return out;
	}
	
	/**
	 * Checks if a set of files exists for a given folder path
	 * @param path Folder path to the set of files in question
	 * @param files Array of filenames to be tested
	 * @return true if all the files exist and false otherwise
	 */
	public boolean checkFiles(String path, String[] files) {
		boolean out = true;
		for(int i = 0; i < files.length; i++) {
			File file = new File(path + File.separator + files[i]);
			if(!file.exists()) {
				out = false;
				break;
			}
		}
		return out;
	}
	
	public String getPathToResource(String filename) {
		String workingDirectory = System.getProperty("user.dir");
		int index = workingDirectory.indexOf(WORKING_DIRECTORY);
		String localPath = workingDirectory.substring(0,index);
		String out = localPath + WORKING_DIRECTORY + "/" + RESOURCE_PATH + filename;
		
		return out;
	}
	
	public static void main(String[] args) {
		FileHelper fh = new FileHelper();
		String out = fh.getPathToResource("Model_Roster.csv");
		System.out.println(out);
	}
}
