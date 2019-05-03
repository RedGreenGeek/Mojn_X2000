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
	public String AssignStaffAPI(String firstName, String lastName,String departmentName ,String day, String month, String year, String staffID, String email) {
		API api =  API.getInstance();
		String  birthday = "";
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid Birthdate";
		}
		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		System.out.println(birthday);
		String a = api.assignStaffDepartment(this.sessionModel.getPassword(),this.sessionModel.getUsername(), departmentName,  staffID,  firstName,  lastName,  birthday,  email);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);	
	}
}