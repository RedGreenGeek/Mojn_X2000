package framework.person;

import framework.Department;
import framework.Person;

public class Patient extends Person {
	
	private int patientID;
	private static int counter;
	private Integer triage;
	private Integer bed_location;
	
	public Patient(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive, String d) {
	    this.setFirstName(firstName);
	    this.setLastName(lastName);
	    this.setAlive(alive);
	    this.setBirthDay(day, month, year);
	    this.setTribe(tribe);
	    this.setAdress(adress);
	    Patient.counter +=1;
	    this.patientID = Patient.counter;
	    this.setDepartment(d);
	}
	
	public Patient(int PatientID, String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive, String d) {
	    
		this.setPatientID(PatientID);
		this.setFirstName(firstName);
	    this.setLastName(lastName);
	    this.setAlive(alive);
	    this.setBirthDay(day, month, year);
	    this.setTribe(tribe);
	    this.setAdress(adress);
	    Patient.counter +=1;
	    this.patientID = Patient.counter;
	    this.setDepartment(d);
	    
	}

	@Override
	public String getID() {
		return Integer.toString(this.patientID);
	}
	
	public void setPatientID(int patientID) {
	    this.patientID = patientID;
	}
	
	public void setTriage(int triage) {
		this.triage = triage;
	}
	
	public void setBedLocation(int bed_location) {
		this.bed_location = bed_location;
	}
	
	public int getPatientID() {
	    return patientID;
	}
	
	public String getTriage() {
		return Integer.toString(triage);
	}
	
	public String getBedLocation() {
		return Integer.toString(bed_location);
	}



	@Override
	public String toString() {
		return patientID+"; "+this.getDepartment()+"; "+this.getLastName()+", "+this.getFirstName();
	}
	
}