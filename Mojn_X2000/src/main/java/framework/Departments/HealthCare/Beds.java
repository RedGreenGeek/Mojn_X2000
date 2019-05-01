package framework.Departments.HealthCare;

import framework.Person;
import framework.person.Patient;

public class Beds {
	protected Person[] beds;
	protected int bedsInUse;
	
	public Beds(int maxBeds) {
		this.beds = new Person[maxBeds];
		this.bedsInUse = 0;
	}
	
	public String AllocateBed(Person patient) {
		String message = "-1";
		int i=0;
		for (Person p: beds) {
			i++;
			if (p == null) {
				p = patient;
				bedsInUse++;
				message = beds.toString();
				((Patient) patient).setBedLocation(i);				
				break;
			}
		}
		return message;
	}
	
	public String AllocateBed(Person patient, int bedNo) {
		int index = bedNo-1;
		System.out.println("Number of beds: " + beds.length);
		System.out.println("Bed number: " + index);
		 
		if (beds[index] == patient) {
			return "Same bed";
		}
		if (beds[index] == null) {
			beds[index] = patient;
			bedsInUse++;
			return "Ok";
		}
		else {return "Error";}
	}
	
	public void Discharge(Person patient) {
		for (Person p: beds) {
			if (p == patient) {
				p = null;
				bedsInUse--;
				break;
			}
		}
	}
	
	public void Discharge(int bedNo) {
		beds[bedNo] = null;
		bedsInUse--;
	}
	
	public int getMaxBeds() {
		return beds.length;
	}

	public int getBedsInUse() {
		return bedsInUse;
	}

	public void setBedsInUse(int bedsInUse) {
		this.bedsInUse = bedsInUse;
	}
	
	public boolean getBedsAvailable() {	 
		boolean Beds = bedsInUse<beds.length;
		return Beds;
	}
}
