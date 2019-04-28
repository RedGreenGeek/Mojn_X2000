package framework.Departments.HealthCare;

import java.util.HashSet;

import framework.Person;
import framework.Departments.HCDepart;

public class InPatientDepart extends HCDepart{
	
	public Beds beds;
	public int max_beds;
		
	public InPatientDepart(String departName, HashSet<Person> staffSet, HashSet<Person> patientSet, int maxBeds) {
		super.setName(departName);
		super.setStaff(staffSet); 
		this.patientSet = patientSet;
		this.beds = new Beds(maxBeds);
	}
	
	public InPatientDepart(String departName, int maxBeds) {
		this.setStaff(new HashSet<Person>());
		this.patientSet = new HashSet<Person>();
		super.setName(departName);
		this.max_beds = maxBeds;
		this.beds = new Beds(maxBeds);
	}
	
	public int get_max_beds() {
		return max_beds;
	}
	
	public int get_beds_in_use() {
		return beds.bedsInUse;
	}
		
	

}


