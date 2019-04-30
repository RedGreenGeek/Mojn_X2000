package application.controller;

import application.model.Session;
import application.view.AdmitPatientView;
import application.view.AllocatePatientView;
import application.view.DischargePatientView;
import application.view.HospitalView;
import application.view.MovedBedPatientView;
import application.view.SearchPatientView;
import framework.API;

public class MovedBedPatientController {
	private MovedBedPatientView view;
	private Session sessionModel;

	
	
	public MovedBedPatientController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(MovedBedPatientView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	}
	
	public String MovedBedAPI( String patientId, String newBed) {
		API api =  API.getInstance();


		String a = api.movePatientBed(patientId,  newBed);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}