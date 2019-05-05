package application.controller;

import application.model.Session;
import application.view.DischargePatientView;
import framework.API;

public class DischargePatientController  extends Controller{
	private DischargePatientView view;
	
	// This constructor takes the singleton session so the controller has all the user info 
	public DischargePatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(DischargePatientView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String DischargeAPI( String patientId) {
		API api =  API.getInstance();
		String a = api.discharge(this.sessionModel.getPassword(),this.sessionModel.getUsername(), patientId);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);	
	}
}