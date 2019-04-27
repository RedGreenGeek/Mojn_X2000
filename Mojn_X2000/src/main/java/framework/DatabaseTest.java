package framework;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import framework.Departments.AdminDepart;
import framework.Departments.HealthCare.*;
import framework.person.Patient;
import framework.person.Staff;
import framework.person.staff.Clerk;
import framework.person.staff.Doctor;
import framework.person.staff.Nurse;

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
		InPatientDepart d = new InPatientDepart("Trauma Department", 200); // why is it not recognized by changereg?
		p.setBedLocation(32);
		R.add(d, p);
		String message = DB.writePatient(p);
		
		assertEquals("SUCCESS!", message);
		
		System.out.println(d.getPatient());

	}
	
	@Test
	void Write_at_outdepart_to_database() {

		Patient p = new Patient("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,true,"ER");
		p.setTriage(3);
		OutPatientDepart d = new OutPatientDepart("Emergency");
		d.EnQueue(p, p.getTriage());
		R.add(d, p);
		String message = DB.writePatient(p);
		
		assertEquals("SUCCESS!", message);

	}
	
	@Test
	void Write_staff_to_database() {

		Staff staff = new Doctor("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,"DOCTOR_department");
		
		String message = DB.writeStaff(staff);

		assertEquals("SUCCESS!", message);

	}
	
	@Test
	void Writing_department_to_database() throws Throwable {
		
		OutPatientDepart dOUT = new OutPatientDepart("Department of Diagnosis");
		InPatientDepart dIN = new InPatientDepart("Department of Surgery", 200);
		AdminDepart dAD = new AdminDepart("Department of Human Relations");
		Patient p = new Patient("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,true,"ER");
		
		String message1 = DB.writeDepartment(dOUT);
		String message2 = DB.writeDepartment(dIN);
		String message3 = DB.writeDepartment(dAD);
		
		assertEquals("SUCCESS!", message1);
		assertEquals("SUCCESS!", message2);
		assertEquals("SUCCESS!", message3);
		
	}
	
	@Test
	void Read_staff_from_database() throws Throwable {
		
		HashSet<Staff> s = DB.loadStaff();
		System.out.println("TEST Read_staff_from_database: " + s);
		assertFalse(s.isEmpty());

		
	}
	
	@Test
	void Read_patient_from_database() throws Throwable {
		
		HashSet<Patient> s = DB.loadPatient();
		System.out.println("TEST Read_patient_from_database: " + s);
		assertFalse(s.isEmpty());

		
	}
	
	@Test
	void Read_department_from_database() throws Throwable {
		
		HashSet<Department> result = DB.loadDepartment();
		
		System.out.println("RESULT OF READ DEPARTMENT: " + result);
		
		assertTrue(!result.isEmpty());
		
	}
	
	@Test
	void Assemble_hospital_on_parameter_input() {
		
		
		HashSet<Patient> patientset = new HashSet<Patient>();
		HashSet<Staff> staffset = new HashSet<Staff>();
		HashSet<Department> departmentset = new HashSet<Department>();
		Hospital h = new Hospital();
		
		// Adding patient to out-depart
		patientset.add(new Patient(323, "Hans","Hansen","Nyamwezi","TanzaniaRoad1",17,6,1972,true, "OUT1", 3, null));
		patientset.add(new Patient(756, "Børge","Hansen","Mahanji","TanzaniaRoad2",2,2,1986,true,"OUT1", 7, null));
		patientset.add(new Patient(756, "Tibo","Wuanka","Gandaza","TanzaniaRoad3",2,7,1983,true,"OUT1", 300, null));
		
		// Adding patient to in-depart with a specified bed
		patientset.add(new Patient(42, "Tage","Radezki","Kwifa","TanzaniaRoad4",14,11,1967,true,"IN1", null, 55));
		patientset.add(new Patient(47, "Ib","Gorbatjov","Bembe","TanzaniaRoad5",24,12,1996,true, "IN1", null, 98));
		
		// Adding staff to out-depart
		staffset.add(new Nurse("N54", "Birgit","Kretz","Nyamwezi","TanzaniaRoad99",2,4,1994,"OUT1"));
		staffset.add(new Doctor("D33", "Hans","Overlæge","Nyamwezi","TanzaniaRoad201",7,8,1988,"OUT1"));
		
		// Adding staff to in-depart
		staffset.add(new Nurse("N54", "Karsten","Sygepasser","Nyamwezi","TanzaniaRoad76",8,4,1976,"IN1"));
		staffset.add(new Doctor("D33", "Britta","Lægesen","Nyamwezi","TanzaniaRoad21",2,8,1955,"IN1"));
		
		// Adding staff to admin-depart
		staffset.add(new Clerk("C54", "Computer","Bug","Cyborg","TechRoad32",8,4,1586,"AD1"));
		
		// Adding the three departments
		departmentset.add(new InPatientDepart("IN1",200));
		departmentset.add(new OutPatientDepart("OUT1"));
		departmentset.add(new AdminDepart("AD1"));
		
		h = DB.buildHospital(h, departmentset, staffset, patientset);

	}

}
