package framework.person;

public class Patient extends Person {
	private int patientID;
	private static int counter;
	
	public int getPatientID() {
	    return this.patientID;
	}
	private Patient() {}
	
	public static Patient newPatient(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {
		if (!(firstName.replaceAll(" ", "").equals("") || lastName.replaceAll(" ", "").equals("") || tribe.replaceAll(" ", "").equals(""))) {
			Patient p = new Patient();
		    p.setFirstName(firstName);
		    p.setLastName(lastName);
		    p.setAlive(alive);
		    p.setBirthDay(day, month, year);
		    p.setTribe(tribe);
		    p.setAdress(adress);
		    Patient.counter +=1;
		    p.patientID = Patient.counter;
		    return p;
			
		} else {
			return null;
			// Do nothing
		}
	}
}
