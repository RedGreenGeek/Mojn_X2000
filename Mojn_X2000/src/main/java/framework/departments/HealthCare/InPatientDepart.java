package framework.departments.HealthCare;

import java.util.HashSet;

import framework.departments.*;
import framework.person.Person;
//
public class InPatientDepart extends HCDepart{
//	public Beds beds;
		
	public InPatientDepart(String departName, HashSet<Person> staffSet /*, HashSet<Person> patientSet, int maxBeds*/) {
		super.setName(departName);
		super.setStaff(staffSet); 
//		this.patientSet = patientSet;
//		this.beds = new Beds(maxBeds);
	}
		
	

}
//
//
