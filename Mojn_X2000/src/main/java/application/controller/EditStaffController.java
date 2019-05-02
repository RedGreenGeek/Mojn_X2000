package application.controller;

import application.model.Session;
import application.view.EditStaffView;
import framework.API;

public class EditStaffController  extends Controller{
	private EditStaffView view;

	
	
	public EditStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(EditStaffView view) {
		this.view = view;
	}
	
	public String EditAPI(String ID,String firstName, String lastName,String adress , String tribe, String job) {
		API api =  API.getInstance();
		// essential that the day, month and year are set to 0 because then the API doesn't change the birthday.
		String a = api.changeStaff(ID,  job,  firstName,  lastName,  adress, tribe,0,0,0);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}