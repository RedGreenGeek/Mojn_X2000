

package application.controller;

import application.model.Session;
import application.view.SearchStaffView;
import framework.API;

public class SearchStaffController  extends Controller{
	private SearchStaffView view;
	
	// This constructor takes the singleton session so the controller has all the user info 
	public SearchStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	// boots up the view of this function
	public void setView(SearchStaffView view) {
		this.view = view;
	}
	
	// Calls the api that handles the functionality of the page
	public String SearchAPI(String firstName, String surname, String email, String day, String month, String year, String id) {
		API api =  API.getInstance();
		String  birthday = "";
		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		String a = api.staffSearcher(this.sessionModel.getPassword(),this.sessionModel.getUsername(), id,  firstName,  surname,  birthday,  email); //last is email
		return a;
	}
	
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}