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
	
	//KONSTRUKTOR Z PARAMETRAMI
	public Solution(String created, String updated, String description){
		this.id=0;
		this.setCreated(created);
		this.setUpdated(updated);
		this.setDescription(description);
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
	
	public String toString() {
		return (id + " | " + created + " " + updated + " " + description);
	}
	
	
	//savetoDB 
		public void saveToDB(Connection conn) throws SQLException {
			if (this.id == 0) {
				String sql = "INSERT INTO solution (created, updated, description) VALUES (?, ?, ?)";
				String generatedColumns[] = { "ID" };
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql, generatedColumns);
				preparedStatement.setString(1, this.created);
				preparedStatement.setString(2, this.updated);
				preparedStatement.setString(3, this.description);
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					this.id = rs.getInt(1);
				}
			} else {
				String sql = "UPDATE solution SET created=?, updated=?, description=? where id = ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, this.created);
				preparedStatement.setString(2, this.updated);
				preparedStatement.setString(3, this.description);
				preparedStatement.setInt(4, this.id);
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
				solution.add(loadedSolution);}
			Solution[] uArray = new Solution[solution.size()]; uArray = solution.toArray(uArray);
			return uArray;}
		
		//delete user
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
	
}
