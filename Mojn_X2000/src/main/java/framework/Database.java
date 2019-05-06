package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import framework.Departments.AdminDepart;
import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.Password.Password;
import framework.person.Patient;
import framework.person.Staff;
import framework.person.staff.Clerk;
import framework.person.staff.Doctor;
import framework.person.staff.ICTOfficer;
import framework.person.staff.Nurse;

public class Database {
 
 public static String DEFAULT = "local";
 public static String REMOTE = "remote";
 private String driver_host;
 private String URL;
 private String database;
 private String username;
 private String password;
 private String driver;
 
 
 private Connection myConnection;
 
 /* ######################################################################### */
 /* __________ SECTION 1: Connection                                _________ */
 /* ######################################################################### */

 // The input to the constructor is given in order to distinguish between the local and the remote database. 
 
 public Database(String name_of_database, String host, String database, String username, String password) {
  
  if (name_of_database.equals("local")) {

	   this.driver_host = host;
	   this.database = database;
	   this.username = username;
	   this.password = password;
	   this.URL = driver_host + database;
 
   EstablishConnection();
   
  } else if (name_of_database.equals("remote")) {
   
	   this.driver_host = host;
	   this.database = database;
	   this.username = username;
	   this.password = password;
	   this.URL = driver_host + database;
	   this.driver = "com.mysql.cj.jdbc.Driver";
 
	   EstablishConnection();

  } else {System.err.println("Database connection not recognized!");};
   
 }
 
 
 // Establish connection is the method that is called upon construction of the database. 
 // In case of errors with driver or port connected, the exception is thrown, and the stack trace is printed. 
 

private void EstablishConnection() {

  try {

	   Class.forName(driver);
	   myConnection = DriverManager.getConnection(URL, username, password);

	   Statement myStatement = myConnection.createStatement();
	   myStatement.executeUpdate("USE " + database + ";");

	   myStatement.close();

  }

  catch (Exception e) {
   System.err.println("Connecting to database failed!");
  }
 }
 
 // If the database has been created, and the connection has been established, the isConnected() will return true in the case
// where the connection is successfully established. 
 
 public boolean isConnected() {
  return myConnection != null;
 }
 
 /* ######################################################################### */
 /* ___________ SECTION 2: Adding two types of queries ______________ */
 /* ######################################################################### */
 
 // GET() takes care of all queries to the MySQL database, where data needs to be extracted. 
 // It is made separately due to a difference in the statements are handled by the driver. 
 
 private ResultSet GET(String query) {
  
  try { 
   Statement st = myConnection.createStatement(); 
   return st.executeQuery(query);
  } catch(Exception e) {
   System.err.println("Could not load from database. Please see the stack trace below ..");
   e.printStackTrace();
  }
  
  return null;
  
 }
 
 // INSERT takes care of all queries where data needs to be inserted or updated. 
 
 private String INSERT(String query) {
	  
	  try { 
	   Statement st = myConnection.createStatement(); 
	   st.executeUpdate(query);
	   return "SUCCESS!";
	   
	  } catch(Exception e) {
	   e.printStackTrace();
	   return "ERROR";
	  }
	  
	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 3: Writing all objects to database __________ */
	 /* ######################################################################### */
	 
	 // writePatient() takes a patient as input, dissembles the patient into simple data structures, and writes the data to the database.
	 // In case a patient with same primary key (i.e. 'id') exists in the database, all data is updated.
	 // This is needed when patient information is edited. 
 
 	 protected String writePassword(String username, String hashvalue) {
 		 
 		 String query1 = String.format("INSERT INTO Login (Username, Hashvalue) VALUES (\"%s\", \"%s\")", username, hashvalue);
 		 String query2 = String.format(" ON DUPLICATE KEY UPDATE Username = \"%s\", Hashvalue = \"%s\"", username, hashvalue);
 		 String query = query1 + query2;
 		 
 		 return INSERT(query);
 	 }
	 
	 protected String writePatient(Patient p) {
	  
	  String firstName = p.getFirstName();
	  String lastName = p.getLastName();
	  String birthday = p.getBirthday();
	  String address = p.getAdress();
	  String tribe = p.getTribe();
	  boolean alive = p.isAlive();
	  String department = p.getDepartment();
	  String id = p.getID();
	  Integer triage = p.getTriage();
	  Integer bed_location = p.getBedLocation();
	  
	  String query;
	  
	  // In case the patient is not assigned to a department, he or she cannot have any triage level or bed number. 
	  // Therefore these are written into the database with the value being null. 
	  
	  if (department == null) {
	   
	   String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage, bed) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, null,\"%s\", \"%s\", null, null) ",
	     id, firstName, lastName, birthday, alive, address, tribe);
	    
	   String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
	     + "alive = %b, Department_name = null, address = \"%s\", tribe = \"%s\", triage = null, bed = null", id, firstName, lastName, birthday, alive, address, tribe);
	   
