package application.controller;

import application.model.Session;
import application.view.MovedBedPatientView;
import framework.API;

public class MovedBedPatientController  extends Controller{
	private MovedBedPatientView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public MovedBedPatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(MovedBedPatientView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String MovedBedAPI( String patientId, String newBed) {
		API api =  API.getInstance();
		String a = api.movePatientBed(this.sessionModel.getPassword(),this.sessionModel.getUsername(),patientId,  newBed);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}