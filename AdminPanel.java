import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminPanel extends JPanel {
	private Controller c;
	protected JButton backButton, search, viewReqs, assignTeach, updateTeach;
	protected JPanel panelR, panelC, panelL;
	protected JPanel assignF;
	private JButton assign;
	private JTextField teachName, requestNo;
	public AdminPanel(Controller c) {
		this.c = c;
		
		GridLayout g = new GridLayout(1,3);
		this.setLayout(g);
		
		this.panelL = new JPanel();
		
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
        panelL.setLayout(new GridLayout(5,1));
        panelL.add(search);
        panelL.add(viewReqs);
        panelL.add(assignTeach);
        panelL.add(updateTeach);
        panelL.add(backButton);
        
        this.add(panelR);
        
        panelC = new JPanel();
        panelR = new JPanel();
        JTextArea textArea = new JTextArea(50, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600,350));
        textArea.setEditable(false);
        textArea.setText("Select Action");
        
        panelC.add(textArea);
        panelR.add(scrollPane);
        
        this.add(panelC); this.add(panelR);
        
        
        //creting function specific panels for admin
        assignF = generateAssignF();
	}
	
	protected JPanel generateAssignF() {
		JPanel p = new JPanel();
		JLabel teacher= new JLabel("Enter teacher name");
        teachName= new JTextField(25); //allows to enter teacher name
        JLabel request= new JLabel("Enter request number");
        requestNo= new JTextField(25); //allows to enter request number

        assign= new JButton("Assign"); //button to assign teacher to request
        assign.setToolTipText("Assign Teacher to Request");
        assign.addActionListener(c);

        //adds all components to JPanel assignTeach
        p.setLayout(new GridLayout(6,1));
        p.add(teacher);
        p.add(teachName);
        p.add(request);
        p.add(requestNo);
        p.add(assign);
        
		return p;
	}

	public JPanel getPanelR() {
		return panelR;
	}

	public void setPanelR(JPanel panelR) {
		this.panelR = panelR;
	}

	public JPanel getPanelC() {
		return panelC;
	}

	public void setPanelC(JPanel panelC) {
		this.panelC = panelC;
	}
	
	
}
