package net.gondr.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("net.gondr.controller")
public class CommonExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e , Model model ) {
		e.printStackTrace();
		
		model.addAttribute("title" , e.getClass().getName());
		model.addAttribute("errorMsg" , e.getMessage());
		return "errorpage";
	}
	
}
