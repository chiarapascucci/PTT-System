import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Controller implements ActionListener {
	
	public static void main(String[] args) {
		
		// Create a DataHandler object in a AbstractDHFactory reference
		// (getDataHandlerInstance creates the instance and allows only 1 to exist) 
		AbstractDataHandlerFactory dataHandlerFactory = DataHandler.getDataHandlerInstance(); 
		
		// Create a Controller object to begin the application and control code flow 
		Controller control = new Controller(dataHandlerFactory);	
	}
	
	// Controller attributes
	private UserInterface 					view = null;
	private AbstractDataHandlerFactory 		data; 
	private InputGuard 						inputguard;
	
	String filepathAndName; 
	
	// Constructor 
	public Controller (AbstractDataHandlerFactory dataHandlerFactory) {
	
		// Set view
		view = new UserInterface(this);
		view.setVisible(true);
		
		// Set the data handler
		this.data = dataHandlerFactory; 
			
		// Load data for application 
		filepathAndName = "PTTAppData.txt";
		data.loadData(filepathAndName);
		
		// Set inputguard to check each actionPerformed branch 
		inputguard = new InputGuard(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//1-GET TO ADMIN VIEW
		if (e.getSource() == view.adminButton) {
			System.out.println("view as Admin");
			view.updateView(view.adminMain);
		}
		
		//2-GET TO CD VIEW
		else if (e.getSource() == view.courseButton) {
			System.out.println("view as Course Director");
			view.updateView(view.cDPanel);
		}
		
		//3 - EXIT BUTTON
		else if (e.getSource() == view.exitButton) {
			
			// Save file before exiting 
			data.saveData(filepathAndName);
			System.exit(0);
		}
		
		//3.2 BACK BUTTON FOR BOTH ADMIN AND CD
		else if (e.getSource() == view.adminMain.backButton || e.getSource() == view.cDPanel.backButton) {
			if (e.getSource() == view.adminMain.backButton) view.backToMain(view.adminMain);
			else view.backToMain(view.cDPanel);
		}
		
		//1.1 ADMIN >> ASSIGN TEACHER TO REQUEST
		else if (e.getSource() == view.adminMain.assign) {		
				
			view.adminMain.textArea.setText("");
		

			// Get teacher ID entered by user and ensure it is an integer 
			int TID;
			if (inputguard.ensureInteger(view.adminMain.teachName.getText())) {
				TID = Integer.parseInt(view.adminMain.teachName.getText());
			}
			else {
				view.adminMain.textArea.setText(inputguard.ensureIntegerMsg); 
				return; 
			}

			// Get request ID entered and ensure it is an integer
			int RID; 
			if (inputguard.ensureInteger(view.adminMain.requestNo.getText())) {
				RID = Integer.parseInt(view.adminMain.requestNo.getText());
			} 
			else {
				view.adminMain.textArea.setText(inputguard.ensureIntegerMsg); 
				return; 
			}
			
			// Get teacher and request references 
			TeachRequest t = data.getLOR().findReq(RID);
			PTTeacher p = data.getLOP().getTeacherRef(TID);
			
			boolean outcome = false;
			if (inputguard.ensureNotNullReference(p) && inputguard.ensureNotNullReference(t)) {
				
				outcome = p.assignTeacher(t);
				if (!outcome) {
					view.adminMain.textArea.setText("Request cannot be assigned to teacher as teacher unavailable.");				
				} else {
					outcome = t.addTeacher(p);
					if(!outcome) {
						view.adminMain.textArea.setText("Teacher cannot be assigned to request as does not fulfil request requirments.");
					} else {
						view.adminMain.textArea.setText("Success! teacher assigned to request.");
					}
					
				}
			}
			else {
				view.adminMain.textArea.setText("Invalid request number or teacher name");
				return; 
			}
		
		//view.adminMain.assignF.setEnabled(false);
	}
		
		// 1.2 - ADMIN >> SEARCH TEACHERS BY DIFFERENT CRITERIA
		else if (e.getSource() == view.adminMain.searchButton) {
			
			view.adminMain.textArea.setText("");	
			
			// Start option list 
			int i = view.adminMain.optionList.getSelectedIndex();
			System.out.println("search - int:" + i );
			String s = view.adminMain.searchChoiceOne.getText().trim();
			
			// Store results in array list
			ArrayList <PTTeacher> result = data.getLOP().findTeacher(s, i);
			
			if (inputguard.ensureNotNullReference(result.get(0))) {
				for (PTTeacher p : result) {
					view.adminMain.textArea.append(p.toString());
				}
			}
			else { 
				view.adminMain.textArea.setText("No results.");
				return;
			}
		}
		
		//1.3 ADMIN >> VIEW REQUESTS
		else if (e.getSource() == view.adminMain.viewReqs) {
			
			System.out.println("view reqs");
			view.adminMain.textArea.setText("");
			
			// Obtain list of requests in string[] form 
			String [] s = data.getLOR().printReqList();
			for (String i : s) {
				
				// Add these strings to the view
				view.adminMain.textArea.append(i);
				System.out.println(i);
			}

		}
		
		//1.4 ADMIN >> UPDATE INFORMATION FOR A SPECIFIC TEACHER
		//1.4.1 ADMIN >> add skill/training
		else if (e.getSource() == view.adminMain.addSkill) {
			
			
			view.adminMain.textArea.setText("");
			
			// Get teacher ID
			int TID;
			if (inputguard.ensureInteger(view.adminMain.teachID.getText())) {
				TID = Integer.parseInt(view.adminMain.teachID.getText());
			}
			else { 
				view.adminMain.textArea.setText(inputguard.ensureIntegerMsg);
				return; 
			}
			
			// Get teacher reference
			PTTeacher t = data.getLOP().getTeacherRef(TID);
			
			// Ensure it is not null using input guards
			if (inputguard.ensureNotNullReference(t)) {
				
				int n 		= view.adminMain.optionListUpdate.getSelectedIndex();
				String s 	= view.adminMain.choice.getText().trim();
				
				// Based on option list choice, add String to skills or training
				if (n == 0) {
					t.addSkill(s);
					view.adminMain.textArea.setText("Skill: " + s + " added to teacher ID " + TID);
				}
				else if(n== 1) {
					t.addTraining(s);
					view.adminMain.textArea.setText("Training: " + s + " added to teacher ID " + TID);
				}
			}
			else {
				view.adminMain.textArea.setText("Teacher ID does not exist.");
				return; 
			}

		}
		//1.4.2 ADMIN >> remove skill/training	
		else if (e.getSource() == view.adminMain.remSkill) {
			
			view.adminMain.textArea.setText("");
			
			// Get teacher ID from input field
			int TID; 
			if (inputguard.ensureInteger(view.adminMain.teachID.getText())) {
				TID = Integer.parseInt(view.adminMain.teachID.getText());
			}
			else {
				view.adminMain.textArea.setText(inputguard.ensureIntegerMsg);
				return; 
			}
			
			// Get teacher reference
			PTTeacher t = data.getLOP().getTeacherRef(TID);
			
			// Ensure reference is not null 
			if (inputguard.ensureNotNullReference(t)) {
				
				int n = view.adminMain.optionListUpdate.getSelectedIndex();
				String s = view.adminMain.choice.getText().trim();
				
				if (n == 0) {
					t.removeSkill(s);
				}
				else if (n ==1 ) {
					t.removeTraining(s);
				}
			}
		}
		
		//1.5 ADMIN >> view list of teachers
		else if (e.getSource() == view.adminMain.viewPTT) {
			 
			// Using a print list method instead cause trying to format via max char length of each name... 
			view.adminMain.textArea.setText("");
			String s = data.getLOP().printList();
			view.adminMain.textArea.append(s);
		}
		
		//1.6 ADMIN >> add teacher to the system
		else if (e.getSource() == view.adminMain.addT) {
			String fname = view.adminMain.addFName.getText();
			String lname = view.adminMain.addLName.getText();
			
			PTTeacher t = new PTTeacher (fname, lname, data.getLOP());
			
			view.adminMain.textArea.setText("New teacher record created: \n \n" + t);
		}
	
		//2.1 CD >> VIEW STATUS OF REQUESTS IN THE SYSTEM
		else if (e.getSource() == view.cDPanel.statCheck) {
			
			System.out.println("View request statuses:");
			
			view.cDPanel.displayField.setText("");
			String [] s = data.getLOR().printReqListStatus();
			for (String i : s) {
				System.out.println(i);
				view.cDPanel.displayField.append(i);
			}
		}
		
		//2.2 CD >> ADD A REQUEST TO THE SYSTEM MANUALLY
		else if (e.getSource() == view.cDPanel.submitReq) {
			
			view.cDPanel.displayField.setText("");
			String n = view.cDPanel.courseName.getText();
				
			int i; 
			if (inputguard.ensureInteger(view.cDPanel.noReq.getText())) {
				i = Integer.parseInt(view.cDPanel.noReq.getText());
			}
			else {
				view.cDPanel.displayField.setText(inputguard.ensureIntegerMsg);
				return;
			}
						
			String s = view.cDPanel.skills.getText();
			String [] skills = s.split(",");
			TeachRequest r = new TeachRequest (n,i,data.getLOR(), skills);
			
			view.cDPanel.displayField.setText("New request created: \n"+ r.toString());
		}
		
		// Automatically save data after every operation 
		data.saveData(filepathAndName);
	}
}
