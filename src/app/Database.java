package app;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public Database() {}

    Connection link;

    public Connection getConnection() {

        // MySQL database credentials
        String databaseName = "cms";
        String username = "root";
        String password = "1532486579";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Creating a connection with MySQL database
            link = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName, username, password);
        } catch (Exception e) {
            // Catching exception
            System.err.println("Exception occurred in Database.java: " + e);
        }
        return link;
    }
}
