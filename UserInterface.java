import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UserInterface extends JFrame {
   
	private 	JPanel 			mainPanel;
    private 	Controller 		controller;
    protected	JButton 		exitButton, adminButton, courseButton;
    protected 	AdminPanel 		adminMain;
    protected 	CDPanel 		cDPanel;
    private 	BorderLayout 	b;
    
    //protected JButton backButton, viewReqs, assignTeach, search, updateTeach;
    
    //initialises UI
    public UserInterface(Controller controller)  {
    	
        //creates start window and formats main JFrame
        setTitle("Requesto (powered by PoweRon)");
        setSize(1000, 500);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        b = new BorderLayout();
        this.setLayout(b);
        this.setVisible(true);
        

        // Set  controller reference
        this.controller = controller;
       
        mainPanel = new JPanel();
        
        exitButton = new JButton("Exit");
        exitButton.setToolTipText("Exits Program");
        exitButton.addActionListener(controller);
        //button that takes you to main Admin page
        adminButton = new JButton("Admin");
        adminButton.setToolTipText("Admin Page");
        adminButton.addActionListener(controller);
        //button that takes you to main Course Director page
        courseButton = new JButton("Course Director");
        courseButton.setToolTipText("Course Director Page");
        courseButton.addActionListener(controller);

        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(adminButton);
        mainPanel.add(courseButton);
        mainPanel.add(exitButton);
        
        this.add(mainPanel,b.CENTER);
        
        cDPanel = new CDPanel(controller);
        adminMain = new AdminPanel(controller);
        
    }
    
    public void setController(Controller c) {
    	this.controller =c;
    }
    
    protected void updateView(JPanel j) {
    	mainPanel.setVisible(false);
    	this.add(j, b.CENTER);
    	//return this;
    }
    
    protected void backToMain(JPanel j) {
    	j.setVisible(false);
    	mainPanel.setVisible(true);
    	this.add(mainPanel);
    }
    
    public JPanel getAdminMain() {
    	return adminMain;
    }

    public JPanel getCDPanel() {  
    	return cDPanel;
    }
    
    public JPanel getMainPanel() {
    	return mainPanel;
    }

    //course director functions
    private void submitRequest(){
        //create new screen for search by requirements
        setTitle("Submit Request");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel subReq= new JPanel();

        var backButton = new JButton("Back"); //creates a back button that returns to course director main page
        backButton.setToolTipText("Return to course director page");
        //backButton.addActionListener((event) -> cdMain());

        var submitReq = new JButton("Submit Request"); //creates a back button that returns to course director main page
        submitReq.setToolTipText("Return to course director page");
       // submitReq.addActionListener((event) -> cdMain());

        JLabel teacher= new JLabel("Enter teacher number");
        JTextField teachNo= new JTextField(25); //allows to enter teacher number
        JLabel skill1= new JLabel("First Requirement");
        JTextField skillOne= new JTextField(25); //allows to enter search requirements
        JLabel skill2= new JLabel("Second Requirement");
        JTextField skillTwo= new JTextField(25);
        JLabel skill3= new JLabel("Third Requirement");
        JTextField skillThree= new JTextField(25);

        subReq.setLayout(new GridLayout(5,2));

        subReq.add(teacher);
        subReq.add(teachNo);
        subReq.add(skill1);
        subReq.add(skillOne);
        subReq.add(skill2);
        subReq.add(skillTwo);
        subReq.add(skill3);
        subReq.add(skillThree);
        subReq.add(submitReq);
        subReq.add(backButton);

        //contentPane.remove(0);//clear JFrame
        //contentPane.add(subReq);//add new JPanel

    }

}