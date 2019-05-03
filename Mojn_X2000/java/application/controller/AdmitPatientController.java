package application.controller;

import application.model.Session;
import application.view.AdmitPatientView;
import framework.API;

public class AdmitPatientController extends Controller{
	private AdmitPatientView view;
	
	// This constructor takes the singleton session so the controller has all the user info 
	public AdmitPatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(AdmitPatientView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String AdmitAPI(String triLvl, String departmentName, String patientId) {
		API api =  API.getInstance();
		String  birthday = "";
		System.out.println(birthday);
		String a = api.patientAdmission(this.sessionModel.getPassword(),this.sessionModel.getUsername(), triLvl,  departmentName,  patientId);
		return a;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
	}
}