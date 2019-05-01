package application.controller;

import java.util.Iterator;
import java.util.LinkedList;

import application.model.Session;
import application.view.AdmitPatientView;
import application.view.DischargePatientView;
import application.view.GetDepartmentView;
import application.view.HospitalView;
import application.view.SearchPatientView;
import framework.API;

public class GetDepartmentController  extends Controller{
	private GetDepartmentView view;

	
	
	public GetDepartmentController(Session session) {
		this.sessionModel = session;

	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(GetDepartmentView view) {
		this.view = view;
	}
	
	public String GetDepartmentAPI() {
		API api =  API.getInstance();


		LinkedList<String> a = api.getDepartments();
		
		return a.toString();
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}