import java.util.ArrayList;

public class TeachRequest {
	private int id;
	private static int nextID = 0;
	private boolean status;

	private String courseID;
	private int teachNo;
	private ArrayList <String> trainingRequired;
	private ArrayList <PTTeacher> assigned;

	public TeachRequest(String c, int no, ListOfRequests LOR, String... t) {
		this.id = nextID;
		this.nextID++;
		requestSetup(c,no,LOR,t);
	}

	// Alt. constructor sets a specific request ID
	public TeachRequest(int i, String c, int no, ListOfRequests LOR, String... t ) {
		this.id = i;
		requestSetup(c,no,LOR,t);
		this.nextID = LOR.maxRID() + 1;	
	}

	// Helper for common constructor content
	// Exceptions to capture: arraylist constructor
	private void requestSetup(String c, int no,ListOfRequests LOR, String... t) {
		this.courseID = c;
		this.teachNo = no;
		this.assigned = new ArrayList <PTTeacher> ();

		// Add request to list 
		LOR.getListReference().add(this);

		// Add training(s)
		this.trainingRequired = new ArrayList <String> ();
		for(String arg: t) {
			this.trainingRequired.add(arg);
		}
		
		// Set status
		this.statusCheck();
	}

	

	public boolean addTeacher(PTTeacher target) {
		if(this.assigned.contains(target)) {
			return false;
		} else {
			this.assigned.add(target);
			this.statusCheck();
			return true;
		}
	}

	public boolean removeTeacher(PTTeacher target) {
		if(!(this.assigned.contains(target))) {
			return false;
		} else {
			this.assigned.remove(target);
			this.statusCheck();
			return true;			
		}
	}

	// Update request fulfillment, may need to update to public
	private void statusCheck() {

		this.assigned.trimToSize();
		int invalidTeacher = 0;
		boolean passCheck = true;

		// Count teachers without required training ('invalid')
		// Missing check for null skill/training array(?)
		for(PTTeacher t: this.assigned) {
			ArrayList <String> allTS = new ArrayList <String> ();
			allTS.addAll(t.getTraining()); allTS.addAll(t.getSkills());

			if(!(allTS.containsAll(this.trainingRequired))) {
				invalidTeacher++;
			}
		}

		// Check quantity of valid teachers
		if((this.assigned.size() - invalidTeacher) < this.teachNo) {
			passCheck = false;
		} 

		// Set status
		if(passCheck) { this.status = true; }
		else { this.status = false; }
	}

	public String printTrainingRequired() {
		String s = "";
		if(!(this.trainingRequired.isEmpty())) {
			for(String i: this.trainingRequired) {	s = " " + i;	}
		}
		
		return s;
	}

	public void printTeachers() {
		if(!(this.assigned.isEmpty())) {
			for(PTTeacher t: this.assigned) {	System.out.println(t);	}
		}
	}

	// Update this with something more useful if necessary
	public String toString() {
		
		for (int i = 0; i <= trainingRequired.size(); i++) {
			
		}
		String output = "ID: " + id + " | Complete?: " + status + "skills: " + this.printTrainingRequired();
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
