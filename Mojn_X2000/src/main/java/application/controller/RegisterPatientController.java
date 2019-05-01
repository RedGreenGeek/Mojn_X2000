package application.controller;

import application.model.Session;
import application.view.RegisterPatientView;
import framework.API;

public class RegisterPatientController  extends Controller{
	private RegisterPatientView view;

	
	
	public RegisterPatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(RegisterPatientView view) {
		this.view = view;
	}
	
	public String RegisterAPI(String firstName, String lastName,String adress ,String day, String month, String year, String tribe, boolean alive) {
		API api =  API.getInstance();
		String  birthday = "";
		
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid Birthdate";
		}

		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		String a = api.registerPatient( firstName,  lastName, tribe,  adress, Integer.parseInt(day),Integer.parseInt(month), Integer.parseInt(year), alive);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}