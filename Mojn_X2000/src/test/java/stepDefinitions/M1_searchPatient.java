package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cucumber.api.java.en.*;
import framework.API;

public class M1_searchPatient {
	API api = API.getInstance();
	String message; 
	
	@Given("^there is registered patients$")
	public void there_is_registered_patients() {
		// in the instance of the API we have a predefined hospital with patients
	}

	@When("^I am entering search data matching patient$")
	public void i_am_entering_search_data_matching_patient() {
		String firstName = "Jens";
		message = api.patientSearcher("admin","I","", firstName, "", "");
	}

	@Then("^I get a list the patients maching my search data$")
	public void i_get_a_list_the_patients_maching_my_search_data() {
		assertNotEquals(message, "No match to search parameters!");
	}

	@When("^I am entering search data not matching patient$")
	public void i_am_entering_search_data_not_matching_patient() {
		String birthday = "01-01-9999";
		message = api.patientSearcher("admin","I","", "", "", birthday);
	}

	@Then("^I recieve a message that no patients are maching the data$")
	public void i_recieve_a_message_that_no_patients_are_maching_the_data() {
		assertEquals(message, "No match to search parameters!");
	}
}
