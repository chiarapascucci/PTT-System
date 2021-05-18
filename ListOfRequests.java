import java.util.ArrayList;

public class ListOfRequests {
	private ArrayList <TeachRequest> reqList;

	public ListOfRequests() {
		reqList = new ArrayList <TeachRequest> ();
	}

	public void printReqList() {
		for(TeachRequest t: reqList) System.out.println(t);
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
	
	
}
