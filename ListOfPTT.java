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
}
