package application.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.AddDepartController;
import application.utils.GridBagLayoutUtils;

public class AddDepartView extends JFrame {
	private static final long serialVersionUID = 98526482041187452L;
	private JLabel DepartTypeLabel;
	private JLabel NameDepartLabel;
	private JLabel maxBedLabel;

	private JTextField DepartNameField;
	private JTextField maxBedField;
	
	private JRadioButton Indepart;
	private JRadioButton Outdepart;
	private JRadioButton Admindepart;
	private ButtonGroup bg;

	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Add Department", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	private AddDepartController controller;
	
	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public AddDepartView(AddDepartController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		// All components of the add are defined
		setTitle("Add Department");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		NameDepartLabel = new JLabel("Name of the department: ");
		DepartTypeLabel = new JLabel("Deartment type: ");
		maxBedLabel = new JLabel("Maximum number of beds: ");
		
		maxBedField = new JTextField(11);
		DepartNameField = new JTextField(11);
		Indepart = new JRadioButton("Indepart");
		Outdepart = new JRadioButton("Outdepart");
		Admindepart = new JRadioButton("Admindepart");
		bg = new ButtonGroup();
		bg.add(Indepart);
		bg.add(Outdepart);
		bg.add(Admindepart);
		
		menuTop.setSession(controller.getSession());
		
		// Listeners to the buttons are defined
		Indepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				maxBedField.setEditable(true);
			}
			
		});
		Outdepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				maxBedField.setText("");
				maxBedField.setEditable(false);
			}
			
		});
		Admindepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				maxBedField.setText("");
				maxBedField.setEditable(false);
			}
			
		});
		
		okBtn = new JButton("OK");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String DepartName = DepartNameField.getText();
				String MaxBeds;
				String departType;
				
				if (!maxBedField.getText().equals("")) {
					 MaxBeds = maxBedField.getText();
				} else { MaxBeds = "";}
				
				if (Indepart.isSelected()) {
					 departType = "inpatient";
				}
				if (Outdepart.isSelected()) {
					 departType = "outpatient";
				} else { departType = "admin";}
				
				DepartNameField.setText("");
				maxBedField.setText("");
				
				msg = controller.AddDepartAPI(departType, DepartName,MaxBeds);
				textPanel.textArea.append(msg);
				textPanel.textArea.append("\n");
			}
			
		});
		
		menuTop.backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
		layoutComponents();
	} 
	// All the components are organized by use of a grid layout and a border layout
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		/////////////////////////////////// 1. line
		int y = 0;
		
		inputArea.add(NameDepartLabel , GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(DepartNameField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 2. line
		y = y +1;
		
		inputArea.add(maxBedLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(maxBedField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		/////////////////////////// 3. line
		y = y +1;
		
		inputArea.add(DepartTypeLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		JPanel buttonGroup = new JPanel();
		buttonGroup.add(Indepart, GridBagLayoutUtils.constraint(0, 0, 0.5, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		buttonGroup.add(Outdepart, GridBagLayoutUtils.constraint(1, 0, 0.5, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		buttonGroup.add(Admindepart, GridBagLayoutUtils.constraint(2, 0, 0.5, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		inputArea.add(buttonGroup, GridBagLayoutUtils.constraint(1, y, 0.5, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
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

}