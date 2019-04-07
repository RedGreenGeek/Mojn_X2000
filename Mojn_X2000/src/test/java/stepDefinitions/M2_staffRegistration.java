package stepDefinitions;

import cucumber.api.java.en.*;
import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import framework.*;
import framework.person.Staff;



public class M2_staffRegistration {
	
	Hospital h;
	HashSet<Staff> hS;
	Staff s1;
	
	@Given("^with a Staff Registery$")
	public void with_a_Staff_Registery() throws Throwable {
		hS = Hospital.getStaffRegistery();
		s1 = new Staff("s1_first", "s1_last", "s1_street", "s1_tribe", 02, 02, 2222, true, "Nurse");
		hS.add(s1);
		Hospital.setStaffRegistery(hS);
	}

	@Given("^I am on the staff registration page$")
	public void i_am_on_the_staff_registration_page() throws Throwable {
	    
	}
	
	String k;
	
	@When("^I am entering sufficient staff data$")
	public void i_am_entering_sufficient_staff_data() throws Throwable {
		k = UI_API.RegisterStaff("s2_first", "s2_last", "s2_street", "s2_tribe", 02, 02, 2222, true, "Nurse");
	}
	//API call.

	@Then("^I get a message that the staff was registered succesfully$")
	public void i_get_a_message_that_the_staff_was_registered_succesfully() throws Throwable {
	    assertEquals(k, "s2_first s2_last registered succesfully!");
	}

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<Next Scenario.>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@When("^I am entering insufficient staff data$")
	public void i_am_entering_insufficient_staff_data() throws Throwable {
		k = UI_API.RegisterStaff("s2_first", "s2_last", "s2_street", "", 14, 02, 2222, true, "Nurse");
	}

	@Then("^I get a message$")
	public void i_get_a_message() throws Throwable {
		assertEquals("Insufficient data: Please fill all fields with correct data.",k);
	}

}
