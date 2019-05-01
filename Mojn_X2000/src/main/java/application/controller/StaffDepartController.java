package application.controller;

import java.util.LinkedList;

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


		LinkedList<String> a = api.getDeparmentStaff( departmentName);
		return a.toString();
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}