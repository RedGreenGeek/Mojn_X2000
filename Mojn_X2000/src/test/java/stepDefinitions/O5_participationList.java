package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import cucumber.api.java.en.*;
import framework.API;

public class O5_participationList {
	API api = API.getInstance();
	String message;
	boolean departmentName;
	boolean birthday;
	boolean address;
	boolean tribe;
	
	
	@When("^I am requesting a partipation list of entire hospital$")
	public void i_am_requesting_a_partipation_list_of_entire_hospital() {
	    //Enter request
	}

	@When("^I choose what data I would like to receive$")
	public void i_choose_what_data_I_would_like_to_receive() {
		departmentName = true;
		birthday = true;
		address = true;
		tribe = true;
	}

	@Then("^I get a csv containing information of all patients$")
	public void i_get_a_csv_containing_information_of_all_patients() throws IOException {
	    message = api.getParticipationList("admin","I",departmentName,birthday,address,tribe);
	    
	    assertEquals(message,"Participation list was created successfully.");
	}

	@When("^I am requesting a partipation list of a given department$")
	public void i_am_requesting_a_partipation_list_of_a_given_department() {
		//Make said request
	}

	@Then("^I get a csv containing information of patients from that department$")
	public void i_get_a_csv_containing_information_of_patients_from_that_department() throws IOException {
		message = api.getParticipationList("admin","I","ER", departmentName,birthday,address,tribe);
	    
		assertEquals(message,"Participation list was created successfully.");
	}

	@When("^I am entering a non unique department name$")
	public void i_am_entering_a_non_unique_department_name() {
	    //Enter eg klm
	}

	@Then("^I get message saying there was an error$")
	public void i_get_message_saying_there_was_an_error() throws IOException {
		message = api.getParticipationList("admin","I","klm", departmentName,birthday,address,tribe);
	    
		assertEquals(message,"Warning, an error occured, no list was created.");
	}
}
