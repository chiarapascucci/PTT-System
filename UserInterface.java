
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
        //creates start window
        setTitle("Requesto (powered by PoweRon)");
        setSize(800, 400);
        contentPane= new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       mainButtons();
       contentPane.setVisible(true);

    }

    public void mainButtons(){
        JPanel home= new JPanel();

        var exitButton = new JButton("Exit");
        exitButton.setToolTipText("Exits Program");
        exitButton.addActionListener((event) -> System.exit(0));

        var adminButton = new JButton("Admin");
        adminButton.setToolTipText("Admin Page");
        adminButton.addActionListener((event) -> adminMain());

        var courseButton = new JButton("Course Director");
        courseButton.setToolTipText("Course Director Page");
        courseButton.addActionListener((event) -> cdMain());

        home.setLayout(new GridLayout(3,1));
        home.add(adminButton);
        home.add(courseButton);
        home.add(exitButton);

        contentPane.add(home);
        home.setVisible(true);
    }


    public  void adminMain(){
        //creates admin window
        setTitle("Admin Home");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel adMain= new JPanel();

        var backButton = new JButton("Back");
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> initGUI());

        var search = new JButton("Search By Requirements");
        search.setToolTipText("Search by requirements");
        search.addActionListener((event) -> searchByReqs());

        var viewReqs = new JButton("View Requests");
        viewReqs.setToolTipText("View Requests");
        viewReqs.addActionListener((event) -> viewRequests());

        var assignTeach = new JButton("Assign Teacher");
        assignTeach.setToolTipText("Assign Teacher");
        assignTeach.addActionListener((event) -> assignTeacher());

        adMain.setLayout(new GridLayout(4,1));
        adMain.add(search);
        adMain.add(viewReqs);
        adMain.add(assignTeach);
        adMain.add(backButton);

        contentPane.remove(0);
        contentPane.add(adMain);

    }

    public void cdMain(){
        //creates course director window
        setTitle("Course Director Home");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel cdMain= new JPanel();

        var backButton = new JButton("Back");
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> initGUI());

        var submitReq = new JButton("Submit Request");
        submitReq.setToolTipText("Submit Request");
        submitReq.addActionListener((event) -> submitRequest());

        var statCheck = new JButton("Check Request Status");
        statCheck.setToolTipText("View Requests");
        statCheck.addActionListener((event) -> statusCheck());

        cdMain.setLayout(new GridLayout(3,1));
        cdMain.add(submitReq);
        cdMain.add(statCheck);
        cdMain.add(backButton);

        contentPane.remove(0);
        contentPane.add(cdMain);
    }



    //admin primary functions
    private void viewRequests(){
        setTitle("View Requests");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel requestView= new JPanel();// new JPanel for assign functions

        var backButton = new JButton("Back"); //creates a back button that returns to admin main page
        backButton.setToolTipText("Return to previous page");
        backButton.addActionListener((event) -> adminMain());

        requestView.setLayout(new GridLayout());
        requestView.add(backButton);

        contentPane.remove(0);
        contentPane.add(requestView);


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
        JTextField teachName= new JTextField(25); //allows to enter search requirements
        JLabel request= new JLabel("Enter request number");
        JTextField requestNo= new JTextField(25); //allows to enter search requirements

        var assign= new JButton("Assign"); //creates a back button that returns to admin main page
        assign.setToolTipText("Assign Teacher to Request");
        //backButton.addActionListener((event) -> adminMain());

        assignTeach.setLayout(new GridLayout(6,1));
        assignTeach.add(teacher);
        assignTeach.add(teachName);
        assignTeach.add(request);
        assignTeach.add(requestNo);
        assignTeach.add(assign);
        assignTeach.add(backButton);

        contentPane.remove(0);
        contentPane.add(assignTeach);

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

        JTextField searchChoiceOne= new JTextField(25); //allows to enter search requirements
        JTextField searchChoiceTwo= new JTextField(25);
        JTextField searchChoiceThree= new JTextField(25);

        var searchButton = new JButton("Search");
        searchButton.setToolTipText("Search on Requirement");
        searchButton.addActionListener((event) -> runSearch());

        searchMain.setLayout(new GridLayout(3,2));
        searchMain.add(optionList);
        searchMain.add(searchChoiceOne);
        searchMain.add(searchChoiceTwo);
        searchMain.add(searchChoiceThree);
        searchMain.add(searchButton);
        searchMain.add(backButton);

        contentPane.remove(0);
        contentPane.add(searchMain);
    }


    //Admin secondary functions
    private void runSearch(){

    }





    //course director functions
    private void submitRequest(){
        // Submit Request
    }
    private void statusCheck(){
        // Check request status
    }








    public static void main(String[] args) {
        UserInterface ui= new UserInterface();
        ui.setVisible(true);
    }

}
