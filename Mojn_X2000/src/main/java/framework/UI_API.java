
	

			
package framework;

import framework.person.*;
import framework.Hospital;

public class UI_API {
	
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PATIENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public String RegisterPatient(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {
		
		try {
			
			ChangeReg cr = new ChangeReg();
			Patient patient = new Patient(firstName, lastName, adress, tribe, day, month, year, alive);
			ChangeReg(Hospital.getHospital(), patient);
			return String.format("%s %s registered succesfully!", firstName, lastName);
		} catch (IllegalArgumentException e) {
			
		}
			return "Illegal Argument!";
		
	}
	
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   STAFF   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String RegisterStaff(String FirstName, String LastName, String Adress, String Tribe, int bDay, int bMonth, int bYear) {
		
		
		
		return "The patient has been registered succesfully!";
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DEPARTMENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public int BedsCurrentlyInUse(Department p) {
		return ((InPatientDepart)p).beds.getBedsInUse();
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   USER ACCESS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DATABASE   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PARTICIPATION LISTS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
