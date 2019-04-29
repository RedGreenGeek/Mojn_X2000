package application.controller;

import application.model.Session;
import application.view.HospitalView;
import application.view.RegisterPatientView;
import application.view.RegisterStaffView;
import application.view.SearchPatientView;
import framework.API;

public class RegisterStaffController {
	private RegisterStaffView view;
	private Session sessionModel;

	
	
	public RegisterStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(RegisterStaffView sView) {
		this.view = sView;
		this.view.setSession(sessionModel);
	}
	
	public String RegisterAPI(String jobtype,String firstName, String lastName,String adress ,String day, String month, String year, String tribe) {
		API api =  API.getInstance();
		String  birthday = "";
		
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid infomation";
		}

		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		System.out.println(birthday);
		String a = api.registerStaff (jobtype , firstName,  lastName, adress, tribe, Integer.parseInt(day),Integer.parseInt(month), Integer.parseInt(year));
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}