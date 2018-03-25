package com.softserve.academy.dashboard.dto;

public class ItemDTO {

	private long idItem;
	private String title;
	private String description;
	private long idUser;
	
	
	public ItemDTO(long idItem, String title, String description, long idUser) {
		this.idItem = idItem;
		this.title = title;
		this.description = description;
		this.idUser = idUser;
	}
	
	public ItemDTO(long idItem) {
		this.idItem = idItem;
	}
	
	//getters
	public long getIdItem() {
		return idItem;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public long getIdUser() {
		return idUser;
	}
	
	//setters
	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	
}
