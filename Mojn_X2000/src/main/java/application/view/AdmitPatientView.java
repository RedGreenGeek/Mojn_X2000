package application.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import application.controller.AdmitPatientController;
import application.utils.GridBagLayoutUtils;

public class AdmitPatientView extends JFrame {
	private static final long serialVersionUID = 534204231187452L;
	private JLabel triLvlLabel;
	private JLabel departmentNameLabel;
	private JLabel IDLabel;
	private JTextField triLvlField;
	private JTextField departmentNameField;
	private JTextField idField;

	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Admit Patient to Department");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	private AdmitPatientController controller;
	
	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public AdmitPatientView(AdmitPatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		// All components of the window are defined
		setTitle("Admit Patients");
		setPreferredSize(new Dimension(900, 700));
		setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("icons/heisenbug.png")).getImage());
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		triLvlLabel = new JLabel("Triage Level: ");
		departmentNameLabel = new JLabel("Department Name: ");
		IDLabel = new JLabel("ID: ");
		
		triLvlField = new JTextField(11);
		departmentNameField = new JTextField(11);
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
				String triLvl = triLvlField.getText();
				String departmentName = departmentNameField.getText();
				String id = idField.getText();
				triLvlField.setText("");
				departmentNameField.setText("");
				idField.setText("");
				msg = controller.AdmitAPI(triLvl, departmentName, id);
				textPanel.textArea.append(msg);
				textPanel.textArea.append("\n");
			}
			
		});

		layoutComponents();

		
	} 
	
	// All the components are organized by use of a grid layout and a border layout
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		/////////////////////////////////// 1. line
		int y = 0;
		
		inputArea.add(triLvlLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));

		inputArea.add(triLvlField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////////////// 2. line
		y = y + 1;
		
		inputArea.add(departmentNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(departmentNameField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 3. line
		y = y + 1;
		
		inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(idField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 4. line
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