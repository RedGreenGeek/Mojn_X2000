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

import application.controller.StaffDepartController;
import application.utils.GridBagLayoutUtils;

public class StaffDepartView extends JFrame {
	private static final long serialVersionUID = 34696435671L;

	private JLabel departmentNameLabel;

	private JTextField departmentNameField;

	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Getting Staff from a Department");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	private StaffDepartController controller;

	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public StaffDepartView(StaffDepartController controller) {
		this.controller = controller;
		initGUI();
	}
	
	// All components of window are defined
	private void initGUI() {
		setTitle("Get staff in department");
		setPreferredSize(new Dimension(900, 700));
		setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("icons/heisenbug.png")).getImage());
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		departmentNameLabel = new JLabel("Department Name: ");
		departmentNameField = new JTextField(11);
		

		
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
				String departmentName = departmentNameField.getText();
				departmentNameField.setText("");
				msg = controller.GetDepartStaffAPI(departmentName);
				textPanel.textArea.append(msg);
				textPanel.textArea.append("\n");
			}
		});
		layoutComponents();
	} 
	
	// All the components are organized by use of a grid layout and a border layout
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		/////////////////////////// 1. line
		int y = 0;
		
		inputArea.add(departmentNameLabel,  GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		inputArea.add(departmentNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 2. line
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