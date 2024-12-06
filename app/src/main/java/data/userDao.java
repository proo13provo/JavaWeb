package data;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.User;

public class userDao implements UserDaoImpl{
	public List<User> SelectAll() {
		List<User> resultList = new ArrayList<User>();
		try {
			Connection conn = DatabaseConnection.getConection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from user");
			while (rs.next()) {
				int id = rs.getInt("id");
				String uName = rs.getString("name");
				String uPass = rs.getString("Password");
				String uEmail = rs.getString("Email");
				User user = new User(id ,uName, uPass, uEmail);
				resultList.add(user);
			}
			DatabaseConnection.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultList;
	}
	public User selectUserandPass(User u) {
		User user = null;
		try {
			Connection conn = DatabaseConnection.getConection();
			String sql = "SELECT * FROM user where Name = ? and  Password = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, u.getuName());
			st.setString(2, u.getuPassword());
			ResultSet rs  = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("Name");
				String password = rs.getString("Password");
				String email = rs.getString("email");
				
				user = new User(id, name, password, email);
			}
			DatabaseConnection.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConection();
			PreparedStatement preStatement = conn.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?, ?)");
			preStatement.setString(1, user.getuName());
			preStatement.setString(2, user.getuPassword());
			preStatement.setString(4, user.getEmail());
			preStatement.executeUpdate();
			preStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	public static void main(String[] args) {
		userDao u = new userDao();
		User user = u.selectUserandPass(new User(1, "truong", "123", ""));
		System.out.println(user);
		
	}
}
