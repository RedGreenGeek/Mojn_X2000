package application.controller;

import application.model.Session;
import application.view.RegisterStaffView;
import framework.API;

public class RegisterStaffController  extends Controller{
	private RegisterStaffView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public RegisterStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(RegisterStaffView sView) {
		this.view = sView;
	}
	// Calls the api that handles the functionality of the page
	public String RegisterAPI(String jobtype,String firstName, String lastName,String adress ,String day, String month, String year, String tribe) {
		API api =  API.getInstance();
		String  birthday = "";
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid infomation";
		}
		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		int d;
		int m;
		int y;
		try {
			d = Integer.parseInt(day);
			m = Integer.parseInt(month);
			y = Integer.parseInt(year);
		} catch (Exception e) {
			return "The day, month or year input isn't of the type interger. Please comply to this";
		}
		String a = api.registerStaff(this.sessionModel.getPassword(),this.sessionModel.getUsername(), jobtype , firstName,  lastName, adress, tribe, d,m,y);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}