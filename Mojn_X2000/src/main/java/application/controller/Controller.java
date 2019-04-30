package application.controller;

import application.model.Session;

public abstract class Controller {
	protected Session sessionModel;
	
	public Session getSession() {
		return this.sessionModel;
	}
}
