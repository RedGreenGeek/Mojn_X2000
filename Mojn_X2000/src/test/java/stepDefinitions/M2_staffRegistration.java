package stepDefinitions;

import cucumber.api.java.en.*;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import framework.*;
import framework.department.*;
import framework.department.hc.*;
import framework.person.*;
import framework.person.staff.*;


public class M2_staffRegistration {
	
	Hospital h;
	
	// Background 
	
	@Given("^that we are on a Hospital$")
	public void that_we_are_on_a_Hospital() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    h = new Hospital();
	}

	@Given("^with sufficient departments$")
	public void with_sufficient_departments() throws Throwable {

		// Staff set and patient set
		HashSet<Person> staff_set = new HashSet<Person>();
		HashSet<Person> patient_set = new HashSet<Person>();
		
		
		// staff
		Staff clerk = new Clerk("clerk_first", "clerk_last", "clerk_street", "clerk_tribe", 01, 01, 1111);
		Staff ICT = new ICTOfficer("ict_first", "ict_last", "ict_street", "ict_tribe", 02, 02, 2222);
		Staff doctor = new Doctor("doctor_first", "doctor_last", "doctor_street", "doctor_tribe", 03, 03, 3333);
		Staff nurse = new Nurse("nurse_first", "nurse_last", "nurse_street", "nurse_tribe", 04, 04, 4444);
		
		staff_set.add(clerk);
		staff_set.add(ICT);
		staff_set.add(doctor);
		staff_set.add(nurse);
		
		// patient
		Patient p1 = new Patient("p1_first", "p1_last", "p1_street", "p1_tribe", 01, 01, 1111, true);
		Patient p2 = new Patient("p2_first", "p2_last", "p2_street", "p2_tribe", 02, 02, 2222, true);
		
		patient_set.add(p1);
		patient_set.add(p2);
		
		
		// Departments
		Department Admin_depart = new AdminDepart("admin depart", staff_set);
		Department out_depart = new OutPatientDepart("ER", staff_set, patient_set);
		Department in_depart = new InPatientDepart("Cardio", staff_set, patient_set, 14);
		
		
		HashSet<Department> depart_set = new HashSet<Department>();
		depart_set.add(Admin_depart);
		depart_set.add(out_depart);
		depart_set.add(in_depart);
		
		h.setDepartSet(depart_set);
		
		
		

	}

	@Given("^I am on the staff registration page$")
	public void i_am_on_the_staff_registration_page() throws Throwable {
	    
	}

    public static String FirstName = "clerk_first";
    public static String LastName = "clerk_last";
    public static String Adress = "clerk_street";
    public static String Tribe = "clerk_tribe";
    public static String JobType = "Clerk";
    public static int bDay = 01;
    public static int bMonth = 01;
    public static int bYear = 2001;
    
	@When("^I enter sufficient staff data$")
	public void i_enter_sufficient_staff_data() throws Throwable {
	    FirstName = "clerk_first";
	    LastName = "clerk_last";
	    Adress = "clerk_street";
	    Tribe = "clerk_tribe";
	    JobType = "Clerk";
	    bDay = 01;
	    bMonth = 01;
	    bYear = 2001;
	}
	//API call.
	public static String mes = new String();
	
	@When("^click register staff$")
	public void click_register_staff() throws Throwable {
		mes = RegisterStaff(FirstName, LastName, Adress, Tribe, bDay, bMonth, bYear);
	}

	@Then("^I receive confirmation that the staff member is registred$")
	public void i_receive_confirmation_that_the_staff_member_is_registred() throws Throwable {
	    assertEquals(mes, StaffID +" "+ FirstName + " is registered");
	}

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<Next Scenario.>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Given("^I am on the registration page$")
	public void i_am_on_the_registration_page() throws Throwable {
	    
	}
	@When("^I enter insufficient staff data$")
	public void i_enter_insufficient_staff_data() throws Throwable {
	    LastName = "clerk_last";
	    Adress = "clerk_street";
	    Tribe = "clerk_tribe";
	    bDay = 01;
	    bMonth = 01;
	    bYear = 2001;
	}
	
	
	@When("^I hit register staff$")
	public void I_hit_register_staff() throws Throwable {
		mes = RegisterStaff(LastName, Adress, Tribe, bDay, bMonth, bYear);
	}

	@Then("^I receive an error message \"([^\"]*)\"$")
	public void i_receive_an_error_message(String arg1) throws Throwable {
		assertEquals(mes, "Insufficient data: Please fill all fields with correct data.");
	}

}
