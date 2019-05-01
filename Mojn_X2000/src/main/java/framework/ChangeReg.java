package framework;
//
import java.util.HashSet;

import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.Patient;
import framework.person.Staff;

public class ChangeReg {
	
	// Adding database connection
	private Database DB = Database.getInstance(Database.DEFAULT);
	
	// Method has been overloaded to accept a person and the only instance of our hospital,
	// such that a person can be added to the overall organization. 
	protected void add(Hospital h, Staff p) {	
		HashSet<Staff> allStaffSet = h.getStaffSet();
		allStaffSet.add(p);
		h.setAllStaff(allStaffSet);
	}
	
	protected void add(Hospital h, Patient p) {
		HashSet<Patient> allPatientSet = h.getAllPatientSet();
		allPatientSet.add(p);
		h.setAllPatientSet(allPatientSet);
//		DB.writePatient(p);
		
	}
	
	protected void add(Hospital h, Department d) {
		HashSet<Department> departSet = h.getDepartSet();
		departSet.add(d);
		h.setDepartSet(departSet);
	}
	
	protected void remove(Hospital h, Department d) {
		HashSet<Department> departSet = h.getDepartSet();
		departSet.remove(d);
		h.setDepartSet(departSet);
	}
	
	protected void add(Department d, Staff s) {
		HashSet<Person> staffSet = d.getStaff();
		staffSet.add(s);
		d.setStaff(staffSet);
	}
	
	protected void remove(Department d, Staff s) {
		HashSet<Person> staffSet = d.getStaff();
		staffSet.remove(s);
		d.setStaff(staffSet);
	}
	
	public void add(Department d, Patient p) {
		HashSet<Person> patientSet = d.getPatient();
		p.setDepartment(d.getName());
		patientSet.add(p);
		d.setPatient(patientSet);
		
		if (d instanceof InPatientDepart) {
			((InPatientDepart) d).beds.AllocateBed(p);
		}
		else if(d instanceof OutPatientDepart) {
			((OutPatientDepart) d).EnQueue(p,p.getTriage());
		}
	}
	
	protected void add(Department d, Patient p,int triageLevel) {
		HashSet<Person> patientSet = d.getPatient();

		if(d instanceof OutPatientDepart) {
			OutPatientDepart OutD = (OutPatientDepart)d;
			OutD.EnQueue(p,triageLevel);
			patientSet.add(p);
			d.setPatient(patientSet);
		}
		else {System.err.println("Only OutPatient Departments have triage levels");}
	}
	
	 public void remove(Department d, Patient p) {
		HashSet<Person> patientSet = d.getPatient();
		patientSet.remove(p);
		d.setPatient(patientSet);
	}
}