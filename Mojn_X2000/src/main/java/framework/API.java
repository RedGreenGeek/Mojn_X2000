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
	
	/* _____________ PATIENT REGISTRATION for M1 ______________ */
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

	
	/* _____________ STAFF REGISTRATION for M2 ______________ */
	
	public String registerStaff (String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
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
	
	public String assignStaffDepartment(String departmentName, String staffID, String firstName, String lastName, String birthday, String email) {
		LinkedList<Person> staffRes = searcher.staffSearch(staffID, firstName, lastName, birthday, email);
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		
		if (staffRes.size()!=1 || departmentRes.size()!=1) {
			return "Warning, invalid person info or department name";
		}
		
		R.remove(searcher.departmentSearch(staffRes.getFirst().getDepartment()).getFirst(),(Staff) staffRes.getFirst());
		R.add(departmentRes.getFirst(), (Staff) staffRes.getFirst());
		staffRes.getFirst().setDepartment(departmentName);
		
		return "Staff added successfully to department";
	}
	
	
	public String changeStaff (String StaffID ,String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		
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

	public String staffSearcher(String staffID, String firstName, String lastName, String birthday, String email) {

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
	
	/* _____________ HEALTH FACILITY MANAGMENT for M3 ______________ */
	
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
	
	public static String movePatientDepart(String ID, String depart) {
		Patient p;
		String return_message;
		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient wasn't moved";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}

		return_message = discharge(ID);
		if (return_message.equals("The department isn't uniqe") || return_message.equals("The patient ID's isn't uniqe")) {
			return "The patient wasn't moved";
		}
		
		String[] bday = p.getBirthday().split("-");
		int day = Integer.parseInt(bday[0]);
		int month = Integer.parseInt(bday[1]);
		int year = Integer.parseInt(bday[2]);
		return_message = patientAdmission(depart, p.getFirstName(), p.getLastName(), p.getAdress(), p.getTribe(), day, month, year);
		if (return_message.equals("The department specification is ambigious") || return_message.equals("Unsuccesful registration cause to invalid patient data!")) {
			return "The patient wasn't moved";
		} else {return "The patient was moved succesfully to" + depart;}
	}
	
	public static String movePatientBed(String ID, String newBed, String newDepart) {
		InPatientDepart newDepartment;
		InPatientDepart oldDepartment;
		Patient p;
		String returnmessage;
		int bedNo;
		try {
			bedNo = Integer.parseInt(newBed);
		} catch (Exception e) {return "The bed specification wasn't an integer";}
		// Should not be able to give an error message.
		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient wasn't moved cause to invalid ID";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}
		// Should not be able to give an error message.
		if (searcher.departmentSearch(p.getDepartment()).size() != 1) {
			return "The department isn't uniqe";
		}
		// This should also not could give an error message
		if (!(searcher.departmentSearch(p.getDepartment()).getFirst() instanceof framework.Departments.HealthCare.InPatientDepart)) {
			return "The department isn't an indepartment";
		}
		else {oldDepartment = (InPatientDepart) searcher.departmentSearch(p.getDepartment()).peek();}
		
		// If a new department is specified
		if (!newDepart.equals("")) {
			if (searcher.departmentSearch(newDepart).size() != 1) {
				return "The new department matches several or no departments";
			}
			if (!(searcher.departmentSearch(newDepart).getFirst() instanceof framework.Departments.HealthCare.InPatientDepart)) {
				return "The department isn't an indepartment";
				
			}
			else {
				newDepartment = (InPatientDepart) searcher.departmentSearch(newDepart).getFirst();
				oldDepartment.beds.Discharge(p);
				if (newDepartment.beds.getMaxBeds() < bedNo) {
					return "The bed wasn't free";
				}
				returnmessage = newDepartment.beds.AllocateBed(p, bedNo);
				if (returnmessage.equals("Ok")) {
					return "The patient was moved succesfully";
				} else {return "The bed wasn't free";}
			}
		} 
		// If no new department is specified wee assume it's an internal bed move
		else {
			oldDepartment.beds.Discharge(p);
			if (oldDepartment.beds.getMaxBeds() < bedNo) {
				return "The bed wasn't free";
			}
			returnmessage = oldDepartment.beds.AllocateBed(p, bedNo);	
			if (returnmessage.equals("Ok")) {
				return "The patient was moved succesfully";
			} else {return "The bed wasn't free";}
		}
	}

	
	
	/* _____________ PATIENT WAITING O3 ______________ */
	public LinkedList<String> getQueue(String departmentName) {
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		OutPatientDepart outDepart;
		LinkedList<String> res = new LinkedList<String>();
		
		if (departmentRes.size() != 1) {
			res.add("Warning, could not retrieve queue of given department.");
			return res;
		}
		try {
			outDepart = (OutPatientDepart) departmentRes.getFirst();
		} 
		catch (ClassCastException e) {
			res.add("Warning, could not retrieve queue of given department.");
			return res;
		}
		ArrayList<Person> queue = outDepart.PrintQueue();
		
		for (int i = 0; i<queue.size(); i++) {
			res.add(queue.get(i).toString());
		}

		return res;
	}

	public String getNextInQueue(String departmentName) {
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		OutPatientDepart outDepart;
		
		if (departmentRes.size() != 1) {
			return "Warning, could not retrieve next in line.";
		}
		try {
			outDepart = (OutPatientDepart) departmentRes.getFirst();
		} 
		catch (ClassCastException e) {
			return "Warning, could not retrieve next in line.";
		}
		Person next = outDepart.DeQueue();
		if (next == null) {
			return "Warning, could not retrieve next in line.";
		}
		
		return outDepart.DeQueue().toString();
	}
}