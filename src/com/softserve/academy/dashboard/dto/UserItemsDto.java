package com.softserve.academy.dashboard.dto;

import java.util.ArrayList;
import java.util.List;

public class UserItemsDto {
	
	private String userName;
	private List<ItemDTO> itemsList;
	
	public UserItemsDto(String u, List<ItemDTO> itemsList) {
		this.userName = u;
		this.itemsList = itemsList;
	}

	public UserItemsDto(String u) {
		this.userName = u;
		this.itemsList = new ArrayList<ItemDTO>();
	}

	//getters
	public String getUserName() {
		return userName;
	}

	public List<ItemDTO> getItemsList() {
		return itemsList;
	}

	//setters
	public void setUserId(String s) {
		this.userName= s;
	}

	public void setItemsList(List<ItemDTO> itemsList) {
		this.itemsList = itemsList;
	}
	
	//others
	public void addItemDtoToList(ItemDTO itemDTO) {
		getItemsList().add(itemDTO);
	}
	

}
