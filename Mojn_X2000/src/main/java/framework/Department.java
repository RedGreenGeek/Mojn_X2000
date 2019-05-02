package framework;
import java.util.*;

public abstract class Department {
	
	private HashSet<Person> staffSet = new HashSet<Person>();
	private String departName = null;
	
	protected  String getName(){
		return departName;
	}
	
	protected void setName(String departmentName) {
		this.departName = departmentName;
	}
	
	protected HashSet<Person> getStaff() {
		return this.staffSet;
	}
	
	protected void setStaff(HashSet<Person> staffSet) {
		this.staffSet = staffSet;
	}

	protected HashSet<Person> getPatient(){
		System.err.println("Warning, only HCDepartments has patients.");
		return(new HashSet<Person>());
	}
	
	protected void setPatient(HashSet<Person> patientSet) {}
	
	@Override
	public String toString() {
		return departName;
	}
}
