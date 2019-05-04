package application.controller;

import application.model.Session;
import application.view.AssignStaffView;
import framework.API;

public class AssignStaffController  extends Controller{
	private AssignStaffView view;
	
	// This constructor takes the singleton session so the controller has all the user info 
	public AssignStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(AssignStaffView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String AssignStaffAPI(String departmentName , String staffID) {
		API api =  API.getInstance();
		
		String a = api.assignStaffDepartment(this.sessionModel.getPassword(),this.sessionModel.getUsername(), departmentName,  staffID);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);	
	}
}