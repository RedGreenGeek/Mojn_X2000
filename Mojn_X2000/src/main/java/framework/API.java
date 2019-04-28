package framework;

import java.io.IOException;
import java.util.*;

import framework.Departments.AdminDepart;
import framework.Departments.HCDepart;
import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.*;
import framework.Password.*;

import framework.person.staff.*;

public class API {
	// INIT ATTRIBUTES  
	private static API instance;
	private Hospital h;
	private Searcher searcher;
	private HashSet<Person> totalSet = new HashSet<Person>();
	private ChangeReg R;
	private Database DB;
	
	public static synchronized API getInstance() {
		if (instance==null) {
			instance = new API();
		}
		return instance;
	}
	
	private API (){
		// CONNECTION TO DATABASE TO ENSURE CONNECTION
		DB = Database.getInstance();
		
		R = new ChangeReg();
		
		//LOAD ARTIFICIAL HOSPITAL
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
		Patient P1_in = new Patient("Jens","Jensen","Jagtvej 69","Zulu",24,9,97,true,"ER");
		Patient P2_in = new Patient("Hans","Hansen","Tagensvej 101","Masai",24,12,2000,true,"ER");
		Doctor D1_in = new Doctor("Svend","Nielsen","Doktorvej","Dansk",01,01,1901,"ER");
		Nurse N1_in = new Nurse("Jonna","Nielsen","Ikke-doktorvej","Tysk",02,02,1902,"ER");
		
		R.add(In, P1_in);
		R.add(In, P2_in);
		R.add(In, D1_in);
		R.add(In, N1_in);
		R.add(h, D1_in);
		R.add(h, N1_in);
		
		
		//------
		Password Pas = Password.getInstance();
		//
		
		Patient P1_out = new Patient("Søren","Sørensen","Hellerup","Ventre",24,9,97,true,"Cardio");
		Patient P2_out = new Patient("Lars","Larsen","Nordvestjylland","Jysk",20,12,1950,true,"Cardio");
		Doctor D1_out = new Doctor("Lars","Løkke","Græsted","Ventre",01,01,1950,"Cardio");
		Nurse N1_out = new Nurse("Helle","Thorning","Herlev","Gucci",02,02,1960,"Cardio");
		Pas.addPassToMap("asd", "N3");
		
		
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
		Patient P1_in2 = new Patient("Jens","Jensen","Jagtvej 69","Zulu",24,9,97,true,"Pediatric");
		Patient P2_in2 = new Patient("Hans","Hansen","Tagensvej 101","Masai",24,12,2000,true,"Pediatric");
		Doctor D1_in2 = new Doctor("Svend","Nielsen","Doktorvej","Dansk",01,01,1901,"Pediatric");
		Nurse N1_in2 = new Nurse("Jonna","Nielsen","Ikke-doktorvej","Tysk",02,02,1902,"Pediatric");
		
		R.add(In2, P1_in2);
		R.add(In2, P2_in2);
		R.add(In2, D1_in2);
		R.add(In2, N1_in2);
		//LOADING COMPLETE
	}
	

	
	/* _____________ PATIENT REGISTRATION for M1 ______________ */
	
	// REGISTER PATIENT TO HOSPITAL (NO DEPARTMENT)
	public String registerPatient(String firstName, String lastName, String tribe, String address, int day, int month, int year, boolean alive) {
		if (Person.isValidPersonData(firstName, lastName, day, month, year, address, tribe, alive)) {
			// Adding to hospital  ->  The changereg R makes sure to handle database communication
			R.add(h, new Patient(firstName,lastName,tribe,address,day,month,year,alive,null)); // Adding patient through changereg
			totalSet.add(new Patient(firstName,lastName,tribe,address,day,month,year,alive,null));
			return "Patient registered succesfully.";
		} else {return "Additional information is needed.";}
	}
	
	// CHANGE EXISTING PATIENT 
	public String changePatient(String ID, String firstName, String lastName, String tribe, String address, int day, int month, int year, boolean alive) {
		Patient p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();
		if (p==null) {return "No patient with that ID found.";}
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
	
	// PATIENT SEARCH 'PATIENT ATBs.' -> String (patient info)
	public String patientSearcher(String patientID, String firstName, String lastName, String birthday) {
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
	
	//registerStaff takes staff ATBs, adds staff to hospital (no department)
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
			else {return "Unsuccesful registration!";}
			R.add(h, p);
			return "The " + jobtype + " has been registered succesfully!";
		}else {return "Unsuccesful registration!";}
	}
	
	//ASSIGN STAFF TO DEPARTMENT
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
	
