package com.codingSQL.model;


public class User_group {

	// ATRYTBUTY
	private int id;
	private String name;

	// KONSTRUKOTR
	public User_group(String name) {
		this.id = 0;
		this.setName(name);
	}

	// KONSTRUKTOR DOMYSLNY
	public User_group() {
		this.id = 0;
		this.name = null;
	}

	// SETTERY I GETTERY
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}

	// toString
	public String toString() {
		return (this.id + " | " + this.name);
	}

	
}