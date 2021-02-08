package net.gondr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.gondr.domain.BoardVO;
import net.gondr.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession session;
	
	private final String ns = "net.gondr.mappers.BoardMapper";

	@Override
	public void write(BoardVO data) {		
		session.insert( ns + ".write" , data );
	}
	
	@Override
	public void delete(Integer id) {
		session.delete( ns + ".delete" , id );
	}
	
	@Override
	public Integer getCnt() {
		return session.selectOne( ns + ".cnt");
	}
	
	@Override
	public Integer getCnt(Criteria c) {
		return session.selectOne( ns + ".cnt" , c );
	}
	
	@Override
	public List<BoardVO> list(Integer start, Integer end) {
		
		Map<String , Integer> paramMap = new HashMap<>();
		paramMap.put("pageStart" , start);
		paramMap.put("perPageNum" , end);
		return session.selectList( ns + ".list" , paramMap );
		
	}

	@Override
	public List<BoardVO> list(Criteria c) {
		
		return session.selectList( ns + ".list" , c );
	}
	
	@Override
	public void update(BoardVO data) {
		session.update( ns + ".update" , data );
	}
	
	@Override
	public BoardVO view(Integer id) {
		return session.selectOne(ns + ".view", id);
	}
	
}
