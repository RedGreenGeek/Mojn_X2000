package framework;

import java.util.LinkedList;

import framework.person.Staff;

public class SearchEngine {
	
	protected LinkedList<Department> department(String departmentName, LinkedList<Department> ds){
		LinkedList<Department> res = new LinkedList<Department>();
		@SuppressWarnings("unchecked")
		LinkedList<Department> dList = (LinkedList<Department>) ds.clone();
		while (!dList.isEmpty()) {
			if (dList.getFirst().getName() != null && dList.getFirst().getName().toLowerCase().startsWith(departmentName.toLowerCase())){
				res.add(dList.removeFirst());
			}
			else {
				dList.removeFirst();
			}
		}

		return res;
	}
	
	protected LinkedList<Person> firstName(String firstName,LinkedList<Person> sList){
		LinkedList<Person> res = new LinkedList<Person>();
		while (!sList.isEmpty()) {
			if (sList.getFirst().getFirstName().toLowerCase().startsWith(firstName.toLowerCase())) {
				res.add(sList.removeFirst());
			}
			else {
				sList.removeFirst();
			}
		}
		return res;
	}
	
	protected LinkedList<Person> lastName(String lastName,LinkedList<Person> sList){
		LinkedList<Person> res = new LinkedList<Person>();
		while (!sList.isEmpty()) {
			if (sList.getFirst().getLastName().toLowerCase().startsWith(lastName.toLowerCase())) {
				res.add(sList.removeFirst());
			}
			else {
				sList.removeFirst();
			}
		}
		return res;
	}
	
	protected LinkedList<Person> birthday(String birthday,LinkedList<Person> sList){
		LinkedList<Person> res = new LinkedList<Person>();
		while (!sList.isEmpty()) {
			if (sList.getFirst().getBirthday().toLowerCase().startsWith(birthday.toLowerCase())) {
				res.add(sList.removeFirst());
			}
			else {
				sList.removeFirst();
			}
		}
		return res;
	}
	protected LinkedList<Person> email(String email, LinkedList<Person> sList){
		LinkedList<Person> res = new LinkedList<Person>();
		while (!sList.isEmpty()) {
			Staff s = (Staff) sList.getFirst();
			if (s.getEmail().toLowerCase().startsWith(email.toLowerCase())) {
				res.add(sList.removeFirst());
			}
			else {
				sList.removeFirst();
			}
		}
		return res;
	}

	protected LinkedList<Person> id(String id, LinkedList<Person> pList) {
		LinkedList<Person> res = new LinkedList<Person>();
		while (!pList.isEmpty()) {
			if (pList.getFirst().getID().equals(id)) {
				res.add(pList.removeFirst());
			}
			else {
				pList.removeFirst();
			}
		}
		return res;
	}
}