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
	public Hospital h;
	private Searcher searcher;
	private ChangeReg R;
	private Database DB;
	private Password Pas = Password.getInstance();
//	private Logger log;
	
	public static synchronized API getInstance() {
		if (instance == null) {
			instance = new API();
		}
		return instance;
	}
	
	private API (){
		// CONNECTION TO DATABASE TO ENSURE CONNECTION
		DB = Database.getInstance(Database.DEFAULT);
		
//		try {
//			log = new Logger();
//			log.write("SYSTEM","REBOOT","NONE");
//		} catch (IOException e) {e.printStackTrace();}
		
		//LOAD ARTIFICIAL HOSPITAL
		h = new Hospital();
		searcher = new Searcher(h);
		R = new ChangeReg();
		
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
		this.registerPatient("admin","I","Jens","Jensen","Jagtvej 69","Zulu",24,9,97,true);
		this.patientAdmission("admin","I","", "ER", "1");
		
		this.registerPatient("admin","I","Hans","Hansen","Tagensvej 101","Masai",24,12,2000,true);
		this.patientAdmission("admin","I","", "ER", "2");
		
		
		this.registerStaff("admin","I","Doctor", "Svend","Nielsen","Doktorvej","Dansk",01,01,1901);
		this.assignStaffDepartment("admin","I","ER", "", "Svend", "Nielsen", "", "");
		
		this.registerStaff("admin","I","Nurse", "Jonna","Nielsen","Ikke-doktorvej","Tysk",02,02,1902);
		this.assignStaffDepartment("admin","I","ER", "", "Jonna", "Nielsen", "", "");
		

		//------
		
		//
		this.registerPatient("admin","I","Søren","Sørensen","Hellerup","Ventre",24,9,97,true);
		this.patientAdmission("admin","I","", "Cardio", "3");
		
		this.registerPatient("admin","I","Lars","Larsen","Nordvestjylland","Jysk",20,12,1950,true);
		this.patientAdmission("admin","I","", "Cardio", "4");
		
		this.registerStaff("admin","I","Doctor", "Lars","Løkke","Græsted","Ventre",01,01,1950);
		this.assignStaffDepartment("admin","I","Cardio", "", "Lars", "Løkke", "", "");
		
		this.registerStaff("admin","I","Nurse", "Helle","Thorning","Herlev","Gucci",02,02,1960);
		this.assignStaffDepartment("admin","I","Cardio", "", "Helle", "Thorning", "", "");
		
		Pas.addPassToMap("password", "IT4");
		Pas.addPassToMap("password", "C5");
		Pas.addPassToMap("password", "D2");
		Pas.addPassToMap("password", "N3");
		
		//------
		this.registerStaff("admin","I","ICTOfficer", "Jens","Hansen","Norway","Indian",29,2,1996);
		this.assignStaffDepartment("admin","I","IT", "", "Jens", "Hansen", "", "");
		
		this.registerStaff("admin","I","Clerk", "Mads", "Hansen", "Uganda","Black-rocks Clan",23,4,2000);
		this.assignStaffDepartment("admin","I","IT", "", "Mads", "Hansen", "", "");
		
		
		//------
		this.registerPatient("admin","I","Jens","Jensen","Jagtvej 69","Zulu",24,9,97,true);
		this.patientAdmission("admin","I","", "Pediatric", "5");
		
		this.registerPatient("admin","I","Hans","Hansen","Tagensvej 101","Masai",24,12,2000,true);
		this.patientAdmission("admin","I","", "Pediatric", "6");
		//LOADING COMPLETE
	}
	

	
	/* _____________ PATIENT REGISTRATION for M1 ______________ */
	
	// REGISTER PATIENT TO HOSPITAL (NO DEPARTMENT)
	public String registerPatient(String password, String userID, String firstName, String lastName, String tribe, String address, int day, int month, int year, boolean alive) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		if (Person.isValidPersonData(firstName, lastName, day, month, year, address, tribe, alive)) {
			// Adding to hospital  ->  The changereg R makes sure to handle database communication
			Patient p = new Patient(firstName,lastName,tribe,address,day,month,year,alive,null);
			R.add(h, p); // Adding patient through changereg
			
			/* write to log file */
//			log.write(userID,"REGISTERED PATIENT",p.toString());
			
			return "Patient registered succesfully.";
		} else {return "Additional information is needed.";}
	}
	
	// CHANGE EXISTING PATIENT 
	public String changePatient(String password, String userID, String ID, String firstName, String lastName, String tribe, String address, int day, int month, int year, boolean alive) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		LinkedList<Person> patientSearch = searcher.patientSearch(ID, "", "", "");
		if (patientSearch.size() != 1) {return "No patient with that ID found.";}
		Patient p = (Patient) patientSearch.getFirst();
		if (Person.isValidPersonData(firstName, lastName, day, month, year, address, tribe, alive)) {
			p.setFirstName(firstName);
			p.setLastName(lastName);
			p.setBirthDay(day, month, year);
			p.setAdress(address);
			p.setTribe(tribe);
			p.setAlive(alive);
			
			/* write to log file */
//			log.write(userID,"PATIENT DATA CHANGED",p.toString());
			
			return "Patient information has been changed successfully.";
		} else {return "Illegal information changes.";}
	}
	
	// PATIENT SEARCH 'PATIENT ATBs.' -> String (patient info)
	public String patientSearcher(String password, String userID,String patientID, String firstName, String lastName, String birthday) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
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
	public String registerStaff (String password, String userID, String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
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
			
			/* write to log file */
//			log.write(userID,"STAFF REGISTERED",p.toString());
			
			return "The " + jobtype + " has been registered succesfully!";
		}else {return "Unsuccesful registration!";}
	}
	
	//ASSIGN STAFF TO DEPARTMENT
	public String assignStaffDepartment(String password, String userID,String departmentName, String staffID, String firstName, String lastName, String birthday, String email) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		LinkedList<Person> staffRes = searcher.staffSearch(staffID, firstName, lastName, birthday, email);
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		if (staffRes.size()!=1 || departmentRes.size()!=1) {
			return "Warning, invalid person info or department name";
		}
		if (staffRes.getFirst().getDepartment() != null) {
			R.remove(searcher.departmentSearch(staffRes.getFirst().getDepartment()).getFirst(),(Staff) staffRes.getFirst());
		}
		R.add(departmentRes.getFirst(), (Staff) staffRes.getFirst());
		staffRes.getFirst().setDepartment(departmentName);
		
		/* write to log file */
