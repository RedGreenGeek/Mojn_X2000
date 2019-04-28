package application.model;

public final class Session {

	private User user;
	private String role;
	private static Session self;
	
	public static synchronized Session getInstance() {
		if (self == null){
			self = new Session();
		}
		return self; 
	}

	

	
	public void setRole(String Role) {
		char first = Role.charAt(0);
		
		if (first == 'D') {
			role = "Doctor";
					}
		if (first == 'C') {
			role = "Clerk";
		}
		
		if (first == 'I') {
			role = "ICT-Officer";
		}
		
		if (first == 'N') {
			role = "Nurse";
		}
		
		

	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername() {
		return user.getUsername();
	}
	
	public String getRole() {
		return role;
	}
}
