package stepDefinitions;

import static org.junit.Assert.*;

import cucumber.api.java.en.*;
import framework.API;

public class M2_changeStaffInfo {
	
	API api;
	String message;

	@Given("^there is registered staff$")
	public void there_is_registered_staff() {
		api = API.getInstance();
	}
	
	@Given("^I am an ICT officer$")
	public void i_am_an_ICT_officer() {
		api.registerStaff("Nurse", "Erika", "Eriksen", "NurseRoad", "Humanist", 13, 5, 1990);
		api.staffSearcher("", "Erika", "Eriksen", "", "");
	}
	
	@When("^I am entering sufficient search data$")
	public void i_am_entering_sufficient_search_data() {
		message = api.staffSearcher("", "Lars", "", "", "");
		assertFalse(message.equals("No match to search parameters!"));
	}
	
	@Then("^I can change the wanted parameters to something valid$")
	public void i_can_change_the_wanted_parameters_to_something_valid() {
		message = api.changeStaff ("D2" ,"" ,"LARS", "LYKKE","Venstre", "", 0, 0, 0);
		assertTrue(message.equals("Staff information has been changed successfully!"));

	}
	
	@Then("^Load the staff member back into the database$")
	public void load_the_staff_member_back_into_the_database() {
		
		// It happens automatically
		
	}
	
	@When("^I am entering insufficient search data$")
	public void i_am_entering_insufficient_search_data() {
		
		message = api.changeStaff("J192" ,"" ,"", "","", "", 0, 0, 0);	
		
	}
	
	@Then("^I recieve an error message that the employee does not exist$")
	public void i_recieve_an_error_message() {

		assertEquals(message, "The ID does not match an employee!");

		
	}
	
	@When("^I try invalid changes on a staff$")
	public void i_try_invalid_changes_on_a_staff() {
	    
		message = api.changeStaff("" ,"" ,"Lars", "Lykke","", "", 45, 2, 1971);	
	}

	@Then("^I recieve an error message saying that it is invalid$")
	public void i_recieve_an_error_message_saying_that_it_is_invalid() throws Throwable {
	    
		assertEquals(message, "Illegal changes to patient. Please check that the information is correct!");
	}
	
	@When("^I am entering sufficient data, but no job type$")
	public void i_am_entering_sufficient_data_but_no_job_type() {
		message = api.changeStaff("N3" ,"" ,"Jonna", "Nielsen","Ikke-doktorvej", "Tysk", 02,02, 1902);
	}

	@Then("^I recieve a message saying that the change has been done$")
	public void i_recieve_a_message_saying_that_the_change_has_been_done() {
		assertEquals(message, "Staff information has been changed successfully!");
	}
	
	@When("^I am entering sufficient data, with jobtype$")
	public void i_am_entering_sufficient_data_with_jobtype() {
		message = api.changeStaff("N3" ,"Doctor" ,"Jonna", "Nielsen","Ikke-doktorvej", "Tysk", 02,02, 1902);
	}

	@Then("^I recieve a message saying that the change has been Done$")
	public void i_recieve_a_message_saying_that_the_change_has_been_Done()  {
		assertEquals(message, "The Doctor has been registered succesfully!");
	}


}