package application.controller;

import application.model.Session;
import application.view.RegisterPatientView;
import framework.API;

public class RegisterPatientController  extends Controller{
	private RegisterPatientView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public RegisterPatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(RegisterPatientView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String RegisterAPI(String firstName, String lastName,String adress ,String day, String month, String year, String tribe, boolean alive) {
		API api =  API.getInstance();
		@SuppressWarnings("unused")
		String  birthday = "";
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid Birthdate";
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
		String a = api.registerPatient(this.sessionModel.getPassword(),this.sessionModel.getUsername(), firstName,  lastName, tribe,  adress,d,m,y , alive);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}