package application.controller;

import application.model.Session;
import application.view.StaffDepartView;
import framework.API;

public class StaffDepartController  extends Controller{
	private StaffDepartView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public StaffDepartController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	// boots up the view of this function
	public void setView(StaffDepartView view) {
		this.view = view;
	}
	
	// Calls the api that handles the functionality of the page
	public String GetDepartStaffAPI( String departmentName) {
		API api =  API.getInstance();
		return api.getDeparmentStaff(this.sessionModel.getPassword(),this.sessionModel.getUsername(), departmentName);
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}