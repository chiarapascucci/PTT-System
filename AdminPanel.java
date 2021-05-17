import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AdminPanel extends JPanel {
	private Controller c;
	protected JButton backButton, search, viewReqs, assignTeach, updateTeach;
	private JPanel panel;
	
	public AdminPanel(Controller c) {
		this.c = c;
		
		GridLayout g = new GridLayout(1,2);
		this.setLayout(g);
		
		this.panel = new JPanel();
		
	    //button to return to home page
        backButton = new JButton("Back");
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener(c);

        //button to search by requirements page
        search = new JButton("Search By Requirements");
        search.setToolTipText("Search by requirements");
        search.addActionListener(c);

        //button to go to view request page
        viewReqs = new JButton("View Requests");
        viewReqs.setToolTipText("View Requests");
        viewReqs.addActionListener(c);

        //button to go to page to assign teachers to request
        assignTeach = new JButton("Assign Teacher");
        assignTeach.setToolTipText("Assign Teacher");
        assignTeach.addActionListener(c);

        //button to go to page to update teacher's info
        updateTeach = new JButton("Update Teacher Information");
        updateTeach.setToolTipText("UpdateTeacher Information");
        updateTeach.addActionListener(c);

        //format components
        panel.setLayout(new GridLayout(5,1));
        panel.add(search);
        panel.add(viewReqs);
        panel.add(assignTeach);
        panel.add(updateTeach);
        panel.add(backButton);
        
        this.add(panel);
        
        JTextArea displayField = new JTextArea();
        
        this.add(displayField);
	}
}
