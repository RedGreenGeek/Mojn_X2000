package application.controller;

import application.model.Session;
import application.view.AdmitPatientView;
import application.view.AllocatePatientView;
import application.view.DepartmentMovePatientView;
import application.view.DischargePatientView;
import application.view.HospitalView;
import application.view.MovedBedPatientView;
import application.view.SearchPatientView;
import framework.API;

public class DepartmentMovePatientController  extends Controller{
	private DepartmentMovePatientView view;
	private Session sessionModel;

	
	
	public DepartmentMovePatientController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(DepartmentMovePatientView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	}
	
	public String MoveDepartmentAPI( String patientId, String departmentName , String trilvl) {
		API api =  API.getInstance();


		String a = api.movePatientDepart( patientId,  departmentName,  trilvl);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}