package application.controller;

import application.model.Inventory;
import application.model.Session;
import application.view.StaffView;
import application.view.InventoryView;

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
		
	}





	public void ToEdit() {
		// TODO Auto-generated method stub
		
	}





	public void ToRegister() {
		// TODO Auto-generated method stub
		
	}





	public void ToAssign() {
		// TODO Auto-generated method stub
		
	}

}
