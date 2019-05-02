package application.controller;

import application.model.Session;
import application.view.MovedBedPatientView;
import framework.API;

public class MovedBedPatientController  extends Controller{
	private MovedBedPatientView view;

	
	public MovedBedPatientController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(MovedBedPatientView view) {
		this.view = view;
	}
	
	public String MovedBedAPI( String patientId, String newBed) {
		API api =  API.getInstance();


		String a = api.movePatientBed(this.sessionModel.getPassword(),this.sessionModel.getUsername(),patientId,  newBed);
		return a;
	}

	public void Back2Main() {
		view.setVisible(false);
		
	}
}