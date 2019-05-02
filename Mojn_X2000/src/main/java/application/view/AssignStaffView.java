package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.AssignStaffController;
import application.utils.GridBagLayoutUtils;

public class AssignStaffView extends JFrame{
	private static final long serialVersionUID = 989075282041120552L;
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
	private JPanel inputArea;
	private AssignStaffController controller;
	private MenuTopView menuTop = new MenuTopView("Assign Staff", "Back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	

	
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
		
		int y = 0;

		inputArea.add(firstNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(firstNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		///////////////////////////// 2. linje
		
		
		y = y+1;
		
		inputArea.add(surnameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(surnameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 3. linje
		
		y = y + 1;
		
		inputArea.add(staffIDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		
		inputArea.add(staffIDField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 4. linje
		
		y = y + 1;
		
		inputArea.add(departmentNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));

		
		inputArea.add(departmentNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		/////////////////////////// 5. linje
		
		y = y+1;
		
		inputArea.add(birthdayLabel, GridBagLayoutUtils.constraint(0, y, 0.1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(dayField, GridBagLayoutUtils.constraint(1, y, 0.1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		inputArea.add(monthField, GridBagLayoutUtils.constraint(1, y, 0.1, 0.1, 0, 38, 0, 0, GridBagConstraints.LINE_START));
		
		inputArea.add(yearField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 76, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 6. linje
		
		y = y + 1;
		
		inputArea.add(emailLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(emailField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		
		/////////////////////////// 7. linje
		
		y = y+1;
		
		inputArea.add(okBtn, GridBagLayoutUtils.constraint(1, y, 1, 1, 0, 0, 0, 0, GridBagConstraints.FIRST_LINE_START));
		inputArea.setBorder(new EmptyBorder(10,10,10,10));
		
		
		//////////////////////////// TEXT AREA //////////////////////////////7
		setLayout(new BorderLayout());
		
		add(inputArea, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
	
		pack();
		setLocationRelativeTo(null);
		
		menuTop.setSession(controller.getSession());
		
//		menuTop.backBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				controller.Back2Main();
//			}
//		});
		
	}
	

}
