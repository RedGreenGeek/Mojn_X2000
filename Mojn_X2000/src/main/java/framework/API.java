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
	private Password Pas;
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
		Pas = Password.getInstance();
		searcher = new Searcher(h);
		R = new ChangeReg();
		Pas.addPassToMap("I", "I");
//		try {
//			log = new Logger();
//			log.write("SYSTEM","REBOOT","NONE");
//		} catch (IOException e) {e.printStackTrace();}
		
		//LOAD ARTIFICIAL HOSPITAL
		
		try {
		h = DB.boot();
		} catch(Throwable t){
			System.out.println("System was not able to boot. Contact System Administrator!");
		}
		
		Pas.addPassToMap("password", "IT4");
		Pas.addPassToMap("password", "C5");
		Pas.addPassToMap("password", "D2");
		Pas.addPassToMap("password", "N3");
		
		//LOADING COMPLETE
	}
	

	
	/* _____________ PATIENT REGISTRATION for M1 ______________ */
	
	// REGISTER PATIENT TO HOSPITAL (NO DEPARTMENT)
	public String registerPatient(String password, String userID, String firstName, String lastName, String tribe, String address, int day, int month, int year, boolean alive) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
	public String changePatient(String password, String userID, String ID, String firstName, String lastName, String tribe, String address, boolean alive) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		LinkedList<Person> patientSearch = searcher.patientSearch(ID, "", "", "");
		if (patientSearch.size() != 1) {return "No patient with that ID found.";}
		Patient p = (Patient) patientSearch.getFirst();
		if (Person.isValidPersonData(firstName, lastName, 01, 01, 1997, address, tribe, alive)) {
			if (!firstName.isEmpty()) {
			p.setFirstName(firstName);}
			if (!lastName.isEmpty()) {
			p.setLastName(lastName);}
			if (!address.isEmpty()) {
			p.setAdress(address);}
			if (!tribe.isEmpty()) {
			p.setTribe(tribe);} 
			p.setAlive(alive);
			
			//Write to database 
			DB.writePatient(p);
			/* write to log file */
//			log.write(userID,"PATIENT DATA CHANGED",p.toString());
			
			return "Patient information has been changed successfully.";
		} else {return "Illegal information changes.";}
	}
	
	// PATIENT SEARCH 'PATIENT ATBs.' -> String (patient info)
	public String patientSearcher(String password, String userID, String patientID, String firstName, String lastName, String birthday, String department) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		LinkedList<Person> persons = searcher.patientSearch(patientID, firstName, lastName, birthday);
		if (!department.equals("")) {
			@SuppressWarnings("unchecked")
			LinkedList<Person> p2 = (LinkedList<Person>) persons.clone();
			persons.clear();
				while (!p2.isEmpty()) {
				Person p = p2.removeFirst();
				if (p.getDepartment().equals(department)) {
					persons.add(p);
				}
			}
		}
		String message = "ID\tDepartment\tSurname\tName\tBedNo/Triage";
		while (!persons.isEmpty()) {
			message += "\n"+persons.removeFirst().toString();
		}
		if (message.equals("ID\tDepartment\tSurname\tName\tBedNo/Triage")) {
			return "No match to search parameters!";
		} else {return message; }
		}
	
	
	//GET PATIENT LIST OF GIVEN DEPARTMETN
	public String getDeparmentPatient(String password, String userID, String departmentName) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		LinkedList<Department> resList = searcher.departmentSearch(departmentName);
		String res = "ID\tDepartment\tSurname\tName\tBedNo/Triage";
		if (resList.size()==1) {
			LinkedList<Person> sList = new LinkedList<Person>(resList.removeFirst().getPatient());
			while (!sList.isEmpty()) {
				res += "\n"+sList.removeFirst().toString();
			}
		} else {res = "No or multiple department(s) match your search criterion";}
		return res;
	}

	
	
	/* _____________ STAFF REGISTRATION for M2 ______________ */
	
	//registerStaff takes staff ATBs, adds staff to hospital (no department)
	public String registerStaff(String password, String userID, String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
	public String assignStaffDepartment(String password, String userID, String departmentName, String staffID, String firstName, String lastName, String birthday, String email) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
	public String changeStaff (String password, String userID, String StaffID ,String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setTribe(tribe);
			person.setAdress(adress);
			person.setBirthDay(day, month, year);
			String id = person.getJobType();
			id.replaceAll("N", "").replaceAll("D", "").replaceAll("IT", "").replaceAll("C", "");
			
			if (jobtype.equals("Nurse")) {
				id = "N"+id;
			}
			if (jobtype.equals("Doctor")) {
				id = "D"+id;
			}
			if (jobtype.equals("Clerk")) {
				id = "C"+id;
			}
			if (jobtype.equals("ICTOfficer")) {
				id = "IT"+id;
			}
			
			person.setID(id);
			person.setJobType(jobtype);
		
			
			return "The "+jobtype+" has been registered succesfully!";
			} else {return "Invalid job type!";}
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
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		LinkedList<Person> persons = searcher.staffSearch(staffID,firstName, lastName, birthday, email);
		String message = "ID\tJob\tDepartment\tSurname\tName";
		while (!persons.isEmpty()) {
			message += "\n"+persons.removeFirst().toString();
		}
		if (message.equals("ID\tJob\tDepartment\tSurname\tName")) {
			return "No match to search parameters!";
		} else {return message; }
	}
	
	
	/* _____________ HEALTH FACILITY MANAGMENT for M3 ______________ */
	
	//GET ALL DEPARTMENTS
	public String getDepartments(String password, String userID) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		LinkedList<Department> resList = searcher.departmentSearch(departmentName);
		String res = "ID\tJob\tDepartment\tSurname\tName";
		if (resList.size()==1) {
			LinkedList<Person> sList = new LinkedList<Person>(resList.removeFirst().getStaff());
			while (!sList.isEmpty()) {
				res += "\n"+sList.removeFirst().toString();
			}
		} else {res = "No or multiple department(s) match your search criterion";}
		return res;
	}
	
	//ALLOCATE EXSISTING PATIENT TO BED
	public String allocateToBed(String password, String userID, String departmentName, String patientID) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
		
		discharge("I","I",patientRes.getFirst().getID());
		patientAdmission("I","I","", departmentName, patientID);
		
		/* write to log file */
