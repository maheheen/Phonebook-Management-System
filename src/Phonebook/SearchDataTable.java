package Phonebook;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchDataTable extends JFrame {
    String[] x = {"Id", "Name", "Nick Name", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};
    ArrayList<String[]> data; // ArrayList to hold the data
    JTable t;
    Font f;

    public SearchDataTable() {
    }

    public SearchDataTable(String name) {
        super("Contact Information"); //Window title
        setLocation(1, 1); //Position of the window
        setSize(800, 400); //Size of the window

        f = new Font("Arial", Font.BOLD, 14);
        data = new ArrayList<>(); // Initialize the ArrayList to store rows of data

        try {
            // Database connection
            ConnectionClass obj = new ConnectionClass();

            // SQL query to retrieve contact details by name
            String query = "SELECT * FROM add_contact WHERE name = '" + name + "'";
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
        add(sp);
    }

}
