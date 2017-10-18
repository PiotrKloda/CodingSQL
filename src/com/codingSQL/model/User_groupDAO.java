package com.codingSQL.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User_groupDAO {

	// savetoDB
	static public void saveToDB(User_group ug) throws SQLException {
		try {
			Connection conn = DbUtil.getConnection();
			if (ug.getId() == 0) {
				String sql = "INSERT INTO user_group(name) VALUES (?)";
				String generatedColumns[] = { "ID" };
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql, generatedColumns);
				preparedStatement.setString(1, ug.getName());
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					ug.setId(rs.getInt(1));
				}
			} else {
				String sql = "UPDATE user_group SET name=? where id = ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, ug.getName());
				preparedStatement.setInt(2, ug.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Saving failed");
			e.printStackTrace();
		}
	}

	// loadUser_groupsById
	static public User_group loadUser_groupById(int id) throws SQLException {
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM user_group where id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User_group loadedUser_group = new User_group();
				loadedUser_group.setId(resultSet.getInt("id"));
				loadedUser_group.setName(resultSet.getString("name"));
				return loadedUser_group;
			}
		} catch (SQLException e) {
			System.out.println("Loading User_groups by id failed");
			e.printStackTrace();
		}
		return null;
	}

	// loadAllUser_groups
	static public User_group[] loadAllUser_groups() throws SQLException {
		ArrayList<User_group> user_groups = new ArrayList<User_group>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM user_group";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User_group loadedUser_group = new User_group();
				loadedUser_group.setId(resultSet.getInt("id"));
				loadedUser_group.setName(resultSet.getString("name"));
				user_groups.add(loadedUser_group);
			}
		} catch (SQLException e) {
			System.out.println("Loading All User_groups failed");
			e.printStackTrace();
		}
		User_group[] u_gArray = new User_group[user_groups.size()];
		u_gArray = user_groups.toArray(u_gArray);
		return u_gArray;
	}

	// delete user_group
	static public void delete(User_group ug) throws SQLException {
		if (ug.getId() != 0) {
			try {
				Connection conn = DbUtil.getConnection();
				String sql = "DELETE FROM user_group WHERE id= ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, ug.getId());
				preparedStatement.executeUpdate();
				ug.setId(0);
			} catch (SQLException e) {
				System.out.println("Deleting user_group failed");
				e.printStackTrace();
			}
		}
	}
}
