

package application.controller;

import application.model.Session;
import application.view.SearchStaffView;
import framework.API;

public class SearchStaffController  extends Controller{
	private SearchStaffView view;
	
	public SearchStaffController(Session session) {
		this.sessionModel = session;
	}
	
	public void display() {
		view.setVisible(true);
	}
	
	public void setView(SearchStaffView view) {
		this.view = view;
	}
	
	public String SearchAPI(String firstName, String surname, String email, String day, String month, String year, String id) {
		API api =  API.getInstance();
		String  birthday = "";

		if (!(day.equals(""))) {
			  birthday = day+"-"+month+"-"+year;
		}
		String a = api.staffSearcher(this.getSession().getPassword(),this.getSession().getUsername(),id,  firstName,  surname,  birthday,  email); //last is email
		return a;
	}

	public void Back2Main() {
		view.setVisible(false);
	}
}