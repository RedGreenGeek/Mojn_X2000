package application.controller;

import application.model.Session;
import application.view.StaffView;
import application.view.AddPasswordView;
import application.view.AssignStaffView;
import application.view.ChangePasswordView;
import application.view.EditStaffView;
import application.view.RegisterStaffView;
import application.view.SearchStaffView;

public class StaffController  extends Controller{
	private StaffView view;
	private ApplicationController application;
	
	// 
	public StaffController(Session session) {
		this.sessionModel = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}

	public void display() {
		view.setVisible(true);
	}
	
	public void setView(StaffView view) {
		this.view = view;
	}
	
	public void Back2Main() {
		view.setVisible(false);
		application.manageMainMenu(sessionModel);
	}

	public void ToSearch() {
		SearchStaffController SS = new SearchStaffController(sessionModel);
		SearchStaffView SView = new SearchStaffView(SS);
		SS.setView(SView);
		SS.display();	
	}

	public void ToEdit() {
		EditStaffController SC = new EditStaffController(sessionModel);
		EditStaffView SView = new EditStaffView(SC);
		SC.setView(SView);
		SC.display();		
	}

	public void ToRegister() {
		RegisterStaffController SC = new RegisterStaffController(sessionModel);
		RegisterStaffView SView = new RegisterStaffView(SC);
		SC.setView(SView);
		SC.display();
	}

	public void ToAssign() {
		AssignStaffController SC = new AssignStaffController(sessionModel);
		AssignStaffView SView = new AssignStaffView(SC);
		SC.setView(SView);
		SC.display();
	}
	
	public void ToAddPassword() {
		AddPasswordController SC = new AddPasswordController(sessionModel);
		AddPasswordView SView = new AddPasswordView(SC);
		SC.setView(SView);
		SC.display();
		}
	
	public void ToChangePassword() {
		ChangePasswordController SC = new ChangePasswordController(sessionModel);
		ChangePasswordView SView = new ChangePasswordView(SC);
		SC.setView(SView);
		SC.display();
		}
}
