import java.util.ArrayList;

public class ListOfPTT {
	private ArrayList <PTTeacher> list;
	
	//constructor
	public ListOfPTT () {
		list = new ArrayList <PTTeacher>();
	}
	
	//not sure if we need more methods given that ArrayList has already methods for add, remove, find etc.
	
	protected void addTeacher(PTTeacher t) {
		list.add(t);
	}
	
	public void printList() {
		for (PTTeacher p : list) System.out.println(p);
		
	}
	
	public ArrayList<PTTeacher> getListReference() {
		return list;
	}
}
