package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.ChangePasswordController;
import application.utils.GridBagLayoutUtils;

public class ChangePasswordView extends JFrame {
	private static final long serialVersionUID = 985282041187452L;
	private JLabel NewPass1Label;
	private JLabel NewPass2Label;
	private JLabel OldPass1Label;

	private JLabel IDLabel;

	private JTextField NewPass1Field;
	private JTextField NewPass2Field;
	private JTextField OldPass1Field;

	private JTextField IDField;



	private JButton okBtn;
	private JPanel inputArea;
	private MenuTopView menuTop = new MenuTopView("Change password of a staff", "back");
	private TextPanelView textPanel = new TextPanelView();
	private String msg;
	
	
	private ChangePasswordController controller;

	
	public ChangePasswordView(ChangePasswordController controller) {
		this.controller = controller;
		initGUI();
	}
	
	
	private void initGUI() {
		setTitle("Change password of a staff");
		setPreferredSize(new Dimension(800, 700));
		
		inputArea = new JPanel();
		inputArea.setLayout(new GridBagLayout());
		
		OldPass1Label = new JLabel("Old password: ");
		NewPass1Label = new JLabel("Wanted password: ");
		NewPass2Label = new JLabel("Repeat password: ");
		
		IDLabel = new JLabel("ID: ");

		
		NewPass1Field = new JTextField(11);
		NewPass2Field = new JTextField(11);
		OldPass1Field = new JTextField(11);
		
		IDField = new JTextField(11);

		

		
		okBtn = new JButton("OK");
		
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String NewPass1 = NewPass1Field.getText();
				String NewPass2 = NewPass2Field.getText();
				String oldPassword = OldPass1Field.getText();

		
				String ID = IDField.getText();

				NewPass1Field.setText("");
				NewPass2Field.setText("");
				OldPass1Field.setText("");

		
				IDField.setText("");

				msg = controller.ChangePasswordAPI(NewPass1, NewPass2,ID, oldPassword);
				


				textPanel.textArea.append(msg);
				textPanel.textArea.append("\n");

				

;			}
			
		});
		

		
		layoutComponents();

		
	} // her ender constructoren
	
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		int y = 0;
		
		inputArea.add(NewPass1Label, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
		
		inputArea.add(NewPass1Field,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 7. linje
		y = y +1;
		
		inputArea.add(NewPass2Label, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(NewPass2Field, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		y = y +1;
		
		inputArea.add(OldPass1Label, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(OldPass1Field, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		/////////////////////////// 7. linje
		y = y +1;
		
		inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
		
		inputArea.add(IDField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
		
		
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
