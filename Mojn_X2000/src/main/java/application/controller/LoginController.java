package application.controller;

import java.io.IOException;

import application.model.Session;
import application.model.User;
import application.view.LoginView;
import framework.API;

public class LoginController {

	private ApplicationController application;
	private Session session;
	private LoginView view;
	
	// The constructor boots up the application controller and creates a session based on the user login informations
	public LoginController(ApplicationController application) {
		this.application = ApplicationController.getInstance();
		this.session = Session.getInstance();
		this.view = new LoginView(this);
	}
	
	// checks if the login matches a known login in the system
	public void validateCredentials(String username, String password) {
		API api = API.getInstance();
		
		if ((!username.isEmpty()) && (!password.isEmpty()) && api.passwordMatch(password,username)) {
			session.setRole(username);
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			session.setUser(user);
			session.setPassword(password);
			view.setVisible(false);
			application.manageMainMenu(session);
		} else {
			view.showError();
		}
	}

	public void display() {
		view.setVisible(true);
	}
}