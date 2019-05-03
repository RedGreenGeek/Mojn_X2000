package application.controller;

import application.model.Session;
import application.view.ChangePasswordView;
import framework.API;

public class ChangePasswordController extends Controller {
	private ChangePasswordView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public ChangePasswordController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(ChangePasswordView sView) {
		this.view = sView;
	}
	// Calls the api that handles the functionality of the page
	public String ChangePasswordAPI(String newPassword1,String newPassword2 ,String staffID, String oldPassword) {
		API api =  API.getInstance();
		String a = api.ChangePassword(this.sessionModel.getPassword(),this.sessionModel.getUsername(), oldPassword ,  newPassword1,  newPassword2,  staffID);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}
