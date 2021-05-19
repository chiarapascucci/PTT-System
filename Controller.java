import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Controller implements ActionListener {
	
	private UserInterface view = null;
	
	public Controller (UserInterface v) {
	
		view = v;
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
			view.updateView(view.getCDPanel());
		}
		
		//3 - EXIT BUTTON
		else if (e.getSource() == view.exitButton) {
			//write to file?
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
				String name = view.adminMain.teachName.getText();
				int iD = Integer.parseInt(view.adminMain.requestNo.getText()); 
				TeachRequest t = DataHandler.getLOR().findReq(iD);
				PTTeacher p = DataHandler.getLOP().getTeacherRef(name);
				
				boolean outcome = false;
				if (t == null || p == null) {
					view.adminMain.textArea.setText("invalid request number or teacher name");
				}
				else {
					t.addTeacher(p);
					outcome = p.assign(t);
				
				}
				if (!outcome) {
					view.adminMain.textArea.setText("assignment failed");
				}
			
			
			//view.adminMain.assignF.setEnabled(false);
		}
		
		// 1.2 - ADMIN >> SEARCH TEACHERS BY DIFFERENT CRITERIA
		else if (e.getSource() == view.adminMain.searchButton) {
			
			view.adminMain.textArea.setText("");
			
			int i = view.adminMain.optionList.getSelectedIndex();
			
			System.out.println("search - int:" + i );
			
			String s = view.adminMain.searchChoiceOne.getText().trim();
			
			ArrayList <PTTeacher> result = DataHandler.getLOP().findTeacher(s, i);
			
			for (PTTeacher p : result) {
				view.adminMain.textArea.append(p.toString());
			}
			if (result.size() == 0) {
				view.adminMain.textArea.setText("no results");
			}
			
			//view.adminMain.searchF.setEnabled(false);
		}
		
		//1.3 ADMIN >> VIEW REQUESTS
		else if (e.getSource() == view.adminMain.viewReqs) {
			
			System.out.println("view reqs");
			view.adminMain.textArea.setText("");
			String [] s =DataHandler.getLOR().printReqList();
			for (String i : s) {
				view.adminMain.textArea.append(i);
				System.out.println(i);
			}

		}
		
		//1.4 ADMIN >> UPDATE INFORMATION FOR A SPECIFIC TEACHER
		//1.4.1 ADMIN >> add skill/training
		else if (e.getSource() == view.adminMain.addSkill) {
			view.adminMain.textArea.setText("");
			
			int iD = Integer.parseInt(view.adminMain.teachID.getText());
			PTTeacher t = DataHandler.getLOP().getTeacherRef(iD);
			int n = view.adminMain.optionListUpdate.getSelectedIndex();
			String s = view.adminMain.choice.getText().trim();
				
			if (n == 0) {
				t.addSkill(s);				
			}
			else if(n== 1) {
				t.addTraining(s);
			}
		}
		//1.4.2 ADMIN >> remove skill/training	
		else if (e.getSource() == view.adminMain.remSkill) {
			view.adminMain.textArea.setText("");
			int iD = Integer.parseInt(view.adminMain.teachID.getText());
			PTTeacher t = DataHandler.getLOP().getTeacherRef(iD);
			int n = view.adminMain.optionListUpdate.getSelectedIndex();
			String s = view.adminMain.choice.getText().trim();
			
			if (n == 0) {
				t.removeSkill(s);
			}
			else if (n ==1 ) {
				t.removeTraining(s);
			}
		}
		
		//1.5 ADMIN >> view list of teachers
		else if (e.getSource() == view.adminMain.viewPTT) {
			 
			view.adminMain.textArea.setText("");
			
			for (PTTeacher p : DataHandler.getLOP().getListReference()) {
				String s = p.toString();
				view.adminMain.textArea.append(s);
			}
		}
	
		//2.1 CD >> VIEW STATUS OF REQUESTS IN THE SYSTEM
		else if (e.getSource() == view.cDPanel.statCheck) {
			System.out.println("view reqs status");
			view.cDPanel.displayField.setText("");
			String [] s =DataHandler.getLOR().printReqListStatus();
			for (String i : s) {
				System.out.println(i);
				view.cDPanel.displayField.append(i);
			}
		}
		
		else if (e.getSource() == view.cDPanel.submitReq) {
			view.cDPanel.displayField.setText("");
			String n = view.cDPanel.courseName.getText();
			int i = Integer.parseInt(view.cDPanel.noReq.getText());
			String s = view.cDPanel.skills.getText();
			String [] skills = s.split(",");
			TeachRequest r = new TeachRequest (n,i,DataHandler.getLOR(), skills);
			
			view.cDPanel.displayField.setText("new request created: \n"+ r.toString());
		}
	}
}
