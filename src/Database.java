import java.sql.*;

// Database connection helper class
class Database {
    // Change database name, user, password to match your setup
    private static final String URL = "jdbc:mysql://localhost:3306/kahlua_rental";
    private static final String USER = "root";   // your MySQL user
    private static final String PASSWORD = "Raya1234"; // your MySQL password

    public static Connection getConnection()
            throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
