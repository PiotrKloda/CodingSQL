package com.codingSQL.model;


public class Solution {

	// ATRYBUTY
	private int id;
	private String created;
	private String updated;
	private String description;
	private Exercise exercise_id;	//FOREIGN OBJECT
	private User users_id;			//FOREIGN OBJECT

	
	// KONSTRUKTOR Z PARAMETRAMI
	public Solution(String created, String updated, String description, int exercise_id, int users_id) {
		this.id = 0;
		this.setCreated(created);
		this.setUpdated(updated);
		this.setDescription(description);
		this.setExercise_id(exercise_id);
		this.setUsers_id(users_id);
	}

	// KONSTRUKTOR DOMYSLNY
	public Solution() {
	}

	// GET i SET
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
		return exercise_id.getId();
	}

	public int getUsers_id() {
		return users_id.getId();
	}

	public void setExercise_id(int exercise_id) {
		this.exercise_id.setId(exercise_id);
	}

	public void setUsers_id(int users_id) {
		this.users_id.setId(users_id);
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Exercise getExercise_id_obj() {
		return exercise_id;
	}

	public User getUsers_id_obj() {
		return users_id;
	}
	
	

	public void setExercise_id(Exercise exercise_id) {
		this.exercise_id = exercise_id;
	}

	public void setUsers_id(User users_id) {
		this.users_id = users_id;
	}

	// TOSTRNG
	public String toString() {
		return (id + " | " + created + " " + updated + " " + description + " " + exercise_id + " " + users_id);
	}

	

}
