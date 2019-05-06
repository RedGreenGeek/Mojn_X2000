package framework.person;

import framework.Person;


// This abstract class takes care of all the functionalities that has something to do with a staff member
public abstract class Staff extends Person{
	//Create variables for different properties belonging to staff
private String email;
private String serialID;
private String jobType;
public static int counter;

	@Override
	//Override toString method, so we can print the properties of the staff
	public String toString() {
		if (this.getDepartment() != null) {
			return this.getID() + "\t" + this.getJobType() + "\t" + this.getDepartment() + "\t" + this.getLastName() + "\t" + this.getFirstName() + "\t" + this.getTribe() +"\t" + this.getEmail();
		}
		else {
			return this.getID() + "\t" + this.getJobType() + "\t" + " " + "\t" + this.getLastName() + "\t" + this.getFirstName() + "\t" + this.getTribe() +"\t" + this.getEmail();
		}
	}
	
	//get functions for accessing different properties of a staff 
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	public String getJobType() {
		return this.jobType;
	}
	//Set ID's 
	public void setID(String jobTypeID) {
		this.serialID = jobTypeID + Staff.counter;
		Staff.counter +=1; 
	}
	public void setIDstatic(String jobTypeID) {
		this.serialID = jobTypeID; 
	}
	//Set ID if a new ID is wanted, without incrementing the counter
	protected void setID_reload(String jobTypeID) {
		this.serialID = jobTypeID;
	}
	
	public String getID() {
		return serialID;
	}
	//Set emails, one default, and one with wanted email
	protected void setEmail() {
		this.email = (this.getFirstName() + this.getLastName() + "_" + this.serialID + "@mail.com").replaceAll(" ","");
	}
	
	protected void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}