package application.controller;

import application.model.Session;
import application.view.DischargePatientView;
import framework.API;

public class DischargePatientController  extends Controller{
	private DischargePatientView view;
	
	
	public DischargePatientController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(DischargePatientView view) {
		this.view = view;
	}
	
	public String DischargeAPI( String patientId) {
		API api =  API.getInstance();


		String a = api.discharge( patientId);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}