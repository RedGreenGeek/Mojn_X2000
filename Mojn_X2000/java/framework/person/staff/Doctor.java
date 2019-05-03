package framework.person.staff;

import framework.person.Staff;

//This class represents the staff type doctor
public class Doctor extends Staff{
	// This constructor is used when you have a completely new doctor that needs to be added
	public Doctor(String firstName, String lastName,String adress, String tribe, int day, int month, int year, String d) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthDay(day, month, year);
		this.setTribe(tribe);
		this.setAdress(adress);
		this.setJobType("Doctor");// It's very important that you first define job type, then ID and lastly email because they use each other
		this.setID("D");
		this.setEmail();
		this.setDepartment(d);
	}
	// This constructor is used when the doctor is already registered under a ID but needs to be changed in some way
	public Doctor(String jobID, String firstName, String lastName,String adress, String tribe, int day, int month, int year, String d) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthDay(day, month, year);
		this.setTribe(tribe);
		this.setAdress(adress);
		this.setJobType("Doctor");// It's very important that you first define job type, then ID and lastly email because they use each other
		this.setID_reload(jobID);
		this.setEmail();
		this.setDepartment(d);
	}
}
