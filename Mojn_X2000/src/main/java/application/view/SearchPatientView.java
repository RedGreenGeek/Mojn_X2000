


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
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		
		/////////////////////////// 4. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
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
		
		inputArea.add(IDLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(idField, gc);
		 
		/////////////////////////////// 8. linje
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(departLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(departField, gc);
		
		
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