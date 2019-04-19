package framework;

import java.util.*;

import framework.Departments.AdminDepart;
import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.*;
import framework.person.staff.*;

public class API {
	private static Hospital h;
	private static Searcher searcher;
	private static HashSet<Person> totalSet = new HashSet<Person>();
	private static API instance;
	private static ChangeReg R;
	
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
		R = new ChangeReg();
		
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
		R.add(h, D1_in);
		R.add(h, N1_in);
		
		
		//------
		Patient P1_out = new Patient("Søren","Sørensen","Ventre","Hellerup",24,9,97,true,"Cardio");
		Patient P2_out = new Patient("Lars","Larsen","Jysk","Nordvestjylland",20,12,1950,true,"Cardio");
		Doctor D1_out = new Doctor("Lars","Løkke","Ventre","Græsted",01,01,1950,"Cardio");
		Nurse N1_out = new Nurse("Helle","Thorning","Gucci","Herlev",02,02,1960,"Cardio");
		
		R.add(Out, P1_out);
		R.add(Out, P2_out);
		R.add(Out, D1_out);
		R.add(Out, N1_out);
		R.add(h, D1_out);
		R.add(h, N1_out);
		
		//------
		ICTOfficer ICTOf = new ICTOfficer("Jens","Hansen","Norway","Indian",29,2,1996,"IT");
		Clerk clerk = new Clerk("Mads","hansen","Uganda","Black-rocks Clan",23,4,2000,"IT");
		
		R.add(A,ICTOf);
		R.add(A, clerk);
		R.add(h,ICTOf);
		R.add(h, clerk);
		
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
	
	/* _____________ STAFF REGISTRATION for M2 ______________ */
	
	public static String registerStaff (String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		
		Staff p;
		
		if (Person.isValidPersonData(firstName, lastName, day, month, year, adress, tribe, true)) {
			
			if (jobtype.equals("Clerk")) {
				p = new Clerk(firstName, lastName, adress, tribe, day, month ,year, null);
			}
			
			else if (jobtype.equals("Nurse")) {
				p = new Nurse(firstName, lastName, adress, tribe, day, month ,year, null);
			}
			
			else if (jobtype.equals("Doctor")) {
				p = new Doctor(firstName, lastName, adress, tribe, day, month ,year, null);
			}
			
			else if (jobtype.equals("ICTOfficer")) {
				p = new ICTOfficer(firstName, lastName, adress, tribe, day, month ,year, null);
			}
			
			else {
				return "Unsuccesful registration!";
			}
			
			R.add(h, p);
			
			return "The " + jobtype + " has been registered succesfully!";
		
		}
		
		else {
			
			return "Unsuccesful registration!";
			
		}
		
		
	}
	
	public static String changeStaff (String StaffID ,String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		
		if (searcher.staffSearch(StaffID, "", "", "", "").size() == 0) {
			return "The ID does not match an employee!";
		}
		Staff person = (Staff) searcher.staffSearch(StaffID, "", "", "", "").get(0);
		if (person==null) {
			return "The ID does not match an employee!";
		}
		if (firstName == "") {
			firstName = person.getFirstName();
		}
		if (lastName == "") {
			lastName = person.getLastName();
		}
		if (adress == "") {
			adress = person.getAdress();
		}
		if (tribe == "") {
			tribe = person.getTribe();
		}
		if (day == 0 || month == 0 || year == 0) {
			String[] birthday = person.getBirthday().split("-");
			day = Integer.parseInt(birthday[0]);
			month = Integer.parseInt(birthday[1]);
			year = Integer.parseInt(birthday[2]);
		}
		
		// special: Job type is wanted changed
		if (jobtype != "") {
			if(jobtype.equals("Nurse") ||jobtype.equals("Doctor") ||jobtype.equals("Clerk") ||jobtype.equals("ICTOfficer")) {
			String d = person.getDepartment();
			Department dd = searcher.departmentSearch(d).peek();
			// removed from the specific department
			R.remove(dd, person);
			// removed from the overall set
			h.getAllStaff("Overall").remove(person);
			// registered as with new job type.
			String message = registerStaff(jobtype, firstName, lastName, adress, tribe, day, month ,year);
			return message;} else {return "Invalid job type!";}
		}
		else {
			if (Person.isValidPersonData(firstName, lastName, day, month, year, adress, tribe, true)) {
				person.setFirstName(firstName);
				person.setLastName(lastName);
				person.setBirthDay(day, month, year);
				person.setAdress(adress);
				person.setTribe(tribe);
				return "Staff information has been changed successfully!";
			}
			else {
				return "Illegal changes to patient. Please check that the information is correct!";
			}
		}
		
	}

