package com.softserve.academy.dashboard.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserve.academy.dashboard.entity.ItemEntity;
import com.softserve.academy.dashboard.services.PropertyFile;

public class ItemDao extends ADaoCRUD<ItemEntity> {

	private PreparedStatement ps;
	private ResultSet rs;

	public ItemDao() {
	}

	// Select methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - - - - - - - - - - - - - - - - -
	@Override
	public ItemEntity getById(Long id) {
		List<ItemEntity> itemEntityList;
		ItemEntity itemEntity = null;
		try {
			ps = getPreparedStatement(getSelectByIdQuery());
			ps.setLong(1, id);
			rs = ps.executeQuery();
			itemEntityList = getItemEntity(rs);
			// if(getItemEntity(rs).size() == 1) { /*< - - - size == 1*/
			if (itemEntityList.size() == 1) {
				itemEntity = itemEntityList.get(0); // <-- size == 0 ??
			}
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return itemEntity;
	}

	@Override
	public List<ItemEntity> getByFieldName(String fieldName, String text) {

		List<ItemEntity> itemEntityList = new ArrayList<>();
		try {
			ps = getPreparedStatement(getSelectByFieldNameQuery(fieldName));
			ps.setString(1, text);
			rs = ps.executeQuery();
			itemEntityList.addAll(getItemEntity(rs));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return itemEntityList;

	}

	@Override
	public List<ItemEntity> getAll() {
		List<ItemEntity> itemEntityList = null;
		try {
			ps = getPreparedStatement(getAllQuery());
			rs = ps.executeQuery();
			itemEntityList = getItemEntity(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return itemEntityList;
	}

	// Update methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public boolean updateById(ItemEntity entity) {
		boolean b = true;
		try {
			ps = getPreparedStatement(getUpdateByIdQuery());
			List<Object> parameters = getParameters(entity);
			for (int i = 1; i <= parameters.size(); i++) {
				ps.setObject(i, parameters.get(i - 1));
			}
			
			ps.setLong(4, entity.getId());
			ps.execute();
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return b;
	}

	// Delete methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public boolean deleteById(Long id) {
		boolean b = true;
		try {
			ps = getPreparedStatement(getDeleteByIdQuery());
			ps.setLong(1, id);
			ps.execute();
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return b;
	}

	@Override
	public boolean delete(ItemEntity entity) {
		boolean b = true;
		try {
			ps = getPreparedStatement(getDeleteQuery());
			List<Object> paramList = getParameters(entity);
			for (int i = 1; i <= paramList.size(); i++) {
				ps.setObject(i, paramList.get(i - 1));
			}
			ps.execute();
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeResultSet(rs);
		}
		return b;
	}

	// get Queries methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public String getInsertQuery() {
		return PropertyFile.get("item_query_save");
	}

	@Override
	public String getSelectByIdQuery() {
		return PropertyFile.get("item_query_getById");
	}

	@Override
	public String getSelectByFieldNameQuery(String fieldName) {
		return PropertyFile.get("item_query_getByFieldNamePart1") + fieldName
				+ PropertyFile.get("item_query_getByFieldNamePart2");
	}

	@Override
	public String getAllQuery() {
		return PropertyFile.get("item_query_getAll");
	}

	@Override
	public String getUpdateByIdQuery() {
		return PropertyFile.get("item_query_updateById");
	}

	@Override
	public String getDeleteByIdQuery() {
		return PropertyFile.get("item_query_deleteById");
	}

	@Override
	public String getDeleteQuery() {
		return PropertyFile.get("item_query_delete");
	}

	// Other - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	@Override
	public List<Object> getParameters(ItemEntity entity) {

		List<Object> paramList = new ArrayList<>(3);
		paramList.add(entity.getTitle());
		paramList.add(entity.getDescription());
		paramList.add(entity.getUserId());

		return paramList;
	}

	public List<ItemEntity> getItemEntity(ResultSet rs) {
		List<ItemEntity> itemEntityList = new ArrayList<>();
		
		try {
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				ItemEntity itemEntity = new ItemEntity();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = rs.getMetaData().getColumnName(i);
					if (columnName.equalsIgnoreCase("id")) {
						itemEntity.setId(rs.getLong(i));
						
					} else if (columnName.equalsIgnoreCase("title")) {
						itemEntity.setTitle(rs.getString(i));
						
					} else if (columnName.equalsIgnoreCase("description")) {
						itemEntity.setDescription(rs.getString(i));
						
					} else if (columnName.equalsIgnoreCase("user_id")) {
						itemEntity.setUserId(rs.getLong(i));
					}
				}
				itemEntityList.add(itemEntity);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemEntityList;
	}

}
