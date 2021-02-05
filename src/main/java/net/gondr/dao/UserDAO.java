  
package net.gondr.dao;

import net.gondr.domain.UserVO;

public interface UserDAO {

	public UserVO getUser(String userid);
	public UserVO loginUser(String userid , String password);
	public void insertUser(UserVO user);
	public void updateExp(String userid);
	public void updateLevel(String userid);
}