//		log.write(userID,"ASSIGNED DEPARTMENT",staffRes.getFirst().toString());
		
		return "Staff added successfully to department";
	}
	
	//CHANGE STAFF FROM ID -> TO ATBs
	public String changeStaff(String password, String userID,String StaffID ,String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
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
			h.getStaffSet().remove(person);
			// registered as with new job type.
			String message = registerStaff("admin","I",jobtype, firstName, lastName, adress, tribe, day, month ,year);
			return message;} else {return "Invalid job type!";}
		}
		else {
			if (Person.isValidPersonData(firstName, lastName, day, month, year, adress, tribe, true)) {
				person.setFirstName(firstName);
				person.setLastName(lastName);
				person.setBirthDay(day, month, year);
				person.setAdress(adress);
				person.setTribe(tribe);
				
				/* write to log file */
//				log.write(userID,"STAFF DATA CHANGED",person.toString());
				
				return "Staff information has been changed successfully!";
			}
			else {return "Illegal changes to patient. Please check that the information is correct!";}
		}
	}
	
	//STAFF SEARCH
	public String staffSearcher(String password, String userID, String staffID, String firstName, String lastName, String birthday, String email) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
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
	public String getDepartments(String password, String userID) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		LinkedList<Department> ds = new LinkedList<Department>(h.getDepartSet());
		String res = "";
		while (!ds.isEmpty()) {
			res += ds.removeFirst().toString()+"\n";
		}
		return res;
	}
	
	//GET STAFF LIST OF GIVEN DEPARTMETN
	public String getDeparmentStaff(String password, String userID, String departmentName) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		LinkedList<Department> resList = searcher.departmentSearch(departmentName);
		String res = "";
		if (resList.size()==1) {
			LinkedList<Person> sList = new LinkedList<Person>(resList.removeFirst().getStaff());
			while (!sList.isEmpty()) {
				res += sList.removeFirst().toString()+"\n";
			}
		} else {res = "No or multiple department(s) match your search criterion";}
		return res;
	}
	
	//ALLOCATE EXSISTING PATIENT TO BED
	public String allocateToBed(String password, String userID, String departmentName, String patientID) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		LinkedList<Person> patientRes = searcher.patientSearch(patientID,"","","");
		if (departmentRes.size()!=1) {
			return "No or multiple department(s) match your search criterion";
		}
		if (patientRes.size()!=1) {
			return ("No patient with given ID in department: " + departmentName); 
		}
		Patient p = (Patient) patientRes.getFirst();
		if (!(departmentRes.getFirst() instanceof InPatientDepart)) {
			return "Department "+departmentName+" does not contain any beds.";
		}
		InPatientDepart depart = (InPatientDepart) departmentRes.getFirst();
		if (!depart.beds.getBedsAvailable()) {
			return "No beds available in department: " + departmentName;
		}
		discharge("admin","I",patientRes.getFirst().getID());
		patientAdmission("admin","I",p.getTriage().toString(), departmentName, patientID);
		
		/* write to log file */
