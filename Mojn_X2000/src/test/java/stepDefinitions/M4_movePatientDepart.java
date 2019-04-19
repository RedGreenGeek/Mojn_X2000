package stepDefinitions;

import cucumber.api.java.en.*;
import framework.API;
import static org.junit.Assert.*;

public class M4_movePatientDepart {
	API api = API.getInstance();
	String message;
	
	@When("^I am entering a valid patient ID$")
	public void i_am_entering_a_valid_patient_ID() {
		// Done in last when statement
	}

	@When("^I am entering an existing department$")
	public void i_am_entering_an_existing_department()  {
		message = API.movePatientDepart("6", "Pediatric");
	}

	@Then("^I get a message with a statement that the patient has been moved succesfully$")
	public void i_get_a_message_with_a_statement_that_the_patient_has_been_moved_succesfully()  {
		assertNotEquals(message, "The patient wasn't moved");
	}

	@When("^I am entering an invalid patient ID$")
	public void i_am_entering_an_invalid_patient_ID()  {
		message = API.movePatientDepart("12434", "Pediatric");
	}

	@Then("^I get an error message that the move was unsuccesful$")
	public void i_get_an_error_message_that_the_move_was_unsuccesful()  {
		assertEquals(message, "The patient wasn't moved");
	}

	@When("^I am trying to move to a non existent department$")
	public void i_am_trying_to_move_to_a_non_existent_department()  {
		message = API.movePatientDepart("6", "Non existent");
	}
	@Then("^I get a message that the move was unsuccesful$")
	public void i_get_a_message_that_the_move_was_unsuccesful() {
		assertEquals(message, "The patient wasn't moved");
	}

}
