package application.controller;

import application.model.Session;
import application.view.AdmitPatientView;
import application.view.AllocatePatientView;
import application.view.DischargePatientView;
import application.view.HospitalView;
import application.view.SearchPatientView;
import framework.API;

public class AllocatePatientController {
	private AllocatePatientView view;
	private Session sessionModel;

	
	
	public AllocatePatientController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(AllocatePatientView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	}
	
	public String AllocateToBedAPI( String patientId, String departmentName) {
		API api =  API.getInstance();


		String a = api.allocateToBed( departmentName,  patientId);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}