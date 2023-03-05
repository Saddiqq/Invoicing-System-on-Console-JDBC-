package invoicingSystem2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {

    // Initialize database with relevant tables
    public static void initialize(String url, String username, String password) throws SQLException {
        
        // Connect to database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            
            // Create table for invoices
            String createInvoicesTable = "CREATE TABLE invoices (" +
                    "invoice_number INT PRIMARY KEY," +
                    "customer_name VARCHAR(255) NOT NULL," +
                    "phone_number VARCHAR(20)," +
                    "invoice_date DATE NOT NULL," +
                    "total_amount DECIMAL(10, 2) NOT NULL," +
                    "paid_amount DECIMAL(10, 2) NOT NULL," +
                    "balance DECIMAL(10, 2) NOT NULL)";
            try (PreparedStatement statement = connection.prepareStatement(createInvoicesTable)) {
                statement.execute();
            }
            
            // Create table for items
            String createItemsTable = "CREATE TABLE items (" +
                    "item_id INT PRIMARY KEY," +
                    "invoice_number INT NOT NULL," +
                    "item_name VARCHAR(255) NOT NULL," +
                    "unit_price DECIMAL(10, 2) NOT NULL," +
                    "quantity INT NOT NULL," +
                    "qty_amount DECIMAL(10, 2) NOT NULL," +
                    "FOREIGN KEY (invoice_number) REFERENCES invoices (invoice_number))";
            try (PreparedStatement statement = connection.prepareStatement(createItemsTable)) {
                statement.execute();
            }
        }
    }
}




