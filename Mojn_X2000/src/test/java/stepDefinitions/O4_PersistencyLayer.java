package stepDefinitions;

import static org.junit.Assert.*;

import java.util.HashSet;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import framework.API;
import framework.Department;
import framework.SystemDatabase;
import framework.person.Patient;

public class O4_PersistencyLayer {
	
	API api;
	SystemDatabase DB;
	HashSet<Department> dp = new HashSet<Department>();
	
	@Given("^a hospital using the management system$")
	public void a_hospital_using_the_management_system() {
	    
		// The hospital uses the management system
		
	}

	@When("^some staff start the system on their computer$")
	public void some_staff_start_the_system_on_their_computer() {
	    
		api = API.getInstance();
		
	}

	@Then("^the system is connected to the database$")
	public void the_system_is_connected_to_the_database() {
	    
		assertTrue(api.isConnected());
		
	}
	
	@When("^some staff adds a patient$")
	public void some_staff_adds_a_patient() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
		
	}

	@Then("^the patient is stored in the database$")
	public void the_patient_is_stored_in_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a staff in the system$")
	public void a_staff_in_the_system() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^some staff adds a staff$")
	public void some_staff_adds_a_staff() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the staff is stored in the database$")
	public void the_staff_is_stored_in_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^some staff adds a department$")
	public void some_staff_adds_a_department() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the department is stored in the database$")
	public void the_department_is_stored_in_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a database with patient information$")
	public void a_database_with_patient_information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^all patients are loaded from the database$")
	public void all_patients_are_loaded_from_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a database with staff information$")
	public void a_database_with_staff_information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^all staff are loaded from the database$")
	public void all_staff_are_loaded_from_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a database with department information$")
	public void a_database_with_department_information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^all departments are loaded from the database$")
	public void all_departments_are_loaded_from_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the departments contains pointers to patients$")
	public void the_departments_contains_pointers_to_patients() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the departments contains pointers to staff$")
	public void the_departments_contains_pointers_to_staff() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a database with all hospital information$")
	public void a_database_with_all_hospital_information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the entire hospital is reconstructed to match the last state$")
	public void the_entire_hospital_is_reconstructed_to_match_the_last_state() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	


}
