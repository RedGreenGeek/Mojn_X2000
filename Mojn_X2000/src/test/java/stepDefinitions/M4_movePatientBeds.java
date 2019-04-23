package stepDefinitions;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.*;
import framework.API;

public class M4_movePatientBeds {
	API api = API.getInstance();
	String message;
	String ID = "6";
	
	
	@Given("^that we have a hospital with indepartments$")
	public void that_we_have_a_hospital_with_indepartments() {
		// The API has such a hospital
	}

	@When("^I am writing a valid patient ID$")
	public void i_am_writing_a_valid_patient_ID() {
		// All the inputs will be given in the last when call
	}

	@When("^I am entering an existing indepartment$")
	public void i_am_entering_an_existing_indepartment() {
		// All the inputs will be given in the last when call
	}

	@When("^I am entering a non occupied bedNo$")
	public void i_am_entering_a_non_occupied_bedNo() {
		message = API.movePatientBed("6", "1", "Pediatric");
		
	}

	@Then("^I get a return message with the statement that the patient was moved succesfully$")
	public void i_get_a_return_message_with_the_statement_that_the_patient_was_moved_succesfully() {
		assertEquals(message, "The patient was moved succesfully");
	}

	@When("^I am writing an invalid patient ID$")
	public void i_am_writing_an_invalid_patient_ID() {
		message = API.movePatientBed("6340", "1", "Pediatric");
	}

	@Then("^I get an error message that the move between beds was unsuccesful$")
	public void i_get_an_error_message_that_the_move_between_beds_was_unsuccesful() {
		assertEquals(message, "The patient wasn't moved cause to invalid ID");
		
	}

	@When("^I am trying to move to a non existent department or multiple departments$")
	public void i_am_trying_to_move_to_a_non_existent_department_or_multiple_departments() {
		message = API.movePatientBed("6", "1", "Non existent");
		
	}

	@Then("^I get a message that the move between beds was unsuccesful$")
	public void i_get_a_message_that_the_move_between_beds_was_unsuccesful() {
		assertEquals(message, "The new department matches several or no departments");
	}

	@When("^I am trying to move to a department that isn't an indepartment$")
	public void i_am_trying_to_move_to_a_department_that_isn_t_an_indepartment() {
		message = API.movePatientBed("6", "1", "Cardio");
	}

	@Then("^I get a message that the move between beds was unsuccesful because the department isn't an indepartment$")
	public void i_get_a_message_that_the_move_between_beds_was_unsuccesful_because_the_department_isn_t_an_indepartment() {
		assertEquals(message, "The department isn't an indepartment");
	}
}
