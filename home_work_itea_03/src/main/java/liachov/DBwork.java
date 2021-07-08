package liachov;

import java.sql.*;


public class DBwork {
	private  final String SELECT_USER = "SELECT NAME FROM `user` WHERE login = ? AND password = ?;";

	public DBwork () {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/itea_db_01?" + "user=root&password=root");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	public String getName(String login, String password) {
		String name = null;
		Connection con = getConnection();
		try(PreparedStatement ps = con.prepareStatement(SELECT_USER);){
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return name;
	}

	public static void main(String[] args) {
		DBwork dBwork = new DBwork ();
		System.out.println(dBwork.getName("login", "password"));
	}

}
