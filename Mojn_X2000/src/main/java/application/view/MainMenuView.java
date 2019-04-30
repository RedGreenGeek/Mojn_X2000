package application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import application.controller.MainMenuController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class MainMenuView extends JFrame {

	private static final long serialVersionUID = 989075282041187452L;
	private MainMenuController controller;
	private JTable tblInventory;
	private JLabel lblUser;
	private JLabel lblMainMenu;
	
	public MainMenuView(MainMenuController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Menu");
		setPreferredSize(new Dimension(800, 700));
		
		// Labels
		lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		// buttons
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		JButton btnPatient = new JButton("Patient");
		Image patientImg = new ImageIcon(this.getClass().getResource("/Patient.png")).getImage();
		btnPatient.setIcon(new ImageIcon(patientImg));
		btnPatient.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPatient.setHorizontalTextPosition(SwingConstants.CENTER);	
		
		JButton btnStaff = new JButton("Staff");
		Image StaffImg = new ImageIcon(this.getClass().getResource("/Staff.png")).getImage();
		btnStaff.setIcon(new ImageIcon(StaffImg));		
		btnStaff.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnStaff.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnHospital = new JButton("Hospital");
		Image HospitalImg = new ImageIcon(this.getClass().getResource("/hospital.png")).getImage();
		btnHospital.setIcon(new ImageIcon(HospitalImg));		
		btnHospital.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHospital.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		buttonsPanel.add(btnHospital, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,100,10));
		buttonsPanel.add(btnStaff, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,100,10));
		buttonsPanel.add(btnPatient, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,100,10));
		
//		 toolbar
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		
		JPanel userPanel = new JPanel();
		lblUser = new JLabel();
		userPanel.add(lblUser, BorderLayout.EAST);
		
		
		centerPanel.add(lblMainMenu,GridBagLayoutUtils.constraint(2, 0, 0, 0, 50, 0, 100, 0));
		centerPanel.add(buttonsPanel,GridBagLayoutUtils.constraint(2, 1, 0, 0, 0, 0, 0, 0) );
		
		add(centerPanel, BorderLayout.CENTER);
		add(userPanel, BorderLayout.NORTH);
	
		pack();
		setLocationRelativeTo(null);
	
		
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
		
	}
//	
//	public void setTableModel(TableModel model) {
//		tblInventory.setModel(model);
//	}

	public void setSession(Session sessionModel) {
		lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
	
	}
}
