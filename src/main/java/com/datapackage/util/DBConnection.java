package com.datapackage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	 private static final String URL = "jdbc:mysql://localhost:3306/TEAapp";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Mobitel#123"; 

	    private DBConnection() {}

	    public static Connection getConnection() throws SQLException, ClassNotFoundException {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }

		 public static void closeConnection(Connection connection) {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing connection: " + e.getMessage());
	            }
	        }
	    }
}