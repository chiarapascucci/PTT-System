import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Controller implements ActionListener {
	
	private UserInterface view = null;
	
	public Controller (UserInterface v) {
	
		view = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.adminButton) {
			System.out.println("view as Admin");
			view.updateView(view.adminMain);
		}
		
		else if (e.getSource() == view.courseButton) {
			System.out.println("view as Course Director");
			view.updateView(view.getCDPanel());
		}
		
		else if (e.getSource() == view.adminMain.backButton || e.getSource() == view.cDPanel.backButton) {
			if (e.getSource() == view.adminMain.backButton) view.backToMain(view.adminMain);
			else view.backToMain(view.cDPanel);
		}
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
		
		else if (e.getSource() == view.adminMain.search) {
			System.out.println("search");
			
			view.adminMain.searchF.setEnabled(true);
			
			if (view.adminMain.optionList.getSelectedIndex() == 0) {
				
			}
			else if (view.adminMain.optionList.getSelectedIndex() == 1) {
				
			}
			else if (view.adminMain.optionList.getSelectedIndex() == 2) {
				
			}
			//need dh --> access ptt lists
		}
		
		else if (e.getSource() == view.adminMain.viewReqs) {
			//need dh --> access reqs list
			System.out.println("view reqs");
			String [] s =DataHandler.getLOR().printReqList();
			for (String i : s) view.adminMain.textArea.append(i);
			
			//this would access the DH and from there LOR, then use print method of LOR (should return String[]) to display list in scroll pane
		}
		
		else if (e.getSource() == view.adminMain.updateTeach) {
			System.out.println("update teacher");
		}
		
		else if (e.getSource() == view.cDPanel.statCheck) {
			System.out.println("view reqs");
			String [] s =DataHandler.getLOR().printReqListStatus();
			for (String i : s) view.cDPanel.displayField.append(i);
		}
	}
}
