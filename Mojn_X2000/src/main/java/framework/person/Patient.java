package framework.person;

import framework.Person;

public class Patient extends Person {
	private int patientID;
	public static int counter;
	private Integer triage = null;
	private Integer bed_location = null;
	
	@Override
	public String toString() {
		
		if (this.getDepartment() == null) {
			return patientID+"\t"+""+"\t"+this.getLastName()+"\t"+this.getFirstName()+"\t"+" "+"\t" + this.getTribe() +"\t"+this.getAdress();
		}
		
		else if (this.getTriage() == null) {
			return patientID+"\t"+this.getDepartment()+"\t"+this.getLastName()+"\t"+this.getFirstName()+"\t    "+this.getBedLocation()+" /"+""+"\t" + this.getTribe() +"\t"+this.getAdress();
		}
		
		else if (this.getBedLocation() == null) {
			return patientID+"\t"+this.getDepartment()+"\t"+this.getLastName()+"\t"+this.getFirstName()+"\t"+"      "+"/  "+this.getTriage()+"\t" + this.getTribe() +"\t"+this.getAdress();
		}
		
		else {
			return "ERROR";
		}
		
		
	}
	
	// This constructor creates a patient if the patient haven't been admitted to the hospital before
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
	// This constructor is used if the patient has been registered in the system before but needs to be changed in some way
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
	    	this.setTriage(null);
	    }
	    if (triage != null) {
		    this.setTriage(triage);
		    this.setBedLocation(null);
	    }
	    
	    if (department == null) {
	    	this.setTriage(null);
	    	this.setBedLocation(null);	
	    } 
	    	    
	}

	@Override
	public String getID() {
		return Integer.toString(this.patientID);
	}
	
	protected void setPatientID(int patientID) {
	    this.patientID = patientID;
	}
	
	public void setTriage(Integer triage) {
		this.triage = triage;
	}
	
	public void setBedLocation(Integer bed_location) {
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
}