package stepDefinitions;

import static org.junit.Assert.*;

import cucumber.api.java.en.*;
import framework.API;

public class O7_addDepartment {
	API api = API.getInstance();
	String message;
	
	@Given("^that we have a hospital$")
	public void that_we_have_a_hospital() {
	    // The api insures this
	}

	@When("^a new outPatient department$")
	public void a_new_outPatient_department() {
	    message = api.addDepartment("I","I","outPatient","Surgery","32");
	}

	@Then("^I get message that the department was added$")
	public void i_get_message_that_the_department_was_added() {
	    assertEquals("The department was added!",message);
	}

	@When("^a new inPatient department$")
	public void a_new_inPatient_department() {
		message = api.addDepartment("I","I","inPatient","Orthopedic","47");
	}

	@When("^a new admin department$")
	public void a_new_admin_department() {
		message = api.addDepartment("I","I","admin","HR","32");
	}

	@When("^I try to add an invalid department type$")
	public void i_try_to_add_an_invalid_department_type() {
		message = api.addDepartment("I","I","Mongollos","SpasserAfdeling","232");
	}

	@Then("^I get message that no department was added$")
	public void i_get_message_that_no_department_was_added() {
	    assertEquals("Invalid department type. It must be admin, inPatient, or outPatient!",message);
	}
	
	@When("^I try to add a inPatient but maxBed is not an integer$")
	public void i_try_to_add_a_inPatient_but_maxBed_is_not_an_integer() {
		message = api.addDepartment("I","I","inPatient","ddasdc","mojn2000");
	}

	@Then("^I get a message that maxBeds must be an integer$")
	public void i_get_a_message_that_maxBeds_must_be_an_integer() {
	    assertEquals("maxBeds must be a integer!",message);
	}
	
	@When("^I try to add a department with a name already in use$")
	public void i_try_to_add_a_department_with_a_name_already_in_use() {
	    message = api.addDepartment("I","I","inPatient", "ER", "4234");
	}

	@Then("^I get a message that the name must be unique$")
	public void i_get_a_message_that_the_name_must_be_unique() {
	    assertEquals("Department name must be unique!",message);
	}
}
