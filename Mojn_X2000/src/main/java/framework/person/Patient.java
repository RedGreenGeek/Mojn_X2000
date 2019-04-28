package framework.person;

import framework.Person;

public class Patient extends Person {
	
	private int patientID;
	private static int counter;
	private Integer triage = null;
	private Integer bed_location = null;
	
	public Patient(String firstName, String lastName, String address, String tribe, int day, int month, int year, boolean alive, String department) {
	    this.setFirstName(firstName);
	    this.setLastName(lastName);
	    this.setAlive(alive);
	    this.setBirthDay(day, month, year);
	    this.setTribe(tribe);
	    this.setAdress(address);
	    this.patientID = ++Patient.counter;
	    this.setDepartment(department);
	    this.triage = null;
	    this.bed_location = null;
	}
	
	public Patient(int PatientID, String firstName, String lastName, String address, String tribe, int day, int month, int year, boolean alive, String department, Integer triage, Integer bed) {
		this.setPatientID(PatientID);
		this.setFirstName(firstName);
	    this.setLastName(lastName);
	    this.setAlive(alive);
	    this.setBirthDay(day, month, year);
	    this.setTribe(tribe);
	    this.setAdress(address);
	    this.setPatientID(PatientID);
	    this.setDepartment(department);	   
 
	    if (bed != null) {
	    	this.setBedLocation(bed);
	    }
	    
	    if (triage != null) {
		    this.setTriage(triage);
	    }
	}

	@Override
	public String getID() {
		return Integer.toString(this.patientID);
	}
	
	protected void setPatientID(int patientID) {
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
	
	public Integer getTriage() {
		return triage;
	}
	
	public Integer getBedLocation() {
		return bed_location;
	}

	@Override
	public String toString() {
		return patientID+"; "+this.getDepartment()+"; "+this.getLastName()+", "+this.getFirstName();
	}
}