package application.controller;

import application.model.Session;
import application.view.EditPatientView;
import framework.API;

public class EditPatientController  extends Controller{
	private EditPatientView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public EditPatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(EditPatientView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String EditAPI(String ID,String firstName, String lastName,String adress , String tribe, boolean alive) {
		API api =  API.getInstance();
		String a = api.changePatient(this.sessionModel.getPassword(),this.sessionModel.getUsername(),ID,  firstName,  lastName,  tribe,  adress, alive);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}