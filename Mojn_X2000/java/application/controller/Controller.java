package application.controller;

import application.model.Session;
// This is a super class of all the controller classes that holds the common session
public abstract class Controller {
	
	protected Session sessionModel;
	
	public JobTypes getRole() {
		return this.sessionModel.getRole();
	}
	
	// Here we define the different job types the users can have and these are used to handle clearances  
	public enum JobTypes{
		Doctor,Clerk, ICTOfficer , Nurse;
	}

}
