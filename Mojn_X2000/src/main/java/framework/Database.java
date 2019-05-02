package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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
	public static String DEFAULT = "mydb";
	private String driver_host;
	private String URL;
	private String database;
	private String username;
	private String password;
	
	private Connection myConnection;
	
	/* ######################################################################### */
	/* ______________ SECTION 1: Connection and singleton-creation _____________ */
	/* ######################################################################### */

	private Database(String name_of_database) {

		this.driver_host = "jdbc:mysql://localhost:3306/";
		this.database = name_of_database;
		this.username = "root";
		this.password = "AGILE2019";
		this.URL = driver_host + database;

		EstablishConnection();

	}
	
	public static synchronized Database getInstance(String name_of_database) {
		
		if (instance==null) {
			instance = new Database(name_of_database);
			return instance;
		}
		else {
			return instance;
		}
	}
	
	private void EstablishConnection() {

		try {
			// 1. Get connection to MySQL database

			myConnection = DriverManager.getConnection(URL, username, password);

			// 2. Create a statement  ->  connect to database
			Statement myStatement = myConnection.createStatement();
			myStatement.executeUpdate("USE " + database + ";");

			myStatement.close();
			
			System.out.println("Successful connection to database!");
		}

		catch (Exception e) {
			System.out.println("Connecting to database failed!");
		}
	}
	
	public boolean isConnected() {
		return myConnection != null;
	}
	
	/* ######################################################################### */
	/* _______________ SECTION 2: Adding two types of queries __________________ */
	/* ######################################################################### */
	
	private ResultSet GET(String query) {
		
		try {	
			Statement st = myConnection.createStatement();	
			return st.executeQuery(query);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
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
	/* _______________ SECTION 3: Writing all objects to database ______________ */
	/* ######################################################################### */
	
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
		
		if (department == null) {
			
			String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage, bed) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, null,\"%s\", \"%s\", null, null) ",
					id, firstName, lastName, birthday, alive, address, tribe);
			 
			String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
					+ "alive = %b, Department_name = null, address = \"%s\", tribe = \"%s\", triage = null, bed = null", id, firstName, lastName, birthday, alive, address, tribe);
			
			query = query1 + query2;
			
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
	/* _______________ SECTION 4: Construction of objects  _____________________ */
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
	/* _______________ SECTION 5: Loading objects  _____________________________ */
	/* ######################################################################### */
	
	public int loadStaffCounter() throws Throwable {
		
		ResultSet rs = GET("SELECT * FROM Additional");
		rs.next();
		int result = rs.getInt("staff_counter");
		
		return result;
		
	}
	
	public int loadPatientCounter() throws Throwable {
		
		ResultSet rs = GET("SELECT * FROM Additional");
		rs.next();
		int result = rs.getInt("patient_counter");
		
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
	/* _______________ SECTION 6: Connector  ___________________________________ */
	/* ######################################################################### */
	
	Hospital buildHospital(int staff_counter, int patient_counter, HashSet<Department> departmentset, HashSet<Staff> staffset, HashSet<Patient> patientset) {

		Hospital hospital = new Hospital();
		
		Patient.counter = patient_counter;
		Staff.counter = staff_counter;
		
		hospital.setDepartSet(departmentset);
		
		Searcher s = new Searcher(hospital);
		ChangeReg R = new ChangeReg();
		
		LinkedList<Staff> staffList = new LinkedList<Staff>(staffset);
		
		while (!staffList.isEmpty()) {
			Staff pat = staffList.getFirst();
			LinkedList<Department> d_list = new LinkedList<Department>();

			d_list = s.departmentSearch(pat.getDepartment());

			if (!d_list.isEmpty()) {
				Department d = d_list.getFirst();
				Staff s1 = staffList.removeFirst();
				R.add(d, s1);
			}
			
			else {
				Staff s1 = staffList.removeFirst();
				System.out.println("No matches on department: " + pat.getDepartment());
			}

		}
		
		LinkedList<Patient> patientList = new LinkedList<Patient>(patientset);
		
		while (!patientList.isEmpty()) {
			LinkedList<Department> d = s.departmentSearch(patientList.getFirst().getDepartment());
			
			if (!d.isEmpty()) {
				Department d_res = d.getFirst();
				Patient p1 = patientList.removeFirst();
				R.add(d_res, p1);
			}
			
			else {
				Patient p1 = patientList.removeFirst();
				System.out.println("No matches on department: " + p1.getDepartment());
			}

		}
		
		
		hospital.setDepartSet(departmentset);
		hospital.setAllPatientSet(patientset);
		hospital.setAllStaff(staffset);
		
		return hospital;


	}
	
	/* ######################################################################### */
	/* _______________ SECTION 7: Rebooting hospital ___________________________ */
	/* ######################################################################### */
	
	Hospital boot() throws Throwable {
		
		HashSet<Patient> p_set = loadPatient();
		HashSet<Staff> s_set = loadStaff();
		HashSet<Department> d_set = loadDepartment();
		int staff_counter = loadStaffCounter();
		int patient_counter = loadPatientCounter();
		return buildHospital(staff_counter, patient_counter, d_set, s_set, p_set);
		
	}
	
	/* ######################################################################### */
	/* _______________ SECTION 8: Cleaning hospital ___________________________ */
	/* ######################################################################### */
	
	void clean() throws Throwable {
		
		INSERT("TRUNCATE Patient");
		INSERT("TRUNCATE Staff");
		INSERT("TRUNCATE Department");
		

	}
	
	/* ######################################################################### */
	/* _______________ SECTION 9: Deleting data      ___________________________ */
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
