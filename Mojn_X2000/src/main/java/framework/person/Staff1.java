package framework.person;

import java.util.Random;


public abstract class Staff1 extends Person{
	
	private String email;
	private String serialID;
	private String jobType;
	private static int counter;
	private String Password;

	public Staff1(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive, String JobType) {
		
		if (isValidStafftData(firstName, lastName, adress, tribe, day, month, year, alive)) {
			
		    this.setFirstName(firstName);
		    this.setLastName(lastName);
		    this.setAlive(alive);
		    this.setBirthDay(day, month, year);
		    this.setTribe(tribe);
		    this.setAdress(adress);
		    this.setStaffID(JobType);
		    this.setEmail();
			
		} else {
			
			throw new IllegalArgumentException ("Staff data is wrong!");
			
		}
	
	}

	
	public static boolean isValidStafftData(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {
		
		return isValidPersonData(firstName, lastName, day, month, year, adress, tribe, alive);
		
	}

@Override
public String toString() {
	String s = this.getJobType() + ", " + this.getFirstName() + " " + this.getLastName() + ", \t Email: " + this.getEmail();
	return s;
}

//public void setPassword(Password P) {
//	Random r = new Random();
//	String Pass = "";
//	String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k",
//						 "k","l","m","n","o","p","q","r","s","t","u",
//						 "v","x","y","z","w"};
//	String[] ints = {"1","2","3","4","5","6","7","8","9","0"};
//	
//	for (int x = 0 ; x<=5; x++) {
//		if (x>=3) {
//					Pass = Pass.concat((alphabet[r.nextInt(alphabet.length)]));}
//		else { Pass = Pass.concat(ints[r.nextInt(ints.length)]);}
//	}
//	this.Password = Pass;
//	P.addPassToMap(Pass, this.getID());
//	System.out.println("Your password has been created, it is: " + Pass);
//	
//}
//
//public void setPassword(Password P, String Pass) {
//	this.Password = Pass;
//	P.addPassToMap(Pass, this.getID());
//}
//
//public String getPassword() {
//	return this.Password;
//}

protected void setJobType(String jobType) {
	this.jobType = jobType;
}

public String getJobType() {
	return this.jobType;
}

protected void setStaffID(String jobTypeID) {
	this.serialID = jobTypeID + Staff1.counter;
	Staff1.counter +=1; 
}

public String getID() {
	return serialID;
}

protected void setEmail() {
	this.email = (this.getFirstName() + this.getLastName() + "_" + this.serialID + "@mail.com").replaceAll(" ","");
}

public String getEmail() {
	return email;
}
}