
// Import statements 
import java.io.*;
import java.util.Scanner;

/* 
 * Class Objective: 
 * - Read program data from a .txt file 
 * - Write program data to a .txt file 
 * - Read in request data from Course director input
 */

// Static class so only one instance can exist
public class DataHandler extends AbstractDataHandlerFactory {
	
	
	// Attributes (references to key data variables) 
	private  ListOfPTT 		LOP; 
	private  ListOfRequests LOR;

	// Singleton attribute (to tell if one has been created yet)
	private static DataHandler instance = null; 
	
	
	// Constructor (private for singleton)
	private DataHandler() {
		
		// Instantiate list on construction
		LOP = new ListOfPTT();
		LOR = new ListOfRequests();	
	}; 
	
	
	// Singleton method to create only one instance
	public static DataHandler getDataHandlerInstance() {
	
		// Check if instance has been created yet. If not, create. 
		if (instance == null) {
			instance = new DataHandler(); 
		}
		return instance;
	}
	
	
	/* AbstractDataHandlerFactory class method implementations */

	public void loadData(String filepathAndName) {
		
		// Need to check if file exists firsts and create it if it doesnt exist
//		File f = new File(filepathAndName);
		
		// If the file does not exist
//		if(!f.isFile()) {
//			
//			// Create a file 
//			try {
//				f.createNewFile();
//			}
//			catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		// Read from file
		FileReader filereader 	= null;
		Scanner	input		  	= null;
		
		try {
			filereader = new FileReader(filepathAndName); 
			input = new Scanner(filereader); 
			
			// Loop over file lines
			while (input.hasNextLine()) {
				
				// Line to be parsed 
				String line = input.nextLine(); 
				
				// Send line to parse and set function which parses the line 
				// and applies the data to its respective object and list
				parseDataAndSet(line); 
				
				System.out.println("DH: done");
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

	public void saveData(String filepathAndName) { 
		
		FileWriter filewriter = null;
		try {
			filewriter = new FileWriter(filepathAndName); 
			
			String exportString; 
			
			// Write the teachers to the file
			for (int i = 0; i < LOP.getListReference().size(); i++) {
				exportString = exportDataFormat(LOP.getListReference().get(i)); 
				filewriter.write(exportString);
			}
			
			// Write the active requests to the file 
			for (int i = 0; i < LOR.getListReference().size(); i++) {
				exportString = exportDataFormat(LOR.getListReference().get(i)); 
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
	
	public ListOfPTT getLOP() {
		return LOP; 
	}
	
	public ListOfRequests getLOR() {
		return LOR; 
	}
	
	
	
	
	/* Unique class methods (DatabaseDataHandler will likely do something entirely different) */

	// Parse data being imported
	private void parseDataAndSet(String data) {

		// Create string array container to hold data the line data in its tokens
		String [] splitData;

		// Parse line as a teacher data object
		if (data.charAt(0) == 'T') {

			// Remove outer casing of the data 
			data = data.substring(2,data.length()-1); 		// Removing "T(" at start and ")" at end. Could change this to calculate ( position first

			// Split data by commas and store into a string array 
			splitData = data.split(",");

			// Create a new teach entity (adds to list automatically in the constructor to satisfy class invariant)
			// Using constructor with ID to enforce constant ID for each teacher when loading in teacher data 
			//							 First name,	last name  ,   			ID ,				List reference
			PTTeacher p = new PTTeacher(splitData[0], splitData[1], Integer.parseInt(splitData[2]), LOP); 	


			/* Extract and set rest of PTTeacher attributes */

			// Normal attributes
			p.setAvailable(Boolean.parseBoolean(splitData[3]));	

			// Skills 
			String temp = splitData[4].substring(1,splitData[4].length()-1); 	// Remove out casing of Skills data {}
			String[] tempArray = temp.split(";");
			for (int i = 0; i < tempArray.length; i++) {
				p.getSkills().add(tempArray[i]);
			}

			// Training
			temp = splitData[5].substring(1,splitData[5].length()-1); 
			tempArray = temp.split(";");
			for (int i = 0; i < tempArray.length; i++) {
				p.getTraining().add(tempArray[i]);
			}
		}
		// Parse line as a request data object
		if (data.charAt(0) == 'R') {

			// Temp string container to store data
			String temp;
			
			// Remove outer casing of the data 
			data = data.substring(2,data.length()-1); 		// Removing "R(" at start and ")" at end. Could change this to calculate ( position first

			// Split data by commas and store into a string array 
			splitData = data.split(",");

			// For training data to be passed to request (remove outer curly brackets
			temp = splitData[4].substring(1,splitData[4].length()-1);
			
			// Create a new request entity (adds to list automatically in the constructor to satisfy class invariant)
			// Using constructor with ID to enforce constant ID for each teacher when loading in teacher data 
			//											ID, 					CourseID, 			numTeachers, 		List ref,  Training required					
			TeachRequest r = new TeachRequest(Integer.parseInt(splitData[0]), splitData[1], Integer.parseInt(splitData[3]),LOR,		temp ); 	


			/* Extract and set rest of TeachRequest attributes */

			// Teachers assigned to request
			temp 		= splitData[5].substring(1,splitData[5].length()-1); 	// Remove out casing of assigned teacher data 
			
			// Splitting IDs assigned to the request (if multiple)
			String[] tempArray 	= temp.split(";"); 
			
			// Iterating over IDs to fetch references of PTTeachers to assign to request
			for (int i = 0; i < tempArray.length; i++) {
				if (! tempArray[i].isEmpty()) {
					r.getAssigned().add(LOP.getTeacherRef(Integer.parseInt(tempArray[i])));
				}
			}
		}			
	}

	// Format teacher data for exporting data 
	private String exportDataFormat(PTTeacher T) {
		
		
		// Create string to store all teacher data and instantiate to be returned. 
		// Data is encapsulated in an indicator for its respective class and brackets
		
		String	 exportString = "T("; 
		
		// Append class attributes 
		// ArrayLists<> references toString() produces [String1, String 2] automatically. 
		exportString += T.getfName() + "," + T.getlName() + "," + T.gettID() + "," + T.isAvailable(); 
		
		// Store arrays as a single string encapuslated in {} for clarity
		// Using different delimiter for parsing (;)
		// Skills
		String temp = "{"; 
		for (int i = 0; i < T.getSkills().size(); i++) {
			
			if (i < T.getSkills().size() - 1) {
				temp += T.getSkills().get(i) + ";";
			}
			else { 
				temp += T.getSkills().get(i);
			}
		}
		temp += "}";
		exportString += "," + temp;
		
		
		// Training string
		temp = "{"; 
		for (int i = 0; i < T.getTraining().size(); i++) {
			
			if (i < T.getTraining().size() - 1) {
				temp += T.getTraining().get(i) + ";";
			}
			else {
				temp += T.getTraining().get(i);
			}
		}
		temp += "}";
		exportString += "," + temp + ")\n"; 
		
		return exportString; 
	}

	// Format request data for export
	private String exportDataFormat(TeachRequest R) {
		// Create string to store all teacher data and instantiate to be returned. 
		// Data is encapsulated in an indicator for its respective class and brackets
		
		String	 exportString = "R("; 
		
		// Append class attributes
		exportString += R.getReqID() + "," + R.getStatus() + "," + R.getCourseID() + "," + R.getTeachNo();  
		
		// Store arrays as a single string encapuslated in [] for clarity 
		
		// Training 
		String temp = "{"; 
		for (int i = 0; i < R.getTrainingRequired().size(); i++) {
			
			// Only storing ID as a string. Reference can be established on load. 
			if (i < R.getTrainingRequired().size() - 1) {
				temp += R.getTrainingRequired().get(i) + ";";
			}
			else {
				temp += R.getTrainingRequired().get(i);
			}
		}
		temp += "}";
		exportString += "," + temp;
		
		// assigned teachers 
		temp = "{"; 
		for (int i = 0; i < R.getAssigned().size(); i++) {
			
			// Only storing ID as a string. Reference can be established on load. 
			if (i < R.getAssigned().size() - 1) {
				temp += R.getAssigned().get(i).gettID() + ";";
			}
			else {
				temp += R.getAssigned().get(i).gettID();
			}
		}
		temp += "}";
		exportString += "," + temp + ")\n"; 
		
		return exportString; 
	}


}
