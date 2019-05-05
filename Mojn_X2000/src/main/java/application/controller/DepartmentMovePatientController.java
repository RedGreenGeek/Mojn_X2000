package application.controller;

import application.model.Session;
import application.view.DepartmentMovePatientView;
import framework.API;

public class DepartmentMovePatientController  extends Controller{
	private DepartmentMovePatientView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public DepartmentMovePatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(DepartmentMovePatientView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String MoveDepartmentAPI( String patientId, String departmentName , String trilvl) {
		API api =  API.getInstance();
		String a = api.movePatientDepart(this.sessionModel.getPassword(),this.sessionModel.getUsername(), patientId,  departmentName,  trilvl);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);	
	}
}