package application.controller;

import application.model.Session;
import application.view.AssignStaffView;
import application.view.HospitalView;
import application.view.RegisterPatientView;
import application.view.SearchPatientView;
import framework.API;

public class AssignStaffController  extends Controller{
	private AssignStaffView view;
	private Session sessionModel;

	
	
	public AssignStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(AssignStaffView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	}
	
	public String AssignStaffAPI(String firstName, String lastName,String departmentName ,String day, String month, String year, String staffID, String email) {
		API api =  API.getInstance();
		String  birthday = "";
		
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid Birthdate";
		}

		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		System.out.println(birthday);
		String a = api.assignStaffDepartment( departmentName,  staffID,  firstName,  lastName,  birthday,  email);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}