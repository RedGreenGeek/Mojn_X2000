package application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import application.controller.MainMenuController;
import application.utils.GridBagLayoutUtils;

public class MainMenuView extends JFrame {
	private static final long serialVersionUID = 98907522357896452L;
	private MainMenuController controller;
	private MenuTopView menuTop = new MenuTopView("Main Menu");
	
	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public MainMenuView(MainMenuController controller) {
		this.controller = controller;
		initGUI();
	}
	
	// All components of the window are defined
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Menu");
		setPreferredSize(new Dimension(800, 700));
		
		// A panel for the buttons are defined
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		JButton btnPatient = new JButton("Patient");
		Image patientImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Patient.png")).getImage();
		btnPatient.setIcon(new ImageIcon(patientImg));
		btnPatient.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPatient.setHorizontalTextPosition(SwingConstants.CENTER);	
		
		JButton btnStaff = new JButton("Staff");
		Image StaffImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Staff.png")).getImage();
		btnStaff.setIcon(new ImageIcon(StaffImg));		
		btnStaff.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnStaff.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnHospital = new JButton("Hospital");
		Image HospitalImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/hospital.png")).getImage();
		btnHospital.setIcon(new ImageIcon(HospitalImg));		
		btnHospital.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHospital.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		buttonsPanel.add(btnHospital, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,100,10));
		buttonsPanel.add(btnStaff, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,100,10));
		buttonsPanel.add(btnPatient, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,100,10));
		
		// Listeners are defined
		btnStaff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Change2Staff();

			}
		});
		
		btnHospital.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Change2Hospital();

			}
		});
		
		btnPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Change2Patient();

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
		
		// All components are added to the frame
		add(buttonsPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
		menuTop.setSession(controller.getSession());
	
		pack();
		setLocationRelativeTo(null);
	}
}
