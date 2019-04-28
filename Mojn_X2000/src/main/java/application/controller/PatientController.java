package application.controller;

import application.model.Inventory;
import application.model.Session;
import application.view.StaffView;
import application.view.HospitalView;
import application.view.InventoryView;
import application.view.PatientView;
import application.view.SearchView;

public class PatientController {
	private PatientView view;
	private Session sessionModel;
	private ApplicationController application;
	private Session session;
	
	
	
	public PatientController(Session session) {
		this.sessionModel = session;
		this.session = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}



	
	
	public void display() {
		view.setVisible(true);
	}
	public void setView(PatientView view) {
		this.view = view;
//		this.view.setTableModel(inventoryModel);
		this.view.setSession(sessionModel);
	}
	public void Back2Main() {


		view.setVisible(false);

		application.manageInventory(session);
	}
	
	public void ToAllocate() {
		
	}
	public void ToAdmit() {
		
	}
	public void ToMoveBed() {
		
	}
	public void ToDischarge() {
		
	}
	
	public void ToEdit() {
		
	}
	public void ToRegister() {
		
	}
	public void ToSearch() {
		
		SearchController SC = new SearchController(session);
		SearchView SView = new SearchView(SC);
		SC.setView(SView);

		SC.display();
		
	}
	
	public void ToMove() {
		
	}
	

}