package test;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import net.gondr.domain.BoardVO;
import net.gondr.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-context.xml" })
public class BoardServiceTest {
	
	@Autowired
	private BoardService service;

	//@Test
	public void writeBoardTest() {
		BoardVO board = new BoardVO();
		board.setTitle("이번에도 테스트");
		board.setContent("이번에도 테스트 내용입니다. 테스트 테스트");
		board.setWriter("gondr");
		service.writeArticle(board);
	}

	//@Test
	public void viewArticle() {
		BoardVO board = service.viewArticle(5);
		System.out.println( board );
	}

	//@Test
	public void getArticleList() {
		List<BoardVO> list = service.getArticleList(0, 10);
		for (BoardVO data : list) {
			System.out.println(data.getTitle());
		}
	}

	//@Test
	public void updateArticle() {
		BoardVO board = service.viewArticle(2);
		board.setTitle("서비스에서 수정한 제목입니다.");
		board.setContent("서비스에서 수정한 내용입니다.");
		service.updateArticle(board);
	}

	//@Test
	public void deleteArticle() {
		service.deleteArticle(2);
	}

	//@Test
	public void countArticle() {
		System.out.println(service.countArticle());
	}
}
