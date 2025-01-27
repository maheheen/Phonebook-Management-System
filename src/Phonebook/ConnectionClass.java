package Phonebook;

import java.awt.*;
import java.sql.*;
public class ConnectionClass {
    Statement stm;
    Connection con;
    public ConnectionClass(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/phonebook", "root", "0007");
            stm = con.createStatement();
//            System.out.println("Connection Established Successfully!");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConnectionClass();
    }
}
