package framework;

import java.util.*;

import framework.person.Patient;

public class Hospital {
	
	private static Hospital single_instance = null; 
	
	//private HashSet<Department> departSet = new HashSet<Department>();
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
    
    public static HashSet<Patient> getPatientRegistery() {
		return patientRegistery;
	}

	public static void setPatientRegistery(HashSet<Patient> patientRegistery) {
		Hospital.patientRegistery = patientRegistery;
	}
}