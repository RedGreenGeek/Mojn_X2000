//package framework;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashSet;
//import java.util.Iterator;
//
//import framework.Departments.AdminDepart;
//import framework.Departments.HealthCare.InPatientDepart;
//import framework.Departments.HealthCare.OutPatientDepart;
//import framework.person.Patient;
//import framework.person.Staff;
//import framework.person.staff.*;
//
//public class SystemDatabase {
//	
//	// The system database constitute a composition with the other classes of the hospital. 
//	// The reason being that it does not make sense to store data without specifications of what to store.
//	// Thus the solid principles are violated to make the system running. 
//	
//	// The database design:
//	// Foreign Key contraints have been made for Staff and Patients. Every person and staff can hold a reference to a department. 
//	// 
//	// Restrict  ->  throws error if it is violated
//	// Cascade -> children will be deleted, when parents are deleted
//	// SET NULL  ->  if department is deleted, the children will have that reference be null. 
//	
//	private static SystemDatabase instance;
//	
//	private String port;
//	private String URL;
//	private String database;
//	private String username;
//	private String password;
//	
//	private Connection myConnection;
//	
//	/* ######################################################################### */
//	/* ______________ SECTION 1: Connection and singleton-creation _____________ */
//	/* ######################################################################### */
//
//	private SystemDatabase() {
//
//		this.port = "3306/";
//		this.URL = "jdbc:mysql://localhost:3306/mydb";
//		this.database = "mydb";
//		this.username = "root";
//		this.password = "AGILE2019";
//
//		EstablishConnection();
//
//	}
//	
//	public static synchronized SystemDatabase getInstance() {
//		
//		if (instance==null) {
//			instance = new SystemDatabase();
//			return instance;
//		}
//		else {
//			return instance;
//		}
//	}
//	
//	private void EstablishConnection() {
//
//		try {
//
//			// 1. Get connection to MySQL database
//
//			myConnection = DriverManager.getConnection(URL, username, password);
//
//			// 2. Create a statement  ->  connect to database
//			Statement myStatement = myConnection.createStatement();
//			myStatement.executeUpdate("USE " + database + ";");
//
//			myStatement.close();
//
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	/* ######################################################################### */
//	/* _____________ SECTION 2: Reading from and writing to database ___________ */
//	/* ######################################################################### */
//	
//	private ResultSet GET(String query) {
//		
//		try {	
//			Statement st = myConnection.createStatement();	
//			return st.executeQuery(query);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//		
//	}
//	
//	private void INSERT(String query) {
//		
//		try {	
//			Statement st = myConnection.createStatement();	
//			st.executeUpdate(query);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	/* ######################################################################### */
//	/* ___________ SECTION 3: Construction of objects from ResultSet ___________ */
//	/* ######################################################################### */
//	
//	
//	// Introducing a help function that takes a certain result from a query and outputs a patient
//	// The inputs in .getString(INPUT) corresponds to the name of the columns in the database tables
//	
//	private Patient makePatient(ResultSet rs) {
//		
//		try {
//		
//			String firstName = rs.getString("first_name");
//			String lastName = rs.getString("last_name");
//			String[] birthday = rs.getString("birthday").split("-");
//		    String address = rs.getString("address");
//			String tribe = rs.getString("tribe");
//			boolean alive = rs.getBoolean("alive");
//			String department = rs.getString("Department_name");
//			int priority = rs.getInt("priority");
//			int id = rs.getInt("id"); // Not used right now
//			
//			return new Patient(firstName, lastName, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), alive, department);
//			
//		} catch (Exception e) {
//			
//			System.out.println("Something went wrong when trying to read patient from database!");
//			
//			return null;
//			
//		}
//	}
//	
//	// Introducing a help function that takes a certain result from a query and outputs a staff
//	private Staff makeStaff(ResultSet rs) {
//		
//		try {
//		
//			String jobtype = rs.getString("jobtype");
//			String first_name = rs.getString("first_name");
//			String last_name = rs.getString("last_name");
//			String address = rs.getString("address");
//			String tribe = rs.getString("tribe");
//			String[] birthday = rs.getString("birthday").split("-");
//			String department = rs.getString("department"); // We need to put that employee back into an department
//			Staff employee;
//	
//			
//			if (jobtype.equals("Clerk")) {
//				
//				employee = new Clerk(first_name, last_name, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), "");
//				
//				
//			}
//			
//			else if (jobtype.equals("Doctor")) {
//				
//				
//				employee = new Doctor(first_name, last_name, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), "");
//				
//				
//			}
//			
//			else if (jobtype.equals("ICTOfficer")) {
//				
//				
//				employee = new ICTOfficer(first_name, last_name, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), "");
//				
//				
//			}
//			
//			else if (jobtype.equals("Nurse")) {
//				
//				
//				employee = new Nurse(first_name, last_name, address, tribe, Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]), "");
//				
//				
//			} else {return null;}
//			
//			return employee;
//			
//		} catch (Exception e) {
//			
//			System.out.println("Something went wrong when reading staff from database!");
//			
//			return null;
//
//			
//		}
//	}
//	
//	/* ######################################################################### */
//	/* SECTION 4: Construction of all objects from a certain table from database */
//	/* ######################################################################### */
//	
//	public HashSet<Patient> loadPatients(String query){
//		
//		try {
//			
//			HashSet<Patient> patients = new HashSet<Patient>();
//			ResultSet rs = GET(query);
//			
//			while (rs.next()) {
//				
//				patients.add(makePatient(rs));
//				
//			}
//			
//			return patients;
//			
//		} catch (Exception e) {
//			
//			System.out.println("Something when wrong when trying to load patients!");
//			
//			return null;
//		
//		}
//		
//	}
//	
//	public HashSet<Staff> loadStaff(String query){
//		
//		try {
//			
//			HashSet<Staff> staff = new HashSet<Staff>();
//			ResultSet rs = GET(query);
//			
//			while (rs.next()) {
//				
//				staff.add(makeStaff(rs));
//				
//			}
//			
//			return staff;
//			
//		} catch (Exception e) {
//			
//			System.out.println("Something when wrong when trying to load patients!");
//			
//			return null;
//		
//		}
//		
//	}
//	
//	public HashSet<Department> loadDepartments(){
//		
//		HashSet<Department> departments = new HashSet<Department>();
//		
//		try {
//	
//			ResultSet rs = GET("SELECT * FROM Department");
//			
//			while (rs.next()) {
//				
//				String type = rs.getString("type");
//				String name = rs.getString("name");
//				
//				if (type.equals("Admin")) {
//					
//					
//					/* Building a hash set of staff */
//	
//					String query = String.format("SELECT * FROM Staff WHERE Department_name = '%s'", name);
//					HashSet<Staff> staff = loadStaff(query);
//					
//					//departments.add(new AdminDepart(name, staff));
//
//				}
//				
//				else if (type.equals("InPatient")) {
//					
//					int max_beds = rs.getInt("beds_max");
//					
//					String query1 = String.format("SELECT * FROM Staff WHERE Department_name = '%s'", name);
//					String query2 = String.format("SELECT * FROM Patient WHERE Department_name = '%s'", name);
//					HashSet<Staff> staffset = loadStaff(query1);
//					HashSet<Staff> patientset = loadStaff(query2);
//					
//					//departments.add(new InPatientDepart(name, staffset, patientset, max_beds));
//
//					
//				}
//				
//				else if (type.equals("OutPatient")) {
//
//					String query1 = String.format("SELECT * FROM Staff WHERE Department_name = '%s'", name);
//					String query2 = String.format("SELECT * FROM Patient WHERE Department_name = '%s'", name);
//					HashSet<Staff> staffset = loadStaff(query1);
//					HashSet<Staff> patientset = loadStaff(query2);
//					
//					//departments.add(new OutPatientDepart(name, staffset, patientset));
//				}
//				
//				else {
//					
//				}
//
//			}
//			
//			
//		} catch (Exception e) { System.out.println("Something went wrong when loading departments from database!");}
//		
//		return departments;
//		
//	}
//	
//	public Hospital boot() {
//		
//		// Type errors again  ->   Huge fuckup
//		Hospital hospital = new Hospital();
//		
//		// The following is done, because the staffset and patientset needs to be pointers to the actual objects. Not sets of new persons.
//		HashSet<Department> departSet = loadDepartments();
//		hospital.setDepartSet(departSet);
//		//hospital.setAllStaff(hospital.getAllStaff());
//		hospital.setAllPatientSet(hospital.getAllPatient());
//		
//		return hospital;
//
//		
//	}
//	
//	/* ######################################################################### */
//	/* _______________ SECTION 5: Writing all objects to database ______________ */
//	/* ######################################################################### */
//	
//	protected void writePatient() {
//		
//	}
//	
//	protected void writeStaff() {
//		
//	}
//	
//	protected void writeDepartments(HashSet<Department> data) {
//		
//		String name;
//		String type;
//		String query;
//		
//		Iterator<Department> iterator = data.iterator();
//		
//		while (iterator.hasNext()) {
//			
//			Department department = iterator.next();
//			name = department.getName();
//			
//			if (department instanceof AdminDepart) {
//
//				type = "Admin";
//				query = String.format("INSERT IGNORE INTO Department ('name', 'type')"
//						+ "values ('%s','%d','%d', '%s')", name, type) ;
//				
//				try {
//
//					INSERT(query);
//					
//				
//				} catch (Exception e) { System.out.println("Failed to create a AdminDepart!");}
//				
//			}
//			
//			else if (department instanceof InPatientDepart) {
//				
//				type = "InPatient";
//				int beds_max = ((InPatientDepart) department).get_max_beds();
//				int beds_use = ((InPatientDepart) department).get_beds_in_use();
//				query = String.format("INSERT IGNORE INTO Department ('name', 'beds_max', 'beds_use', 'type') "
//						+ "values ('%s','%d','%d', '%s')", name, beds_max, beds_use, type) ;
//				
//				try {
//
//					INSERT(query);
//				
//				} catch (Exception e) { System.out.println("Failed to create a InPatientDepart!");}
//				
//				
//			}
//			
//			else if (department instanceof OutPatientDepart) {
//				
//				type = "OutPatient";
//				int beds_max = ((InPatientDepart) department).get_max_beds();
//				int beds_use = ((InPatientDepart) department).get_beds_in_use();
//				query = String.format("INSERT IGNORE INTO Department ('name', 'beds_max', 'beds_use', 'type') "
//						+ "values ('%s','%d','%d', '%s')", name, beds_max, beds_use, type) ;
//				
//				try {
//
//					INSERT(query);
//				
//				} catch (Exception e) { System.out.println("Failed to create a InPatientDepart!");}
//				
//			}
//			
//			else {
//				
//				System.out.println("An error occured. Department does not exist!");
//				
//			}
//		}
//	}
//	
//	
//// Nodes:
//	
//// Static counter in patients
//// Why does AdminDepart accept persons?
//// Why does all departments do that?
//
//
//}
