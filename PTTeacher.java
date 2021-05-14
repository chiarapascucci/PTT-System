import java.util.ArrayList;

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
	private ArrayList <TeachRequest> assign;
	private static ListOfPTT list; //-- invariant may be that if a teacher exists then it must belong to a list
	
	//constructor
	public PTTeacher (String f, String l, ListOfPTT j) {
		this.fName = f;
		this.lName = l;
		this.list = j;
		this.list.addTeacher(this);
		
		
		//this.year = y;
		this.tID = nextTID;
		nextTID ++;
		available = true;
		//initialise request list
		
		this.skills = new String[10];
		this.training = new String[10];
		
		//listSetUp();
		
	}
	
	//method to manage teacher - list interaction
	//if instance is first teacher being created then list is created with it, if not the teacher is just added to the list
	//one invariant may be that if there is a teacher, then a list must exist to contain the teacher
	/*
	 * private void listSetUp() { if (this.tID == 0) { list = new ListOfPTT();
	 * list.addTeacher(this); }
	 * 
	 * else { list.addTeacher(this); } }
	 */

	
	
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
	
	public void addTraining(String s) {
		//Need input checking
		if (freeSpace(this.training) == false) {
			System.out.println("error");
		}
		else {
			for (int i =0; i<training.length;i++) {
				if (training[i] == null) {
					training[i] = s;
					break;
				}
				
			}
		}
	}
	
	public void addSkill(String s) {
		if (freeSpace(this.skills) == false) {
			System.out.println("error"); //maybe replace with error message
		}
		else {
			for (int i =0; i<skills.length;i++) {
				if (skills[i] == null) {
					skills[i] = s;
					break;
				}
				
			}
		}
	}
	
	// Added this here so i dont need a loop during the load process
	public void addSkillArray(String[] sArray) {
		skills = sArray;
	}
	
	public void addTrainingArray(String[] tArray) {
		training = tArray; 
	}
	
	public void complTraining(int i) {
		this.addSkill(this.training[i]);
		this.training[i] = null;
	}
	private static boolean freeSpace(String[]s) {
		boolean result = false;
		for (String i : s) {
			if(i == null) result = true;
		}
		
		return result;
	}
	
	public boolean isAvailable() {
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

	

	public String[] getSkills() {
		return skills;
	}



	public String[] getTraining() {
		return training;
	}

	
	public ListOfPTT getList(){
		return list;
	}

	public int gettID() {
		return tID;
	}
	
	
}