package application.controller;

import application.model.Session;
import application.view.MenuTopView;
// This is a super class of all the controller classes that holds the common session
public abstract class Controller {
	
	protected Session sessionModel;
	protected MenuTopView menutop;
	
	public Session getSession() {
		return this.sessionModel;
	}
	
	// Here we define the different job types the users can have and these are used to handle clearances  
	public enum JobTypes{
		Doctor,Clerk, ICTOfficer , Nurse;
	}

}
