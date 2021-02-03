package net.gondr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gondr.dao.UserDAO;
import net.gondr.domain.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO dao;

	@Override
	public UserVO getUserInfo(String userid) {
		
		return dao.getUser(userid);		
	}
	
	@Override
	public UserVO login(String userid, String password) {
		
		return dao.loginUser(userid, password);
	}
	
	@Override
	public void register(UserVO user) {
		
		dao.insertUser(user);
	}
	
}
