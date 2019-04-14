package framework;

import java.util.*;

import framework.Departments.AdminDepart;
import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.*;
import framework.person.staff.*;

public class API {
	Hospital h;
	private static Searcher searcher;
	private static HashSet<Person> totalSet = new HashSet<Person>();
	private static API instance;
	
	public static synchronized API getInstance() {
		if (instance==null) {
			instance = new API();
			return instance;
		}
		else {
			return instance;
		}
	}
	
	private API (){
		//Loads hospital in from database
		ChangeReg R = new ChangeReg();
		
		//------
		h = new Hospital();
		searcher = new Searcher(h);
		
		//------
		InPatientDepart In = new InPatientDepart("ER",new HashSet<Person>(),new HashSet<Person>(),7);
		InPatientDepart In2 = new InPatientDepart("Pediatric",new HashSet<Person>(),new HashSet<Person>(),2);
		OutPatientDepart Out = new OutPatientDepart("Cardio",new HashSet<Person>(),new HashSet<Person>());
		AdminDepart A = new AdminDepart("IT",new HashSet<Person>());
		
		R.add(h, In);
		R.add(h, In2);
		R.add(h, Out);
		R.add(h, A);
		
		
		//------
		Patient P1_in = new Patient("Jens","Jensen","Zulu","Jagtvej 69",24,9,97,true,"ER");
		Patient P2_in = new Patient("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,true,"ER");
		Doctor D1_in = new Doctor("Svend","Nielsen","Dansk","Doktorvej",01,01,1901,"ER");
		Nurse N1_in = new Nurse("Jonna","Nielsen","Tysk","Ikke-doktorvej",02,02,1902,"ER");
		
		R.add(In, P1_in);
		R.add(In, P2_in);
		R.add(In, D1_in);
		R.add(In, N1_in);
		
		
		//------
		Patient P1_out = new Patient("Søren","Sørensen","Ventre","Hellerup",24,9,97,true,"Cardio");
		Patient P2_out = new Patient("Lars","Larsen","Jysk","Nordvestjylland",20,12,1950,true,"Cardio");
		Doctor D1_out = new Doctor("Lars","Løkke","Ventre","Græsted",01,01,1950,"Cardio");
		Nurse N1_out = new Nurse("Helle","Thorning","Gucci","Herlev",02,02,1960,"Cardio");
		
		R.add(Out, P1_out);
		R.add(Out, P2_out);
		R.add(Out, D1_out);
		R.add(Out, N1_out);
		
		//------
		ICTOfficer ICTOf = new ICTOfficer("Jens","Hansen","Norway","Indian",29,2,1996,"IT");
		Clerk clerk = new Clerk("Mads","hansen","Uganda","Black-rocks Clan",23,4,2000,"IT");
		
		R.add(A,ICTOf);
		R.add(A, clerk);
		
		//------
		Patient P1_in2 = new Patient("Jens","Jensen","Zulu","Jagtvej 69",24,9,97,true,"Pediatric");
		Patient P2_in2 = new Patient("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,true,"Pediatric");
		Doctor D1_in2 = new Doctor("Svend","Nielsen","Dansk","Doktorvej",01,01,1901,"Pediatric");
		Nurse N1_in2 = new Nurse("Jonna","Nielsen","Tysk","Ikke-doktorvej",02,02,1902,"Pediatric");
		
		R.add(In2, P1_in2);
		R.add(In2, P2_in2);
		R.add(In2, D1_in2);
		R.add(In2, N1_in2);
		
		In2.beds.AllocateBed(P1_in2);
		In2.beds.AllocateBed(P2_in2);
		
		System.out.println(In.beds.getBedsAvailable());
	}
	
	
	public static String registerPatient(String firstName, String lastName, String tribe, String address, int day, int month,
			int year, boolean alive) {
		if (Person.isValidPersonData(firstName, lastName, day, month, year, address, tribe, alive)) {
			totalSet.add(new Patient(firstName,lastName,tribe,address,day,month,year,alive,null));
			return "Patient registered succesfully.";
		}
		else {
			return "Additional information is needed.";
		}
	}
	
	public static String changePatient(String ID, String firstName, String lastName, String tribe, String address, int day, int month, int year, boolean alive) {
		Patient p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();
		if (p==null) {
			return "No patient with that ID found.";
		}
		else {
			if (Person.isValidPersonData(firstName, lastName, day, month, year, address, tribe, alive)) {
				p.setFirstName(firstName);
				p.setLastName(lastName);
				p.setBirthDay(day, month, year);
				p.setAdress(address);
				p.setTribe(tribe);
				p.setAlive(alive);
				return "Patient information has been changed successfully.";
			}
			else {
				return "Illegal information changes.";
			}
		}
	}

	public LinkedList<String> getDepartments() {
		LinkedList<Department> ds = new LinkedList<Department>(h.getDepartSet());
		LinkedList<String> dlist = new LinkedList<String>();
		while (!ds.isEmpty()) {
			dlist.add(ds.removeFirst().toString());
		}
		return dlist;
	}

	public LinkedList<String> getDeparmentStaff(String departmentName) {
		LinkedList<Department> resList = searcher.departmentSearch(departmentName);
		LinkedList<String> res = new LinkedList<String>();
		if (resList.size()==1) {
			LinkedList<Person> sList = new LinkedList<Person>(resList.removeFirst().getStaff());
			while (!sList.isEmpty()) {
				res.add(sList.removeFirst().toString());
			}
		}
		else {
			res.add("No or multiple department(s) match your search criterion");
		}
		return res;
	}

	public String allocateToBed(String departmentName, String patientID) {
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		LinkedList<Person> patientRes = searcher.patientSearch(patientID,"","","");
		if (departmentRes.size()!=1) {
			return "No or multiple department(s) match your search criterion";
		}
		if (patientRes.size()!=1) {
			return ("No patient with given ID in department: " + departmentName); 
		}
		if (!(departmentRes.getFirst() instanceof InPatientDepart)) {
			return "Department "+departmentName+" does not contain any beds.";
		}
		InPatientDepart depart = (InPatientDepart) departmentRes.getFirst();
		
		if (!depart.beds.getBedsAvailable()) {
			return "No beds available in department: " + departmentName;
		}
		String bedNo = depart.beds.AllocateBed(patientRes.getFirst());
		return patientRes.getFirst().toString()+" was added to bed: "+bedNo;
	}

	public String bedsAvailable(String departmentName) {
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		if (departmentRes.size()!=1) {
			return "No or multiple department(s) match your search criterion";
		}
		if (!(departmentRes.getFirst() instanceof InPatientDepart)) {
			return "Department "+departmentName+" does not contain any beds.";
		}
		InPatientDepart depart = (InPatientDepart) departmentRes.getFirst();
		
		if (!depart.beds.getBedsAvailable()) {
			return "No beds available in department: " + departmentName;
		}
		return "Beds available in department: " + departmentName;
	}
	
	
	public String bedsInUse(String departmentName) {
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		if (departmentRes.size()!=1) {
			return "No or multiple department(s) match your search criterion";
		}
		if (!(departmentRes.getFirst() instanceof InPatientDepart)) {
			return "Department "+departmentName+" does not contain any beds.";
		}
		InPatientDepart depart = (InPatientDepart) departmentRes.getFirst();
		
		return "Department: " + departmentName+" currently have "+depart.beds.getBedsInUse()+" beds in use.";
	}
}
 // dsa






