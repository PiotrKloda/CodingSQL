package com.codingSQL.model;

public class Exercise {

	// ATRYBUTY
	public int id;
	public String title;
	public String description;

	// CONSTRUCOTORS
	public Exercise(String title, String description) {
		// this.id=0;
		setTitle(title);
		setDescription(description);
	}

	public Exercise() {
		this.id = 0;
		this.title = null;
		this.description = null;
	}

	public String toString() {
		return (id + " " + title + " " + description);
	}

	// SET & GET
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(int id) {
		this.id = id;
	}

	
}
