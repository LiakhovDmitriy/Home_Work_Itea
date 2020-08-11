package com.gmail.dimaliahov.model;

public class Product {

	private int id;
	private String name;
	private int price;
	private int categoryID;
	private int rating;
	private String description;
	private String shortDescription;
	private int discount;
	private String photoUrl;

	public Product () {
	}

	public Product (int id, String name, int price, int categoryID, int rating, String description, String shortDescription, int discount, String photoUrl) {

		this.id = id;
		this.name = name;
		this.price = price;
		this.categoryID = categoryID;
		this.rating = rating;
		this.description = description;
		this.shortDescription = shortDescription;
		this.discount = discount;
		this.photoUrl = photoUrl;
	}

	public int getId () {
		return id;
	}

	public Product setId (int id) {
		this.id = id;
		return this;
	}

	public String getPhotoUrl () {
		return photoUrl;
	}

	public Product setPhotoUrl (String photoUrl) {
		this.photoUrl = photoUrl;
		return this;
	}

	public String getName () {
		return name;
	}

	public Product setName (String name) {
		this.name = name;
		return this;
	}

	public int getPrice () {
		return price;
	}

	public Product setPrice (int price) {
		this.price = price;
		return this;

	}

	public int getCategoryID () {
		return categoryID;
	}

	public Product setCategoryID (int categoryID) {
		this.categoryID = categoryID;
		return this;

	}

	public int getRating () {
		return rating;
	}

	public Product setRating (int rating) {
		this.rating = rating;
		return this;

	}

	public String getDescription () {
		return description;
	}

	public Product setDescription (String description) {
		this.description = description;
		return this;

	}

	public String getShortDescription () {
		return shortDescription;
	}

	public Product setShortDescription (String shortDescription) {
		this.shortDescription = shortDescription;
		return this;

	}

	public int getDiscount () {
		return discount;
	}

	public Product setDiscount (int discount) {
		this.discount = discount;
		return this;

	}

	@Override
	public String toString () {
		return "\nProduct{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", categoryID=" + categoryID +
				", rating=" + rating +
				", description='" + description + '\'' +
				", shortDescription='" + shortDescription + '\'' +
				", discount=" + discount +
				", photoUrl='" + photoUrl + '\'' +
				'}';
	}
}
