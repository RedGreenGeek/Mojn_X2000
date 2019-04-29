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
import application.controller.InventoryController;
import application.controller.LoginController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class StaffView extends JFrame {

	private static final long serialVersionUID = 100L;
	private StaffController controller;

	private Session session;

	
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
		String path = "C:\\Users\\Mathi\\Desktop\\4. semester\\Agile Object-Oriented Software Development\\Icons\\";
		
		// Labels
		lblStaff = new JLabel("Staff Menu");
		lblStaff.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		// buttons
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		JButton btnSearch = new JButton("Search Staff");
		btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setIcon(new ImageIcon(path + "Staff menu\\search.png"));
		
		JButton btnRegister = new JButton("Register Staff");
		btnRegister.setIcon(new ImageIcon(path + "Staff menu\\register.png"));
		btnRegister.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRegister.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnAssign = new JButton("Assign to Depart");
		btnAssign.setIcon(new ImageIcon(path + "Staff menu\\assigndepart.png"));
		btnAssign.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAssign.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnEdit = new JButton("Edit Staff");
		btnEdit.setIcon(new ImageIcon(path + "Staff menu\\edit.png"));
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
	
	

	
