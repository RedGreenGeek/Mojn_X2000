package application.controller;

import application.model.Inventory;
import application.model.Session;
import application.view.StaffView;
import application.view.EditStaffView;
import application.view.InventoryView;
import application.view.RegisterStaffView;
import application.view.SearchPatientView;
import application.view.SearchStaffView;

public class StaffController {
	private StaffView view;
	private Session sessionModel;
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
		this.view.setSession(sessionModel);
	}
	public void Back2Main() {

		
		view.setVisible(false);

		application.manageInventory(session);
		
	}





	public void ToSearch() {
		
		SearchStaffController SC = new SearchStaffController(session);
		SearchStaffView SView = new SearchStaffView(SC);
		SC.setView(SView);

		SC.display();
		
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
		// TODO Auto-generated method stub
		
	}

}
