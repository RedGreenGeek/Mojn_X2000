package application.controller;


import application.model.Session;
import application.view.GetDepartmentView;
import framework.API;

public class GetDepartmentController  extends Controller{
	private GetDepartmentView view;

	// This constructor takes the singleton session so the controller has all the user info 
	public GetDepartmentController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(GetDepartmentView view) {
		this.view = view;
	}
	// Calls the api that handles the functionality of the page
	public String GetDepartmentAPI() {
		API api =  API.getInstance();
		return api.getDepartments(this.sessionModel.getPassword(),this.sessionModel.getUsername());
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);	
	}
}