package application.controller;

import application.model.Inventory;
import application.model.Session;
import application.view.StaffView;
import application.view.AssignStaffView;
import application.view.EditStaffView;
import application.view.MainMenuView;
import application.view.RegisterStaffView;
import application.view.SearchPatientView;
import application.view.SearchStaffView;

public class StaffController  extends Controller{
	private StaffView view;
	private ApplicationController application;
	private Session session;
	
	
	public StaffController(Session session) {
		this.sessionModel = session;
		this.session = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}



	
	
	public void display() {
		view.setVisible(true);
	}
	public void setView(StaffView view) {
		this.view = view;
//		this.view.setTableModel(inventoryModel);
//		this.view.setSession(sessionModel);
	}
	public void Back2Main() {

		
		view.setVisible(false);

		application.manageInventory(session);
		
	}





	public void ToSearch() {
		SearchStaffController SS = new SearchStaffController(session);
		SearchStaffView SView = new SearchStaffView(SS);
		SS.setView(SView);

		SS.display();
		
	
		
	}
		
	





	public void ToEdit() {
		EditStaffController SC = new EditStaffController(session);
		EditStaffView SView = new EditStaffView(SC);
		SC.setView(SView);

		SC.display();		
	}





	public void ToRegister() {
		RegisterStaffController SC = new RegisterStaffController(session);
		RegisterStaffView SView = new RegisterStaffView(SC);
		SC.setView(SView);

		SC.display();
		
	}





	public void ToAssign() {
		AssignStaffController SC = new AssignStaffController(session);
		AssignStaffView SView = new AssignStaffView(SC);
		SC.setView(SView);

		SC.display();
		
	}

}
