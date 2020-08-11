package com.gmail.dimaliahov.db;


import com.gmail.dimaliahov.model.UserPojo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class DbUser {

	private final String SELECT_NAME_USER = "SELECT NAME FROM `user` WHERE login = ? AND password = ?;";
	private final String GET_USER = "SELECT name, gender, country, city, address, phone, role FROM `user` WHERE login = ? AND password = ?;";
	private final String SET_USER = "INSERT INTO user (login, password, name, gender, country, city, address, phone, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String SALT = "mWeRtY!4$5^78Jbr";

	public DbUser () {
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
		DbUser dbWork = new DbUser();
		UserPojo userPojo = new UserPojo("log", "pas", "nam", "male", "add", "comme", "123", "sdv","role");
		System.out.println(dbWork.setUser(userPojo));
		System.out.println(dbWork.getName("login", "password"));


	}

	public Connection getConnection () {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itea_db?" + "user=root&password=password&serverTimezone=UTC");
		} catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	public String getName (String login, String password) {
		String name = null;
		Connection con = getConnection();
		try (PreparedStatement ps = con.prepareStatement(SELECT_NAME_USER);) {
			ps.setString(1, login);
			ps.setString(2, hashPass(SALT + password + SALT));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
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
		return name;
	}

	public UserPojo getUser (String login, String password) throws SQLException {
		UserPojo userPojo = new UserPojo();
		userPojo.setPassword(password);
		userPojo.setLogin(login);

		Connection con = getConnection();
		try (PreparedStatement ps = con.prepareStatement(GET_USER);) {
			ps.setString(1, login);
			ps.setString(2, hashPass(SALT + password + SALT));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userPojo.setName(rs.getString("name"));
				userPojo.setGender(rs.getString("gender"));
				userPojo.setCountry(rs.getString("country"));
				userPojo.setCity(rs.getString("city"));
				userPojo.setAddress(rs.getString("address"));
				userPojo.setPhone(rs.getString("phone"));
				userPojo.setRole(rs.getString("role"));
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
		return userPojo;
	}

	public boolean setUser (UserPojo userPojo) {
		Connection con = getConnection();
		try (PreparedStatement ps = con.prepareStatement(SET_USER);) {
			System.out.println(userPojo);
			ps.setString(1, userPojo.getLogin());
			ps.setString(2, hashPass(SALT + userPojo.getPassword() + SALT));
			ps.setString(3, userPojo.getName());
			ps.setString(4, userPojo.getGender());
			ps.setString(5, userPojo.getCountry());
			ps.setString(6, userPojo.getCity());
			ps.setString(7, userPojo.getAddress());
			ps.setString(8, userPojo.getPhone());
			ps.setString(9, userPojo.getRole());
			int rows = ps.executeUpdate();


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

	private String hashPass (String password) throws SQLException {
		String hash = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(StandardCharsets.UTF_8.encode(password));
			hash = String.format("%032x", new BigInteger(messageDigest.digest()));
		} catch (NoSuchAlgorithmException e){
			throw new SQLException();
		}
		return hash;
	}

}
