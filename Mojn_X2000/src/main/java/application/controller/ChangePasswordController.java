package application.controller;

import application.model.Session;
import application.view.ChangePasswordView;
import framework.API;

public class ChangePasswordController extends Controller {
	private ChangePasswordView view;

	
	public ChangePasswordController(Session session) {
		this.sessionModel = session;
	}
	
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(ChangePasswordView sView) {
		this.view = sView;
	}
	
	public String ChangePasswordAPI(String newPassword1,String newPassword2 ,String staffID, String oldPassword) {
		API api =  API.getInstance();


		String a = api.ChangePassword(this.sessionModel.getPassword(),this.sessionModel.getUsername(), oldPassword ,  newPassword1,  newPassword2,  staffID);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}
}
