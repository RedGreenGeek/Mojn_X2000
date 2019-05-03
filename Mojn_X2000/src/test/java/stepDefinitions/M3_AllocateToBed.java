package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.*;
import framework.API;

public class M3_AllocateToBed {
	API api = API.getInstance();
	String departmentName;
	String patientID;
	String message;
	
	@Given("^with unique department name$")
	public void with_unique_department_name()  {
	    departmentName = "ER";
	}

	@Given("^with a patientID$")
	public void with_a_patientID()  {
	    patientID = "3";
	}

	@Given("^I want to allocate a patient from that deparment to a bed$")
	public void i_want_to_allocate_a_patient_from_that_deparment_to_a_bed()  {
	    // Nothing to test here
	}

	@When("^I allocate patient to bed$")
	public void i_allocate_patient_to_bed()  {
	    message = api.allocateToBed("I","I", departmentName, patientID);
	}

	@Then("^I get a message saying the patient was allocated to bed no x$")
	public void i_get_a_message_saying_the_patient_was_allocated_to_bed_no_x()  {
	    assertTrue(message.contains("was added to bed:"));
	}

	@Then("^I get a message that no beds are currently available$")
	public void i_get_a_message_that_no_beds_are_currently_available()  {
	    message = api.allocateToBed("I","I", "Pediatric", patientID);
		assertEquals(message,"No beds available in department: Pediatric");
	}

	@Then("^I get a message that no patient with given ID at department$")
	public void i_get_a_message_that_no_patient_with_given_ID_at_department()  {
		message = api.allocateToBed("I","I", departmentName, "53");
		assertEquals(message,"No patient with given ID in department: ER");
	}
}
