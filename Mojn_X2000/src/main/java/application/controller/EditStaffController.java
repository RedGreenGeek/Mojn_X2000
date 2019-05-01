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
	
	public String EditAPI(String ID,String firstName, String lastName,String adress ,String day, String month, String year, String tribe, String job) {
		API api =  API.getInstance();
		
		if (day.equals("") || month.equals("") || year.equals("")){
			return "Invalid Birthdate";
		}

		String a = api.changeStaff(this.getSession().getPassword(),this.getSession().getUsername(), ID,  job,  firstName,  lastName,  adress, tribe, Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
		return a;
	}

	public void Back2Main() {
		view.setVisible(false);
	}
}