import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UserInterface extends JFrame {
    protected JPanel mainPanel;
    private Controller controller;
    protected JButton exitButton, adminButton, courseButton;
    protected AdminPanel adminMain;
    protected CDPanel cDPanel;
    private BorderLayout b;
    
    //protected JButton backButton, viewReqs, assignTeach, search, updateTeach;

    //initialises UI
    public UserInterface()  {
    	
        //creates start window and formats main JFrame
        setTitle("Requesto (powered by PoweRon)");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1));
        
        //button that takes you to main Admin page
        adminButton = new JButton("Admin");
        adminButton.setToolTipText("Admin Page");
        adminButton.addActionListener(controller);
        //button that takes you to main Course Director page
        courseButton = new JButton("Course Director");
        courseButton.setToolTipText("Course Director Page");
        courseButton.addActionListener(controller);
      
        mainPanel.add(adminButton);
        mainPanel.add(courseButton);
        //mainPanel.add(exitButton);
        
        this.add(mainPanel,b.CENTER);
        
        controller = new Controller(this);
        
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
  



    private void updateTeacher() {//page for updating teacher information

        setTitle("Update Teacher Information");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel updateTeach= new JPanel();// new JPanel for update functions

        var backButton = new JButton("Back"); //creates a back button that returns to admin main page
        backButton.setToolTipText("Return to previous page");
        //backButton.addActionListener((event) -> adminMain());

        JLabel teacher= new JLabel("Enter Teacher Name");
        JTextField teachName= new JTextField(25); //allows to enter teacher name

        JLabel option= new JLabel("Select Skill/Training");
        String[] options= {"Skills", "Training" }; //creates a drop down menu of search criteria
        JComboBox optionList= new JComboBox(options);

        JLabel skillTrain= new JLabel("Enter skill/training to add/remove");
        JTextField choice= new JTextField(25); //allows to enter skill/training to add/remove

        var addSkill = new JButton("Add"); //creates a button that adds skill/training
        addSkill.setToolTipText("Add skill/training");
        addSkill.addActionListener((event) -> controller());

        var remSkill = new JButton("Remove"); //creates a button that removes skill/training
        remSkill.setToolTipText("Remove skill/training");
        remSkill.addActionListener((event) -> controller());

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
        updateTeach.add(backButton);

        //contentPane.remove(0);//clear JFrame
        //contentPane.add(updateTeach);//add new JPanel
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

        subReq.setLayout(new GridLayout(5,2));// format layout
        //add request submission components
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
    private void statusCheck(){
    	//reformat window for status check screen
    	 setTitle("Check Request Status");
         setSize(800, 400);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         JPanel statCheck= new JPanel();//new main panel for status check function

         var backButton = new JButton("Back"); //creates a back button that returns to course director main page
         backButton.setToolTipText("Return to course director page");
         //backButton.addActionListener((event) -> cdMain());
         
         JLabel req= new JLabel("Enter request number");
         JTextField reqNo= new JTextField(5); //allows to enter request number
         
         var check = new JButton("Check"); //creates a button that allows to check status
         check.setToolTipText("Check request status");
         check.addActionListener((event) -> controller());
         
         JTextArea textArea = new JTextArea(1, 10);// text area for displaying status
         textArea.setEditable(false); // only displays requests
         
         statCheck.setLayout(new GridLayout(3,2));//create page layout and add components
         statCheck.add(req);
         statCheck.add(reqNo);
         statCheck.add(check);
         statCheck.add(textArea);
         statCheck.add(backButton);
         

    }

    public static void main(String[] args) {//instantiate and call UI
        
    	UserInterface ui= new UserInterface();
        ui.setVisible(true);
    }
    
    private void controller() {
        //generic hold for controller
    }

}