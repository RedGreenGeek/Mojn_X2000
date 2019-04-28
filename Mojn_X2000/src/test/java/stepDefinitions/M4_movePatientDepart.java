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

	@When("^I am entering an existing health care department$")
	public void i_am_entering_an_existing_health_care_department()  {
		message = api.movePatientDepart("3", "ER","");
	}

	@Then("^I get a message with a statement that the patient has been moved succesfully$")
	public void i_get_a_message_with_a_statement_that_the_patient_has_been_moved_succesfully()  {
		message = api.movePatientDepart("3", "ER","");
		assertEquals("The patient was moved successfully!",message);
	}

	@When("^I am entering a wrong patient ID$")
	public void i_am_entering_a_wrong_patient_ID()  {
		message = api.movePatientDepart("6sdfg", "ER","");
	}

	@Then("^I get an ID error$")
	public void i_get_an_ID_error()  {
		assertEquals(message, "The patient wasn't moved");
	}

	@When("^I am entering a wrong patient admission$")
	public void i_am_entering_a_wrong_patient_admission()  {
		message = api.movePatientDepart("2", "PasdfHund","");
	}

	@Then("^I get an admit error$")
	public void i_get_an_admit_error()  {
		assertEquals(message, "The patient wasn't moved");
	}

}
