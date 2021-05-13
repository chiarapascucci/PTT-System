import java.util.ArrayList;

public class TestPTT {
	public static void main(String[] args) {
		PTTeacher bob = new PTTeacher("Bob", "Bobson");
		PTTeacher alice = new PTTeacher ("Alice", "Doe");
		PTTeacher ken = new PTTeacher ("Ken", "Kan");
		
		//TEST 1
		//System.out.println(bob);
		//System.out.println(alice +"\n" +ken);
		
		//TEST 2
		ListOfPTT myList = bob.getList();
		myList.printList();
		
	}

}
