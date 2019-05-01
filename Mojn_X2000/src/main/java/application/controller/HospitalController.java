package application.controller;

import application.model.Session;
import application.view.AddPasswordView;
import application.view.ChangePasswordView;
import application.view.FreeBedsHospitalView;
import application.view.GetDepartmentView;
import application.view.GetQueueView;
import application.view.HospitalView;
import application.view.ParticipationListView;
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
	public void ToParticipationList() {
		ParticipationListController SC = new ParticipationListController(session);
		ParticipationListView SView = new ParticipationListView(SC);
		SC.setView(SView);

		SC.display();
	}
}