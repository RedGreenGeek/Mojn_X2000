package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.*;
import framework.API;

public class M3_bedsAvailable {
	String departmentName;
	String message;
	API api = API.getInstance();

	
	@Given("^that we are on a department$")
	public void that_we_are_on_a_department()  {
	    // See below
	}

	@Given("^with a unique department name$")
	public void with_a_unique_in_department_name()  {
		departmentName = "ER";
	}

	@When("^I am entering the request$")
	public void i_am_entering_the_request()  {
	    message = api.bedsAvailable(departmentName);
	}

	@Then("^I get a message with a statement that there is available beds$")
	public void i_get_a_message_with_a_statement_that_there_is_available_beds()  {
		message = api.bedsAvailable(departmentName);
		assertTrue(message.contains("Beds available in department"));
	}

	@Then("^I get a message with a statement that there is no available beds$")
	public void i_get_a_message_with_a_statement_that_there_is_no_available_beds()  {
		message = api.bedsAvailable("Pediatric");
		assertEquals(message,"No beds available in department: Pediatric");
	}
}
