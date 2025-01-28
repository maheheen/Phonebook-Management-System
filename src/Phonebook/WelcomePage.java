package Phonebook;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomePage extends JFrame implements ActionListener {

    //Components for GUI
    JLabel titleLabel, imageLabel;
    JButton loginButton, signupButton;
    Font titleFont, buttonFont;

    public WelcomePage() {
        super("Welcome to Phonebook");  //Title
        setLocation(400, 200);         //Location on screen
        setSize(600, 350);      //Size of window

        titleFont = new Font("Arial", Font.BOLD, 25);
        buttonFont = new Font("Arial", Font.PLAIN, 18);

        // Title Label (Centered at Top)
        titleLabel = new JLabel("Welcome to Phonebook!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(titleFont);

        //Image placement
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Images/phone-book.png")); // Ensure this file exists
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Resizing
        imageLabel = new JLabel(new ImageIcon(img));

        // Adding two buttons for login and sign up
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        loginButton.setFont(buttonFont);
        signupButton.setFont(buttonFont);

        loginButton.setPreferredSize(new Dimension(120, 40));
        signupButton.setPreferredSize(new Dimension(120, 40));

        loginButton.addActionListener(this);
        signupButton.addActionListener(this);

        // Panel for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(signupButton, gbc);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.insets = new Insets(10, 20, 10, 20);

        // Title at the top
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.gridwidth = 2;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, mainGbc);

        // Buttons on the Left
        mainGbc.gridx = 0;
        mainGbc.gridy = 1;
        mainGbc.gridwidth = 1;
        mainGbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(buttonPanel, mainGbc);

        // Image on the Right
        mainGbc.gridx = 1;
        mainGbc.gridy = 1;
        mainGbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(imageLabel, mainGbc);

        // Set Main Layout
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
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