	public static String staffSearcher(String staffID, String firstName, String lastName, String birthday, String email) {

		LinkedList<Person> persons = searcher.staffSearch(staffID,firstName, lastName, birthday, email);
		String[] list = new String[persons.size()+1];
		list[0] = "First name | Last name | Address | Birthday |  ID  | Job type ";
		Staff s;
		String message = "";
		for (int i = 0; i<persons.size(); i++) {
			s = (Staff) persons.get(i);
			list[i+1] = s.getFirstName() + " | " + s.getLastName() + " | " + s.getAdress() + " | " + s.getBirthday() + " | " + s.getID() + " | " + s.getJobType();
		}
		for (int i = 0; i<list.length; i++) {
			message = message + list[i] + "\n";
		}
		if (message.equals(list[0] + "\n")) {
			return "No match to search parameters!";
		} else {return message; }
		
		
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
	
	public static String patientSearcher(String patientID, String firstName, String lastName, String birthday) {
		
		LinkedList<Person> persons = searcher.patientSearch(patientID, firstName, lastName, birthday);
		String[] list = new String[persons.size()+1];
		list[0] = "First name | Last name | Address | Birthday |  ID ";
		Patient s;
		String message = "";
		for (int i = 0; i<persons.size(); i++) {
			s = (Patient) persons.get(i);
			list[i+1] = s.getFirstName() + " | " + s.getLastName() + " | " + s.getAdress() + " | " + s.getBirthday() + " | " + s.getID();
		}
		for (int i = 0; i<list.length; i++) {
			message = message + list[i] + "\n";
		}
		if (message.equals(list[0] + "\n")) {
			return "No match to search parameters!";
		} else {return message; }
		
		
	}
	
	
	/* _____________ PATIENT ADMISSION for M4 ______________ */
	public static String patientAdmission(String department, String firstName, String lastName, String adress, String tribe, int day, int month, int year) {
		Patient p;
		Department depart;
		if (searcher.departmentSearch(department).size() != 1) {
			return "The department specification is ambigious";
		}else {
			depart = searcher.departmentSearch(department).peek();
		}
		
		
		if (Person.isValidPersonData(firstName, lastName, day, month, year, adress, tribe, true)) {
			
			p = new Patient(firstName, lastName, adress, tribe, day, month ,year, true, department);
			R.add(depart, p);
			return "The patient has been registered succesfully to " + department +  "!";
		
		}
		
		else {
			
			return "Unsuccesful registration cause to invalid patient data!";
			
		}
	}
	
	// The input to this function should be specified in the gui so when
	// I search for the patient and click remove this function is given the patient ID
	public static String discharge(String ID) {
		Patient p;
		Department depart;
		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient ID's isn't uniqe";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}
		
		if (searcher.departmentSearch(p.getDepartment()).size() != 1) {
			return "The department isn't uniqe";
		} else {depart = searcher.departmentSearch(p.getDepartment()).peek();}
		
		R.remove(depart, p);
		
		return "The patient " + p.getFirstName() + " " + p.getLastName() + ", " + ID + ", has been removed succesfully from " + depart.getName();
	}
	
	
}









