
// Import statements 
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/* 
 * Class Objective: 
 * - Read program data from a .txt file 
 * - Write program data to a .txt file 
 * - Read in request data from Course director input
 */

// Static class so only one instance can exist
public class DataHandler {
	
	// Attributes (references to key data variables) 
	private static ListOfPTT 		LOP; 
	private static ListOfRequests 	LOR;
	private static String filename = "PTTAppData.txt"; 
	
	// Test method
	public static void setRef() {
		LOP = new ListOfPTT();
		LOR = new ListOfRequests();
	}
	
	// Class static methods 
	public static void loadData(String path) {
		
		// Instantiate lists 
		LOP = new ListOfPTT();
		LOR = new ListOfRequests();
		
		// Need to check if file exists firsts and create it if it doesnt exist
		
		// Read from file
		FileReader filereader 	= null;
		Scanner	input		  	= null;
		
		try {
			filereader = new FileReader(path + "\\" + filename); 
			input = new Scanner(filereader); 
			
			// Loop over file lines
			while (input.hasNextLine()) {
				
				// Line to be parsed 
				String line = input.nextLine(); 
				
				// Send line to parse and set function which parses the line 
				// and applies the data to its respective object and list
				parseDataAndSet(line); 
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
			// Close file reader and Scanner
			try {
				filereader.close(); 
				input.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void saveData(String path) { 
		
		FileWriter filewriter = null;
		try {
			filewriter = new FileWriter(path + "\\" + filename); 

			for (int i = 0; i < LOP.getListReference().size(); i++) {
				String exportString = exportDataFormat(LOP.getListReference().get(i)); 
				filewriter.write(exportString);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally { 
			try {
				filewriter.close(); 
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	// Parse data being imported
	private static void parseDataAndSet(String data) {


		String [] splitData;

		// Parse line as a teacher data object
		if (data.charAt(0) == 'T') {

			// Remove outer casing of the data 
			data = data.substring(2,data.length()-2); 		// Removing "T(" at start and ")" at end. Could change this to calculate ( position first

			// Split data by commas and store into a string array 
			splitData = data.split(",");

			// Create a new teach entity (adds to list automatically in the constructor to satisfy class invariant)
			PTTeacher p = new PTTeacher(splitData[0], splitData[1], LOP); 	


			/* Extract and set rest of PTTeacher attributes */

			// Normal attributes
			// ID is set on creation. Might need a new constructor to allow for ID to be set by loaded data 
			p.setAvailable(Boolean.parseBoolean(splitData[3]));	

			// Skills 
			String temp = splitData[4].substring(1,splitData[4].length()-2); 	// Remove out casing of Skills data (the curly brackets) 
			p.addSkillArray(temp.split(","));

			// Training
			temp = splitData[5].substring(1,splitData[5].length()-2); 	
			p.addTrainingArray(temp.split(","));
		}
		// Parse line as a request data object
		if (data.charAt(0) == 'R') {

			// Remove outer casing of the data 
			data = data.substring(2,data.length()-2); 		

		}			
	}

	
	// Format teacher data for exporting data 
	private static String exportDataFormat(PTTeacher T) {
		
		
		// Create string to store all teacher data and instantiate to be returned. 
		// Data is encapsulated in an indicator for its respective class and brackets
		
		String	 exportString = "T("; 
		
		
		// Append class attributes
		exportString += T.getfName() + "," + T.getlName() + "," + T.gettID() + "," + T.isAvailable(); 
		
		// Store arrays as a single string encapuslated in {} for clarity 
		// Skills
		String temp = "{"; 
		for (int i = 0; i < T.getSkills().length; i++) {
			
			if (i < T.getSkills().length - 1) {
				temp += T.getSkills()[i] + ",";
			}
			else {
				temp += T.getSkills()[i] + "}";
			}
		}
		exportString += "," + temp;
		
		
		// Training string
		temp = "{"; 
		for (int i = 0; i < T.getTraining().length; i++) {
			
			if (i < T.getTraining().length - 1) {
				temp += T.getTraining()[i] + ",";
			}
			else {
				temp += T.getTraining()[i] + "}";
			}
		}
		exportString += "," + temp + ")\n"; 
		
		return exportString; 
	}

	// Format request data for export
	private static String exportDataFormat(TeachRequest R) {
		String s = ""; 
		return s; 
	}


	// Getters
	public static ListOfPTT getLOP() {
		return LOP; 
	}
	
	public static ListOfRequests getLOR() {
		return LOR; 
	}
}