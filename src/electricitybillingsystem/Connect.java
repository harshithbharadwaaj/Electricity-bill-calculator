package electricitybillingsystem;

import java.sql.*;

public class Connect {
    Connection c;
    Statement s;

    Connect() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///elecbillsys", "root", "MYsql2003");
            s = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., show an error message)
        }
    }
}
