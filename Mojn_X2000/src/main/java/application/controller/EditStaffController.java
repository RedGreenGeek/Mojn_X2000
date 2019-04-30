package application.controller;

import application.model.Session;
import application.view.EditPatientView;
import application.view.EditStaffView;
import application.view.HospitalView;
import application.view.RegisterPatientView;
import application.view.SearchPatientView;
import framework.API;

public class EditStaffController  extends Controller{
	private EditStaffView view;
	private Session sessionModel;

	
	
	public EditStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(EditStaffView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	}
	
	public String EditAPI(String ID,String firstName, String lastName,String adress ,String day, String month, String year, String tribe, String job) {
		API api =  API.getInstance();
		String  birthday = "";
		
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid Birthdate";
		}

		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		String a = api.changeStaff(ID,  job,  firstName,  lastName,  adress, tribe, Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}