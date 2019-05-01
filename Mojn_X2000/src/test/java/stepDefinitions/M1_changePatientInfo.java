package stepDefinitions;

import static org.junit.Assert.*;

import cucumber.api.java.en.*;
import framework.API;

public class M1_changePatientInfo {
	String patientID;
	String message;
	API ui = API.getInstance();
	
	@Given("^I have a patientID of a patient and I want to change their personal info$")
	public void i_have_a_patientID_of_a_patient_and_I_want_to_change_their_personal_info() {
	}

	@When("^I am entering a valid patientID$")
	public void i_am_entering_a_valid_patientID() {
		patientID = "3";
	}

	@When("^I am changing the given info to something valid$")
	public void i_am_changing_the_given_info_to_something_valid() {
	    message=ui.changePatient(patientID, "Søren", "Sørensen", "SF", "Hellerup", true);
	}

	@Then("^I get a message that the change was succesful$")
	public void i_get_a_message_that_the_change_was_succesful() {
	   assertEquals("Patient information has been changed successfully.",message);
	}

	@When("^I am entering invalid patientID$")
	public void i_am_entering_invalid_patientID() {
	    
	}

	@Then("^I get a message that the patient does not exist$")
	public void i_get_a_message_that_the_patient_does_not_exist() {
	    
	}

	@When("^I am changing the info to something illegal$")
	public void i_am_changing_the_info_to_something_illegal() {
	    
	}

	@Then("^I get a message that information entered was illegal$")
	public void i_get_a_message_that_information_entered_was_illegal() {
	    
	}
}
