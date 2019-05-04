package jUTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import framework.API;

public class Test_CheckBirthday_Test {
	API api = API.getInstance();
	 
	  @Test
	  public void simpleTest() {
		  api.registerPatient("I", "I", "I", "I", "I", "I", 29, 2, 1996,false);
		assertTrue(api.patientSearcher("I", "I", "", "I", "", "", "").contains("I"));
		assertEquals("Additional information is needed.", api.registerPatient("I", "I", "A", "A", "A", "A", 30, 2, 1996,false));
		  api.registerPatient("I", "I", "B", "B", "B", "B", 30, 4, 1996,false);
		assertTrue(api.patientSearcher("I", "I", "", "B", "", "", "").contains("B"));
		assertEquals("Additional information is needed.", api.registerPatient("I", "I", "C", "C", "C", "C", 32, 4, 1996,false));  
		  api.registerPatient("I", "I", "D", "D", "D", "D", 15, 3, 1996,false);
		assertTrue(api.patientSearcher("I", "I", "", "D", "", "", "").contains("D"));
		assertEquals("Additional information is needed.", api.registerPatient("I", "I", "E", "E", "E", "E", 32, 3, 1996,false)) ;
		api.registerPatient("I", "I", "F", "F", "F", "F", 30, 4, 1997,false);
		assertTrue(api.patientSearcher("I", "I", "", "F", "", "", "").contains("F"));
		assertEquals("Additional information is needed.", api.registerPatient("I", "I", "F", "F", "F", "F", 31, 4, 1997,false)) ;
	    System.err.println("Palle er gud");
	  }

	}