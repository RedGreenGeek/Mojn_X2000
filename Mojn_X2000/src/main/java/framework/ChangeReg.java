package framework;
//
import java.util.HashSet;

import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.Patient;
import framework.person.Staff;

public class ChangeReg {
	// Adding database connection
	private Database DB;
	
	public ChangeReg (Database db) {
		this.DB = db;
	}
	
	
	// Method has been overloaded to accept a person and the only instance of our hospital,
	// such that a person can be added to the overall organization. 
	protected void add(Hospital h, Staff p) {	
		HashSet<Staff> allStaffSet = h.getStaffSet();
		allStaffSet.add(p);
		h.setAllStaff(allStaffSet);
		
		//Adding staff to database
		DB.writeStaff(p);
		
	}
	
	protected void add(Hospital h, Patient p) {
		HashSet<Patient> allPatientSet = h.getAllPatientSet();
		allPatientSet.add(p);
		h.setAllPatientSet(allPatientSet);
		
		//Adding patient to database
		DB.writePatient(p);
		
	}
	
	protected void add(Hospital h, Department d) {
		HashSet<Department> departSet = h.getDepartSet();
		departSet.add(d);
		h.setDepartSet(departSet);
		
		// Writing department to database
		DB.writeDepartment(d);
		
	}
	
	protected void remove(Hospital h, Department d) {
		HashSet<Department> departSet = h.getDepartSet();
		departSet.remove(d);
		h.setDepartSet(departSet);
		
		//Removing department from database
		DB.deleteDepartment(d);
	}
	
	protected void add(Department d, Staff s) {
		HashSet<Person> staffSet = d.getStaff();
		staffSet.add(s);
		d.setStaff(staffSet);
		
		//Writing changes to database
		DB.writeStaff(s);
	}
	
	protected void remove(Department d, Staff s) {
		HashSet<Person> staffSet = d.getStaff();
		staffSet.remove(s);
		d.setStaff(staffSet);
		
		//Changing attribute department for the specific staff and writing changes to database
		s.setDepartment(null);
		DB.writeStaff(s);
		
	}
	
	public void add(Department d, Patient p) {
		
	  HashSet<Person> patientSet = d.getPatient();
	  p.setDepartment(d.getName());
	  patientSet.add(p);
	  d.setPatient(patientSet);
	  
	  if (d instanceof InPatientDepart) {
	   ((InPatientDepart) d).beds.AllocateBed(p);
	   
	   // Deleting patient to ensure that the patients triage level is set to null in case he/she is moved from an out patient department. 
	   DB.deletePatient(p);
	   DB.writePatient(p);
	   
	   // Writing department to update beds in use. Will update all values. 
	   DB.writeDepartment(d);

	  }
	  else if(d instanceof OutPatientDepart) {
	   ((OutPatientDepart) d).EnQueue(p,p.getTriage());
	   
	   // Deleting patient to ensure that the bed number is set to null in case the patient was moved from an in patient department. 
	   DB.deletePatient(p);
	   // Writing patient to get all values updated. 
	   DB.writePatient(p);
	   
	   
	  }
	 
	}
	
	protected void add(Department d, Patient p, int triage) {
		HashSet<Person> patientSet = d.getPatient();

		if(d instanceof OutPatientDepart) {
			OutPatientDepart OutD = (OutPatientDepart)d;
			OutD.EnQueue(p,triage);
			patientSet.add(p);
			d.setPatient(patientSet);
			p.setBedLocation(null);
			
			// Deleting patient to update columns that should be null
			DB.deletePatient(p);
			DB.writePatient(p);
			
		}
		else {System.err.println("Only OutPatient Departments have triage levels");}
	}
	
	 public void remove(Department d, Patient p) {
		// Setting department, triage and BedNo to null
		if (d instanceof InPatientDepart) {
			((InPatientDepart) d).beds.Discharge(p);
		}
		if (d instanceof OutPatientDepart) {
			((OutPatientDepart) d).removeFromQueue(p);
		}
		p.setBedLocation(null);
		p.setTriage(null);
		p.setDepartment(null);
		HashSet<Person> patientSet = d.getPatient();
		patientSet.remove(p);
		d.setPatient(patientSet);
		
		
		// To insert the value null in department_name column, delete the patient and write the patient again
		DB.deletePatient(p);
		DB.writePatient(p);
	}
}