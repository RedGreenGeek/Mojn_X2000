package application.controller;

import javax.swing.JOptionPane;

import application.model.Inventory;
import application.model.Session;
import application.view.MainMenuView;

public class MainMenuController extends Controller{
	
	private Inventory inventoryModel;
	private MainMenuView view;
	private ApplicationController application;
	private Session session;


	
	public MainMenuController(Inventory inventory, Session session) {
		this.inventoryModel = inventory;
		this.sessionModel = session;
		this.session = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}
//	public Session getSession(){
//		return sessionModel;
//	}
	
	public void setView(MainMenuView view) {
		this.view = view;
//		this.view.setTableModel(inventoryModel);
//		this.view.setSession(sessionModel);
	}

	public void display() {
		view.setVisible(true);
	}
	public void Change2Staff() {

		
		view.setVisible(false);

		application.manageStaff(session);

		
	}
	public void Change2Hospital() {

		
		view.setVisible(false);

		application.manageHospital(session);
	}
	public void Change2Patient() {

		
		view.setVisible(false);

		application.managePatient(session);
	}
}
