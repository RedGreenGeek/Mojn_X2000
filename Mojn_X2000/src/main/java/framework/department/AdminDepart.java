package framework.department;

import java.util.HashSet;

import framework.person.Person;

public class AdminDepart extends Department {
	public AdminDepart(String departName, HashSet<Person> staffSet) {
		super.setName(departName);
		super.setStaff(staffSet);
	}
	
	@Override
	public void setPatient(HashSet<Person> patientSet) {
		System.err.println("Warning, AdminDepartments cannot be assigned any patients.");
	}
}