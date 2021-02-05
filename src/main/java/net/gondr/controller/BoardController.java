package net.gondr.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.lucy.security.xss.LucyXssFilter;
import com.nhncorp.lucy.security.xss.XssSaxFilter;

import net.gondr.domain.BoardVO;
import net.gondr.domain.UploadResponse;
import net.gondr.domain.UserVO;
import net.gondr.service.BoardService;
import net.gondr.service.UserService;
import net.gondr.util.FileUtil;
import net.gondr.util.MediaUtil;
import net.gondr.validator.BoardValidator;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private UserService user_service;
	
	private BoardValidator validator = new BoardValidator();
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String viewListPage(  ) {
		
		return "board/list";
	}
	
	@RequestMapping( value="view/{id}" , method=RequestMethod.GET )
	public String viewArticle( @PathVariable Integer id , Model model ) {
		
		BoardVO board = service.viewArticle(id);
		System.out.println(board);
		model.addAttribute("board" , board);
	
		return "board/view";
	}
	
	@RequestMapping(value="write" , method=RequestMethod.POST)
	public String writeProcess( BoardVO board , HttpSession session , Errors errors ) {
		this.validator.validate(board, errors);
		if( errors.hasErrors() ) {
			return "board/write";
		}
		
		UserVO user = (UserVO) session.getAttribute("user");
		board.setWriter( user.getUserid() );
		
		LucyXssFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml");
		String filtered_content = filter.doFilter(board.getContent());
		board.setContent(filtered_content);
		
		String filtered_title = filter.doFilter(board.getTitle());
		board.setTitle(filtered_title);
		
		service.writeArticle(board);
		user_service.increaseExp( user.getUserid() );
		
		session.setAttribute("user",  user_service.getUserInfo( user.getUserid() ) );
		 
		return ("redirect:/board");
	}

	
	@RequestMapping( value="write" , method=RequestMethod.GET )
	public String viewWritePage( Model model ) {
		
		model.addAttribute("BoardVO" , new BoardVO());
		
		return "board/write";
	}
	
	@RequestMapping(value="upload" , method=RequestMethod.POST)
	@ResponseBody
	public UploadResponse handleImageUpload( @RequestParam("file") MultipartFile file , HttpServletResponse response) {
		
		String uploadPath = context.getRealPath("/images");
		UploadResponse upResponse = new UploadResponse();
		
		try {
			
			String name = file.getOriginalFilename(); // 원본 이름
			String ext = name.substring( name.lastIndexOf(".") + 1 );
			if(MediaUtil.getMediaType(ext) == null) {
				throw  new Exception("올바르지 않은 파일 형식");
			}
			
			String upFile = FileUtil.uploadFile(uploadPath, name, file.getBytes());
			
			// 썸네일
			upResponse.setThumbImage("/images" + upFile);
			
			// 실제 파일 경로
			upFile = "/images/" + upFile.substring(3 , upFile.length());
			upResponse.setUploadImage(upFile);
			upResponse.setResult(true);
			upResponse.setMsg("성공적으로 업로드 됨");
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			upResponse.setMsg( e.getMessage() );
			upResponse.setResult(false);
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
			
		}
		
		return upResponse;
	}
	
}
