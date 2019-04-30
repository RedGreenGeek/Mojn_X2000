package application.controller;

import java.util.LinkedList;

import application.model.Session;
import application.view.AdmitPatientView;
import application.view.DischargePatientView;
import application.view.GetQueueView;
import application.view.HospitalView;
import application.view.SearchPatientView;
import application.view.StaffDepartView;
import framework.API;

public class GetQueueController {
	private GetQueueView view;
	private Session sessionModel;

	
	
	public GetQueueController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(GetQueueView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	}
	
	public String GetQueueAPI( String departmentName) {
		API api =  API.getInstance();


		LinkedList<String> a = api.getQueue( departmentName);
		return a.toString();
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}