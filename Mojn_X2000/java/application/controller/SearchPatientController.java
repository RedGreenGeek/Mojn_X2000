package application.controller;

import application.model.Session;
import application.view.SearchPatientView;
import framework.API;

public class SearchPatientController  extends Controller{
	private SearchPatientView view;
	
	// This constructor takes the singleton session so the controller has all the user info 
	public SearchPatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(SearchPatientView view) {
		this.view = view;
	}
	
	// Calls the api that handles the functionality of the page
	public String SearchAPI(String firstName, String surname, String day, String month, String year, String id, String depart) {
		API api =  API.getInstance();
		String  birthday = "";
		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		String a = api.patientSearcher(this.sessionModel.getPassword(),this.sessionModel.getUsername(), id, firstName, surname, birthday, depart);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}