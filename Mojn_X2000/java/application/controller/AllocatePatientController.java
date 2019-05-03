package application.controller;

import application.model.Session;
import application.view.AllocatePatientView;
import framework.API;

public class AllocatePatientController  extends Controller{
	private AllocatePatientView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public AllocatePatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(AllocatePatientView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String AllocateToBedAPI( String patientId, String departmentName) {
		API api =  API.getInstance();
		String a = api.allocateToBed(this.sessionModel.getPassword(),this.sessionModel.getUsername(), departmentName,  patientId);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}