package application.controller;

import application.model.Session;
import application.view.AddPasswordView;
import framework.API;

public class AddPasswordController  extends Controller{
	private AddPasswordView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public AddPasswordController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(AddPasswordView sView) {
		this.view = sView;
	}
	// Calls the api that handles the functionality of the page
	public String AddPasswordAPI(String newPassword1,String newPassword2 ,String staffID) {
		API api =  API.getInstance();
		String a = api.AddPassword(this.sessionModel.getPassword(), this.sessionModel.getUsername(),  newPassword1,  newPassword2,  staffID);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}