package stepDefinitions;

import cucumber.api.java.en.*;
import framework.API;
import static org.junit.Assert.assertEquals;

public class O2_ChangePassword {
	API api = API.getInstance();
	String message;
	
	@Given("^with a staff ID & a Password$")
	public void with_a_staff_ID_a_Password() {

	}

	@When("^I am entering the staff-ID, old password and new wanted password twice$")
	public void i_am_entering_the_staff_ID_old_password_and_new_wanted_password_twice() {
		message = api.ChangePassword("admin","I","password" , "asd2", "asd2", "N3");
	}

	@Then("^I get a message that my password was correctly changed$")
	public void i_get_a_message_that_my_password_was_correctly_changed(){
		assertEquals("Password changed",message);
	}

	@When("^I am entering the staff-ID, wrong old password and new wanted password twice$")
	public void i_am_entering_the_staff_ID_wrong_old_password_and_new_wanted_password_twice(){
		message = api.ChangePassword("admin","I","notRight" , "asd2", "asd2", "N3");

	}

	@Then("^I get a message that my password has not been changed, as I did not know my old password$")
	public void i_get_a_message_that_my_password_has_not_been_changed_as_I_did_not_know_my_old_password() {
		assertEquals(message,"Wrong old password");

	}

	@When("^I am entering the staff-ID, old password, new wanted password and a wrong new wanted password$")
	public void i_am_entering_the_staff_ID_wrong_old_password_new_wanted_password_and_a_wrong_new_wanted_password(){
		message = api.ChangePassword("admin","I","asd2" , "asd5", "asd3", "N3");

	}

	@Then("^I get a message that my password has not been changed, as I did not repeat my new password correctly$")
	public void i_get_a_message_that_my_password_has_not_been_changed_as_I_did_not_repeat_my_new_password_correctly(){
		assertEquals(message,"The 2 new passwords are not equal");

	}
	

	
	
	@When("^I am entering a wrong staff id, wanted password twice and old password$")
	public void i_am_entering_a_wrong_staff_id_wanted_password_twice_and_old_password(){
		message = api.ChangePassword("admin","I","asd2" , "asd5", "asd5", "N9999");
	}

	@Then("^I get a message that my password was not added, as the Staff does not exist$")
	public void i_get_a_message_that_my_password_was_not_added_as_the_Staff_does_not_exist() {
		assertEquals(message,"Staff ID does not exist");

	}
}
