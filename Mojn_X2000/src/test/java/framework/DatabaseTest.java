package framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import framework.Departments.AdminDepart;
import framework.Departments.HealthCare.*;
import framework.person.Patient;
import framework.person.Staff;
import framework.person.staff.Clerk;
import framework.person.staff.Doctor;
import framework.person.staff.ICTOfficer;
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
		
		String message1 = DB.writeDepartment(dOUT);
		String message2 = DB.writeDepartment(dIN);
		String message3 = DB.writeDepartment(dAD);
		
		assertEquals("SUCCESS!", message1);
		assertEquals("SUCCESS!", message2);
		assertEquals("SUCCESS!", message3);
		
	}
	
	@Test
	void Update_and_read_counters() throws Throwable {
		
		assertEquals(6, DB.loadStaffCounter());
		assertEquals(6, DB.loadPatientCounter());
		
		
	}
	
	@Test
	void Read_staff_from_database() throws Throwable {
		
		HashSet<Staff> s = DB.loadStaff();
		assertFalse(s.isEmpty());

		
	}
	
	@Test
	void Read_patient_from_database() throws Throwable {
		
		HashSet<Patient> s = DB.loadPatient();
		assertFalse(s.isEmpty());

		
	}
	
	@Test
	void Read_department_from_database() throws Throwable {
		
		HashSet<Department> result = DB.loadDepartment();		
		assertTrue(!result.isEmpty());
		
	}
	
	@Test
	void Assemble_hospital_on_parameter_input() throws Throwable {
		
		DB.clean();
		
		HashSet<Patient> patientset = new HashSet<Patient>();
		HashSet<Staff> staffset = new HashSet<Staff>();
		HashSet<Department> departmentset = new HashSet<Department>();
		
		// Making patients 
		
		Patient p1 = new Patient(323, "Hans","Hansen","Nyamwezi","TanzaniaRoad1",17,6,1972,true, "OUT1", 3, null);
		Patient p2 = new Patient(756, "Børge","Hansen","Mahanji","TanzaniaRoad2",2,2,1986,true,"OUT1", 7, null);
		Patient p3 = new Patient(756, "Tibo","Wuanka","Gandaza","TanzaniaRoad3",2,7,1983,true,"OUT1", 300, null);
		
		// Adding patient to out-depart
		patientset.add(p1); DB.writePatient(p1);
		patientset.add(p2); DB.writePatient(p2);
		patientset.add(p3); DB.writePatient(p3);
		
		// Adding patient to in-depart with a specified bed
		
		Patient p4 = new Patient(42, "Tage","Radezki","Kwifa","TanzaniaRoad4",14,11,1967,true,"IN1", null, 55);
		Patient p5 = new Patient(47, "Ib","Gorbatjov","Bembe","TanzaniaRoad5",24,12,1996,true, "IN1", null, 98);
		patientset.add(p4); DB.writePatient(p4);
		patientset.add(p5); DB.writePatient(p5);
		
		// Adding staff to out-depart
		
		Staff s1 = new Nurse("N54", "Birgit","Kretz","Nyamwezi","TanzaniaRoad99",2,4,1994,"OUT1");
		Staff s2 = new Doctor("D33", "Hans","Overlæge","Nyamwezi","TanzaniaRoad201",7,8,1988,"OUT1");
		staffset.add(s1); DB.writeStaff(s1);
		staffset.add(s2); DB.writeStaff(s2);
		
		// Adding staff to in-depart
		Staff s3 = new Nurse("N75", "Karsten","Sygepasser","Nyamwezi","TanzaniaRoad76",8,4,1976,"IN1");
		Staff s4 = new Doctor("D666", "Britta","Lægesen","Nyamwezi","TanzaniaRoad21",2,8,1955,"IN1");
		staffset.add(s3); DB.writeStaff(s3);
		staffset.add(s4); DB.writeStaff(s4);
		
		// Adding staff to admin-depart
		Staff s5 = new Clerk("C54", "Computer","Bug","Cyborg","TechRoad32",8,4,1586,"AD1");
		staffset.add(s5);
		
		// Adding the three departments
		InPatientDepart d1 = new InPatientDepart("IN1",200);
		OutPatientDepart d2 = new OutPatientDepart("OUT1");
		AdminDepart d3 = new AdminDepart("AD1");
		departmentset.add(d1); DB.writeDepartment(d1);
		departmentset.add(d2); DB.writeDepartment(d2);
		departmentset.add(d3); DB.writeDepartment(d3);

		Hospital h = DB.buildHospital(5, 5, departmentset, staffset, patientset);
		
		assertEquals(h.getAllPatientSet(), patientset);
		assertEquals(h.getStaffSet(), staffset);
		assertEquals(h.getDepartSet(), departmentset);

	}
	
	@Test
	void boot_hospital() throws Throwable {
		
		DB.clean();
		
		HashSet<Patient> patientset = new HashSet<Patient>();
		HashSet<Staff> staffset = new HashSet<Staff>();
		HashSet<Department> departmentset = new HashSet<Department>();
		
		// Making patients 
		
		Patient p1 = new Patient(1, "Hans","Hansen","Nyamwezi","TanzaniaRoad1",17,6,1972,true, "OUT1", 3, null);
		Patient p2 = new Patient(2, "Børge","Hansen","Mahanji","TanzaniaRoad2",2,2,1986,true,"OUT1", 7, null);
		Patient p3 = new Patient(3, "Tibo","Wuanka","Gandaza","TanzaniaRoad3",2,7,1983,true,"OUT1", 300, null);
		
		// Adding patient to out-depart
		patientset.add(p1); DB.writePatient(p1);
		patientset.add(p2); DB.writePatient(p2);
		patientset.add(p3); DB.writePatient(p3);
		
		// Adding patient to in-depart with a sp8ecified bed
		
		Patient p4 = new Patient(4, "Tage","Radezki","Kwifa","TanzaniaRoad4",14,11,1967,true,"IN1", null, 55);
		Patient p5 = new Patient(5, "Ib","Gorbatjov","Bembe","TanzaniaRoad5",24,12,1996,true, "IN1", null, 98);
		patientset.add(p4); DB.writePatient(p4);
		patientset.add(p5); DB.writePatient(p5);
		
		// Adding staff to out-depart
		
		Staff s1 = new Nurse("N1", "Birgit","Kretz","Nyamwezi","TanzaniaRoad99",2,4,1994,"OUT1");
		Staff s2 = new Doctor("D2", "Hans","Overlæge","Nyamwezi","TanzaniaRoad201",7,8,1988,"OUT1");
		staffset.add(s1); DB.writeStaff(s1);
		staffset.add(s2); DB.writeStaff(s2);
		
		// Adding staff to in-depart
		Staff s3 = new Nurse("N3", "Karsten","Sygepasser","Nyamwezi","TanzaniaRoad76",8,4,1976,"IN1");
		Staff s4 = new Doctor("D4", "Britta","Lægesen","Nyamwezi","TanzaniaRoad21",2,8,1955,"IN1");
		staffset.add(s3); DB.writeStaff(s3);
		staffset.add(s4); DB.writeStaff(s4);
		
		// Adding staff to admin-depart
		Staff s5 = new Clerk("C5", "Computer","Bug","Cyborg","TechRoad32",8,4,1586,"AD1");
		Staff s6 = new ICTOfficer("C6", "El-giganten","Power","Transistor","TechRoad345",8,4,1776,"AD1");
		staffset.add(s5); DB.writeStaff(s5); staffset.add(s6); DB.writeStaff(s6); 
		
		// Adding the three departments
		AdminDepart d1 = new AdminDepart("AD1");
		InPatientDepart d2 = new InPatientDepart("IN1",200);
		OutPatientDepart d3 = new OutPatientDepart("OUT1");
		departmentset.add(d1); DB.writeDepartment(d1);
		departmentset.add(d2); DB.writeDepartment(d2);
		departmentset.add(d3); DB.writeDepartment(d3);

		Hospital h = DB.buildHospital(5,5,departmentset, staffset, patientset);

		Hospital h_res = DB.boot();
		
		assertFalse(h_res.getAllPatientSet().isEmpty());
		assertFalse(h_res.getAllStaff().isEmpty());
		assertFalse(h_res.getDepartSet().isEmpty());
		assertEquals(Patient.counter, 5);
		assertEquals(Staff.counter, 6);
		
		System.out.println("BUILD D 1: " + h.getDepartSet() + "\n" + h.getAllPatientSet() + "\n" + h.getAllStaff());
		System.out.println("BOOT D 1: " + h_res.getDepartSet() + "\n" + h_res.getAllPatientSet() + "\n" + h_res.getAllStaff());
		
	}
}