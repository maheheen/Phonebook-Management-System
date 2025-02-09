package Phonebook;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener {

    // Components for the Home screen
    JLabel l1, l2;
    JButton bt1, bt2, bt3, bt4, bt5, bt6; // Added bt6 for "View All"
    JPanel p1, p2, p3;
    Font f, f1;
    int userID;

    public Home(int userID) {
        super("Home Selection"); // Title of the window
        setLocation(450, 250); // Position of the window on the screen
        setSize(450, 450); // Increased size to accommodate extra button
        this.userID = userID;

        // Initialize fonts for the text
        f = new Font("Arial", Font.BOLD, 20);
        f1 = new Font("Arial", Font.BOLD, 15);

        l1 = new JLabel("Welcome to Phonebook!");

        // Buttons for various actions
        bt1 = new JButton("Entry");
        bt2 = new JButton("Search");
        bt3 = new JButton("Edit");
        bt4 = new JButton("Delete");
        bt5 = new JButton("Exit");
        bt6 = new JButton("View All"); // New button added

        l1.setHorizontalAlignment(JLabel.CENTER);

        // Add action listeners to handle button clicks
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);
        bt5.addActionListener(this);
        bt6.addActionListener(this); // Added action listener for "View All"

        // Placeholder for an image
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Images/phonebook.png"));
        Image img2 = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        l2 = new JLabel(img3);

        // Set fonts for the components
        l1.setFont(f);
        bt1.setFont(f1);
        bt2.setFont(f1);
        bt3.setFont(f1);
        bt4.setFont(f1);
        bt5.setFont(f1);
        bt6.setFont(f1); // Set font for "View All"

        // Panel to hold buttons (center part of the layout)
        p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 1, 10, 10)); // Changed grid to 6 rows
        p1.add(bt1);
        p1.add(bt2);
        p1.add(bt3);
        p1.add(bt4);
        p1.add(bt6); // Added "View All" before Exit
        p1.add(bt5);

        // Panel to hold the welcome label (top part of the layout)
        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1, 10, 10));
        p2.add(l1);

        // Panel to hold the image (right part of the layout)
        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));
        p3.add(l2);

        // Set up the main layout of the frame
        setLayout(new BorderLayout(10, 20));
        add(p2, "North");
        add(p3, "East");
        add(p1, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) { // If "Entry" button is clicked
            this.setVisible(false);
            new EntryData(userID).setVisible(true);
        }

        if (e.getSource() == bt2) { // If "Search" button is clicked
            this.setVisible(false);
            new SearchData(userID).setVisible(true);
        }

        if (e.getSource() == bt3) { // If "Edit" button is clicked
            this.setVisible(false);
            new SearchNameForEdit(userID).setVisible(true);
        }

        if (e.getSource() == bt4) { // If "Delete" button is clicked
            this.setVisible(false);
            new DeleteContact(userID).setVisible(true);
        }

        if (e.getSource() == bt6) { // If "View All" button is clicked
            this.setVisible(false);
            new ViewAllContacts(userID).setVisible(true); // Open the "View All" window
        }

        if (e.getSource() == bt5) { // If "Exit" button is clicked
            System.exit(0);
        }
    }
}
