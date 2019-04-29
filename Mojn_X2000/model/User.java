package application.model;

import framework.Password.Password;

public final class User {

	private String username;
	private String password;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void load() {
		this.password = username; // dummy: this means that password and username have to be the same
	}
	
	public boolean passwordMatches(String password) {
		Password pass = Password.getInstance();
		
		System.out.println(pass.checkPassword(password, this.username));
		return (pass.checkPassword(password, this.username));
	}
}
