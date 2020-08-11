package com.gmail.dimaliahov.model;

public class Category {

	private int id;
	private int categoryID;
	private String categoryName;

	public Category () {
	}

	public Category (int id, int categoryID, String categoryName) {
		this.id = id;
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public int getCategoryID () {
		return categoryID;
	}

	public void setCategoryID (int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName () {
		return categoryName;
	}

	public void setCategoryName (String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString () {
		return "\nCategory{" +
				"id=" + id +
				", categoryID=" + categoryID +
				", categoryName='" + categoryName + '\'' +
				'}';
	}
}
