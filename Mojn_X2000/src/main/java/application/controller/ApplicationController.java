package application.controller;

import application.model.Inventory;
import application.model.Session;
import application.view.StaffView;
import framework.API;
import application.view.HospitalView;
import application.view.InventoryView;
import application.view.PatientView;

public class ApplicationController {
	
	private LoginController loginController;
	private InventoryController inventoryController;
	private StaffController StaffController;
	private HospitalController HospitalController;
	private PatientController PatientController;

	private static ApplicationController self;

	
	public static synchronized ApplicationController getInstance() {
		API api =  API.getInstance();

		if (self == null){
			self = new ApplicationController();
		}
		return self; 
	}
	
	public void manageInventory(Session session) {
		inventoryController = new InventoryController(new Inventory(), session);
		InventoryView invView = new InventoryView(inventoryController);

		inventoryController.setView(invView);
		inventoryController.display();
	}
	public void manageStaff(Session session) {
		StaffController = new StaffController(session);
		StaffView CSView = new StaffView(StaffController);
		StaffController.setView(CSView);

		StaffController.display();

	}
	
	public void manageHospital(Session session) {
		HospitalController = new HospitalController(session);
		HospitalView HView = new HospitalView(HospitalController);
		HospitalController.setView(HView);

		HospitalController.display();
	}
	
	public void managePatient(Session session) {
		PatientController = new PatientController(session);
		PatientView PView = new PatientView(PatientController);
		PatientController.setView(PView);

		PatientController.display();
	}
	
	public void login() {
		loginController = new LoginController(this);
		loginController.display();
	}
	
	
	public static void main(String[] args) {
		ApplicationController app = ApplicationController.getInstance();
		app.login();
	}
}
