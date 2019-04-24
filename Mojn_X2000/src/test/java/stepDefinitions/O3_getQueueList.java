package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import cucumber.api.java.en.*;
import framework.API;

public class O3_getQueueList {
	API api = API.getInstance();
	LinkedList<String> message;
	String departmentName;
	
	
	@Given("^with a unique out patient department name$")
	public void with_a_unique_out_patient_department_name() {
	    departmentName = "Cardio";
	}

	@When("^I am entering unique department name of a out patient department$")
	public void i_am_entering_unique_department_name_of_a_out_patient_department() {
	    message = api.getQueue(departmentName);
	}

	@Then("^I get a list of the current queue$")
	public void i_get_a_list_of_the_current_queue() {
	    assertTrue(!message.getFirst().contains("Warning") && message.getFirst()!=null);
	}

	@When("^I am entering non unique department name$")
	public void i_am_entering_non_unique_department_name() {
		message = api.getQueue("Cardio42");
	}

	@Then("^I get a error message$")
	public void i_get_a_error_message() {
		assertEquals(message.getFirst(),"Warning, could not retrieve queue of given department.");
	}

	@When("^I am entering unique department name of non out patient department$")
	public void i_am_entering_unique_department_name_of_non_out_patient_department() {
		message = api.getQueue("ER");
	}
}
