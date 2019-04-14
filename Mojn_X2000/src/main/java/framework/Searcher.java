package framework;

import java.util.LinkedList;

public class Searcher {
	static Searcher s;
	static Hospital h;
	private SearchEngine se = new SearchEngine();
	
	public Searcher(Hospital hos) {
		h = hos;
	}
	
	
	protected LinkedList<Department> departmentSearch(String departmentName){
		LinkedList<Department> dList = new LinkedList<Department>(h.getDepartSet());
		return se.department(departmentName, dList);
	}
	
	protected LinkedList<Person> patientSearch(String id, String firstName, String lastName, String birthday) {
		LinkedList<Person> pList = new LinkedList<Person>(h.getAllPatient());
		if (!id.equals("")) {
			pList = se.id(id, pList);
		}
		if (!birthday.equals("")) {
			pList = se.birthday(birthday, pList);
		}
		if (!lastName.equals("")) {
			pList = se.lastName(lastName, pList);
		}
		if (!firstName.equals("")) {
			pList = se.firstName(firstName, pList);
		}
		return pList;		
	}
	
	protected LinkedList<Person> staffSearch(String staffId, String firstName, String lastName, String birthday,String email) {
		LinkedList<Person> pList = new LinkedList<Person>(h.getAllStaff("With and without department"));
		if (!staffId.equals("")) {
			pList = se.id(staffId, pList);
		}
		if (!email.equals("")) {
			pList = se.email(email,pList);
		}
		if (!birthday.equals("")) {
			pList = se.birthday(birthday, pList);
		}
		if (!lastName.equals("")) {
			pList = se.lastName(lastName, pList);
		}
		if (!firstName.equals("")) {
			pList = se.firstName(firstName, pList);
		}
		return pList;		
	}
}
