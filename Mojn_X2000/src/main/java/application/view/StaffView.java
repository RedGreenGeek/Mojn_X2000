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

import application.controller.StaffController;
import application.model.Session;
import application.model.Session.JobTypes;
import application.utils.GridBagLayoutUtils;

public class StaffView extends JFrame {

	private static final long serialVersionUID = 100L;
	private StaffController controller;

	private Session session;
	private MenuTopView menuTop = new MenuTopView("Staff Menu");

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
		Image SearchImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Staff menu/search.png")).getImage();
		btnSearch.setIcon(new ImageIcon(SearchImg));	
		btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnRegister = new JButton("Register Staff");
		Image registerImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Staff menu/register.png")).getImage();
		btnRegister.setIcon(new ImageIcon(registerImg));
		btnRegister.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRegister.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnAssign = new JButton("Assign to Depart");
		Image assignImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Staff menu/assigndepart.png")).getImage();
		btnAssign.setIcon(new ImageIcon(assignImg));
		btnAssign.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAssign.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnEdit = new JButton("Edit Staff");
		Image editImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Staff menu/edit.png")).getImage();
		btnEdit.setIcon(new ImageIcon(editImg));
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		JButton btnBack = new JButton("Back");
		btnBack.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBack.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		if(session.getRole() == JobTypes.ICTOfficer ) {
		buttonsPanel.add(btnEdit, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,50,10));}
		
		if(session.getRole() == JobTypes.Clerk || session.getRole() == JobTypes.ICTOfficer ) {
		buttonsPanel.add(btnRegister, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnAssign, GridBagLayoutUtils.constraint(4, 1, 0, 0, 0,10,50,10));
	}
		
		buttonsPanel.add(btnSearch, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,50,10));
		

		
		
		
		
//		 toolbar
		add(buttonsPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
		menuTop.setSession(controller.getSession());
		add(btnBack, BorderLayout.SOUTH);
	
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
		
	}
	
	
//	public void setSession(Session sessionModel) {
//		lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
//	
//	}
}
	
	

	
