package com.softserve.academy;


import java.awt.ItemSelectable;

import com.mysql.jdbc.Connection;
import com.softserve.academy.dashboard.dao.ItemDao;
import com.softserve.academy.dashboard.dao.UserDao;
import com.softserve.academy.dashboard.dto.ItemDTO;
import com.softserve.academy.dashboard.dto.LoginDTO;
import com.softserve.academy.dashboard.dto.UserDTO;
import com.softserve.academy.dashboard.dto.UserItemsDto;
import com.softserve.academy.dashboard.entity.ItemEntity;
import com.softserve.academy.dashboard.entity.UserEntity;
import com.softserve.academy.dashboard.services.ItemService;
import com.softserve.academy.dashboard.services.LoginService;
import com.softserve.academy.dashboard.services.PropertyFile;
import com.softserve.academy.dashboard.services.UserItemsService;
import com.softserve.academy.dashboard.services.UserService;



public class Main {

	public static void main(String[] args) {
	
		/*UserDao userDao = new UserDao();
		ItemDao itemDao = new ItemDao();
		ItemService itemService = new ItemService(itemDao);
		UserService userService = new UserService(userDao);
		ItemDao i = new ItemDao();
		ItemEntity ientity = i.getById((long)5);
		System.out.println(u.delete(uentity));
		System.out.println(i.delete(ientity));
		ItemDTO itemDTO = itemService.getItemDto((long)3);
		UserDTO userDTO = userService.getUserDTO("login1");
		
		LoginDTO login = new LoginDTO("login1", "asd");
		UserItemsService userItemsService = new UserItemsService(userDao, itemDao);
		userItemsService.getUserItems(login);
		LoginDTO login = new LoginDTO("login2", "pass2");

		LoginService loginService = new LoginService(userDao);
		System.out.println(loginService.isLogged(login));*/
		
		System.out.println(PropertyFile.get("user_query_save"));
	
		
	}

}











/*public static void createTable() {
	try {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		conn = DriverManager.getConnection(url, user,pass);
		Statement st = conn.createStatement();
		st.execute("create database db304");
	} catch(SQLException e) {
		e.printStackTrace();
	}
}*/