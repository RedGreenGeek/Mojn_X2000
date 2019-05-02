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
	
	public String EditAPI(String ID,String firstName, String lastName,String adress , String tribe, boolean alive) {
		API api =  API.getInstance();
		

		String a = api.changePatient(this.sessionModel.getPassword(),this.sessionModel.getUsername(),ID,  firstName,  lastName,  tribe,  adress, alive);
		return a;
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}
}