package framework.person;
import java.util.Random;

import framework.Person;
import framework.Password.Password;

public abstract class Staff extends Person{
private String email;
private String serialID;
private String jobType;
public static int counter;
private String Password;

	@Override
	public String toString() {
		return this.getID() + "\t" + this.getJobType() + "\t" + this.getDepartment() + "\t" + this.getLastName() + "\t" + this.getFirstName();
	}
	
	protected void setPassword(Password P) {
		Random r = new Random();
		String Pass = "";
		String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k",
							 "k","l","m","n","o","p","q","r","s","t","u",
							 "v","x","y","z","w"};
		String[] ints = {"1","2","3","4","5","6","7","8","9","0"};
		
		for (int x = 0 ; x<=5; x++) {
			if (x>=3) {
						Pass = Pass.concat((alphabet[r.nextInt(alphabet.length)]));}
			else { Pass = Pass.concat(ints[r.nextInt(ints.length)]);}
		}
		this.Password = Pass;
		P.addPassToMap(Pass, this.getID());
		System.out.println("Your password has been created, it is: " + Pass);
		
	}
	
	protected void setPassword(Password P, String Pass) {
		this.Password = Pass;
		P.addPassToMap(Pass, this.getID());
	}
	
	protected String getPassword() {
		return this.Password;
	}
	
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	public String getJobType() {
		return this.jobType;
	}
	
	public void setID(String jobTypeID) {
		this.serialID = jobTypeID + Staff.counter;
		Staff.counter +=1; 
	}
	
	protected void setID_reload(String jobTypeID) {
		this.serialID = jobTypeID;
	}
	
	public String getID() {
		return serialID;
	}
	
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