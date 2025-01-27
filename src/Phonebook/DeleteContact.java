package Phonebook;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class DeleteContact extends JFrame implements ActionListener {
    String[] x = {"Id", "Name", "Nickname", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};
    JButton bt;
    ArrayList<String[]> data;
    JTable t;
    Font f;
    JLabel l1;
    JTextField tf1;
    JPanel p1;

    DeleteContact() {
        super("Contact Information");
        setSize(800, 400);
        setLocation(1, 1);
        f = new Font("MS UI Gothic", Font.BOLD, 15);

        data = new ArrayList<>();
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT * FROM add_contact";
            ResultSet rest = obj.stm.executeQuery(q);

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

            // Convert ArrayList to a 2D array for JTable
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
        l1 = new JLabel("Enter Contact Id for deletion");
        tf1 = new JTextField();
        bt = new JButton("Delete");
        bt.setBackground(Color.BLACK);
        bt.setForeground(Color.RED);
        bt.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 3, 10, 10));
        p1.add(l1);
        p1.add(tf1);
        p1.add(bt);

        setLayout(new BorderLayout(10, 10));
        add(p1, "South");
        add(sp, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt) {
            int idNo = Integer.parseInt(tf1.getText());
            try {
                ConnectionClass obj1 = new ConnectionClass();
                String q0 = "DELETE FROM add_contact WHERE Id ='" + idNo + "'";
                int res = obj1.stm.executeUpdate(q0);

                if (res == 1) {
                    JOptionPane.showMessageDialog(null, "Contact successfully deleted");
                    this.setVisible(false);
                    new DeleteContact().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error deleting contact");
                    this.setVisible(false);
                    new DeleteContact().setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DeleteContact().setVisible(true);
    }
}
