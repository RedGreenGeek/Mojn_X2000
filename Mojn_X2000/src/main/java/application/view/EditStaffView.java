package application.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.controller.EditStaffController;
import application.utils.GridBagLayoutUtils;

public class EditStaffView extends JFrame {
    private static final long serialVersionUID = 9890754679504452L;
    private JLabel firstNameLabel;
    private JLabel surnameLabel;
    private JLabel adressLabel;
    private JLabel tribeLabel;
    private JLabel jobLabel;
    private JLabel IDLabel;

    private JTextField firstNameField;
    private JTextField surnameField;
    private JTextField adressField;
    private JTextField tribeField;
	private JComboBox jobField;
	private String[] jobtypes = {"","Nurse", "Clerk", "Doctor", "ICT Officer"};
    private JTextField IDField;

    private JButton okBtn;
    private JPanel inputArea;
    private MenuTopView menuTop = new MenuTopView("Edit Staff information");
    private TextPanelView textPanel = new TextPanelView();
    private String msg;
    
    private EditStaffController controller;
    
    // The constructor for the class is defined with the method initGUI that sets up the view of the class
    public EditStaffView(EditStaffController controller) {
        this.controller = controller;
        initGUI();
    }
    
    private void initGUI() {
        // All components of the window are defined
        setTitle("Edit Staff");
        setPreferredSize(new Dimension(900, 700));
        
        inputArea = new JPanel();
        inputArea.setLayout(new GridBagLayout());
        
        firstNameLabel = new JLabel("First name: ");
        surnameLabel = new JLabel("Surname: ");
        adressLabel = new JLabel("Address: ");
        tribeLabel = new JLabel("Tribe: ");
        jobLabel = new JLabel("Job: ");
        IDLabel = new JLabel("ID: ");
        
        firstNameField = new JTextField(11);
        surnameField = new JTextField(11);
        adressField = new JTextField(11);
        tribeField = new JTextField(11);
        jobField = new JComboBox(jobtypes);
        IDField = new JTextField(11);
        
        
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
                String firstName = firstNameField.getText();
                String surname = surnameField.getText();
                String adress = adressField.getText();
                String tribe = tribeField.getText();
                String job = (String) jobField.getSelectedItem();
                job = job.replace(" ", "");
                String ID = IDField.getText();
                firstNameField.setText("");
                surnameField.setText("");
                IDField.setText("");
                tribeField.setText("");
                adressField.setText("");
                msg = controller.EditAPI(ID,firstName, surname, adress,tribe,job);
                textPanel.textArea.append(msg);
                textPanel.textArea.append("\n");
            }
        });
        layoutComponents();
    } 
    // All the components are organized by use of a grid layout and a border layout
    public void layoutComponents() {
        setLayout(new GridBagLayout());
        
        ///////////////////////////// 1. line
        int y = 0;

        inputArea.add(firstNameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
        inputArea.add(firstNameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        ///////////////////////////// 2. line
        y = y+1;
        
        inputArea.add(surnameLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
        inputArea.add(surnameField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        /////////////////////////// 3. line
        y = y + 1;
        
        inputArea.add(adressLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
        inputArea.add(adressField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        /////////////////////////// 4. line
        y = y + 1;
        
        inputArea.add(tribeLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
        inputArea.add(tribeField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        /////////////////////////// 5. line
        y = y + 1;
        
        inputArea.add(jobLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
        inputArea.add(jobField,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        /////////////////////////// 6. line
        y = y +1;
        
        inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
        inputArea.add(IDField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        /////////////////////////// 7. line
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