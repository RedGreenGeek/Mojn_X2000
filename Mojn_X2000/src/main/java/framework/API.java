package framework;

import java.util.HashSet;

import framework.Departments.AdminDepart;
import framework.Departments.HealthCare.InPatientDepart;
import framework.Departments.HealthCare.OutPatientDepart;
import framework.person.*;
import framework.person.staff.*;

public class API {
	private static Hospital h;
	private static Searcher searcher;
	private static HashSet<Person> totalSet = new HashSet<Person>();
	private static API instance;
	private static ChangeReg R;
	
	public static synchronized API getInstance() {
		if (instance==null) {
			instance = new API();
			return instance;
		}
		else {
			return instance;
		}
	}
	
	private API (){
		//Loads hospital in from database
		R = new ChangeReg();
		
		//------
		h = new Hospital();
		searcher = new Searcher(h);
		
		//------
		Department In = new InPatientDepart("ER",new HashSet<Person>(),new HashSet<Person>(),7);
		Department Out = new OutPatientDepart("Cardio",new HashSet<Person>(),new HashSet<Person>());
		Department A = new AdminDepart("IT",new HashSet<Person>());
		
		R.add(h, In);
		R.add(h, Out);
		R.add(h, A);
		
		//------
		Patient P1_in = new Patient("Jens","Jensen","Zulu","Jagtvej 69",24,9,97,true,"ER");
		Patient P2_in = new Patient("Hans","Hansen","Masai","Tagensvej 101",24,12,2000,true,"ER");
		Doctor D1_in = new Doctor("Svend","Nielsen","Dansk","Doktorvej",01,01,1901,"ER");
		Nurse N1_in = new Nurse("Jonna","Nielsen","Tysk","Ikke-doktorvej",02,02,1902,"ER");
		
		R.add(In, P1_in);
		R.add(In, P2_in);
		R.add(In, D1_in);
		R.add(In, N1_in);
		
		//------
		Patient P1_out = new Patient("Søren","Sørensen","Ventre","Hellerup",24,9,97,true,"Cardio");
		Patient P2_out = new Patient("Lars","Larsen","Jysk","Nordvestjylland",20,12,1950,true,"Cardio");
		Doctor D1_out = new Doctor("Lars","Løkke","Ventre","Græsted",01,01,1950,"Cardio");
		Nurse N1_out = new Nurse("Helle","Thorning","Gucci","Herlev",02,02,1960,"Cardio");
		
		R.add(Out, P1_out);
		R.add(Out, P2_out);
		R.add(Out, D1_out);
		R.add(Out, N1_out);
		
		//------
		ICTOfficer ICTOf = new ICTOfficer("Jens","Hansen","Norway","Indian",29,2,1996,"IT");
		Clerk clerk = new Clerk("Mads","hansen","Uganda","Black-rocks Clan",23,4,2000,"IT");
		
		R.add(A,ICTOf);
		R.add(A, clerk);
		
		System.out.println(P1_in.getID()+ " " + P2_in.getID()+" "+P1_out.getID()+" "+P2_out.getID());
	}
	
	
	public static String registerPatient(String firstName, String lastName, String tribe, String address, int day, int month,
			int year, boolean alive) {
		if (Person.isValidPersonData(firstName, lastName, day, month, year, address, tribe, alive)) {
			totalSet.add(new Patient(firstName,lastName,tribe,address,day,month,year,alive,null));
			return "Patient registered succesfully.";
		}
		else {
			return "Additional information is needed.";
		}
	}
	
	public static String changePatient(String ID, String firstName, String lastName, String tribe, String address, int day, int month, int year, boolean alive) {
		Patient p = (Patient) searcher.patientSearch(ID, "", "", "").getFirst();
		if (p==null) {
			return "No patient with that ID found.";
		}
		else {
			if (Person.isValidPersonData(firstName, lastName, day, month, year, address, tribe, alive)) {
				p.setFirstName(firstName);
				p.setLastName(lastName);
				p.setBirthDay(day, month, year);
				p.setAdress(address);
				p.setTribe(tribe);
				p.setAlive(alive);
				return "Patient information has been changed successfully.";
			}
			else {
				return "Illegal information changes.";
			}
		}
	}
	
	/* _____________ STAFF REGISTRATION for M2 ______________ */
	
	public static String registerStaff (String jobtype ,String firstName, String lastName,String adress, String tribe, int day, int month, int year) {
		
		Staff p;
		
		if (Person.isValidPersonData(firstName, lastName, day, month, year, adress, tribe, true)) {
			
			if (jobtype.equals("Clerk")) {
				p = new Clerk(firstName, lastName, adress, tribe, day, month ,year, null);
			}
			
			else if (jobtype.equals("Nurse")) {
				p = new Nurse(firstName, lastName, adress, tribe, day, month ,year, null);
			}
			
			else if (jobtype.equals("Doctor")) {
				p = new Doctor(firstName, lastName, adress, tribe, day, month ,year, null);
			}
			
			else if (jobtype.equals("ICTOfficer")) {
				p = new ICTOfficer(firstName, lastName, adress, tribe, day, month ,year, null);
			}
			
			else {
				return "Unsuccesful registration!";
			}
			
			R.add(h, p);
			
			return "The " + jobtype + " has been registered succesfully!";
		
		}
		
		else {
			
			return "Unsuccesful registration!";
			
		}
		
		
	}
	
	
	
	
	
	
	
		
}