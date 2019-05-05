package application.model;

import application.controller.Controller.JobTypes;

public final class Session {

	private User user;
	private JobTypes role;
	private static Session self;
	
	// This provides a singleton of the session he or she is logged in 
	public static synchronized Session getInstance() {
		if (self == null){
			self = new Session();
		}
		return self; 
	}


	// ID of the user is mapped to a clearance
	public void setRole(String Role) {
		char first = Role.charAt(0);
		
		if (first == 'D') {
			role = JobTypes.Doctor;
					}
		if (first == 'C') {
			role = JobTypes.Clerk;
		}
		
		if (first == 'I') {
			role = JobTypes.ICTOfficer;
		}
		
		if (first == 'N') {
			role = JobTypes.Nurse;
		}
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername() {
		return user.getUsername();
	}
	
	public JobTypes getRole() {
		return role;
	}

	public void setPassword(String password) {
		this.user.setPassword(password);
	}

	public String getPassword() {
		return this.user.getPassword();
	}
}
