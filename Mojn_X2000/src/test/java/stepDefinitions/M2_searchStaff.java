package stepDefinitions;

import static org.junit.Assert.*;

import cucumber.api.java.en.*;
import framework.API;

public class M2_searchStaff {
	API api = API.getInstance();
	String message;
	
	@When("^I am entering search data matching staff$")
	public void i_am_entering_search_data_matching_staff() {
		String lastName = "Nielsen";
		message = api.staffSearcher("", "", lastName, "", "");
		
	}

	@Then("^I get a list over my search results$")
	public void i_get_a_list_over_my_search_results() {
		assertNotEquals(message, "No match to search parameters!");
	}

	@When("^I am entering search data not matching staff$")
	public void i_am_entering_search_data_not_matching_staff() {
		String firstName = "Anders";
		message = api.staffSearcher("", firstName, "", "", "");
	}

	@Then("^I recieve a message that the employee does not exist$")
	public void i_recieve_a_message_that_the_employee_does_not_exist() {
		assertEquals(message, "No match to search parameters!");
	}
}
