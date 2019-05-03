package stepDefinitions;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.*;
import framework.API;

public class M2_staffRegistration {
	
	API api;

	@Given("^that we are on a hospital$")
	public void that_we_are_on_a_hospital() {
	    
		api = API.getInstance();
		
	}

	@Given("^the hospital contain staff in departments$")
	public void the_hospital_contain_staff_in_departments() {
	    
		// This does actually not matter, since we test, whether they are being added. 
		
	}
	
	String user_message;

	@When("^I am entering sufficient clerk data$")
	public void i_am_entering_sufficient_clerk_data() {
		user_message = api.registerStaff ("I","I", "Clerk" ,"Lars", "Larsen", "Silkeborg", "Jysk billionær", 1, 1, 666);
	}

	@Then("^I get a message that the clerk was registered succesfully$")
	public void i_get_a_message_that_the_clerk_was_registered_succesfully() {  
		assertEquals(user_message, "The Clerk has been registered succesfully!");	
	}

	@When("^I am entering sufficient nurse data$")
	public void i_am_entering_sufficient_nurse_data() {
		user_message = api.registerStaff ("I","I", "Nurse" ,"Lars", "Larsen", "Silkeborg", "Jysk billionær", 1, 1, 666);
	}

	@Then("^I get a message that the nurse was registered succesfully$")
	public void i_get_a_message_that_the_nurse_was_registered_succesfully() {
		assertEquals(user_message, "The Nurse has been registered succesfully!");
	}

	@When("^I am entering sufficient doctor data$")
	public void i_am_entering_sufficient_doctor_data() {
		user_message = api.registerStaff ("I","I", "Doctor" ,"Lars", "Larsen", "Silkeborg", "Jysk billionær", 1, 1, 666);
	}

	@Then("^I get a message that the doctor was registered succesfully$")
	public void i_get_a_message_that_the_doctor_was_registered_succesfully() {
		assertEquals(user_message, "The Doctor has been registered succesfully!");
	}

	@When("^I am entering sufficient ictofficer data$")
	public void i_am_entering_sufficient_ictofficer_data() throws Throwable {
		user_message = api.registerStaff ("I","I", "ICTOfficer" ,"Lars", "Larsen", "Silkeborg", "Jysk billionær", 1, 1, 666);
	}

	@Then("^I get a message that the ictofficer was registered succesfully$")
	public void i_get_a_message_that_the_ictofficer_was_registered_succesfully() {
		assertEquals(user_message, "The ICTOfficer has been registered succesfully!");
	}
	
	String user_message2;

	@When("^I am entering insufficient staff data$")
	public void i_am_entering_insufficient_staff_data() {
		user_message = api.registerStaff ("I","I", "Nurse" ,"Lars", "", "Silkeborg", "Jysk billionær", 1, 1, 666);
		user_message2 = user_message = api.registerStaff ("I","I", "Kromutter" ,"Lars", "Larsen", "Silkeborg", "Jysk billionær", 1, 1, 666);
	}

	@Then("^I get a message$")
	public void i_get_a_message() {
		assertEquals(user_message, "Unsuccesful registration!");
		assertEquals(user_message2, "Unsuccesful registration!");
		
	}


}