package application.model;

public final class Session {

	private User user;
	private JobTypes role;
	private static Session self;
	
	public static synchronized Session getInstance() {
		if (self == null){
			self = new Session();
		}
		return self; 
	}

	public enum JobTypes{
		Doctor,Clerk, ICTOfficer, Nurse;
	}

	
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
}
