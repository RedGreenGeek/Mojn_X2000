


package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.SearchPatientController;
import application.utils.GridBagLayoutUtils;

@SuppressWarnings("serial")
public class SearchPatientView extends JFrame {
	private JLabel firstNameLabel;
	private JLabel surnameLabel;
	private JLabel birthdayLabel;
	private JLabel IDLabel;
	private JLabel departLabel;
	private JTextField firstNameField;
	private JTextField surnameField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField idField;
	private JTextField departField;


	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Search after a patient", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	private SearchPatientController controller;

	
	public SearchPatientView(SearchPatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Search Patients");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		firstNameLabel = new JLabel("First name: ");
		surnameLabel = new JLabel("Surname: ");
		birthdayLabel = new JLabel("Birthday: ");
		IDLabel = new JLabel("ID: ");
		departLabel= new JLabel("Department: ");
		firstNameField = new JTextField(11);
		surnameField = new JTextField(11);
		dayField = new JTextField(3);
		monthField = new JTextField(3);
		yearField = new JTextField(4);
		idField = new JTextField(11);
		departField = new JTextField(11);
		

		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				textPanel.textArea.setText("");
				String firstName = firstNameField.getText();
				String surname = surnameField.getText();
				String day = dayField.getText();
				String month = monthField.getText();
				String year = yearField.getText();
				String id = idField.getText();
				String depart = departField.getText();
				firstNameField.setText("");
				surnameField.setText("");
				dayField.setText("");
				monthField.setText("");
				yearField.setText("");
				idField.setText("");
				departField.setText("");
				msg = controller.SearchAPI(firstName, surname, day, month, year, id, depart);
				


				textPanel.textArea.append(msg);
				

				

;			}
			
		});
		

		
		layoutComponents();

		
	} // her ender constructoren
	
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		int y = 0;

		inputArea.add(firstNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(firstNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		///////////////////////////// 2. linje
		
		
		y = y+1;
		
		inputArea.add(surnameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(surnameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		/////////////////////////// 3. linje
		
		
		/////////////////////////// 4. linje
		
		
		y = y+1;
		
		inputArea.add(birthdayLabel, GridBagLayoutUtils.constraint(0, y, 0.1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(dayField, GridBagLayoutUtils.constraint(1, y, 0.1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		inputArea.add(monthField, GridBagLayoutUtils.constraint(1, y, 0.1, 0.1, 0, 38, 0, 0, GridBagConstraints.LINE_START));
		
		inputArea.add(yearField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 76, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 6. linje
		
		y = y + 1;
		
		inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(idField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
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
		
		menuTop.backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
	}

}