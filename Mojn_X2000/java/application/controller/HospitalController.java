package application.controller;

import application.model.Session;
import application.view.AddDepartView;
import application.view.AddPasswordView;
import application.view.BedsInUseHospitalView;
import application.view.ChangePasswordView;
import application.view.GetDepartmentView;
import application.view.GetQueueView;
import application.view.HospitalView;
import application.view.NextInQueueView;
import application.view.ParticipationListView;
import application.view.StaffDepartView;

public class HospitalController  extends Controller{
	private HospitalView view;
	private ApplicationController application;
	
	// This constructor takes the singleton session so the controller has all the user info 
	public HospitalController(Session session) {
		this.sessionModel = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}

	public void display() {
		view.setVisible(true);
	}
	// boots up the view of this function
	public void setView(HospitalView view) {
		this.view = view;
	}
	// This function closes the page and returns to the menu page.
	public void Back2Main() {
		view.setVisible(false);
		application.manageMainMenu(sessionModel);
	}
	// all the following methods sets up the view of all the functions that the buttons represents 
	public void ToStaffInDepart() {
		StaffDepartController SC = new StaffDepartController(sessionModel);
		StaffDepartView SView = new StaffDepartView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}

	public void ToGetDepart() {
		GetDepartmentController SC = new GetDepartmentController(sessionModel);
		GetDepartmentView SView = new GetDepartmentView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}

	public void ToFreeBeds() {
		BedsInUseHospitalController SC = new BedsInUseHospitalController(sessionModel);
		BedsInUseHospitalView SView = new BedsInUseHospitalView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();		
	}

	public void ToQueue() {
		GetQueueController SC = new GetQueueController(sessionModel);
		GetQueueView SView = new GetQueueView(SC);
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
	
	public void ToParticipationList() {
		ParticipationListController SC = new ParticipationListController(sessionModel);
		ParticipationListView SView = new ParticipationListView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToAddDepart() {
		AddDepartController SC = new AddDepartController(sessionModel);
		AddDepartView SView = new AddDepartView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
	
	public void ToNextInQueue() {
		NextInQueueController SC = new NextInQueueController(sessionModel);
		NextInQueueView SView = new NextInQueueView(SC);
		SC.setView(SView);
		SView.getMenuTop().setSession(sessionModel);
		SC.display();
	}
}