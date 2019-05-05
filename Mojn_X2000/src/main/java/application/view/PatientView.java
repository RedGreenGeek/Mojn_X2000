package application.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import application.controller.Controller.JobTypes;
import application.controller.PatientController;
import application.utils.GridBagLayoutUtils;

public class PatientView extends JFrame {
	private static final long serialVersionUID = 1233547847L;
	private PatientController controller;
	private JLabel lblPatient;
	private JobTypes clear;
	private MenuTopView menuTop;
	private void setTop(JobTypes clear) {
		this.menuTop = new MenuTopView("Patient Menu", this.clear);
	}
	
	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public PatientView(PatientController controller) {
		this.controller = controller;
		this.clear = controller.getRole();
		this.setTop(controller.getRole());
		initGUI();
	}
	
	// All components of the window are defined
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Patient Menu");
		setPreferredSize(new Dimension(800, 800));
		
		lblPatient = new JLabel("Patient Menu");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		// A panel for the buttons are defined
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		// buttons
		JButton btnSearch = new JButton("Search Patient");
		Image searchImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Patient menu/search.png")).getImage();
		btnSearch.setIcon(new ImageIcon(searchImg));
		btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnRegister = new JButton("Register Patient");
		Image registerImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Patient menu/register.png")).getImage();
		btnRegister.setIcon(new ImageIcon(registerImg));
		btnRegister.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRegister.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnMove = new JButton("Move Between Departs");
		Image moveImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Patient menu/move.png")).getImage();
		btnMove.setIcon(new ImageIcon(moveImg));
		btnMove.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMove.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnEdit = new JButton("Edit Patient");
		Image editImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Patient menu/edit.png")).getImage();
		btnEdit.setIcon(new ImageIcon(editImg));
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		JButton btnDischarge = new JButton("Discharge Patient");
		Image dischargeImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Patient menu/discharge.png")).getImage();
		btnDischarge.setIcon(new ImageIcon(dischargeImg));
		btnDischarge.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDischarge.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnAdmit = new JButton("Admit Patient");
		Image admitImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Patient menu/admit.png")).getImage();
		btnAdmit.setIcon(new ImageIcon(admitImg));
		btnAdmit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdmit.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnMoveBed = new JButton("Move Between Beds");
		Image moveBedImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Patient menu/movebed.png")).getImage();
		btnMoveBed.setIcon(new ImageIcon(moveBedImg));
		btnMoveBed.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMoveBed.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBack.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//Listeners:
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
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
		
		menuTop.btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToAddPassword();
			}
		});
		
		menuTop.btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			controller.ToChangePassword();
			}
		});
		
		// Buttons are added to the button panel and the users clearance is checked
		buttonsPanel.add(btnEdit, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,50,10));
		
		if(clear == JobTypes.Nurse || clear == JobTypes.ICTOfficer || clear == JobTypes.Clerk ) {
		buttonsPanel.add(btnRegister, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,50,10));}
		
		if(clear == JobTypes.Clerk || clear == JobTypes.ICTOfficer ) {
		buttonsPanel.add(btnAdmit, GridBagLayoutUtils.constraint(2, 2, 0, 0, 0,10,50,10));}

		if(clear == JobTypes.Doctor || clear == JobTypes.ICTOfficer || clear == JobTypes.Nurse ) {
		buttonsPanel.add(btnMove, GridBagLayoutUtils.constraint(4, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnSearch, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnDischarge, GridBagLayoutUtils.constraint(1, 2, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnMoveBed, GridBagLayoutUtils.constraint(3, 2, 0, 0, 0,10,50,10));	
		}
		
		
		// all the components are added to the frame
		add(buttonsPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
		add(btnBack, BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);
		
	}
	 public MenuTopView getMenuTop() {
		 return this.menuTop;
	 }
}
