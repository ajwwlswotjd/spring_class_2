package net.gondr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.gondr.domain.LoginDTO;
import net.gondr.domain.RegisterDTO;
import net.gondr.domain.UserVO;
import net.gondr.service.UserService;
import net.gondr.util.FileUtil;

@Controller
@RequestMapping(value="/user/")
public class UserController {
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="profile/{file:.+}" , method=RequestMethod.GET)
	@ResponseBody
	public byte[] getUserProfile( @PathVariable String file ) throws IOException {
		
		String uploadPath = context.getRealPath("/WEB-INF/upload");
		File profile = new File( uploadPath + File.separator + file );
		FileInputStream in = new FileInputStream( profile );
		
		return IOUtils.toByteArray( in );
	}
	
	
	@RequestMapping( value="logout" , method=RequestMethod.GET )
	public String logout( HttpSession session ) {
		
		session.removeAttribute("user");
		return ("redirect:/");
	}
	
	@GetMapping("login")
	public String viewLoginPage(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		return "user/login";
	}
	
	@RequestMapping(value="login" , method=RequestMethod.POST)
	public String loginProcess( LoginDTO dto , HttpSession session ) {
		
		if( dto.getUserid().isEmpty() || dto.getPassword().isEmpty() ) {
			session.setAttribute("login_msg", "비어있는 값이 존재합니다.");
			return ("redirect:/");
		}
		
		UserVO user = service.login(dto.getUserid(), dto.getPassword());
		if(user == null) {
			session.setAttribute("login_msg", "아이디 또는 비밀번호가 잘못되었습니다.");
			return ("redirect:/");
		}
		
		session.setAttribute("user", user);
		return ("redirect:/");
	}
	
	@RequestMapping(value="register" , method=RequestMethod.GET)
	public String viewRegisterPage() {
		
		return "user/register";
	}
	
	@RequestMapping(value="register" , method=RequestMethod.POST)
	public String registerProcess( RegisterDTO dto , HttpSession session ) throws Exception {
		
		if( dto.getUserid().isEmpty() || dto.getName().isEmpty() || dto.getPassword().isEmpty() || dto.getPassword_check().isEmpty() ) {
			session.setAttribute("register_msg", "필수값이 비어있습니다.");
			return ("redirect:/user/register");
		}
		
		if( service.getUserInfo(dto.getUserid()) != null ) {
			session.setAttribute("register_msg", "해당 아이디는 이미 존재하는 아이디 입니다.");
			return ("redirect:/user/register");
		}
		
		if( !dto.getPassword().equals(dto.getPassword_check()) ) {
			session.setAttribute("register_msg", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return ("redirect:/user/register");
		}
		
		String uploadPath = context.getRealPath("/WEB-INF/upload");
		System.out.println(uploadPath);
		
		MultipartFile file = dto.getProfileImg();
		String upFile = FileUtil.uploadFile( uploadPath , file.getOriginalFilename(), file.getBytes() );
		System.out.println(uploadPath + " 에 " + upFile + " 이름으로 업로드 되었습니다.");
		
		UserVO vo = new UserVO();
		vo.setName( dto.getName() );
		vo.setUserid( dto.getUserid() );
		vo.setPassword( dto.getPassword() );
		vo.setImg( upFile );
		service.register(vo);
		
		return ("redirect:/");
	}

}
