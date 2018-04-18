package MVC.view;

import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * Created by plesha on 08-Apr-18.
 */
public class LoginViewGUI {
    private JPanel panel;
    private JPanel loginPanel1;
    private JTextPane passwordTextPane;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextPane usernameTextPane;
    private JTextPane welcomeToOurPharmacyTextPane;
    private JButton loginButton;
    private JFrame frame = new JFrame("Login Panel - Pharmacy");

    public LoginViewGUI(){
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public void closeLoginFrame(){
        frame.setVisible(false);
        frame.dispose();
    }


    public void addLoginButtonListener(ActionListener login ){
        loginButton.addActionListener(login);
    }

    public String getUsername(){
        return this.textField1.getText();
    }

    public String getPassword(){
        return this.passwordField1.getText();
    }

    public void showMessage( String message ){
        JOptionPane.showMessageDialog(null,message);
    }
}
