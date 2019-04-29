package application.controller;

import application.model.Session;
import application.model.User;
import application.view.LoginView;
import framework.API;
import framework.Password.Password;

public class LoginController {

	private ApplicationController application;
	private Session session;
	private LoginView view;
	
	public LoginController(ApplicationController application) {
		this.application = ApplicationController.getInstance();
		this.session = Session.getInstance();
		this.view = new LoginView(this);
	}
	
	public void validateCredentials(String username, String password) {
		
		session.setRole(username);
		User user = new User();
		user.setUsername(username);
		user.load();
		if ((!username.isEmpty()) && user.passwordMatches(password)) {
			session.setUser(user);
			view.setVisible(false);
			application.manageInventory(session);
		} else {
			view.showError();
		}
	}

	public void display() {
		view.setVisible(true);
	}

	
}
