package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cucumber.api.java.en.*;
import framework.API;

public class M4_PatientAdmission {
	
	API api = API.getInstance();
	String message;
	@Given("^that we have a hospital with departments$")
	public void that_we_have_a_hospital_with_departments() {
		// The API holds a hospital with departments that complies to the requirements 
	}

	@Given("^with a unique department names$")
	public void with_a_unique_department_names() {
		// The API holds a hospital with departments that complies to the requirements 
	}

	@When("^I am entering valid patient data$")
	public void i_am_entering_valid_patient_data() {
		String firstName = "Anton";
		String lastName = "Larsen";
		String adress = "Jagtvej";
		String tribe = "DTU";
		int day = 1;
		int month = 12;
		int year = 1996;
		// These data will be handled in the next step
		
	}

	@When("^I am entering a existing department$")
	public void i_am_entering_a_existing_department() {
		String firstName = "Anton";
		String lastName = "Larsen";
		String adress = "Jagtvej";
		String tribe = "DTU";
		int day = 1;
		int month = 12;
		int year = 1996;
		String department = "ER";
		message = API.patientAdmission(department, firstName, lastName, adress, tribe, day, month, year);

	}

	@Then("^I get a message with a statement that the patient has been added succesfully$")
	public void i_get_a_message_with_a_statement_that_the_patient_has_been_added_succesfully() {
		assertNotEquals(message, "Unsuccesful registration cause to invalid patient data!");
		assertNotEquals(message, "The department specification is ambigious");
	}

	@When("^I am entering invalid patient data$")
	public void i_am_entering_invalid_patient_data() {
		String firstName = "Anton";
		String lastName = "Larsen";
		String adress = "Jagtvej";
		String tribe = "DTU";
		int day = 1;
		int month = 52;
		int year = 1996;
		String department = "ER";
		message = API.patientAdmission(department, firstName, lastName, adress, tribe, day, month, year);
	}

	@Then("^I get a message with the statement that the patient hasn't been added unsuccesfully cause to invalid patient data$")
	public void i_get_a_message_with_the_statement_that_the_patient_hasn_t_been_added_unsuccesfully_cause_to_invalid_patient_data() {
		assertEquals(message, "Unsuccesful registration cause to invalid patient data!");
	}

	@When("^I am entering a non existent department$")
	public void i_am_entering_a_non_existent_department() {
		String firstName = "Anton";
		String lastName = "Larsen";
		String adress = "Jagtvej";
		String tribe = "DTU";
		int day = 1;
		int month = 12;
		int year = 1996;
		String department = "Ieggfn";
		message = API.patientAdmission(department, firstName, lastName, adress, tribe, day, month, year);
	}

	@Then("^I get a message with the statement that the patient hasn't been added unsuccesfully cause to non existent department$")
	public void i_get_a_message_with_the_statement_that_the_patient_hasn_t_been_added_unsuccesfully_cause_to_non_existent_department() {
		assertEquals(message, "The department specification is ambigious");
	}
}
