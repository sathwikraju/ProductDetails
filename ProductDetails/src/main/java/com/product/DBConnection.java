package com.product;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static Connection con;
	public static Connection getConnection() {
		try {
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","Sathwik@@143");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection conn) {
		
	}
	public static void closeConnection(Connection conn) {
		// TODO Auto-generated method stub
		
	}

}