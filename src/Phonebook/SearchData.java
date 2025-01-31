package Phonebook;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchData extends JFrame implements ActionListener {
    //Components for SearchData Screen
    JLabel l1, l2;
    JTextField tf1;
    JButton bt1, bt2;
    Font f, f1;
    JPanel p1, p2;
    int id = 0;
    int userId;

    public SearchData(int userId) {
        super("Search Contact"); //Title of the window
        setLocation(450, 50); //Position of the window on the screen
        setSize(470, 180); //Size of the window

        this.userId = userId;

        // Initializing fonts
        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 15);

        // Initializing labels
        l1 = new JLabel("Search a Contact");
        l2 = new JLabel("Enter Name: ");

        // Text field for user input
        tf1 = new JTextField();

        // Initializing buttons
        bt1 = new JButton("Search Contact");
        bt2 = new JButton("Back");

        // Center-aligning the header label
        l1.setHorizontalAlignment(JLabel.CENTER);

        // Adding action listeners for button clicks
        bt1.addActionListener(this);
        bt2.addActionListener(this);

        // Setting fonts for labels, text field, and buttons
        l1.setFont(f);
        l2.setFont(f1);
        tf1.setFont(f1);
        bt1.setFont(f1);
        bt2.setFont(f1);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 2, 10, 10));
        p2.add(l2);
        p2.add(tf1);
        p2.add(bt1);
        p2.add(bt2);

        // Setting the layout for the frame and adding panels
        setLayout(new BorderLayout(10, 20));
        add(p1, "North");
        add(p2, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        String name = tf1.getText(); // Getting the name entered by the user

        if (e.getSource() == bt1) { // If "Search Contact" button is clicked
            try {
                // Establishing database connection
                ConnectionClass obj = new ConnectionClass();

                // SQL query to search for the contact by name
                String query = "SELECT name FROM add_contact WHERE name = '" + name + "' AND user_id = " + userId;
                ResultSet rest = obj.stm.executeQuery(query);

                if (rest.next()) { // If the contact exists in the database
                    String name1 = rest.getString("name"); //Get the name
                    new SearchDataTable(name1).setVisible(true); // Open the search results table
                }
                else { // If no contact is found
                    id = 0; // Set id to 0 determining no contact exists
                    JOptionPane.showMessageDialog(null, "Contact not found"); // Show error message
                    this.setVisible(false); // Close the current window
                    this.setVisible(true); // Reopen the search window
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == bt2) { // If "Back" button is clicked
            this.setVisible(false); // Close the current window
            new Home(userId).setVisible(true); // Open the Home window
        }
    }
}
