package Phonebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ViewAllContacts extends JFrame {
    String[] x = {"Id", "Name", "Nick Name", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};
    ArrayList<String[]> data; // ArrayList to hold the data
    JTable t;
    Font f;
    JButton backButton;

    public ViewAllContacts(int userId) {
        super("Contact Information"); // Window title
        setLocation(100, 100); // Position of the window
        setSize(800, 400); // Size of the window
        setLayout(new BorderLayout());

        f = new Font("Arial", Font.BOLD, 14);
        data = new ArrayList<>(); // Initialize the ArrayList to store rows of data

        try {
            // Database connection
            ConnectionClass obj = new ConnectionClass();

            // SQL query to retrieve contact details by user_id
            String query = "SELECT * FROM add_contact WHERE user_id = " + userId;
            ResultSet rest = obj.stm.executeQuery(query);

            // Loop through the result set and populate the data ArrayList
            while (rest.next()) {
                // Create a row for each record
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
                data.add(row); // Add row to the ArrayList
            }

            // Convert ArrayList to 2D array for JTable
            String[][] y = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {
                y[i] = data.get(i);
            }

            // Create JTable with the retrieved data
            t = new JTable(y, x);
            t.setFont(f);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(t);
        add(sp, BorderLayout.CENTER);

        // **Back Button**
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.setFocusable(false);

        // Add ActionListener to go back
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new Home(userId).setVisible(true); // Navigate back to Home or main page
            }
        });

        // Panel to hold the back button at the bottom
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
