package test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.gondr.dao.UserDAO;
import net.gondr.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-context.xml"})
public class UserDAOTest {
	
	@Autowired
	private UserDAO dao;
	
	//@Test
	public void testInsertUser() throws Exception {
		UserVO user = new UserVO();
		user.setUserid("test");
		user.setName("테스트");
		user.setPassword("test");
		user.setImg("");
		
		dao.insertUser(user);
	}
	
	@Test
	public void testSelectUser() throws Exception {
		UserVO user = dao.getUser("test");
		System.out.println( user.toString() );
	}
	
	//@Test
	public void testLogin() throws Exception {
		UserVO user = dao.loginUser("test", "test");
		System.out.println( user.toString() );
	}
	
}
