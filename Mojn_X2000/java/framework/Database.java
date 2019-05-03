package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import framework.Departments.AdminDepart;
import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.Patient;
import framework.person.Staff;
import framework.person.staff.Clerk;
import framework.person.staff.Doctor;
import framework.person.staff.ICTOfficer;
import framework.person.staff.Nurse;

public class Database {
 
 private static Database instance;
 public static String DEFAULT = "local";
 public static String REMOTE = "remote";
 private String driver_host;
 private String URL;
 private String database;
 private String username;
 private String password;
 
 
 private Connection myConnection;
 
 /* ######################################################################### */
 /* __________ SECTION 1: Connection and singleton-creation _________ */
 /* ######################################################################### */

 // The constructor has been made private in order to make the singleton design-pattern. 
 // In this way multiple connections to the database are avoided. 
 // The input to the constructor is given in order to distinguish between the local and the remote database. 
 
 private Database(String name_of_database) {
  
  if (name_of_database.equals("local")) {

   this.driver_host = "jdbc:mysql://localhost:3306/";
   this.database = "mydb";
   this.username = "root";
   this.password = "AGILE2019";
   this.URL = driver_host + database;
 
   EstablishConnection();
   
  } else if (name_of_database.equals("remote")) {
   
   this.driver_host = "jdbc:mysql://remotemysql.com:3306/";
   this.database = "NgJ59PJEgK ";
   this.username = "NgJ59PJEgK ";
   this.password = "k0LOT2B4MF";
   this.URL = driver_host + database;
 
   EstablishConnection();

  } else {System.out.println("Database connection not recognized!");};
   
 }
 
 // The method getInstance ensures that only 1 instance of the database is created.
 // It can be called statically, which is needed since the constructor cannot be accessed outside of the class.
 
 public static synchronized Database getInstance(String name_of_database) {
  
  if (instance==null) {
   instance = new Database(name_of_database);
   return instance;
  }
  else {
   return instance;
  }
 }
 
 // Establish connection is the method that is called upon construction of the instance. 
 // In case of errors with driver or port connected, the exception is thrown, and the stack trace is printed. 
 
 private void EstablishConnection() {

  try {

   myConnection = DriverManager.getConnection(URL, username, password);

   Statement myStatement = myConnection.createStatement();
   myStatement.executeUpdate("USE " + database + ";");

   myStatement.close();

  }

  catch (Exception e) {
   System.out.println("Connecting to database failed!");
  }
 }
 
 // If the getInstance() has been called, and the connection has been established, the isConnected() will return true. 
 
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
   System.out.println("Could not load from database. Please see the stack trace below ..");
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
	   
