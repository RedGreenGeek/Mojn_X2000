


package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.MovedBedPatientController;
import application.utils.GridBagLayoutUtils;


@SuppressWarnings("serial")
public class MovedBedPatientView extends JFrame {
	private JLabel IDLabel;
	private JLabel newBedLabel;

	private JTextField idField;
	private JTextField newBedField;



	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Move a patient to another bed", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	private MovedBedPatientController controller;

	
	public MovedBedPatientView(MovedBedPatientController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Move a patient to new bed");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());

		IDLabel = new JLabel("ID: ");
		newBedLabel = new JLabel("New bed name: ");


		idField = new JTextField(11);
		newBedField = new JTextField(11);


		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String id = idField.getText();
				String departName = newBedField.getText();

				idField.setText("");
				newBedField.setText("");

				msg = controller.MovedBedAPI(id,departName);
				


				textPanel.textArea.append(msg);
				textPanel.textArea.append("\n");

				

;			}
			
		});
		

		
		layoutComponents();

		
	} // her ender constructoren
	
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		int y = 0;

		inputArea.add(newBedLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(newBedField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		

		
		/////////////////////////// 6. linje
		y = y +1;
		
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