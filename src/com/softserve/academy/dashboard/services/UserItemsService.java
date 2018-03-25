package com.softserve.academy.dashboard.services;

import java.util.List;

import com.softserve.academy.dashboard.dao.ItemDao;
import com.softserve.academy.dashboard.dao.UserDao;
import com.softserve.academy.dashboard.dto.ItemDTO;
import com.softserve.academy.dashboard.dto.LoginDTO;
import com.softserve.academy.dashboard.dto.UserDTO;
import com.softserve.academy.dashboard.dto.UserItemsDto;
import com.softserve.academy.dashboard.entity.ItemEntity;
import com.softserve.academy.dashboard.entity.UserEntity;

public class UserItemsService {
	
	private UserDao userDao;
	private ItemDao itemDao;

	public UserItemsService(UserDao userDao, ItemDao itemDaol) {
		this.userDao = userDao;
		this.itemDao = itemDaol;
	}
	
	public UserItemsDto getUserItems(LoginDTO loginDTO) {
		UserItemsDto userItemsDto = null;
		UserEntity userEntity = userDao.getByFieldName("login", loginDTO.getLogin()).get(0);
		
		if(userEntity != null) {
			 userItemsDto = new UserItemsDto(userEntity.getLogin());
			for(ItemEntity i : itemDao.getAll()) {
				if(i.getUserId() == userEntity.getId()) {
					ItemDTO itemDTO = new ItemDTO(i.getId(), i.getTitle(), i.getDescription(), i.getUserId());
					userItemsDto.addItemDtoToList(itemDTO);
				}
			}
		}
		return userItemsDto;
		
	}
	

}
