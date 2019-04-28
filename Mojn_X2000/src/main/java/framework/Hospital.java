package framework;

import java.util.*;

import framework.Departments.HCDepart;
import framework.person.Patient;
import framework.person.Staff;

public class Hospital {
	
	// Remember to override hashcode and equals
	
	private HashSet<Department> departSet = new HashSet<Department>();
	private HashSet<Patient> allPatientSet = new HashSet<Patient>();
	private HashSet<Staff> allStaffSet = new HashSet<Staff>();
	
	public Hospital() {
	}
	
	protected HashSet<Patient> getAllPatientSet() {
		return allPatientSet;
	}

	protected void setAllPatientSet(HashSet<Patient> allPatientSet) {
		this.allPatientSet = allPatientSet;
	}
	
	protected void setDepartSet(HashSet<Department> departSet) {
		this.departSet = departSet;
	}
	
	protected HashSet<Department> getDepartSet() {
		return this.departSet;
	}
	
	protected HashSet<Staff> getStaffSet(){
		return this.allStaffSet;
	}
	
	protected void setAllStaff(HashSet<Staff> allStaffSet){
		this.allStaffSet = allStaffSet;
	}
	
	protected HashSet<Person> getAllStaff(){
		HashSet<Person> staffSet = new HashSet<Person>();
		LinkedList<Department> departList = new LinkedList<Department>(this.departSet);
		while (!departList.isEmpty()) {
			staffSet.addAll(departList.removeFirst().getStaff());
		}
		return staffSet;
	}
	
	protected HashSet<Person> getAllAdmittedPatients(){
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