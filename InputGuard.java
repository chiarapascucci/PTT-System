
public class InputGuard {

	// Attributes
	String ensureIntegerMsg; 
	
	// Constructor 
	public InputGuard() {
		ensureIntegerMsg = "Please enter a integer value in input field";
	}
	
	
	// InputGuard methods (guards) 
	
	public boolean ensureInteger(String input) {
		
		try {
			Integer.parseInt(input);
		}
		catch (NumberFormatException e) {
			return false; 
		}
		return true; 
	}
	
	public boolean ensureNotNullReference(Object o) {
		if (o == null) {
			return false; 
		}
		return true; 
	}
}
