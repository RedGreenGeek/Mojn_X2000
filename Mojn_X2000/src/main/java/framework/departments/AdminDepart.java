package framework.departments;
import framework.Department;
import framework.person.Person;

import java.util.*;

public class AdminDepart extends Department {
	public AdminDepart(String departName, HashSet<Person> staffSet) {
		super.setName(departName);
		super.setStaff(staffSet);
	}
	
//	@Override
//	protected void setPatient(HashSet<Person> patientSet) {
//		System.err.println("Warning, AdminDepartments cannot be assigned any patients.");
//	}
	
}
