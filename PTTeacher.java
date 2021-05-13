//PTT teach class
//committ test

public class PTTeacher {
	
	//class variables
	private String fName;
	private String lName;
	//private int year;
	private int tID;
	private static int nextTID;
	private boolean available;
	private String[] skills;
	private String[] training;
	//private ArrayList <Request>;
	private static ListOfPTT list; //-- invariant may be that if a teach exists then it must belong to a list
	
	//constructor
	public PTTeacher (String f, String l, int y ) {
		this.fName = f;
		this.lName = l;
		//this.year = y;
		this.tID = nextTID;
		nextTID ++;
		available = true;
		//initialise request list
		
		this.skills = new String[10];
		this.training = new String[10];
		
		listSetUp();
		
	}
	
	//method to manage teacher - list interaction
	//if instance is first teacher being created then list is created with it, if not the teacher is just added to the list
	//one variant may be that if there is a teacher, then a list must exist to contain the teacher
	private void listSetUp() {
		if (this.tID == 0) {
			list = new ListOfPTT();
			list.addTeacher(this);
		}
		
		else {
			list.addTeacher(this);
		}
	}

	
	
	//CLASS METHODS//
	
	public String toString() {
		String s = ""+ fName + " " + lName + ", "+ tID;
		return s;
	}
	
	public void printSkills() {
		for(String s : skills) System.out.println(s);
	}
	
	public void printTraining() {
		for(String s : training) System.out.println(s);
	}
	//toString
	//addSkill
	//assign
	//availability
	//training print
	//skill print
	//summary print
	
	
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public String[] getTraining() {
		return training;
	}

	public void setTraining(String[] training) {
		this.training = training;
	}

	public int gettID() {
		return tID;
	}
	
	
}