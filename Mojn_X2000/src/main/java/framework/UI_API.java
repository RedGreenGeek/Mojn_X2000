package framework;

import javax.swing.text.html.HTMLDocument.Iterator;

import framework.person.*;
import framework.*;

public class UI_API {
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PATIENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public static String RegisterPatient(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {
		
		try {
			Patient patient = new Patient(firstName, lastName, adress, tribe, day, month, year, alive);
			ChangeReg C = new ChangeReg();
			C.add(Hospital.getHospital(),patient);
	
			return String.format("%s %s registered succesfully!", firstName, lastName);
		} catch (IllegalArgumentException e) {
			
		}
			return "Illegal Argument!";
		
	}
		

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   STAFF   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public static String RegisterStaff(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive, String JobType) {
		
		try {
			Staff staff = new Staff(firstName, lastName, adress, tribe, day, month, year, alive, JobType);
			ChangeReg C = new ChangeReg();
			C.add(Hospital.getHospital(),staff);
	
			return String.format("%s %s registered succesfully!", firstName, lastName);
		} catch (IllegalArgumentException e) {
			
		}
			return "Insufficient data: Please fill all fields with correct data.";
		
	}
	/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DEPARTMENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	
	//searches for department and returns beds in use for the department matching the name.
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   USER ACCESS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DATABASE   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PARTICIPATION LISTS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



}
