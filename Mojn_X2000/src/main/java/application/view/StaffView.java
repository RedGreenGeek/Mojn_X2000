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
import application.controller.MainMenuController;
import application.controller.LoginController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class StaffView extends JFrame {

	private static final long serialVersionUID = 100L;
	private StaffController controller;

	private Session session;

	private String path;
	private JLabel lblUser;
	private JLabel lblStaff;
	
	
	public StaffView(StaffController controller) {
		this.controller = controller;
		initGUI();
	}
	
	

	private void initGUI() {
		this.session = Session.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Staff Menu");
		setPreferredSize(new Dimension(800, 700));
		
		// Labels
		lblStaff = new JLabel("Staff Menu");
		lblStaff.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		// buttons
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		JButton btnSearch = new JButton("Search Staff");
		Image SearchImg = new ImageIcon(this.getClass().getResource("/Staff menu/search.png")).getImage();
		btnSearch.setIcon(new ImageIcon(SearchImg));	
		btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnRegister = new JButton("Register Staff");
		Image registerImg = new ImageIcon(this.getClass().getResource("/Staff menu/register.png")).getImage();
		btnRegister.setIcon(new ImageIcon(registerImg));
		btnRegister.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRegister.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnAssign = new JButton("Assign to Depart");
		Image assignImg = new ImageIcon(this.getClass().getResource("/Staff menu/assigndepart.png")).getImage();
		btnAssign.setIcon(new ImageIcon(assignImg));
		btnAssign.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAssign.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnEdit = new JButton("Edit Staff");
		Image editImg = new ImageIcon(this.getClass().getResource("/Staff menu/edit.png")).getImage();
		btnEdit.setIcon(new ImageIcon(editImg));
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		JButton btnBack = new JButton("Back");
		btnBack.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBack.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		if(session.getRole() == "ICT-Officer" ) {
		buttonsPanel.add(btnEdit, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,50,10));}
		
		if(session.getRole() == "Clerk" || session.getRole() == "ICT-Officer" ) {
		buttonsPanel.add(btnRegister, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnAssign, GridBagLayoutUtils.constraint(4, 1, 0, 0, 0,10,50,10));
	}
		
		buttonsPanel.add(btnSearch, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,50,10));
		

		
		
		
		
//		 toolbar
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		
		JPanel userPanel = new JPanel();
		lblUser = new JLabel();
		userPanel.add(lblUser, BorderLayout.EAST);
		
		
		centerPanel.add(lblStaff,GridBagLayoutUtils.constraint(2, 0, 0, 0, 50, 0, 100, 0));
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
		
		btnAssign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToAssign();
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToRegister();
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToEdit();
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToSearch();
			}
		});
		
	}
	
	
	public void setSession(Session sessionModel) {
		lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
	
	}
}
	
	

	
