package com.softserve.academy.dashboard.dto;

public class UserDTO {
	
	private long idUser;
	private String login;
	private String email;
	private String password;
	
	public UserDTO(long idUser,String login, String email, String password) {
		this.idUser = idUser;
		this.email = email;
		this.login = login;
		this.password = password;
	}


	//getters
	public long getIdUser() {
		return idUser;
	}
	

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	//setters
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
