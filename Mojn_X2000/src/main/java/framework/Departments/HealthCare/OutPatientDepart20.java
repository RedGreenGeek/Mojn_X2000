package framework.Departments.HealthCare;
import java.util.ArrayList;
//
import java.util.HashSet;
import java.util.PriorityQueue;

import framework.ChangeReg;
import framework.Department;
import framework.Person;
import framework.Departments.HCDepart;
import framework.person.Patient;
import framework.person.Staff;

public class OutPatientDepart20 extends HCDepart {
	
	class Pair implements Comparable<Pair> {
		
	      Patient P;
	      int triageLevel;
	      
	      public Pair(Patient P, int triageLevel) {
	        this.P = P;
	        this.triageLevel = P.getTriage();
	      }
	      

	      public int compareTo(Pair pair) {
	        return Integer.compare(pair.triageLevel,triageLevel);
	      }
	      
	      public Patient getPatient() {
	        return this.P;
	      }

	}

	public PriorityQueue<Pair> queue;
		
	public OutPatientDepart20(String departName, HashSet<Staff> staffSet, HashSet<Patient> patientSet) {
		super.setName(departName);
//		super.setStaff(staffSet); 
//		this.patientSet = patientSet;
		this.queue = new PriorityQueue<Pair>(1);
	}
	
	public OutPatientDepart20(String departName) {
		super.setName(departName);
		this.queue = new PriorityQueue<Pair>(1);
	}
	
	public void EnQueue(Patient P, int triageLevel) {
		Pair p = new Pair(P,triageLevel);
		this.queue.add(p);
		
	}
	public void EnQueue(Patient P) {
		Pair p = new Pair(P,1);
		this.queue.add(p);
		
	}
	
	public Person DeQueue() {
		if (!queue.isEmpty()) {
			ChangeReg r = new ChangeReg();
			Patient p = this.queue.poll().getPatient();
			r.remove((Department)this,(Patient)p);
			return p;
		}
		return null;
	}
	
	public ArrayList<Patient> PrintQueue() {
		java.util.Iterator<Pair> Q = this.queue.iterator();
		ArrayList<Patient> PList = new ArrayList<Patient>();
		while(Q.hasNext()) {
			PList.add(Q.next().getPatient());
			
		}
		
		return PList;
		
		
	}
}
