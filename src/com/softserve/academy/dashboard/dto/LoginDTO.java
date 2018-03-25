package com.softserve.academy.dashboard.dto;

public class LoginDTO {

	private String login;
	private String password;
	
	public LoginDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}

	//getters
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	//setters
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
