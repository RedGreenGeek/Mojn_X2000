package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.SearchStaffController;
import application.utils.GridBagLayoutUtils;

public class SearchStaffView extends JFrame {
	private static final long serialVersionUID = 7869432568973L;
	private JLabel firstNameLabel;
	private JLabel surnameLabel;
	private JLabel emailLabel;
	private JLabel birthdayLabel;
	private JLabel IDLabel;
	
	private JTextField firstNameField;
	private JTextField surnameField;
	private JTextField emailField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField idField;

	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Searching for Staff Members");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	private SearchStaffController controller;

	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public SearchStaffView(SearchStaffController controller) {
		this.controller = controller;
		initGUI();
	}
	
	// All components of window are defined
	private void initGUI() {
		setTitle("Search Staff");
		setPreferredSize(new Dimension(900, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		firstNameLabel = new JLabel("First name: ");
		surnameLabel = new JLabel("Surname: ");
		emailLabel = new JLabel("Email: ");
		birthdayLabel = new JLabel("Birthday: ");
		IDLabel = new JLabel("ID: ");
		firstNameField = new JTextField(11);
		surnameField = new JTextField(11);
		emailField = new JTextField(11);
		dayField = new JTextField(3);
		monthField = new JTextField(3);
		yearField = new JTextField(4);
		idField = new JTextField(11);
	
		
		// Listeners to the buttons are defined
		menuTop.backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
		
		okBtn = new JButton("OK");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				textPanel.textArea.setText("");
				String firstName = firstNameField.getText();
				String surname = surnameField.getText();
				String email = emailField.getText();
				String day = dayField.getText();
				String month = monthField.getText();
				String year = yearField.getText();
				String id = idField.getText();
				firstNameField.setText("");
				surnameField.setText("");
				dayField.setText("");
				monthField.setText("");
				yearField.setText("");
				idField.setText("");
				emailField.setText("");
				msg = controller.SearchAPI(firstName, surname, email, day, month, year, id);
				textPanel.textArea.append(msg);
			}
		});
		layoutComponents();
	} 
	
	// All the components are organized by use of a grid layout and a border layout
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		///////////////////////////// 1. line
		int y = 0;

		inputArea.add(firstNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		inputArea.add(firstNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		///////////////////////////// 2. line
		y = y+1;
		
		inputArea.add(surnameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		inputArea.add(surnameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 3. line
		y = y+1;
		
		inputArea.add(emailLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		inputArea.add(emailField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 4. line
		y = y+1;
		
		inputArea.add(birthdayLabel, GridBagLayoutUtils.constraint(0, y, 0.1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		inputArea.add(dayField, GridBagLayoutUtils.constraint(1, y, 0.1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		inputArea.add(monthField, GridBagLayoutUtils.constraint(1, y, 0.1, 0.1, 0, 38, 0, 0, GridBagConstraints.LINE_START));
		inputArea.add(yearField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 76, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 5. line
		y = y + 1;
		
		inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		inputArea.add(idField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 6. line
		y = y+1;
		
		inputArea.add(okBtn, GridBagLayoutUtils.constraint(1, y, 1, 1, 0, 0, 0, 0, GridBagConstraints.FIRST_LINE_START));
		inputArea.setBorder(new EmptyBorder(10,10,10,10));
		
		// All components are added to the frame
		setLayout(new BorderLayout());
		
		add(inputArea, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
	
		pack();
		setLocationRelativeTo(null);
		
	}
	 public MenuTopView getMenuTop() {
		 return this.menuTop;
	 }
}
