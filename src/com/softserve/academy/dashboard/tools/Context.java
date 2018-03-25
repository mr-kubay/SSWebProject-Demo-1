package com.softserve.academy.dashboard.tools;


import com.softserve.academy.dashboard.dao.ItemDao;
import com.softserve.academy.dashboard.dao.UserDao;
import com.softserve.academy.dashboard.services.ItemService;
import com.softserve.academy.dashboard.services.LoginService;
import com.softserve.academy.dashboard.services.UserItemsService;
import com.softserve.academy.dashboard.services.UserService;
//mine
public class Context {

	private static volatile Context instance = null;
	
	private UserDao userDao;
	private ItemDao itemDao;
	
	private UserService userService;
	private ItemService itemService;
	private LoginService loginService;
	private UserItemsService userItemsService;
	
	private Context() {
		initComponents();
	}
	
	public static Context getInstance() {
		if (instance == null) {
			synchronized (Context.class) {
				if (instance == null) {
					instance = new Context();
				}
			}
		}
		return instance;
	}
	
	private void initComponents() {
		this.userDao = new UserDao();
		this.itemDao = new ItemDao();
		this.userService = new UserService(userDao);
		this.itemService = new ItemService(itemDao);
		this.loginService = new LoginService(userDao);
		this.userItemsService = new UserItemsService(userDao, itemDao);
	}

	//getters
	public UserDao getUserDao() {
		return userDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public UserItemsService getUserItemsService() {
		return userItemsService;
	}

	
	
	
}
