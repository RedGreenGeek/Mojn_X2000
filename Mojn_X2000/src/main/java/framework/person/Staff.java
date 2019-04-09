package framework.person;

public class Staff extends Person {

	private String email;
	private String serialID;
	private String jobType;
	private static int counter;
	private String Password;

	public Staff(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive, String JobType) {
		
		if (isValidStafftData(firstName, lastName, adress, tribe, day, month, year, alive)) {
			
		    this.setFirstName(firstName);
		    this.setLastName(lastName);
		    this.setAlive(alive);
		    this.setBirthDay(day, month, year);
		    this.setTribe(tribe);
		    this.setAdress(adress);
		    this.setStaffID(JobType);
		    this.setEmail();
		    this.setJobType(JobType);
			
		} else {
			
			throw new IllegalArgumentException ("Insufficient data: Please fill all fields with correct data.");
			
		}
	
	}
	
	public static boolean isValidStafftData(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {
		
		return isValidPersonData(firstName, lastName, day, month, year, adress, tribe, alive);
		
	}
	
	protected void setStaffID(String jobTypeID) {
		this.serialID = jobTypeID + Staff.counter;
		Staff.counter +=1; 
	}
	
	public String getSerialID() {
		return serialID;
	}

	protected void setEmail() {
		this.email = (this.getFirstName() + this.getLastName() + "_" + this.serialID + "@mail.com").replaceAll(" ","");
	}
	
	protected void setJobType(String jobType) {
		this.jobType = jobType;
	}

}
