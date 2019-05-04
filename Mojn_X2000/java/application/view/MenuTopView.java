package application.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import application.controller.Controller.JobTypes;
import application.model.Session;

public class MenuTopView extends JMenuBar {
    private static final long serialVersionUID = 1234324324L;
    protected JButton backBtn;
    protected JMenuItem btnAdd = new JMenuItem("Add Password");;
    protected JMenuItem btnChange = new JMenuItem("Change Password");;
    private JLabel lblTitle;
    private JLabel lblUser;
    private JMenu mnPassword;
    private JobTypes clear;
    
    // This constructor is used for the menus
    public MenuTopView(String title, JobTypes clear) {
        // sets up the components
    	this.clear = clear;
        setLayout(new BorderLayout(0, 0));
        
        lblTitle = new JLabel();
        lblTitle.setText(title);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        // Checks the clearance of the user
        if (this.clear == JobTypes.ICTOfficer || this.clear == JobTypes.Clerk ) {
            mnPassword = new JMenu();
            Image featureImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/feature.png")).getImage();
            mnPassword.setIcon(new ImageIcon(featureImg));
            add(mnPassword, BorderLayout.WEST);
                if (this.clear == JobTypes.ICTOfficer){
                    mnPassword.add(btnChange);
                }
            mnPassword.add(btnAdd);                
        }
        lblUser = new JLabel();
        lblUser.setBorder(new EmptyBorder(0, 0, 0, 10));
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Adds the components to the menubar
        add(lblTitle, BorderLayout.CENTER);
        add(lblUser, BorderLayout.EAST);
        setBorder(new EtchedBorder(10));
    }
    
    // This constructor is used for the functional views
    public MenuTopView(String title) {
        // sets up the components
        setLayout(new BorderLayout(0, 0));

        lblUser = new JLabel();
        lblUser.setBorder(new EmptyBorder(0, 0, 0, 10));
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EtchedBorder(10));
        
        lblTitle = new JLabel(title);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        backBtn = new JButton("Back");
        backBtn.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Adds the components to the menubar
        add(lblUser, BorderLayout.EAST);
        add(lblTitle, BorderLayout.CENTER);
        add(backBtn, BorderLayout.WEST);
        
    }
    
    // This method is used to set the users ID and role in right corner
    public void setSession(Session sessionModel) {
        this.lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
    }

    
    
}