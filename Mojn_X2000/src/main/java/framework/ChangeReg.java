package framework;
//
import java.util.HashSet;

import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.Patient;
import framework.person.Staff;

public class ChangeReg {
	
	// Adding database connection
	
	Database DB = Database.getInstance();
	
	// Method has been overloaded to accept a person and the only instance of our hospital,
	// such that a person can be added to the overall organization. 
	public void add(Hospital h, Staff p) {	
		HashSet<Staff> allStaffSet = h.getAllStaff("With and without department");
		allStaffSet.add(p);
		h.setAllStaff(allStaffSet);
		
	}
	
	public void add(Hospital h, Patient p) {
		HashSet<Person> allPatientSet = h.getAllPatientSet();
//		System.out.println(allPatientSet);
		allPatientSet.add(p);
//		System.out.println(allPatientSet);
		h.setAllPatientSet(allPatientSet);
		String message = DB.writePatient(p);
		
		System.out.println(message);
		
	}
	
	public void add(Hospital h, Department d) {
		HashSet<Department> departSet = h.getDepartSet();
		departSet.add(d);
		h.setDepartSet(departSet);
	}
	public void remove(Hospital h, Department d) {
		HashSet<Department> departSet = h.getDepartSet();
		departSet.remove(d);
		h.setDepartSet(departSet);
	}
	
	public void add(Department d, Staff s) {
		HashSet<Person> staffSet = d.getStaff();
		staffSet.add(s);
		d.setStaff(staffSet);
	}
	public void remove(Department d, Staff s) {
		HashSet<Person> staffSet = d.getStaff();
		staffSet.remove(s);
		d.setStaff(staffSet);
	}
	
	public void add(Department d, Patient p) {
		HashSet<Person> patientSet = d.getPatient();
		if (d instanceof InPatientDepart) {
			InPatientDepart IPD = (InPatientDepart)d;
			if (IPD.beds.getBedsAvailable()) {
				patientSet.add(p);
				p.setDepartment(IPD.getName());
			}
		}
		else if(d instanceof OutPatientDepart) {
			OutPatientDepart OutD = (OutPatientDepart)d;
			OutD.EnQueue(p);
			patientSet.add(p);
			d.setPatient(patientSet);
			p.setDepartment(OutD.getName());
		}
		else {System.err.println("Only Available to InPatient and OutPatient Departments.");}
	}
	
	public void add(Department d, Patient p,int triageLevel) {
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