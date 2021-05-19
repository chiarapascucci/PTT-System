
public abstract class AbstractDataHandlerFactory {

	/* - Defines methods which must be implemented in child/sub classes. 
	   - These child classes can be passed to the controller without any change to the controller implmentation
	   - In this case, the benefit of this is that a FileDataHandler (for this app) and a DatabaseDataHandler can be
	     defined and passed to the controller without any change to the controller. 
	*/
	
	// Load PTTeacher and Teach request entities and populate Lists of these entities 
	public abstract void loadData(String filepathAndName);
	public abstract void saveData(String filepathAndName);
	
	// Return a reference to these lists for ACCESS and OPERATIONS over data. 
	public abstract ListOfPTT 		getLOP(); 
	public abstract ListOfRequests 	getLOR();
	
}
