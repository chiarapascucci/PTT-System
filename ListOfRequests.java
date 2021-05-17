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
	
	public void add(TeachRequest R) {
		reqList.add(R);
	}
}
