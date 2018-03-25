package com.softserve.academy.dashboard.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class ConnectionManager {

	private static volatile ConnectionManager instance = null;
	private final Map<Long, Connection> connections;
	private DataSource dataSource;

	public ConnectionManager() {
		this.connections = new HashMap<>();
	}
	
	public static ConnectionManager getInstance() {
		return getInstance(null);
	}
	
	public static ConnectionManager getInstance(DataSource dataSource) {
		if(instance == null) {
			synchronized (ConnectionManager.class) {
				if(instance == null) {
					instance = new ConnectionManager();
				}
			}
		}
		instance.checkStatus(dataSource);
		return instance;
	}
	
	public void checkStatus(DataSource dataSource) {
		/*-		dataSource		this.dataSource		    Action
		 * 			null			null				create default
		 * 			null			not null			nothing
		 * 			not null		null				save dataSource
		 * 			not null		not null			if equals then nothing 
		 */
		if(dataSource == null) {
			if(getDataSource() == null) {
				setDataSource(DataSourceRepository.getDefault());
			}
		} else if ((getDataSource() == null)
				|| (!getDataSource().equals(dataSource))) {
			setDataSource(dataSource);
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		synchronized (ConnectionManager.class) {
			this.dataSource = dataSource;
			registerDriver();
			closeAllConnections();
		}
	}
	
	public void registerDriver() {
		try {
			DriverManager.registerDriver(getDataSource().getJdbcDriver());
		}catch(SQLException e) {
			throw new RuntimeException("failed to regist driver", e);
		}
	}
	
	private Map<Long,Connection> getAllConnections() {
		return this.connections;
	}
	
	private void addConnection(Connection c) {
		getAllConnections().put(Thread.currentThread().getId(), c);
	}
	
	public Connection getConnection()  {
		Connection connection = getAllConnections().get(Thread.currentThread().getId());
		if(connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(getDataSource().getConnectionUrl(),getDataSource().getUserName(),
						getDataSource().getPassword());
			} catch (SQLException e) {
				throw new RuntimeException("failed to create connection", e);
			} catch(ClassNotFoundException e) {
				throw new RuntimeException("failed to regist driver");
			}
			addConnection(connection);
		}
		return connection;
	}
	
	public void closeAllConnections() {
		if(instance != null) {
			for(Long key : instance.getAllConnections().keySet()) {
				if(instance.getAllConnections().get(key) != null) {
					try {
						instance.getAllConnections().get(key).close();
					} catch (SQLException e) {
						throw new RuntimeException("failed to close connection", e);
					}
					instance.getAllConnections().put(key, null);

				}
			}
		}
	}
	
	public void beginTransaction() {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException("failed to connect", e);
		}
	}

	public void commitTransaction() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException("failed to connect", e);
		}
	}

	public void rollbackTransaction() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException("failed to connect", e);
		}
	}

	
	
}
