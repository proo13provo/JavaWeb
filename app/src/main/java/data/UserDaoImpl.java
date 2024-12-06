package data;

import java.util.List;

import model.User;

public interface UserDaoImpl {
	public List<User> SelectAll();
	public void insertUser(User user);
}
