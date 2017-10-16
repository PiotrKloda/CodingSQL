package com.codingSQL.classesSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

public class User {

	//ATRYBUTY
	private int id;
	private String username;
	private String password;
	private String email;

	//KONSTRUKTOR Z PARAMETRAMI
	public User(String username, String email, String password) {
		this.id=0;
		this.username=username;
		this.email=email;
		this.setPassword(password);
	}
	//KONSTRUKTOR DOMYSLNY
	public User() {
		this.id=0;
		this.username=null;
	}
	
	//ZMIANA OPISU FUNKCJI
	public String toString(){
		return (id + " " + username + " " + password + " " + email);
	}

	//GETTERY I SETTERY
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt() );
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//savetoDB 
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE users SET username=?, email=?, password=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			preparedStatement.setInt(4, this.id);
			preparedStatement.executeUpdate();
			}
		}
	
	//loadUserById
	static public User loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM users where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
		return loadedUser;}
		return null;}
	
	//loadAllUsers
	static public User[] loadAllUsers(Connection conn) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users"; PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			users.add(loadedUser);}
		User[] uArray = new User[users.size()]; uArray = users.toArray(uArray);
		return uArray;}
	
	//delete user
	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
		String sql = "DELETE FROM users WHERE id= ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, this.id);
		preparedStatement.executeUpdate();
		this.id=0;
		}
	}
}