package stepDefinitions;

import static org.junit.Assert.*;

import cucumber.api.java.en.*;
import framework.API;

public class M1_RegisterPatient {

	String message;
	String firstName = "firstName";
	String lastName = "lastName";
	String tribe = "tribe";
	String address = "address";
	int day = 31;
	int month = 12;
	int year = 1990;
	boolean alive = true;
	
	API api = API.getInstance();

	
	@Given("^I have a patient I want to register$")
	public void I_have_a_patient_I_want_to_register() {
		
		assertTrue(true);
		
	}
	
	@When("^I am entering sufficient patient data$")
	public void i_am_entering_sufficient_patient_data() {
		
		message = api.registerPatient(firstName, lastName, tribe, address, day, month, year, alive);

	}

	@Then("^I get a message that the patient was registered succesfully$")
	public void i_get_a_message_that_the_patient_was_registered_succesfully() throws Throwable {

		assertEquals(message, "Patient registered succesfully.");
		
	}
	
	@When("^I am not entering lastname$")
	public void i_am_not_entering_lastname() {	
		message = api.registerPatient(firstName, "", tribe, address, day, month, year, alive);
	}

	@When("^I am not entering first name$")
	public void i_am_not_entering_first_name() {
		message = api.registerPatient("", lastName, tribe, address, day, month, year, alive);
	}

	@When("^I am not entering adress$")
	public void i_am_not_entering_adress() {
		message = api.registerPatient(firstName, lastName, tribe, "", day, month, year, alive);
	}

	@When("^I am not entering tribe$")
	public void i_am_not_entering_tribe() {
		message = api.registerPatient(firstName, lastName, "", address, day, month, year, alive);
	}

	@When("^I am entering birthday wrongly$")
	public void i_am_entering_birthday_wrongly() {
		message = api.registerPatient(firstName, lastName, tribe, address, 32, month, year, alive);
	}
	
	@Then("^I get a message that additional information is needed$")
	public void i_get_a_message_that_additional_information_is_needed() {
		assertTrue(message.equals("Additional information is needed."));
	}
}
