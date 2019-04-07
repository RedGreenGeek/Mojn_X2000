package framework.person.staff;

import framework.person.Staff;

public class ICTOfficer extends Staff {
	public ICTOfficer(String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthDay(day, month, year);
		this.setTribe(tribe);
		this.setAdress(adress);
		this.setJobType("ITC Officer"); // It's very important that you first define job type, then ID and lastly email because they use each other
		this.setID("IT");
		this.setEmail();
	}
}
