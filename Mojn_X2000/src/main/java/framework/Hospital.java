package framework;

import java.util.*;

import framework.department.Department;
import framework.department.hc.HCDepart;
import framework.person.Patient;
import framework.person.Person;

public class Hospital {
	
	private HashSet<Department> departSet = new HashSet<Department>();
	protected static HashSet<Patient> patientRegistery = new HashSet<Patient>(); // temporary
	
	
	public Hospital() {
		
	}
	
	public Hospital(HashSet<Department> departSet) {
		this.departSet = departSet;
	}
	 
	public void setDepartSet(HashSet<Department> departSet) {
		this.departSet = departSet;
	}
	
	public HashSet<Department> getDepartSet() {
		return this.departSet;
	}
	
//	public Department getDepartment(String departName) {
//
//		for (Department i:this.departSet) {
//			if (i.departName == departName) {
//			return i;
//			}
//		}
//		throw new IllegalArgumentException("No such department");
//	}
	
	public HashSet<Person> getAllStaff(){
		HashSet<Person> staffSet = new HashSet<Person>();
		LinkedList<Department> departList = new LinkedList<Department>(this.departSet);
		while (!departList.isEmpty()) {
			staffSet.addAll(departList.removeFirst().getStaff());
		}
		return staffSet;
	}
	
	public HashSet<Person> getAllPatient(){
		HashSet<Person> patientSet = new HashSet<Person>();
		LinkedList<Department> departList = new LinkedList<Department>(this.departSet);
		while (!departList.isEmpty()) {
			if (departList.getFirst() instanceof HCDepart) {
				patientSet.addAll(departList.removeFirst().getPatient());
			}
			else {
				departList.removeFirst();
			}
		}
		return patientSet;
	}
}