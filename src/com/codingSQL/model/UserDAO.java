package com.codingSQL.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

	// savetoDB
	static public void saveToDB(User u) throws SQLException {
		try {
			Connection conn = DbUtil.getConnection();
			if (u.getId() == 0) {
				String sql = "INSERT INTO users(username, email, password, person_group_id) VALUES (?, ?, ?, ?)";
				String generatedColumns[] = { "ID" };
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql, generatedColumns);
				preparedStatement.setString(1, u.getUsername());
				preparedStatement.setString(2, u.getEmail());
				preparedStatement.setString(3, u.getPassword());
				preparedStatement.setInt(4, u.getPerson_group_id());
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					u.setId(rs.getInt(1));
				}
			} else {
				String sql = "UPDATE users SET username=?, email=?, password=?, person_group_id=? where id = ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, u.getUsername());
				preparedStatement.setString(2, u.getEmail());
				preparedStatement.setString(3, u.getPassword());
				preparedStatement.setInt(4, u.getPerson_group_id());
				;
				preparedStatement.setInt(5, u.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Saving failed");
			e.printStackTrace();
		}
	}

	// loadUserById
	static public User loadUserById(int id) throws SQLException {
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM users where id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User loadedUser = new User();
				loadedUser.setId(resultSet.getInt("id"));
				loadedUser.setUsername(resultSet.getString("username"));
				loadedUser.setPassword(resultSet.getString("password"));
				loadedUser.setEmail(resultSet.getString("email"));
				loadedUser.setPerson_group_id(resultSet.getInt("person_group_id"));
				return loadedUser;
			}
		} catch (SQLException e) {
			System.out.println("Loading User by id failed");
			e.printStackTrace();
		}
		return null;
	}

	// loadAllUsers
	static public User[] loadAllUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM users";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User loadedUser = new User();
				loadedUser.setId(resultSet.getInt("id"));
				loadedUser.setUsername(resultSet.getString("username"));
				loadedUser.setPassword(resultSet.getString("password"));
				loadedUser.setEmail(resultSet.getString("email"));
				loadedUser.setPerson_group_id(resultSet.getInt("person_group_id"));
				users.add(loadedUser);
			}
		} catch (SQLException e) {
			System.out.println("Loading All Users failed");
			e.printStackTrace();
		}
		User[] uArray = new User[users.size()];
		uArray = users.toArray(uArray);
		return uArray;
	}

	// delete user
	static public void delete(User u) throws SQLException {
		try {
			Connection conn = DbUtil.getConnection();
			if (u.getId() != 0) {
				String sql = "DELETE FROM users WHERE id= ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, u.getId());
				preparedStatement.executeUpdate();
				u.setId(0);
			}
		} catch (SQLException e) {
			System.out.println("Deleting User failed");
			e.printStackTrace();
		}
	}

	// All solutions of a user
	static public User[] loadAllByGroupId(int id) throws SQLException {
		ArrayList<User> usersList = new ArrayList<>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = " SELECT * FROM user_group JOIN users ON user_group.id=users.person_group_id WHERE user_group.id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User loadedUser = new User();
				loadedUser.setId(rs.getInt("id"));
				loadedUser.setUsername(rs.getString("username"));
				loadedUser.setEmail(rs.getString("email"));
				loadedUser.setPassword(rs.getString("password"));
				loadedUser.setPerson_group_id(rs.getInt("person_group_id"));
				usersList.add(loadedUser);
			}
		} catch (SQLException e) {
			System.out.println("Loading all solutions of a user failed");
			e.printStackTrace();
		}
		User[] uArray = new User[usersList.size()];
		uArray = usersList.toArray(uArray);
		return uArray;
	}

	// to see username of User of a solution - homePage
	static public User[] loadUsersOfLatestSolution(Solution[] sList) {
		User[] uList = new User[sList.length];
		try {
			for (int i = 0; i < sList.length; i++) {
				uList[i] = UserDAO.loadUserById(sList[i].getUsers_id());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}

}
