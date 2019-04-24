package framework;

import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.Patient;

public class M4 {
	/* _____________ PATIENT ADMISSION for M4 ______________ */
	public String patientAdmission(String trilvl, String department, String firstName, String lastName, String adress, String tribe, int day, int month, int year) {
		Patient p;
		Department depart;
		int triagelvl = 1;
		try {
			if (!trilvl.equals("")) {
				triagelvl = Integer.parseInt(trilvl);
			}
		} catch (Exception e) {return "The triage level specification wasn't an integer";}
		
		if (searcher.departmentSearch(department).size() != 1 ) {
			return "The department specification is ambigious";
		}
		if ((searcher.departmentSearch(department).getFirst() instanceof framework.Departments.AdminDepart)) {
			return "The department is an administrativ department";
		}
		else {
			depart = searcher.departmentSearch(department).peek();
		}
		
		
		if (Person.isValidPersonData(firstName, lastName, day, month, year, adress, tribe, true)) {
			p = new Patient(firstName, lastName, adress, tribe, day, month ,year, true, department);
			if (depart instanceof framework.Departments.HealthCare.InPatientDepart) {
				InPatientDepart inDepart = (InPatientDepart) depart;
				R.add(inDepart, p);
				inDepart.beds.AllocateBed(p);
			}
			else {
				OutPatientDepart outDepart = (OutPatientDepart) depart;
				R.add(outDepart, p);
				outDepart.EnQueue(p, triagelvl);
			}
			return "The patient has been registered succesfully to " + department +  "!";
		
		}
		
		else {
			
			return "Unsuccesful registration cause to invalid patient data!";
			
		}
	}
	
		// The input to this function should be specified in the gui so when
		// I search for the patient and click remove this function is given the patient ID
	public String discharge(String ID) {
		Patient p;
		Department depart;
		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient ID's isn't uniqe";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}
		
		depart = searcher.departmentSearch(p.getDepartment()).peek();
		
		R.remove(depart, p);
		
		return "The patient " + p.getFirstName() + " " + p.getLastName() + ", " + ID + ", has been removed succesfully from " + depart.getName();
	}
	
	public String movePatientDepart(String ID, String depart, String trilvl) {
		Patient p;
		String return_message;

		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient wasn't moved";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}
		
		
		String[] bday = p.getBirthday().split("-");
		int day = Integer.parseInt(bday[0]);
		int month = Integer.parseInt(bday[1]);
		int year = Integer.parseInt(bday[2]);
		return_message = patientAdmission(trilvl, depart, p.getFirstName(), p.getLastName(), p.getAdress(), p.getTribe(), day, month, year);
		if (return_message.equals("The department specification is ambigious") || return_message.equals("Unsuccesful registration cause to invalid patient data!") || return_message.equals("The triage level specification wasn't an integer") || return_message.equals("The department is an administrativ department")) {
			return "The patient wasn't moved";
		} else {
			discharge(ID);
			return "The patient was moved succesfully to " + depart;
		}
	}
	
	public String movePatientBed(String ID, String newBed) {
		InPatientDepart Department;
		Patient p;
		String returnmessage;
		int bedNo;
		try {
			bedNo = Integer.parseInt(newBed);
		} catch (Exception e) {return "You need to specify the bed number as an integer";}
		// Should not be able to give an error message.
		if (searcher.patientSearch(ID, "", "", "").size() != 1) {
			return "The patient wasn't moved cause to invalid ID";
		}else {p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();}

		// This should also not could give an error message
		if (!(searcher.departmentSearch(p.getDepartment()).getFirst() instanceof framework.Departments.HealthCare.InPatientDepart)) {
			return "The department isn't an indepartment";
		}
		else {Department = (InPatientDepart) searcher.departmentSearch(p.getDepartment()).peek();}
		

			
			if (Department.beds.getMaxBeds() < bedNo) {
				return "There aren't that many beds in the department";
			}
			returnmessage = Department.beds.AllocateBed(p, bedNo);	
			if (returnmessage.equals("Ok")) {
				Department.beds.Discharge(p);
				return "The patient was moved succesfully";
			}
			if (returnmessage.equals("Same bed")) {
				return "The patient was moved succesfully to the same bed";
			}
			else {return "The bed wasn't free";}
		
	}


}