	   System.out.println("Something went wrong when trying to write a department!");
	   return "ERROR";
	   
	  }

	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 4: Construction of objects  _________________ */
	 /* ######################################################################### */
	 
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
	   
	   System.out.println("Something went wrong when trying to read patient from database!");
	   e.printStackTrace();
	   
	   return null;
	   
	  }
	 }
	 
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
	   
	   if (department == null) {
	    System.out.println("DEPARTMENT IS NULL!");
	   }

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
	   
	   System.out.println("Something went wrong when reading staff from database!");
	   e.printStackTrace();
	   
	   return null;

	   
	  }
	 }
	 
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
	   
	   System.out.println("Something went wrong when trying to create a department!");
	   return null;
	   
	  }
	  
	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 5: Loading objects  _________________________ */
	 /* ######################################################################### */
	 
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
	 /* ___________ SECTION 6: Connector  _______________________________ */
	 /* ######################################################################### */
	 
	 Hospital buildHospital(int staff_counter, int patient_counter, HashSet<Department> departmentset, HashSet<Staff> staffset, HashSet<Patient> patientset) {

	  
	  System.out.println(staffset);
	  Hospital hospital = new Hospital();
	  
	  Patient.counter = patient_counter;
	  Staff.counter = staff_counter;
	  
	  hospital.setDepartSet(departmentset);
	  hospital.setAllPatientSet(patientset);
	  hospital.setAllStaff(staffset);
	  
	  Iterator<Department> I_d = departmentset.iterator();
	  
	  System.out.println("STATUS OF DEPARTMENTS BEFORE BUILT!!!!");
	  
	  while (I_d.hasNext()) {
	   
	   Department d = I_d.next();
	   
	   System.out.println(d.getName() + " with patients: " + d.getPatient());
	   System.out.println(d.getName() + " with staff: " + d.getStaff());
	   
	   
	  }
	  
	  Searcher s = new Searcher(hospital);
	  ChangeReg R = new ChangeReg();
	  
	  LinkedList<Staff> staffList = new LinkedList<Staff>(staffset);
	  
	  while (!staffList.isEmpty()) {
	   Staff staff = staffList.removeFirst();

	   // If staff does not belong to a department, we should not search for a department. Thus skip and add to hospital.
	   if (!(staff.getDepartment() == null)) {
	    LinkedList<Department> d_list = new LinkedList<Department>();
	 
	    d_list = s.departmentSearch(staff.getDepartment());
	 
	    if (!d_list.isEmpty()) {
	     Department d = d_list.getFirst();
	     R.add(d, staff);
	    }
	    
	    else {
	     System.out.println("ERROR: No matches on department: " + staff.getDepartment() + " with staff id  " + staff.getID());
	    }
	   }  
	  }
	  
	  LinkedList<Patient> patientList = new LinkedList<Patient>(patientset);
	  
	  while (!patientList.isEmpty()) {
	   
	   Patient patient = patientList.removeFirst();
	   System.out.println(patient);

	   // If patient does not belong to a department, we should not search for a department. Thus skip and add to hospital.
	   if (!(patient.getDepartment() == null)) {
	    
	    LinkedList<Department> d = s.departmentSearch(patient.getDepartment());
	    
	    if (!d.isEmpty()) {
	     Department d_res = d.getFirst();
	     R.add(d_res, patient);
	     System.out.println(d_res.getPatient());
	    }
	    
	    else {
	     System.out.println("ERROR: No matches on department: " + patient.getDepartment() + " with staff id  " + patient.getID());
	    }
	   }
	  }
	  
	  System.out.println("Built succesfully!");
	  
	  Iterator<Department> iter = departmentset.iterator();
	  
	  System.out.println("STATUS OF DEPARTMENTS AFTER BUILT!!!!");
	  
	  while (iter.hasNext()) {
	   
	   Department d = iter.next();
	   
	   System.out.println(d.getName() + " with patients: " + d.getPatient());
	   System.out.println(d.getName() + " with staff: " + d.getStaff());
	   
	   
	  }
	  
	  
	  return hospital;


	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 7: Rebooting hospital _______________________ */
	 /* ######################################################################### */
	 
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
	   System.out.println("System could not boot. Please see stack trace to find error!");
	   e.printStackTrace();
	   return null;
	  }
	  
	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 8: Cleaning hospital _______________________ */
	 /* ######################################################################### */
	 
	 void clean() throws Throwable {
	  
	  INSERT("TRUNCATE Patient");
	  INSERT("TRUNCATE Staff");
	  INSERT("TRUNCATE Department");
	  

	 }
	 
	 /* ######################################################################### */
	 /* ___________ SECTION 9: Deleting data      _______________________ */
	 /* ######################################################################### */
	 
	 
	 // Deletes the patient from the database utilizing that id's are unique
	 
	 void deletePatient(Patient patient) {
	  
	  String id = patient.getID();
	  String query = String.format("DELETE FROM Patient WHERE id = %s", id);
	  INSERT(query);
	  
	 }
	 
	 // Deletes the patient from the database utilizing that id's are unique
	 
	 void deleteStaff(Staff staff) {
	  
	  String id = staff.getID();
	  String query = String.format("DELETE FROM Staff WHERE id = %s", id);
	  INSERT(query);
	  
	  
	 }
	 
	 // Deletes the department from the database utilizing that the names are unique
	 
	 void deleteDepartment(Department department) {
	  
	  String name = department.getName();
	  String query = String.format("DELETE FROM Department WHERE name = \"%s\" ", name);
	  INSERT(query);
	  
	 }
	 

	}