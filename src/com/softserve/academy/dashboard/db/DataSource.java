package com.softserve.academy.dashboard.db;

import java.sql.Driver;


public class DataSource {
	private Driver jdbcDriver;
	private String connectionUrl;
	private String userName;
	private String password;
	
	public DataSource(Driver jdbcDriver, String connectionUrl, String userName, String password) {
		this.jdbcDriver = jdbcDriver;
		this.connectionUrl = connectionUrl;
		this.userName = userName;
		this.password = password;
	}

	
	//getters
	
	public Driver getJdbcDriver() {
		return jdbcDriver;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	//setters
	public void setJdbcDriver(Driver jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object dataSource) {
		boolean result = false; 
		if (dataSource instanceof DataSource) {
			result = getJdbcDriver().getClass().getName()
						.equals(((DataSource) dataSource).getJdbcDriver().getClass().getName())
					&& getConnectionUrl().equals(((DataSource) dataSource).getConnectionUrl())
					&& getUserName().equals(((DataSource) dataSource).getUserName())
					&& getPassword().equals(((DataSource) dataSource).getPassword());
		} 
		return result;
	}
	

}
