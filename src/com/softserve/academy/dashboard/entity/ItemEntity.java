package com.softserve.academy.dashboard.entity;


public class ItemEntity extends BaseEntity{

	
	
    private String title;
    private String description;
    private Long userId;
    
	public ItemEntity(String title, String description, Long userId) {
		this.title = title;
		this.description = description;
		this.userId = userId;
	}
	
	public ItemEntity() {}
	
	// setters

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	// getters

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Long getUserId() {
		return userId;
	}
	
	//To string 

	@Override
	public String toString() {
		return "ItemEntity [title=" + title + ", description=" + description + ", userId=" + userId + "]";
	}
	    
}
