package com.softserve.academy.dashboard.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.softserve.academy.dashboard.db.ConnectionManager;

public abstract class ADaoCRUD<TEntity> {

	// Read
	public abstract TEntity getById(Long id);

	public abstract List<TEntity> getByFieldName(String fieldName, String text);

	public abstract List<TEntity> getAll();


	// Update
	public abstract boolean updateById(TEntity entity);

	// Delete
	public abstract boolean deleteById(Long id);

	public abstract boolean delete(TEntity entity);

	// - - - - - - - - Helpful methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	public abstract List<Object> getParameters(TEntity entity);

	// - - - - - - - - - - -get queries - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	public abstract String getInsertQuery();

	public abstract String getSelectByIdQuery();

	public abstract String getSelectByFieldNameQuery(String fieldName);
	public abstract String getAllQuery();
	public abstract String getUpdateByIdQuery();
	public abstract String getDeleteByIdQuery();
	public abstract String getDeleteQuery();



	public boolean save(TEntity entity) {

		boolean result = false;
		try {
			PreparedStatement ps = getPreparedStatement(getInsertQuery());
			List<Object> parameters = getParameters(entity);
			for (int i = 1; i <= parameters.size(); i++) {
				ps.setObject(i, parameters.get(i-1));
			}

			ps.execute();
			System.out.println("Saving done");
		} catch(SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public PreparedStatement getPreparedStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = ConnectionManager
					.getInstance()
					.getConnection()
					.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	public void closePreparedStatement(PreparedStatement ps) {

		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeResultSet(ResultSet rs) {
		try {
			if(rs !=null) {
				if(!rs.isClosed()) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
