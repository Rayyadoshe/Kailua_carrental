import java.util.Scanner;
import java.sql.*;

import static javax.management.remote.JMXConnectorFactory.connect;


public class Main {

    // show all cars metoden
    private static void showAllCars(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Car";
//    Statement statement = new Statement();
        try (Statement statement = conn.createStatement())
        ResultSet rs = statement.executeQuery(sql) {
            System.out.println("\nCustomers: ");
            while (rs.next()) {
                System.out.println(rs.getString("Driver Licence: ") + " - " +
                        rs.getString("Name") + ", " +
                        rs.getString("City"));
            }
        }
    }

    // Insert a new customer
    private static void insertCustomer(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter driver license: ");
        String dl = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        System.out.print("Enter zip: ");
        String zip = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();


        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dl);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, city);
            pstmt.setString(5, zip);
            pstmt.setString(6, phone);
            pstmt.setString(7, email);
            pstmt.executeUpdate();
            System.out.println("✅ Customer inserted!");
        }
    }

          // Show all customers
              private static void showAllCustomers(Connection conn) throws SQLException {
                  String sql = "SELECT * FROM Customer";
                  try (Statement stmt = conn.createStatement();
                       ResultSet rs = stmt.executeQuery(sql)) {
                      System.out.println("\nCustomers:");
                      while (rs.next()) {
                          System.out.println(rs.getString("DriverLicense") + " - " +
                                             rs.getString("Name") + ", " +
                                             rs.getString("City"));
                      }
                  }
              }








// main metoden starter her
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         connect();

        while (true) {
            System.out.println("\n=== Kailua Car Rental ===");

            System.out.println("1. Show all cars");
            System.out.println("2. Show all customers");
            System.out.println("3. Insert new customer");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();


            try (Connection conn = Database.getConnection()) {

                switch (choice) {
                    case 1 -> showAllCars(conn);
                    case 2 -> showAllCustomers(conn);
                    case 3 -> insertCustomer(conn, scanner);
                    case 4 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
