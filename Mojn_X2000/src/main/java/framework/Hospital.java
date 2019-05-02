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
	
	//Retrieve all patients registered at the hospital.
	public HashSet<Patient> getAllPatientSet() {
		return allPatientSet;
	}
	
	//Write changes to all patient registered at the hospital.
	protected void setAllPatientSet(HashSet<Patient> allPatientSet) {
		this.allPatientSet = allPatientSet;
	}
	
	//Make it possible to overwrite, for addind new departments.
	protected void setDepartSet(HashSet<Department> departSet) {
		this.departSet = departSet;
	}
	
	//Retrieve the departments in the hospital.
	protected HashSet<Department> getDepartSet() {
		return this.departSet;
	}
	
	//Retrieves the staff in the hospital.
	protected HashSet<Staff> getStaffSet(){
		return this.allStaffSet;
	}
	
	//Sets the staff in the hospital.
	protected void setAllStaff(HashSet<Staff> allStaffSet){
		this.allStaffSet = allStaffSet;
	}
	
	//Retrieve all staff member currently employed at a department.
	protected HashSet<Person> getAllStaff(){
		HashSet<Person> staffSet = new HashSet<Person>();
		LinkedList<Department> departList = new LinkedList<Department>(this.departSet);
		while (!departList.isEmpty()) {
			staffSet.addAll(departList.removeFirst().getStaff());
		}
		return staffSet;
	}
	
	//Retrieve all patient currently admitted to the hospital.
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