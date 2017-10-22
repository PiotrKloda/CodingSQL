package com.codingSQL.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDAO {

	// savetoDB
	public static void saveToDB(Exercise ex) throws SQLException {

		try {
			Connection conn = DbUtil.getConnection();
			if (ex.getId() == 0) {
				String sql = "INSERT INTO exercise(title, description) VALUES (?, ?)";
				String generatedColumns[] = { "ID" };
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql, generatedColumns);
				preparedStatement.setString(1, ex.getTitle());
				preparedStatement.setString(2, ex.getDescription());
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					ex.setId(rs.getInt(1));
				}
			} else {
				String sql = "UPDATE exercise SET title=?, description=? where id = ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, ex.getTitle());
				preparedStatement.setString(2, ex.getDescription());
				preparedStatement.setInt(3, ex.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Saving failed");
			e.printStackTrace();
		}
	}

	// loadExerciseById
	static  public Exercise loadExerciseById(int id) throws SQLException {
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM exercise where id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Exercise loadedExercise = new Exercise();
				loadedExercise.id = resultSet.getInt("id");
				loadedExercise.title = resultSet.getString("title");
				loadedExercise.description = resultSet.getString("description");
				return loadedExercise;
			}
		} catch (SQLException e) {
			System.out.println("Loading Exercise by id failed");
			e.printStackTrace();
		}
		return null;
	}

	// loadAllExercises
	static public Exercise[] loadAllExercises() throws SQLException {

		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "SELECT * FROM exercise";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Exercise loadedExercise = new Exercise();
				loadedExercise.id = resultSet.getInt("id");
				loadedExercise.title = resultSet.getString("title");
				loadedExercise.description = resultSet.getString("description");
				exercises.add(loadedExercise);
			}
		} catch (SQLException e) {
			System.out.println("Loading All exercises failed");
			e.printStackTrace();
		}
		Exercise[] uArray = new Exercise[exercises.size()];
		uArray = exercises.toArray(uArray);
		return uArray;
	}

	// delete Exercise
	static public void delete(Exercise ex) throws SQLException {
		try {
			if (ex.getId() != 0) {
				Connection conn = DbUtil.getConnection();
				String sql = "DELETE FROM exercise WHERE id= ?";
				PreparedStatement preparedStatement;
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, ex.getId());
				preparedStatement.executeUpdate();
				ex.setId(0);
			}
		} catch (SQLException e) {
			System.out.println("Deleting exercise failed");
			e.printStackTrace();
		}
	}
	
	// to see title of Exercise of a solution - homePage
		static public Exercise[] loadExercisesOfLatestSolution(Solution[] sList) {
			Exercise[] eList = new Exercise[sList.length];
			try {
				for (int i = 0; i < sList.length; i++) {
					eList[i] = ExerciseDAO.loadExerciseById(sList[i].getExercise_id());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return eList;
		}

}
