package ModelViewController;
public class InputGuard {

	// Attributes
	String ensureIntegerMsg; 
	String ensureStringNotEmptyMsg; 
	
	// Constructor 
	public InputGuard() {
		ensureIntegerMsg 			= "Please enter a integer value in input field";
		ensureStringNotEmptyMsg 	= "Please enter a String value in input field."; 
	}
	
	
	// InputGuard methods (guards) 
	
	// Ensure integer is inputed 
	public boolean ensureInteger(String input) {
		
		try {
			Integer.parseInt(input);
		}
		catch (NumberFormatException e) {
			return false; 
		}
		return true; 
	}
	
	// Ensure the object created is not passed to the next method if it is null (i.e. if it hasnt fulfilled a criteria at all) 
	public boolean ensureNotNullReference(Object o) {
		if (o == null) {
			return false; 
		}
		return true; 
	}
	
	// Ensure the input string is not empty (i.e. a course name input should never be empty) 
	public boolean ensureSuitableString(String s) {
		
		// Ensure the String is not empty 
		if (s.isEmpty()) {
			return false; 
		}
		
		// Can add other checks for Strings here 
		
		// return true if all satisfied 
		return true; 
	}
}
