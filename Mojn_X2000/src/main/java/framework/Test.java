package framework;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		API api = API.getInstance();
		api.getParticipationList("I","I",true,true,true,true);
	}
}
