package application.controller;

import application.model.Session;
// This is a super class of all the controller classes that holds the common session
public abstract class Controller {
	
	protected Session sessionModel;
	
	public Session getSession() {
		return this.sessionModel;
	}
}
