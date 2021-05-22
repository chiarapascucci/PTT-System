import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CDPanel extends JPanel {
	private Controller c;
	protected JButton backButton, submitReq, statCheck; //main buttons for course director page
	protected JPanel lPanel, cPanel, rPanel; //left, centre, right
	protected JTextField courseName, noReq, skills; 
	private JLabel cName, nRq, rSkills;
	protected JTextArea displayField;
	protected JScrollPanel scroll;
	
	public CDPanel(Controller c) {
		this.c = c;
		//format new panel for course director functions
		GridLayout g = new GridLayout(1,3);
		this.setLayout(g);
		
		lPanel = new JPanel();
		cPanel = new JPanel();
		rPanel = new JPanel();
		
		//button to search by requirements page
		backButton = new JButton("Back");
		backButton.setToolTipText("Return to previous page");
		backButton.addActionListener(c);

		//button to page to check request status
		statCheck = new JButton("Check Request Status");
		statCheck.setToolTipText("View Request's status");
		statCheck.addActionListener(c);


		//format components
		lPanel.setLayout(new GridLayout(3,1));
		cPanel.setLayout(new GridLayout(4,2));

		lPanel.add(statCheck);
		lPanel.add(backButton);

		courseName = new JTextField();
		cName = new JLabel("Enter the course name");
		noReq = new JTextField();
		nRq = new JLabel("Enter number of PT teachers required");
		skills = new JTextField ();
		rSkills = new JLabel ("Enter the skills required, separated by ','");


	      //button to submit request page
		submitReq = new JButton("Submit Request");
		submitReq.setToolTipText("Submit Request");
		submitReq.addActionListener(c);

		//add components to panel
		cPanel.add(cName);
		cPanel.add(courseName);
		cPanel.add(nRq);
		cPanel.add(noReq);
		cPanel.add(submitReq);
		cPanel.add(rSkills);
		cPanel.add(skills);      
		cPanel.add(submitReq);


		//create a display for results and add to panel
		displayField = new JTextArea(40,20);
		displayField.setText("Select Action");
        	scroll = new JScrollPane(displayField);
        	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		rPanel.add(displayField);
		
		this.add(lPanel);
		this.add(cPanel);
		this.add(rPanel);
	}
}
