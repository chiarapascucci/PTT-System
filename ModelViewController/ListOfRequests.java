package ModelViewController;
import java.util.ArrayList;

public class ListOfRequests {
	private ArrayList <TeachRequest> reqList;

	public ListOfRequests() {
		reqList = new ArrayList <TeachRequest> ();
	}

	public String[] printReqListStatus() {
		String [] s = new String [reqList.size()];
		for (int i = 0; i<s.length; i++) {
			String t = "Request ID: "+ reqList.get(i).getReqID() + " - " + reqList.get(i).getStatus() + "\n" ;
			s[i] = t;
		}
		return s;
	}
	
	public String[] printReqList() {
		String [] s = new String [reqList.size()];
		for (int i = 0; i<s.length; i++) {
			String t = "Request ID: "+ reqList.get(i).getReqID() +"\n"+ "Course ID: "+ reqList.get(i).getCourseID() +"\n"+ "no. of teachers required: " + reqList.get(i).getTeachNo() +"\n"+"Request status: "+ reqList.get(i).getStatus() +"\n"+"Skills required: " + reqList.get(i).printTrainingRequired() +"\n" ;
			s[i] = t;
		}
		
		return s;
	}
	

	
	
	public  ArrayList <TeachRequest> getListReference() {
		return reqList; 
	}
	
	
	// Adding to ensure nextTID is always the highest
	protected int maxRID() {
		
		int maxRID = 0;	// Initialise maxID
		
		for (int i = 0; i < reqList.size(); i++) {		// Iterate over list
			if (reqList.get(i).getReqID() > maxRID) {	// Compare maxID with current ID
				maxRID = reqList.get(i).getReqID(); 	// If maxID < currentID -> maxID = currentID
			}
		}
		return maxRID; 
	}
	
	public TeachRequest findReq(int i) {
		for (TeachRequest t : reqList) {
			if (t.getReqID() == i) return t;
		
		}
		
		return null;
	}
	
}