//		log.write(userID,"ALLOCATED PATIENT TO BED",p.toString());
		
		return p+" was added to bed: "+p.getBedLocation();
	}
	
	//BEDS AVAILABLE IN GIVEN DEPARTMENT (Y/N)
	public String bedsAvailable(String password, String userID, String departmentName) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		LinkedList<Department> departmentRes = searcher.departmentSearch(departmentName);
		if (departmentRes.size()!=1) {
			return "No or multiple department(s) match your search criterion";
		}
		if (!(departmentRes.getFirst() instanceof InPatientDepart)) {
			return "Department "+departmentName+" does not contain any beds.";
		}
		InPatientDepart depart = (InPatientDepart) departmentRes.getFirst();
		
		return "Department: " + departmentName+" currently have "+ depart.beds.getBedsInUse() + " beds in use.";
	}
	
	
	/* _____________ PATIENT ADMISSION for M4 ______________ */
	
	//ADMIT EXSISTING PATIENT TO DEPARTMENT
	public String patientAdmission(String password, String userID, String trilvl, String departmentName, String patientId) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
		  discharge("I","I",p.getID());
		  if (d instanceof InPatientDepart) {
		   InPatientDepart inDepart = (InPatientDepart) d;
		   R.add(inDepart, p);
		  } else {
		   OutPatientDepart outDepart = (OutPatientDepart) d;
		   p.setTriage(triagelvl);
		   R.add(outDepart, p);
		  }
		  
		  /* write to log file */
		//  log.write(userID,"PATITENT ADMITTED",p.toString());
		  
		  return "The patient has been registered succesfully to " + departmentName +  "!";
		 }
	
	// The input to this function should be specified in the GUI so when
	// I search for the patient and click remove this function is given the patient ID
	public String discharge(String password, String userID, String ID) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		if (patientAdmission("I","I",trilvl,departmentName,ID).contains("succesfully")) {
			
			/* write to log file */
//			log.write(userID,"PATIENT MOVED DEPARTMENT",p.toString());
			
			return "The patient was moved successfully!";
		}
		return "The patient wasn't moved";
	}
	
	//MOVE A PATIENT TO A NEW BED
	public String movePatientBed(String password, String userID, String ID, String newBed) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		
		if (Pas.checkUniqueID(staffID)) {
			return "Password already created for this staff!";
		}
		if (newPassword1.equals(newPassword2) ) {
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
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		if (!Pas.checkUniqueID(staffID)) {
			System.out.println("fuck");
			return "Staff ID does not exist";
			
		}
		if (Pas.checkPassword(oldPassword, staffID) && newPassword1.equals(newPassword2) ) {
			Pas.addPassToMap(newPassword1, staffID);
			
			/* write to log file */
//			log.write(userID,"PASSWORD CHANGED",staffID);
			
			return "Password changed";
		}
		if (!Pas.checkPassword(oldPassword, staffID) && newPassword1.equals(newPassword2)  ) {
			return "Wrong old password";
		}
		if (Pas.checkPassword(oldPassword, staffID) && !newPassword1.equals(newPassword2) ) {
			return "The 2 new passwords are not equal";
		}
		return "Something went wrong";
	}
	
	
	/* _____________ PATIENT WAITING O3 ______________ */
	
	//GET WAITING QUEUE OF GIVEN DEPARTMENT
	public String getQueue(String password, String userID, String departmentName) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
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
		
		return "ID\tDepartment\tSurname\tName\tBedNo/Triage\n"+next.toString();
	}
	
	
	
	/* _____________ DATABASE PROPERTIES for O4 _______________ */
	
	//CHECK FOR CONNECTIVITY TO DATABASE
	public boolean isConnected() {
		return DB != null;
	}
	
	
	/* ______________  PARTICIPATION LIST for O5 ________________    */
	
	//CREATES A .csv FILE CONTAINING ALL PATIENTS
	public String getParticipationList(String password, String userID, boolean department, boolean birthday, boolean address, boolean tribe) throws IOException {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		new ParticipationList(new LinkedList<Person>(h.getAllAdmittedPatients()), department, birthday, address, tribe);
		return "Participation list was created successfully.";
	}
	
	//CREATES A .csv FILE CONTAINING ALL PATIENTS OF GIVEN DEPARTMENT
	public String getParticipationList(String password, String userID, String departmentName, boolean department, boolean birthday, boolean address, boolean tribe) throws IOException {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		LinkedList<Department> dList = searcher.departmentSearch(departmentName);
		if (dList.size() != 1 || !(dList.getFirst() instanceof HCDepart)) {
			return "Warning, an error occured, no list was created.";
		}
		new ParticipationList(new LinkedList<Person>(dList.getFirst().getPatient()), department, birthday, address, tribe);
		return "Participation list was created successfully.";
	}
	
	
	
	/* ______________  EXTRA FEATURES for O7 ________________    */
	
	public String addDepartment(String password, String userID, String type, String departmentName, String maxBed) {
		if (Pas.getClearence(password,userID) < 1) {
			return "You do not have the clearency to do this, contact system admin!";
		}
		
		LinkedList<Department> dList =new LinkedList<Department>(h.getDepartSet());
		while (!dList.isEmpty()) {
			Department d = dList.removeFirst();
			if (d.getName()!=null && d.getName().toLowerCase().equals(departmentName.toLowerCase())) {
				return "Department name must be unique!";
			}
		}
		if (type.toLowerCase().equals("admin")) {
			R.add(this.h,new AdminDepart(departmentName));
		}
		else if (type.toLowerCase().equals("inpatient")) {
			try {
				R.add(this.h,new InPatientDepart(departmentName, Integer.parseInt(maxBed)));
			} catch (Exception e) { return "maxBeds must be a integer!";} 
		}
		else if (type.toLowerCase().equals("outpatient")) {
			R.add(this.h, new OutPatientDepart(departmentName));
		}
		else {return "Invalid department type. It must be admin, inPatient, or outPatient!";}
		return "The department was added!";
	}
}