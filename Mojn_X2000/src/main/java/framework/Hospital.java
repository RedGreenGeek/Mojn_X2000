package framework;

import java.util.*;

import framework.person.Patient;
import framework.person.Staff;
import framework.Department;

public class Hospital {
	
	private static Hospital single_instance = null; 
	
	//private HashSet<Department> departSet = new HashSet<Department>();
	protected static HashSet<Patient> patientRegistery = new HashSet<Patient>(); // temporary Patient registry,
	//to store patients in when not in the hospital, until we get a database.
	
	protected static HashSet<Staff> staffRegistery = new HashSet<Staff>(); // temporary Staff registry,
	//to store staff members in until we get a database.
	
	protected static HashSet<Department> departSet = new HashSet<Department>(); // temporary Staff registry,
	//to store staff members in until we get a database.
	
	
	
	private Hospital(){         
    }
	
    public static HashSet<Patient> getPatientRegistery() {
		return patientRegistery;
	}

	public static void setPatientRegistery(HashSet<Patient> patientRegistery) {
		Hospital.patientRegistery = patientRegistery;
	}
  
    public static HashSet<Staff> getStaffRegistery() {
		return staffRegistery;
	}

	public static void setStaffRegistery(HashSet<Staff> staffRegistery) {
		Hospital.staffRegistery = staffRegistery;
	}

	// static method to create instance of Singleton class, 
    //if the Hospital does not exist it is generated when called 
    public static Hospital getHospital() 
    { 
        if (single_instance == null) 
            single_instance = new Hospital(); 
  
        return single_instance; 
    }
//    protected void setDepartSet(HashSet<Department> departSet) {
//		this.departSet = departSet;
//	}
//	
	public static HashSet<Department> getDepartSet() {
		return departSet;
	}
//	
//	public HashSet<Person> getAllStaff(){
//		HashSet<Person> staffSet = new HashSet<Person>();
//		LinkedList<Department> departList = new LinkedList<Department>(this.departSet);
//		while (!departList.isEmpty()) {
//			staffSet.addAll(departList.removeFirst().getStaff());
//		}
//		return staffSet;
//	}
//	
//	public HashSet<Person> getAllPatient(){
//		HashSet<Person> patientSet = new HashSet<Person>();
//		LinkedList<Department> departList = new LinkedList<Department>(this.departSet);
//		while (!departList.isEmpty()) {
//			if (departList.getFirst() instanceof HCDepart) {
//				patientSet.addAll(departList.removeFirst().getPatient());
//			}
//			else {
//				departList.removeFirst();
//			}
//		}
//		return patientSet;
//	}
}