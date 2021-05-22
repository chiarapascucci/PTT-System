package ModelViewController;
import java.util.ArrayList;

public class ListOfPTT {
	
	// Attributes
	private ArrayList <PTTeacher> list;
	
	// Constructor
	protected ListOfPTT () {
		list = new ArrayList <PTTeacher>();
	}
	
	//method to add a teacher to the list, only visibile to classes in the same package	
	protected void addTeacher(PTTeacher t) {
		list.add(t);
	}
	
	// Adding to ensure nextTID is always the highest
	protected int maxID() {
		
		int maxID = 0;	// Initialise maxID
		
		for (int i = 0; i < list.size(); i++) {	// Iterate over list
			if (list.get(i).gettID() > maxID) {	// Compare maxID with current ID
				maxID = list.get(i).gettID(); 	// If maxID < currentID -> maxID = currentID
			}
		}
		
		return maxID; 
	}
	
	// Adding a getPTTeacherReference function to find a teacher with their ID number
	protected PTTeacher getTeacherRef(int ID) {
		
		// Iterate over list
		for (int i = 0; i < list.size(); i++) {
			
			// If ID of desired teacher matches ID of a teacher in the list return it
			if (ID == list.get(i).gettID()) {
				return list.get(i);
			}
		}
		return null; 
	}
	
	//method to find teacher by their first name
	public PTTeacher getTeacherRef(String n) {
		for (PTTeacher t : list) {
			n.toLowerCase();
			if (t.getfName().trim().toLowerCase().equals(n)) return t;
		}
		
		return null;
	}
	
	//supporter method used by controller: this method takes the search string and search criteria input from the user
	//depending on the search criteria a different kind of search is done: on teacher ID, on skills, or training
	protected ArrayList <PTTeacher> findTeacher(String s, int i){
		
		ArrayList <PTTeacher> result = new ArrayList <PTTeacher>();
		
		s.toLowerCase().trim();
		
		if ( i == 0) {
			
			PTTeacher p = this.getTeacherRef(s);
			
			if (p != null) {
				result.add(p);
			}
		}
		
		else if (i == 1) {
			for (PTTeacher p : list) if (p.getSkills().contains(s)) result.add(p);
		}
		else if (i ==2) {
			for (PTTeacher p : list) if (p.getTraining().contains(s)) result.add(p);			
		}
		return result;
	}
	
	protected ArrayList <PTTeacher> getListReference(){
		return this.list;
	}
	
	
	protected String printList() {
		
		String returnString = ""; 
		
		// Calculate the max fName and lName chars
		int fName_maxChar = 0; 
		int lName_maxChar = 0; 
		for (PTTeacher p : list) {
			if (p.getfName().length() > fName_maxChar) {
				fName_maxChar = p.getfName().length(); 
			}
			
			if (p.getlName().length() > lName_maxChar) {
				lName_maxChar = p.getlName().length(); 
			}
		}
		
		// Generate string of list
		for (PTTeacher p : list) {
			returnString += String.format("%-" + (fName_maxChar+5) + "s%s%d\n", p.getfName(), p.getlName(), p.gettID());
		}
		
		
		return returnString; 
	}

}
