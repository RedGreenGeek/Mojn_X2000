package application.controller;

import application.model.Session;
import application.view.SearchPatientView;
import framework.API;

public class SearchPatientController  extends Controller{
	private SearchPatientView view;
	
	
	public SearchPatientController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(SearchPatientView view) {
		this.view = view;
	}
	
	public String SearchAPI(String firstName, String surname, String day, String month, String year, String id, String depart) {
		API api =  API.getInstance();
		String  birthday = "";

		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		System.out.println(birthday);
		String a = api.patientSearcher(this.sessionModel.getPassword(),this.sessionModel.getUsername(), id, firstName, surname, birthday, depart);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
	}
}