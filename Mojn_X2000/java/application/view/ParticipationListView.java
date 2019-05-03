package application.view;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.ParticipationListController;
import application.utils.GridBagLayoutUtils;


public class ParticipationListView extends JFrame {
	private static final long serialVersionUID = 9890752820411452L;
	private JLabel departmentNameLabel;
	private JLabel departLabel;
	private JLabel birthLabel;
	private JLabel addressLabel;
	private JLabel tribeLabel;

	
	private JCheckBox departBox;
	private JCheckBox birthBox;
	private JCheckBox addressBox;
	private JCheckBox tribeBox;



	private JTextField departmentNameField;


	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Save participation list", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	private ParticipationListController controller;

	
	public ParticipationListView(ParticipationListController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Participation list");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());

		departmentNameLabel = new JLabel("Department name: ");
		departLabel = new JLabel("Departments? ");
		birthLabel = new JLabel("Birthday? ");
		addressLabel = new JLabel("Adress? ");
		tribeLabel = new JLabel("Tribe? ");



		departmentNameField = new JTextField(11);
		departBox = new JCheckBox();
		birthBox = new JCheckBox();
		addressBox = new JCheckBox();
		tribeBox = new JCheckBox();
		
		

		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String departments = departmentNameField.getText();
				boolean departs = departBox.isSelected();
				boolean birthday = birthBox.isSelected();
				boolean adress = addressBox.isSelected();
				boolean tribe = tribeBox.isSelected();


				departmentNameField.setText("");
				try {
					msg = controller.ParticipationListAPI(departments, departs, birthday, adress, tribe);
				} catch (IOException e) {
					System.out.println("?");
				}
				


				textPanel.textArea.append(msg);
				textPanel.textArea.append("\n");

				

;			}
			
		});
		

		
		layoutComponents();

		
	} // her ender constructoren
	
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		int y = 0;

		inputArea.add(departLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(departBox, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		

		/////////////////////////// 3. linje
		

		y = y + 1;

		inputArea.add(addressLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(addressBox, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 4. linje
		
		y = y + 1;

		inputArea.add(tribeLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(tribeBox, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		/////////////////////////// 5. linje
		
		y = y + 1;

		inputArea.add(birthLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(birthBox, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
		/////////////////////////// 6. linje
		
		y = y + 1;

		inputArea.add(departmentNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(departmentNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
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