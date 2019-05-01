package stepDefinitions;

import static org.junit.Assert.assertEquals;

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
		// Data will be handled in the next step
	}

	@When("^I am entering a existing In depart$")
	public void i_am_entering_a_existing_In_depart() {
		String departmentName = "ER";
		String trilvl = "";
		message = api.patientAdmission("admin","I",trilvl, departmentName, "2");
	}
	
	@Then("^I get a message with a positiv In feed$")
	public void i_get_a_message_with_a_positiv_In_feed() {
		assertEquals(message, "The patient has been registered succesfully to ER!");
	}


	@When("^I am entering a existing Out depart$")
	public void i_am_entering_a_existing_Out_depart() {
		String department = "Cardio";
		String trilvl = "2";
		message = api.patientAdmission("admin","I",trilvl, department, "1");
	}
	
	@Then("^I get a message with a positiv Out feed$")
	public void i_get_a_message_with_a_positiv_Out_feed() {
		assertEquals(message, "The patient has been registered succesfully to Cardio!");
	}

	@When("^I am entering a wrong Admin depart$")
	public void i_am_entering_a_wrong_Admin_depart() {
		String department = "IT";
		String trilvl = "";
		message = api.patientAdmission("admin","I",trilvl, department,"4");
	}

	@Then("^I get an error message Admin depart$")
	public void i_get_an_error_message_Admin_depart() {
		assertEquals(message, "The department is an administrativ department");
	}

	@When("^I am entering a wrong trilvl$")
	public void i_am_entering_a_wrong_trilvl() {
		String department = "ER";
		String trilvl = "wer";
		message = api.patientAdmission("admin","I",trilvl, department,"5");
	}

	@Then("^I get an error message trilvl$")
	public void i_get_an_error_message_trilvl() {
		assertEquals(message, "The triage level specification wasn't an integer");
	}

	@When("^I am entering a wrong Person value$")
	public void i_am_entering_a_wrong_Person_value() {
		String department = "ER";
		String trilvl = "";
		message = api.patientAdmission("admin","I",trilvl, department,"4243");
	}

	@Then("^I get an error message invalid data$")
	public void i_get_an_error_message_invalid_data() {
		assertEquals(message, "Invalid patient ID");
	}

	@When("^I am entering a wrong invalid depart$")
	public void i_am_entering_a_wrong_invalid_depart() {
		String department = "ERsfdgsfg343tr";
		String trilvl = "";
		message = api.patientAdmission("admin","I",trilvl, department,"2");
	}

	@Then("^I get an error message invalid depart$")
	public void i_get_an_error_message_invalid_depart() {
		assertEquals(message, "The department specification is ambigious");
	}

}