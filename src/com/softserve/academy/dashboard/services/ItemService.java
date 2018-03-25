package com.softserve.academy.dashboard.services;


import com.softserve.academy.dashboard.dao.ItemDao;
import com.softserve.academy.dashboard.dto.ItemDTO;
import com.softserve.academy.dashboard.entity.ItemEntity;

public class ItemService {

	private ItemDao itemDao;
	
	public ItemService(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public ItemDTO getItemDto(String title) {
		ItemDTO itemDTO = null;
		ItemEntity itemEntity = itemDao.getByFieldName("title", title).get(0);
		if(itemEntity != null) {
			itemDTO = new ItemDTO(itemEntity.getId(), itemEntity.getTitle(), itemEntity.getDescription(), itemEntity.getUserId());
		}
		return itemDTO;
	}
	

	public boolean removeItem(ItemDTO itemDto) {
		boolean result = false;
		try {
			itemDao.deleteById(itemDto.getIdItem());
			result = true;
		} catch (RuntimeException e) {
			System.out.println("Item not found, message: " + e.getMessage());
		}
		return result;
	}
	
	public boolean setItemDto(ItemDTO itemDTO) {
		boolean result = false;
		ItemEntity itemEntity = new ItemEntity(itemDTO.getTitle(),itemDTO.getDescription(),itemDTO.getIdUser());
		itemEntity.setId(itemDTO.getIdItem());

		if(itemDTO.getIdItem()>0) {
			itemDao.updateById(itemEntity);
			result = true;
		} else if(!isExsistItem(itemEntity.getId())) {
			itemDao.save(itemEntity);
			result = true;
		}

		return result;
	}
	
	public boolean isExsistItem(long id) {
		return itemDao.getById(id)!=null ? true : false;
	}
}
