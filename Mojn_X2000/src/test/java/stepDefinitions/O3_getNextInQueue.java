package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.*;
import framework.API;

public class O3_getNextInQueue {
	API api = API.getInstance();
	String message;
	String departmentName;
	
	@Given("^we ask for next person in queue$")
	public void we_ask_for_next_person_in_queue() {
		departmentName = "Cardio";
	    message = api.getNextInQueue(departmentName);
	}

	@Then("^I get the next patient in queue$")
	public void i_get_the_next_patient_in_queue(){
	    assertTrue(!message.contains("Warning"));
	}
	
	@When("^I am entering non unique department names$")
	public void i_am_entering_non_unique_department_names() {
	    message = api.getNextInQueue("John Cena");
	}

	@Then("^I get an error message$")
	public void i_get_an_error_message() {
	    assertEquals(message,"Warning, could not retrieve next in line.");
	}

	@When("^I am entering a unique department name of non out patient department$")
	public void i_am_entering_a_unique_department_name_of_non_out_patient_department() {
	    message = api.getNextInQueue("ER");
	}
}
