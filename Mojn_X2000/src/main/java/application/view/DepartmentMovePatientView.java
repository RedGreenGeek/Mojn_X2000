


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


public class DepartmentMovePatientView extends JFrame {
	private static final long serialVersionUID = 989075234541187452L;
	private JLabel IDLabel;
	private JLabel departNameLabel;
	private JLabel triLabel;

	private JTextField idField;
	private JTextField departNameField;
	private JTextField triField;



	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Admit a patient to a department", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	private DepartmentMovePatientController controller;

	
	public DepartmentMovePatientView(DepartmentMovePatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Move patients between departments");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());

		IDLabel = new JLabel("ID: ");
		triLabel = new JLabel("Triage Level: ");
		departNameLabel = new JLabel("New department name: ");


		idField = new JTextField(11);
		departNameField = new JTextField(11);
		triField = new JTextField(11);


		
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

				

;			}
			
		});
		

		
		layoutComponents();

		
	} // her ender constructoren
	
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		/////////////////////////////////// 1. linje
		

		
		///////////////////////////// 2. linje
		

		/////////////////////////// 3. linje
		

		
		/////////////////////////// 4. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(triLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(triField, gc);
		

		
		/////////////////////////// 5. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(departNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(departNameField, gc);
		

		
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