	//CHANGE STAFF FROM ID -> TO ATBs
	public String changeStaff (String StaffID ,String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		if (searcher.staffSearch(StaffID, "", "", "", "").size() == 0) {
			return "The ID does not match an employee!";
		}
		Staff person = (Staff) searcher.staffSearch(StaffID, "", "", "", "").get(0);
		if (person==null) {return "The ID does not match an employee!";}
		if (firstName == "") {firstName = person.getFirstName();}
		if (lastName == "") {lastName = person.getLastName();}
		if (adress == "") {adress = person.getAdress();}
		if (tribe == "") {tribe = person.getTribe();}
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
			else {return "Illegal changes to patient. Please check that the information is correct!";}
		}
	}
	
	//STAFF SEARCH
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
	
	//GET ALL DEPARTMENTS
	public LinkedList<String> getDepartments() {
		LinkedList<Department> ds = new LinkedList<Department>(h.getDepartSet());
		LinkedList<String> dlist = new LinkedList<String>();
		while (!ds.isEmpty()) {
			dlist.add(ds.removeFirst().toString());
		}
		return dlist;
	}
	
	//GET STAFF LIST OF GIVEN DEPARTMETN
	public LinkedList<String> getDeparmentStaff(String departmentName) {
		LinkedList<Department> resList = searcher.departmentSearch(departmentName);
		LinkedList<String> res = new LinkedList<String>();
		if (resList.size()==1) {
			LinkedList<Person> sList = new LinkedList<Person>(resList.removeFirst().getStaff());
			while (!sList.isEmpty()) {
				res.add(sList.removeFirst().toString());
			}
		} else {res.add("No or multiple department(s) match your search criterion");}
		return res;
	}
	
	//ALLOCATE EXSISTING PATIENT TO BED
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
	
	//BEDS AVAILABLE IN GIVEN DEPARTMENT (Y/N)
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
	
	//HOW MANY BEDS IN USE IN GIVEN DEPARTMENT
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
	
	//ADMIT EXSISTING PATIENT TO DEPARTMENT
	public String patientAdmission(String trilvl, String departmentName, String patientId) {
		//CHECK TRIAGE LEVEL
		int triagelvl = 1;
		try {
			if (!trilvl.equals("")) {triagelvl = Integer.parseInt(trilvl);}
		} catch (Exception e) {return "The triage level specification wasn't an integer";}
	
		LinkedList<Person> pSearch = searcher.patientSearch(patientId, "", "", "");
		if (pSearch.size()!=1) {return "Invalid patient ID";}
		Patient p = (Patient) pSearch.getFirst();
		
		LinkedList<Department> departmentSearch = searcher.departmentSearch(departmentName);
		if (departmentSearch.size() != 1 ) {
			return "The department specification is ambigious";
		}
		Department d = departmentSearch.getFirst();
		if (d instanceof framework.Departments.AdminDepart) {
			return "The department is an administrativ department";
		}
		discharge(p.getID());
		if (d instanceof framework.Departments.HealthCare.InPatientDepart) {
			InPatientDepart inDepart = (InPatientDepart) d;
			R.add(inDepart, p);
			inDepart.beds.AllocateBed(p);
		} else {
			OutPatientDepart outDepart = (OutPatientDepart) d;
			R.add(outDepart, p);
			outDepart.EnQueue(p, triagelvl);
		}
		return "The patient has been registered succesfully to " + departmentName +  "!";
	}
	
	// The input to this function should be specified in the GUI so when
	// I search for the patient and click remove this function is given the patient ID
	public String discharge(String ID) {
		Patient p;
		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient ID's isn't uniqe";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}
		
		LinkedList<Department> dSearch = searcher.departmentSearch(p.getDepartment());
		if (dSearch.size() != 1) {return p + " was not assigned to any department.";}
		Department d = dSearch.getFirst();
		R.remove(d, p);
		return p + ", has been removed succesfully from " + d;
	}
	
	//MOVE PATIENT FROM DEPARTMENT TO DEPARTMENT
	public String movePatientDepart(String ID, String departmentName, String trilvl) {
		if (patientAdmission(ID,departmentName,trilvl).contains("succesfully")) {
			return "The patient was moved successfully";
		}
		return "The patient wasn't moved";
	}
	
	//MOVE A PATIENT TO A NEW BED
	public String movePatientBed(String ID, String newBed) {
		InPatientDepart Department;
		Patient p;
		String message;
		int bedNo;
		try {
			bedNo = Integer.parseInt(newBed);
		} catch (Exception e) {return "You need to specify the bed number as an integer";}
		
		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient wasn't moved cause to invalid ID";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}
		
		if (!(searcher.departmentSearch(p.getDepartment()).getFirst() instanceof framework.Departments.HealthCare.InPatientDepart)) {
			return "The department isn't an indepartment";
		}
		else {Department = (InPatientDepart) searcher.departmentSearch(p.getDepartment()).getFirst();}		
			if (Department.beds.getMaxBeds() < bedNo) {
				return "There aren't that many beds in the department";
			}
			message = Department.beds.AllocateBed(p, bedNo);	
			if (message.equals("Ok")) {
				Department.beds.Discharge(p);
				return "The patient was moved succesfully";
			}
			if (message.equals("Same bed")) {
				return "The patient was moved succesfully to the same bed";
			}
			else {return "The bed wasn't free";}
	}
	
	
	/* _____________ PATIENT WAITING O3 ______________ */
	
	//GET WAITING QUEUE OF GIVEN DEPARTMENT
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
		} catch (ClassCastException e) {
			res.add("Warning, could not retrieve queue of given department.");
			return res;
		}
		ArrayList<Person> queue = outDepart.PrintQueue();
		for (int i = 0; i<queue.size(); i++) {
			res.add(queue.get(i).toString());
		}
		return res;
	}
	
	//GET NEXT IN QUEUE (METHOD DEQUEUES PATIENT!)
	public String getNextInQueue(String departmentName) {
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		OutPatientDepart outDepart;
		if (departmentRes.size() != 1) {
			return "Warning, could not retrieve next in line.";
		}
		if (!(departmentRes.getFirst() instanceof OutPatientDepart)) {
			return "Warning, could not retrieve next in line.";
		}
		outDepart = (OutPatientDepart) departmentRes.getFirst();
		Person next = outDepart.DeQueue();
		if (next == null) {
			return "Warning, could not retrieve next in line.";
		}
		return next.toString();
	}
	
	
	/* ______________  PASSWORD METHODS for O2 ________________    */
	
	//ADDS NEW PASSWORD
	public String AddPassword(String newPassword1, String newPassword2, String staffID) {
		Password Pass = Password.getInstance();
		if (Pass.checkUniqueID(staffID)) {
			return "Password already created for this staff!";
		}
		if (newPassword2 == newPassword1) {
			Pass.addPassToMap(newPassword1, staffID);
			return "Password created";
		} else{
			return "The two passwords do not match";
		}
	}
	
	//CHANGE PASSWORD FROM KNOWN PASSWORD
	public String ChangePassword(String oldPassword , String newPassword1, String newPassword2, String staffID) {
		Password Pass = Password.getInstance();
		if (!Pass.checkUniqueID(staffID)) {
			return "Staff ID does not exist";
		}
		if (Pass.checkPassword(oldPassword, staffID) && newPassword1 == newPassword2 ) {
			Pass.addPassToMap(newPassword1, staffID);
			return "Password changed";
		}
		if (!Pass.checkPassword(oldPassword, staffID) && newPassword1 == newPassword2  ) {
			return "Wrong old password";
		}
		if (Pass.checkPassword(oldPassword, staffID) && (newPassword1 != newPassword2)  ) {
			return "The 2 new passwords are not equal";
		}
		return "Something went wrong";
	}
	
	
	/* _____________ DATABASE PROPERTIES for O4 _______________ */
	
	//CHECK FOR CONNECTIVITY TO DATABASE
	public boolean isConnected() {
		return DB != null;
	}
	
	
	/* ______________  PARTICIPATION LIST for O5 ________________    */
	
	//CREATES A .csv FILE CONTAINING ALL PATIENTS
	public String getParticipationList(boolean department, boolean birthday, boolean address, boolean tribe) throws IOException {
		new ParticipationList(new LinkedList<Person>(h.getAllPatient()), department, birthday, address, tribe);
		return "Participation list was created successfully.";
	}
	
	//CREATES A .csv FILE CONTAINING ALL PATIENTS OF GIVEN DEPARTMENT
	public String getParticipationList(String departmentName, boolean department, boolean birthday, boolean address, boolean tribe) throws IOException {
		LinkedList<Department> dList = searcher.departmentSearch(departmentName);
		if (dList.size() != 1 || !(dList.getFirst() instanceof HCDepart)) {
			return "Warning, an error occured, no list was created.";
		}
		new ParticipationList(new LinkedList<Person>(dList.getFirst().getPatient()), department, birthday, address, tribe);
		return "Participation list was created successfully.";
	}
}