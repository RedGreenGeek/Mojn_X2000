package framework.Departments;

import java.util.*;

import framework.Department;
import framework.Person;

public abstract class HCDepart extends Department{	
	protected HashSet<Person> patientSet = new HashSet<Person>();
	
	public HashSet<Person> getPatient(){
		return patientSet;
	}
	
	protected void setPatient(HashSet<Person> patientSet) {
		this.patientSet = patientSet;
	}
	
}
