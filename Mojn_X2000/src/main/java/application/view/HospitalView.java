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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import application.controller.StaffController;
import application.controller.HospitalController;
import application.controller.MainMenuController;
import application.controller.LoginController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class HospitalView extends JFrame {

	private static final long serialVersionUID = 12347L;
	private HospitalController controller;
	private Session session;
	private String path = "C:\\Users\\Mathi\\Desktop\\4. semester\\Agile Object-Oriented Software Development\\Icons\\";



	
	
	private JLabel lblUser;
	private JLabel lblHospital;
	
	
	public HospitalView(HospitalController controller) {
		this.controller = controller;
		initGUI();
	}
	
	

	private void initGUI() {
		this.session = Session.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hospital Structur Menu");
		setPreferredSize(new Dimension(800, 700));
		
		// Labels
		lblHospital = new JLabel("Hospital Structur Menu");
		lblHospital.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		// buttons
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		JButton btnGetDepart = new JButton("Get Depart");
		Image getDepartImg = new ImageIcon(this.getClass().getResource("/Hospital structur/getdepart.png")).getImage();
		btnGetDepart.setIcon(new ImageIcon(getDepartImg));
		btnGetDepart.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGetDepart.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnStaffInDepart = new JButton("Staff In Depart");
		Image staffInDepartImg = new ImageIcon(this.getClass().getResource("/Hospital structur/getstaff.png")).getImage();
		btnStaffInDepart.setIcon(new ImageIcon(staffInDepartImg));
		btnStaffInDepart.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnStaffInDepart.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnFreeBeds = new JButton("Avaliable Beds In Depart");
		Image freeBedsImg = new ImageIcon(this.getClass().getResource("/Hospital structur/free.png")).getImage();
		btnFreeBeds.setIcon(new ImageIcon(freeBedsImg));
		btnFreeBeds.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnFreeBeds.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnQueue = new JButton("Queue In Out Depart");
		Image queueImg = new ImageIcon(this.getClass().getResource("/Hospital structur/queue.png")).getImage();
		btnQueue.setIcon(new ImageIcon(queueImg));
		btnQueue.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnQueue.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		JButton btnBack = new JButton("Back");
		btnBack.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBack.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		buttonsPanel.add(btnQueue, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnFreeBeds, GridBagLayoutUtils.constraint(4, 1, 0, 0, 0,10,50,10));

		if(session.getRole() == "Clerk" || session.getRole() == "ICT-Officer" ) {
		buttonsPanel.add(btnStaffInDepart, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnGetDepart, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,50,10));}
		

		
		
		
		
//		 toolbar
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		
		JPanel userPanel = new JPanel();
		lblUser = new JLabel();
		userPanel.add(lblUser, BorderLayout.EAST);
		
		
		centerPanel.add(lblHospital,GridBagLayoutUtils.constraint(2, 0, 0, 0, 50, 0, 100, 0));
		centerPanel.add(buttonsPanel,GridBagLayoutUtils.constraint(2, 1, 0, 0, 0, 0, 0, 0) );
		centerPanel.add(btnBack,GridBagLayoutUtils.constraint(2, 3, 0, 0, 0, 0, 0, 0) );
		
		
		add(centerPanel, BorderLayout.CENTER);
		add(userPanel, BorderLayout.NORTH);
	
		pack();
		setLocationRelativeTo(null);
		
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
		btnQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToQueue();
			}
		});
		btnFreeBeds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToFreeBeds();
			}
		});
		btnStaffInDepart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToStaffInDepart();
			}
		});
		btnGetDepart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToGetDepart();
			}
		});
		
	}
	
	
	public void setSession(Session sessionModel) {
		lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
	
	}
}
	
	

	
