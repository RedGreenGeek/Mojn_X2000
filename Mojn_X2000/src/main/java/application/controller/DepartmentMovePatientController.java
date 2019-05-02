package application.controller;

import application.model.Session;
import application.view.DepartmentMovePatientView;
import framework.API;

public class DepartmentMovePatientController  extends Controller{
	private DepartmentMovePatientView view;

	
	
	public DepartmentMovePatientController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(DepartmentMovePatientView view) {
		this.view = view;
	}
	
	public String MoveDepartmentAPI( String patientId, String departmentName , String trilvl) {
		API api =  API.getInstance();


		String a = api.movePatientDepart(this.sessionModel.getPassword(),this.sessionModel.getUsername(), patientId,  departmentName,  trilvl);
		return a;
	}

	public void Back2Main() {
		view.setVisible(false);	
	}
}