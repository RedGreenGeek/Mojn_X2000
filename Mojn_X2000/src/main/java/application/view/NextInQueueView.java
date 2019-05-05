package application.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.NextInQueueController;
import application.utils.GridBagLayoutUtils;

public class NextInQueueView extends JFrame {
	private static final long serialVersionUID = 9852645437452L;
	private JLabel DepartNameLabel;

	private JTextField DepartNameField;
	
	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Next in queue");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	private NextInQueueController controller;
	
	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public NextInQueueView(NextInQueueController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		// All components of the add are defined
		setTitle("Next in queue");
		setPreferredSize(new Dimension(900, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		DepartNameLabel = new JLabel("Name of the department: ");
		
		DepartNameField = new JTextField(11);

		
		
		// Listeners to the buttons are defined
		
		okBtn = new JButton("OK");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String DepartName = DepartNameField.getText();
				msg = controller.nextInQueueAPI(DepartName);
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
		
		inputArea.add(DepartNameLabel , GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		inputArea.add(DepartNameField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
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