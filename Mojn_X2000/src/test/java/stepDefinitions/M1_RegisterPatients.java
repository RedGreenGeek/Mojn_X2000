package stepDefinitions;

import java.util.HashSet;

import framework.*;
import framework.department.AdminDepart;
import framework.department.Department;
import framework.department.hc.InPatientDepart;
import framework.department.hc.OutPatientDepart;
import framework.person.*;
import framework.person.staff.Clerk;
import framework.person.staff.Doctor;
import framework.person.staff.ICTOfficer;
import framework.person.staff.Nurse;
import cucumber.api.java.en.Given;

public class M1_RegisterPatients {
	
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
	
	// Scenarios

	@Given("^I am on the patient registration page$")
	public void i_am_on_the_patient_registration_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I am entering sufficient patient data$")
	public void i_am_entering_sufficient_patient_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I hit \"([^\"]*)\"$")
	public void i_hit(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I get a message that the patient was registered succesfully$")
	public void i_get_a_message_that_the_patient_was_registered_succesfully() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I am entering insufficient patient data$")
	public void i_am_entering_insufficient_patient_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I get a message that additional information is needed$")
	public void i_get_a_message_that_additional_information_is_needed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
