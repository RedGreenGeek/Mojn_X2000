

		
package framework;

import framework.person.*;
import framework.Hospital;

import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.Iterator;

import framework.department.Department;
import framework.department.hc.InPatientDepart;
import framework.*;
import framework.person.*;

public class UI_API {
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PATIENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public String RegisterPatient(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {
		
		try {
			
			ChangeReg cr = new ChangeReg();
			Patient patient = new Patient(firstName, lastName, adress, tribe, day, month, year, alive);
			cr.add(Hospital.getHospital(), patient);

			return String.format("%s %s registered succesfully!", firstName, lastName);
			
		} catch (IllegalArgumentException e) {
			
		}
			return "Illegal Argument!";
		
	}
	
	public HashSet<Patient> GetPatientRegister() {
		
		return Hospital.getHospital().getPatientRegistery();
		
	}
	
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   STAFF   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public String RegisterStaff(String FirstName, String LastName, String Adress, String Tribe, int bDay, int bMonth, int bYear) {
		
		return "The patient has been registered succesfully!";
		
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DEPARTMENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public int BedsCurrentlyInUse(String dName) {
		Searcher s = new Searcher(Hospital.getHospital());
		java.util.Iterator<Department> I = s.departmentSearch(dName).iterator();
		
		while (I.hasNext()) {
			Department d = I.next();		
			if (d.getName().equals(dName)) {
				return ((InPatientDepart)d).beds.getBedsInUse();
			}
		}
		throw new IllegalArgumentException("Department name not found");
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   USER ACCESS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DATABASE   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PARTICIPATION LISTS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



}
