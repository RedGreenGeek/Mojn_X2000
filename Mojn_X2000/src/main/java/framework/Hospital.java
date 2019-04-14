package framework;

import java.util.*;

import framework.Departments.HCDepart;
import framework.person.Staff;

public class Hospital {
	private HashSet<Department> departSet = new HashSet<Department>();
	private HashSet<Person> allPatientSet = new HashSet<Person>();
	private HashSet<Staff> allStaffSet = new HashSet<Staff>();
	
	
	public Hospital() {
	}
	
	protected HashSet<Person> getAllPatientSet() {
		return allPatientSet;
	}

	protected void setAllPatientSet(HashSet<Person> allPatientSet) {
		this.allPatientSet = allPatientSet;
	}
	
	protected void setDepartSet(HashSet<Department> departSet) {
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
	
	public HashSet<Staff> getAllStaff(String s){
		
		return this.allStaffSet;
	}
	
	public void setAllStaff(HashSet<Staff> allStaffSet){
		
		this.allStaffSet = allStaffSet;
		
	}
	
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