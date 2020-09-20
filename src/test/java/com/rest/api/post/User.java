package com.rest.api.post;

//POJO Template for user
public class User {

	//define all the members in the request body
	//create user
	//JSON --Object
	//POJO -- Plain Old Java Object -- Private members --Public methods(getters and setters)
	
	//class vars:
	private String name;
	private String gender;
	private String email;
	private String status;
	
	
	//constructor

	public User(String name, String gender, String email, String status) {
		super();
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.status = status;
	}

	
	//getters and setters methods:
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



	


	
}
