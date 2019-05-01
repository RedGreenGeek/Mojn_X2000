package application.controller;

import application.model.Session;
import application.view.AllocatePatientView;
import framework.API;

public class AllocatePatientController  extends Controller{
	private AllocatePatientView view;

	
	
	public AllocatePatientController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(AllocatePatientView view) {
		this.view = view;
//		this.view.setSession(sessionModel);
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