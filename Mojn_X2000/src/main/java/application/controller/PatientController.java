package application.controller;

import application.model.Session;
import application.view.AdmitPatientView;
import application.view.AllocatePatientView;
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
//		this.view.setSession(sessionModel);
	}
	public void Back2Main() {


		view.setVisible(false);

		application.manageInventory(session);
	}
//	public Session getSession(){
//		return sessionModel;
//	}
	public void ToAllocate() {
		AllocatePatientController SC = new AllocatePatientController(session);
		AllocatePatientView SView = new AllocatePatientView(SC);
		SC.setView(SView);

		SC.display();
		
		
		
		
	}
	public void ToAdmit() {
		
		AdmitPatientController SC = new AdmitPatientController(session);
		AdmitPatientView SView = new AdmitPatientView(SC);
		SC.setView(SView);

		SC.display();
		
	}
	public void ToMoveBed() {
		
		MovedBedPatientController SC = new MovedBedPatientController(session);
		MovedBedPatientView SView = new MovedBedPatientView(SC);
		SC.setView(SView);

		SC.display();
		
		
	}
	public void ToDischarge() {
		
		DischargePatientController SC = new DischargePatientController(session);
		DischargePatientView SView = new DischargePatientView(SC);
		SC.setView(SView);

		SC.display();
		
	}
	
	public void ToEdit() {
		EditPatientController SC = new EditPatientController(session);
		EditPatientView SView = new EditPatientView(SC);
		SC.setView(SView);

		SC.display();
		
		
	}
	public void ToRegister() {
		
		RegisterPatientController SC = new RegisterPatientController(session);
		RegisterPatientView SView = new RegisterPatientView(SC);
		SC.setView(SView);

		SC.display();
		
		
	}
	public void ToSearch() {
		
		SearchPatientController SC = new SearchPatientController(session);
		SearchPatientView SView = new SearchPatientView(SC);
		SC.setView(SView);

		SC.display();
		
	}
	
	public void ToDepartmentMove() {
		DepartmentMovePatientController SC = new DepartmentMovePatientController(session);
		DepartmentMovePatientView SView = new DepartmentMovePatientView(SC);
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
	

}