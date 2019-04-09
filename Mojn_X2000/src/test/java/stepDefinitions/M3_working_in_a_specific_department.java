package stepDefinitions;

import java.util.HashSet;

import cucumber.api.java.en.*;
import framework.*;
import framework.person.*;
import framework.departments.*;
import framework.departments.HealthCare.*;

public class M3_working_in_a_specific_department {
	
	Hospital h; // We have an instance of the hospital, since it is the framework
	Staff s1; // Arbitrary staff member
	Staff s2; // Arbitrary staff member
	HashSet<Department> hd; // HashSet for storing department
	private HashSet<Person> ss;
	
	
	@Given("^with departments$")
	public void with_departments() throws Throwable {
	    
		hd = Hospital.getDepartSet();
		Department d1 = new AdminDepart("AdminDepart", ss);
		Department d2 = new OutPatientDepart("OutDepart", ss);
		Department d3 = new InPatientDepart("InPatient", ss);
		
		hd.add(d1);
		hd.add(d2);
		hd.add(d3);
		
	}

	String k;
	
	@Given("^I want to see who is working in a specific department$")
	public void i_want_to_see_who_is_working_in_a_specific_department() throws Throwable {
	    
		
		
		
	}

	@When("^I am entering a correct department name\\.$")
	public void i_am_entering_a_correct_department_name() throws Throwable {

	}

	@Then("^I get a list of staff member for the specific department\\.$")
	public void i_get_a_list_of_staff_member_for_the_specific_department() throws Throwable {

	}

	@When("^I am entering a incorrect department name\\.$")
	public void i_am_entering_a_incorrect_department_name() throws Throwable {

	}

	@Then("^I get a message that the department is not found$")
	public void i_get_a_message_that_the_department_is_not_found() throws Throwable {

	}


}
