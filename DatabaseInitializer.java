package invoicingSystem2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

	String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;" + "trustServerCertificate=true";
	String user = "sa";
	String pass = "root";
Connection con = null;

try
	{
		Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
//// Table 1 ////
		String sql = "CREATE TABLE Customers (Customer_Id INT PRIMARY KEY IDENTITY, "
				+ "Customer_Full_Name VARCHAR(25) NOT NULL, " + "Customer_Phone_Number VARCHAR(10),);"
/// Table 2 ////
				+ "CREATE TABLE Invoices (Invoice_Id INT PRIMARY KEY ," + "Customer_Id int REFERENCES Customer_Id(id),"
				+ "Invoice_Date VARCHAR(255)," + "total_Amount int," + "paid_Amount int not null ," + ""
				+ "total_Balance int NOT NULL);"
///////////////////////////////// the 3 table ///////////////////////////////////////
				+ "CREATE TABLE InvoiceItems (\r\n" + " InvoiceItem_Id INTEGER PRIMARY KEY,\r\n"
				+ " Invoice_Id INTEGER REFERENCES Invoice_Id(id),\r\n"
				+ " Customer_Id INTEGER REFERENCES Customer_Id(id),\r\n" + " Number_Of_Items int NOT NULL,\r\n"
				+ " total_Amount int,\r\n" + " paid_Amount DECIMAL NOT NULL\r\n" + " total_Balance DECIMAL NOT NULL\r\n" 
				+ ");\r\n" + "\r\n";
		Integer m = st.executeUpdate(sql);
		if (m >= 1) {
			System.out.println("inserted successfully : " + sql);
		} else {
			System.out.println("insertion failed");
		}
		con.close();
	}catch(
	Exception ex)
	{
		System.err.println(ex);
	}
}}
