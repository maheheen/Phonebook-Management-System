package Phonebook;

import com.mysql.cj.conf.ConnectionPropertiesTransform;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    //Components for Login Screen
    JLabel l1, l2, l3, l4;
    JTextField textField;
    JPasswordField passwordField;
    JPanel p1, p2, p3;
    JButton bt1, bt2;
    Font f1, f2;

    public Login() {
        super("Login Phonebook"); //Title of the window
        setLocation(400, 300); //Position of the window on the screen
        setSize(530, 250); //Size of the window

        // Initializing fonts
        f1 = new Font("Arial", Font.BOLD, 25);
        f2 = new Font("Arial", Font.BOLD, 18);

        // Initializing labels
        l1 = new JLabel("Welcome to Phonebook!");
        l2 = new JLabel("Username:");
        l3 = new JLabel("Password:");

        // Centering and setting fonts for labels
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f1);
        l2.setFont(f2);
        l3.setFont(f2);

        // Initializing input fields
        textField = new JTextField();
        passwordField = new JPasswordField();

        // Setting fonts for input fields
        textField.setFont(f2);
        passwordField.setFont(f2);

        // Initializing buttons
        bt1 = new JButton("Login");
        bt2 = new JButton("Cancel");

        // Adding action listeners for buttons
        bt1.addActionListener(this);
        bt2.addActionListener(this);

        // Setting fonts for buttons
        bt1.setFont(f2);
        bt2.setFont(f2);

        // Adding an image/icon to the UI
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Images/phonebook.png"));
        Image img2 = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        l4 = new JLabel(img3);

        // Panel for form inputs
        p1 = new JPanel();
        p1.setLayout(new GridLayout(3, 2, 10, 10));
        p1.add(l2);
        p1.add(textField);
        p1.add(l3);
        p1.add(passwordField);
        p1.add(bt1);
        p1.add(bt2);

        // Panel for welcome text
        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1, 10, 10));
        p2.add(l1);

        // Panel for image/icon
        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));
        p3.add(l4);

        // Adding panels to the frame with BorderLayout
        setLayout(new BorderLayout(10, 20));
        add(p2, "North");
        add(p3, "East");
        add(p1, "Center");
    }

    // Handling button actions
    public void actionPerformed(ActionEvent e) {
        String username = textField.getText(); // Get username input
        String password = passwordField.getText(); // Get password input

        if (e.getSource() == bt1) { // If login button is clicked
            try {
                ConnectionClass obj = new ConnectionClass();
                String hashedPassword = PasswordHasher.hashPassword(password); // Hash the entered password
                String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + hashedPassword + "'";
                ResultSet rest = obj.stm.executeQuery(query); //Executing query

                if (rest.next()) { // If user credentials are valid
                    new Home().setVisible(true); // Open Home screen
                    System.out.println("You have logged in");
                    this.setVisible(false); // Close login window
                } else {
                    JOptionPane.showMessageDialog(null, "Your credentials are incorrect"); // Show error message
                    this.setVisible(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == bt2) { // If cancel button is clicked
            this.setVisible(false); // Close login window
        }
    }
}
