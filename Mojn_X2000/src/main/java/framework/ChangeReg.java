package framework;

import java.util.HashSet;

import framework.department.*;
import framework.department.hc.*;
import framework.person.*;

public class ChangeReg {
	
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
	
	protected void add(Department d, Patient p) {
		HashSet<Person> patientSet = d.getPatient();
		if (d instanceof InPatientDepart) {
			InPatientDepart IPD = (InPatientDepart)d;
			if (IPD.beds.getBedsAvailable()) {
				patientSet.add(p);
				IPD.beds.AllocateBed(p);
				
			}
			
		}
		else if(d instanceof OutPatientDepart) {
			OutPatientDepart OutD = (OutPatientDepart)d;
			OutD.EnQueue(p);
			patientSet.add(p);
			d.setPatient(patientSet);
			
		}
		else {System.err.println("Only Available to InPatient and OutPatient Departments.");}
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
	
	protected void remove(Department d, Patient p) {
		HashSet<Person> patientSet = d.getPatient();
		patientSet.remove(p);
		d.setPatient(patientSet);
	}

}
