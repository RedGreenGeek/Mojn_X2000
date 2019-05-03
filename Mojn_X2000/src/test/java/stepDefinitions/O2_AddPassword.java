package stepDefinitions;

import cucumber.api.java.en.*;
import framework.API;

import static org.junit.Assert.assertEquals;

public class O2_AddPassword {
	
	API api = API.getInstance();
	String message;
	
	@Given("^with a staff ID & a password$")
	public void with_a_staff_ID_a_password()
	{
		//This is assmued from the API.
	}

	@When("^I am entering the staff-ID and wanted password twice$")
	public void i_am_entering_the_staff_ID_and_wanted_password_twice(){
		message = api.AddPassword("I","I","1234a", "1234a","D1234" );
	}

	@Then("^I get a message that my password was correctly added to the system$")
	public void i_get_a_message_that_my_password_was_correctly_added_to_the_system() {
		assertEquals(message,"Password created");				
	}

	@When("^I am entering the staff-ID, wanted password and a wrong wanted password$")
	public void i_am_entering_the_staff_ID_wanted_password_and_a_wrong_wanted_password() throws Throwable {
		message = api.AddPassword("I","I","asd2", "asd1", "D11234");
	}

	@Then("^I get a message that my password was not added, as I did not repeat my password correctly$")
	public void i_get_a_message_that_my_password_was_not_added_as_I_did_not_repeat_my_password_correctly() throws Throwable {
		assertEquals(message,"The two passwords do not match");
	}
	
	@When("^I am entering the staff id and wanted password twice$")
	public void i_am_entering_the_staff_id_and_wanted_password_twice() {
		message = api.AddPassword("I","I","asd1", "asd1", "N3");
	}

	@Then("^I get a message that my password was not added, as the Staff already has a password$")
	public void i_get_a_message_that_my_password_was_not_added_as_the_Staff_already_has_a_password() throws Throwable {
		assertEquals(message,"Password already created for this staff!");
	}

}
