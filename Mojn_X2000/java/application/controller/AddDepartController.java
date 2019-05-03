package application.controller;

import application.model.Session;
import application.view.AddDepartView;
import framework.API;

public class AddDepartController  extends Controller{
	private AddDepartView view;

	
	public AddDepartController(Session session) {
		this.sessionModel = session;
	}
	
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(AddDepartView sView) {
		this.view = sView;
	}
	
	public String AddDepartAPI(String type,String name ,String maxbed) {
		API api =  API.getInstance();


		String a = api.addDepartment(this.sessionModel.getPassword(), this.sessionModel.getUsername(),type,  name,  maxbed);
		return a;
		
	}

	public void Back2Main() {
		view.setVisible(false);
		
		
	}

}