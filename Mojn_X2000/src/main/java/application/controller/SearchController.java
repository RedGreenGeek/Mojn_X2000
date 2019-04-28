package application.controller;

import application.model.Session;
import application.view.HospitalView;
import application.view.SearchView;
import framework.API;

public class SearchController {
	private SearchView view;
	private Session sessionModel;
	private ApplicationController application;
	private Session session;
	
	
	public SearchController(Session session) {
		this.sessionModel = session;
		this.session = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(SearchView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	}
	
	public String SearchAPI(String firstName, String surname, String adress, String tribe, String day, String month, String year, String depart) {
		API api =  API.getInstance();
		String a = api.patientSearcher("1", firstName, surname, day+"-"+month+"-"+year);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}
