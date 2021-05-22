package ModelViewController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import DataHandling.*;

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
			
		// Load data for application (File name hard coded and assumed to be in directory of program) 
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
			
			boolean outcome1 = false;
			boolean outcome2 = false;
			if (inputguard.ensureNotNullReference(p) && inputguard.ensureNotNullReference(t)) {
				
				// Both references must be successfully added for accurate assignment
				outcome1 = p.assignTeacher(t);
				outcome2 = t.addTeacher(p);
				
				/* If one assignment failed due to checks, remove successful *
				 * one as Teacher/Request references mirror each other.		 */
				if ((!outcome1) || (!outcome2)) {
					if(outcome1) {		
						p.removeRequest(t);
					} else if(outcome2) {
						t.removeTeacher(p);
					}
					view.adminMain.textArea.setText("Could not assign teacher.");				
				} else {
					view.adminMain.textArea.setText("Success! teacher assigned to request.");
					
				}
			}
			else {
				view.adminMain.textArea.setText("Invalid request number or teacher name.");
				return; 
			}
		
		
	}
		
		// 1.2 - ADMIN >> SEARCH TEACHERS BY DIFFERENT CRITERIA
		else if (e.getSource() == view.adminMain.searchButton) {
			
			view.adminMain.textArea.setText("");	
			
			// Start option list 
			int i = view.adminMain.optionList.getSelectedIndex();
			System.out.println("search - int:" + i );
			
			// Get user input (trim.() to ensure any spaces dont ruin input)
			String s = view.adminMain.searchChoiceOne.getText().trim();
			
			// Check if string is suitable (not empty for this case) 
			if (inputguard.ensureSuitableString(s)) {
				
				// Store results in array list
				ArrayList <PTTeacher> result = data.getLOP().findTeacher(s, i);
				
				if (!result.isEmpty()) {
					for (PTTeacher p : result) {
						view.adminMain.textArea.append(p.toString());
						view.adminMain.textArea.append("\n");
					}
				}
				else { 
					view.adminMain.textArea.setText("No results.");
					return;
				}
			}
			else { 
				view.adminMain.textArea.setText(inputguard.ensureStringNotEmptyMsg);
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
				view.adminMain.textArea.append("\n");
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
				//ensure string is not empty
				if (inputguard.ensureSuitableString(s)) {
					if (n == 0) {
						boolean res = t.addSkill(s);
						if (res) {
							view.adminMain.textArea.setText("Skill: " + s + " added to teacher ID " + TID);
						}
						else view.adminMain.textArea.setText("The selected teacher already has skill: " + s);
					}
					else if(n== 1) {
						boolean res = t.addTraining(s);
						if (res) {
							view.adminMain.textArea.setText("Training: " + s + " added to teacher ID " + TID);
						}
						else view.adminMain.textArea.setText("The selected teacher already has training/skill: " + s);
					}	
				}
				else view.adminMain.textArea.setText("Please enter a skill or training");
			}
			else {
				view.adminMain.textArea.setText("Teacher ID does not exist.");
				
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
				
				if (inputguard.ensureSuitableString(s)) {
					if (n == 0) {
						boolean res = t.removeSkill(s);
						if (!res) view.adminMain.textArea.setText("Oh dear! this teacher does not have the skill entered");
						else view.adminMain.textArea.setText("skill ["+s+"] has been removed from teacher ID: "+t.gettID());
					}
					else if (n ==1 ) {
						boolean res = t.removeTraining(s);
						if (!res) view.adminMain.textArea.setText("Oh dear! this teacher does not have the training entered");
						else view.adminMain.textArea.setText("training ["+s+"] has been removed from teacher ID: "+t.gettID());
					}
				}
				else view.adminMain.textArea.setText("Please enter a skill/training to remove");				
			}
		}
		
		
		//1.4.3 ADMIN >> mark training as complete: training gets removed from trainig list and added as a skill for the target teacher
		else if(e.getSource()==view.adminMain.compTraining) {
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
					view.adminMain.textArea.setText("You have selected skills, please select training");
				}
				else if (n ==1 ) {
					boolean res = t.completeTraining(s);
					if (res == false) {
						view.adminMain.textArea.setText("The selected teacher does not have this training assigned. \n please check your input");
					}
					else {
						view.adminMain.textArea.setText("teacher with ID: " + t.gettID() +"\n"+ "training ["+s+"] completed");
					}

				}
			}
			else {
				view.adminMain.textArea.setText("Teacher ID does not exist.");
				return;
			}
		}
		
		//1.5 ADMIN >> view list of teachers
		else if (e.getSource() == view.adminMain.viewPTT) {
			 
			
			view.adminMain.textArea.setText("");
			for (PTTeacher i : data.getLOP().getListReference()) {
				view.adminMain.textArea.append(i.toString());
				view.adminMain.textArea.append("\n");
			}
			
		}
		
		//1.6 ADMIN >> add teacher to the system
		else if (e.getSource() == view.adminMain.addT) {
			String fname = view.adminMain.addFName.getText().trim();
			String lname = view.adminMain.addLName.getText().trim();
			
			if (inputguard.ensureSuitableString(fname) && inputguard.ensureSuitableString(lname)) {
				PTTeacher t = new PTTeacher (fname, lname, data.getLOP());
				view.adminMain.textArea.setText("New teacher record created: \n \n" + t);
			}
			else { 
				view.adminMain.textArea.setText(inputguard.ensureStringNotEmptyMsg); 
				return;
			}

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
			
			// Get course name
			String n = view.cDPanel.courseName.getText().trim();
		
			// Get number of teachers required
			int i; 
			if (inputguard.ensureInteger(view.cDPanel.noReq.getText())) {
				i = Integer.parseInt(view.cDPanel.noReq.getText());
			}
			else {
				view.cDPanel.displayField.setText(inputguard.ensureIntegerMsg);
				return;
			}
			
			//  Get entered skills
			String s = view.cDPanel.skills.getText();
			String [] skills = s.split(",");
			
			// Ensure each skill has no white spaces which affect results
			for (int j = 0; j < skills.length; j++) {
				skills[j] = skills[j].trim(); 
			}
			
			if (inputguard.ensureSuitableString(n)) {
				TeachRequest r = new TeachRequest (n,i,data.getLOR(), skills);
				view.cDPanel.displayField.setText("New request created: \n"+ r.toString());
			} 
			else {
				view.cDPanel.displayField.setText(inputguard.ensureStringNotEmptyMsg + "in Course Name.");
				return;
			}
			
		}
		
		// Automatically save data after every operation to ensure data is preserved in the event of a crash
		data.saveData(filepathAndName);
	}
}
