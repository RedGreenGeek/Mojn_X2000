package framework.person;
import java.util.Random;

import framework.Person;
import framework.Password.Password;

// This abstract class takes care of all the functionalities that has something to do with a staff member
public abstract class Staff extends Person{
	//Create variables for different properties belonging to staff
private String email;
private String serialID;
private String jobType;
public static int counter;
private String Password;

	@Override
	//Override toString method, so we can print the properties of the staff
	public String toString() {
		return this.getID() + "\t" + this.getJobType() + "\t" + this.getDepartment() + "\t" + this.getLastName() + "\t" + this.getFirstName();
	}
	
	//Method for setting a (random) password for a staff member, it needs the Password class to work
	protected void setPassword(Password P) {
		Random r = new Random();
		String Pass = "";
		String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k",
							 "k","l","m","n","o","p","q","r","s","t","u",
							 "v","x","y","z","w"};
		String[] ints = {"1","2","3","4","5","6","7","8","9","0"};
		//Logics for the random password creation
		for (int x = 0 ; x<=5; x++) {
			if (x>=3) {
						Pass = Pass.concat((alphabet[r.nextInt(alphabet.length)]));}
			else { Pass = Pass.concat(ints[r.nextInt(ints.length)]);}
		}
		//Save the staff's password
		this.Password = Pass;
		P.addPassToMap(Pass, this.getID());
		System.out.println("Your password has been created, it is: " + Pass);
	}
	//overloaded setPassword method, if a specific password is wanted
	protected void setPassword(Password P, String Pass) {
		this.Password = Pass;
		P.addPassToMap(Pass, this.getID());
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