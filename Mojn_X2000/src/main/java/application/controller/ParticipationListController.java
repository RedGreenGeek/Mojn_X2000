package application.controller;

import java.io.IOException;

import application.model.Session;
import application.view.DischargePatientView;
import application.view.ParticipationListView;
import framework.API;

public class ParticipationListController  extends Controller{
	private ParticipationListView view;
	
	
	public ParticipationListController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(ParticipationListView view) {
		this.view = view;
	}
	
	public String ParticipationListAPI( String department,boolean depart, boolean birthday, boolean address, boolean tribe) throws IOException {
		API api =  API.getInstance();


		String a = api.getParticipationList( department,depart ,birthday, address, tribe);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}