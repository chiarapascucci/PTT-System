import java.util.ArrayList;

public class PTTeacher {
	
	//class variables
	private String fName;
	private String lName;
	private int tID;
	private static int nextTID;
	private boolean available;
	private ArrayList <String> skills;
	private ArrayList <String> training;
	private ArrayList <TeachRequest> assign;
	
	//PTT TEACHER CLASS INVARIANTS//
	//1.A teacher must always belong to a list
	//2.A teacher can only be assigned to maximum of 5 teaching requests at one time
	//3.A teacher's ID is unique
	
	//constructor used when loading a new file
	public PTTeacher (String f, String l, ListOfPTT j) {
		this.fName = f;
		this.lName = l;
		j.addTeacher(this);
		
		this.tID = nextTID;
		nextTID ++;
		available = true;
		
		this.skills 	= new ArrayList <String>(); 
		this.training 	= new ArrayList<String>();

		this.assign = new ArrayList <TeachRequest> ();
	}
	
	// Overloaded constructor to use when loading data saved previously
	public PTTeacher (String f, String l, int ID, ListOfPTT j) {
		this.fName = f;
		this.lName = l;
		j.addTeacher(this);
		
		// Setting ID from saved data 
		this.tID = ID; 
		nextTID = j.maxID() + 1; 	// Max ID enforces that the nextID is set to the max after loading data 
		available = true;
		
		this.skills 	= new ArrayList <String>(); 
		this.training 	= new ArrayList<String>();
		 
		this.assign = new ArrayList <TeachRequest> ();
	}

	
	//CLASS METHODS//
	
	//----------------//printing methods//--------------//
	
	public String toString() {
		

		String s = "  First name: "+ fName +"\n"+ "  Last name: " + lName  + "\n" + "  ID: "+ tID + "\n" + this.printSkills() + "Training:\n" + this.printTraining() + "\n";

		return s;
	}
	
	public String printSkills() {
		String s = "";
		if (!this.skills.isEmpty()) {
			for(int i = 0; i<this.skills.size(); i++) {
				s += "  - " + skills.get(i)+"\n";
			}
		}
		
		return s;
	}
	
	public String printTraining() {
		String s = "";
		if(!this.training.isEmpty()) {
			for(int i = 0; i<this.training.size(); i++) {
				s += "  " + training.get(i)+" ";
			}
		}
		
		return s;
	}
	
	//-----------------//Training and Skills Methods//--------------//
	
	public boolean addTraining(String s) {
		s= s.trim().toLowerCase();
		if (training.contains(s) || skills.contains(s)) return false;
		else {
			training.add(s);
			return true;
		}
		
	}
	
	public boolean addSkill(String s) {
		s= s.trim().toLowerCase();
		if (skills.contains(s)) return false;
		else {
			skills.add(s);
			return true;
		}	
	}
	
	public boolean removeTraining(String s) {
		s = s.trim().toLowerCase();
		if (training.contains(s)) {
			training.remove(s);
			invalidCheck(s);
			return true;
		}
		
		else return false;
		
	}
	
	public boolean removeSkill(String s) {
		s = s.trim().toLowerCase();
		if (skills.contains(s)) {
			skills.remove(s);
			invalidCheck(s);
			return true;
		}
		
		else return false;
	}
	
	// specialised training/skill method: when training is completed the teacher is recorded as having acquired the skill
	public boolean completeTraining(String s) {
		s = s.toLowerCase().trim();
		boolean res = false;
		int index = training.indexOf(s);
		if (index == -1) return res;
		else {
			skills.add(training.get(index));
			training.remove(training.get(index));
			res=true;
			return res;
		}
	}
	
	// Checks whether removed training/skill invalidates PTTeacher's assigned requests
	private void invalidCheck(String s) {
		
		// Look for invalidated requests
		ArrayList <TeachRequest> invalidRequest = new ArrayList <TeachRequest> ();
		for(TeachRequest t: this.assign) {
			if(t.getTrainingRequired().contains(s)) {
				t.statusCheck();
				invalidRequest.add(t);
			}
		}
		
		// Remove teacher's invalidated requests
		for(TeachRequest r: invalidRequest) {
			this.removeRequest(r);
		}	
	}

	//--------//TEACHER ASSIGMENT AND AVAILABILITY METHODS//--------//
	
	//availability check
	public boolean isAvailable() {
		if (assign.size() >= 5) available = false;
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	//method to assign a teacher to a request
	protected boolean assignTeacher(TeachRequest q) {
		
		// is teacher available
		if (this.isAvailable() == false) {
			return false;
		} 
		// is request already assigned to teacher
		else if (this.assign.contains(q)) {
			return false;
		}
		else if (this.isAvailable()) {
			this.assign.add(q);
			this.isAvailable();
			return true;
		}
		return false;
	}

	// Method to manage teacher assignment, not a user function itself
	protected void removeRequest(TeachRequest q) {
		
		if(this.assign.contains(q)) {
			this.assign.remove(q);
			if(this.assign.size() < 5) { this.setAvailable(true); }	
		}
	}
	
	//GETTERS AND SETTERS//
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public ArrayList <String> getSkills() {
		return skills;
	}

	public ArrayList <String> getTraining() {
		return training;
	}
	
	public ArrayList <TeachRequest> getAssigned() {
		return assign;
	}

	public int gettID() {
		return tID;
	}
	
	
	//--------//Data Handler Helper Methods//-------//
	//methods to help data loading and writing by DataHandler class. 
	//strings are trimmed and set to lower cases
	
	protected void addSkillArray(ArrayList <String> sArray) { 
		for (String i : sArray) i = i.trim().toLowerCase();
		skills = sArray; 
		
		
	}
	  
	public void addTrainingArray(ArrayList <String> tArray) { 
		for (String i : tArray) i = i.trim().toLowerCase();
		training = tArray;
	}
	
	
}
