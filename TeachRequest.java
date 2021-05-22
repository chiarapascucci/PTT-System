import java.util.ArrayList;

public class TeachRequest {
	private int id;
	private static int nextID = 0;
	private boolean status;

	private String courseID;
	private int teachNo;
	private ArrayList <String> trainingRequired;
	private ArrayList <PTTeacher> assigned;
	
	public TeachRequest(String c, int no, ListOfRequests LOR, String[] t) {
		this.id = nextID;
		nextID++;
		requestSetup(c,no,LOR,t);
	}

	// Alt. constructor sets a specific request ID
	public TeachRequest(int i, String c, int no, ListOfRequests LOR, String[] t) {
		this.id = i;
		requestSetup(c,no,LOR,t);
		nextID = LOR.maxRID() + 1;	
	}

	// Helper for common constructor content
	private void requestSetup(String c, int no,ListOfRequests LOR, String[] t) {
		this.courseID = c;
		this.teachNo = no;
		this.assigned = new ArrayList <PTTeacher> ();

		// Add training(s)
		this.trainingRequired = new ArrayList <String> ();
		for(int i = 0; i < t.length; i++) {
			this.trainingRequired.add(t[i]);
		}
		
		// Set status
		this.statusCheck();
		
		// Add request to list 
		LOR.getListReference().add(this);
	}

	/*	 >>> Status management methods			*/

	public boolean addTeacher(PTTeacher target) {
		
		//Get teacher's combined training & skills
		ArrayList <String> allTS = new ArrayList <String> ();
		allTS.addAll(target.getTraining()); allTS.addAll(target.getSkills());
		
		// Teacher already assigned to request
		if(this.assigned.contains(target)) {
			return false;
		} 
		// Required teachers quantity already met
		else if (this.assigned.size() >= teachNo) {
			return false;
		}
		// Teacher does not have *all* the required training in their skills&training
		else if(!(allTS.containsAll(this.trainingRequired))) {
			return false;
		}
		else {
			this.assigned.add(target);
			target.assignTeacher(this); 
			this.statusCheck();	
			return true;
		}
	}

	public boolean removeTeacher(PTTeacher target) {
		if(!(this.assigned.contains(target))) {		
			return false;							
		} else {									
			this.assigned.remove(target);
			this.statusCheck();	// status needs updating, teachNo needs updating
			return true;			
		}
	}

	// Set status according to fulfilled requirements
	public void statusCheck() {

		boolean passCheck = true;
		ArrayList <PTTeacher> invalidTeachers = new ArrayList <PTTeacher> ();

		// Check for invalid teacher assignments to this request
		for(PTTeacher t: this.assigned) {
			ArrayList <String> allTS = new ArrayList <String> ();
			allTS.addAll(t.getTraining()); allTS.addAll(t.getSkills());

			// If teacher's skills&training do not match required training:
			if(!(allTS.containsAll(this.trainingRequired))) {
				invalidTeachers.add(t);
			}
		}
		
		// Remove the invalid teachers from this request. New loop to avoid Comodification.
		for(PTTeacher p: invalidTeachers) {
			this.assigned.remove(p);
		}		

		// Check quantity of valid teachers
		if(this.assigned.size() < this.teachNo) {
			passCheck = false;
		} 

		// Set status
		if(passCheck) { this.status = true; }
		else { this.status = false; }
	}

	
	
	
	
	/*	 >>> Print methods			*/
	
	public String printTrainingRequired() {
		String s = "";
		if(!(this.trainingRequired.isEmpty())) {
			for(String i: this.trainingRequired) {	s = s + "\n- " + i	;}
		}
		
		return s;
	}

	public String printTeachers() {
		String s = "";
		if(!(this.assigned.isEmpty())) {
			for(PTTeacher t: this.assigned) {	s = " " + t;	}
		}
		
		return s;
	}

	// Update this with something more useful if necessary
	public String toString() {
		String output = "ID: " + id + "\nComplete: " + status + "\nSkills: " + this.printTrainingRequired();
		return output;
	}



	
	
	/*	 >>> Getter/setters			*/
	
	public int getReqID() {
		return id;
	}

	public boolean getStatus() {
		return status;
	}
	
	protected void setStatus(boolean status) {
		this.status = status;
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
