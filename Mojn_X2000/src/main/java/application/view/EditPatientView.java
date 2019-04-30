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

import application.controller.EditPatientController;
import application.controller.PatientController;
import application.controller.RegisterPatientController;
import application.controller.SearchPatientController;
import application.controller.SearchStaffController;
import application.model.Session;

public class EditPatientView extends JFrame {
	private JLabel firstNameLabel;
	private JLabel surnameLabel;
	private JLabel adressLabel;
	private JLabel tribeLabel;
	private JLabel birthdayLabel;
	private JLabel aliveLabel;
	private JLabel IDLabel;

	private JTextField firstNameField;
	private JTextField surnameField;
	private JTextField adressField;
	private JTextField tribeField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField aliveField;
	private JTextField IDField;

	private JLabel ageLabel ;


	private JButton okBtn;
	private JButton backBtn;
	private JTextArea textArea;
	private JPanel inputArea;
	private JPanel textPanel;
	private JPanel menuTop;
	private String msg;
	
	private JLabel lblUser;
	
	private EditPatientController controller;

	
	public EditPatientView(EditPatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Edit Patients");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		firstNameLabel = new JLabel("First name: ");
		surnameLabel = new JLabel("Surname: ");
		adressLabel = new JLabel("Adress: ");
		tribeLabel = new JLabel("Tribe: ");
		birthdayLabel = new JLabel("Birthday: ");
		ageLabel = new JLabel("Job type: ");
		aliveLabel = new JLabel("alive: ");
		IDLabel = new JLabel("ID: ");

		
		firstNameField = new JTextField(11);
		surnameField = new JTextField(11);
		adressField = new JTextField(11);
		tribeField = new JTextField(11);
		dayField = new JTextField(3);
		monthField = new JTextField(3);
		yearField = new JTextField(4);
		aliveField = new JTextField(11);
		IDField = new JTextField(11);

		

		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String firstName = firstNameField.getText();
				String surname = surnameField.getText();
				String adress = adressField.getText();
				String tribe = tribeField.getText();
				String day = dayField.getText();
				String month = monthField.getText();
				String year = yearField.getText();
				String alive = aliveField.getText();
				String ID = IDField.getText();

				firstNameField.setText("");
				surnameField.setText("");
				dayField.setText("");
				monthField.setText("");
				yearField.setText("");
				aliveField.setText("");
				IDField.setText("");

				tribeField.setText("");
				adressField.setText("");
				msg = controller.EditAPI(ID,firstName, surname, adress, day, month, year, tribe,alive);
				


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
		
		inputArea.add(adressLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(adressField, gc);
		
		/////////////////////////// 4. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(tribeLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(tribeField, gc);
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
		
		inputArea.add(aliveLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(aliveField, gc);
		
		
		/////////////////////////// 7. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(IDLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(IDField, gc);
		

		
		
		/////////////////////////////8. linje:
		
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
		
		JLabel lblTitle = new JLabel("Edit");
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
