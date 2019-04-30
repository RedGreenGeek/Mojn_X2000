

package application.controller;

import application.model.Session;
import application.view.HospitalView;
import application.view.SearchPatientView;
import application.view.SearchStaffView;
import framework.API;

public class SearchStaffController {
	private SearchStaffView view;
	private Session sessionModel;
	private ApplicationController application;
	private Session session;
	
	
	public SearchStaffController(Session session) {
		this.sessionModel = session;
		this.session = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(SearchStaffView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	}
	
	public String SearchAPI(String firstName, String surname, String email, String day, String month, String year, String id) {
		API api =  API.getInstance();
		String  birthday = "";

		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		String a = api.staffSearcher(id,  firstName,  surname,  birthday,  email); //last is email
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}