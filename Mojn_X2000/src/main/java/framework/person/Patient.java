package framework.person;

public class Patient extends Person {
	
	static int counter;
	private int patientID; // Common to all instances of the patient class
	
	public int getPatientID() {
	    return this.getPatientID();
	}
	
	public void setPatientID(int patientID) {
	    this.patientID = patientID;
	}
	
	private Patient(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {

	    this.setFirstName(firstName);
	    this.setLastName(lastName);
	    this.setAlive(alive);
	    this.setBirthDay(day, month, year);
	    this.setTribe(tribe);
	    this.setAdress(adress);
	    this.patientID = ++Patient.counter;

	}
}
