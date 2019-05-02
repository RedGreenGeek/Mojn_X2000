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

import application.controller.HospitalController;
import application.model.Session;
import application.model.Session.JobTypes;
import application.utils.GridBagLayoutUtils;

public class HospitalView extends JFrame {
	private static final long serialVersionUID = 12347L;
	private HospitalController controller;
	private Session session;
	private MenuTopView menuTop = new MenuTopView("Hospital Menu");
	
	private JLabel lblHospital;

	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public HospitalView(HospitalController controller) {
		this.controller = controller;
		initGUI();
	}
	// All components of the window are defined
	private void initGUI() {
		this.session = Session.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hospital Structur Menu");
		setPreferredSize(new Dimension(800, 700));
		
		lblHospital = new JLabel("Hospital Structur Menu");
		lblHospital.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		// A panel for the buttons are defined
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		// All the buttons are defined and a icon added
		JButton btnGetDepart = new JButton("Get Depart");
		Image getDepartImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Hospital structur/getdepart.png")).getImage();
		btnGetDepart.setIcon(new ImageIcon(getDepartImg));
		btnGetDepart.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGetDepart.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnStaffInDepart = new JButton("Staff In Depart");
		Image staffInDepartImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Hospital structur/getstaff.png")).getImage();
		btnStaffInDepart.setIcon(new ImageIcon(staffInDepartImg));
		btnStaffInDepart.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnStaffInDepart.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnFreeBeds = new JButton("Avaliable Beds In Depart");
		Image freeBedsImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Hospital structur/free.png")).getImage();
		btnFreeBeds.setIcon(new ImageIcon(freeBedsImg));
		btnFreeBeds.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnFreeBeds.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnQueue = new JButton("Queue In Out Depart");
		Image queueImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/Hospital structur/queue.png")).getImage();
		btnQueue.setIcon(new ImageIcon(queueImg));
		btnQueue.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnQueue.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		JButton btnParticipationList = new JButton("Get participation list");
		
		JButton btnBack = new JButton("Back");
		btnBack.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBack.setHorizontalTextPosition(SwingConstants.CENTER);

		
		buttonsPanel.add(btnQueue, GridBagLayoutUtils.constraint(1, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnFreeBeds, GridBagLayoutUtils.constraint(4, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnParticipationList, GridBagLayoutUtils.constraint(1, 2, 0, 0, 0,10,50,10));

		if(session.getRole()==JobTypes.Clerk || session.getRole() == JobTypes.ICTOfficer ) {
		buttonsPanel.add(btnStaffInDepart, GridBagLayoutUtils.constraint(2, 1, 0, 0, 0,10,50,10));
		buttonsPanel.add(btnGetDepart, GridBagLayoutUtils.constraint(3, 1, 0, 0, 0,10,50,10));}
		
		
		// Listeners are defined
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
		btnParticipationList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ToParticipationList();
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
		add(btnBack, BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);

	}
	
}
