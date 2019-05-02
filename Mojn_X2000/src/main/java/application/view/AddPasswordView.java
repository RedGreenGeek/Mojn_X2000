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

import application.controller.AddPasswordController;
import application.controller.EditPatientController;
import application.controller.PatientController;
import application.controller.RegisterPatientController;
import application.controller.SearchPatientController;
import application.controller.SearchStaffController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class AddPasswordView extends JFrame {
	private static final long serialVersionUID = 985282041187452L;
	private JLabel NewPass1Label;
	private JLabel NewPass2Label;

	private JLabel IDLabel;

	private JTextField NewPass1Field;
	private JTextField NewPass2Field;

	private JTextField IDField;



	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Add password to a staff", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	private AddPasswordController controller;

	
	public AddPasswordView(AddPasswordController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Add password to staff");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		NewPass1Label = new JLabel("Wanted password: ");
		NewPass2Label = new JLabel("Repeat password: ");
		
		IDLabel = new JLabel("ID: ");

		
		NewPass1Field = new JTextField(11);
		NewPass2Field = new JTextField(11);
	
		IDField = new JTextField(11);

		

		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String NewPass1 = NewPass1Field.getText();
				String NewPass2 = NewPass2Field.getText();
		
				String ID = IDField.getText();

				NewPass1Field.setText("");
				NewPass2Field.setText("");
		
				IDField.setText("");

				msg = controller.AddPasswordAPI(NewPass1, NewPass2,ID);
				


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
		
		inputArea.add(NewPass1Label, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(NewPass1Field,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 2. linje
		y = y +1;
		
		inputArea.add(NewPass2Label, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(NewPass2Field, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		/////////////////////////// 2. linje
		y = y +1;
		
		inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(IDField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		/////////////////////////// 3. linje
		
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
