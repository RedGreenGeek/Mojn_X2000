package framework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import framework.Departments.HealthCare.*;
import framework.person.Patient;

class DatabaseTest {
	
	ChangeReg R = new ChangeReg();
	Database DB = Database.getInstance(Database.DEFAULT);
	
	@Test
	void Can_I_connect_to_database() {
		
		boolean connected = DB.isConnected();	
		assertTrue(connected);
		
	}
	
	@Test
	void Write_new_patient_to_database() {

		Patient p = new Patient("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,true,"ER");
		String message = DB.writePatient(p);
		
		assertEquals("SUCCESS!", message);

		
	}
	
	@Test
	void Write_at_indepart_to_database() {

		Patient p = new Patient("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,true,"ER");
		InPatientDepart d = new InPatientDepart("Trauma Department", 200);
		R.add(d, p);
		String message = DB.writePatient(p);
		
		assertEquals("SUCCESS!", message);

	}
	
	@Test
	void Write_at_outdepart_to_database() {

		Patient p = new Patient("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,true,"ER");
		p.setTriage(3);
		OutPatientDepart d = new OutPatientDepart("Emergency");
		d.EnQueue(p, triageLevel);
		R.add(d, p);
		String message = DB.writePatient(p);
		
		assertEquals("SUCCESS!", message);

	}

}
