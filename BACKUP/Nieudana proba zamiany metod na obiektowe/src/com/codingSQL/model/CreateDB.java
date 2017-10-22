package com.codingSQL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

	public static String creDataBase = "CREATE DATABASE codingSQL;";
	public static String useDataBase = "use codingSQL;";
	public static String creTab_user_group = "CREATE TABLE user_group("
															+ "id INT(11) AUTO_INCREMENT,"
															+ "name VARCHAR(255),"
															+ "PRIMARY KEY(id));";
	public static String creTab_users = "CREATE TABLE users("
															+ "id BIGINT(20) AUTO_INCREMENT,"
															+ "username VARCHAR(255),"
															+ "email VARCHAR(255),"
															+ "password VARCHAR(245),"
															+ "person_group_id INT(11),"
															+ "PRIMARY KEY(id),"
															+ "FOREIGN KEY(person_group_id) REFERENCES user_group(id)); ";
	public static String creTab_exercise = "CREATE TABLE exercise("
															+ "id INT(11) AUTO_INCREMENT,"
															+ "title VARCHAR(255),"
															+ "description TEXT,"
															+ "PRIMARY KEY(id));";
	public static String creTab_solution = "CREATE TABLE solution("
															+ "id INT(11) AUTO_INCREMENT,"
															+ "created DATETIME,"
															+ "updated DATETIME,"
															+ "description TEXT,"
															+ "exercise_id INT(11),"
															+ "users_id BIGINT(20),"
															+ "PRIMARY KEY (id),"
															+ "FOREIGN KEY (exercise_id) REFERENCES exercise(id),"
															+ "FOREIGN KEY (users_id) REFERENCES users(id) );" ;
	public static void main(String[] args) {
		
		Connection conn;
		try {
			conn = DriverManager.getConnection( "jdbc:mysql://localhost/codingSQL?useSSL=false","root", "coderslab" );
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(creTab_user_group);
			stmt.executeUpdate(creTab_users);
			stmt.executeUpdate(creTab_exercise);
			stmt.executeUpdate(creTab_solution);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
