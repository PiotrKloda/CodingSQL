package com.codingSQL.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User_group {

	//ATRYTBUTY
	private int id;
	private String name;
	
	//KONSTRUKOTR 
	public User_group (String name) {
		this.id=0;
		this.setName(name);
	}
	
	//KONSTRUKTOR DOMYSLNY
	public User_group() {
		this.id=0;
		this.name=null;
	}
	
	//SETTERY I GETTERY
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//toString
	public String toString() {
		return (this.id + " | " + this.name);
	}
	
	//savetoDB 
		public void saveToDB(Connection conn) throws SQLException {
			if (this.id == 0) {
				String sql = "INSERT INTO user_group(name) VALUES (?)";
				String generatedColumns[] = { "ID" };
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql, generatedColumns);
				preparedStatement.setString(1, this.name);
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					this.id = rs.getInt(1);
				}
			} else {
				String sql = "UPDATE user_group SET name=? where id = ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, this.name);
				preparedStatement.setInt(2, this.id);
				preparedStatement.executeUpdate();
				}
			}
		
		//loadUserById
		static public User_group loadUser_groupById(Connection conn, int id) throws SQLException {
			String sql = "SELECT * FROM user_group where id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User_group loadedUser_group = new User_group();
				loadedUser_group.id = resultSet.getInt("id");
				loadedUser_group.name = resultSet.getString("name");
			return loadedUser_group;}
			return null;}
		
		//loadAllUsers
		static public User_group[] loadAllUser_groups(Connection conn) throws SQLException {
			ArrayList<User_group> user_groups = new ArrayList<User_group>();
			String sql = "SELECT * FROM user_group"; PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User_group loadedUser_group = new User_group();
				loadedUser_group.id = resultSet.getInt("id");
				loadedUser_group.name = resultSet.getString("name");
				user_groups.add(loadedUser_group);}
			User_group[] u_gArray = new User_group[user_groups.size()]; 
			u_gArray = user_groups.toArray(u_gArray);
			return u_gArray;}
		
		//delete user
		public void delete(Connection conn) throws SQLException {
			if (this.id != 0) {
			String sql = "DELETE FROM user_group WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id=0;
			}
		}
}