
public class Testbench {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		DataHandler.setRef();
////		
////		PTTeacher a = new PTTeacher("bob", "bobb", DataHandler.getLOP());
////		PTTeacher b = new PTTeacher("dob", "dobb", DataHandler.getLOP());
////		PTTeacher c = new PTTeacher("fob", "fobb", DataHandler.getLOP());
////	
////		TeachRequest a = new TeachRequest("d",1,"training1", DataHandler.getLOR());
////		TeachRequest b = new TeachRequest("b",2,"training2", DataHandler.getLOR());
////		TeachRequest c = new TeachRequest("c",3,"training3", DataHandler.getLOR());
////
////		DataHandler.saveData("D:\\");
////		
//		DataHandler.loadData("D:\\");
////		
//		DataHandler.getLOR().printReqList();
////	
		AbstractDataHandlerFactory data = DataHandler.getDataHandlerInstance(); 
		
		data.loadData("D:\\PTTAppData.txt");
		
		data.getLOP().toString();
//		
		
	}

}
