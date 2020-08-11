package db;

import model.UserPojo;

import java.sql.*;


public class DBwork {
	private final String SELECT_USER = "SELECT NAME FROM `user` WHERE login = ? AND password = ?;";
	private final String GET_USER = "SELECT name, password, gender, address, comment FROM `user` WHERE login = ?;";
	private final String SET_USER = "INSERT INTO user (login, password, name, gender, address, comment) VALUES (?, ?, ?, ?, ?, ?)";

	public DBwork () {
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

	public static void main (String[] args) {
		DBwork dbWork = new DBwork();
		UserPojo userPojo = new UserPojo ("log", "pas", "nam", "male", "add", "comme");
		System.out.println (dbWork.setUser (userPojo));
//		System.out.println (dbWork.getName ("login", "password"));

	}

	public Connection getConnection () {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/itea_db?" + "user=root&password=password&serverTimezone=UTC");
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
		try (PreparedStatement ps = con.prepareStatement(SELECT_USER);) {
			ps.setString(1, login);
			ps.setString(2, password);
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

	public UserPojo getUser (String login) {
		UserPojo userPojo = new UserPojo();
		userPojo.setLogin(login);

		Connection con = getConnection();
		try (PreparedStatement ps = con.prepareStatement(GET_USER);) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userPojo.setName(rs.getString("name"));
				userPojo.setPassword(rs.getString("password"));
				userPojo.setGender(rs.getString("gender"));
				userPojo.setAddress(rs.getString("address"));
				userPojo.setComment(rs.getString("comment"));
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
			ps.setString(2, userPojo.getPassword());
			ps.setString(3, userPojo.getName());
			ps.setString(4, userPojo.getGender());
			ps.setString(5, userPojo.getAddress());
			ps.setString(6, userPojo.getComment());
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

}
