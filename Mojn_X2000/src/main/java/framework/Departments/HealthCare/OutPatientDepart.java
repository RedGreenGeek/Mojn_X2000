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

public class OutPatientDepart extends HCDepart {
	
	class Pair implements Comparable<Pair> {
	      Person P;
	      int triageLevel;
	      
	      public Pair(Person P, int triageLevel) {
	        this.P = P;
	        this.triageLevel = triageLevel;
	      }
	      
	      
	      public int compareTo(Pair pair) {
	    	  if (pair.triageLevel<this.triageLevel) {
	    		  return -1;
	    	  }
	    	  else if (pair.triageLevel>this.triageLevel) {
	    		  return 1;
	    	  }
	    	  else if (pair.P.equals(this.P)) {
	    		  return 0;
	    	  }
	    	  else {
	    		  return -1;
	    	  }
	      }
	      
	      public Person getPatient() {
	        return this.P;
	      }
	}

	private PriorityQueue<Pair> queue;
	
	public void removeFromQueue(Patient p) {
		queue.remove(new Pair(p,p.getTriage()));
	}
	
	public OutPatientDepart(String departName, HashSet<Person> staffSet, HashSet<Person> patientSet) {
		super.setName(departName);
		super.setStaff(staffSet); 
		super.setPatient(patientSet);
		this.queue = new PriorityQueue<Pair>(1);
	}
	
	public OutPatientDepart(String departName) {
		super.setName(departName);
		this.queue = new PriorityQueue<Pair>(1);
	}
	
	public void EnQueue(Person P, int triageLevel) {
		Pair p = new Pair(P,triageLevel);
		this.queue.add(p);
		((Patient) P).setTriage(triageLevel);
	}
	
	public void EnQueue(Person P) {
		this.EnQueue(P,1);
	}
	
	public Person DeQueue() {
		if (!queue.isEmpty()) {
			Person p = this.queue.poll().getPatient();
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
}