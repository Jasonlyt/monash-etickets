package fb.dao;

import fb.entity.User;

public interface UserDao {
	
	public boolean isUserExisted(String username);
	
	public boolean insertNewUser(User user);
}
