package application.controller;

import application.model.Session;
import application.view.BedsInUseHospitalView;
import framework.API;

public class BedsInUseHospitalController  extends Controller{
	private BedsInUseHospitalView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public BedsInUseHospitalController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(BedsInUseHospitalView sView) {
		this.view = sView;
	}
	// Calls the api that handles the functionality of the page
	public String FreeBedsAPI(String departmentName) {
		API api =  API.getInstance();
		String a = api.bedsInUse(this.sessionModel.getPassword(),this.sessionModel.getUsername(), departmentName);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}