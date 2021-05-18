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
		
		//3.2 BACK BUTTON FOR BOTH ADMIN AND CD
		else if (e.getSource() == view.adminMain.backButton || e.getSource() == view.cDPanel.backButton) {
			if (e.getSource() == view.adminMain.backButton) view.backToMain(view.adminMain);
			else view.backToMain(view.cDPanel);
		}
		
		//1.1 ADMIN >> ASSIGN TEACHER TO REQUEST
		else if (e.getSource() == view.adminMain.assignTeach) {
			System.out.println("assign teacher");
			view.adminMain.assignF.setEnabled(true);
			
			if (e.getSource() == view.adminMain.assign) {
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
			}
		}
		
		// 1.2 - ADMIN >> SEARCH TEACHERS BY DIFFERENT CRITERIA
		else if (e.getSource() == view.adminMain.search) {
			System.out.println("search");
			
			view.adminMain.searchF.setEnabled(true);
			
			int i = view.adminMain.optionList.getSelectedIndex();
			String s = view.adminMain.searchChoiceOne.getText().trim();
			ArrayList <PTTeacher> result = DataHandler.getLOP().findTeacher(s, i);
			for (PTTeacher p : result) {
				view.adminMain.textArea.append(p.toString());
			}
		}
		
		//1.3 ADMIN >> VIEW REQUESTS
		else if (e.getSource() == view.adminMain.viewReqs) {
			
			System.out.println("view reqs");
			String [] s =DataHandler.getLOR().printReqList();
			for (String i : s) view.adminMain.textArea.append(i);

		}
		
		//1.4 ADMIN >> UPDATE INFORMATION FOR A SPECIFIC TEACHER
		else if (e.getSource() == view.adminMain.updateTeach) {
			
			
			if (e.getSource() == view.adminMain.addSkill) {
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
			
			else if (e.getSource() == view.adminMain.remSkill) {
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
			
		}
		
		
		//2.1 CD >> VIEW STATUS OF REQUESTS IN THE SYSTEM
		else if (e.getSource() == view.cDPanel.statCheck) {
			System.out.println("view reqs");
			String [] s =DataHandler.getLOR().printReqListStatus();
			for (String i : s) view.cDPanel.displayField.append(i);
		}
	}
}
