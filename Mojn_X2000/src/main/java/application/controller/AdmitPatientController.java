package application.controller;

import application.model.Session;
import application.view.AdmitPatientView;
import application.view.HospitalView;
import application.view.SearchPatientView;
import framework.API;

public class AdmitPatientController extends Controller{
	private AdmitPatientView view;
	private Session sessionModel;

	
	
	public AdmitPatientController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(AdmitPatientView view) {
		this.view = view;
	}
	
	public String AdmitAPI(String triLvl, String departmentName, String patientId) {
		API api =  API.getInstance();
		String  birthday = "";


		System.out.println(birthday);
		String a = api.patientAdmission( triLvl,  departmentName,  patientId);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}