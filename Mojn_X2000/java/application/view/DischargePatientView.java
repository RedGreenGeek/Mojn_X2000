


package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.DischargePatientController;
import application.utils.GridBagLayoutUtils;


public class DischargePatientView extends JFrame {
	private static final long serialVersionUID = 9890752820411452L;
	private JLabel IDLabel;

	private JTextField idField;


	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Discharge a patient", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	private DischargePatientController controller;

	
	public DischargePatientView(DischargePatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Discharge Patients");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());

		IDLabel = new JLabel("ID: ");


		idField = new JTextField(11);
		

		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String id = idField.getText();

				idField.setText("");
				msg = controller.DischargeAPI(id);
				


				textPanel.textArea.append(msg);
				textPanel.textArea.append("\n");

				

;			}
			
		});
		

		
		layoutComponents();

		
	} // her ender constructoren
	
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		int y = 0;
		inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(idField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		
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