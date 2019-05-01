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

import application.controller.PatientController;
import application.controller.RegisterPatientController;
import application.controller.RegisterStaffController;
import application.controller.SearchPatientController;
import application.controller.SearchStaffController;
import application.model.Session;

public class RegisterStaffView extends JFrame {
	private JLabel firstNameLabel;
	private JLabel surnameLabel;
	private JLabel adressLabel;
	private JLabel tribeLabel;
	private JLabel birthdayLabel;
	private JLabel jobLabel;
	private JTextField firstNameField;
	private JTextField surnameField;
	private JTextField adressField;
	private JTextField tribeField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField jobField;


	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Register a staff", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	private RegisterStaffController controller;

	
	public RegisterStaffView(RegisterStaffController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Register Staff");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		firstNameLabel = new JLabel("First name: ");
		surnameLabel = new JLabel("Surname: ");
		adressLabel = new JLabel("Adress: ");
		tribeLabel = new JLabel("Tribe: ");
		birthdayLabel = new JLabel("Birthday: ");
		jobLabel = new JLabel("job: ");
		firstNameField = new JTextField(11);
		surnameField = new JTextField(11);
		adressField = new JTextField(11);
		tribeField = new JTextField(11);
		dayField = new JTextField(3);
		monthField = new JTextField(3);
		yearField = new JTextField(4);
		jobField = new JTextField(11);
		

		
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
				String job = jobField.getText();
				firstNameField.setText("");
				surnameField.setText("");
				dayField.setText("");
				monthField.setText("");
				yearField.setText("");
				jobField.setText("");
				tribeField.setText("");
				adressField.setText("");
				msg = controller.RegisterAPI(job,firstName, surname, adress, day, month, year, tribe);
				


				textPanel.textArea.append(msg);
				textPanel.textArea.append("\n");

				

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
		
		inputArea.add(jobLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(jobField, gc);
		
		
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
		setLayout(new BorderLayout());
		
		add(inputArea, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
	
		pack();
		setLocationRelativeTo(null);
		
		menuTop.setSession(controller.getSession());
		
		menuTop.backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
	}


}
