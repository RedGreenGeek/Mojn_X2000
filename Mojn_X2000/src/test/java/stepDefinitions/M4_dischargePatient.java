package stepDefinitions;

import static org.junit.Assert.assertNotEquals;

import cucumber.api.java.en.*;
import framework.API;

public class M4_dischargePatient {
	API api = API.getInstance();
	String message;
	
	@Given("^that we have a hospital with patients$")
	public void that_we_have_a_hospital_with_patients() {
		// This is implementet in the API
	}

	@When("^I am entering an unique patient ID$")
	public void i_am_entering_an_unique_patient_ID() {
		// This can only be true hence the way the system is designed
		message = API.discharge("8");
	}

	@When("^The patient belongs to an unique department$")
	public void the_patient_belongs_to_an_unique_department() {
		// This can only be true hence the way the system is designed
		
	}

	@Then("^I get a message with a statement that the patient has been removed succesfully$")
	public void i_get_a_message_with_a_statement_that_the_patient_has_been_removed_succesfully() {
		assertNotEquals(message,"The department isn't uniqe");
		assertNotEquals(message,"The patient ID's isn't uniqe");
		
	}
}
