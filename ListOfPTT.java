import java.util.ArrayList;

public class ListOfPTT {
	private ArrayList <PTTeacher> list;
	
	//constructor
	public ListOfPTT () {
		list = new ArrayList <PTTeacher>();
	}
	
		
	protected void addTeacher(PTTeacher t) {
		list.add(t);
	}
	
	public String[] printList() {
		String [] s = (String[]) list.toArray();
		return s;
	}
	
	public ArrayList<PTTeacher> getListReference() {
		return list;
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
	
	// Adding a getPTTeacherReference function to find a teacher for a request
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
	
	public ArrayList <PTTeacher> findTeacher(String s, int i){
		ArrayList <PTTeacher> result = new ArrayList <PTTeacher>();
		
		if ( i == 0) {
			PTTeacher p = this.getTeacherRef(s);
			result.add(p);
		}
		
		else if (i == 1) {
			for (PTTeacher p : list) if (p.getSkills().contains(s)) result.add(p);
		}
		else if (i ==2) {
			for (PTTeacher p : list) if (p.getTraining().contains(s)) result.add(p);			
		}
		return result;
	}
	
	
	public PTTeacher getTeacherRef(String n) {
		for (PTTeacher t : list) {
			System.out.println(t.getfName() + " - " + n);
			if (t.getfName().trim().equals(n)) return t;
		}
		
		return null;
	}
}
