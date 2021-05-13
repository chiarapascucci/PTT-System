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
}
