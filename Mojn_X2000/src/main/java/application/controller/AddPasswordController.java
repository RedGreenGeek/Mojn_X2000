package application.controller;

import application.model.Session;
import application.view.AddPasswordView;
import framework.API;

public class AddPasswordController  extends Controller{
	private AddPasswordView view;

	
	public AddPasswordController(Session session) {
		this.sessionModel = session;
	}
	
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(AddPasswordView sView) {
		this.view = sView;
	}
	
	public String AddPasswordAPI(String newPassword1,String newPassword2 ,String staffID) {
		API api =  API.getInstance();

		String a = api.AddPassword(this.sessionModel.getPassword(), this.sessionModel.getUsername(),  newPassword1,  newPassword2,  staffID);
		return a;
	}

	public void Back2Main() {
		view.setVisible(false);
	}
}