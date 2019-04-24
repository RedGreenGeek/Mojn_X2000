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

	@When("^I am entering a Diffrent bed no$")
	public void i_am_entering_a_Diffrent_bed_no() {
		message = api.movePatientBed("3", "4");
	}

	@Then("^I get a good Moved to diffrent bed$")
	public void i_get_a_good_Moved_to_diffrent_bed() {
		assertEquals(message, "The patient was moved succesfully");
	}

	@When("^I am entering a Same bed no$")
	public void i_am_entering_a_Same_bed_no() {
		message = api.movePatientBed("3", "4");
	}

	@Then("^I get a good Moved to same bed$")
	public void i_get_a_good_Moved_to_same_bed() {
		assertEquals(message, "The patient was moved succesfully to the same bed");
	}

	@When("^I am writing an not int bed$")
	public void i_am_writing_an_not_int_bed() {
		message = api.movePatientBed("3", "dfgas");
	}

	@Then("^I get an not int error$")
	public void i_get_an_not_int_error() {
		assertEquals(message, "You need to specify the bed number as an integer");
	}

	@When("^I am writing an Out patient$")
	public void i_am_writing_an_Out_patient() {
		message = api.movePatientBed("4", "2");
	}

	@Then("^I get an Out patient error$")
	public void i_get_an_Out_patient_error() {
		assertEquals(message, "The department isn't an indepartment");
	}

	@When("^I am writing an Wrong ID$")
	public void i_am_writing_an_Wrong_ID() {
		message = api.movePatientBed("sdgsd", "2");
	}

	@Then("^I get an Wrong ID error$")
	public void i_get_an_Wrong_ID_error() {
		assertEquals(message, "The patient wasn't moved cause to invalid ID");
	}

	@When("^I am writing an bed over max$")
	public void i_am_writing_an_bed_over_max() {
		message = api.movePatientBed("3", "200");
	}

	@Then("^I get an bed max error$")
	public void i_get_an_bed_max_error() {
		assertEquals(message, "There aren't that many beds in the department");
	}


}
