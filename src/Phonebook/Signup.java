package Phonebook;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Signup extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton signupButton, backButton;
    Font f1, f2;

    public Signup() {
        super("Sign Up - Phonebook");
        setLocation(400, 300);
        setSize(530, 300);

        f1 = new Font("Arial", Font.BOLD, 25);
        f2 = new Font("Arial", Font.BOLD, 18);

        l1 = new JLabel("Create Your Account");
        l2 = new JLabel("Username:");
        l3 = new JLabel("Password:");

        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f1);
        l2.setFont(f2);
        l3.setFont(f2);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameField.setFont(f2);
        passwordField.setFont(f2);

        signupButton = new JButton("Sign Up");
        backButton = new JButton("Back");

        signupButton.setFont(f2);
        backButton.setFont(f2);

        signupButton.addActionListener(this);
        backButton.addActionListener(this);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        formPanel.add(l2);
        formPanel.add(usernameField);
        formPanel.add(l3);
        formPanel.add(passwordField);
        formPanel.add(signupButton);
        formPanel.add(backButton);

        setLayout(new BorderLayout(20, 20));
        add(l1, "North");
        add(formPanel, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String hashedPassword = PasswordHasher.hashPassword(password); // Hash the password
                String query = "INSERT INTO login (username, password) VALUES ('" + username + "', '" + hashedPassword + "')";
                obj.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully!");
                this.setVisible(false);
                new Login().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == backButton) {
            new WelcomePage().setVisible(true);
            this.setVisible(false);
        }
    }
}

