package net.gondr.service;

import javax.servlet.http.HttpSession;

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
	public void increaseExp(String userid) {
		UserVO user = dao.getUser(userid);
		if(user != null) {
			dao.updateExp(userid);
			dao.updateLevel(userid);			
		}
	}
	
	@Override
	public UserVO login(String userid, String password) {
		
		UserVO user = dao.loginUser(userid, password);
		if(user != null) {
			dao.updateExp(userid);
			dao.updateLevel(userid);
			user = dao.getUser(userid);
		}
		
		return user;
	}
	
	@Override
	public void register(UserVO user) {
		
		dao.insertUser(user);
	}
	
}
