package net.gondr.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.gondr.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private SqlSession session; 
	// 공장에서 찍어낸 세션  의존성 주입
	
	// final === const => 가비지 컬렉팅이 이루어지지 않는다.
	private static final String ns = "net.gondr.mappers.UserMapper";
	
	@Override
	public UserVO getUser(String userid) {
		
		return session.selectOne( ns+".selectUser" , userid );
	}
	
	@Override
	public void insertUser(UserVO user) {
		
		session.insert( ns + ".insertUser" , user );
	}
	
	@Override
	public UserVO loginUser(String userid, String password) {
		
		Map<String , String> loginMap = new HashMap<>();
		loginMap.put("userid" , userid);
		loginMap.put("password" , password);
		
		return session.selectOne(ns + ".loginUser" , loginMap);
	}
	
}
