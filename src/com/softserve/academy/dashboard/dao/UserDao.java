package com.softserve.academy.dashboard.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserve.academy.dashboard.entity.UserEntity;
import com.softserve.academy.dashboard.services.PropertyFile;

public class UserDao extends ADaoCRUD<UserEntity> {

	private PreparedStatement ps;
	private ResultSet rs;


	public UserDao() {	}


	// Select methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public UserEntity getById(Long id) {
		List<UserEntity> userEntityList;
		UserEntity userEntity = null;
		try {
			ps = getPreparedStatement(getSelectByIdQuery());
			ps.setLong(1, id);
			rs= ps.executeQuery();
			userEntityList = getUserEntity(rs);
			//		if(getUserEntity(rs).size() == 1) { /*< - - - size == 1*/
			if(userEntityList.size() == 1) {
				userEntity = userEntityList.get(0); // <-- size == 0 ??
			}
			//		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return userEntity;
	}

	@Override
	public List<UserEntity> getByFieldName(String fieldName, String text) {

		List<UserEntity> userEntityList= new ArrayList<>();
		try {
			ps = getPreparedStatement(getSelectByFieldNameQuery(fieldName));
			ps.setString(1, text);
			rs = ps.executeQuery();
			userEntityList = getUserEntity(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return userEntityList;


	}

	@Override
	public List<UserEntity> getAll() {
		List<UserEntity> userEntityList = null;
		try {
			ps = getPreparedStatement(getAllQuery());
			rs = ps.executeQuery();
			userEntityList = getUserEntity(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return userEntityList;
	}



	//Update methods  - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public boolean updateById(UserEntity entity) {
		boolean b = true ;
		try {
			ps = getPreparedStatement(getUpdateByIdQuery());
			List<Object> parameters = getParameters(entity);
			for (int i = 1; i <= parameters.size(); i++) {
				ps.setObject(i, parameters.get(i-1));
			}
			ps.setLong(4, entity.getId());
			ps.execute();
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return b;
	}

	//Delete methods  - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public boolean deleteById(Long id) {
		boolean b = true ;
		try {
			ps = getPreparedStatement(getDeleteByIdQuery());
			ps.setLong(1, id);
			b = ps.execute();
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return b;
	}

	@Override
	public boolean delete(UserEntity entity) {
		boolean b = true;
		try {
			ps = getPreparedStatement(getDeleteQuery());
			List<Object> paramList = getParameters(entity);
			for(int i=1; i<=paramList.size(); i++) {
				ps.setObject(i, paramList.get(i-1));
			}
			ps.execute();
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return b;
	}


	//get Queries methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public String getInsertQuery() {
		return PropertyFile.get("user_query_save");
	}


	@Override
	public String getSelectByIdQuery() {
		return PropertyFile.get("user_query_getById");
	}


	@Override
	public String getSelectByFieldNameQuery(String fieldName) {
		return PropertyFile.get("user_query_getByFieldNamePart1")+ fieldName + PropertyFile.get("user_query_getByFieldNamePart2");
	}


	@Override
	public String getAllQuery() {
		return PropertyFile.get("user_query_getAll");
	}


	@Override
	public String getUpdateByIdQuery() {
		return PropertyFile.get("user_query_updateById");
	}

	@Override
	public String getDeleteByIdQuery() {
		return PropertyFile.get("user_query_deleteById");
	}

	@Override
	public String getDeleteQuery() {
		return PropertyFile.get("user_query_delete");
	}



	//Other - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public List<Object> getParameters(UserEntity entity) {

		List<Object> paramList = new ArrayList<>(3);
		paramList.add(entity.getLogin());
		paramList.add(entity.getEmail());
		paramList.add(entity.getPasswd());
		

		return paramList;
	}


	public List<UserEntity> getUserEntity(ResultSet rs) {
		List<UserEntity> userEntityList = new ArrayList<>();
		UserEntity userEntity = new UserEntity();
		try {
			int columnCount = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				for(int i =1;i<=columnCount;i++) {
					String columnName = rs.getMetaData().getColumnName(i);
					if(columnName.equalsIgnoreCase("id")) {
						userEntity.setId(rs.getLong(i));
					}
					else if(columnName.equalsIgnoreCase("login")) {
						userEntity.setLogin(rs.getString(i));
					}
					else if(columnName.equalsIgnoreCase("email")) {
						userEntity.setEmail(rs.getString(i));
					}
					else if(columnName.equalsIgnoreCase("pass")) {
						userEntity.setPasswd(rs.getString(i));
					}
				}
				userEntityList.add(userEntity);				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userEntityList;
	}


}
