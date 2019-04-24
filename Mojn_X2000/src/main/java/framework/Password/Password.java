package framework.Password;
import java.util.HashMap;


public class Password {
	private HashMap<String, String> PassMap;
	private long hashValue;
	private static Password self;
	
	public static synchronized Password getInstance() {
		if (self == null){
			self = new Password();
		}
		return self; 
	}

	private Password() {
		this.PassMap = new HashMap<String,String>();		
	}
	
	public void addPassToMap(String Pass, String StaffId) {
		HashPassword(Pass);
		String key = String.valueOf(this.hashValue);
						
		this.PassMap.put(StaffId, key);
		
	}
	
	public boolean checkUniqueID(String StaffID) {
		String value = this.PassMap.get(StaffID);

		if (value == null) {
		    return false;
		} else {
		    return true;
		}
		
	}

	
	public boolean checkPassword(String EnterPass, String StaffId) {
		if (EnterPass == null) {
			System.out.println("Do NOT try to hack the passwords");
			return false;
		}
		HashPassword(EnterPass);
		String key = String.valueOf(this.hashValue);
		System.out.println("Key is: " + key);

		if (this.PassMap.get(StaffId).equals( key)) {
			System.out.println("Acces granted");
			return true;
		}
		
		else {
		System.out.println("Acces DENIED!!!");
		return false;}
	}
	
	private void HashPassword(String Pass) {
		long prime = 2147483647;
		this.hashValue = 127;
		
		for (int i = 0; i < Pass.length(); i++){
		    char c = Pass.charAt(i);   
		    int charVal = (int) c;
		    	
		    	this.hashValue = (hashValue+(charVal * i * 8191 ) * prime);
		 
		 	    	
		}
	}
	
	
}
