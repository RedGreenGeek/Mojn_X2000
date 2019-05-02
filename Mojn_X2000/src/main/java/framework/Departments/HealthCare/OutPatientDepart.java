package framework.Departments.HealthCare;
import java.util.ArrayList;
//

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import framework.ChangeReg;
import framework.Department;
import framework.Person;
import framework.Departments.HCDepart;
import framework.person.Patient;

public class OutPatientDepart extends HCDepart {
	private class Pair {
	      Person P;
	      int triageLevel;
	      
	      public Pair(Person P, int triageLevel) {
	        this.P = P;
	        this.triageLevel = triageLevel;
	      }
	      
	      public Person getPatient() {
	        return this.P;
	      }
	}

	private LinkedList<Pair> queue;
	
	
	public OutPatientDepart(String departName, HashSet<Person> staffSet, HashSet<Person> patientSet) {
		super.setName(departName);
		super.setStaff(staffSet); 
		super.setPatient(patientSet);
		this.queue = new LinkedList<Pair>();
	}
	
	public OutPatientDepart(String departName) {
		super.setName(departName);
		this.queue = new LinkedList<Pair>();
	}
	
	public void EnQueue(Person P, int triageLevel) {
		Pair p = new Pair(P,triageLevel);
		((Patient) P).setTriage(triageLevel);
		
		boolean added = false;
		for (int i = 0; i<this.queue.size(); i++) {
			if (this.queue.get(i)==null || (this.queue.get(i).triageLevel) <= (p.triageLevel)) {
				this.queue.add(i, p);
				added = true;
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
			Person p = this.queue.removeFirst().getPatient();
			HashSet<Person> patientSet = super.getPatient();
			patientSet.remove(p);
			super.setPatient(patientSet);
			return p;
		}
		return null;
	}
	
	public ArrayList<Person> PrintQueue() {
		java.util.Iterator<Pair> Q = this.queue.iterator();
		ArrayList<Person> PList = new ArrayList<Person>();
		while(Q.hasNext()) {
			PList.add(Q.next().getPatient());
		}
		return PList;
	}

	public void removeFromQueue(Patient p) {
		for (int i = 0; i<this.queue.size(); i++) {
			if (p.equals(this.queue.get(i).P)) {
				this.queue.remove(i);
			}
		}
	}
}