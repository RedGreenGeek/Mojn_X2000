package application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import application.controller.InventoryController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class InventoryView extends JFrame {

	private static final long serialVersionUID = 989075282041187452L;
	private InventoryController controller;
	private JTable tblInventory;
	private JLabel lblUser;
	private JLabel lblMainMenu;
	
	public InventoryView(InventoryController controller) {
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
		btnPatient.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPatient.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPatient.setIcon(new ImageIcon("C:\\Users\\Mathi\\Desktop\\4. semester\\Agile Object-Oriented Software Development\\Icons\\Patient.png"));
		
		JButton btnStaff = new JButton("Staff");
		btnStaff.setIcon(new ImageIcon("C:\\Users\\Mathi\\Desktop\\4. semester\\Agile Object-Oriented Software Development\\Icons\\Staff.png"));
		btnStaff.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnStaff.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnHospital = new JButton("Hospital");
		btnHospital.setIcon(new ImageIcon("C:\\Users\\Mathi\\Desktop\\4. semester\\Agile Object-Oriented Software Development\\Icons\\hospital.png"));
		btnHospital.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHospital.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		buttonsPanel.add(btnHospital, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,100,10));
		buttonsPanel.add(btnStaff, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,100,10));
		buttonsPanel.add(btnPatient, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,100,10));
		
//		 toolbar
//		JPanel centerPanel = new JPanel();
//		centerPanel.setLayout(new GridBagLayout());
//		
//		JPanel userPanel = new JPanel();
//		lblUser = new JLabel();
//		userPanel.add(lblUser, BorderLayout.EAST);
//		
//		
//		centerPanel.add(lblMainMenu,GridBagLayoutUtils.constraint(2, 0, 0, 0, 50, 0, 100, 0));
//		centerPanel.add(buttonsPanel,GridBagLayoutUtils.constraint(2, 1, 0, 0, 0, 0, 0, 0) );
//		
//		add(centerPanel, BorderLayout.CENTER);
//		add(userPanel, BorderLayout.NORTH);
//	
		
		JMenuBar menuTop;
		menuTop = new JMenuBar();
		menuTop.setLayout(new BorderLayout(0, 0));
		
		JMenu mnPassword = new JMenu();
		mnPassword.setIcon(new ImageIcon("C:\\Users\\anton\\OneDrive\\Dokumenter\\Uni\\Uni\\4. semester\\Agil\\Exam proj\\icons\\feature.png"));
		menuTop.add(mnPassword, BorderLayout.WEST);
		
		JButton btnChange = new JButton("Change Password");
		mnPassword.add(btnChange);

		JButton btnAdd = new JButton("Add Password");
		mnPassword.add(btnAdd);				
		
		lblUser = new JLabel("ID## - User");
		lblUser.setBorder(new EmptyBorder(0, 0, 0, 10));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		menuTop.add(lblUser, BorderLayout.EAST);
		
		menuTop.setBorder(new EtchedBorder(10));
		
		JLabel lblTitle = new JLabel("Patient");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		menuTop.add(lblTitle, BorderLayout.CENTER);
		
		add(buttonsPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
		
		
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