	   query = query1 + query2;
	   
	  // In case the patient has both triage and bed (which should NOT be the case), both are written into the database. 
	  // The rest of the 'else if' follow the same procedure. 
	   
	  } else if (triage != null && bed_location != null) {

	   String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage, bed) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, \"%s\",\"%s\", \"%s\", %d, %d) ",
	     id, firstName, lastName, birthday, alive, 
	     department, address, tribe, triage, bed_location);
	    
	   String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
	     + "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\", triage = %d, bed = %d", id, firstName, lastName, birthday, alive, 
	     department, address, tribe, triage, bed_location);
	   
	   query = query1 + query2;

	   
	  } else if (triage == null && bed_location != null) {
	   
	   String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage, bed) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, \"%s\",\"%s\", \"%s\", null, %d) ",
	     id, firstName, lastName, birthday, alive, 
	     department, address, tribe, bed_location);
	    
	   String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
	     + "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\", triage = null, bed = %d", id, firstName, lastName, birthday, alive, 
	     department, address, tribe, bed_location);
	   
	   query = query1 + query2;
	   
	  } else if (triage != null && bed_location == null) {
	   
	   String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage, bed) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, \"%s\",\"%s\", \"%s\", %d, null) ",
	     id, firstName, lastName, birthday, alive, 
	     department, address, tribe, triage);
	    
	   String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
	     + "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\", triage = %d, bed = null", id, firstName, lastName, birthday, alive, 
	     department, address, tribe, triage);
	   
	   query = query1 + query2;
	   
	  // When both triage and bed are null, the following query will be send
	  } else {
	   
	   String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage, bed) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, \"%s\",\"%s\",\"%s\", null, null) ",
	     id, firstName, lastName, birthday, alive, 
	     department, address, tribe);
	    
	   String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
	     + "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\", triage = null, bed = null", id, firstName, lastName, birthday, alive, 
	     department, address, tribe);
	   
	   query = query1 + query2;
	  
	  }

	  return INSERT(query);
	  
	 }
	 
	 // writeStaff() takes a staff as input, dissembles the staff into simple data structures, and writes the data to the database.
	 // Since staff do not have triage levels or bed locations, and therefore the method contains less statements. 
	 
	 public String writeStaff(Staff s) {
	  
	  String id = s.getID();
	  String firstName = s.getFirstName();
	  String lastName = s.getLastName();
	  String birthday = s.getBirthday();
	  String department = s.getDepartment();
	  String address = s.getAdress();
	  String tribe = s.getTribe();
	  boolean alive = s.isAlive();
	  
	  String query;
	  
	  if (department != null) {

	   String query1 = String.format("INSERT INTO Staff (id, first_name, last_name, birthday, Department_name, address, tribe, alive) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", \"%s\" ,\"%s\", \"%s\", %b)",
	     id, firstName, lastName, birthday, department, address, tribe, alive);
	    
	   String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
	     + "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\"", id, firstName, lastName, birthday, alive, 
	     department, address, tribe);
	   
	   query = query1 + query2;
	  
	  } else {
	   
	   String query1 = String.format("INSERT INTO Staff (id, first_name, last_name, birthday, Department_name, address, tribe, alive) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", null ,\"%s\", \"%s\", %b)",
	     id, firstName, lastName, birthday, department, address, tribe, alive);
	    
	   String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
	     + "alive = %b, Department_name = null, address = \"%s\", tribe = \"%s\"", id, firstName, lastName, birthday, alive, 
	     department, address, tribe);
	   
	   query = query1 + query2;
	   
	  }

	  return INSERT(query);
	  
	 }
	 
	 // writeDepartment() contains a if-statement for each department type. 
	 // In that right amount of information will be written to the database. 
	 
	 public String writeDepartment(Department department) {
	  
	  String name = department.getName();
	  
	  if (department instanceof InPatientDepart ) {
	   
	   int max = ((InPatientDepart) department).get_max_beds();
	   
	   int use = ((InPatientDepart) department).get_beds_in_use();
	   
	   String query1 = String.format("INSERT INTO Department (name, beds_max, beds_use, type) VALUES (\"%s\", \"%d\", \"%d\", \"%s\")", name, max, use, "IPD");
	   
	   String query2 =  String.format(" ON DUPLICATE KEY UPDATE beds_max = \"%d\", beds_use = \"%d\" ", max, use);
	   
	   String query = query1 + query2;
	   
	   return INSERT(query);
	   
	  }
	  
	  else if (department instanceof AdminDepart ) {
	   
	   String query = String.format("INSERT IGNORE INTO Department (name, type) VALUES (\"%s\", \"%s\")", name, "AMD");
	   
	   return INSERT(query);
	   
	  }
	  
	  else if (department instanceof OutPatientDepart ) {
	   
	   String query = String.format("INSERT IGNORE INTO Department (name, type) VALUES (\"%s\", \"%s\")", name, "OPD");
	   
	   return INSERT(query);
	   
	  }
	  
	  else {
	   
	   System.err.println("Something went wrong when trying to write a department!");
	   return "ERROR";
	   
	  }

	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 4: Construction of objects  _________________ */
	 /* ######################################################################### */

	 // This sections deal with methods that are able to read the result of the queries 
	 // such that the objects are constructed correctly. All the make-methods follow the same principles.
	 // 1. Retrieve information 2. Call constructor of object. 
	 
	 public Patient makePatient(ResultSet rs) {
	  
	  try {
	   
	   
	  
	   String firstName = rs.getString("first_name");
	   String lastName = rs.getString("last_name");
	   String[] birthday = rs.getString("birthday").split("-");
	      String address = rs.getString("address");
	   String tribe = rs.getString("tribe");
	   boolean alive = rs.getBoolean("alive");
	   String department = rs.getString("Department_name");
	   Integer triage = rs.getInt("triage");
	   Integer id = rs.getInt("id"); // Not used right now
	   Integer bed = rs.getInt("bed");
	   
	   return new Patient(id, firstName, lastName, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), alive, department, triage, bed);
	   
	  } catch (Exception e) {
	   
	   System.err.println("Something went wrong when trying to read patient from database!");
	   e.printStackTrace();
	   
	   return null;
	   
	  }
	 }
	 
	 // Using that the staff ID starts with a letter depending on which staff type it is
	 // the makeStaff() knows which object to create. 
	 
	 public Staff makeStaff(ResultSet rs) {
	  
	  try {
	  
	   String jobid = rs.getString("id");
	   char jobtype = jobid.charAt(0);
	   String first_name = rs.getString("first_name");
	   String last_name = rs.getString("last_name");
	   String address = rs.getString("address");
	   String tribe = rs.getString("tribe");
	   String[] birthday = rs.getString("birthday").split("-");
	   String department = rs.getString("Department_name"); // We need to put that employee back into an department
	   Staff employee;
	   

	   if (jobtype == 'C') {
	    
	    employee = new Clerk(jobid, first_name, last_name, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), department);
	    
	   }
	   
	   else if (jobtype == 'D') {
	    
	    
	    employee = new Doctor(jobid, first_name, last_name, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), department);
	     
	   }
	   
	   else if (jobtype == 'I') {
	    
	    
	    employee = new ICTOfficer(jobid, first_name, last_name, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), department);
	    
	   }
	   
	   else if (jobtype == 'N') {
	    
	    
	    employee = new Nurse(jobid, first_name, last_name, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), department);
	    
	    
	   } else {return null;}
	   
	   return employee;
	   
	  } catch (Exception e) {
	   
	   System.err.println("Something went wrong when reading staff from database!");
	   e.printStackTrace();
	   
	   return null;

	   
	  }
	 }
	 
	 // Using the type of department, the right object will be constructed. 
	 
	 public Department makeDepartment(ResultSet rs) throws SQLException {
	  
	  String type = rs.getString("type");
	  String name = rs.getString("name");
	  
	  if (type.equals("IPD")) {
	   
	   int max = rs.getInt("beds_max");
	   //int use = rs.getInt("beds_use"); NEEDS TO BE ADDED LATER
	   
	   return new InPatientDepart(name, max);
	   
	  }
	  
	  else if (type.equals("AMD")) {
	   

	   return new AdminDepart(name);
	   
	  }
	  
	  else if (type.equals("OPD")) {
	   
	   return new OutPatientDepart(name);
	   
	  }
	  
	  else {
	   
	   System.err.println("Something went wrong when trying to create a department!");
	   return null;
	   
	  }
	  
	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 5: Loading objects  _________________________ */
	 /* ######################################################################### */
	 
	 // This section deals with the methods needed to retrieve information from the database
	 // The methods are distinct in the sense that they take care of one small task each. 
	 // This improves readability, especially when these are combined in the boot-method. 
	 
	 // Basically a while-loop is made to iterate over the result set return from the query. 
	 // For each iteration as check if made such that the correct object is made. 
	 
	 
	 public Password loadLogin() {
		 
		 try {
			 
			 String query = "SELECT * FROM Login";
			 ResultSet rs = GET(query);
			 String username;
			 String hashvalue;
			 HashMap<String,String> hashmap = new HashMap<String,String>();
			 
			 while (rs.next()) {
				 
				 username = rs.getString("Username");
				 hashvalue = rs.getString("Hashvalue");
				 
				 if (username != null && hashvalue != null) {
					 hashmap.put(username, hashvalue);
				 }
				 
			 }
			 
			 if (!hashmap.isEmpty()) {
				 return new Password(hashmap);
			 } else {return new Password();}

		 } catch (Exception e) {
			 System.err.println("Login informations could not be retrieved. Please contact system administrator!");
			 return null;
		 }
		 
	 }
	 
	 public int loadStaffCounter() throws Throwable {
	  
	  ResultSet rs = GET("SELECT COUNT(*) FROM Staff");
	  rs.next();
	  int result = rs.getInt(1);
	  
	  return result;
	  
	 }
	 
	 public int loadPatientCounter() throws Throwable {
	  
	  ResultSet rs = GET("SELECT COUNT(*) FROM Patient");
	  rs.next();
	  int result = rs.getInt(1);
	  
	  return result;
	  
	 }
	 
	 public HashSet<Staff> loadStaff() throws Throwable {
	  
	  HashSet<Staff> staffset = new HashSet<Staff>();
	  ResultSet rs = GET("SELECT * FROM Staff");
	  
	  while(rs.next()) {
	   
	   staffset.add(makeStaff(rs));

	  }

	  return staffset;

	 }
	 
	 public HashSet<Patient> loadPatient() throws Throwable {
	  
	  HashSet<Patient> patientset = new HashSet<Patient>();
	  ResultSet rs = GET("SELECT * FROM Patient");
	  
	  while(rs.next()) {
	   
	   patientset.add(makePatient(rs));

	  }

	  return patientset;

	  
	  
	 }
	 
	 public HashSet<Department> loadDepartment() throws SQLException {
	  
	  HashSet<Department> departmentset = new HashSet<Department>();
	  ResultSet rs = GET("SELECT * FROM Department");
	  
	  while(rs.next()) {
	   
	   departmentset.add(makeDepartment(rs));

	  }

	  return departmentset;
	   
	 }
	 
	 /* ######################################################################### */
	 /* ____SECTION 6: Assemble hospital based on all information retrieved______ */
	 /* ######################################################################### */
	 
	 Hospital buildHospital(int staff_counter, int patient_counter, HashSet<Department> departmentset, HashSet<Staff> staffset, HashSet<Patient> patientset) {

	  
	  Hospital hospital = new Hospital();
	  
	  // The counters are set to make sure that creation of staff and patient will result in 
	  // person with different identification numbers. 
	  
	  Patient.counter = patient_counter;
	  Staff.counter = staff_counter;
	  
	  // Setting the hospital to have all information at a high level.
	  // In that way it is easy to make an overview of what the hospital contains. 
	  
	  hospital.setDepartSet(departmentset);
	  hospital.setAllPatientSet(patientset);
	  hospital.setAllStaff(staffset);
	  
	  Searcher s = new Searcher(hospital);
	  ChangeReg R = new ChangeReg(this);
	  
	  LinkedList<Staff> staffList = new LinkedList<Staff>(staffset);
	  
	  while (!staffList.isEmpty()) {
	   Staff staff = staffList.removeFirst();

	   // If staff does not belong to a department, we should not search for a department. Thus skip and add to hospital.
	   if (!(staff.getDepartment() == null)) {
	    LinkedList<Department> d_list = new LinkedList<Department>();
	 
	    // Searching for the specific department.
	    // In the way we construct staff and patients we make sure that 
	    // they can only contain a department name if it exists. 
	    // In that way the searcher WILL find a unique department. 
	    
	    d_list = s.departmentSearch(staff.getDepartment());
	 
	    if (!d_list.isEmpty()) {
	     Department d = d_list.getFirst();
	     R.add(d, staff);
	    }
	    
	    else {
	     System.err.println("ERROR: No matches on department: " + staff.getDepartment() + " with staff id  " + staff.getID());
	    } // ending else
	   } // ending if  
	  } // ending while-loop
	  
	  LinkedList<Patient> patientList = new LinkedList<Patient>(patientset);
	  
	  // The same procedure is repeated for patients. 
	  while (!patientList.isEmpty()) {
	   
	   Patient patient = patientList.removeFirst();

	   // If patient does not belong to a department, we should not search for a department. Thus skip and add to hospital.
	   if (!(patient.getDepartment() == null)) {
	    
	    LinkedList<Department> d = s.departmentSearch(patient.getDepartment());
	    
	    if (!d.isEmpty()) {
	     Department d_res = d.getFirst();
	     R.add(d_res, patient);
	    }
	    
	    else {
	     System.err.println("ERROR: No matches on department: " + patient.getDepartment() + " with staff id  " + patient.getID());
	    } // ending else
	   } // ending if
	  } // ending while-loop	  
	  
	  return hospital;


	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 7: Rebooting hospital _______________________ */
	 /* ######################################################################### */
	 
	 // This section is the key component. Booting the hospital means restoring the previous state.
	 // It combines all the loading methods and used the buildHospital() to insert the patients correctly. 
	 
	 Hospital boot() {
	  
	  try {
	   
	   HashSet<Patient> p_set = loadPatient();
	   HashSet<Staff> s_set = loadStaff();
	   HashSet<Department> d_set = loadDepartment();
	   int staff_counter = loadStaffCounter();
	   int patient_counter = loadPatientCounter();
	   return buildHospital(staff_counter, patient_counter, d_set, s_set, p_set);

	  } catch (Throwable e) {
	   // TODO Auto-generated catch block
	   System.err.println("System could not boot. Please see stack trace to find error!");
	   e.printStackTrace();
	   return null;
	  }
	  
	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 8: Cleaning hospital _______________________ */
	 /* ######################################################################### */
	 
	 // Restoring testing data before built. 
	 // In that case all tests will be consistent with data loaded from database. 
	 // It is used in test M1_changePatientInfo
	 
	 public void restore_for_testing_mode()  {

	  String query1 = "USE 0S1l397yKA";
	  String query18 ="TRUNCATE Login";
	  String query19 = "TRUNCATE Department";
	  String query20 = "TRUNCATE Staff";
	  String query21 = "TRUNCATE Patient"; 
	  String query2 = "INSERT INTO Department (name, beds_max, type) VALUES (\"ER\", 7, \"IPD\")";
	  String query3	= "INSERT INTO Department (name, beds_max, type) VALUES (\"Pediatric\", 2, \"IPD\")";
	  String query4 = "INSERT INTO Department (name, type) VALUES (\"Cardio\", \"OPD\")";
	  String query5 = "INSERT INTO Department (name, type) VALUES (\"IT\", \"AMD\")";
	  
	  String query6 = "INSERT INTO Patient (id, first_name, last_name, birthday, bed, alive, Department_name, address, tribe) VALUES (1, \"Jens\", \"Jensen\", \"24-09-1997\", 1, true, \"ER\", \"Jagtvej 69\", \"Zulu\")"; 
	  String query7 = "INSERT INTO Patient (id, first_name, last_name, birthday, bed, alive, Department_name, address, tribe) VALUES (2, \"Hans\", \"Hansen\", \"24-12-2000\", 2, true, \"ER\", \"Tagensvej 101\", \"Masai\")"; 
	  String query8 = "INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage) VALUES (3, \"Søren\", \"Sørensen\", \"24-09-1997\", true, \"Cardio\", \"Hellerup\", \"Venstre\", 1)"; 
	  String query9 = "INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage) VALUES (4, \"Lars\", \"Larsen\", \"20-12-1950\", true, \"Cardio\", \"Nordvestjylland\", \"Jysk\", 1)"; 
	  String query10 = "INSERT INTO Patient (id, first_name, last_name, birthday, bed, alive, Department_name, address, tribe) VALUES (5, \"Jens\", \"Jensen\", \"24-09-1997\", 1, true, \"Pediatric\", \"Jagtvej 69\", \"Zulu\")"; 
	  String query11 = "INSERT INTO Patient (id, first_name, last_name, birthday, bed, alive, Department_name, address, tribe) VALUES (6, \"Hans\", \"Hansen\", \"24-12-2000\", 2, true, \"Pediatric\", \"Tagensvej 101\", \"Masai\")"; 

	  String query12 =	"INSERT INTO Staff (id, first_name, last_name, birthday, Department_name, address, tribe, alive) VALUES (\"C5\", \"Mads\", \"Hansen\", \"23-04-2000\", \"IT\", \"Uganda\", \"Black-rocks Clan\", true)"; 
	  String query13 =	"INSERT INTO Staff (id, first_name, last_name, birthday, Department_name, address, tribe, alive) VALUES (\"D0\", \"Svend\", \"Nielsen\", \"01-01-1901\", \"ER\", \"Doktorvej\", \"Dansk\", true)"; 
	  String query14 =	"INSERT INTO Staff (id, first_name, last_name, birthday, Department_name, address, tribe, alive) VALUES (\"D2\", \"Lars\", \"Løkke\", \"01-01-1950\", \"Cardio\", \"Græsted\", \"Venstre\", true)"; 
	  String query15 =	"INSERT INTO Staff (id, first_name, last_name, birthday, Department_name, address, tribe, alive) VALUES (\"IT4\", \"Jens\", \"Hansen\", \"29-02-1996\", \"IT\", \"Norway\", \"Indian\", true)"; 
	  String query16 =	"INSERT INTO Staff (id, first_name, last_name, birthday, Department_name, address, tribe, alive) VALUES (\"N1\", \"Jonna\", \"Nielsen\", \"02-02-1902\", \"ER\", \"Ikke-doktorvej\", \"Tysk\", true)";
	  String query17 =	"INSERT INTO Staff (id, first_name, last_name, birthday, Department_name, address, tribe, alive) VALUES (\"N3\", \"Helle\", \"Thorning\", \"02-02-1960\", \"Cardio\", \"Herlev\", \"Gucci\", true)";
	  
	  try {
		  INSERT(query18);
		  INSERT(query19);
		  INSERT(query20);
		  INSERT(query21);
		  INSERT(query1);
		  INSERT(query2);
		  INSERT(query3);
		  INSERT(query4);
		  INSERT(query5);
		  INSERT(query6);
		  INSERT(query7);
		  INSERT(query8);
		  INSERT(query9);
		  INSERT(query10);
		  INSERT(query11);
		  INSERT(query12);
		  INSERT(query13);
		  INSERT(query14);
		  INSERT(query15);
		  INSERT(query16);
		  INSERT(query17);
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }

	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 9: Deleting data      _______________________ */
	 /* ######################################################################### */
	 
	 // In case a department needs to be removed from the system
	 // it is also deleted from the database
	 
	 void deleteDepartment(Department department) {
	  
	  String name = department.getName();
	  String query = String.format("DELETE FROM Department WHERE name = \"%s\" ", name);
	  INSERT(query);
	  
	 }
	 

	}