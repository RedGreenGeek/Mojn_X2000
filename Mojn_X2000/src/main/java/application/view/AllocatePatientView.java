


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

import application.controller.AdmitPatientController;
import application.controller.AllocatePatientController;
import application.controller.DischargePatientController;
import application.controller.PatientController;
import application.controller.SearchPatientController;
import application.model.Session;

public class AllocatePatientView extends JFrame {
	private JLabel IDLabel;
	private JLabel DepartNameLabel;

	private JTextField idField;
	private JTextField DepartNameField;



	private JButton okBtn;
	private JButton backBtn;
	private JTextArea textArea;
	private JPanel inputArea;
	private JPanel textPanel;
	private JPanel menuTop;
	private String msg;
	
	private JLabel lblUser;
	
	private AllocatePatientController controller;

	
	public AllocatePatientView(AllocatePatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Allocate Patients to beds");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());

		IDLabel = new JLabel("ID: ");
		DepartNameLabel = new JLabel("Department name: ");


		idField = new JTextField(11);
		DepartNameField = new JTextField(11);


		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String id = idField.getText();
				String departName = DepartNameField.getText();

				idField.setText("");
				DepartNameField.setText("");

				msg = controller.AllocateToBedAPI(id,departName);
				


				textArea.append(msg);
				textArea.append("\n");

				

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
		

		
		/////////////////////////// 5. linje
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5); // (top, left, bottom, right)
		
		inputArea.add(DepartNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		
		inputArea.add(DepartNameField, gc);
		

		
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
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textArea = new JTextArea();	
		textArea.setEditable(false);
		
		textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	
		
		////////////////////// MENU TOP /////////////////////////7
		menuTop = new JPanel();
		menuTop.setLayout(new BorderLayout(0, 0));
		
		backBtn = new JButton("Back");
		backBtn.setHorizontalAlignment(SwingConstants.LEFT);
		menuTop.add(backBtn, BorderLayout.WEST);
		
		lblUser = new JLabel("ID## - User");
		lblUser.setBorder(new EmptyBorder(0, 0, 0, 10));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		menuTop.add(lblUser, BorderLayout.EAST);
		
		menuTop.setBorder(new EtchedBorder(10));
		
		JLabel lblTitle = new JLabel("Allocate to beds");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		menuTop.add(lblTitle, BorderLayout.CENTER);
		
		
		
		
		
		
		setLayout(new BorderLayout());
		
		add(inputArea, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(menuTop, BorderLayout.NORTH);
	
		pack();
		setLocationRelativeTo(null);
		
		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.Back2Main();
			}
		});
	}
	
	
	
	
	
	
	
	public void setSession(Session sessionModel) {
		lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
	
	}

}