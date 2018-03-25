package com.softserve.academy.dashboard.services;

import com.softserve.academy.dashboard.dao.UserDao;
import com.softserve.academy.dashboard.dto.UserDTO;
import com.softserve.academy.dashboard.entity.UserEntity;

public class UserService {

	private UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDTO getUserDTO(String login) {
		UserEntity userEntity = userDao.getByFieldName("login", login).get(0);
			return new UserDTO(userEntity.getId(), userEntity.getLogin(), userEntity.getEmail(), userEntity.getPasswd());
	}
	
	public boolean setUserDTO(UserDTO userDTO) {
		boolean result = false;
		UserEntity userEntity = new UserEntity(userDTO.getLogin(),userDTO.getEmail(),userDTO.getPassword());
		userEntity.setId(userDTO.getIdUser());
		if(userDTO.getIdUser()>0) {
			userDao.updateById(userEntity);
			result = true;
		} else if(!isExistLogin(userDTO.getLogin()) && userDTO.getIdUser() <= 0) {
			userDao.save(userEntity);
			result = true;
		} 
		return result;
		
	}
	
	public boolean isExistLogin(String login) {
		boolean result = true;
		try {
			userDao.getByFieldName("login", login).get(0);
			
		}catch(RuntimeException e) {
			result = false;
			System.out.println("login not found " + e.getMessage());
		}
		return result;
	}


}
