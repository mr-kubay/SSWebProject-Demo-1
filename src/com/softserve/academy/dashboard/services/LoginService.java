package com.softserve.academy.dashboard.services;

import java.util.List;

import com.softserve.academy.dashboard.dao.UserDao;
import com.softserve.academy.dashboard.dto.LoginDTO;
import com.softserve.academy.dashboard.entity.UserEntity;

public class LoginService {

	private UserDao userDao;
	
	public LoginService(UserDao userDao) {
		this.userDao = userDao;
	}
	public LoginService() {
		this.userDao = new UserDao();
	}
	
	public boolean isLogged(LoginDTO loginDTO) {
		List<UserEntity> usersList ;
		usersList = userDao.getByFieldName("login", loginDTO.getLogin());
		boolean result  = (usersList.size() ==1)&&(usersList.get(0).getPasswd().equals(loginDTO.getPassword()));
		return result;
	}
	
}
