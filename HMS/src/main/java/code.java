
public class code {
	
	private int id;

	public code(scenario s) {
		
		this.id = s.getId();
		
	}

	public int getId() {
		return id;
	}
	
	
	public boolean isCorrect(scenario s) {
		
		boolean result = s.getId() == id;
		
		return result;
		
	}
	

}
