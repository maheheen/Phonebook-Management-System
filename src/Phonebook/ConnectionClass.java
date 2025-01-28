package Phonebook;

import java.sql.*;
public class ConnectionClass {
    Statement stm;              //Object to handle and execute SQL Queries
    Connection con;             //Object to set up a connection to the database
    public ConnectionClass(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");      // Loading MySQL JDBC driver for communication with database.

            // Establish the connection to the database
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/phonebook", "root", "0007");
            stm = con.createStatement();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConnectionClass();
    }
}
