package com.codingSQL.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {

	// ATRYBUTY
	private int id;
	private String username;
	private String password;
	private String email;
	private User_group person_group_id; //FOREIGN OBJECT

	// KONSTRUKTOR Z PARAMETRAMI
	public User(String username, String email, String password, int person_group_id) {
		this.id = 0;
		this.username = username;
		this.email = email;
		this.setPassword(password);
		this.setPerson_group_id(person_group_id);
	}
	// KONSTRUKTOR do metody
		public User(int id) {
			this.id = id;
		}

	// KONSTRUKTOR DOMYSLNY
	public User() {
	}

	// ZMIANA OPISU FUNKCJI
	public String toString() {
		return (id + " | " + username + " " + password + " " + email + " " + person_group_id);
	}

	// GETTERY I SETTERY
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPerson_group_id() {
		return person_group_id.getId();
	}

	public void setPerson_group_id(int person_group_id) {
		this.person_group_id.setId(person_group_id);
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setPerson_group_id(User_group person_group_id) {
		this.person_group_id = person_group_id;
	}

	
}