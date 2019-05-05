package application.controller;

import java.io.IOException;

import application.model.Session;
import application.view.ParticipationListView;
import framework.API;

public class ParticipationListController  extends Controller{
	private ParticipationListView view;
	
	// This constructor takes the singleton session so the controller has all the user info 
	public ParticipationListController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(ParticipationListView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String ParticipationListAPI( String department,boolean depart, boolean birthday, boolean address, boolean tribe) throws IOException {
		API api =  API.getInstance();
		String a = api.getParticipationList(this.sessionModel.getPassword(),this.sessionModel.getUsername(), department,depart ,birthday, address, tribe);
		return a;	
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}