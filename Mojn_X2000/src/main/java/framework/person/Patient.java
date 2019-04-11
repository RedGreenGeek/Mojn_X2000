package framework.person;

import framework.Department;
import framework.Person;

public class Patient extends Person {
private int patientID;
private static int counter;

public int getPatientID() {
    return this.patientID;
}

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

@Override
public String getID() {
	return Integer.toString(this.patientID);
}
}