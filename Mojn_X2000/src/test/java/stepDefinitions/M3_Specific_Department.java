package stepDefinitions;

import static org.junit.Assert.*;

import java.util.LinkedList;

import cucumber.api.java.en.*;
import framework.API;

public class M3_Specific_Department {
	API api;
	String departments;
	String ERStaff;
	
	@Given("^that we are on a Hospital$")
	public void that_we_are_on_a_Hospital()  {
	    api = API.getInstance();
	    assertTrue(api!=null);
	}

	@Given("^with departments$")
	public void with_departments()  {
	    departments = api.getDepartments();
	    LinkedList<String> dTest = new LinkedList<String>();
	    dTest.add("IT");
	    dTest.add("ER");
	    dTest.add("Pediatric");
	    dTest.add("Cardio");
	    
	    assertTrue(departments.contains("IT") && departments.contains("ER") && departments.contains("Pediatric") && departments.contains("Cardio"));
	}

	@Given("^I want to see who is working in a specific department$")
	public void i_want_to_see_who_is_working_in_a_specific_department()  {
	    ERStaff = api.getDeparmentStaff("ER");
	}

	@When("^I am entering a correct department name\\.$")
	public void i_am_entering_a_correct_department_name()  {
	    // same as above
	}

	@Then("^I get a list of staff member for the specific department\\.$")
	public void i_get_a_list_of_staff_member_for_the_specific_department()  {
		assertTrue(ERStaff!=null);
	}

	@When("^I am entering a incorrect department name\\.$")
	public void i_am_entering_a_incorrect_department_name()  {
	    ERStaff = api.getDeparmentStaff("E213");
	}

	@Then("^I get a message that the department is not found$")
	public void i_get_a_message_that_the_department_is_not_found()  {
	    assertEquals(ERStaff,"No or multiple department(s) match your search criterion");
	}
}
