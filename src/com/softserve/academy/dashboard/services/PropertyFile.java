package com.softserve.academy.dashboard.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFile {

	private static Properties property;
	
	static {
		property = new Properties();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream("SQL_queries.properties");
			/*InputStream fis = 
					PropertyFile.class
					.getClassLoader()
					.getResourceAsStream("resources/SQL_queries.properties");
					new FileInputStream("resources/SQL_queries.properties");*/
			property.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException("failed loading file",e);
		}
		
	}
	
	public static String get(String key) {
		return property.containsKey(key) ? property.getProperty(key) : "";
	}
}
