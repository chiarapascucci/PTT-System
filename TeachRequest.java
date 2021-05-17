import java.util.ArrayList;

public class TeachRequest {
	private int id;
	private static int nextID = 0;
	private boolean status;

	private String courseID;
	private int teachNo;
	private ArrayList <String> trainingRequired;
	private ArrayList <PTTeacher> assigned;

	// Pass: associated course, required no. of staff, required training
	// Need to implement free-ID check later
	public TeachRequest(String c, int no, String... t) {
		id = nextID;
		nextID++;
		requestSetup(c,no,t);
	}

	// Alt. constructor sets a specific request ID
	public TeachRequest(int i, String c, int no, String... t) {
		id = i;
		requestSetup(c,no,t);
	}

	// Helper for common constructor content
	// Exceptions to capture: arraylist constructor
	private void requestSetup(String c, int no, String... t) {
		status = false;
		courseID = c;
		teachNo = no;
		assigned = new ArrayList <PTTeacher> (no);

		//Process training args
		trainingRequired = new ArrayList <String> ();
		for(String arg: t) {
			trainingRequired.add(arg);
		}

		this.statusCheck();
	}

	

	public void addTeacher(PTTeacher target) {
		assigned.add(target);
		this.statusCheck();
	}

	public void removeTeacher(PTTeacher target) {
		assigned.remove(target);
		this.statusCheck();
	}

	// Checks fulfillment of request requirements
	public void statusCheck() {

		boolean passCheck = true;

		// Check for enough assigned teachers.
		assigned.trimToSize();
		if(assigned.size() < teachNo){
			status = false;	
			passCheck = false;
		} 

		// Per assigned PTT, check for required training/skill. Note excess teachers must be trained too.
		// Missing check for null skill/training array(?)
		else {
			for(PTTeacher t: assigned) {
				boolean teachersOK = false;
				for(String s: trainingRequired) {
					if(t.getTraining().isEmpty() && t.getSkills().isEmpty()) {
						break;
					}else if ((t.getTraining()).contains(s) || (t.getSkills()).contains(s)) {
						teachersOK = true;
						break;
					}
				}
				if(!teachersOK) {
					status = false;
					passCheck = false;
					break;
				}
			}
		}

		if(passCheck) { status = true; }
	}

	public void printTrainingRequired() {
		if(trainingRequired != null) {
			for(String s: trainingRequired) {	System.out.print(s + " ");	}
		}
	}

	public void printTeachers() {
		if(assigned != null) {
			for(PTTeacher t: assigned) {	System.out.println(t);	}
		}
	}

	// Update this with something more useful if necessary
	public String toString() {
		String output = "ID: " + id + " | Complete?: " + status;
		return output;
	}



	// Get/set
	public int getReqID() {
		return id;
	}

	public boolean getStatus() {
		return status;
	}

	public String getCourseID() {
		return courseID;
	}

	public int getTeachNo() {
		return teachNo;
	}

	public ArrayList <String> getTrainingRequired() {
		return trainingRequired;
	}

	public ArrayList <PTTeacher> getAssigned() {
		return assigned;
	}

}
