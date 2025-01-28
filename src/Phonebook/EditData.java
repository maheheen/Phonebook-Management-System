package Phonebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EditData extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13 ;
    JButton bt1, bt2;
    JPanel p1, p2, p3;                              //Declaration of all the variables and SWING components used.
    Font f,f1;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;
    public EditData(){

    }
    public EditData (int idno) {               // Constructor to initialize the UI
        super(("Edit Contact"));               //Title of the window
        setLocation(450, 50);            //Location of window appearance on screen
        setSize(450, 650);        //Size of the particular screen

        //Fonts for labels, buttons etc.
        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 15);

        //Initializing labels
        l1 = new JLabel("Update Contact");
        l2 = new JLabel("Name: ");
        l3 = new JLabel("Nick Name: ");
        l4 = new JLabel("Phone No: ");
        l5 = new JLabel("Mobile No: ");
        l6 = new JLabel("Email Address: ");
        l7 = new JLabel("Home Address: ");
        l8 = new JLabel("Company: ");
        l9 = new JLabel("Position: ");
        l10 = new JLabel("Group Name: ");
        l11 = new JLabel("Id: ");

        //Initializing text fields
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();
        tf10 = new JTextField();

        //Initializing buttons
        bt1 = new JButton("Edit Contact");
        bt2 = new JButton("Back");

        l1.setHorizontalAlignment(JLabel.CENTER);       //Main heading should be in the center.

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        bt1.setBackground(Color.black);
        bt1.setForeground(Color.RED);

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l9.setFont(f1);
        l10.setFont(f1);
        l11.setFont(f1);

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tf9.setFont(f1);
        tf10.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(11, 2, 10, 10));

        p2.add(l11);
        p2.add(tf10);
        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2);
        p2.add(l4);
        p2.add(tf3);
        p2.add(l5);
        p2.add(tf4);
        p2.add(l6);
        p2.add(tf5);
        p2.add(l7);
        p2.add(tf6);
        p2.add(l8);
        p2.add(tf8);
        p2.add(l9);
        p2.add(tf9);
        p2.add(bt1);
        p2.add(bt2);

        try {                   // Fetch and display contact details for the given ID
            ConnectionClass obj = new ConnectionClass();        // Connect to the database
            String q = "select * from add_contact where id = '" + idno + "'";       // Query to get contact details
            ResultSet rest = obj.stm.executeQuery(q);

            //Populating the fields with data stored in database
            while (rest.next()) {
                tf10.setText(rest.getString("Id"));
                tf1.setText(rest.getString("name"));
                tf2.setText(rest.getString("nickname"));
                tf3.setText(rest.getString("phone"));
                tf4.setText(rest.getString("mobile"));
                tf5.setText(rest.getString("email"));
                tf6.setText(rest.getString("address"));
                tf7.setText(rest.getString("company"));
                tf8.setText(rest.getString("position"));
                tf9.setText(rest.getString("group_name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tf10.setEditable(false);

        setLayout(new BorderLayout(10, 10));
        add(p1, "North");
        add(p2, "Center");
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {         // If "Edit" button is clicked
            int Cid = Integer.parseInt(tf10.getText());     // Get the contact ID
            String name = tf1.getText();
            String nickName = tf2.getText();
            String phone = tf3.getText();
            String mobile = tf4.getText();
            String email = tf5.getText();
            String address = tf6.getText();
            String company = tf7.getText();
            String position = tf8.getText();
            String group = tf9.getText();

            try{
                ConnectionClass obj = new ConnectionClass();

                // // Update query to modify contact details
                String q1 = "update add_contact set name ='" + name +"', nickname = '" + nickName +
                        "', phone ='" + phone + "', mobile ='" + mobile + "', email = '" + email+ "', address ='" + address + "', company = '" + company + "', position ='" + position + "', group_name = '" + group + "' where id = '" + Cid + "'";
                int check = obj.stm.executeUpdate(q1);      // Execute the update query
                if(check == 1){
                    JOptionPane.showMessageDialog(null, "Your data is successfully updated!");
                    this.setVisible(false);
                    new SearchDataTableForEdit(name).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please, fill all your details carefully!");

                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        if(e.getSource() == bt2){
            this.setVisible(false);
            new Home().setVisible(true);
        }
    }
}
