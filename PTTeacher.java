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
		String s = ""+ fName + " " + lName + ", "+ tID +"\n";
		return s;
	}
	
	public String printSkills() {
		String s = "";
		for(int i = 0; i<this.skills.size(); i++) {
			s = " " + skills.get(i);
		}
		
		return s;
	}
	
	public String printTraining() {
		String s = "";
		for(int i = 0; i<=this.training.size(); i++) {
			s = " " + training.get(i);
		}
		
		return s;
	}
	
	//-----------------//Training and Skills Methods//--------------//
	
	public void addTraining(String s) {
		training.add(s);
	}
	
	public void addSkill(String s) {
		skills.add(s);		
	}
	
	public void removeTraining(String s) {
		training.remove(s);
	}
	
	public void removeSkill(String s) {
		skills.remove(s);
	}
	
	// specialised training/skill method: when training is completed the teacher is recorded as having acquired the skill
	public void completeTraining(int i) {
		skills.add(training.get(i));
		training.remove(training.get(i));
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
	
	//method to assing a teacher to a request
	
	protected boolean assignTeacher(TeachRequest q) {
		
		if (this.isAvailable() == false) return false ;
		else if (this.isAvailable()) this.assignTeacher(q);
			
		if (this.assign.size()>= 5) {	
			this.setAvailable(false);
			return true;
		}
		
		return false;
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

	public int gettID() {
		return tID;
	}
	
	
	//--------//Data Handler Helper Methods//-------//
	//methods to help data loading and writing by DataHandler class. 
	//strings are trimmed
	
	protected void addSkillArray(ArrayList <String> sArray) { 
		for (String i : sArray) i = i.trim();
		skills = sArray; 
		
		
	}
	  
	public void addTrainingArray(ArrayList <String> tArray) { 
		for (String i : tArray) i = i.trim();
		training = tArray;
	}
	
	
}
