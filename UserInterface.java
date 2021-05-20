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
    	j.setVisible(true);
    	this.add(j, b.CENTER);
    	
    }
    
    protected void backToMain(JPanel j) {
    	j.setVisible(false);
    	mainPanel.setVisible(true);
    	this.add(mainPanel);
    }
    
   

    //course director functions
    

}