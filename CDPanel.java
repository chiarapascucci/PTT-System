import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CDPanel extends JPanel {
	private Controller c;
	protected JButton backButton, submitReq, statCheck;
	private JPanel panel;
	
	public CDPanel(Controller c) {
		this.c = c;
		
		GridLayout g = new GridLayout(1,2);
		this.setLayout(g);
		
		this.panel = new JPanel();
		
        //button to search by requirements page
        backButton = new JButton("Back");
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener(c);

        //button to submit request page
        submitReq = new JButton("Submit Request");
        submitReq.setToolTipText("Submit Request");
        submitReq.addActionListener(c);

        //button to page to check request status
        statCheck = new JButton("Check Request Status");
        statCheck.setToolTipText("View Request's status");
        statCheck.addActionListener(c);
        //format components
        panel.setLayout(new GridLayout(5,1));
	//adds components to panel	
        panel.add(submitReq); 
        panel.add(statCheck);
        panel.add(backButton);
        
        this.add(panel);//adds cd panel to main window
        
        JTextArea displayField = new JTextArea(); //creates new text area for displaying results 
        
        this.add(displayField); //adds text area to main window
	}
}
