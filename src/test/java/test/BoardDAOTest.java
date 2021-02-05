package test;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.gondr.dao.BoardDAO;
import net.gondr.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-context.xml" })
public class BoardDAOTest {
	@Autowired
	private BoardDAO dao;

	//@Test
	public void createBoard() {
		BoardVO board = new BoardVO();
		board.setContent("글내용입니다. 테스트용 내용");
		board.setTitle("글 제목입니다. 테스트용");
		board.setWriter("gondr");
		dao.write(board);
	}

	//@Test
	public void readBoard() {
		BoardVO data = dao.view(1);
		System.out.println(data.getTitle());
		System.out.println(data.getContent());
	}

	//@Test
	public void getListBoard() {
		List<BoardVO> list = dao.list(0, 5);
		for (BoardVO board : list) {
			System.out.println(board.getTitle());
		}
	}

	//@Test
	public void getCnt() {
		Integer cnt = dao.getCnt();
		System.out.println(cnt);
	}

	//@Test
	public void update() {
		BoardVO data = dao.view(1);
		data.setTitle("수정된 제목입니다");
		data.setContent("수정된 내용입니다.");
		dao.update(data);
	}

	//@Test
	public void delete() {
		dao.delete(1);
	}
}
