import java.util.ArrayList;

public class TeachRequest {
	private int reqID;
	private static int nextRID = 0;
	private boolean status;

	private String courseID;
	private int teachNo;
	private String trainingRequired;
	private ArrayList <PTTeacher> assigned;

	// Pass: associated course, required no. of staff, required training
	public TeachRequest(String c, int no, String t, ListOfRequests LOR) {
		reqID = nextRID;
		nextRID++;

		status = false;
		courseID = c;
		teachNo = no;
		trainingRequired = t;
		assigned = new ArrayList <PTTeacher> (no);
		
		LOR.add(this); 
		statusCheck();
	}

	public void assignTeacher(PTTeacher target) {
		assigned.add(target);
		statusCheck();
	}

	public void removeTeacher(PTTeacher target) {
		assigned.remove(target);
		statusCheck();
	}

	// Check there is 1) enough teachers 2) all received training
	public void statusCheck() {

		boolean passCheck = true;

		// Teacher quantity check
		if(assigned.size() < teachNo){
			status = false;
			passCheck = false;
		} 

		// Training check
		else {
			for(PTTeacher t: assigned) {
				boolean match = false;
				String[] trainList = t.getTraining();
				
				for(int i = 0; i < trainList.length; i++) {
					if(trainingRequired.equals(trainList[i])) {
						match = true;
						break;
					}
				}
				if(!match) {
					status = false;
					passCheck = false;
					break;
				}
			}
		}

		if(passCheck) { status = true; }
	}

	// print()
	// Save + destroy object

	public String toString() {
		String line = reqID + " | " + teachNo + ", " + trainingRequired;
		return line;
	}

	// Get/set
	public int getReqID() {
		return reqID;
	}

	public boolean getStatus() {
		return status;
	}

	/* -- Manually set status; not needed if status is auto-updated? */
	// public void setStatus(boolean s) {
	// 	status = s;
	// }

	public String getCourseID() {
		return courseID;
	}

	public int getTeachNo() {
		return teachNo;
	}

	public String getTrainingRequired() {
		return trainingRequired;
	}
	
	public ArrayList <PTTeacher> getAssigned(){
		return assigned; 
	}

}
