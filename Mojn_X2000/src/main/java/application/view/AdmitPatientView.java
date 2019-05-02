


package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.AdmitPatientController;
import application.utils.GridBagLayoutUtils;

public class AdmitPatientView extends JFrame {

	private static final long serialVersionUID = 989075342041187452L;
	private JLabel triLvlLabel;
	private JLabel departmentNameLabel;
	private JLabel IDLabel;
	private JTextField triLvlField;
	private JTextField departmentNameField;
	private JTextField idField;


	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Admit a patient to a department", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	private AdmitPatientController controller;

	
	public AdmitPatientView(AdmitPatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Admit Patients");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		triLvlLabel = new JLabel("Triage Level: ");
		departmentNameLabel = new JLabel("Department Name: ");
		IDLabel = new JLabel("ID: ");
		triLvlField = new JTextField(11);
		departmentNameField = new JTextField(11);

		idField = new JTextField(11);
		

		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String triLvl = triLvlField.getText();
				String departmentName = departmentNameField.getText();
				String id = idField.getText();
				triLvlField.setText("");
				departmentNameField.setText("");

				idField.setText("");
				msg = controller.AdmitAPI(triLvl, departmentName, id);
				


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
		
		inputArea.add(triLvlLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));

		
		inputArea.add(triLvlField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		y = y + 1;
		
		inputArea.add(departmentNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(departmentNameField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		
		/////////////////////////// 6. linje
		
		y = y + 1;
		
		inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(idField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		
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