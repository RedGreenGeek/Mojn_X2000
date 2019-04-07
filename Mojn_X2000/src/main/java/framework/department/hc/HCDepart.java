package framework.department.hc;

import java.util.HashSet;

import framework.department.Department;
import framework.person.Person;


public abstract class HCDepart extends Department{	
	protected HashSet<Person> patientSet = new HashSet<Person>();
	
	public HashSet<Person> getPatient(){
		return patientSet;
	}
	
	public void setPatient(HashSet<Person> patientSet) {
		this.patientSet = patientSet;
	}
}
