package framework;

import java.util.*;

import framework.department.Department;
import framework.department.hc.HCDepart;
import framework.person.Patient;
import framework.person.Person;

public class Hospital {
	
	private static Hospital single_instance = null; 
	
	private HashSet<Department> departSet = new HashSet<Department>();
	protected static HashSet<Patient> patientRegistery = new HashSet<Patient>(); // temporary Patient registry,
	//to store patients in when not in the hospital, until we get a database.
	
    private Hospital(){         
    } 
  
    // static method to create instance of Singleton class, 
    //if the Hospital does not exist it is generated when called 
    public static Hospital getHospital() 
    { 
        if (single_instance == null) 
            single_instance = new Hospital(); 
  
        return single_instance; 
    }
    //<<<<<<<<<<<<<<< OK TO DELETE BELOW?>>>>>>>>>>>>>
//	public Hospital() {
//		
//	}
//	
//	public Hospital(HashSet<Department> departSet) {
//		this.departSet = departSet;
//	}
	 
	public HashSet<Patient> getPatientRegistery() {
		return patientRegistery;
	}

	public void setPatientRegistery(Patient p) {
		this.patientRegistery.add(p);
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