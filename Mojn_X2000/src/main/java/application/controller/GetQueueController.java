package application.controller;


import application.model.Session;
import application.view.GetQueueView;
import framework.API;

public class GetQueueController  extends Controller{
	private GetQueueView view;
	
	public GetQueueController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(GetQueueView view) {
		this.view = view;
	}
	
	public String GetQueueAPI( String departmentName) {
		API api =  API.getInstance();

		return api.getQueue(this.getSession().getPassword(),this.getSession().getUsername(), departmentName);
	}

	public void Back2Main() {
		view.setVisible(false);
	}
}