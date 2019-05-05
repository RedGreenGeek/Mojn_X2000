package application.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.controller.GetDepartmentController;
import application.utils.GridBagLayoutUtils;

public class GetDepartmentView extends JFrame {
	private static final long serialVersionUID = 43556572341L;
	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Get all departments");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	private GetDepartmentController controller;

	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public GetDepartmentView(GetDepartmentController controller) {
		this.controller = controller;
		initGUI();
	}
	// All components of the window are defined
	private void initGUI() {
		setTitle("Get Departments");
		setPreferredSize(new Dimension(900, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());

		
		// Listeners to the buttons are defined
		menuTop.backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
		
		okBtn = new JButton("Get Departments");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				msg = controller.GetDepartmentAPI();
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