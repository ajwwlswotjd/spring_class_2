package net.gondr.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import net.gondr.domain.BoardVO;

public class BoardValidator implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
		return BoardVO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required" ,  "글의 제목은 필수값 입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "required" ,  "글의 내용은 필수값 입니다.");
		
	}
	
}
