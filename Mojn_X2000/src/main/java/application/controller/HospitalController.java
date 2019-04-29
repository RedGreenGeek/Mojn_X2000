package application.controller;

import application.model.Inventory;
import application.model.Session;
import application.view.StaffView;
import application.view.FreeBedsHospitalView;
import application.view.HospitalView;
import application.view.InventoryView;
import application.view.RegisterPatientView;

public class HospitalController {
	private HospitalView view;
	private Session sessionModel;
	private ApplicationController application;
	private Session session;
	
	
	public HospitalController(Session session) {
		this.sessionModel = session;
		this.session = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}



	
	
	public void display() {
		view.setVisible(true);
	}
	public void setView(HospitalView view) {
		this.view = view;
//		this.view.setTableModel(inventoryModel);
		this.view.setSession(sessionModel);
	}
	public void Back2Main() {

		
		view.setVisible(false);

		application.manageInventory(session);
		
	}





	public void ToStaffInDepart() {
		// TODO Auto-generated method stub
		
	}





	public void ToGetDepart() {
		// TODO Auto-generated method stub
		
	}





	public void ToFreeBeds() {
		FreeBedsHospitalController SC = new FreeBedsHospitalController(session);
		FreeBedsHospitalView SView = new FreeBedsHospitalView(SC);
		SC.setView(SView);

		SC.display();		
	}





	public void ToQueue() {
		// TODO Auto-generated method stub
		
	}

}