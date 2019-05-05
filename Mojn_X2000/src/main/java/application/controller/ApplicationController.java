package application.controller;

import application.model.Session;
import application.view.StaffView;
import application.view.HospitalView;
import application.view.MainMenuView;
import application.view.PatientView;
// This class manage the navigation around the menus and also holds the main method for the application
public class ApplicationController {
	private LoginController loginController;
	private MainMenuController MainMenuController;
	private StaffController StaffController;
	private HospitalController HospitalController;
	private PatientController PatientController;
	private static ApplicationController self;

	// this singleton of the class makes sure that there only exists one controller
	public static synchronized ApplicationController getInstance() {
		if (self == null){
			self = new ApplicationController();
		}
		return self; 
	}
	
	// the following methods handles the navigation around the menus
	public void manageMainMenu(Session session) {
		MainMenuController = new MainMenuController( session);
		MainMenuView invView = new MainMenuView(MainMenuController);
		MainMenuController.setView(invView);
		invView.getMenuTop().setSession(session);
		MainMenuController.display();
	}
	
	public void manageStaff(Session session) {
		StaffController = new StaffController(session);
		StaffView CSView = new StaffView(StaffController);
		StaffController.setView(CSView);
		CSView.getMenuTop().setSession(session);
		StaffController.display();
	}
	
	public void manageHospital(Session session) {
		HospitalController = new HospitalController(session);
		HospitalView HView = new HospitalView(HospitalController);
		HospitalController.setView(HView);
		HView.getMenuTop().setSession(session);
		HospitalController.display();
	}
	
	public void managePatient(Session session) {
		PatientController = new PatientController(session);
		PatientView PView = new PatientView(PatientController);
		PatientController.setView(PView);
		PView.getMenuTop().setSession(session);
		PatientController.display();
	}
	
	// calls the login controller
	public void login() {
		loginController = new LoginController(this);
		loginController.display();
	}
	
	public static void main(String[] args) {
		ApplicationController app = ApplicationController.getInstance();
		app.login();
	}
}
