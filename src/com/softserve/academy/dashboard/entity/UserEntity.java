package com.softserve.academy.dashboard.entity;


public class UserEntity extends BaseEntity {
	

    private String login;
    private String passwd;
    private String email;
    
    public UserEntity(String login, String email, String passwd) {
		this.login = login;
		this.email = email;
		this.passwd = passwd;
		
	}
    

    
    public UserEntity() {
	}



	//getters 
    
	public String getLogin() {
		return login;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getEmail() {
		return email;
		
	}


	//setters
	
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "UserEntity [login=" + login + ", email=" + email + "]" + ", passwd=" + passwd;
	}



	



	
    
}
