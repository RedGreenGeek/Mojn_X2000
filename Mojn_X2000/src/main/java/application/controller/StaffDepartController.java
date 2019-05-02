package application.controller;

import application.model.Session;
import application.view.StaffDepartView;
import framework.API;

public class StaffDepartController  extends Controller{
	private StaffDepartView view;

	
	
	public StaffDepartController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(StaffDepartView view) {
		this.view = view;
	}
	
	public String GetDepartStaffAPI( String departmentName) {
		API api =  API.getInstance();

		return api.getDeparmentStaff(this.sessionModel.getPassword(),this.sessionModel.getUsername(), departmentName);
	}

	public void Back2Main() {
		view.setVisible(false);
	}
}