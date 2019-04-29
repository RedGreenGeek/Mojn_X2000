package application.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import application.controller.StaffController;
import application.controller.HospitalController;
import application.controller.MainMenuController;
import application.controller.LoginController;
import application.controller.PatientController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class PatientView extends JFrame {

	private static final long serialVersionUID = 12347L;
	private PatientController controller;
	private Session session;
	private String path = "C:\\Users\\Mathi\\Desktop\\4. semester\\Agile Object-Oriented Software Development\\Icons\\";


	
	
	private JLabel lblUser;
	private JLabel lblPatient;
	
	
	public PatientView(PatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		this.session = Session.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Patient Menu");
		setPreferredSize(new Dimension(800, 800));
		
		// Labels
		lblPatient = new JLabel("Patient Menu");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		// buttons
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		JButton btnSearch = new JButton("Search Patient");
		btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setIcon(new ImageIcon(path+"Patient menu\\search.png"));
		
		JButton btnRegister = new JButton("Register Patient");
		btnRegister.setIcon(new ImageIcon(path+"Patient menu\\register.png"));
		btnRegister.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRegister.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnMove = new JButton("Move Between Departs");
		btnMove.setIcon(new ImageIcon(path+"Patient menu\\move.png"));
		btnMove.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMove.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnEdit = new JButton("Edit Patient");
		btnEdit.setIcon(new ImageIcon(path+"Patient menu\\edit.png"));
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		JButton btnDischarge = new JButton("Discharge Patient");
		btnDischarge.setIcon(new ImageIcon(path+"Patient menu\\discharge.png"));
		btnDischarge.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDischarge.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnAdmit = new JButton("Admit Patient");
		btnAdmit.setIcon(new ImageIcon(path+"Patient menu\\admit.png"));
		btnAdmit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdmit.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnMoveBed = new JButton("Move Between Beds");
		btnMoveBed.setIcon(new ImageIcon(path+"Patient menu\\movebed.png"));
		btnMoveBed.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMoveBed.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnAllocate = new JButton("Allocate Patient To Bed");
		btnAllocate.setIcon(new ImageIcon(path+"Patient menu\\allocatebed.png"));
		btnAllocate.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAllocate.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnBack = new JButton("Back");
		btnBack.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBack.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		buttonsPanel.add(btnEdit, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,50,10));
		
		if(session.getRole() == "Nurse" || session.getRole() == "ICT-Officer" ) {
		buttonsPanel.add(btnRegister, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,50,10));}
		
		if(session.getRole() == "Clerk" || session.getRole() == "ICT-Officer" ) {
		buttonsPanel.add(btnSearch, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnAdmit, GridBagLayoutUtils.constraint(2, 2, 0, 0, 0,10,50,10));}

		if(session.getRole() == "Doctor" || session.getRole() == "ICT-Officer" || session.getRole() == "Nurse" ) {
		buttonsPanel.add(btnMove, GridBagLayoutUtils.constraint(4, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnDischarge, GridBagLayoutUtils.constraint(1, 2, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnMoveBed, GridBagLayoutUtils.constraint(3, 2, 0, 0, 0,10,50,10));	
		buttonsPanel.add(btnAllocate, GridBagLayoutUtils.constraint(4, 2, 0, 0, 0,10,50,10));}
		
		
		
		
//		 toolbar
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		
		JPanel userPanel = new JPanel();
		lblUser = new JLabel();
		userPanel.add(lblUser, BorderLayout.EAST);
		
		
		centerPanel.add(lblPatient,GridBagLayoutUtils.constraint(2, 0, 0, 0, 50, 0, 100, 0));
		centerPanel.add(buttonsPanel,GridBagLayoutUtils.constraint(2, 1, 0, 0, 0, 0, 0, 0) );
		centerPanel.add(btnBack,GridBagLayoutUtils.constraint(2, 3, 0, 0, 0, 0, 0, 0) );
		
		
		add(centerPanel, BorderLayout.CENTER);
		add(userPanel, BorderLayout.NORTH);
	
		pack();
		setLocationRelativeTo(null);
	
		//Listeners:
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
		btnAllocate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToAllocate();
			}
		});
		btnAdmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToAdmit();
			}
		});
		btnMoveBed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToMoveBed();
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToEdit();
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToRegister();
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToSearch();
			}
		});
		
		btnMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToDepartmentMove();
			}
		});
		
		btnDischarge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToDischarge();
			}
		});
		
	
		
		
		
		
	}
	
	
	public void setSession(Session sessionModel) {
		lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
	
	}
}
	
	

	
