package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import cucumber.api.java.en.*;
import framework.*;
import framework.department.*;
import framework.department.hc.*;
import framework.person.*;
import framework.person.staff.*;


public class M3_BedsAvailable {
	
	Hospital h;
	Department in_depart;
	
	// Background 
	
	@Given("^a Hospital$")
	public void that_we_are_on_a_Hospital_with_Departments() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    h = Hospital.getHospital();
	}

	@Given("^with operational IP_department$")
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
		
//		 patient
		Patient p1 = new Patient("p1_first", "p1_last", "p1_street", "p1_tribe", 01, 01, 1111, true);
		Patient p2 = new Patient("p2_first", "p2_last", "p2_street", "p2_tribe", 02, 02, 2222, true);
		
		patient_set.add(p1);
		patient_set.add(p2);
		
		
		// Departments
		Department Admin_depart = new AdminDepart("admin depart", staff_set);
		Department out_depart = new OutPatientDepart("ER", staff_set, patient_set);
		Department in_depart = new InPatientDepart("Cardio", staff_set, patient_set, 3);
		((InPatientDepart)in_depart).beds.AllocateBed(p1);
		((InPatientDepart)in_depart).beds.AllocateBed(p1,12);
		
		
		HashSet<Department> depart_set = new HashSet<Department>();
		depart_set.add(Admin_depart);
		depart_set.add(out_depart);
		depart_set.add(in_depart);
		
		h.setDepartSet(depart_set);
	}
	@Given("^That beds are available$")
	public void beds_are_available() {
		
	}
	
	int k;
	
	@When("^I ask for the number of beds available$")
	public void i_ask_for_the_number_of_beds_available() throws Throwable {
		k = UI_API.BedsAvailable("Cardio");
	}
	
	@Then("^the number of beds available is returned\\.$")
	public void the_number_of_beds_available_is_returned() throws Throwable {
		assertEquals(k,1);
	}
	
	//Next scenario
	@Given("^That beds are not available$")
	public void beds_are_not_available() {
		Patient p3 = new Patient("p3_first", "p3_last", "p3_street", "p3_tribe", 01, 01, 1111, true);
		

		
	}
	
	@When("^I request the beds available$")
	public void i_request_the_beds_available() throws Throwable {
		k = UI_API.BedsAvailable("Cardio");
	}
	
	@Then("^the string \"([^\"]*)\"\\.$")
	public void the_string(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}