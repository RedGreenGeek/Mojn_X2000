package application.controller;

import application.model.Session;
import application.view.FreeBedsHospitalView;
import framework.API;

public class FreeBedsHospitalController  extends Controller{
	private FreeBedsHospitalView view;
	
	public FreeBedsHospitalController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(FreeBedsHospitalView sView) {
		this.view = sView;
	}
	
	public String FreeBedsAPI(String departmentName) {
		API api =  API.getInstance();


		String a = api.bedsAvailable(this.getSession().getPassword(),this.getSession().getUsername(), departmentName);
		return a;
	}

	public void Back2Main() {
		view.setVisible(false);
	}
}