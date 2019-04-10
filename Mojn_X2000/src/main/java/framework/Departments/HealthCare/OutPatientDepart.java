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
	        return Integer.compare(pair.triageLevel,triageLevel);
	      }
	      
	      public Person getID() {
	        return this.P;
	      }

	    }

	
	public int triageLevel;
	public Patient P;
	public PriorityQueue<Pair> queue;
	
	
	
	
	public OutPatientDepart(String departName, HashSet<Person> staffSet, HashSet<Person> patientSet) {
		super.setName(departName);
		super.setStaff(staffSet); 
		this.patientSet = patientSet;
		this.queue = new PriorityQueue<Pair>(1);
	}
	
	public void EnQueue(Person P, int triageLevel) {
		Pair p = new Pair(P,triageLevel);
		this.queue.add(p);
		
	}
	public void EnQueue(Person P) {
		Pair p = new Pair(P,1);
		this.queue.add(p);
		
	}
	public Person DeQueue() {
		ChangeReg r = new ChangeReg();
		Person p = this.queue.poll().getID();
		r.remove((Department)this,(Patient)p);
		return p;
		
		
	}
	public ArrayList<Person> PrintQueue() {
		java.util.Iterator<Pair> Q = this.queue.iterator();
		ArrayList<Person> PList = new ArrayList<Person>();
		while(Q.hasNext()) {
			PList.add(Q.next().getID());
			
		}
		
		return PList;
		
		
	}
}
