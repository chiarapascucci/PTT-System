
public class Testbench {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		DataHandler.setRef();
//		
//		PTTeacher a = new PTTeacher("bob", "bobb", DataHandler.getLOP());
//		PTTeacher b = new PTTeacher("dob", "dobb", DataHandler.getLOP());
//		PTTeacher c = new PTTeacher("fob", "fobb", DataHandler.getLOP());
//	
//		DataHandler.saveData("D:\\");
		
		DataHandler.loadData("D:\\");
		
		DataHandler.getLOP().printList();
		
		
		
	}

}
