package com.codingSQL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
	private static String DB_URL = "jdbc:mysql://localhost/codingSQL?useSSL=false";
	private static String DB_USER = "root";
	private static String DB_PASS = "coderslab";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Failed to connect with Database");
			e.printStackTrace();
		}
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
}