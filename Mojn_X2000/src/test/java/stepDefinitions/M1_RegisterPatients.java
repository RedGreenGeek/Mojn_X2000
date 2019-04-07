package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import framework.*;
import framework.person.Patient;
import cucumber.api.java.en.*;

public class M1_RegisterPatients {
	
	Hospital h;
	HashSet<Patient> hP;
	Patient p1; 
	
	// Background 
	
	@Given("^that we are on a Hospital$")
	public void that_we_are_on_a_Hospital() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    h = Hospital.getHospital();
	}

	@Given("^with a Patient Registery$")
	public void with_a_Patient_Registery() throws Throwable {
		hP = h.getPatientRegistery();
		p1 = new Patient("p1_first", "p1_last", "p1_street", "p1_tribe", 02, 02, 2222, true);
		hP.add(p1);
		h.setPatientRegistery(hP);
	}


	@Given("^I am on the patient registration page$")
	public void i_am_on_the_patient_registration_page() throws Throwable {
	}
	
	// Scenarios
	String k;
	
	@When("^I am entering sufficient patient data$")
	public void i_am_entering_sufficient_patient_data() throws Throwable {
		k = UI_API.RegisterPatient("p2_first", "p2_last", "p2_street", "p2_tribe", 02, 02, 2222, true);
		
	}

	@Then("^I get a message that the patient was registered succesfully$")
	public void i_get_a_message_that_the_patient_was_registered_succesfully() throws Throwable {
		assertEquals(k, ("p2_first p2_last registered succesfully!"));
	}
	
	
	//Next Scenario
	@Given("^I am entering insufficient patient data$")
	public void i_am_entering_insufficient_patient_data() throws Throwable {
		k = UI_API.RegisterPatient("p2_first", "", "p2_street", "p2_tribe", 02, 02, 2222, true);
	}

	@Then("^I get a message that additional information is needed$")
	public void i_get_a_message_that_additional_information_is_needed() throws Throwable {
		
	}
}
