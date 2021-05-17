import java.util.ArrayList;

//PTT teach class
//committ test

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
	private  ListOfPTT list; //-- invariant may be that if a teacher exists then it must belong to a list
	
	//constructor
	public PTTeacher (String f, String l, ListOfPTT j) {
		this.fName = f;
		this.lName = l;
		this.list = j;
		this.list.addTeacher(this);
		
		this.tID = nextTID;
		nextTID ++;
		available = true;
		
		
		 // this.skills = new ArrayList <String>(); this.training = new ArrayList
		 // <String>();
		 
		this.assign = new ArrayList <TeachRequest> ();
		
	}

	
	//CLASS METHODS//
	
	public String toString() {
		String s = ""+ fName + " " + lName + ", "+ tID;
		return s;
	}
	
	public String[] printSkills() {
		String [] s = new String[skills.size()];
		for(int i = 0; i<s.length; i++) {
			s[i] = skills.get(i);
		}
		
		return s;
	}
	
	public String [] printTraining() {
		String [] s = new String[training.size()];
		for(int i = 0; i<s.length; i++) {
			s[i] = training.get(i);
		}
		
		return s;
	}
	
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
	
	// keeping the training completion option:
	public void completeTraining(int i) {
		skills.add(training.get(i));
		training.remove(training.get(i));
	}
	
	
	// Added this here so i dont need a loop during the load process// @@ do we still need this if the array list are initialised in the constructor?@@
	
	public void addSkillArray(ArrayList <String> sArray) { skills = sArray; }
	  
	public void addTrainingArray(ArrayList <String> tArray) { training = tArray;}
	 
	
	
	//availability - assumed limit of classes a teacher can cover is 5. if no limit there is little use of availability as class var
	
	public boolean isAvailable() {
		if (assign.size() >= 5) available = false;
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	//assign

	protected boolean assign(TeachRequest q) {
		if (this.assign == null) this.assign = new ArrayList <TeachRequest>();
		
		if (this.isAvailable()) {
			this.assign.add(q);
			}
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

	
	public ListOfPTT getList(){
		return list;
	}

	public int gettID() {
		return tID;
	}
	
	
}