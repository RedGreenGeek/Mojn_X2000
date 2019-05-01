package application.controller;

import application.model.Session;
import application.view.AdmitPatientView;
import application.view.AllocatePatientView;
import application.view.DischargePatientView;
import application.view.FreeBedsHospitalView;
import application.view.HospitalView;
import application.view.MovedBedPatientView;
import application.view.SearchPatientView;
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


		String a = api.bedsAvailable( departmentName);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}