package Phonebook;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class DeleteContact extends JFrame implements ActionListener {
    // Column names for the JTable
    String[] x = {"Id", "Name", "Nickname", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};

    JButton bt, bt2;
    ArrayList<String[]> data; // List to store contact data from the database
    JTable t;
    Font f;
    JLabel l1;
    JTextField tf1;
    JPanel p1;
    int userId;

    DeleteContact(int userId) {
        super("Contact Information"); //Title of the window
        setSize(800, 400); //Size of the window
        setLocation(1, 1); //Position of the window on the screen

        this.userId = userId;
        f = new Font("MS UI Gothic", Font.BOLD, 15);

        // Initialize the data list to store contact details
        data = new ArrayList<>();
        try {
            // Establish connection to the database
            ConnectionClass obj = new ConnectionClass();

            String q = "SELECT * FROM add_contact where user_id = " + userId; // Query to fetch all contact records
            ResultSet rest = obj.stm.executeQuery(q); // Execute the query

            // Iterate through the result set and add data to the ArrayList
            while (rest.next()) {
                String[] row = new String[10];
                row[0] = rest.getString("id");
                row[1] = rest.getString("name");
                row[2] = rest.getString("nickname");
                row[3] = rest.getString("phone");
                row[4] = rest.getString("mobile");
                row[5] = rest.getString("email");
                row[6] = rest.getString("address");
                row[7] = rest.getString("company");
                row[8] = rest.getString("position");
                row[9] = rest.getString("group_name");
                data.add(row); // Add the row to the data list
            }

            // Convert ArrayList to a 2D array for displaying in JTable
            String[][] y = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {
                y[i] = data.get(i);
            }

            // Create a JTable to display the contact data
            t = new JTable(y, x);
            t.setFont(f);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(t);
        l1 = new JLabel("Enter Contact Id for deletion");
        tf1 = new JTextField();

        // Button to trigger the deletion process
        bt = new JButton("Delete");
        bt.setBackground(Color.BLACK);
        bt.setForeground(Color.RED);
        bt.addActionListener(this);

        bt2 = new JButton("Back");
        bt2.addActionListener(this);


        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 3, 10, 10));
        p1.add(l1);
        p1.add(tf1);
        p1.add(bt);
        p1.add(bt2);

        // Set the layout for the main frame
        setLayout(new BorderLayout(10, 10));
        add(p1, "South");
        add(sp, "Center");
    }

    // Handle button click events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt) { // If the delete button is clicked
            int idNo = Integer.parseInt(tf1.getText()); // Get the ID entered by the user
            try {
                // Establish connection to the database
                ConnectionClass obj1 = new ConnectionClass();

                // Query to delete the contact with the given ID
                String q0 = "DELETE FROM add_contact WHERE Id ='" + idNo + "' AND user_id  = " + userId;
                int res = obj1.stm.executeUpdate(q0); // Execute the deletion query

                if (res == 1) { // If a contact was deleted
                    JOptionPane.showMessageDialog(null, "Contact successfully deleted");
                    this.setVisible(false);
                    new DeleteContact(userId).setVisible(true);
                }
                else { // If no contact was deleted
                    JOptionPane.showMessageDialog(null, "Error deleting contact");
                    this.setVisible(false);
                    new DeleteContact(userId).setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == bt2) {
            new Home(userId).setVisible(true);
            this.setVisible(false);
        }
    }

}
