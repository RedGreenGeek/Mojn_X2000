package stepDefinitions;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.*;
import framework.API;

public class M2_assignStaffDepartment {
	API api = API.getInstance();
	String departmentName;
	String ID;
	String message;
	
	@Given("^given a department name$")
	public void given_a_department_name()  {
	    departmentName = "Pediatric";
	}

	@Given("^given staff info$")
	public void given_staff_info()  {
		ID = "N1";
	    
	}

	@When("^I am entering correct info$")
	public void i_am_entering_correct_info()  {
	    // It's correct
	}

	@When("^I am entered a correct department name$")
	public void i_am_entered_a_correct_department_name()  {
	    // It's correct
	}

	@Then("^I get a message that the staff was added sucessfully$")
	public void i_get_a_message_that_the_staff_was_added_sucessfully()  {
	    message = api.assignStaffDepartment("I","I", departmentName, ID);
	    assertEquals(message,"Staff added successfully to department");
	}

	@When("^I am entering invalid department name$")
	public void i_am_entering_invalid_department_name()  {
	    departmentName = "NoDepart";
	}

	@Then("^I recieve an error message$")
	public void i_recieve_an_error_message()  {
		message = api.assignStaffDepartment("I","I", departmentName, ID);
	    assertEquals(message,"Warning, invalid person info or department name");
	}

	@When("^I am entering invalid info$")
	public void i_am_entering_invalid_staffID()  {
	    ID = "hgffasdfj";
	}
	@Then("^I recieve a error message$")
	public void i_recieve_a_error_message()  {
		message = api.assignStaffDepartment("I","I", departmentName, ID);
	    assertEquals(message,"Warning, invalid person info or department name");
	}
}
