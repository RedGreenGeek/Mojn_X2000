package application.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import application.controller.MenuTopController;
import application.model.Session;
import application.model.Session.JobTypes;

public class MenuTopView extends JMenuBar {
    private static final long serialVersionUID = 1234324324L;
    protected JButton backBtn;
    protected JMenuItem btnAdd = new JMenuItem("Add Password");;
    protected JMenuItem btnChange = new JMenuItem("Change Password");;
    private JLabel lblTitle;
    private JLabel lblUser;
    private MenuTopController controller;
    private JMenu mnPassword;
    
    
    // This constructor is used for the menus
    public MenuTopView(String title, MenuTopController controller) {
    	
    	this.controller = controller;
    	initTop(title);
    }
    
    public MenuTopView(String title, String back, MenuTopController controller) {
    	this.controller = controller;
    	initTop(title, back);
    	
    }
    private void initTop(String title, MenuTopController controller) {
        // sets up the components
        Session session = controller.getSession();
        setLayout(new BorderLayout(0, 0));
        
        lblTitle = new JLabel();
        lblTitle.setText(title);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        
        // Checks the clearance of the user
        if (session.getRole() == JobTypes.ICTOfficer || session.getRole() == JobTypes.Clerk ) {
            mnPassword = new JMenu();
            Image featureImg = new ImageIcon(this.getClass().getClassLoader().getResource("icons/feature.png")).getImage();
            mnPassword.setIcon(new ImageIcon(featureImg));
            add(mnPassword, BorderLayout.WEST);
                if (session.getRole() == JobTypes.ICTOfficer){
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
    public void initTop(String title, String back, MenuTopController controller) {
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
        lblUser.setText("ID: "+ sessionModel.getUsername() + "            "+ "Role: " + sessionModel.getRole() );
    }

    
    
}