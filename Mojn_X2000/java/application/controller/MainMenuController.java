package application.controller;

import application.model.Session;
import application.view.AddPasswordView;
import application.view.ChangePasswordView;
import application.view.MainMenuView;

public class MainMenuController extends Controller{
	private MainMenuView view;
	private ApplicationController application;
	private Session session;

	// This constructor takes the singleton session so the controller has all the user info 
	public MainMenuController( Session session) {
		this.sessionModel = session;
		this.session = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(MainMenuView view) {
		this.view = view;
	}
	// navigates to the sub menus
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
	// all the following methods sets up the view of all the functions that the buttons represents 
	public void ToAddPassword() {
		AddPasswordController SC = new AddPasswordController(session);
		AddPasswordView SView = new AddPasswordView(SC);
		SC.setView(SView);
		SC.display();
		}
	
	public void ToChangePassword() {
		ChangePasswordController SC = new ChangePasswordController(session);
		ChangePasswordView SView = new ChangePasswordView(SC);
		SC.setView(SView);
		SC.display();
		}
}
