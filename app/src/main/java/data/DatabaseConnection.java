package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection getConection() {
		Connection c = null;

		try {
			// đk MySql driver với driverManager
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

			// các thông số
			String url = "jdbc:mySQL://localhost:3307/dbdemo";
			String username = "root";
			String password = "";

			// Tạo Kết Nối
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if(c != null) {
				c.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
