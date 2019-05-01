package application.controller;

import application.model.Session;
import application.view.AddPasswordView;
import application.view.AdmitPatientView;
import application.view.AllocatePatientView;
import application.view.DischargePatientView;
import application.view.FreeBedsHospitalView;
import application.view.HospitalView;
import application.view.MovedBedPatientView;
import application.view.SearchPatientView;
import framework.API;

public class AddPasswordController  extends Controller{
	private AddPasswordView view;

	
	public AddPasswordController(Session session) {
		this.sessionModel = session;
	}
	
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(AddPasswordView sView) {
		this.view = sView;
	}
	
	public String AddPasswordAPI(String newPassword1,String newPassword2 ,String staffID) {
		API api =  API.getInstance();


		String a = api.AddPassword( newPassword1,  newPassword2,  staffID);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}