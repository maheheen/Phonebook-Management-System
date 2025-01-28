package Phonebook;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomePage extends JFrame implements ActionListener {
    JLabel l1;
    JButton loginButton, signupButton;
    Font f1, f2;

    public WelcomePage() {
        super("Welcome to Phonebook");
        setLocation(400, 300);
        setSize(500, 250);

        f1 = new Font("Arial", Font.BOLD, 25);
        f2 = new Font("Arial", Font.PLAIN, 18); // Smaller font size

        l1 = new JLabel("Welcome to Phonebook!");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f1);

        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");

        loginButton.setFont(f2);
        signupButton.setFont(f2);

        // Set preferred size for buttons
        loginButton.setPreferredSize(new Dimension(100, 35));
        signupButton.setPreferredSize(new Dimension(100, 35));

        loginButton.addActionListener(this);
        signupButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Adds spacing
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        setLayout(new BorderLayout(10, 10));
        add(l1, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            new Login().setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == signupButton) {
            new Signup().setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new WelcomePage().setVisible(true);
    }
}
