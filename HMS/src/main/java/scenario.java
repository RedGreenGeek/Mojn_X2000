
public class scenario {
	
	private user_story us;
	private int id = -1;

	public scenario(user_story us) {
		
		this.us = us;
		this.id = us.getId();
	}


	public int getId() {
		return id;
	}


}
