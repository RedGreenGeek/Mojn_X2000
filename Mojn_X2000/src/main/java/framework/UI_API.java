package framework;

import java.util.HashSet;

import javax.swing.text.html.HTMLDocument.Iterator;

import framework.person.*;
import framework.*;

public class UI_API {
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PATIENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public static String RegisterPatient(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive) {
		
		try {
			Patient patient = new Patient(firstName, lastName, adress, tribe, day, month, year, alive);
			ChangeReg C = new ChangeReg();
			C.add(Hospital.getHospital(),patient);
	
			return String.format("%s %s registered succesfully!", firstName, lastName);
		} catch (IllegalArgumentException e) {
			
		}
			return "Insufficient data: Please fill all fields with correct data.";
		
	}
		

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   STAFF   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public static String RegisterStaff(String firstName, String lastName, String adress, String tribe, int day, int month, int year, boolean alive, String JobType) {
		
		try {
			Staff staff = new Staff(firstName, lastName, adress, tribe, day, month, year, alive, JobType);
			ChangeReg C = new ChangeReg();
			C.add(Hospital.getHospital(),staff);
	
			return String.format("%s %s registered succesfully!", firstName, lastName);
		} catch (IllegalArgumentException e) {
			
		}
			return "Insufficient data: Please fill all fields with correct data.";
		
	}
	/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DEPARTMENTS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	
	public static String[] StaffAtDepartment(String dName) {
	    Searcher s = Searcher.getInstance(Hospital.getHospital());
	    
	    try {
	    	java.util.Iterator<Department> I = s.departmentSearch(dName).iterator();
	    
	    	String[] result = new String[0];
		    while (I.hasNext()) {
		      Department d = I.next();    
		      if (d.getName().equals(dName)) {
		        
		    	  HashSet<Person> dummy = d.getStaff();
		    	  result = new String[dummy.size()];
		    	  int i = 0;
		    	  java.util.Iterator<Person> I1 = dummy.iterator();
		    	  
		    	  while (I1.hasNext()){
		    		  
		    		  Staff staff = (Staff) I1.next(); //downcast finally worked!
		    	  
		    		  result[i++] = "StaffID: " + staff.getSerialID() + "Firstname: " + staff.getFirstName() + "Lastname: " + staff.getLastName();
		    		  
		    	  }
		        
		    	  return result;
		      }
		      
		      return result;
		      
		    }
	    } catch (IllegalArgumentException e) {
	    	
	    	return null;
	    	
	    }
	    
	    return null;
	    
	}
	
	//searches for department and returns beds in use for the department matching the name.
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   USER ACCESS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   DATABASE   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   PARTICIPATION LISTS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
