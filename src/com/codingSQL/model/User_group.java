package com.codingSQL.model;

public class User_group {

	private int id;
	private String name;

	public User_group(String name) {
		this.id = 0;
		this.setName(name);
	}

	public User_group() {
		this.id = 0;
		this.name = null;
	}

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

	public String toString() {
		return (this.id + " | " + this.name);
	}

}