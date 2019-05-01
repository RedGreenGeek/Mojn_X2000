package stepDefinitions;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.*;
import framework.API;

public class M3_bedsInUse {
	API api = API.getInstance();
	@Then("^I get a message with a statement about beds in use$")
	public void i_get_a_message_with_a_statement_about_beds_in_use() {
		assertEquals(api.bedsInUse("admin","I","Pediatric"),"Department: Pediatric currently have 2 beds in use.");
	}
}