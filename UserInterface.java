import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UserInterface extends JFrame {
    private JPanel contentPane;

    //initialises UI
    public UserInterface()  {
        initGUI();
    }
    //Structures UI
    private void initGUI(){
        //creates start window and formats main JFrame
        setTitle("Requesto (powered by PoweRon)");
        setSize(800, 400);
        contentPane= new JPanel();//creates new main panel
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       mainButtons();//calls function adding home buttons to JFrame
       contentPane.setVisible(true);//set always visible
    }

    public void mainButtons(){
        JPanel home= new JPanel();// creates home page JPanel

        //creates button to close program
        var exitButton = new JButton("Exit");
        exitButton.setToolTipText("Exits Program");
        exitButton.addActionListener((event) -> System.exit(0));
        //button that takes you to main Admin page
        var adminButton = new JButton("Admin");
        adminButton.setToolTipText("Admin Page");
        adminButton.addActionListener((event) -> adminMain());
        //button that takes you to main Course Director page
        var courseButton = new JButton("Course Director");
        courseButton.setToolTipText("Course Director Page");
        courseButton.addActionListener((event) -> cdMain());

        //formats layout and adds buttons to JPanel
        home.setLayout(new GridLayout(3,1));
        home.add(adminButton);
        home.add(courseButton);
        home.add(exitButton);

        contentPane.add(home);//adds home page to window

    }

    //Admin Home Page
    public  void adminMain(){
        //creates and formats admin page
        setTitle("Admin Home");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel adMain= new JPanel();//new panel for admin page

        //button to return to home page
        var backButton = new JButton("Back");
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> initGUI());

        //button to search by requirements page
        var search = new JButton("Search By Requirements");
        search.setToolTipText("Search by requirements");
        search.addActionListener((event) -> searchByReqs());

        //button to go to view request page
        var viewReqs = new JButton("View Requests");
        viewReqs.setToolTipText("View Requests");
        viewReqs.addActionListener((event) -> viewRequests());

        //button to go to page to assign teachers to request
        var assignTeach = new JButton("Assign Teacher");
        assignTeach.setToolTipText("Assign Teacher");
        assignTeach.addActionListener((event) -> assignTeacher());

        //button to go to page to update teacher's info
        var updateTeach = new JButton("Update Teacher Information");
        updateTeach.setToolTipText("UpdateTeacher Information");
        updateTeach.addActionListener((event) -> updateTeacher());

        //format components
        adMain.setLayout(new GridLayout(5,1));
        adMain.add(search);
        adMain.add(viewReqs);
        adMain.add(assignTeach);
        adMain.add(updateTeach);
        adMain.add(backButton);

        contentPane.remove(0);//clear JFrame
        contentPane.add(adMain);//add new JPanel
    }

    //Course Director Home Page
    public void cdMain(){
        //formats course director page
        setTitle("Course Director Home");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel cdMain= new JPanel();//creates main panel for course director page
        //button to return to home page
        var backButton = new JButton("Back");
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> initGUI());

        //button to submit request page
        var submitReq = new JButton("Submit Request");
        submitReq.setToolTipText("Submit Request");
        submitReq.addActionListener((event) -> submitRequest());

        //button to page to check request status
        var statCheck = new JButton("Check Request Status");
        statCheck.setToolTipText("View Requests");
        statCheck.addActionListener((event) -> statusCheck());
        //formats course director display
        cdMain.setLayout(new GridLayout(3,1));
        cdMain.add(submitReq);
        cdMain.add(statCheck);
        cdMain.add(backButton);

        contentPane.remove(0);//clears window
        contentPane.add(cdMain);//adds course director panel to window
    }



    //admin primary functions


    private void viewRequests(){
        //reformats JFrame
        setTitle("View Requests");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel requestView= new JPanel();// new JPanel for assign functions

        //creates and formats the window for displaying requests
        JTextArea textArea = new JTextArea(50, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600,350));
        textArea.setEditable(false); // only displays requests

        var backButton = new JButton("Back"); //creates a back button that returns to admin main page
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> adminMain());

        requestView.setLayout(new FlowLayout());//creates layout
        requestView.add(scrollPane);
        requestView.add(backButton);

        contentPane.remove(0);// clears JFrame
        contentPane.add(requestView);//adds request view JPanel to frame
    }
    private void assignTeacher(){
        setTitle("Assign Teacher");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel assignTeach= new JPanel();// new JPanel for assign functions

        var backButton = new JButton("Back"); //creates a back button that returns to admin main page
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> adminMain());

        JLabel teacher= new JLabel("Enter teacher name");
        JTextField teachName= new JTextField(25); //allows to enter teacher name
        JLabel request= new JLabel("Enter request number");
        JTextField requestNo= new JTextField(25); //allows to enter request number

        var assign= new JButton("Assign"); //button to assign teacher to reequest
        assign.setToolTipText("Assign Teacher to Request");
        //backButton.addActionListener((event) -> adminMain());

        //adds all components to JPanel assignTeach
        assignTeach.setLayout(new GridLayout(6,1));
        assignTeach.add(teacher);
        assignTeach.add(teachName);
        assignTeach.add(request);
        assignTeach.add(requestNo);
        assignTeach.add(assign);
        assignTeach.add(backButton);

        contentPane.remove(0);//clears JFrame
        contentPane.add(assignTeach);//adds new JPanel
    }
    private void searchByReqs(){
        //create new screen for search by requirements
        setTitle("Search By Requirements");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel searchMain= new JPanel();// new JPanel for search functions

        var backButton = new JButton("Back"); //creates a back button that returns to admin main page
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> adminMain());

        String[] options= {"Name","Skills", "Training" }; //creates a drop down menu of search criteria
        JComboBox optionList= new JComboBox(options);
        JLabel selectOption= new JLabel("Select Search Type");

        JLabel searchOne= new JLabel("Enter first search requirement");
        JTextField searchChoiceOne= new JTextField(25); //allows to enter search requirements
        JLabel searchTwo= new JLabel("Enter second search requirement");
        JTextField searchChoiceTwo= new JTextField(25);
        JLabel searchThree= new JLabel("Enter third search requirement");
        JTextField searchChoiceThree= new JTextField(25);

        var searchButton = new JButton("Search"); //search button creation
        searchButton.setToolTipText("Search on Requirement");
        searchButton.addActionListener((event) -> controller());

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
        searchMain.add(backButton);

        contentPane.remove(0);//clear JFrame
        contentPane.add(searchMain);//add new JPanel
    }
    private void updateTeacher() {
        setTitle("Update Teacher Information");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel updateTeach= new JPanel();// new JPanel for update functions

        var backButton = new JButton("Back"); //creates a back button that returns to admin main page
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> adminMain());

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

        contentPane.remove(0);//clear JFrame
        contentPane.add(updateTeach);//add new JPanel
    }

    private void controller() {
        //generic hold for controller
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
        backButton.addActionListener((event) -> cdMain());

        var submitReq = new JButton("Submit Request"); //creates a back button that returns to course director main page
        submitReq.setToolTipText("Return to course director page");
        submitReq.addActionListener((event) -> cdMain());

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

        contentPane.remove(0);//clear JFrame
        contentPane.add(subReq);//add new JPanel

    }
    private void statusCheck(){
    	 setTitle("Check Request Status");
         setSize(800, 400);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         JPanel statCheck= new JPanel();

         var backButton = new JButton("Back"); //creates a back button that returns to course director main page
         backButton.setToolTipText("Return to course director page");
         backButton.addActionListener((event) -> cdMain());
         
         JLabel req= new JLabel("Enter request number");
         JTextField reqNo= new JTextField(5); //allows to enter request number
         
         var check = new JButton("Check"); //creates a button that allows to check status
         check.setToolTipText("Check request status");
         check.addActionListener((event) -> controller());
         
         JTextArea textArea = new JTextArea(1, 10);// text area for displaying status
         textArea.setEditable(false); // only displays requests
         
         statCheck.setLayout(new GridLayout(3,2));
         statCheck.add(req);
         statCheck.add(reqNo);
         statCheck.add(check);
         statCheck.add(textArea);
         statCheck.add(backButton);
         
         contentPane.remove(0);//clear JFrame
         contentPane.add(statCheck);//add new JPanel
         
        

    }


    public static void main(String[] args) {//instantiate and call UI
        UserInterface ui= new UserInterface();
        ui.setVisible(true);
    }

}