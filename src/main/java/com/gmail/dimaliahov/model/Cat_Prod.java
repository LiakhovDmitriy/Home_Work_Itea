package com.gmail.dimaliahov.model;

public class Cat_Prod {

	private int id;
	private int categoryID;
	private int productID;

	public Cat_Prod () {
	}

	public Cat_Prod (int id, int categoryID, int productID) {
		this.id = id;
		this.categoryID = categoryID;
		this.productID = productID;
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

	public int getProductID () {
		return productID;
	}

	public void setProductID (int productID) {
		this.productID = productID;
	}

	@Override
	public String toString () {
		return "\nCat_Prod{" +
				"id=" + id +
				", categoryID=" + categoryID +
				", productID=" + productID +
				'}';
	}
}
