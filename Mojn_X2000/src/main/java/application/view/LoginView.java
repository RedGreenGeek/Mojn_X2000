package application.view;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import application.controller.LoginController;
import application.utils.GridBagLayoutUtils;

public class LoginView extends JFrame {
    private static final long serialVersionUID = 8981053836072595592L;
    
    private JButton btnLogin;
    private JTextField txtLogin;
    private JPasswordField txtPass;
    private LoginController controller;
    
    // The constructor for the class is defined with the method initGUI that sets up the view of the class
    public LoginView(LoginController controller) {
        this.controller = controller;
        initGUI();
    }
    
    // All components of the window are defined
    private void initGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Login");
        setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("icons/heisenbug.png")).getImage());
        setLayout(new GridBagLayout());
        
        txtLogin = new JTextField(15);
        txtPass = new JPasswordField(15);
        btnLogin = new JButton("Login");
        
        // Listeners to the buttons are defined
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.validateCredentials(txtLogin.getText(), String.valueOf(txtPass.getPassword()));
            }
        });
        
        // to listen to the enter key
        getRootPane().setDefaultButton(btnLogin);
        

        // All components are added to the frame
        add(new JLabel("Username:"), GridBagLayoutUtils.constraint(0, 0, 1, 1,  5));
        add(txtLogin, GridBagLayoutUtils.constraint(1, 0,  1, 1, 5));
        add(new JLabel("Password:"), GridBagLayoutUtils.constraint(0, 1,  1, 1, 5));
        add(txtPass, GridBagLayoutUtils.constraint(1, 1,  1, 1, 5));
        add(btnLogin, GridBagLayoutUtils.constraint(1, 2,  1, 1, 5));
        
        pack();
        setLocationRelativeTo(null);
    }
    
    // method that displays a error message when wrong login is entered
    public void showError() {
        JOptionPane.showMessageDialog(this, "Wrong username/password combination", "Login error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showError2() {
        JOptionPane.showMessageDialog(this, "Not able to establish connection to server, please try again.", "Connection error", JOptionPane.ERROR_MESSAGE);
    }
    
}