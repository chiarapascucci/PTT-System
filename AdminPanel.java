import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminPanel extends JPanel {
	private Controller c;
	//main buttons
	protected JButton backButton, search, viewReqs, assignTeach, updateTeach;
	//main panels
	protected JPanel panelR, panelC, panelL; //right, centre, left hand panels
	
	//assign teacher elements
	protected JPanel assignF; //panel for assignment functions
	private JButton assign;
	private JTextField teachName, requestNo;
	
	//search by reqs elements
	protected JPanel searchF; //panel for search functions
	private JComboBox optionList;
	private JTextField searchChoiceOne, searchChoiceTwo, searchChoiceThree;
	private JButton searchButton;
	private String[] options = {"Name","Skills", "Training" };
	
	//update teacher elements
	protected JPanel updateF; //panel for update functions
	private JTextField teachNameS;
	private String[] optionsUpdate= {"Skills", "Training" };
	private JComboBox optionListUpdate;
	private	JButton addSkill, remSkill;
	
	//constructor
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
        
        	this.add(panelL);//adds to left hand panel of view
        
        	GridLayout l = new GridLayout(4,1);
        	panelC = new JPanel();//creates centre panel
        	panelR = new JPanel();//creates right hand panel
        	JTextArea textArea = new JTextArea(50, 50); //text area for displaying results
        	JScrollPane scrollPane = new JScrollPane(textArea);//make text area scrollable
        	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        	scrollPane.setPreferredSize(new Dimension(600,350));
        	textArea.setEditable(false);//make sure it is display only
        	textArea.setText("Select Action");
		
        	//creting function specific panels for admin
        	assignF = generateAssignF();
        	assignF.setVisible(true);
        	assignF.setEnabled(false);
        
        	searchF = generateSearchF();
        	searchF.setVisible(true);
        	searchF.setEnabled(false);
       
        
        	panelC.setLayout(l);// sets centre panel to be in centre
        	//adds panels to desired orientation panels
		panelC.add(assignF);
        	panelC.add(searchF);
        	panelC.add(textArea);
        	panelR.add(scrollPane);
       
        	this.add(panelC); this.add(panelR);// adds centre and right panel to main window
	}
	
	protected JPanel generateAssignF() {//creates functions for assignment
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
	
	private JPanel generateUpdateF() { //creates update teacher functions
		JPanel updateTeach= new JPanel();
		
        	JLabel teacher= new JLabel("Enter Teacher Name");
        	teachName= new JTextField(25); //allows to enter teacher name

        	JLabel option = new JLabel("Select Skill/Training");
        
        	optionListUpdate = new JComboBox(optionsUpdate);

        	JLabel skillTrain= new JLabel("Enter skill/training to add/remove");
        	JTextField choice= new JTextField(25); //allows to enter skill/training to add/remove

        	addSkill = new JButton("Add"); //creates a button that adds skill/training
        	addSkill.setToolTipText("Add skill/training");
        	addSkill.addActionListener(c);

        	remSkill = new JButton("Remove"); //creates a button that removes skill/training
        	remSkill.setToolTipText("Remove skill/training");
        	remSkill.addActionListener(c);

        	//adds all components to panel and formats them
        	updateTeach.setLayout(new GridLayout(5,2));
        	updateTeach.add(teacher);
        	updateTeach.add(teachName);
        	updateTeach.add(option);
		updateTeach.add(optionList);
		updateTeach.add(skillTrain);
		updateTeach.add(choice);
		updateTeach.add(addSkill);
		updateTeach.add(remSkill);
		
		return updateTeach;
	}
	
	private JPanel generateSearchF() {// creates search by requirement functions
		JPanel searchMain = new JPanel();
		
		optionList= new JComboBox(options);
	        JLabel selectOption= new JLabel("Select Search Type");

	        JLabel searchOne= new JLabel("Enter first search requirement");
	        searchChoiceOne= new JTextField(25); //allows to enter search requirements
	        JLabel searchTwo= new JLabel("Enter second search requirement");
	        searchChoiceTwo= new JTextField(25);
	        JLabel searchThree= new JLabel("Enter third search requirement");
	        searchChoiceThree= new JTextField(25);

	        searchButton = new JButton("Search"); //search button creation
	        searchButton.setToolTipText("Search on Requirement");
	        searchButton.addActionListener(c);

	        //sets layout and adds all components to search JPanel
	        searchMain.setLayout(new GridLayout(5,2));
	        searchMain.add(selectOption);
	        searchMain.add(optionList);
	        searchMain.add(searchOne);
	        searchMain.add(searchChoiceOne);
	        searchMain.add(searchTwo);
	        searchMain.add(searchChoiceTwo);
	        searchMain.add(searchThree);
	        searchMain.add(searchChoiceThree);
	        searchMain.add(searchButton);
	      
		return searchMain;// returns main search panel
	}

	
	//getters and setters
	
	public JComboBox getOptionList() {
		return optionList;
	}

	public void setOptionList(JComboBox optionList) {
		this.optionList = optionList;
	}

	public JPanel getAssignF() {
		return assignF;
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
	
	public void updatePanels(JPanel a) {
		this.remove(panelC);
		this.add(a);
	}
}
