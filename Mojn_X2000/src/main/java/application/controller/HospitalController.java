package application.controller;

import application.model.Inventory;
import application.model.Session;
import application.view.StaffView;
import application.view.FreeBedsHospitalView;
import application.view.GetDepartmentView;
import application.view.GetQueueView;
import application.view.HospitalView;
import application.view.MainMenuView;
import application.view.RegisterPatientView;
import application.view.StaffDepartView;

public class HospitalController  extends Controller{
	private HospitalView view;
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
//		this.view.setSession(sessionModel);
	}
	public void Back2Main() {

		
		view.setVisible(false);

		application.manageInventory(session);
		
	}





	public void ToStaffInDepart() {
		StaffDepartController SC = new StaffDepartController(session);
		StaffDepartView SView = new StaffDepartView(SC);
		SC.setView(SView);

		SC.display();
		
	}





	public void ToGetDepart() {
		GetDepartmentController SC = new GetDepartmentController(session);
		GetDepartmentView SView = new GetDepartmentView(SC);
		SC.setView(SView);

		SC.display();
		
	}





	public void ToFreeBeds() {
		FreeBedsHospitalController SC = new FreeBedsHospitalController(session);
		FreeBedsHospitalView SView = new FreeBedsHospitalView(SC);
		SC.setView(SView);

		SC.display();		
	}





	public void ToQueue() {
		GetQueueController SC = new GetQueueController(session);
		GetQueueView SView = new GetQueueView(SC);
		SC.setView(SView);

		SC.display();		
	}

}