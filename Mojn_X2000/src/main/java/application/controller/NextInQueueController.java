package application.controller;

import application.model.Session;
import application.view.NextInQueueView;
import framework.API;

public class NextInQueueController  extends Controller{
	private NextInQueueView view;

	
	public NextInQueueController(Session session) {
		this.sessionModel = session;
	}
	
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(NextInQueueView sView) {
		this.view = sView;
	}
	
	public String nextInQueueAPI(String departName) {
		API api =  API.getInstance();

		String a = api.getNextInQueue(this.sessionModel.getPassword(), this.sessionModel.getUsername(),departName);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}