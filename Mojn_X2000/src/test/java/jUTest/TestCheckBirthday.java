package jUTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import framework.API;
import framework.Person;
public class TestCheckBirthday {
	API api = API.getInstance();
	 
	  @Test
	  public void simpleTest() {
		  api.registerPatient("I", "I", "I", "I", "I", "I", 29, 2, 1996,false);
		assertTrue(api.patientSearcher("I", "I", "", "I", "", "", "").contains("I"));
		  api.registerPatient("I", "I", "A", "A", "A", "A", 30, 2, 1996,false);
		assertTrue(api.patientSearcher("I", "I", "", "A", "", "", "").contains("A"));
	    System.err.println("Palle er gud");
	  }

	}