package framework.person.staff;

import framework.Department;
import framework.person.Staff;

public class Doctor extends Staff{
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
