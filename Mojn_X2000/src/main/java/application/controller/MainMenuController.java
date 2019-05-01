package application.controller;

import javax.swing.JOptionPane;

import application.model.Session;
import application.view.AddPasswordView;
import application.view.ChangePasswordView;
import application.view.MainMenuView;

public class MainMenuController extends Controller{
	
	private MainMenuView view;
	private ApplicationController application;
	private Session session;


	
	public MainMenuController( Session session) {
		this.sessionModel = session;
		this.session = Session.getInstance();
		this.application = ApplicationController.getInstance();
	}
	
	public void setView(MainMenuView view) {
		this.view = view;
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
