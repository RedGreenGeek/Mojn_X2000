package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import application.controller.AssignStaffController;
import application.controller.PatientController;
import application.controller.RegisterPatientController;
import application.controller.RegisterStaffController;
import application.controller.SearchPatientController;
import application.controller.SearchStaffController;
import application.model.Session;

public class AssignStaffView extends JFrame {
	private JLabel firstNameLabel;
	private JLabel surnameLabel;
	private JLabel staffIDLabel;
	private JLabel departmentNameLabel;
	private JLabel birthdayLabel;
	private JLabel emailLabel;
	private JTextField firstNameField;
	private JTextField surnameField;
	private JTextField staffIDField;
	private JTextField departmentNameField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField emailField;


	private JButton okBtn;
	private JButton backBtn;
	private JTextArea textArea;
	private JPanel inputArea;
	private JPanel textPanel;
	private JPanel menuTop;
	private String msg;
	
	private JLabel lblUser;
	
	private AssignStaffController controller;

	
	public AssignStaffView(AssignStaffController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Assign staff to department");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		firstNameLabel = new JLabel("First name: ");
		surnameLabel = new JLabel("Surname: ");
		staffIDLabel = new JLabel("Staff ID: ");
		departmentNameLabel = new JLabel("Department Name: ");
		birthdayLabel = new JLabel("Birthday: ");
		emailLabel = new JLabel("email: ");
		firstNameField = new JTextField(11);
		surnameField = new JTextField(11);
		staffIDField = new JTextField(11);
		departmentNameField = new JTextField(11);
		dayField = new JTextField(3);
		monthField = new JTextField(3);
		yearField = new JTextField(4);
		emailField = new JTextField(11);
		

		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String firstName = firstNameField.getText();
				String surname = surnameField.getText();
				String staffID = staffIDField.getText();
				String departmentName = departmentNameField.getText();
				String day = dayField.getText();
				String month = monthField.getText();
				String year = yearField.getText();
				String email = emailField.getText();
				firstNameField.setText("");
				surnameField.setText("");
				dayField.setText("");
				monthField.setText("");
				yearField.setText("");
				emailField.setText("");
				departmentNameField.setText("");
				staffIDField.setText("");
				msg = controller.AssignStaffAPI(firstName , surname, departmentName, day, month, year, staffID, email);
				


				textArea.append(msg);
				textArea.append("\n");

				

;			}
			
		});
		

		
		layoutComponents();

		
	} // her ender constructoren
	
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		/////////////////////////////////// 1. linje
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(firstNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(firstNameField, gc);
		
		///////////////////////////// 2. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		
		inputArea.add(surnameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(surnameField, gc);
		/////////////////////////// 3. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(staffIDLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(staffIDField, gc);
		
		/////////////////////////// 4. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(departmentNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(departmentNameField, gc);
		/////////////////////////// 5. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(birthdayLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(dayField, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,38,0,0);
		
		inputArea.add(monthField, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,76,0,0);
		
		inputArea.add(yearField, gc);
		
		/////////////////////////// 6. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(emailLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(emailField, gc);
		
		
		/////////////////////////// 7. linje
		
		gc.gridx = 1;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(okBtn, gc);
		inputArea.setBorder(new EmptyBorder(10,10,10,10));
		
		
		//////////////////////////// TEXT AREA //////////////////////////////7
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textArea = new JTextArea();	
		textArea.setEditable(false);
		
		textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	
		
		////////////////////// MENU TOP /////////////////////////7
		menuTop = new JPanel();
		menuTop.setLayout(new BorderLayout(0, 0));
		
		backBtn = new JButton("Back");
		backBtn.setHorizontalAlignment(SwingConstants.LEFT);
		menuTop.add(backBtn, BorderLayout.WEST);
		
		lblUser = new JLabel("ID## - User");
		lblUser.setBorder(new EmptyBorder(0, 0, 0, 10));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		menuTop.add(lblUser, BorderLayout.EAST);
		
		menuTop.setBorder(new EtchedBorder(10));
		
		JLabel lblTitle = new JLabel("Assign staff to department");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		menuTop.add(lblTitle, BorderLayout.CENTER);
		
		
		
		
		
		
		setLayout(new BorderLayout());
		
		add(inputArea, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
	
		pack();
		setLocationRelativeTo(null);
		
		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
	}
	
	
	
	
	
	
	
	public void setSession(Session sessionModel) {
		lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
	
	}

}
