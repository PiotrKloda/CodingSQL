package com.codingSQL.classesSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {

	//ATRYBUTY
	private int id;
	private String created;
	private String updated;
	private String description;
	private int exercise_id;
	private int users_id;
	
	
	//KONSTRUKTOR Z PARAMETRAMI
	public Solution(String created, String updated, String description, int exercise_id, int users_id){
		this.id=0;
		this.setCreated(created);
		this.setUpdated(updated);
		this.setDescription(description);
		this.setExercise_id(exercise_id);
		this.setUsers_id(users_id);
	}
	
	//KONSTRUKTOR DOMYSLNY
	public Solution() {
	}

	//GET i SET
	public int getId() {
		return id;
	}
	public String getCreated() {
		return created;
	}
	public String getUpdated() {
		return updated;
	}
	public String getDescription() {
		return description;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExercise_id() {
		return exercise_id;
	}
	public int getUsers_id() {
		return users_id;
	}
	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	//TOSTRNG
	public String toString() {
		return (id + " | " + created + " " + updated + " " + description + " " + exercise_id + " " + users_id);
	}
	
	
	//savetoDB 
		public void saveToDB(Connection conn) throws SQLException {
			if (this.id == 0) {
				String sql = "INSERT INTO solution (created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
				String generatedColumns[] = { "ID" };
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql, generatedColumns);
				preparedStatement.setString(1, this.created);
				preparedStatement.setString(2, this.updated);
				preparedStatement.setString(3, this.description);
				preparedStatement.setInt(4, this.exercise_id);
				preparedStatement.setInt(5, this.users_id);
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					this.id = rs.getInt(1);
				}
			} else {
				String sql = "UPDATE solution SET created=?, updated=?, description=?, exercise_id=?, users_id=? where id = ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, this.created);
				preparedStatement.setString(2, this.updated);
				preparedStatement.setString(3, this.description);
				preparedStatement.setInt(4, this.exercise_id);
				preparedStatement.setInt(5, this.users_id);
				preparedStatement.setInt(6, this.id);
				preparedStatement.executeUpdate();
				}
			}
	
		//loadSolutionById
		static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
			String sql = "SELECT * FROM solution where id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Solution loadedSolution = new Solution();
				loadedSolution.id = resultSet.getInt("id");
				loadedSolution.created = resultSet.getString("created");
				loadedSolution.updated = resultSet.getString("updated");
				loadedSolution.description = resultSet.getString("description");
				loadedSolution.exercise_id = resultSet.getInt("exercise_id");
				loadedSolution.users_id = resultSet.getInt("users_id");
			return loadedSolution;}
			return null;}
		
		//loadAllSolutions
		static public Solution[] loadAllSolutions(Connection conn) throws SQLException {
			ArrayList<Solution> solution = new ArrayList<Solution>();
			String sql = "SELECT * FROM solution"; PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Solution loadedSolution = new Solution();
				loadedSolution.id = resultSet.getInt("id");
				loadedSolution.created = resultSet.getString("created");
				loadedSolution.updated = resultSet.getString("updated");
				loadedSolution.description = resultSet.getString("description");
				loadedSolution.exercise_id = resultSet.getInt("exercise_id");
				loadedSolution.users_id = resultSet.getInt("users_id");
				solution.add(loadedSolution);}
			Solution[] uArray = new Solution[solution.size()]; uArray = solution.toArray(uArray);
			return uArray;}
		
		//delete Solution
		public void delete(Connection conn) throws SQLException {
			if (this.id != 0) {
			String sql = "DELETE FROM solution WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id=0;
			}
		}
		
		//All solutions of a user
		static public Solution[] loadAllByUserId (Connection conn, int id) throws SQLException {
			ArrayList<Solution> solutionsList = new ArrayList<>();
			String sql = " SELECT * FROM users JOIN solution ON users.id=solution.users_id WHERE users.id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Solution loadedSolution = new Solution();
//				loadedSolution.setCreated(rs.getString("created"));
//				loadedSolution.setUpdated(rs.getString("updated"));
//				loadedSolution.setDescription(rs.getString("description"));
				loadedSolution.id = rs.getInt("solution.id");
				loadedSolution.created = rs.getString("created");
				loadedSolution.updated = rs.getString("updated");
				loadedSolution.description = rs.getString("description");
				loadedSolution.exercise_id = rs.getInt("exercise_id");
				loadedSolution.users_id = rs.getInt("users_id");
				solutionsList.add(loadedSolution);
			}
			Solution[] sArray = new Solution[solutionsList.size()];
			sArray = solutionsList.toArray(sArray);
			return sArray;
		}
		
		//All solutions of an exercise
			static public Solution[] loadAllByExerciseId (Connection conn, int id) throws SQLException {
				ArrayList<Solution> solutionsList = new ArrayList<>();
				String sql = " SELECT * FROM exercise JOIN solution ON exercise.id=solution.exercise_id WHERE exercise.id=?;";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Solution loadedSolution = new Solution();
					loadedSolution.id = rs.getInt("solution.id");
						loadedSolution.setCreated(rs.getString("created"));
						loadedSolution.setUpdated(rs.getString("updated"));
						loadedSolution.setDescription(rs.getString("description"));
					loadedSolution.exercise_id = rs.getInt("exercise_id");
					loadedSolution.users_id = rs.getInt("users_id");
					solutionsList.add(loadedSolution);
				}
				Solution[] sArray = new Solution[solutionsList.size()];
				sArray = solutionsList.toArray(sArray);
				return sArray;
			}
	
}
