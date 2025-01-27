package Phonebook;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SearchDataTableForEdit extends JFrame implements ActionListener {
    String[] x = {"Id", "Name", "Nickname", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};
    ArrayList<String[]> data; // Using ArrayList instead of a fixed array
    JTable t;
    Font f;
    JLabel l1;
    JButton bt1;
    JTextField tf1;
    JPanel p1;

    SearchDataTableForEdit() {
    }

    SearchDataTableForEdit(String name1) {
        super("Contact Information");
        setLocation(1, 1);
        setSize(800, 400);

        f = new Font("Arial", Font.BOLD, 14);
        data = new ArrayList<>();

        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT * FROM add_contact WHERE Name = '" + name1 + "'";
            ResultSet rest = obj.stm.executeQuery(q);

            // Populate the ArrayList
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
                data.add(row);
            }

            // Convert the ArrayList to a 2D array for JTable
            String[][] y = new String[data.size()][10];
            for (int i = 0; i < data.size(); i++) {
                y[i] = data.get(i);
            }

            t = new JTable(y, x);
            t.setFont(f);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(t);
        l1 = new JLabel("Enter Contact Id");
        tf1 = new JTextField();
        bt1 = new JButton("Edit");
        bt1.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 3, 10, 10));
        p1.add(l1);
        p1.add(tf1);
        p1.add(bt1);

        setLayout(new BorderLayout(10, 10));
        add(p1, "South");
        add(sp, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        int idNo = Integer.parseInt(tf1.getText());

        if (e.getSource() == bt1) {
            new EditData(idNo).setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new SearchDataTableForEdit("John").setVisible(true);
    }
}
