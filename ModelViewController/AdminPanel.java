package ModelViewController;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class AdminPanel extends JPanel {
	private Controller c;
	//main buttons
	protected JButton viewReqs,backButton, viewPTT;  //buttons  for viewing requests, returning to previous page, and viewing teacher list
	private JLabel search, assignTeach, updateTeach;
	//main panels
	protected JPanel panelR, panelC, panelL; //right, centre and left panels
	protected JTextArea textArea;//text area for displaying results
	protected JScrollPane scroll;
	//assign teacher elements
	protected JPanel assignF;//assign function panel
	protected JButton assign; //button to assign teacher to request
	protected JTextField teachName, requestNo; 
	
	//search by reqs elements
	protected JPanel searchF; //panel for search functions
	protected JComboBox <String> optionList; //list for selecting requirements
	protected JTextField searchChoiceOne, searchChoiceTwo, searchChoiceThree;  
	protected JButton searchButton; //button for searching
	private String[] options = {"Name","Skills", "Training" }; //list of requirement choices
	
	//Update teacher information elements
	protected JPanel updateF; //update teacher panel
	protected JTextField teachID,choice; 
	private String[] optionsUpdate= {"Skills", "Training" };
	protected JComboBox <String> optionListUpdate; //options for updating teacher data
	protected JButton addSkill, remSkill,compTraining; //button to add or remove skill/training and button to mark training as complete
	
	//add teacher to system
	protected JPanel addTeacherF; //add new teacher panel
	protected JTextField addFName, addLName, addTSkills;
	protected JButton addT; //button to add teacher

	private JPanel titlePanelOne, titlePanelTwo, titlePanelThree, titlePanelFour;

	//constructor
	public AdminPanel(Controller c) {
		this.c = c;
		
		GridLayout g = new GridLayout(1,3); //layout left admin panel
		this.setLayout(g);
		
		this.panelL = new JPanel(); 
		
		searchF = generateSearchF();
	    	
       	backButton = new JButton("Back"); // button to return to previous page
       	backButton.setToolTipText("Return to previous page");
        backButton.addActionListener(c);
        
        viewReqs = new JButton ("View Requests");//button to view existing requests
        viewReqs.setToolTipText("View Requests");
        viewReqs.addActionListener(c);
        
		viewPTT = new JButton ("View Teachers");// button to view teacher list
		viewPTT.setToolTipText("View teachers");
		viewPTT.addActionListener(c);

		//format components
		panelL.setLayout(new GridLayout(3,1)); //formatting above three buttons and adding to left panel
		panelL.add(viewPTT);
		panelL.add(viewReqs);
		panelL.add(backButton);

		this.add(panelL);

		GridLayout l = new GridLayout(5,2); //formatting centre and right panels and adding text area for results display
		panelC = new JPanel();
		panelR = new JPanel();
		textArea = new JTextArea(20,20);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		textArea.setEditable(false); 
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		//creating function specific panels for admin
		assignF = generateAssignF(); //create assign panel
		assignF.setVisible(true);

		addTeacherF = generateAddTeacherF();//create add teacher panel

		searchF.setVisible(true);


		updateF = generateUpdateF();//create update teacher panel
		updateF.setVisible(true);

	       //create title panels		
	       titlePanelOne = new JPanel();
	       titlePanelTwo = new JPanel();
	       titlePanelThree = new JPanel();
	       titlePanelFour = new JPanel();
	       //create title panels
	       JLabel tOne = new JLabel("SEARCH TEACHER");
	       JLabel tTwo = new JLabel("ASSIGN TEACHER");
	       JLabel tThree = new JLabel ("UPDATE TEACHER");
	       JLabel tFour = new JLabel ("ADD TEACHER");
	       //add titles to panels
	       titlePanelOne.add(tOne);
	       titlePanelTwo.add(tTwo);
	       titlePanelThree.add(tThree);
	       titlePanelFour.add(tFour);

		//format centre panel	
		panelC.setLayout(l);
		panelC.add(titlePanelOne);
		panelC.add(searchF);
		panelC.add(titlePanelTwo);
		panelC.add(assignF);
		panelC.add(titlePanelThree);
		panelC.add(updateF);
		panelC.add(titlePanelFour);
		panelC.add(addTeacherF);
		// panelC.add(textArea);
		
		panelR.add(scroll);
		//panelR.add(textArea);
		
		this.add(panelC); this.add(panelR);
	}
	
	protected JPanel generateAssignF() {
		JPanel p = new JPanel();
		JLabel teacher= new JLabel("Enter teacher ID");
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

	private JPanel generateUpdateF() {
		JPanel updateTeach= new JPanel();


		JLabel teacher= new JLabel("Enter Teacher ID");
		teachID= new JTextField(25); //allows to enter teacher name

		JLabel optionUp = new JLabel("Select Skill/Training");

		optionListUpdate = new JComboBox(optionsUpdate);

		JLabel skillTrain= new JLabel("Enter skill/training to add/remove");
		choice= new JTextField(25); //allows to enter skill/training to add/remove

		addSkill = new JButton("Add"); //creates a button that adds skill/training
		addSkill.setToolTipText("Add skill/training");
		addSkill.addActionListener(c);

		remSkill = new JButton("Remove"); //creates a button that removes skill/training
		remSkill.setToolTipText("Remove skill/training");
		remSkill.addActionListener(c);
		
		compTraining = new JButton("Complete");
		compTraining.addActionListener(c);
		

		//adds all components to panel and formats them
		updateTeach.setLayout(new GridLayout(5,2));
		updateTeach.add(teacher);
		updateTeach.add(teachID);
		updateTeach.add(optionUp);
		updateTeach.add(optionListUpdate);
		updateTeach.add(skillTrain);
		updateTeach.add(choice);
		updateTeach.add(addSkill);
		updateTeach.add(remSkill);
		updateTeach.add(compTraining);

		return updateTeach;
	}
	
	private JPanel generateSearchF() {
		JPanel searchMain = new JPanel();
		
		optionList= new JComboBox(options);// creates new option box for search criteria
	        JLabel selectOption= new JLabel("Select Search Type");

	        JLabel searchOne= new JLabel("Enter first search requirement");
	        searchChoiceOne= new JTextField(25); //allows to enter search requirements
	        
	        searchButton = new JButton("Search"); //search button creation
	        searchButton.setToolTipText("Search on Requirement");
	        searchButton.addActionListener(c);

	        //sets layout and adds all components to search JPanel
	        searchMain.setLayout(new GridLayout(5,2));
	        searchMain.add(selectOption);
	        searchMain.add(optionList);
	        searchMain.add(searchOne);
	        searchMain.add(searchChoiceOne);
	        searchMain.add(searchButton);
	      
		return searchMain;
	}
	
	public JPanel generateAddTeacherF() {
		JPanel j = new JPanel();
			
		GridLayout g = new GridLayout(3,2); //creates layout for new teacher panel
		
		j.setLayout(g);
		//adds fields to input new teacher information
		JLabel fName = new JLabel ("Add first name");
		addFName = new JTextField();
		JLabel lName = new JLabel("Add second name");
		addLName = new JTextField();
		addT = new JButton("Create record");
		addT.addActionListener(c);
		//adds components to panel
		j.add(fName);
		j.add(addFName);
		j.add(lName);
		j.add(addLName);
		j.add(addT);
		
		return j;
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
