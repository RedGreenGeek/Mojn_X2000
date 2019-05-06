package application.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import application.controller.AddPasswordController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class AddPasswordView extends JFrame {
    private static final long serialVersionUID = 98526482041187452L;
    private JLabel NewPass1Label;
    private JLabel NewPass2Label;
    private JLabel IDLabel;

    private JTextField NewPass1Field;
    private JTextField NewPass2Field;
    private JTextField IDField;

    private JButton okBtn;
    private JPanel inputArea;
    private MenuTopView menuTop = new MenuTopView("Add Password");
    private TextPanelView textPanel = new TextPanelView();
    private String msg;
    private AddPasswordController controller;
    
    // The constructor for the class is defined with the method initGUI that sets up the view of the class
    public AddPasswordView(AddPasswordController controller) {
        this.controller = controller;
        initGUI();
    }
    
    private void initGUI() {
        // All components of the add are defined
        setTitle("Add password to staff");
        setPreferredSize(new Dimension(900, 700));
        
        inputArea = new JPanel();
        inputArea.setLayout(new GridBagLayout());
        
        NewPass1Label = new JLabel("Wanted password: ");
        NewPass2Label = new JLabel("Repeat password: ");
        IDLabel = new JLabel("ID: ");
        
        NewPass1Field = new JTextField(11);
        NewPass2Field = new JTextField(11);
        IDField = new JTextField(11);
        
        
        // Listeners to the buttons are defined
        okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String NewPass1 = NewPass1Field.getText();
                String NewPass2 = NewPass2Field.getText();
                String ID = IDField.getText();
                NewPass1Field.setText("");
                NewPass2Field.setText("");
                IDField.setText("");
                msg = controller.AddPasswordAPI(NewPass1, NewPass2,ID);
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
        
        inputArea.add(NewPass1Label, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5,  GridBagConstraints.LINE_END));
        
        inputArea.add(NewPass1Field,  GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        /////////////////////////// 2. line
        y = y +1;
        
        inputArea.add(NewPass2Label, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
        
        inputArea.add(NewPass2Field, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        
        /////////////////////////// 3. line
        y = y +1;
        
        inputArea.add(IDLabel, GridBagLayoutUtils.constraint(0, y, 1, 0.1, 0, 0, 0, 5, GridBagConstraints.LINE_END));
        
        inputArea.add(IDField, GridBagLayoutUtils.constraint(1, y, 1, 0.1, 0, 0, 0, 0, GridBagConstraints.LINE_START));
        
        /////////////////////////// 4. line
        y = y+1;
        
        inputArea.add(okBtn, GridBagLayoutUtils.constraint(1, y, 1, 1, 0,0, 0, 0, GridBagConstraints.FIRST_LINE_START));
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