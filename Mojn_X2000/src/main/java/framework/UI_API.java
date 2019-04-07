package framework;

import framework.person.*;

public class UI_API {
	
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PATIENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public String RegisterPatient(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {
		
		try {
			
			ChangeReg cr = new ChangeReg();
			Patient patient = new Patient(firstName, lastName, adress, tribe, day, month, year, alive);
			ChangeReg(getHospital(), patient);
			
			
			
			return String.format("%s %s registered succesfully!", firstName, lastName);
			
		} catch (IllegalArgumentException e) {
			return "Illegal Argument!";
		}
		
		
	}

	
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   STAFF   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DEPARTMENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   USER ACCESS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DATABASE   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PARTICIPATION LISTS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
