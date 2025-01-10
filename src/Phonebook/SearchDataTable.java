package Phonebook;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class SearchDataTable extends JFrame {
    String x[] = {"Id", "Name", "Nick Name", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};
    String y[][] = new String[20][10];
    int i=0, j=0;
    JTable t;
    Font f;

    public SearchDataTable(){

    }
    public SearchDataTable(String name) {
        super("Contact Information");
        setLocation(1, 1);
        setSize(800, 400);

        f = new Font("Arial", Font.BOLD, 20);
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM add_contact WHERE name = '" + name + "'";
            ResultSet rest = obj.stm.executeQuery(query);

            while (rest.next()) {
                y[i][j++] = rest.getString("id");
                y[i][j++] = rest.getString("name");
                y[i][j++] = rest.getString("nickname");
                y[i][j++] = rest.getString("phone");
                y[i][j++] = rest.getString("mobile");
                y[i][j++] = rest.getString("email");
                y[i][j++] = rest.getString("address");
                y[i][j++] = rest.getString("company");
                y[i][j++] = rest.getString("position");
                y[i][j++] = rest.getString("group_name");
                i++;
                j = 0;
            }
            t = new JTable(y,x);
            t.setFont(f);
        }
        catch(Exception ex){
                ex.printStackTrace();
            }
        JScrollPane sp = new JScrollPane(t);
        add(sp);
}

    public static void main(String[] args) {
        new SearchDataTable("ali").setVisible(true);
    }
}
