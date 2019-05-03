package framework.Departments.HealthCare;

import java.util.HashSet;
import java.util.LinkedList;


import framework.Person;
import framework.Departments.HCDepart;
import framework.person.Patient;

public class OutPatientDepart extends HCDepart {
	LinkedList<Patient> queue;
	
	public void removeFromQueue(Patient p) {
		queue.remove(p);
	}
	
	public OutPatientDepart(String departName, HashSet<Person> staffSet, HashSet<Person> patientSet) {
		super.setName(departName);
		super.setStaff(staffSet); 
		super.setPatient(patientSet);
		this.queue = new LinkedList<Patient>();
	}
	
	public OutPatientDepart(String departName) {
		super.setName(departName);
		this.queue = new LinkedList<Patient>();
	}
	
	public void EnQueue(Person P, int triageLevel) {
		Patient p = (Patient) P;
		p.setTriage(triageLevel);
		
		HashSet<Person> patientSet = this.getPatient();
		patientSet.add(p);
		this.setPatient(patientSet);
		
		boolean added = false;
		for (int i=0; i<queue.size(); i++) {
			if (triageLevel <= queue.get(i).getTriage()) {
				this.queue.add(i, p);
				added = true;
				break;
			}
		}
		if (!added) {
			this.queue.addLast(p);
		}
	}
	
	public void EnQueue(Person P) {
		this.EnQueue(P,1);
	}
	
	public Person DeQueue() {
		if (!queue.isEmpty()) {
			Person p = this.queue.removeLast();
			HashSet<Person> patientSet = super.getPatient();
			patientSet.remove(p);
			super.setPatient(patientSet);
			((Patient) p).setTriage(null);
			return p;
		}
		return null;
	}
	
	public String PrintQueue() {
		if (this.queue.isEmpty()) {
			return "The queue is empty!";
		}
		@SuppressWarnings("unchecked")
		LinkedList<Patient> clone = (LinkedList<Patient>) this.queue.clone();
		
		String res = "ID\tDepartment\tSurname\tName\tBedNo/Triage";
		while (!clone.isEmpty()) {
			res += "\n"+clone.removeLast().toString();
		}
		return res;
	}
}