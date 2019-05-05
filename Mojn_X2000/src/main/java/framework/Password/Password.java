package framework.Password;
import java.util.HashMap;

public class Password {
	//Create private variables to store password values in
	private HashMap<String, String> PassMap;
	private long hashValue;
	
	public Password() {
		this.PassMap = new HashMap<String,String>();
	}
	
	public Password(HashMap<String,String> hashmap) {
		this.PassMap = hashmap;
	}
	
	//Public method that adds a hashed password to a map
	public void addPassToMap(String Pass, String StaffId) {
		HashPassword(Pass);
		String key = String.valueOf(this.hashValue);
		this.PassMap.put(StaffId, key);
	}
	
	//method to check if a StaffId exists in the password storage
	public boolean checkUniqueID(String StaffID) {
		String value = this.PassMap.get(StaffID);

		if (value == null) {
		    return false;
		} else {return true;}
	}

	//Checks if entered password entered is equal to the password stored for a StaffId
	public boolean checkPassword(String EnterPass, String StaffId) {
		//Security - if one were to enter null as password, access would always be granted!
		if (EnterPass == null) {
			return false;
		}
		//Hash the entered password
		HashPassword(EnterPass);
		String key = String.valueOf(this.hashValue);
		//Checks if the value in the password storage is equal to the hashed entered password
		if (this.PassMap.containsKey(StaffId) && this.PassMap.get(StaffId).equals(key)) {
			return true;
		}
		else {return false;}
	}
	
	//Hash a password (string) so it gets a unique Long value
	private void HashPassword(String Pass) {
		//hashing is done using primes, to ensure unique long values.
		long prime = 2147483647;
		this.hashValue = 127;
		//Iterate over the string, and map different string values to long values
		for (int i = 0; i < Pass.length(); i++){
		    char c = Pass.charAt(i);   
		    int charVal = (int) c;
		    	this.hashValue = (hashValue+(charVal * i * 8191 ) * prime);  	
		}
	}
	//Simple clearance function mapping StaffID (called userID) to different integer values representing clearance.
	public int getClearence(String password, String userID) {
		if (checkPassword(password,userID)) {
			if (userID.charAt(0)=='C') {
				return 1;
			}
			else if (userID.charAt(0)=='N') {
				return 2;
			}
			else if (userID.charAt(0)=='D') {
				return 3;
			}
			else { return 4;}
		}
		return 0;
	}
}
