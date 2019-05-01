package application.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import application.controller.Controller;
import application.model.Session;
import application.model.Session.JobTypes;

public class MenuTopView extends JMenuBar {

	private static final long serialVersionUID = 1234324324L;
	JLabel lblUser;
	JButton backBtn;
	JLabel lblTitle;
	JButton btnAdd = new JButton("Add Password");;
	JButton btnChange = new JButton("Change Password");;
	JMenu mnPassword;
	Session session;
	
	public MenuTopView(String title) {
		this.session = Session.getInstance();
		setLayout(new BorderLayout(0, 0));
		
		lblTitle = new JLabel();
		lblTitle.setText(title);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblTitle, BorderLayout.CENTER);
		if (session.getRole() == JobTypes.ICTOfficer || session.getRole() == JobTypes.Clerk ) {
			mnPassword = new JMenu();
			Image featureImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/feature.png")).getImage();
			mnPassword.setIcon(new ImageIcon(featureImg));
			add(mnPassword, BorderLayout.WEST);
				if (session.getRole() == JobTypes.ICTOfficer){
					mnPassword.add(btnChange);
				}
			mnPassword.add(btnAdd);				
		}
		lblUser = new JLabel();
		lblUser.setBorder(new EmptyBorder(0, 0, 0, 10));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUser, BorderLayout.EAST);

		setBorder(new EtchedBorder(10));

		
		
	}
	
	public MenuTopView(String title, String back) {
		setLayout(new BorderLayout(0, 0));

		lblUser = new JLabel();
		lblUser.setBorder(new EmptyBorder(0, 0, 0, 10));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUser, BorderLayout.EAST);
		
		setBorder(new EtchedBorder(10));
		
		lblTitle = new JLabel(title);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblTitle, BorderLayout.CENTER);
		
		backBtn = new JButton("Back");
		backBtn.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		add(backBtn, BorderLayout.WEST);
		

		
																																																																																																																																																																																			
		
	}
	
	public void setSession(Session sessionModel) {
		lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
	
	}

	
	
}

