package Phonebook;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SearchDataTableForEdit extends JFrame implements ActionListener {
    // Column names for the table
    String[] x = {"Id", "Name", "Nickname", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};

    // ArrayList to hold data fetched from the database
    ArrayList<String[]> data;
    JTable t;
    Font f;
    JLabel l1;
    JButton bt1;
    JTextField tf1;
    JPanel p1;

    SearchDataTableForEdit() {
    }

    SearchDataTableForEdit(String name1) {
        super("Contact Information");   //Title of the window
        setLocation(1, 1);             //Position of the window on the screen
        setSize(800, 400);      //Size of the window

        f = new Font("Arial", Font.BOLD, 14);

        // Initializing the data ArrayList
        data = new ArrayList<>();

        try {
            // Creating an instance of ConnectionClass to execute the query
            ConnectionClass obj = new ConnectionClass();

            // Query to fetch contact details by name
            String q = "SELECT * FROM add_contact WHERE Name = '" + name1 + "'";
            ResultSet rest = obj.stm.executeQuery(q);

            // Loop through the result set and store data in the ArrayList
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
                data.add(row); // Add the row data to the list
            }

            // Convert the ArrayList to a 2D array for JTable
            String[][] y = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {
                y[i] = data.get(i);
            }

            // Create the JTable with the data
            t = new JTable(y, x);
            t.setFont(f);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(t);

        // Create label and text field for contact ID
        l1 = new JLabel("Enter Contact Id");
        tf1 = new JTextField();

        // Create the edit button with action listener
        bt1 = new JButton("Edit");
        bt1.addActionListener(this);

        // Create a panel to hold the label, text field, and button
        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 3, 10, 10));
        p1.add(l1);
        p1.add(tf1);
        p1.add(bt1);

        // Set the layout and add components to the frame
        setLayout(new BorderLayout(10, 10));
        add(p1, "South");
        add(sp, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        // Get the contact ID entered by the user
        int idNo = Integer.parseInt(tf1.getText());

        // Check if the edit button was clicked
        if (e.getSource() == bt1) {
            // Open the EditData window for the selected contact
            new EditData(idNo).setVisible(true);
            this.setVisible(false); // Close the current window
        }
    }

}
