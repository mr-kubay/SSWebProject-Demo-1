package com.softserve.academy.dashboard.db;

import java.sql.Driver;
import java.sql.SQLException;

public class DataSourceRepository {


	public DataSourceRepository() {

	}
	public static DataSource getDefault() {
		return getMySQLDriver();
	}

	public static DataSource getMySQLDriver() {
		Driver driver;
		try {
			driver = new com.mysql.jdbc.Driver();
		}	catch(SQLException e) {
			throw new RuntimeException("failed to create driver");
		}
		return new DataSource(driver, "jdbc:mysql://localhost:3306/lv304test", "root", "270698jkj");
	}
	
}
