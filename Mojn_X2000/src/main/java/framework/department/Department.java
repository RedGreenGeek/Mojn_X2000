package framework.department;

import java.util.HashSet;

import framework.person.Person;

public abstract class Department {
	private HashSet<Person> staffSet;

	private String departName;
	
	protected  String getName(){
		return departName;
	}
	
	protected void setName(String departmentName) {
		this.departName = departmentName;
	}
	
	public HashSet<Person> getStaff() {
		return this.staffSet;
	}
	
	public void setStaff(HashSet<Person> staffSet) {
		this.staffSet = staffSet;
	}

	public HashSet<Person> getPatient(){
		System.err.println("Warning, only HCDepartments has patients.");
		return(new HashSet<Person>());
	}
	
	public void setPatient(HashSet<Person> patientSet) {}
}
