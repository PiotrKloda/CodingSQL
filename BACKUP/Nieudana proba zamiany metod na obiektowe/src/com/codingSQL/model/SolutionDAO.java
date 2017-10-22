package com.codingSQL.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SolutionDAO {

	// savetoDB
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

	// loadSolutionById
	static public Solution loadSolutionById(int id) throws SQLException {

		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM solution where id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Solution s = new Solution();
				Exercise e = new Exercise();
				User u = new User();
				//s
				s.setId(resultSet.getInt("id"));
				s.setCreated(resultSet.getString("created"));
				s.setUpdated(resultSet.getString("updated"));
				s.setDescription(resultSet.getString("description"));
				//e
				e.setId(resultSet.getInt("exercise_id"));
				//u
				//loadedSolution.setUsers_id(resultSet.getInt("users_id"));
				u.setPerson_group_id(new User_group(resultSet.getString("person_group_id")));
				
				//add e+u to s
				s.setExercise_id(e);
				s.setUsers_id(u);
				return s;
			}
		} catch (SQLException e) {
			System.out.println("Loading Solution by id failed");
			e.printStackTrace();
		}
		return null;
	}

	// loadAllSolutions
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

	// loadAllSolutionsLIMIT
	static public Solution[] loadAllSolutions(int limit) throws SQLException {
		ArrayList<Solution> solution = new ArrayList<Solution>();

		try {
			Connection conn = DbUtil.getConnection();
			//String sql = "SELECT * FROM solution ORDER BY updated DESC LIMIT ?";
			// String sql = "SELECT * FROM solution JOIN exercise ON solution.exercise_id=exercise.id JOIN users ON solution.users_id=users.id ORDER BY updated DESC LIMIT ?";
			// String sql = "SELECT users.username AS username, exercise.title AS title, solution.created AS created, solution.id as id FROM solution JOIN exercise ON solution.exercise_id=exercise.id JOIN users ON solution.users_id=users.id ORDER BY updated DESC LIMIT 2 ";
			 String sql = "SELECT solution.id AS solutionId, created, updated, solution.description AS solutionDescription, exercise_id, users_id, exercise.id AS exerciseId, title, exercise.description AS exerciseDescription, users.id AS usersId, username, email,person_group_id FROM solution JOIN exercise ON solution.exercise_id=exercise.id JOIN users ON solution.users_id=users.id ORDER BY updated DESC LIMIT ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, limit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//3 objects
				Solution s = new Solution();
				Exercise e = new Exercise();
				User u = new User();
				
				//load Solution
				s.setId(resultSet.getInt("SolutionId"));
				s.setCreated(resultSet.getString("created"));
				s.setUpdated(resultSet.getString("updated"));
				s.setDescription(resultSet.getString("SolutionDescription"));
				
				//Load Exercise
				e.setId(resultSet.getInt("exerciseId"));
				e.setTitle(resultSet.getString("title"));
				e.setDescription(resultSet.getString("exerciseDescription"));
				
				//Load User
				u.setId(resultSet.getInt("usersId"));
				u.setUsername(resultSet.getString("username"));
				u.setEmail(resultSet.getString("email"));
				u.setPerson_group_id(new User_group(resultSet.getString("person_group_id")));

				//add e+u to s
				s.setExercise_id(e);
				s.setUsers_id(u);
				solution.add(s);
			}
		} catch (SQLException e) {
			System.out.println("Loading All exercises failed ");
			e.printStackTrace();
		}
		Solution[] uArray = new Solution[solution.size()];
		uArray = solution.toArray(uArray);
		return uArray;
	}

	// delete Solution
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

	// All solutions of a user
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

	// All solutions of an exercise
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
