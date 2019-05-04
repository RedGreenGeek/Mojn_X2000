package framework.Departments.HealthCare;

import framework.Person;
import framework.person.*;

public class Beds {
	protected Person[] beds;
	
	public Beds(int maxBeds) {
		this.beds = new Person[maxBeds];
	}

	public String AllocateBed(Person patient) {
	    String message = "-1";
	    Patient p1 = ((Patient) patient);
	    if (beds[p1.getBedLocation()-1].equals(p1)) {
	    	beds[p1.getBedLocation()-1] = null;
	    	p1.setBedLocation(null);
  	  	}
	    
	    for (int i=0; i<beds.length; i++) {
	    	if (beds[i] == null) {
	    		((Patient) patient).setBedLocation(i+1); 
	    		beds[i]=patient;
	    		return i+"";
	    	}	
	    }
	    return message;
	}
	
	public String AllocateBed(Person patient, int bedNo) {
		int index = bedNo-1;
		 
		if (patient.equals(beds[index])) {
			return "Same bed";
		}
		
		if (beds[index] == null) {
			beds[((Patient) patient).getBedLocation()-1] = null;
			beds[index] = patient;
			((Patient) patient).setBedLocation(bedNo); 
			return "Ok";
		}
		
		else {return "Error";}
	}
	
	public void Discharge(Person patient) {
		for (Person p: beds) {
			if (p == patient) {
				p = null;
				break;
			}
		}
	}
	
	public int getMaxBeds() {
		return beds.length;
	}

	public int getBedsInUse() {
		int inUse=0;
		for (int i=0; i<beds.length; i++) {
			if (beds[i]!=null) {
				inUse++;
			}
		}
		return inUse;
	}
	
	public boolean getBedsAvailable() {	 
		boolean Beds = this.getBedsInUse()<beds.length;
		return Beds;
	}
}
