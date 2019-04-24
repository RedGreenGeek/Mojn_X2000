package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import framework.person.Patient;
import framework.person.Staff;

public class Database {
	
	private static Database instance;

	private String URL;
	private String database;
	private String username;
	private String password;
	
	private Connection myConnection;
	
	/* ######################################################################### */
	/* ______________ SECTION 1: Connection and singleton-creation _____________ */
	/* ######################################################################### */

	private Database() {

		this.URL = "jdbc:mysql://localhost:3306/mydb";
		this.database = "mydb";
		this.username = "root";
		this.password = "AGILE2019";

		EstablishConnection();

	}
	
	public static synchronized Database getInstance() {
		
		if (instance==null) {
			instance = new Database();
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
			return "Success!";
		} catch(Exception e) {
			return "ERROR";
		}
		
	}
	
	/* ######################################################################### */
	/* _______________ SECTION 3: Writing all objects to database ______________ */
	/* ######################################################################### */
	
	public String writePatient(Patient p) {
		
		String firstName = p.getFirstName();
		String lastName = p.getLastName();
		String birthday = p.getBirthday();
	    String address = p.getAdress();
		String tribe = p.getTribe();
		boolean alive = p.isAlive();
		String department = p.getDepartment();
		String triage = p.getTriage();
		String id = p.getID();
		String bed_location = p.getBedLocation();
		
		String query = String.format("INSERT INTO Patient ('id', 'first_name', 'last_name', "
				+ "'birthday', 'bed', 'alive', 'Department_name', 'address', 'tribe', 'triage')"
				+ " ON DUPLICATE KEY UPDATE " + "values ('%s','%s','%s', '%s', '%s', '%s', '%b', '%s', '%s', '%s')", firstName, lastName, birthday, bed_location, address, 
				tribe, alive, department, triage, id) ;

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