//		log.write(userID,"ALLOCATED PATIENT TO BED",p.toString());
		
		return p+" was added to bed: "+p.getBedLocation();
	}
	
	//BEDS AVAILABLE IN GIVEN DEPARTMENT (Y/N)
	public String bedsAvailable(String password, String userID, String departmentName) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
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
	public String bedsInUse(String password, String userID, String departmentName) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
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
	public String patientAdmission(String password, String userID, String trilvl, String departmentName, String patientId) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		//CHECK TRIAGE LEVEL
		int triagelvl = 1;
		try {
			if (!trilvl.equals("")) {triagelvl = Integer.parseInt(trilvl);}
		} catch (Exception e) {return "The triage level specification wasn't an integer";}
	
		LinkedList<Person> pSearch = searcher.patientSearch(patientId, "", "", "");
		if (pSearch.size() != 1) {return "Invalid patient ID";}
		Patient p = (Patient) pSearch.getFirst();
		
		LinkedList<Department> departmentSearch = searcher.departmentSearch(departmentName);
		if (departmentSearch.size() != 1 ) {
			return "The department specification is ambigious";
		}
		Department d = departmentSearch.getFirst();
		if (d instanceof framework.Departments.AdminDepart) {
			return "The department is an administrativ department";
		}
		discharge("admin","I", p.getID());
		if (d instanceof framework.Departments.HealthCare.InPatientDepart) {
			InPatientDepart inDepart = (InPatientDepart) d;
			R.add(inDepart, p);
		} else {
			OutPatientDepart outDepart = (OutPatientDepart) d;
			p.setTriage(triagelvl);
			R.add(outDepart, p);
		}
		
		/* write to log file */
//		log.write(userID,"PATITENT ADMITTED",p.toString());
		
		return "The patient has been registered succesfully to " + departmentName +  "!";
	}
	
	// The input to this function should be specified in the GUI so when
	// I search for the patient and click remove this function is given the patient ID
	public String discharge(String password, String userID, String ID) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		Patient p;
		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient ID's isn't uniqe";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}
		if (p.getDepartment()==null) {return p + " was not assigned to any department.";}
		LinkedList<Department> dSearch = searcher.departmentSearch(p.getDepartment());
		Department d = dSearch.getFirst();
		
		/* write to log file */
