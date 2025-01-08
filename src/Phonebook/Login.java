package Phonebook;

import com.mysql.cj.conf.ConnectionPropertiesTransform;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField textField;
    JPasswordField passwordField;
    JPanel p1, p2, p3;
    JButton bt1, bt2;
    Font f1, f2;
    public Login(){
        super("Login Phonebook");
        setLocation(400, 300);
        setSize(530, 250);

        f1 = new Font("Arial", Font.BOLD, 25);
        f2 = new Font("Arial", Font.BOLD, 18);

        l1 = new JLabel("Welcome to Phonebook!");
        l2 = new JLabel("Username:");
        l3 = new JLabel("Password:");

        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f1);
        l2.setFont(f2);
        l3.setFont(f2);

        textField = new JTextField();
        passwordField = new JPasswordField();

        textField.setFont(f2);
        passwordField.setFont(f2);

        bt1 = new JButton("Login");
        bt2 = new JButton("Cancel");
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt1.setFont(f2);
        bt2.setFont(f2);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource(""));
        Image img2 = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        l4 = new JLabel(img3);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(3,2,10,10));
        p1.add(l2);
        p1.add(textField);
        p1.add(l3);
        p1.add(passwordField);
        p1.add(bt1);
        p1.add(bt2);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1,1,10,10));
        p2.add(l1);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(1,1,10,10));
        p3.add(l4);

        setLayout(new BorderLayout(10,20));

        add(p2, "North");
        add(p3, "East");
        add(p1, "Center");
    }
    public void actionPerformed(ActionEvent e){
        String username = textField.getText();
        String password = passwordField.getText();

        if(e.getSource() == bt1){
            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "SELECT * FROM login WHERE USERNAME = '" + username + "' and password = '" + password + "'" ;
                ResultSet rest = obj.stm.executeQuery(query);

                if(rest.next()){
//                    new Home().setVisible(true);
                    System.out.println("You have logged in");
                    this.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Your Password is incorrect");
                    this.setVisible(false);
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        if(e.getSource() == bt2){
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
