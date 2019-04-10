package framework.Departments;
import java.util.*;

import framework.Department;
import framework.Person;

public class AdminDepart extends Department {
	public AdminDepart(String departName, HashSet<Person> staffSet) {
		super.setName(departName);
		super.setStaff(staffSet);
	}
	
	@Override
	protected void setPatient(HashSet<Person> patientSet) {
		System.err.println("Warning, AdminDepartments cannot be assigned any patients.");
	}
	
}
