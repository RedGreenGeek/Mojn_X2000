package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import framework.person.Patient;
import framework.person.Staff;

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
	
	private Database() {

		this.driver_host = "jdbc:mysql://localhost:3306/";
		this.database = "mydb";
		this.username = "root";
		this.password = "AGILE2019";
		this.URL = driver_host + database;

		EstablishConnection();

	}

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
		
		if (triage != null && bed_location != null) {

			String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage, bed) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, \"%s\",\"%s\", \"%s\", %d, %d) ",
					id, firstName, lastName, birthday, alive, 
					department, address, tribe, triage, bed_location);
			 
			String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
					+ "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\", triage = \"%d\", bed = \"%d\"", id, firstName, lastName, birthday, alive, 
					department, address, tribe, triage, bed_location);
			
			query = query1 + query2;
		
		} else if (triage == null && bed_location != null) {
			
			String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, bed) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, \"%s\",\"%s\", \"%s\", %d) ",
					id, firstName, lastName, birthday, alive, 
					department, address, tribe, bed_location);
			 
			String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
					+ "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\", bed = \"%d\"", id, firstName, lastName, birthday, alive, 
					department, address, tribe, bed_location);
			
			query = query1 + query2;
			
		} else if (triage != null && bed_location == null) {
			
			String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe, triage) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, \"%s\",\"%s\", \"%s\", %d) ",
					id, firstName, lastName, birthday, alive, 
					department, address, tribe, triage);
			 
			String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
					+ "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\", triage = \"%d\"", id, firstName, lastName, birthday, alive, 
					department, address, tribe, triage);
			
			query = query1 + query2;
			
		} else {
			
			String query1 = String.format("INSERT INTO Patient (id, first_name, last_name, birthday, alive, Department_name, address, tribe) VALUES (\"%s\",\"%s\",\"%s\",\"%s\", %b, \"%s\",\"%s\", \"%s\") ",
					id, firstName, lastName, birthday, alive, 
					department, address, tribe);
			 
			String query2 =  String.format(" ON DUPLICATE KEY UPDATE id = \"%s\", first_name = \"%s\", last_name = \"%s\", birthday = \"%s\","
					+ "alive = %b, Department_name = \"%s\", address = \"%s\", tribe = \"%s\"", id, firstName, lastName, birthday, alive, 
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
		
		String query = String.format("INSERT INTO Patient ('id', 'first_name', 'last_name', "
				+ "'birthday', 'Department_name', 'address', 'tribe', 'alive')"
				+ " ON DUPLICATE KEY UPDATE " + "values ('%s','%s','%s', '%s', '%s', '%s', '%s', '%b')", firstName, lastName, birthday, address, 
				tribe, alive, department, id);

		return INSERT(query);
		
	}

}
