package application.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import application.controller.DepartmentMovePatientController;
import application.utils.GridBagLayoutUtils;


public class DepartmentMovePatientView extends JFrame {
	private static final long serialVersionUID = 9890751187452L;
	private JLabel IDLabel;
	private JLabel departNameLabel;
	private JLabel triLabel;

	private JTextField idField;
	private JTextField departNameField;
	private JTextField triField;

	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Move patients between departments");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	private DepartmentMovePatientController controller;
	
	// The constructor for the class is defined with the method initGUI that sets up the view of the class
	public DepartmentMovePatientView(DepartmentMovePatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		// All components of the window are defined
		setTitle("Move patients between departments");
		setPreferredSize(new Dimension(900, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());

		IDLabel = new JLabel("ID: ");
		triLabel = new JLabel("Triage Level: ");
		departNameLabel = new JLabel("New department name: ");

		idField = new JTextField(11);
		departNameField = new JTextField(11);
		triField = new JTextField(11);

		
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
				String id = idField.getText();
				String departName = departNameField.getText();
				String trilvl = triField.getText();
				idField.setText("");
				departNameField.setText("");
				triField.setText("");
				msg = controller.MoveDepartmentAPI(id,departName, trilvl);
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
		
		inputArea.add(triLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		inputArea.add(triField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 2. line
		y = y +1;
		
		inputArea.add(departNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		inputArea.add(departNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 3. line
		y = y +1;
		
		inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		inputArea.add(idField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
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