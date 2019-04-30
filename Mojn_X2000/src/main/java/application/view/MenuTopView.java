package application.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import application.model.Session;

public class MenuTopView extends JMenuBar {

	private static final long serialVersionUID = 1234324324L;
	JLabel lblUser;
	JButton backBtn;
	JLabel lblTitle;
	
	public MenuTopView(String title) {
		setLayout(new BorderLayout(0, 0));
		
		lblTitle = new JLabel();
		lblTitle.setText(title);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblTitle, BorderLayout.CENTER);

		JMenu mnPassword = new JMenu();
		mnPassword.setIcon(new ImageIcon("C:\\Users\\anton\\OneDrive\\Dokumenter\\Uni\\Uni\\4. semester\\Agil\\Exam proj\\icons\\feature.png"));
		add(mnPassword, BorderLayout.WEST);

		JButton btnChange = new JButton("Change Password");
		mnPassword.add(btnChange);

		JButton btnAdd = new JButton("Add Password");
		mnPassword.add(btnAdd);				

		lblUser = new JLabel();
		lblUser.setBorder(new EmptyBorder(0, 0, 0, 10));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUser, BorderLayout.EAST);

		setBorder(new EtchedBorder(10));

		
		
	}
	
	public MenuTopView(String title, Object back) {
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

