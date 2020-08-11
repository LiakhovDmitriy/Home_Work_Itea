package com.gmail.dimaliahov.db;

import com.gmail.dimaliahov.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbProduct {
	private final static String SELECT_PRODUCT_BY_NAME = "SELECT * FROM `product` WHERE name = ?;";
	private final static String SELECT_PRODUCT_BY_ID = "SELECT * FROM `product` WHERE id = ?;";
	private final static String SET_PRODUCT = "INSERT INTO product (name, price, categoryID, rating, description, shortDescription, discount, photoUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String GET_PRODUCTS = "SELECT * FROM PRODUCT";
	private final static String GET_COUNT = "select count(*) from product";

	public DbProduct () {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e){
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public static void main (String[] args) throws SQLException {
		Product product = new Product(1, "ad", 123, 123, 123, "sdv", "fwe", 12, "sdv");
		DbProduct dbProduct = new DbProduct();
		dbProduct.setProduct(product);
	}

	public Connection getConnection () {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itea_db?" + "user=root&product&password=password&serverTimezone=UTC");
		} catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	public List<Product> getProducts () {
		List<Product> products = new ArrayList<Product>();

		Connection con = getConnection();
		try (Statement ps = con.createStatement()) {

			ResultSet rs = ps.executeQuery(GET_PRODUCTS);
			while (rs.next()) {
				Product product = new Product()
						.setId(rs.getInt("id"))
						.setName(rs.getString("name"))
						.setPrice(rs.getInt("price"))
						.setCategoryID(rs.getInt("categoryID"))
						.setRating(rs.getInt("rating"))
						.setDescription(rs.getString("description"))
						.setShortDescription(rs.getString("shortDescription"))
						.setDiscount(rs.getInt("discount"))
						.setPhotoUrl(rs.getString("photoUrl"));
				products.add(product);
			}

			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return products;
	}

	public Product getProductByName (String name) {
		Product product = new Product();
		product.setName(name);
		Connection con = getConnection();
		try (PreparedStatement ps = con.prepareStatement(SELECT_PRODUCT_BY_NAME);) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product.setPrice(rs.getInt("price"));
				product.setCategoryID(rs.getInt("categoryID"));
				product.setRating(rs.getInt("rating"));
				product.setDescription(rs.getString("description"));
				product.setShortDescription(rs.getString("shortDescription"));
				product.setDiscount(rs.getInt("discount"));
			}
			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return product;
	}

	public Product getProductByID (int id) {
		Product product = new Product();
		product.setId(id);
		Connection con = getConnection();
		try (PreparedStatement ps = con.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategoryID(rs.getInt("categoryID"));
				product.setRating(rs.getInt("rating"));
				product.setDescription(rs.getString("description"));
				product.setShortDescription(rs.getString("shortDescription"));
				product.setDiscount(rs.getInt("discount"));
				product.setPhotoUrl(rs.getString("photoUrl"));
			}
			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return product;
	}

	public boolean setProduct (Product product) {
		Connection con = getConnection();
		try (PreparedStatement ps = con.prepareStatement(SET_PRODUCT)) {
			System.out.println(product);
			ps.setString(1, product.getName());
			ps.setString(2, String.valueOf(product.getPrice()));
			ps.setString(3, String.valueOf(product.getCategoryID()));
			ps.setString(4, String.valueOf(product.getRating()));
			ps.setString(5, product.getDescription());
			ps.setString(6, product.getShortDescription());
			ps.setString(7, String.valueOf(product.getDiscount()));
			ps.setString(8, product.getPhotoUrl());
			int rows = ps.executeUpdate();

			System.out.println(" db product ");
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return true;
	}

	public int getCountAll () {
		Connection con = getConnection();
		int count = 0;
		try (Statement s = con.createStatement();) {

			ResultSet rs = s.executeQuery(GET_COUNT);
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}
}
