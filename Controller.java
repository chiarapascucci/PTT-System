import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	private DataHandler currentHandler = null;
	private UserInterface view = null;
	
	public Controller (DataHandler d, UserInterface v) {
		currentHandler = d;
		view = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
