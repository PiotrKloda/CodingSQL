package com.codingSQL.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SolutionDAO {

	static public void saveToDB(Solution s) throws SQLException {

		try {
			Connection conn = DbUtil.getConnection();
			if (s.getId() == 0) {
				String sql = "INSERT INTO solution (created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
				String generatedColumns[] = { "ID" };
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql, generatedColumns);
				preparedStatement.setString(1, s.getCreated());
				preparedStatement.setString(2, s.getUpdated());
				preparedStatement.setString(3, s.getDescription());
				preparedStatement.setInt(4, s.getExercise_id());
				preparedStatement.setInt(5, s.getUsers_id());
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					s.setId(rs.getInt(1));
				}
			} else {
				String sql = "UPDATE solution SET created=?, updated=?, description=?, exercise_id=?, users_id=? where id = ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, s.getCreated());
				preparedStatement.setString(2, s.getUpdated());
				preparedStatement.setString(3, s.getDescription());
				preparedStatement.setInt(4, s.getExercise_id());
				preparedStatement.setInt(5, s.getUsers_id());
				preparedStatement.setInt(6, s.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Saving failed");
			e.printStackTrace();
		}
	}

	static public Solution loadSolutionById(int id) throws SQLException {

		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM solution where id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Solution loadedSolution = new Solution();
				loadedSolution.setId(resultSet.getInt("id"));
				loadedSolution.setCreated(resultSet.getString("created"));
				loadedSolution.setUpdated(resultSet.getString("updated"));
				loadedSolution.setDescription(resultSet.getString("description"));
				loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
				loadedSolution.setUsers_id(resultSet.getInt("users_id"));
				return loadedSolution;
			}
		} catch (SQLException e) {
			System.out.println("Loading Solution by id failed");
			e.printStackTrace();
		}
		return null;
	}

	static public Solution[] loadAllSolutions() throws SQLException {

		ArrayList<Solution> solution = new ArrayList<Solution>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM solution";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Solution loadedSolution = new Solution();
				loadedSolution.setId(resultSet.getInt("id"));
				loadedSolution.setCreated(resultSet.getString("created"));
				loadedSolution.setUpdated(resultSet.getString("updated"));
				loadedSolution.setDescription(resultSet.getString("description"));
				loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
				loadedSolution.setUsers_id(resultSet.getInt("users_id"));
				solution.add(loadedSolution);
			}
		} catch (SQLException e) {
			System.out.println("Loading All exercises failed");
			e.printStackTrace();
		}
		Solution[] uArray = new Solution[solution.size()];
		uArray = solution.toArray(uArray);
		return uArray;
	}

	static public Solution[] loadAllSolutions(int limit) throws SQLException {
		ArrayList<Solution> solution = new ArrayList<Solution>();

		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM solution ORDER BY updated DESC LIMIT ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, limit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Solution loadedSolution = new Solution();
				loadedSolution.setId(resultSet.getInt("id"));
				loadedSolution.setCreated(resultSet.getString("created"));
				loadedSolution.setUpdated(resultSet.getString("updated"));
				loadedSolution.setDescription(resultSet.getString("description"));
				loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
				loadedSolution.setUsers_id(resultSet.getInt("users_id"));
				solution.add(loadedSolution);
			}
		} catch (SQLException e) {
			System.out.println("Loading All exercises failed ");
			e.printStackTrace();
		}
		Solution[] uArray = new Solution[solution.size()];
		uArray = solution.toArray(uArray);
		return uArray;
	}

	static public void delete(Solution s) throws SQLException {
		try {
			Connection conn = DbUtil.getConnection();
			if (s.getId() != 0) {
				String sql = "DELETE FROM solution WHERE id= ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, s.getId());
				preparedStatement.executeUpdate();
				s.setId(0);
				;
			}
		} catch (SQLException e) {
			System.out.println("Deleting exercise failed");
			e.printStackTrace();
		}

	}

	static public Solution[] loadAllByUserId(int id) throws SQLException {
		ArrayList<Solution> solutionsList = new ArrayList<>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = " SELECT * FROM users JOIN solution ON users.id=solution.users_id WHERE users.id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Solution loadedSolution = new Solution();
				loadedSolution.setId(resultSet.getInt("id"));
				loadedSolution.setCreated(resultSet.getString("created"));
				loadedSolution.setUpdated(resultSet.getString("updated"));
				loadedSolution.setDescription(resultSet.getString("description"));
				loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
				loadedSolution.setUsers_id(resultSet.getInt("users_id"));
				solutionsList.add(loadedSolution);
			}
		} catch (SQLException e) {
			System.out.println("Loading all solutions of a user failed");
			e.printStackTrace();
		}
		Solution[] sArray = new Solution[solutionsList.size()];
		sArray = solutionsList.toArray(sArray);
		return sArray;
	}

	static public Solution[] loadAllByExerciseId(int id) throws SQLException {
		ArrayList<Solution> solutionsList = new ArrayList<>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = " SELECT * FROM exercise JOIN solution ON exercise.id=solution.exercise_id WHERE exercise.id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Solution loadedSolution = new Solution();
				loadedSolution.setId(resultSet.getInt("id"));
				loadedSolution.setCreated(resultSet.getString("created"));
				loadedSolution.setUpdated(resultSet.getString("updated"));
				loadedSolution.setDescription(resultSet.getString("description"));
				loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
				loadedSolution.setUsers_id(resultSet.getInt("users_id"));
				solutionsList.add(loadedSolution);
			}
		} catch (SQLException e) {
			System.out.println("Loading all solutions of an exercise failed");
			e.printStackTrace();
		}
		Solution[] sArray = new Solution[solutionsList.size()];
		sArray = solutionsList.toArray(sArray);
		return sArray;
	}

}
