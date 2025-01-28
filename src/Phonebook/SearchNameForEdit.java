package Phonebook;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchNameForEdit extends JFrame implements ActionListener {
    //Variables & Components for GUI screen
    JLabel l1, l2;
    JTextField tf1;
    JButton bt1, bt2;
    Font f, f1;
    JPanel p1, p2;
    int id = 0;

    public SearchNameForEdit() {
        super("Search Name to Edit"); //Title of the window
        setLocation(450, 50);       //Position of the window on screen
        setSize(470, 180);   //Size of the window

        // Setting fonts for the components
        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 15);

        // Initializing UI components
        l1 = new JLabel("Search a name to edit");
        l2 = new JLabel("Enter Name: ");

        tf1 = new JTextField();

        bt1 = new JButton("Search Contact");
        bt2 = new JButton("Back");
        l1.setHorizontalAlignment(JLabel.CENTER);

        // Adding action listeners to buttons
        bt1.addActionListener(this);
        bt2.addActionListener(this);

        // Setting fonts for the labels, text field, and buttons
        l1.setFont(f);
        l2.setFont(f1);
        tf1.setFont(f1);
        bt1.setFont(f1);
        bt2.setFont(f1);

        // Panel for title, buttons, and user input
        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);
        p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 2, 10, 10));
        p2.add(l2);
        p2.add(tf1);
        p2.add(bt1);
        p2.add(bt2);

        // Setting layout for the JFrame and adding panels
        setLayout(new BorderLayout(10, 20));
        add(p1, "North");
        add(p2, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        String name = tf1.getText(); // Get the name entered by the user

        // If search button is clicked
        if (e.getSource() == bt1) {
            try {
                // Establish connection to the database
                ConnectionClass obj = new ConnectionClass();
                String query = "SELECT name FROM add_contact WHERE name = '" + name + "'";
                ResultSet rest = obj.stm.executeQuery(query);

                // If contact is found, open the editing screen
                if (rest.next()) {
                    String name1 = rest.getString("name");
                    new SearchDataTableForEdit(name1).setVisible(true);
                }
                else { // If contact is not found, show a message and refresh the screen
                    id = 0;
                    JOptionPane.showMessageDialog(null, "Contact not found");
                    this.setVisible(false);
                    this.setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // If back button is clicked, return to the home screen
        if (e.getSource() == bt2) {
            this.setVisible(false);
            new Home().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new SearchNameForEdit().setVisible(true);
    }
}
