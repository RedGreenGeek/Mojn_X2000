package application.controller;

import application.model.Session;
import application.view.EditPatientView;
import framework.API;

public class EditPatientController  extends Controller{
	private EditPatientView view;

	
	public EditPatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(EditPatientView view) {
		this.view = view;
	}
	
	public String EditAPI(String ID,String firstName, String lastName,String adress ,String day, String month, String year, String tribe, String alive) {
		API api =  API.getInstance();
		String  birthday = "";
		
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid Birthdate";
		}

		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		System.out.println(birthday);
		String a = api.changePatient(this.getSession().getPassword(),this.getSession().getUsername(), ID,  firstName,  lastName,  tribe,  adress, Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year), Boolean.parseBoolean(alive));
		return a;
	}

	public void Back2Main() {
		view.setVisible(false);
	}
}