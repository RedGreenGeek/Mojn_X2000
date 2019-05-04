package stepDefinitions;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.*;
import framework.API;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.*;
import framework.API;


public class M3_bedsInUse {
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
	     message = api.bedsInUse("I","I", "Pediatric");
	 }
	 
	@Then("^I get a message with a statement about beds in use$")
	public void i_get_a_message_with_a_statement_about_beds_in_use() {
		assertEquals("Department: Pediatric currently have 2 beds in use.", message);
	}
}