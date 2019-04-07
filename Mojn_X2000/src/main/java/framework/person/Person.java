package framework.person;


public abstract class Person {
    
	private String firstName;
	private String lastName;
	private String birthday;
	private String adress;
	private String tribe;
	private boolean alive;
	
	public String getAdress() {
	    return this.adress;
	}
	
	protected void setAdress(String adress) {
	    this.adress = adress;
	}
	
	public String getTribe() {
	    return this.tribe;
	}
	
	protected void setTribe(String tribe) {
	    this.tribe = tribe;
	}
	
	public boolean isAlive() {
	    return this.alive;
	}
	
	protected void setAlive(boolean alive) {
	    this.alive = alive;
	}
	
	protected static boolean checkString(String string) {
		
		return string.replaceAll(" ", "") != "";
		
	}
	
	protected static boolean checkBirthDay(int day, int month, int year) {
		

	    if (year%4==0 && year <= 2019) {
	    	
	        if (month == 4 || month == 6 || month == 9 || month == 11) {
	            if (day > 0 && day <= 31) {
	                return true;
	            } else { return false; }
	            
	        }
	        
	        else if (month == 2) {
	            if (day > 0 && day <= 29) {
	                return true;
	            } else { return false; }
	            
	        }
	        
	        else if (day > 0 && day <= 31 && month > 0 && month <=12) {
	            return true;
	        } 
	        
	        else { return false; }
	        
	    }
	    else {
	        if (month == 4 || month == 6 || month == 9 || month == 11) {
	            if (day > 0 && day <= 31) {
	                return true;
	            } else { return false; }
  
	        }
	        else if (month == 2) {
	            if (day > 0 && day <= 28) {
	                return true;
	            } else { return false; }

	        }
	        else if (day > 0 && day <= 31 && month > 0 && month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
	            return true;
	        } 
	        
	        else { return false; }

	    }
	}
	
	protected void setBirthDay(int day, int month, int year) {
	
	    String days = "" + day;
	    String months = "" + month;
	    String years = "" + year;
	    if (day <10) {
	        days = "0" + day;
	    }
	    if (month <10) {
	        months = "0" + month;
	    }
	    if (year%4==0) {
	        if (month == 4 || month == 6 || month == 9 || month == 11) {
	            if (day > 0 && day <= 31) {
	                this.birthday =  days + "-" + months + "-" +years;
	            }
	        }
	        if (month == 2) {
	            if (day > 0 && day <= 29) {
	                this.birthday =  days + "-" + months + "-" +years;
	            }
	        }
	        if (day > 0 && day <= 31 && month > 0 && month <=12) {
	            this.birthday =  days + "-" + months + "-" +years;
	        }
	    }
	    else {
	        if (month == 4 || month == 6 || month == 9 || month == 11) {
	            if (day > 0 && day <= 31) {
	                this.birthday =  days + "-" + months + "-" +years;
	            }
	        }
	        if (month == 2) {
	            if (day > 0 && day <= 28) {
	                this.birthday =  days + "-" + months + "-" +years;
	            }
	        }
	        if (day > 0 && day <= 31 && month > 0 && month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
	            this.birthday =  days + "-" + months + "-" +years;
	        }    
	    }
	}
	
	public String getBirthday() {
	    return birthday;
	}
	
	public String getFirstName() {
	    return firstName;
	}
	
	public String getLastName() {
	    return lastName;
	}
	
	protected void setFirstName(String name) {
		this.firstName = name;
	}
	
	protected void setLastName(String name) {
	    this.lastName = name;
	}
	
	static public boolean isValidPersonData(String firstName, String lastName, int day, int month, int year, String adress, String tribe, boolean alive) {
		
		return checkBirthDay(day, month, year) && checkString(firstName) && checkString(lastName) && checkString(adress) && checkString(tribe);

	}

}