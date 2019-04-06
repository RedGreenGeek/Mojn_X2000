import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {
	
	functionality f;
	user_story u;
	scenario s;
	code c;
	

	@Given("some functionality with id (\\d+)$")
	public void a_functionality_with_id(int id) {
		
		f = new functionality(id);
		assertEquals(f.getId(), id);

	}

	@Given("a user story with id (\\d+)$")
	public void a_user_story_with_id(int id) {
		
		u = new user_story(id);
		assertEquals(u.getId(), id);

	}

	@When("I have transformed the user story into scenarios$")
	public void make_scenarios() {
		
		s = new scenario(u);
		
		assertTrue(s.getId() == u.getId());
	}
	
	@When("^the code fulfills the scenario after some iterations$")
	public void fulfill_scenario()  {
		
		c = new code(s);
		
		assertTrue(c.getId() == s.getId());
		 

	}
	
	@Then("^I have used BDD correctly$")
	public void check_if_BDD_correct()  {
		
		assertTrue(c.isCorrect(s));

	}


	
}
