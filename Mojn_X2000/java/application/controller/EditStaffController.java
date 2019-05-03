package application.controller;

import application.model.Session;
import application.view.EditStaffView;
import framework.API;

public class EditStaffController  extends Controller{
	private EditStaffView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public EditStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(EditStaffView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String EditAPI(String ID,String firstName, String lastName,String adress ,String day, String month, String year, String tribe, String job) {
		API api =  API.getInstance();
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid Birthdate";
		}
		String a = api.changeStaff(this.sessionModel.getPassword(),this.sessionModel.getUsername(),ID,  job,  firstName,  lastName,  adress, tribe, Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);	
	}
}