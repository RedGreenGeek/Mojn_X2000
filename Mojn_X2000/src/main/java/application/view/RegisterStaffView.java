package application.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.RegisterStaffController;
import application.utils.GridBagLayoutUtils;

public class RegisterStaffView extends JFrame {
	private static final long serialVersionUID = 86464139876543L;
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
		
		int y = 0;

		inputArea.add(firstNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(firstNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		///////////////////////////// 2. linje
		
		
		y = y+1;
		
		inputArea.add(surnameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(surnameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 3. linje
		
		y = y + 1;
		
		inputArea.add(adressLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		
		inputArea.add(adressField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 4. linje
		
		y = y + 1;
		
		inputArea.add(tribeLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));

		
		inputArea.add(tribeField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		/////////////////////////// 5. linje
		
		y = y+1;
		
		inputArea.add(birthdayLabel, GridBagLayoutUtils.constraint(0, y, 0.1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(dayField, GridBagLayoutUtils.constraint(1, y, 0.1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		inputArea.add(monthField, GridBagLayoutUtils.constraint(1, y, 0.1, 0.1, 0, 38, 0, 0, GridBagConstraints.LINE_START));
		
		inputArea.add(yearField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 76, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 6. linje
		
		y = y + 1;
		
		inputArea.add(jobLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(jobField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
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
