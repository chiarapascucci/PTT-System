import java.util.ArrayList;

public class ListOfRequests {
	private ArrayList <TeachRequest> reqList;

	public ListOfRequests() {
		reqList = new ArrayList <TeachRequest> ();
	}

	public void printReqList() {
		for(TeachRequest t: reqList) System.out.println(t);
	}
}
