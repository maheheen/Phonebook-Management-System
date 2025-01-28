package Phonebook;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class EntryData extends JFrame implements ActionListener {
    // Labels for form fields
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;

    // Buttons for submission or going back to Home page
    JButton bt1, bt2;

    // Panels for organizing components
    JPanel p1, p2;

    // Fonts for labels and text fields
    Font f, f1;

    // Text fields for input data
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;

    public EntryData() {
        super("Add Contact"); // Title of the window
        setLocation(450, 50); // Position of the window on the screen
        setSize(450, 650); // Size of the window

        // Initialize fonts for heading and GUI components
        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 15);

        // Initialize labels with descriptive names
        l1 = new JLabel("Add Contact");
        l2 = new JLabel("Name: ");
        l3 = new JLabel("Nick Name: ");
        l4 = new JLabel("Phone No: ");
        l5 = new JLabel("Mobile No: ");
        l6 = new JLabel("Email Address: ");
        l7 = new JLabel("Home Address: ");
        l8 = new JLabel("Company: ");
        l9 = new JLabel("Position: ");
        l10 = new JLabel("Group Name: ");

        // Initialize text fields for user inputs
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();

        // Initialize buttons
        bt1 = new JButton("Add Contact");
        bt2 = new JButton("Back");

        // Add action listeners to buttons
        bt1.addActionListener(this);
        bt2.addActionListener(this);

        // Set font styles for labels and buttons
        l1.setFont(f); // Font for heading
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l9.setFont(f1);
        l10.setFont(f1);

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tf9.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);

        // Create and set up the heading panel
        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10)); // Single row layout for heading
        p1.add(l1); // Add heading label to the panel

        // Create and set up the form panel
        p2 = new JPanel();
        p2.setLayout(new GridLayout(10, 2, 10, 10)); // Grid layout for form fields and labels
        p2.add(l2);
        p2.add(tf1); // Name field
        p2.add(l3);
        p2.add(tf2); // Nickname field
        p2.add(l4);
        p2.add(tf3); // Phone number field
        p2.add(l5);
        p2.add(tf4); // Mobile number field
        p2.add(l6);
        p2.add(tf5); // Email field
        p2.add(l7);
        p2.add(tf6); // Address field
        p2.add(l8);
        p2.add(tf7); // Company field
        p2.add(l9);
        p2.add(tf8); // Position field
        p2.add(l10);
        p2.add(tf9); // Group name field
        p2.add(bt1); // Add Contact button
        p2.add(bt2); // Back button

        // Set layout for the frame and add panels
        setLayout(new BorderLayout(10, 20));
        add(p1, "North"); // Add heading panel to the top
        add(p2, "Center"); // Add form panel to the center
    }

    // Handle button actions
    public void actionPerformed(ActionEvent e) {

        // Collect data entered in the form
        String name = tf1.getText();
        String nickName = tf2.getText();
        String phone = tf3.getText();
        String mobile = tf4.getText();
        String email = tf5.getText();
        String address = tf6.getText();
        String company = tf7.getText();
        String position = tf8.getText();
        String group = tf9.getText();

        // If "Add Contact" button is clicked
        if (e.getSource() == bt1) {
            try {
                // Connect to the database
                ConnectionClass obj = new ConnectionClass();

                // Insert query to save contact details in the database
                String q = "insert into add_contact (name, nickname, phone, mobile, email, address, company, position, group_name) value('" + name + "', '" + nickName + "', '" + phone + "', '" + mobile + "', '" + email + "', '" + address + "', '" + company + "', '" + position + "', '" + group + "')";

                int var = obj.stm.executeUpdate(q); // Execute the query

                if (var == 1) { // If the insertion is successful
                    JOptionPane.showMessageDialog(null, "Your data is inserted.");
                    this.setVisible(false);
                    new Home().setVisible(true);
                } else { // If insertion fails
                    JOptionPane.showMessageDialog(null, "Please fill the details carefully");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // If "Back" button is clicked
        if (e.getSource() == bt2) {
            this.setVisible(false);
            new Home().setVisible(true);
        }
    }

    public static void main(String[] args) {
        // new EntryData().setVisible(true);
    }
}
