package application.controller;

import application.model.Session;
import application.view.AddPasswordView;
import application.view.AdmitPatientView;
import application.view.ChangePasswordView;
import application.view.DepartmentMovePatientView;
import application.view.DischargePatientView;
import application.view.EditPatientView;
import application.view.MovedBedPatientView;
import application.view.PatientView;
import application.view.RegisterPatientView;
import application.view.SearchPatientView;

public class PatientController extends Controller{
	private PatientView view;
	private ApplicationController application;
	
	// This constructor takes the singleton session so the controller has all the user info 
	public PatientController(Session session) {
		this.sessionModel = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}
	
	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(PatientView view) {
		this.view = view;
	}
	
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
		application.manageMainMenu(sessionModel);
	}
	
	// all the following methods sets up the view of all the functions that the buttons represents 
	public void ToAdmit() {
		AdmitPatientController SC = new AdmitPatientController(sessionModel);
		AdmitPatientView SView = new AdmitPatientView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToMoveBed() {
		MovedBedPatientController SC = new MovedBedPatientController(sessionModel);
		MovedBedPatientView SView = new MovedBedPatientView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToDischarge() {
		DischargePatientController SC = new DischargePatientController(sessionModel);
		DischargePatientView SView = new DischargePatientView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToEdit() {
		EditPatientController SC = new EditPatientController(sessionModel);
		EditPatientView SView = new EditPatientView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToRegister() {
		RegisterPatientController SC = new RegisterPatientController(sessionModel);
		RegisterPatientView SView = new RegisterPatientView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToSearch() {
		SearchPatientController SC = new SearchPatientController(sessionModel);
		SearchPatientView SView = new SearchPatientView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToDepartmentMove() {
		DepartmentMovePatientController SC = new DepartmentMovePatientController(sessionModel);
		DepartmentMovePatientView SView = new DepartmentMovePatientView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToAddPassword() {
		AddPasswordController SC = new AddPasswordController(sessionModel);
		AddPasswordView SView = new AddPasswordView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToChangePassword() {
		ChangePasswordController SC = new ChangePasswordController(sessionModel);
		ChangePasswordView SView = new ChangePasswordView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
}