//		log.write(userID,"PATIENT DISCHARGED",p.toString());
		
		R.remove(d, p);
		return p + ", has been removed succesfully from " + d;
	}
	
	//MOVE PATIENT FROM DEPARTMENT TO DEPARTMENT
	public String movePatientDepart(String password, String userID, String ID, String departmentName, String trilvl) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		if (patientAdmission("admin","I",trilvl,departmentName,ID).contains("succesfully")) {
			
			/* write to log file */
//			log.write(userID,"PATIENT MOVED DEPARTMENT",p.toString());
			
			return "The patient was moved successfully!";
		}
		return "The patient wasn't moved";
	}
	
	//MOVE A PATIENT TO A NEW BED
	public String movePatientBed(String password, String userID, String ID, String newBed) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
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
		
		if (!(searcher.departmentSearch(p.getDepartment()).getFirst() instanceof InPatientDepart)) {
			return "The department isn't an indepartment";
		}
		else {Department = (InPatientDepart) searcher.departmentSearch(p.getDepartment()).getFirst();}		
			if (Department.beds.getMaxBeds() < bedNo) {
				return "There aren't that many beds in the department";
			}
			Department.beds.Discharge(p.getBedLocation());
			message = Department.beds.AllocateBed(p, bedNo);	
			if (message.equals("Ok")) {
				Department.beds.Discharge(p);
				
				/* write to log file */
//				log.write(userID,"PATIENT MOVED BED",p.toString());
				
				return "The patient was moved succesfully";
			}
			if (message.equals("Same bed")) {
				return "The patient was moved succesfully to the same bed";
			}
			else {return "The bed wasn't free";}
	}
	
	
	/* ______________  PASSWORD METHODS for O2 ________________    */
	
	//CHECK IF PASSWORD MATCH USERID
	public boolean passwordMatch(String password, String userID) {
		return this.Pas.checkPassword(password, userID);
	}
	
	//ADDS NEW PASSWORD
	public String AddPassword(String password, String userID, String newPassword1, String newPassword2, String staffID) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		if (Pas.checkUniqueID(staffID)) {
			return "Password already created for this staff!";
		}
		if (newPassword2 == newPassword1) {
			Pas.addPassToMap(newPassword1, staffID);
			
			/* write to log file */
//			log.write(userID,"NEW USER ADDED",staffID);
			
			return "Password created";
		} else{
			return "The two passwords do not match";
		}
	}
	
	//CHANGE PASSWORD FROM KNOWN PASSWORD
	public String ChangePassword(String password, String userID, String oldPassword , String newPassword1, String newPassword2, String staffID) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		if (!Pas.checkUniqueID(staffID)) {
			return "Staff ID does not exist";
		}
		if (Pas.checkPassword(oldPassword, staffID) && newPassword1 == newPassword2 ) {
			Pas.addPassToMap(newPassword1, staffID);
			
			/* write to log file */
//			log.write(userID,"PASSWORD CHANGED",staffID);
			
			return "Password changed";
		}
		if (!Pas.checkPassword(oldPassword, staffID) && newPassword1 == newPassword2  ) {
			return "Wrong old password";
		}
		if (Pas.checkPassword(oldPassword, staffID) && (newPassword1 != newPassword2)  ) {
			return "The 2 new passwords are not equal";
		}
		return "Something went wrong";
	}
	
	
	/* _____________ PATIENT WAITING O3 ______________ */
	
	//GET WAITING QUEUE OF GIVEN DEPARTMENT
	public String getQueue(String password, String userID, String departmentName) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		OutPatientDepart outDepart;
		if (departmentRes.size() != 1) {
			return "Warning, could not retrieve queue of given department.";
		}
		try {
			outDepart = (OutPatientDepart) departmentRes.getFirst();
		} catch (ClassCastException e) {
			return "Warning, could not retrieve queue of given department.";
		}
		ArrayList<Person> queue = outDepart.PrintQueue();
		String res = "";
		for (int i = 0; i<queue.size(); i++) {
			res += (queue.get(i).toString())+"\n";
		}
		return res;
	}
	
	//GET NEXT IN QUEUE (METHOD DEQUEUES PATIENT!)
	public String getNextInQueue(String password, String userID, String departmentName) {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
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
		
		/* write to log file */
//		log.write(userID,"PATIENT DEQUEUED",p.toString());
		
		return next.toString();
	}
	
	
	
	/* _____________ DATABASE PROPERTIES for O4 _______________ */
	
	//CHECK FOR CONNECTIVITY TO DATABASE
	public boolean isConnected() {
		return DB != null;
	}
	
	
	/* ______________  PARTICIPATION LIST for O5 ________________    */
	
	//CREATES A .csv FILE CONTAINING ALL PATIENTS
	public String getParticipationList(String password, String userID, boolean department, boolean birthday, boolean address, boolean tribe) throws IOException {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		
		new ParticipationList(new LinkedList<Person>(h.getAllAdmittedPatients()), department, birthday, address, tribe);
		return "Participation list was created successfully.";
	}
	
	//CREATES A .csv FILE CONTAINING ALL PATIENTS OF GIVEN DEPARTMENT
	public String getParticipationList(String password, String userID, String departmentName, boolean department, boolean birthday, boolean address, boolean tribe) throws IOException {
		if (Pas.getClearence(password, userID)<1) {
			return "You do not have clearence to do this.";
		}
		LinkedList<Department> dList = searcher.departmentSearch(departmentName);
		if (dList.size() != 1 || !(dList.getFirst() instanceof HCDepart)) {
			return "Warning, an error occured, no list was created.";
		}
		new ParticipationList(new LinkedList<Person>(dList.getFirst().getPatient()), department, birthday, address, tribe);
		return "Participation list was created successfully.";
	}
}