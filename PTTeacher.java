//PTT teach class
//committ test

public class PTTeacher {
	
	//class variables
	private String fName;
	private String lName;
	private int year;
	private int tID;
	private static int nextTID;
	private String[] skills;
	private String[] training;
	//private ArrayList <Request>;
	//private static ListofPTT list; -- invariant may be that if a teach exists then it must belong to a list
	
	//constructor
	public PTTeacher (String f, String l, int y ) {
		this.fName = f;
		this.lName = l;
		this.year = y;
		this.tID = nextTID;
		nextTID ++;
		
		//initialise request list
		
		this.skills = new String[10];
		this.training = new String[10];
		
		listSetUp();
		
	}
	
	//method to manage teacher - list interaction
	//if instance is first teacher being created then list is created with it, if not the teacher is just added to the list
	//one variant may be that if there is a teacher, then a list must exist to contain the teacher
	private void listSetUp() {
		if (this.tID == 0) {
			list = new ListofPTT();
			list.add(this);
		}
		
		else {
			list.add(this);
		}
	}
	
	
}